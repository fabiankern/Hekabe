package com.hekabe.dashboard.client.view;

import java.util.HashMap;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.hekabe.dashboard.client.CommunicationServiceAsync;
import com.hekabe.dashboard.shared.parameter.YamlParameters;
import com.smartgwt.client.widgets.Button;
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

public class MgmtDetailConfigView extends VLayout {

	private DynamicForm dynamicForm_3_Nodes;
	private RadioGroupItem rgHintedHandoffNodes;
	private IntegerItem intMaxWindowTimeNodes;
	private IntegerItem intThrottleDelayNodes;
	private DynamicForm dynamicForm_4_Nodes;
	private ComboBoxItem cbSyncTypeNodes;
	private IntegerItem intTimeWindowNodes;
	private IntegerItem intCommitlogTotalSpaceNodes;
	private DynamicForm dynamicForm_5_Nodes;
	private FloatItem floatReduceCacheAtNodes;
	private FloatItem floatReduceCacheCapacityNodes;
	private DynamicForm dynamicForm_6_Nodes;
	private IntegerItem intConcurrentReadsNodes;
	private IntegerItem intConcurrentWritesNodes;
	private DynamicForm dynamicForm_7_Nodes;
	private IntegerItem intMemtableTotalSpaceNodes;
	private IntegerItem intMemtableWriterThreadsNodes;
	private IntegerItem intFlushFractionNodes;
	private Label lblNewLabel_1;
	private Label lblNewLabel_2;
	private Label lblNewLabel_3;
	private Label lblNewLabel_4;
	private Label lblNewLabel_5;
	@SuppressWarnings("unused")
	private MgmtDetailView mgmtDetailView;
	private CommunicationServiceAsync rpcService;
	private Button btnUpdateConfigValues;
	private long timestamp;
	private String user;
	
	/**
	 * Creates MgmtDetailConfigView
	 * @param mgmtDetailView
	 * @param rpcService
	 */
	public MgmtDetailConfigView(MgmtDetailView mgmtDetailView, CommunicationServiceAsync rpcService) {
		this.mgmtDetailView = mgmtDetailView;
		this.rpcService = rpcService;
		
		lblNewLabel_1 = new Label("Hinted Handoff");
		lblNewLabel_1.setHeight(30);
		lblNewLabel_1.setWidth(520);
		lblNewLabel_1.setStyleName("label");
		
		lblNewLabel_2 = new Label("Commit Log");
		lblNewLabel_2.setHeight(30);
		lblNewLabel_2.setWidth(520);
		lblNewLabel_2.setStyleName("label");
		
		lblNewLabel_3 = new Label("Garbage Collection");
		lblNewLabel_3.setHeight(30);
		lblNewLabel_3.setWidth(520);
		lblNewLabel_3.setStyleName("label");
		
		lblNewLabel_4 = new Label("Read/Write");
		lblNewLabel_4.setHeight(30);
		lblNewLabel_4.setWidth(520);
		lblNewLabel_4.setStyleName("label");
		
		lblNewLabel_5 = new Label("Memtable");
		lblNewLabel_5.setHeight(30);
		lblNewLabel_5.setWidth(520);
		lblNewLabel_5.setStyleName("label");

		addMember(lblNewLabel_1);
		
		dynamicForm_3_Nodes = new DynamicForm();
		rgHintedHandoffNodes = new RadioGroupItem("newRadioGroupItem_1", "Hinted Handoff");
		rgHintedHandoffNodes.setWidth(220);
		rgHintedHandoffNodes.setVertical(true);
		rgHintedHandoffNodes.setValueMap("Enabled","Disabled");
		intMaxWindowTimeNodes = new IntegerItem("maxWindowTimeNodes", "Maximum Window Time (ms)");
		intThrottleDelayNodes = new IntegerItem("throttleDelayNodes", "Throttle Delay (ms)");
		dynamicForm_3_Nodes.setFields(new FormItem[] { rgHintedHandoffNodes, intMaxWindowTimeNodes, intThrottleDelayNodes });
		addMember(dynamicForm_3_Nodes);
		
		addMember(lblNewLabel_2);
		
		dynamicForm_4_Nodes = new DynamicForm();
		cbSyncTypeNodes = new ComboBoxItem("newComboBoxItem_1", "Synchronisation Type");
		cbSyncTypeNodes.setValueMap("periodic","batch");
		intTimeWindowNodes = new IntegerItem();
		intTimeWindowNodes.setTitle("Time window (ms)");
		intCommitlogTotalSpaceNodes = new IntegerItem();
		intCommitlogTotalSpaceNodes.setTitle("commitlog_total_space (0 = unlimited) (mb)");
		dynamicForm_4_Nodes.setFields(new FormItem[] { cbSyncTypeNodes, intTimeWindowNodes, intCommitlogTotalSpaceNodes});
		addMember(dynamicForm_4_Nodes);
		
		addMember(lblNewLabel_3);
		
		dynamicForm_5_Nodes = new DynamicForm();
		floatReduceCacheAtNodes = new FloatItem("floatReduceCacheAt", "Reduce cache at");
		floatReduceCacheCapacityNodes = new FloatItem("floatReduceCacheCapacity", "Reduce cache capacity");
		dynamicForm_5_Nodes.setFields(new FormItem[] { floatReduceCacheAtNodes, floatReduceCacheCapacityNodes });
		addMember(dynamicForm_5_Nodes);
		
		addMember(lblNewLabel_4);
		
		dynamicForm_6_Nodes = new DynamicForm();
		intConcurrentReadsNodes = new IntegerItem("intConcurrentReads", "Concurrent Reads");
		intConcurrentWritesNodes = new IntegerItem("intConcurrentWrites", "Concurrent Writes");
		dynamicForm_6_Nodes.setFields(new FormItem[] { intConcurrentReadsNodes, intConcurrentWritesNodes });
		addMember(dynamicForm_6_Nodes);

		addMember(lblNewLabel_5);
		
		dynamicForm_7_Nodes = new DynamicForm();
		intMemtableTotalSpaceNodes = new IntegerItem("intMemtableTotalSpace", "Memtable total space (0 = unlimited) (mb)");
		intMemtableWriterThreadsNodes = new IntegerItem("intMemtableWriterThreads", "Memtable writer threads (0 = automatic)");
		intFlushFractionNodes = new IntegerItem("intFlushFraction", "Flush fraction (%)");
		dynamicForm_7_Nodes.setFields(new FormItem[] { intMemtableTotalSpaceNodes, intMemtableWriterThreadsNodes, intFlushFractionNodes });
		
		addMember(dynamicForm_7_Nodes);
		
		btnUpdateConfigValues = new Button("Update Config Values");
		
		addMember(btnUpdateConfigValues);
		
//		initPresetValues();
		
		bind();
	}
	
//	private void initPresetValues() {
//		rgHintedHandoffNodes.setValue("Enabled");
//		intMaxWindowTimeNodes.setValue(3600000);
//		intThrottleDelayNodes.setValue(1);
//		cbSyncTypeNodes.setValue("batch");
//		intTimeWindowNodes.setValue(50);
//		intCommitlogTotalSpaceNodes.setValue(4096);
//		floatReduceCacheAtNodes.setValue("0.85");
//		floatReduceCacheCapacityNodes.setValue("0.6");
//		intConcurrentReadsNodes.setValue(32);
//		intConcurrentWritesNodes.setValue(32);
//		intMemtableTotalSpaceNodes.setValue(2048);
//		intMemtableWriterThreadsNodes.setValue(1);
//		intFlushFractionNodes.setValue("0.75");
//	}

