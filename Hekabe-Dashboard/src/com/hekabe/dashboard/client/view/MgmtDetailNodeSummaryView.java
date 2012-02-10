package com.hekabe.dashboard.client.view;

import java.sql.Timestamp;
import java.util.HashMap;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.hekabe.dashboard.client.CommunicationServiceAsync;
import com.hekabe.dashboard.client.data.ClusterGridRecord;
import com.hekabe.dashboard.client.data.ClusterNodesData;
import com.hekabe.dashboard.client.dialog.ExecuteActionDialog;
import com.hekabe.dashboard.shared.parameter.InstanceStatusParameter;
import com.hekabe.dashboard.shared.parameter.StringParameter;
import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.IconButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tree.TreeGridField;

public class MgmtDetailNodeSummaryView extends VLayout {
	
	private ListGrid nodeListGrid;
	private TreeGridField fieldIp;
	private TreeGridField fieldProvider;
	private TreeGridField fieldRegion;
	private TreeGridField fieldStatus;
	private TreeGridField fieldStartCassandraButton;
	private TreeGridField fieldStopCassandraButton;
	private TreeGridField fieldDecommissionButton;
	private TreeGridField fieldTerminateInstanceButton;
	private MgmtDetailView mgmtDetailView;
	private CommunicationServiceAsync rpcService;
	private IconButton btnStartCassandra;
	private IconButton btnStopCassandra;
	private IconButton btnDecommissionCassandra;
	private IconButton btnTerminateInstance;
	
