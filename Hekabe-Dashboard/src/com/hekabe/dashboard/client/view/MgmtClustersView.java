package com.hekabe.dashboard.client.view;

import java.sql.Timestamp;
import java.util.ArrayList;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.hekabe.dashboard.client.CommunicationServiceAsync;
import com.hekabe.dashboard.client.data.ClusterGridData;
import com.hekabe.dashboard.client.data.ClusterGridRecord;
import com.hekabe.dashboard.client.dialog.BenchmarkDialog;
import com.hekabe.dashboard.client.dialog.ExecuteActionDialog;
import com.hekabe.dashboard.shared.NewClusterExchange;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.IconButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.RecordClickEvent;
import com.smartgwt.client.widgets.grid.events.RecordClickHandler;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tree.TreeGridField;

public class MgmtClustersView extends VLayout {
	private ListGrid runningClusterListGrid;
	private ListGridField fieldName;
	private ListGridField fieldMultiRegion;
	private ListGridField fieldNumberOfNodes;
	private ListGridField fieldStartDate;
	private ListGridField fieldBenchmarkButton;
	private ListGridField fieldRebalanceTokenButton;
	private ListGridField fieldDeleteClusterButton;
	private Button btnRefresh;
	private MgmtView mgmtView;
	private CommunicationServiceAsync rpcService;
	private String user;
	private IconButton btnBenchmark;
	private IconButton btnDeleteCluster;
	private IconButton btnRebalanceToken;
	private int selectedRecordIndex = -1;
	
	/**
	 * Creates MgmtClustersView
	 * @param mgmtView
	 * @param rpcService
	 */
	public MgmtClustersView(MgmtView mgmtView, CommunicationServiceAsync rpcService) {
		this.mgmtView = mgmtView;
		this.rpcService = rpcService;				
		
		runningClusterListGrid = new ListGrid() {
			@Override
			protected Canvas createRecordComponent(final ListGridRecord record, final Integer colNum) {
				String fieldName = getFieldName(colNum); 
				
				if (fieldName.equals("buttonBenchmark")) {  
					btnBenchmark = new IconButton("start");
					btnBenchmark.setIcon("[SKIN]/actions/forward.png");
					btnBenchmark.setIconOrientation("right");

					btnBenchmark.addClickHandler(new ClickHandler() {  
		                public void onClick(ClickEvent event) {
		                    long timestamp = new Timestamp(record.getAttributeAsDate("startDate").getTime()).getTime();
		                    doShowBenchmarkDialog(timestamp);
		                }
		            });  
		            
		            return btnBenchmark; 
				} else if(fieldName.equals("buttonDeleteCluster")) {
					btnDeleteCluster = new IconButton("delete");
					btnDeleteCluster.setIcon("[SKIN]/Dialog/stop.png");
					btnDeleteCluster.setIconOrientation("right");
		            
					btnDeleteCluster.addClickHandler(new ClickHandler() {  
		                public void onClick(ClickEvent event) {  
//		                    Window.alert("delete cluster");        
		                    long timestamp = new Timestamp(record.getAttributeAsDate("startDate").getTime()).getTime();
		                    doDeleteCluster(timestamp);
		                }
		            });  
		            
		            return btnDeleteCluster; 
		        } else if(fieldName.equals("buttonRebalanceToken")) {
		        	btnRebalanceToken = new IconButton("rebalance");
		            btnRebalanceToken.setIcon("[SKIN]/FileBrowser/refresh.png");
		            btnRebalanceToken.setIconOrientation("right");
		              
		            btnRebalanceToken.addClickHandler(new ClickHandler() {
						
		            	public void onClick(ClickEvent event) {
//		            		Window.alert("rebalance token");
							long timestamp = new Timestamp(record.getAttributeAsDate("startDate").getTime()).getTime();
							doRebalanceToken(timestamp);
		            	}
		            });
		            
		            return btnRebalanceToken;
		        } else {
		        	return null;
		        }
			}
		};
		runningClusterListGrid.setAutoDraw(false);
		runningClusterListGrid.setShowRecordComponents(true);
		runningClusterListGrid.setShowRecordComponentsByCell(true);
		
		runningClusterListGrid.setPixelSize(978, 150);
		runningClusterListGrid.setEmptyMessage("No clusters running.");
		fieldName = new TreeGridField("clusterName", "Name");
		fieldMultiRegion = new TreeGridField("multiregion", "Multiregion");
		fieldNumberOfNodes = new TreeGridField("numberOfNodes", "Number of nodes");
		fieldStartDate = new TreeGridField("startDate", "Start Date", 120);		
		fieldStartDate.setType(ListGridFieldType.DATE);
		fieldStartDate.setDateFormatter(DateDisplayFormat.TOJAPANSHORTDATETIME);
		fieldBenchmarkButton = new ListGridField("buttonBenchmark", "Benchmark", 120); 
		fieldBenchmarkButton.setType(ListGridFieldType.ICON);
		fieldBenchmarkButton.setAlign(Alignment.CENTER);
		fieldRebalanceTokenButton = new ListGridField("buttonRebalanceToken", "Rebalance");
		fieldRebalanceTokenButton.setType(ListGridFieldType.ICON);
		fieldRebalanceTokenButton.setAlign(Alignment.CENTER);
		fieldDeleteClusterButton = new ListGridField("buttonDeleteCluster", "Delete Cluster");
		fieldDeleteClusterButton.setType(ListGridFieldType.ICON);
		fieldDeleteClusterButton.setAlign(Alignment.CENTER);
		runningClusterListGrid.setFields(new ListGridField[] { fieldName, fieldMultiRegion, fieldNumberOfNodes, fieldStartDate, fieldBenchmarkButton, fieldRebalanceTokenButton, fieldDeleteClusterButton });
		
		btnRefresh = new Button("Refresh");
		btnRefresh.setIcon("[SKIN]/actions/refresh.png");

		addMember(runningClusterListGrid);
		addMember(btnRefresh);
		
		bind();
	}

