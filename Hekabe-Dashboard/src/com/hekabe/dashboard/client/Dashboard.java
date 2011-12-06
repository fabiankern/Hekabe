/*
 * Copyright 2011 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.hekabe.dashboard.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.types.VisibilityMode;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ComboBoxItem;
import com.smartgwt.client.widgets.form.fields.FloatItem;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.IntegerItem;
import com.smartgwt.client.widgets.form.fields.RadioGroupItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ChangeEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangeHandler;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.SectionStackSection;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;
import com.smartgwt.client.widgets.tree.TreeGridField;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
/**
 * Combobox Version
 * Textbox Revision
 * Cassandra YAML mit sinnvollen std. Werten füllen
 */
public class Dashboard implements EntryPoint {
	private DashboardFunctions df;
	
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again.";
	
	/**
	 * Create a remote service proxy to talk to the server-side Greeting service.
	 */
	
	private final CommunicationServiceAsync communicationService = GWT
			.create(CommunicationService.class);
	private TabSet newClusterTabSet;
	private Tab hardwareTab;
	private Tab cassManagementTab;
	private Tab cassYamlTuningTab;
	private Label lblHekabeDashboard;
	private VLayout LayoutCreateCluster;
	private Label lblProvider;
	private DynamicForm dynamicForm;
	private Label lblNewLabel;
	private DynamicForm dynamicForm_1;
	private TextItem txtClusterName;
	private ComboBoxItem cbPartitioner;
	private IntegerItem intReplicationFactor;
	private ComboBoxItem cbInstanceSize;
	private IntegerItem intNumberOfInstances;
	private ComboBoxItem cbProvider;
	private VLayout LayoutCassManagement;
	private Label lblHardware_1;
	private ListGrid runningNodesListGrid;
	private TreeGridField fieldName;
	private TreeGridField fieldSize;
	private TreeGridField fieldIp;
	private TreeGridField fieldStopButton;
	private TreeGridField fieldKillButton;
	private Label lblAddNodes;
	private DynamicForm dynamicForm_2;
	private ComboBoxItem cbNodeSize;
	private ComboBoxItem cbRegion;
	private ComboBoxItem cbProviderNodes;
	private VLayout LayoutCassYamlTuning;
	private Label lblNewLabel_1;
	private DynamicForm dynamicForm_3;
	private RadioGroupItem rgHintedHandoff;

	private IntegerItem intMaxWindowTime;

	private IntegerItem intThrottleDelay;
	private Label lblNewLabel_2;
	private DynamicForm dynamicForm_4;
	private ComboBoxItem cbSyncType;

	private Label lblNewLabel_3;

	private DynamicForm dynamicForm_5;

	private FloatItem floatFlushLargestMemtableAt;

	private FloatItem floatReduceCacheCapacity;

	private FloatItem floatReduceCacheAt;

	private Label lblNewLabel_4;

	private DynamicForm dynamicForm_6;

	private IntegerItem intConcurrentReads;

	private IntegerItem intConcurrentWrites;

	private Label lblNewLabel_5;

	private IntegerItem intMemtableTotalSpace;

	private DynamicForm dynamicForm_7;

	private IntegerItem intMemtableWriterThreads;

	private IntegerItem intTimeWindows;

	private IntegerItem intCommitlogTotalSpace;

	private SectionStack sectionStack;

	private SectionStackSection managementSection;

	private SectionStackSection newClusterSection;

	private IntegerItem intNumberOfNodes;

	private TabSet managementTabSet;

	private TextItem textAccessKey;
	