	/**
	 * Creates MgmtDetailNodeSummaryView
	 * @param mgmtDetailView
	 * @param rpcService
	 */
	public MgmtDetailNodeSummaryView(MgmtDetailView mgmtDetailView, CommunicationServiceAsync rpcService) {
		this.mgmtDetailView = mgmtDetailView;
		this.rpcService = rpcService;
		
		nodeListGrid = new ListGrid() {
			@Override
			protected Canvas createRecordComponent(final ListGridRecord record, final Integer colNum) {
				String fieldName = getFieldName(colNum); 
				
				if (fieldName.equals("buttonStartCassandra")) {  
		            btnStartCassandra = new IconButton("start"); 
		            btnStartCassandra.setIcon("[SKIN]/actions/forward.png");
		            btnStartCassandra.setIconOrientation("right");
		            
		            if(record.getAttributeAsString("status").equals(InstanceStatusParameter.RUNNING) || 
		            		record.getAttributeAsString("status").equals(InstanceStatusParameter.DECOMMISSIONED) || 
		            		record.getAttributeAsString("status").equals(InstanceStatusParameter.TERMINATED)) {
		            	btnStartCassandra.disable();
		            }
		            
		            btnStartCassandra.addClickHandler(new ClickHandler() {  
		                public void onClick(ClickEvent event) {  
		                    long timestamp = new Timestamp(record.getAttributeAsDate("startDate").getTime()).getTime();
		                    String ip = record.getAttributeAsString("ip");
		                    String provider = record.getAttributeAsString("provider");
		                    doStartCassandra(timestamp, ip, record, provider);
		                }
		            });  
		            
		            return btnStartCassandra;  
		            
		        } else if(fieldName.equals("buttonStopCassandra")) {  
		        	btnStopCassandra = new IconButton("stop");
		        	btnStopCassandra.setAutoFit(true);
		        	btnStopCassandra.setIcon("[SKIN]/actions/pause.png");
		        	btnStopCassandra.setIconOrientation("right");
		        	
		        	if(record.getAttributeAsString("status").equals(InstanceStatusParameter.STOPPED) ||  
		        			record.getAttributeAsString("status").equals(InstanceStatusParameter.DECOMMISSIONED) || 
		            		record.getAttributeAsString("status").equals(InstanceStatusParameter.TERMINATED)) {
		            	btnStopCassandra.disable();
		            } else if(btnStopCassandra.isDisabled()) {
		            	btnStopCassandra.enable();
		            }
		        	
		        	btnStopCassandra.addClickHandler(new ClickHandler() {  
		                public void onClick(ClickEvent event) {  
		                    long timestamp = new Timestamp(record.getAttributeAsDate("startDate").getTime()).getTime();
		                    String ip = record.getAttributeAsString("ip");
		                    String provider = record.getAttributeAsString("provider");
		                    doStopCassandra(timestamp, ip, record, provider);
		                }
		            });  
		            
		            return btnStopCassandra;  
		            
		        } else if(fieldName.equals("buttonDecommissionCassandra")) {
		        	btnDecommissionCassandra = new IconButton("decommission");
		        	btnDecommissionCassandra.setAutoFit(true);
		        	btnDecommissionCassandra.setIcon("[SKIN]/actions/save.png");
		        	btnDecommissionCassandra.setIconOrientation("right");
		            
		        	if(record.getAttributeAsInt("numberOfNodes") == 1 || 
		        		record.getAttributeAsString("status").equals(InstanceStatusParameter.DECOMMISSIONED) ||
		        		record.getAttributeAsString("status").equals(InstanceStatusParameter.STOPPED) ||
	            		record.getAttributeAsString("status").equals(InstanceStatusParameter.TERMINATED)) {
		        		btnDecommissionCassandra.disable();
		        	}
		        	
		        	btnDecommissionCassandra.addClickHandler(new ClickHandler() {  
		                public void onClick(ClickEvent event) {  
		                    long timestamp = new Timestamp(record.getAttributeAsDate("startDate").getTime()).getTime();
		                    String ip = record.getAttributeAsString("ip");
		                    String provider = record.getAttributeAsString("provider");
		                    doDecommissionCassandra(timestamp, ip, record, provider);
		                }
		            });  
		        	
		            return btnDecommissionCassandra;
		            
		        } else if(fieldName.equals("buttonTerminateInstance")) {
		        	btnTerminateInstance = new IconButton("terminate");
		        	btnTerminateInstance.setAutoFit(true);
		        	btnTerminateInstance.setIcon("[SKIN]/Dialog/stop.png");
		        	btnTerminateInstance.setIconOrientation("right");
		            
		        	if(record.getAttributeAsString("status").equals(InstanceStatusParameter.TERMINATED)) {
		        		btnTerminateInstance.disable();
		        	}
		        	
		        	btnTerminateInstance.addClickHandler(new ClickHandler() {  
		                public void onClick(ClickEvent event) {  
		                    long timestamp = new Timestamp(record.getAttributeAsDate("startDate").getTime()).getTime();
		                    String ip = record.getAttributeAsString("ip");
		                    String provider = record.getAttributeAsString("provider");
		                    doTerminateInstance(timestamp, ip, record, provider);
		                }
		            });  
		        	
		            return btnTerminateInstance;
		            
		        } else {
		        	return null;
		        }
			}
		};
		nodeListGrid.setAutoDraw(false);
		nodeListGrid.setShowRecordComponents(true);
		nodeListGrid.setShowRecordComponentsByCell(true);
		
		nodeListGrid.setHeight(150);
		nodeListGrid.setEmptyMessage("No nodes in this cluster running.");
		fieldIp = new TreeGridField("ip", "IP");
		
		fieldProvider = new TreeGridField("provider", "Provider");
		fieldProvider.setImageURLPrefix("/hekabeDashboard/com.hekabe.dashboard.Dashboard/sc/skins/Enterprise/images/hoster/");
		fieldProvider.setImageURLSuffix(".png");
		HashMap<String,String> providerValueIcons = new HashMap<String, String>();
		providerValueIcons.put(StringParameter.AMAZON_EC2, "aws");
		providerValueIcons.put(StringParameter.ONE_AND_ONE_CLOUD, "1und1");
		fieldProvider.setValueIconHeight(10);
		fieldProvider.setValueIconWidth(16);
		fieldProvider.setValueIcons(providerValueIcons);
		
		fieldRegion = new TreeGridField("region", "Region");
		fieldRegion.setImageURLPrefix("/hekabeDashboard/com.hekabe.dashboard.Dashboard/sc/skins/Enterprise/images/flags/");
		fieldRegion.setImageURLSuffix(".png");
		HashMap<String,String> regionValueIcons = new HashMap<String,String>();
		regionValueIcons.put(StringParameter.US_EAST_VIRGINIA, "us");
		regionValueIcons.put(StringParameter.US_WEST_CALIFORNIA, "us");
		regionValueIcons.put(StringParameter.US_WEST_OREGON, "us");
		regionValueIcons.put(StringParameter.ASIA_PACIFIC_SINGAPORE, "sg");
		regionValueIcons.put(StringParameter.ASIA_PACIFIC_TOKYO, "jp");
		regionValueIcons.put(StringParameter.EU_WEST_IRELAND, "eu");
		regionValueIcons.put(StringParameter.SOUTH_AMERICA_SAO_PAULO, "br");
		regionValueIcons.put("Germany", "de");
		fieldRegion.setValueIconHeight(10);
		fieldRegion.setValueIconWidth(16);
		fieldRegion.setValueIcons(regionValueIcons);
		
		fieldStatus = new TreeGridField("status", "Status");
		
		fieldStartCassandraButton = new TreeGridField("buttonStartCassandra", "Start Cassandra");
		fieldStartCassandraButton.setType(ListGridFieldType.ICON);
		fieldStopCassandraButton = new TreeGridField("buttonStopCassandra", "Stop Cassandra");
		fieldStopCassandraButton.setType(ListGridFieldType.ICON);
		fieldDecommissionButton = new TreeGridField("buttonDecommissionCassandra", "Decommission");
		fieldDecommissionButton.setType(ListGridFieldType.ICON);
		fieldTerminateInstanceButton = new TreeGridField("buttonTerminateInstance", "Terminate Instance");
		fieldTerminateInstanceButton.setType(ListGridFieldType.ICON);
		nodeListGrid.setFields(new ListGridField[] { fieldIp, fieldProvider, fieldRegion, fieldStatus, fieldStartCassandraButton, fieldStopCassandraButton, fieldDecommissionButton, fieldTerminateInstanceButton });
		
		addMember(nodeListGrid);
		
		bind();
	}
	
