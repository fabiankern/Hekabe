package com.hekabe.dashboard.client.view;

import java.util.HashMap;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.hekabe.dashboard.client.CommunicationServiceAsync;
import com.hekabe.dashboard.client.dialog.ProgressDialog;
import com.hekabe.dashboard.shared.NewClusterExchange;
import com.hekabe.dashboard.shared.parameter.StringParameter;
import com.hekabe.dashboard.shared.util.Util;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.Dialog;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ComboBoxItem;
import com.smartgwt.client.widgets.form.fields.FloatItem;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.IntegerItem;
import com.smartgwt.client.widgets.form.fields.RadioGroupItem;
import com.smartgwt.client.widgets.layout.VLayout;

public class NewClusterCassandraConfigView extends VLayout {
	
	private Label lblNewLabel_1;
	private DynamicForm dynamicForm_3;
	private RadioGroupItem rgHintedHandoff;
	private IntegerItem intMaxWindowTime;
	private IntegerItem intThrottleDelay;
	private Label lblNewLabel_2;
	private DynamicForm dynamicForm_4;
	private ComboBoxItem cbSyncType;
	private IntegerItem intTimeWindow;
	private IntegerItem intCommitlogTotalSpace;
	private Label lblNewLabel_3;
	private DynamicForm dynamicForm_5;
	private FloatItem floatReduceCacheAt;
	private FloatItem floatReduceCacheCapacity;
	private Label lblNewLabel_4;
	private DynamicForm dynamicForm_6;
	private IntegerItem intConcurrentReads;
	private IntegerItem intConcurrentWrites;
	private Label lblNewLabel_5;
	private DynamicForm dynamicForm_7;
	private IntegerItem intMemtableTotalSpace;
	private IntegerItem intMemtableWriterThreads;
	private IntegerItem intFlushFraction;
	private Button btnStartNewCluster;
	
	private NewClusterView newClusterView;
	private CommunicationServiceAsync rpcService;
	private NewClusterExchange ex;

	/**
	 * 
	 * @param newClusterView
	 * @param rpcService
	 * @param ex
	 */
	public NewClusterCassandraConfigView(NewClusterView newClusterView, CommunicationServiceAsync rpcService, NewClusterExchange ex) {
		this.newClusterView = newClusterView; 
		this.rpcService = rpcService;
		this.ex = ex;
		
		lblNewLabel_1 = new Label("Hinted Handoff");
		lblNewLabel_1.setHeight(30);
		lblNewLabel_1.setWidth(520);
		lblNewLabel_1.setStyleName("label");
		addMember(lblNewLabel_1);
		
		dynamicForm_3 = new DynamicForm();
		rgHintedHandoff = new RadioGroupItem("newRadioGroupItem_1", "Hinted Handoff");
		rgHintedHandoff.setWidth(220);
		rgHintedHandoff.setVertical(false);
		rgHintedHandoff.setValueMap(StringParameter.ENABLED,StringParameter.DISABLED);
		intMaxWindowTime = new IntegerItem("maxWindowTime", "Maximum Window Time (ms)");
		intThrottleDelay = new IntegerItem("throttleDelay", "Throttle Delay (ms)");
		dynamicForm_3.setFields(new FormItem[] { rgHintedHandoff, intMaxWindowTime, intThrottleDelay});
		addMember(dynamicForm_3);
		
		lblNewLabel_2 = new Label("Commit Log");
		lblNewLabel_2.setHeight(30);
		lblNewLabel_2.setWidth(520);
		lblNewLabel_2.setStyleName("label");
		addMember(lblNewLabel_2);
		
		dynamicForm_4 = new DynamicForm();
		cbSyncType = new ComboBoxItem("newComboBoxItem_1", "Synchronisation Type");
		cbSyncType.setValueMap(StringParameter.PERIODIC,
							   StringParameter.BATCH);
		intTimeWindow = new IntegerItem();
		intTimeWindow.setTitle("Time window (ms)");
		intCommitlogTotalSpace = new IntegerItem();
		intCommitlogTotalSpace.setTitle("commitlog_total_space (0 = unlimited) (mb)");
		dynamicForm_4.setFields(new FormItem[] { cbSyncType, intTimeWindow, intCommitlogTotalSpace});
		addMember(dynamicForm_4);
		
		lblNewLabel_3 = new Label("Garbage Collection");
		lblNewLabel_3.setHeight(30);
		lblNewLabel_3.setWidth(520);
		lblNewLabel_3.setStyleName("label");
		addMember(lblNewLabel_3);
		
		dynamicForm_5 = new DynamicForm();
		floatReduceCacheAt = new FloatItem("floatReduceCacheAt", "Reduce cache at");
		floatReduceCacheCapacity = new FloatItem("floatReduceCacheCapacity", "Reduce cache capacity");
		dynamicForm_5.setFields(new FormItem[] { floatReduceCacheAt, floatReduceCacheCapacity });
		addMember(dynamicForm_5);
		
		lblNewLabel_4 = new Label("Read/Write");
		lblNewLabel_4.setHeight(30);
		lblNewLabel_4.setWidth(520);
		lblNewLabel_4.setStyleName("label");
		addMember(lblNewLabel_4);
		
		dynamicForm_6 = new DynamicForm();
		intConcurrentReads = new IntegerItem("intConcurrentReads", "Concurrent Reads");
		intConcurrentWrites = new IntegerItem("intConcurrentWrites", "Concurrent Writes");
		dynamicForm_6.setFields(new FormItem[] { intConcurrentReads, intConcurrentWrites });
		addMember(dynamicForm_6);
		
		lblNewLabel_5 = new Label("Memtable");
		lblNewLabel_5.setHeight(30);
		lblNewLabel_5.setWidth(520);
		lblNewLabel_5.setStyleName("label");
		addMember(lblNewLabel_5);
		
		dynamicForm_7 = new DynamicForm();
		intMemtableTotalSpace = new IntegerItem("intMemtableTotalSpace", "Memtable total space (0 = unlimited) (mb)");
		intMemtableWriterThreads = new IntegerItem("intMemtableWriterThreads", "Memtable writer threads (0 = automatic)");
		intFlushFraction = new IntegerItem("intFlushFraction", "Flush fraction (%)");
		dynamicForm_7.setFields(new FormItem[] { intMemtableTotalSpace, intMemtableWriterThreads, intFlushFraction });
		addMember(dynamicForm_7);
		
		btnStartNewCluster = new Button("Start Cluster");
				
		addMember(btnStartNewCluster);
				
		initPresetValues();
		
		bind();
	}