	/**
	 * Binds handlers
	 */
	private void bind() {
		btnUpdateConfigValues.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				doUpdateConfigValues();
			}
		});
	}
	
	/**
	 * Updates config values
	 */
	protected void doUpdateConfigValues() {
		HashMap<String, String> parameters = new HashMap<String, String>();
		
		parameters.put(YamlParameters.HINTED_HANDOFF, String.valueOf(getHintedHandoff()));
		parameters.put(YamlParameters.MAX_WINDOW_TIME, String.valueOf(getMaximumWindowTime()));
		parameters.put(YamlParameters.THROTTLE_DELAY, String.valueOf(getThrottleDelay()));
		
		parameters.put(YamlParameters.SYNC_TYPE, getSyncType());
		if(getSyncType().equals("batch")) {
			parameters.put(YamlParameters.TIME_WINDOW_BATCH, String.valueOf(getTimeWindow()));
		} else if(getSyncType().equals("periodic")) {
			parameters.put(YamlParameters.TIME_WINDOW_PERIODIC, String.valueOf(getTimeWindow()));
		}
		parameters.put(YamlParameters.COMMITLOG_TOTAL_SPACE, String.valueOf(getCommitlogTotalSpace()));
		parameters.put(YamlParameters.REDUCE_CACHE_AT, String.valueOf(getReduceCacheAt()));
		parameters.put(YamlParameters.REDUCE_CACHE_CAPACITY_TO, String.valueOf(getReduceCacheCapacity()));
		parameters.put(YamlParameters.CONCURRENT_READS, String.valueOf(getConcurrentReads()));
		parameters.put(YamlParameters.CONCURRENT_WRITES, String.valueOf(getConcurrentWrites()));
		parameters.put(YamlParameters.MEMTABLE_WRITER_THREADS, String.valueOf(getMemtableWriterThreads()));
		parameters.put(YamlParameters.MEMTABLE_TOTAL_SPACE, String.valueOf(getMemtableTotalSpace()));
		parameters.put(YamlParameters.FLUSH_FRACTION, String.valueOf(getFlushFraction()));
				
		rpcService.setConfigValues(user, timestamp, parameters, new AsyncCallback<String>() {
			
			public void onSuccess(String result) {
				Window.alert("doUpdateConfigValues success");
			}
			
			public void onFailure(Throwable caught) {
				Window.alert("doUpdateConfigValues failed");
			}
		});
	}

	/**
	 * 
	 * @return Returns 'true' if enabled, else 'false'.
	 */
	public boolean getHintedHandoff() {
		if("Enabled".equals(rgHintedHandoffNodes.getValueAsString())) {
			return true;			
		} else {
			return false;
		}
	}
	
	/**
	 * 
	 * @param hintedHandoff
	 */
	public void setHintedHandoff(boolean hintedHandoff) {
		if(hintedHandoff) {
			rgHintedHandoffNodes.setValue("Enabled");
		} else {
			rgHintedHandoffNodes.setValue("Disabled");
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public int getMaximumWindowTime() {
		return intMaxWindowTimeNodes.getValueAsInteger();
	}
	
	/**
	 * 
	 * @param value
	 */
	public void setMaximumWindowTime(int value) {
		intMaxWindowTimeNodes.setValue(value);
	}
	
	/**
	 * 
	 * @return
	 */
	public int getThrottleDelay() {
		return intThrottleDelayNodes.getValueAsInteger();
	}
	
	/**
	 * 
	 * @param value
	 */
	public void setThrottleDelay(int value) {
		intThrottleDelayNodes.setValue(value);
	}
	
	/**
	 * 
	 * @return
	 */
	public String getSyncType() {
		return cbSyncTypeNodes.getValueAsString();
	}
	
	/**
	 * 
	 * @param value
	 */
	public void setSyncType(String value) {
		cbSyncTypeNodes.setValue(value);
	}
	
	/**
	 * 
	 * @return
	 */
	public int getTimeWindow() {
		return intTimeWindowNodes.getValueAsInteger();
	}
	
	/**
	 * 
	 * @param value
	 */
	public void setTimeWindow(int value) {
		intTimeWindowNodes.setValue(value);
	}
	
	/**
	 * 
	 * @return
	 */
	public int getCommitlogTotalSpace() {
		return intCommitlogTotalSpaceNodes.getValueAsInteger();
	}
	
	/**
	 * 
	 * @param value
	 */
	public void setCommitlogTotalSpace(int value) {
		intCommitlogTotalSpaceNodes.setValue(value);
	}
	
	/**
	 * 
	 * @return
	 */
	public float getReduceCacheAt() {
		return floatReduceCacheAtNodes.getValueAsFloat();
	}
	
	/**
	 * 
	 * @param value
	 */
	public void setReduceCacheAt(float value) {
		floatReduceCacheAtNodes.setValue(value);
	}
	
	/**
	 * 
	 * @return
	 */
	public float getReduceCacheCapacity() {
		return floatReduceCacheCapacityNodes.getValueAsFloat();
	}
	
	/**
	 * 
	 * @param value
	 */
	public void setReduceCacheCapactity(float value) {
		floatReduceCacheCapacityNodes.setValue(value);
	}
	
	/**
	 * 
	 * @return
	 */
	public int getConcurrentReads() {
		return intConcurrentReadsNodes.getValueAsInteger();
	}
	
	/**
	 * 
	 * @param value
	 */
	public void setConcurrentReads(int value) {
		intConcurrentReadsNodes.setValue(value);
	}
	
	/**
	 * 
	 * @return
	 */
	public int getConcurrentWrites() {
		return intConcurrentWritesNodes.getValueAsInteger();
	}
	
	/**
	 * 
	 * @param value
	 */
	public void setConcurrentWrites(int	 value) {
		intConcurrentWritesNodes.setValue(value);
	}
	
	/**
	 * 
	 * @return
	 */
	public int getMemtableTotalSpace() {
		return intMemtableTotalSpaceNodes.getValueAsInteger();
	}
	
	/**
	 * 
	 * @param value
	 */
	public void setMemtableTotalSpace(int value) {
		intMemtableTotalSpaceNodes.setValue(value);
	}
	
	/**
	 * 
	 * @return
	 */
	public int getMemtableWriterThreads() {
		return intMemtableWriterThreadsNodes.getValueAsInteger();
	}
	
	/**
	 * 
	 * @param value
	 */
	public void setMemtableWriterThreads(int value) {
		intMemtableWriterThreadsNodes.setValue(value);
	}
	
	/**
	 * 
	 * @return
	 */
	public int getFlushFraction() {
		return intFlushFractionNodes.getValueAsInteger();
	}
	
	/**
	 * 
	 * @param value
	 */
	public void setFlushFraction(float value) {
		intFlushFractionNodes.setValue(value);
	}
	
	/**
	 * 
	 * @param timestamp
	 */
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	
	/**
	 * 
	 * @param user
	 */
	public void setUser(String user) {
		this.user = user;
	}
}