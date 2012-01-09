package com.hekabe.dashboard.client.view;

import com.google.gwt.user.client.ui.Composite;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ComboBoxItem;
import com.smartgwt.client.widgets.form.fields.FloatItem;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.IntegerItem;
import com.smartgwt.client.widgets.form.fields.RadioGroupItem;
import com.smartgwt.client.widgets.layout.VLayout;

public class NewClusterCassandraConfigView extends Composite {

	private VLayout layoutConfig;
	private Label lblNewLabel_1;
	private DynamicForm dynamicForm_3;
	private RadioGroupItem rgHintedHandoff;
	private IntegerItem intMaxWindowTime;
	private IntegerItem intThrottleDelay;
	private Label lblNewLabel_2;
	private DynamicForm dynamicForm_4;
	private ComboBoxItem cbSyncType;
	private IntegerItem intTimeWindows;
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

	public NewClusterCassandraConfigView() {
		layoutConfig = new VLayout();
		
		lblNewLabel_1 = new Label("Hinted Handoff");
		lblNewLabel_1.setHeight("30");
		layoutConfig.addMember(lblNewLabel_1);
		
		dynamicForm_3 = new DynamicForm();
		rgHintedHandoff = new RadioGroupItem("newRadioGroupItem_1", "Hinted Handoff");
		rgHintedHandoff.setVertical(false);
		rgHintedHandoff.setValueMap("Enabled","Disabled");
		intMaxWindowTime = new IntegerItem("maxWindowTime", "Maximum Window Time (ms)");
		intThrottleDelay = new IntegerItem("throttleDelay", "Throttle Delay (ms)");
		dynamicForm_3.setFields(new FormItem[] { rgHintedHandoff, intMaxWindowTime, intThrottleDelay});
		layoutConfig.addMember(dynamicForm_3);
		
		lblNewLabel_2 = new Label("Commit Log");
		lblNewLabel_2.setHeight(30);
		layoutConfig.addMember(lblNewLabel_2);
		
		dynamicForm_4 = new DynamicForm();
		cbSyncType = new ComboBoxItem("newComboBoxItem_1", "Synchronisation Type");
		cbSyncType.setValueMap("periodic",
							   "batch");
		intTimeWindows = new IntegerItem();
		intTimeWindows.setTitle("Time window (ms)");
		intCommitlogTotalSpace = new IntegerItem();
		intCommitlogTotalSpace.setTitle("commitlog_total_space (0 = unlimited) (mb)");
		dynamicForm_4.setFields(new FormItem[] { cbSyncType, intTimeWindows, intCommitlogTotalSpace});
		layoutConfig.addMember(dynamicForm_4);
		
		lblNewLabel_3 = new Label("Garbage Collection");
		lblNewLabel_3.setHeight(30);
		layoutConfig.addMember(lblNewLabel_3);
		
		dynamicForm_5 = new DynamicForm();
		floatReduceCacheAt = new FloatItem("floatReduceCacheAt", "Reduce cache at");
		floatReduceCacheCapacity = new FloatItem("floatReduceCacheCapacity", "Reduce cache capacity");
		dynamicForm_5.setFields(new FormItem[] { floatReduceCacheAt, floatReduceCacheCapacity });
		layoutConfig.addMember(dynamicForm_5);
		
		lblNewLabel_4 = new Label("Read/Write");
		lblNewLabel_4.setHeight(30);
		layoutConfig.addMember(lblNewLabel_4);
		
		dynamicForm_6 = new DynamicForm();
		intConcurrentReads = new IntegerItem("intConcurrentReads", "Concurrent Reads");
		intConcurrentWrites = new IntegerItem("intConcurrentWrites", "Concurrent Writes");
		dynamicForm_6.setFields(new FormItem[] { intConcurrentReads, intConcurrentWrites });
		layoutConfig.addMember(dynamicForm_6);
		
		lblNewLabel_5 = new Label("Memtable");
		lblNewLabel_5.setHeight(30);
		layoutConfig.addMember(lblNewLabel_5);
		
		dynamicForm_7 = new DynamicForm();
		intMemtableTotalSpace = new IntegerItem("intMemtableTotalSpace", "Memtable total space (0 = unlimited) (mb)");
		intMemtableWriterThreads = new IntegerItem("intMemtableWriterThreads", "Memtable writer threads (0 = automatic)");
		intFlushFraction = new IntegerItem("intFlushFraction", "Flush fraction (%)");
		dynamicForm_7.setFields(new FormItem[] { intMemtableTotalSpace, intMemtableWriterThreads, intFlushFraction });
		layoutConfig.addMember(dynamicForm_7);
		
		btnStartNewCluster = new Button("Start Cluster");
		
		layoutConfig.addMember(btnStartNewCluster);	
		
		initWidget(layoutConfig);
	}
	
	public Canvas getPane() {
		return layoutConfig;
	}
}