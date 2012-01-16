package com.hekabe.dashboard.client.view;

import com.hekabe.dashboard.client.CommunicationServiceAsync;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;

public class MgmtDetailView extends VLayout {
	
	private TabSet mgmtTabSet = new TabSet();
	private Tab nodeSummaryTab = new Tab("Node Summary");
	private Tab newNodeTab = new Tab("New Node");
	private Tab configTab = new Tab("Config");
	private MgmtView mgmtView;
	private CommunicationServiceAsync rpcService;

	public MgmtDetailView(MgmtView mgmtView, CommunicationServiceAsync rpcService) {
		this.mgmtView = mgmtView;
		this.rpcService = rpcService;		
		
		mgmtTabSet.setPixelSize(978, 400);
		
		MgmtDetailNodeSummaryView nodeSummary = new MgmtDetailNodeSummaryView(this, rpcService);
		MgmtDetailNewNodeView newNode = new MgmtDetailNewNodeView(this, rpcService);
		MgmtDetailConfigView config = new MgmtDetailConfigView(this, rpcService);
		
		nodeSummaryTab.setPane(nodeSummary);
		newNodeTab.setPane(newNode);
		configTab.setPane(config);
		
		mgmtTabSet.addTab(nodeSummaryTab);
		mgmtTabSet.addTab(newNodeTab);
		mgmtTabSet.addTab(configTab);
		
		addMember(mgmtTabSet);
	}

	public TabSet getMgmtTabSet() {
		return mgmtTabSet;
	}
	
	public MgmtView getMgmtView() {
		return mgmtView;
	}
}