	@Override
	public void onModuleLoad() {
		df = new DashboardFunctions();
		
		RootPanel rootPanel = RootPanel.get();
		rootPanel.setSize("800", "600");
		
		lblHekabeDashboard = new Label("Hekabe Dashboard");
		lblHekabeDashboard.setSize("150", "30");
		lblHekabeDashboard.setAlign(Alignment.CENTER);
		rootPanel.add(lblHekabeDashboard);
		
		sectionStack = new SectionStack();
		sectionStack.setVisibilityMode(VisibilityMode.MUTEX);
		sectionStack.setSize("750", "650");
		
		managementSection = new SectionStackSection("Management");
		managementSection.setExpanded(true);
		newClusterSection = new SectionStackSection("New Cluster");
		newClusterSection.setExpanded(false);
		
		sectionStack.addSection(managementSection);
		sectionStack.addSection(newClusterSection);
		
		newClusterTabSet = new TabSet();
		newClusterTabSet.setSize("750", "595");
		managementTabSet = new TabSet();
		managementTabSet.setSize("750", "595");
		
		hardwareTab = new Tab("Hardware");
		
		LayoutCreateCluster = new VLayout();
		
		lblProvider = new Label("Provider");
		lblProvider.setHeight("30");
		LayoutCreateCluster.addMember(lblProvider);
		
		dynamicForm = new DynamicForm();
		cbProvider = new ComboBoxItem("Provider", "Provider");
		cbProvider.setShowTitle(true);
		cbProvider.setTooltip("Choose Cloud-Provider.");
		cbProvider.setValueMap("Amazon EC2","1&1 Cloud");
		cbProvider.setDefaultToFirstOption(true);
		cbProvider.addChangeHandler(new ChangeHandler() {
			
			@Override
			public void onChange(ChangeEvent event) {
				df.providerChange("");
			}
			
		});
		
		textAccessKey = new TextItem("", "");
		cbInstanceSize = new ComboBoxItem("InstanceSize", "Instance size");
		cbInstanceSize.setShowTitle(true);
		cbInstanceSize.setTooltip("Choose size of the instance(s).");
		cbInstanceSize.setValueMap("Large (m1.large)",
								   "Extra Large (m1.xlarge)",
								   "High-Memory Extra Large (m2.xlarge)",
								   "High-Memory Double Extra Large (m2.2xlarge)",
								   "High-Memory Quadruple Extra Large (m2.4xlarge)",
								   "High-CPU Extra Large (c1.xlarge)");
		cbInstanceSize.setDefaultToFirstOption(true);
		intNumberOfInstances = new IntegerItem();
		intNumberOfInstances.setTooltip("The number of instances you want to start.");
		intNumberOfInstances.setTitle("Number of instances");
		dynamicForm.setFields(new FormItem[] { cbProvider, intNumberOfInstances, cbInstanceSize});
		LayoutCreateCluster.addMember(dynamicForm);
		
		lblNewLabel = new Label("Cassandra Configuration");
		lblNewLabel.setHeight("30");
		LayoutCreateCluster.addMember(lblNewLabel);
		
		dynamicForm_1 = new DynamicForm();
		txtClusterName = new TextItem("newTextItem_1", "Cluster Name");
		intReplicationFactor = new IntegerItem();
		intReplicationFactor.setTitle("Replication Factor");
		cbPartitioner = new ComboBoxItem("newComboBoxItem_3", "Partitioner");
		cbPartitioner.setValueMap("Random Partitioner","Byte Ordered Partitioner");
		cbPartitioner.setDefaultToFirstOption(true);
		
		dynamicForm_1.setFields(new FormItem[] { txtClusterName, intReplicationFactor, cbPartitioner });
		LayoutCreateCluster.addMember(dynamicForm_1);
		
		hardwareTab.setPane(LayoutCreateCluster);
		
		cassManagementTab = new Tab("Cassandra Management");
		
		LayoutCassManagement = new VLayout();
		LayoutCassManagement.setWidth("750");
		
		lblHardware_1 = new Label("Hardware");
		lblHardware_1.setHeight("30");
		LayoutCassManagement.addMember(lblHardware_1);
		
		runningNodesListGrid = new ListGrid();
		runningNodesListGrid.setHeight("150");
		runningNodesListGrid.setEmptyMessage("No nodes running.");
		fieldName = new TreeGridField("fieldName", "Name");
		fieldSize = new TreeGridField("fieldSize", "Size");
		fieldIp = new TreeGridField("fieldIp", "IP");
		fieldStopButton = new TreeGridField("fieldStopButton", "Stop", 60);
		fieldStopButton.setType(ListGridFieldType.ICON);
		fieldKillButton = new TreeGridField("fieldKillButton", "Kill", 60);
		fieldKillButton.setType(ListGridFieldType.ICON);
		runningNodesListGrid.setFields(new ListGridField[] { fieldName, fieldSize, fieldIp, fieldStopButton, fieldKillButton });
		LayoutCassManagement.addMember(runningNodesListGrid);
		
		lblAddNodes = new Label("Add Nodes");
		lblAddNodes.setHeight("30");
		LayoutCassManagement.addMember(lblAddNodes);
		
		dynamicForm_2 = new DynamicForm();
		intNumberOfNodes = new IntegerItem();
		intNumberOfNodes.setTitle("Number of Nodes");
		cbNodeSize = new ComboBoxItem("nodeSize", "Node size");
		cbNodeSize.setValueMap("Large (m1.large)",
				   "Extra Large (m1.xlarge)",
				   "High-Memory Extra Large (m2.xlarge)",
				   "High-Memory Double Extra Large (m2.2xlarge)",
				   "High-Memory Quadruple Extra Large (m2.4xlarge)",
				   "High-CPU Extra Large (c1.xlarge)");
		cbNodeSize.setDefaultToFirstOption(true);
		cbRegion = new ComboBoxItem("Region", "Region");
		cbRegion.setValueMap("US East (Virginia)",
							 "US West (Oregon)",
							 "US West (California)",
							 "EU West (Ireland)",
							 "Asia Pacific (Singapore)",
							 "Asia Pacific (Tokyo)");
		cbRegion.setDefaultToFirstOption(true);
		cbProviderNodes = new ComboBoxItem("newComboBoxItem_4", "Provider");
		cbProviderNodes.setValueMap("Amazon EC2","1&1 Cloud");
		cbProviderNodes.setDefaultToFirstOption(true);
		
		dynamicForm_2.setFields(new FormItem[] { cbProviderNodes, intNumberOfNodes, cbNodeSize, cbRegion});
		LayoutCassManagement.addMember(dynamicForm_2);
		cassManagementTab.setPane(LayoutCassManagement);
		
		
		cassYamlTuningTab = new Tab("Cassandra YAML Tuning");
		
		LayoutCassYamlTuning = new VLayout();
		
		lblNewLabel_1 = new Label("Hinted Handoff");
		lblNewLabel_1.setHeight("30");
		LayoutCassYamlTuning.addMember(lblNewLabel_1);
		
		dynamicForm_3 = new DynamicForm();
		rgHintedHandoff = new RadioGroupItem("newRadioGroupItem_1", "Hinted Handoff");
		rgHintedHandoff.setVertical(false);
		rgHintedHandoff.setValueMap("Enabled","Disabled");
		intMaxWindowTime = new IntegerItem("maxWindowTime", "Maximum Window Time (ms)");
		intThrottleDelay = new IntegerItem("throttleDelay", "Throttle Delay (ms)");
		dynamicForm_3.setFields(new FormItem[] { rgHintedHandoff, intMaxWindowTime, intThrottleDelay});
		LayoutCassYamlTuning.addMember(dynamicForm_3);
		
		lblNewLabel_2 = new Label("Commit Log");
		lblNewLabel_2.setHeight(30);
		LayoutCassYamlTuning.addMember(lblNewLabel_2);
		
		dynamicForm_4 = new DynamicForm();
		cbSyncType = new ComboBoxItem("newComboBoxItem_1", "Synchronisation Type");
		cbSyncType.setValueMap("periodic",
							   "batch");
		intTimeWindows = new IntegerItem();
		intTimeWindows.setTitle("Time window (ms)");
		intCommitlogTotalSpace = new IntegerItem();
		intCommitlogTotalSpace.setTitle("commitlog_total_space (0 = unlimited) (mb)");
		dynamicForm_4.setFields(new FormItem[] { cbSyncType, intTimeWindows, intCommitlogTotalSpace});
		LayoutCassYamlTuning.addMember(dynamicForm_4);
		
		lblNewLabel_3 = new Label("Garbage Collection");
		lblNewLabel_3.setHeight(30);
		LayoutCassYamlTuning.addMember(lblNewLabel_3);
		
		dynamicForm_5 = new DynamicForm();
		floatFlushLargestMemtableAt = new FloatItem("floatFlushLargestMemtableAt", "Flush largest memtable at");
		floatReduceCacheAt = new FloatItem("floatReduceCacheAt", "Reduce cache at");
		floatReduceCacheCapacity = new FloatItem("floatReduceCacheCapacity", "Reduce cache capacity");
		dynamicForm_5.setFields(new FormItem[] { floatFlushLargestMemtableAt, floatReduceCacheAt, floatReduceCacheCapacity });
		LayoutCassYamlTuning.addMember(dynamicForm_5);
		
		lblNewLabel_4 = new Label("Read/Write");
		lblNewLabel_4.setHeight(30);
		LayoutCassYamlTuning.addMember(lblNewLabel_4);
		
		dynamicForm_6 = new DynamicForm();
		intConcurrentReads = new IntegerItem("intConcurrentReads", "Concurrent Reads");
		intConcurrentWrites = new IntegerItem("intConcurrentWrites", "Concurrent Writes");
		dynamicForm_6.setFields(new FormItem[] { intConcurrentReads, intConcurrentWrites });
		LayoutCassYamlTuning.addMember(dynamicForm_6);
		
		lblNewLabel_5 = new Label("Memtable");
		lblNewLabel_5.setHeight(30);
		LayoutCassYamlTuning.addMember(lblNewLabel_5);
		
		dynamicForm_7 = new DynamicForm();
		intMemtableTotalSpace = new IntegerItem("intMemtableTotalSpace", "Memtable total space (0 = unlimited) (mb)");
		intMemtableWriterThreads = new IntegerItem("intMemtableWriterThreads", "Memtable writer threads (0 = automatic)");
		dynamicForm_7.setFields(new FormItem[] { intMemtableTotalSpace, intMemtableWriterThreads });
		LayoutCassYamlTuning.addMember(dynamicForm_7);
		
		cassYamlTuningTab.setPane(LayoutCassYamlTuning);
		
		newClusterTabSet.addTab(hardwareTab);
		newClusterTabSet.addTab(cassManagementTab);
		newClusterTabSet.addTab(cassYamlTuningTab);
		newClusterSection.addItem(newClusterTabSet);
		rootPanel.add(sectionStack);
	}
	
	protected ComboBoxItem getCbInstanceSize() {
		return cbInstanceSize;
	}
}