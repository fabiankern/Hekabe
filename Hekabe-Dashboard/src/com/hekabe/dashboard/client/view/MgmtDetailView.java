package com.hekabe.dashboard.client.view;

import com.google.gwt.user.client.ui.Composite;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;

public class MgmtDetailView extends Composite {
	private TabSet mgmtTabSet;
	private Tab nodeSummaryTab;
	private Tab newNodeTab;
	private Tab configTab;

	public MgmtDetailView() {
		mgmtTabSet = new TabSet();
		mgmtTabSet.setAutoDraw(false);
		
		mgmtTabSet.setSize("1000", "850");
		
		MgmtDetailNodeSummaryView nodeSummary = new MgmtDetailNodeSummaryView();
		MgmtDetailNewNodeView newNode = new MgmtDetailNewNodeView();
		MgmtDetailConfigView config = new MgmtDetailConfigView();
		
		nodeSummaryTab = new Tab("Node Summary");
		newNodeTab = new Tab("New Node");
		configTab = new Tab("Config");
		
		nodeSummaryTab.setPane(nodeSummary.getPane());
		newNodeTab.setPane(newNode.getPane());
		configTab.setPane(config.getPane());
		
		mgmtTabSet.addTab(nodeSummaryTab);
		mgmtTabSet.addTab(newNodeTab);
		mgmtTabSet.addTab(configTab);
		
		initWidget(mgmtTabSet);
	}
	
	public TabSet getPane() {
		return mgmtTabSet;
	}

}