	/**
	 * Binds handlers
	 */
	private void bind() {
		
	}
	
	/**
	 * Starts cassandra
	 * @param timestamp long
	 * @param ip String
	 * @param record ListGridRecord
	 * @param provider String
	 */
	private void doStartCassandra(long timestamp, String ip, final ListGridRecord record, String provider) {
		String user = mgmtDetailView.getMgmtView().getDashboard().getHeaderView().getUsername();
		final ExecuteActionDialog dialog = new ExecuteActionDialog("Starting cassandra...");
		rpcService.startCassandra(user, timestamp, ip, provider, new AsyncCallback<String>() {

			public void onFailure(Throwable caught) {
				dialog.onFailure();
			}

			public void onSuccess(String result) {
				record.setAttribute("status", InstanceStatusParameter.RUNNING);
				nodeListGrid.refreshFields();
				dialog.onSuccess();
				refreshListGrid();
			}
		});
	}  
	
	/**
	 * Stops cassandra
	 * @param timestamp long
	 * @param ip String
	 * @param record ListGridRecord
	 * @param provider String
	 */
	private void doStopCassandra(long timestamp, String ip, final ListGridRecord record, String provider) {
		String user = mgmtDetailView.getMgmtView().getDashboard().getHeaderView().getUsername();
		final ExecuteActionDialog dialog = new ExecuteActionDialog("Stopping cassandra...");
		rpcService.stopCassandra(user, timestamp, ip, provider, new AsyncCallback<String>() {

			public void onFailure(Throwable caught) {
				dialog.onFailure();
			}

			public void onSuccess(String result) {
				record.setAttribute("status", InstanceStatusParameter.STOPPED);
				nodeListGrid.refreshFields();
				dialog.onSuccess();
				refreshListGrid();
			}
		});
	}
	
	/**
	 * Decommissions node
	 * @param timestamp long
	 * @param ip String
	 * @param record ListGridRecord
	 * @param provider String
	 */
	private void doDecommissionCassandra(long timestamp, String ip, final ListGridRecord record, String provider) {
		String user = mgmtDetailView.getMgmtView().getDashboard().getHeaderView().getUsername();
		final ExecuteActionDialog dialog = new ExecuteActionDialog("Decommissioning cassandra...");
		rpcService.decommissionCassandra(user, timestamp, ip, provider, new AsyncCallback<String>() {

			public void onFailure(Throwable caught) {
				dialog.onFailure();
			}

			public void onSuccess(String result) {
				record.setAttribute("status", InstanceStatusParameter.DECOMMISSIONED);
				nodeListGrid.refreshFields();
				dialog.onSuccess();
				refreshListGrid();
			}
		});
	} 
	
	/**
	 * Terminates instance
	 * @param timestamp long
	 * @param ip String
	 * @param record ListGridRecord
	 * @param provider String
	 */
	private void doTerminateInstance(long timestamp, String ip, final ListGridRecord record, String provider) {
		String user = mgmtDetailView.getMgmtView().getDashboard().getHeaderView().getUsername();
		final ExecuteActionDialog dialog = new ExecuteActionDialog("Terminating instance...");
		rpcService.terminateInstance(user, timestamp, ip, provider, new AsyncCallback<String>() {

			public void onFailure(Throwable caught) {
				dialog.onFailure();
			}

			public void onSuccess(String result) {
				record.setAttribute("status", InstanceStatusParameter.TERMINATED);
				nodeListGrid.refreshFields();
				dialog.onSuccess();
				refreshListGrid();
			}
		});
	} 
	
	/**
	 * Refreshs List Grid
	 */
	private void refreshListGrid() {
		MgmtClustersView clusterView = mgmtDetailView.getMgmtView().getClusterView();
		clusterView.getClusterData(true);
		setDataSource(clusterView.getSelectedRecord());
		nodeListGrid.redraw();
	}
	
	/**
	 * Sets datasource for NodeSummary Tab
	 * @param record
	 */
	protected void setDataSource(ClusterGridRecord record) {
		nodeListGrid.setData(ClusterNodesData.getRecords(record));
	}
	
	/**
	 * Clears form
	 */
	protected void clearForm() {
		nodeListGrid.setData(new ListGridRecord[0]);
	}
}