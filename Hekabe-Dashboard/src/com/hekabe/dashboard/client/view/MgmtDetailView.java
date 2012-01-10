package com.hekabe.dashboard.client.view;

import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;

public class MgmtDetailView extends VLayout {
	
	TabSet mgmtTabSet = new TabSet();
	Tab nodeSummaryTab = new Tab("Node Summary");
	Tab newNodeTab = new Tab("New Node");
	Tab configTab = new Tab("Config");

	public MgmtDetailView() {		
		mgmtTabSet.setPixelSize(1000, 400);
		
		MgmtDetailNodeSummaryView nodeSummary = new MgmtDetailNodeSummaryView();
		MgmtDetailNewNodeView newNode = new MgmtDetailNewNodeView();
		MgmtDetailConfigView config = new MgmtDetailConfigView();
		
		nodeSummaryTab.setPane(nodeSummary);
		newNodeTab.setPane(newNode);
		configTab.setPane(config);
		
		mgmtTabSet.addTab(nodeSummaryTab);
		mgmtTabSet.addTab(newNodeTab);
		mgmtTabSet.addTab(configTab);
		
		addMember(mgmtTabSet);
	}
}