	/**
	 * Binds handlers.
	 */
	private void bind() {
		btnRefresh.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				btnRefresh.setIcon("[SKIN]/loadingSmall.gif");
				clearForm();
				doGetClusterData(false);				
			}
		});
		
		runningClusterListGrid.addRecordClickHandler(new RecordClickHandler() {
			
			public void onRecordClick(RecordClickEvent event) {
				mgmtView.getDetailView().getNodeSummaryView().setDataSource((ClusterGridRecord) event.getRecord());
				selectedRecordIndex = runningClusterListGrid.getRecordIndex(event.getRecord());
				
				long timestamp = new Timestamp(event.getRecord().getAttributeAsDate("startDate").getTime()).getTime();
				mgmtView.getDetailView().getMgmtConfigView().setTimestamp(timestamp);
				mgmtView.getDetailView().getMgmtConfigView().setUser(user);
				doGetConfigValues(timestamp);
			}
		});
	}

	/**
	 * Displays Benchmark Dialog
	 * @param timestamp
	 */
	private void doShowBenchmarkDialog(long timestamp) {
		BenchmarkDialog bench = new BenchmarkDialog(user, timestamp, rpcService);
		bench.show();
	}  
	
	/**
	 * Rebalnce tokens
	 * @param timestamp
	 */
	private void doRebalanceToken(long timestamp) {
		final ExecuteActionDialog dialog = new ExecuteActionDialog("Rebalancing tokens...");
		rpcService.rebalanceToken(user, timestamp, new AsyncCallback<String>() {

			public void onFailure(Throwable caught) {
				dialog.onFailure();
			}

			public void onSuccess(String result) {
				dialog.onSuccess();
			}
		});
	}
	
	/**
	 * delete cluster
	 * @param timestamp
	 */
	private void doDeleteCluster(long timestamp) {
		final ExecuteActionDialog dialog = new ExecuteActionDialog("Delete cluster...");
		rpcService.deleteCluster(user, timestamp, new AsyncCallback<String>() {

			public void onFailure(Throwable caught) {
				dialog.onFailure();
			}

			public void onSuccess(String result) {
				dialog.onSuccess();
				getClusterData(false);
			}
		});
		
	}
	
	/**
	 * get cluster data
	 * @param isRefresh
	 */
	protected void doGetClusterData(final boolean isRefresh) {
		user = mgmtView.getDashboard().getHeaderView().getUsername();
		rpcService.getClusterData(user, new AsyncCallback<ArrayList<NewClusterExchange>>() {

			public void onSuccess(ArrayList<NewClusterExchange> result) {
				btnRefresh.setIcon("[SKIN]/actions/refresh.png");	
				runningClusterListGrid.setData(ClusterGridData.getRecords(result));
				
				if(isRefresh) {
					mgmtView.getDetailView().getNodeSummaryView().setDataSource((ClusterGridRecord) runningClusterListGrid.getRecord(selectedRecordIndex));
				}
			}
			
			public void onFailure(Throwable caught) {
				btnRefresh.setIcon("[SKIN]/actions/refresh.png");
				SC.say("Failure, couldn't update data.");
			}

		});			
	}
	
	/**
	 * clear form
	 */
	public void clearForm() {
		runningClusterListGrid.setData(new ListGridRecord[0]);
		mgmtView.getDetailView().getNodeSummaryView().clearForm();
	}
	
	/**
	 * get cluster data
	 * @param isRefresh
	 */
	public void getClusterData(boolean isRefresh) {
		btnRefresh.setIcon("[SKIN]/loadingSmall.gif");
		
		doGetClusterData(isRefresh);
	}
	
	/**
	 * get config values
	 * @param timestamp
	 */
	protected void doGetConfigValues(long timestamp) {
		final MgmtDetailConfigView cV = mgmtView.getDetailView().getMgmtConfigView();
		
		rpcService.getConfigValues(user, timestamp, new AsyncCallback<NewClusterExchange>() {

			public void onFailure(Throwable caught) {
				Window.alert("doSetConfigValue failed");
			}

			public void onSuccess(NewClusterExchange result) {
				cV.setHintedHandoff(result.isHintedHandoff());
				cV.setMaximumWindowTime(result.getMaxWindowTime());
				cV.setThrottleDelay(result.getThrottleDelay());
				cV.setSyncType(result.getSyncType());
				cV.setTimeWindow(result.getTimeWindow());
				cV.setCommitlogTotalSpace(result.getCommitlogTotalSpace());
				cV.setReduceCacheAt(result.getReduceCacheAt());
				cV.setReduceCacheCapactity(result.getReduceCacheCapacity());
				cV.setConcurrentReads(result.getConcurrentReads());
				cV.setConcurrentWrites(result.getConcurrentWrites());
				cV.setMemtableTotalSpace(result.getMemtableTotalSpace());
				cV.setMemtableWriterThreads(result.getMemtableWriterThreads());
				cV.setFlushFraction(result.getFlushFraction());
			}
		});		
	}
	
	/**
	 * get selected record
	 * @return
	 */
	public ClusterGridRecord getSelectedRecord() {
		return (ClusterGridRecord) runningClusterListGrid.getRecord(selectedRecordIndex);
	}
}