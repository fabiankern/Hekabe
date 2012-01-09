package com.hekabe.dashboard.client.view;

import com.google.gwt.user.client.ui.Composite;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ComboBoxItem;
import com.smartgwt.client.widgets.form.fields.FloatItem;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.IntegerItem;
import com.smartgwt.client.widgets.form.fields.RadioGroupItem;
import com.smartgwt.client.widgets.layout.VLayout;

public class MgmtDetailConfigView extends Composite {

	private VLayout layoutNodeConfig;
	private DynamicForm dynamicForm_3_Nodes;
	private RadioGroupItem rgHintedHandoffNodes;
	private IntegerItem intMaxWindowTimeNodes;
	private IntegerItem intThrottleDelayNodes;
	private DynamicForm dynamicForm_4_Nodes;
	private ComboBoxItem cbSyncTypeNodes;
	private IntegerItem intTimeWindowsNodes;
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

	public MgmtDetailConfigView() {
		layoutNodeConfig = new VLayout();
		layoutNodeConfig.setAutoDraw(false);
		
		lblNewLabel_1 = new Label("Hinted Handoff");
		lblNewLabel_1.setHeight("30");
		lblNewLabel_2 = new Label("Commit Log");
		lblNewLabel_2.setHeight(30);
		lblNewLabel_3 = new Label("Garbage Collection");
		lblNewLabel_3.setHeight(30);
		lblNewLabel_4 = new Label("Read/Write");
		lblNewLabel_4.setHeight(30);
		lblNewLabel_5 = new Label("Memtable");
		lblNewLabel_5.setHeight(30);

		layoutNodeConfig.addMember(lblNewLabel_1);
		
		dynamicForm_3_Nodes = new DynamicForm();
		rgHintedHandoffNodes = new RadioGroupItem("newRadioGroupItem_1", "Hinted Handoff");
		rgHintedHandoffNodes.setVertical(false);
		rgHintedHandoffNodes.setValueMap("Enabled","Disabled");
		intMaxWindowTimeNodes = new IntegerItem("maxWindowTimeNodes", "Maximum Window Time (ms)");
		intThrottleDelayNodes = new IntegerItem("throttleDelayNodes", "Throttle Delay (ms)");
		dynamicForm_3_Nodes.setFields(new FormItem[] { rgHintedHandoffNodes, intMaxWindowTimeNodes, intThrottleDelayNodes });
		layoutNodeConfig.addMember(dynamicForm_3_Nodes);
		
		layoutNodeConfig.addMember(lblNewLabel_2);
		
		dynamicForm_4_Nodes = new DynamicForm();
		cbSyncTypeNodes = new ComboBoxItem("newComboBoxItem_1", "Synchronisation Type");
		cbSyncTypeNodes.setValueMap("periodic","batch");
		intTimeWindowsNodes = new IntegerItem();
		intTimeWindowsNodes.setTitle("Time window (ms)");
		intCommitlogTotalSpaceNodes = new IntegerItem();
		intCommitlogTotalSpaceNodes.setTitle("commitlog_total_space (0 = unlimited) (mb)");
		dynamicForm_4_Nodes.setFields(new FormItem[] { cbSyncTypeNodes, intTimeWindowsNodes, intCommitlogTotalSpaceNodes});
		layoutNodeConfig.addMember(dynamicForm_4_Nodes);
		
		layoutNodeConfig.addMember(lblNewLabel_3);
		
		dynamicForm_5_Nodes = new DynamicForm();
		floatReduceCacheAtNodes = new FloatItem("floatReduceCacheAt", "Reduce cache at");
		floatReduceCacheCapacityNodes = new FloatItem("floatReduceCacheCapacity", "Reduce cache capacity");
		dynamicForm_5_Nodes.setFields(new FormItem[] { floatReduceCacheAtNodes, floatReduceCacheCapacityNodes });
		layoutNodeConfig.addMember(dynamicForm_5_Nodes);
		
		layoutNodeConfig.addMember(lblNewLabel_4);
		
		dynamicForm_6_Nodes = new DynamicForm();
		intConcurrentReadsNodes = new IntegerItem("intConcurrentReads", "Concurrent Reads");
		intConcurrentWritesNodes = new IntegerItem("intConcurrentWrites", "Concurrent Writes");
		dynamicForm_6_Nodes.setFields(new FormItem[] { intConcurrentReadsNodes, intConcurrentWritesNodes });
		layoutNodeConfig.addMember(dynamicForm_6_Nodes);

		layoutNodeConfig.addMember(lblNewLabel_5);
		
		dynamicForm_7_Nodes = new DynamicForm();
		intMemtableTotalSpaceNodes = new IntegerItem("intMemtableTotalSpace", "Memtable total space (0 = unlimited) (mb)");
		intMemtableWriterThreadsNodes = new IntegerItem("intMemtableWriterThreads", "Memtable writer threads (0 = automatic)");
		intFlushFractionNodes = new IntegerItem("intFlushFraction", "Flush fraction (%)");
		dynamicForm_7_Nodes.setFields(new FormItem[] { intMemtableTotalSpaceNodes, intMemtableWriterThreadsNodes, intFlushFractionNodes });
		layoutNodeConfig.addMember(dynamicForm_7_Nodes);
		
		initWidget(layoutNodeConfig);
	}
	
	public VLayout getPane() {
		return layoutNodeConfig;
	}

}
