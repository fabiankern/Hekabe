package com.hekabe.dashboard.client.view;

import com.hekabe.dashboard.client.CommunicationServiceAsync;
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

	public NewClusterCassandraConfigView(NewClusterView newClusterView, CommunicationServiceAsync rpcService) {
		this.newClusterView = newClusterView; 
		this.rpcService = rpcService;
		
		lblNewLabel_1 = new Label("Hinted Handoff");
		lblNewLabel_1.setHeight(30);
		lblNewLabel_1.setWidth(520);
		lblNewLabel_1.addStyleName("label");
		addMember(lblNewLabel_1);
		
		dynamicForm_3 = new DynamicForm();
		rgHintedHandoff = new RadioGroupItem("newRadioGroupItem_1", "Hinted Handoff");
		rgHintedHandoff.setWidth(220);
		//rgHintedHandoff.setVertical(false);
		rgHintedHandoff.setValueMap("Enabled","Disabled");
		intMaxWindowTime = new IntegerItem("maxWindowTime", "Maximum Window Time (ms)");
		intThrottleDelay = new IntegerItem("throttleDelay", "Throttle Delay (ms)");
		dynamicForm_3.setFields(new FormItem[] { rgHintedHandoff, intMaxWindowTime, intThrottleDelay});
		addMember(dynamicForm_3);
		
		lblNewLabel_2 = new Label("Commit Log");
		lblNewLabel_2.setHeight(30);
		lblNewLabel_2.setWidth(520);
		lblNewLabel_2.addStyleName("label");
		addMember(lblNewLabel_2);
		
		dynamicForm_4 = new DynamicForm();
		cbSyncType = new ComboBoxItem("newComboBoxItem_1", "Synchronisation Type");
		cbSyncType.setValueMap("periodic",
							   "batch");
		intTimeWindow = new IntegerItem();
		intTimeWindow.setTitle("Time window (ms)");
		intCommitlogTotalSpace = new IntegerItem();
		intCommitlogTotalSpace.setTitle("commitlog_total_space (0 = unlimited) (mb)");
		dynamicForm_4.setFields(new FormItem[] { cbSyncType, intTimeWindow, intCommitlogTotalSpace});
		addMember(dynamicForm_4);
		
		lblNewLabel_3 = new Label("Garbage Collection");
		lblNewLabel_3.setHeight(30);
		lblNewLabel_3.setWidth(520);
		lblNewLabel_3.addStyleName("label");
		addMember(lblNewLabel_3);
		
		dynamicForm_5 = new DynamicForm();
		floatReduceCacheAt = new FloatItem("floatReduceCacheAt", "Reduce cache at");
		floatReduceCacheCapacity = new FloatItem("floatReduceCacheCapacity", "Reduce cache capacity");
		dynamicForm_5.setFields(new FormItem[] { floatReduceCacheAt, floatReduceCacheCapacity });
		addMember(dynamicForm_5);
		
		lblNewLabel_4 = new Label("Read/Write");
		lblNewLabel_4.setHeight(30);
		lblNewLabel_4.setWidth(520);
		lblNewLabel_4.addStyleName("label");
		addMember(lblNewLabel_4);
		
		dynamicForm_6 = new DynamicForm();
		intConcurrentReads = new IntegerItem("intConcurrentReads", "Concurrent Reads");
		intConcurrentWrites = new IntegerItem("intConcurrentWrites", "Concurrent Writes");
		dynamicForm_6.setFields(new FormItem[] { intConcurrentReads, intConcurrentWrites });
		addMember(dynamicForm_6);
		
		lblNewLabel_5 = new Label("Memtable");
		lblNewLabel_5.setHeight(30);
		lblNewLabel_5.setWidth(520);
		lblNewLabel_5.addStyleName("label");
		addMember(lblNewLabel_5);
		
		dynamicForm_7 = new DynamicForm();
		intMemtableTotalSpace = new IntegerItem("intMemtableTotalSpace", "Memtable total space (0 = unlimited) (mb)");
		intMemtableWriterThreads = new IntegerItem("intMemtableWriterThreads", "Memtable writer threads (0 = automatic)");
		intFlushFraction = new IntegerItem("intFlushFraction", "Flush fraction (%)");
		dynamicForm_7.setFields(new FormItem[] { intMemtableTotalSpace, intMemtableWriterThreads, intFlushFraction });
		addMember(dynamicForm_7);
		
		btnStartNewCluster = new Button("Start Cluster");
				
		addMember(btnStartNewCluster);
		
		bind();
	}

	private void bind() {
		btnStartNewCluster.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				
			}
		});
	}

	/**
	 * 
	 * @return Returns 'true' if enabled, else 'false'.
	 */
	public boolean getHintedHandoff() {
		if("Enabled".equals(rgHintedHandoff.getValueAsString())) {
			return true;			
		} else {
			return false;
		}
	}

	public int getMaxWindowTime() {
		return intMaxWindowTime.getValueAsInteger();
	}

	public int getThrottleDelay() {
		return intThrottleDelay.getValueAsInteger();
	}

	public String getSyncType() {
		return cbSyncType.getValueAsString();
	}

	public int getTimeWindow() {
		return intTimeWindow.getValueAsInteger();
	}

	public int getCommitlogTotalSpace() {
		return intCommitlogTotalSpace.getValueAsInteger();
	}

	public float getReduceCacheAt() {
		return floatReduceCacheAt.getValueAsFloat();
	}

	public float getReduceCacheCapacity() {
		return floatReduceCacheCapacity.getValueAsFloat();
	}

	public int getConcurrentReads() {
		return intConcurrentReads.getValueAsInteger();
	}

	public int getConcurrentWrites() {
		return intConcurrentWrites.getValueAsInteger();
	}

	public int getMemtableTotalSpace() {
		return intMemtableTotalSpace.getValueAsInteger();
	}

	public int getMemtableWriterThreads() {
		return intMemtableWriterThreads.getValueAsInteger();
	}

	public int getFlushFraction() {
		return intFlushFraction.getValueAsInteger();
	}
	
	
}




























