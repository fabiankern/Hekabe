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
	MgmtDetailNodeSummaryView nodeSummary;
	MgmtDetailNewNodeView newNode;
	MgmtDetailConfigView config;
	@SuppressWarnings("unused")
	private CommunicationServiceAsync rpcService;

	/**
	 * Creates MgmtDetailView
	 * @param mgmtView
	 * @param rpcService
	 */
	public MgmtDetailView(MgmtView mgmtView, CommunicationServiceAsync rpcService) {
		this.mgmtView = mgmtView;
		this.rpcService = rpcService;		
		
		mgmtTabSet.setPixelSize(978, 360);
		
		nodeSummary = new MgmtDetailNodeSummaryView(this, rpcService);
		newNode = new MgmtDetailNewNodeView(this, rpcService);
		config = new MgmtDetailConfigView(this, rpcService);
		
		nodeSummaryTab.setPane(nodeSummary);
		newNodeTab.setPane(newNode);
		configTab.setPane(config);
		
		mgmtTabSet.addTab(nodeSummaryTab);
		mgmtTabSet.addTab(newNodeTab);
		mgmtTabSet.addTab(configTab);
		mgmtTabSet.disableTab(newNodeTab);
		addMember(mgmtTabSet);
	}

	/**
	 * 
	 * @return
	 */
	public TabSet getMgmtTabSet() {
		return mgmtTabSet;
	}
	
	/**
	 * 
	 * @return
	 */
	public MgmtDetailNodeSummaryView getNodeSummaryView() {
		return nodeSummary;
	}
	
	/**
	 * 
	 * @return
	 */
	public MgmtView getMgmtView() {
		return mgmtView;
	}
	
	/**
	 * 
	 * @return
	 */
	public MgmtDetailConfigView getMgmtConfigView() {
		return config;
	}
}