	/**
	 * Initializes preset values.
	 */
	private void initPresetValues() {
		rgHintedHandoff.setDefaultValue("Enabled");
		intMaxWindowTime.setDefaultValue(3600000);
		intThrottleDelay.setDefaultValue(1);
		cbSyncType.setDefaultValue("batch");
		intTimeWindow.setDefaultValue(50);
		intCommitlogTotalSpace.setDefaultValue(4096);
		floatReduceCacheAt.setDefaultValue("0.85");
		floatReduceCacheCapacity.setDefaultValue("0.6");
		intConcurrentReads.setDefaultValue(32);
		intConcurrentWrites.setDefaultValue(32);
		intMemtableTotalSpace.setDefaultValue(2048);
		intMemtableWriterThreads.setDefaultValue(1);
		intFlushFraction.setDefaultValue("0.75");
	}

	/**
	 * Binds event handler to widgets.
	 */
	private void bind() {
		btnStartNewCluster.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				doReadWidgetInputInExchangeObject();
				doStartCluster();
			}
		});
	}
	
	/**
	 * Reads all input form data and writes it into the NewClusterExchange object.
	 */
	protected void doReadWidgetInputInExchangeObject() {
		NewClusterHardwareView hardwareView = newClusterView.getNewClusterHardwareView();
		NewClusterCassandraView cassandraView = newClusterView.getNewClusterCassandraView();		
		
		ex.setUsername(newClusterView.getDashboard().getHeaderView().getUsername());
		ex.setProvider(hardwareView.getProvider());
		ex.setAccessKey(hardwareView.getAccessKey());
		ex.setSecretAccessKey(hardwareView.getSecretAccessKey());
		ex.setIps(Util.getIps(hardwareView.getIps(), hardwareView.getPassword()));
		ex.setLogin(hardwareView.getLoginName());
		if(hardwareView.getProvider().equals(StringParameter.ONE_AND_ONE_CLOUD)) {
			ex.setRegion("Germany");
			ex.setNumberOfInstances(hardwareView.getIps().split(";").length);
		} else {
			ex.setRegion(hardwareView.getRegion());
			ex.setNumberOfInstances(hardwareView.getNumberOfInstances());
		}
		ex.setInstanceSize(hardwareView.getInstanceSize());
		ex.setMultiRegionCluster(hardwareView.isMultiregionCluster());
		
		if(ex.isMultiRegionCluster()) {
			ex.setMrProvider(hardwareView.getMrProvider());
		} else {
			ex.setMrProvider("");
		}
		
		ex.setMrAccessKey(hardwareView.getMrAccessKey());
		ex.setMrSecretAccessKey(hardwareView.getMrSecretAccessKey());
		ex.setMrIps(Util.getIps(hardwareView.getMrIps(), hardwareView.getMrPassword()));
		ex.setMrLogin(hardwareView.getMrLoginName());
		if(hardwareView.getMrProvider().equals(StringParameter.ONE_AND_ONE_CLOUD)) {
			ex.setMrRegion("Germany");
			ex.setMrNumberOfInstances(hardwareView.getMrIps().split(";").length);
		} else {
			ex.setMrRegion(hardwareView.getMrRegion());
			ex.setMrNumberOfInstances(hardwareView.getMrNumberOfInstances());
		}
		ex.setMrInstanceSize(hardwareView.getMrInstanceSize());
		
		ex.setClusterName(cassandraView.getClusterName());
		ex.setCassandraVersion(cassandraView.getCassandraVersion());
		ex.setPartitioner(cassandraView.getPartitioner());
		
		ex.setHintedHandoff(getHintedHandoff());
		ex.setMaxWindowTime(getMaxWindowTime());
		ex.setThrottleDelay(getThrottleDelay());
		ex.setSyncType(getSyncType());
		ex.setTimeWindow(getTimeWindow());
		ex.setCommitlogTotalSpace(getCommitlogTotalSpace());
		ex.setReduceCacheAt(getReduceCacheAt());
		ex.setReduceCacheCapacity(getReduceCacheCapacity());
		ex.setConcurrentReads(getConcurrentReads());
		ex.setConcurrentWrites(getConcurrentWrites());
		ex.setMemtableTotalSpace(getMemtableTotalSpace());
		ex.setMemtableWriterThreads(getMemtableWriterThreads());
		ex.setFlushFraction(getFlushFraction());
		
	}

	/**
	 * starts cluster
	 */
	protected void doStartCluster() {
		final ProgressDialog progressDialog = new ProgressDialog();
		progressDialog.show();
		
		rpcService.startInstance(ex, new AsyncCallback<String>() {

			public void onFailure(Throwable caught) {
				Dialog d = new Dialog();
				d.setTitle("START INSTANCE FAILED");
				d.show();
				progressDialog.disable();
				progressDialog.hide();
			}

			public void onSuccess(String result) {
				SC.say(result);
			}
		});

		final AsyncCallback<HashMap<String,String>> statusCallback = new AsyncCallback<HashMap<String,String>>() {
			
			public void onSuccess(HashMap<String,String> result) {
				if(!result.get("action").equals("DONE")) {
					progressDialog.setAction(result.get("action"));
					progressDialog.setProgress(Integer.parseInt(result.get("progress")));
					rpcService.getStartInstanceStatus(this);
				} else {
					progressDialog.hide();
				}
			}
			
			public void onFailure(Throwable caught) {
				Window.alert("Receiving progress status failed.");
			}
		};
		rpcService.getStartInstanceStatus(statusCallback);
		
	}

	/**
	 * 
	 * @return Boolean 'true' if enabled, else 'false'.
	 */
	public boolean getHintedHandoff() {
		if("Enabled".equals(rgHintedHandoff.getValueAsString())) {
			return true;			
		} else {
			return false;
		}
	}

	/**
	 * 
	 * @return
	 */
	public int getMaxWindowTime() {
		return Integer.parseInt(intMaxWindowTime.getValueAsString());
	}

	/**
	 * 
	 * @return
	 */
	public int getThrottleDelay() {
		return Integer.parseInt(intThrottleDelay.getValueAsString());
	}

	/**
	 * 
	 * @return
	 */
	public String getSyncType() {
		return cbSyncType.getValueAsString();
	}

	/**
	 * 
	 * @return
	 */
	public int getTimeWindow() {
		return Integer.parseInt(intTimeWindow.getValueAsString());
	}

	/**
	 * 
	 * @return
	 */
	public int getCommitlogTotalSpace() {
		return Integer.parseInt(intCommitlogTotalSpace.getValueAsString());
	}

	/**
	 * 
	 * @return
	 */
	public float getReduceCacheAt() {
		return Float.parseFloat(floatReduceCacheAt.getValueAsString());
	}

	/**
	 * 
	 * @return
	 */
	public float getReduceCacheCapacity() {
		return Float.parseFloat(floatReduceCacheCapacity.getValueAsString());
	}

	/**
	 * 
	 * @return
	 */
	public int getConcurrentReads() {
		return Integer.parseInt(intConcurrentReads.getValueAsString());
	}

	/**
	 * 
	 * @return
	 */
	public int getConcurrentWrites() {
		return Integer.parseInt(intConcurrentWrites.getValueAsString());
	}

	/**
	 * 
	 * @return
	 */
	public int getMemtableTotalSpace() {
		return Integer.parseInt(intMemtableTotalSpace.getValueAsString());
	}

	/**
	 * 
	 * @return
	 */
	public int getMemtableWriterThreads() {
		return Integer.parseInt(intMemtableWriterThreads.getValueAsString());
	}

	/**
	 * 
	 * @return
	 */
	public float getFlushFraction() {
		return Float.parseFloat(intFlushFraction.getValueAsString());
	}
}