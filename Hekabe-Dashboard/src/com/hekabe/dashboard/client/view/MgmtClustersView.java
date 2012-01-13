package com.hekabe.dashboard.client.view;

import com.hekabe.dashboard.client.CommunicationServiceAsync;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tree.TreeGridField;

public class MgmtClustersView extends VLayout {
	private ListGrid runningClusterListGrid;
	private ListGridField fieldName;
	private ListGridField fieldProvider;
	private ListGridField fieldNumberOfNodes;
	private MgmtView mgmtView;
	private CommunicationServiceAsync rpcService;

	public MgmtClustersView(MgmtView mgmtView, CommunicationServiceAsync rpcService) {
		this.mgmtView = mgmtView;
		this.rpcService = rpcService;
		
		runningClusterListGrid = new ListGrid();
		runningClusterListGrid.setAutoDraw(false);
		
		runningClusterListGrid.setPixelSize(1000, 150);
		runningClusterListGrid.setEmptyMessage("No clusters running.");
		fieldName = new TreeGridField("fieldName", "Name");
		fieldProvider = new TreeGridField("fieldProvider", "Provider");
		fieldNumberOfNodes = new TreeGridField("fieldNumberOfNodes", "Number of nodes");
		runningClusterListGrid.setFields(new ListGridField[] { fieldName, fieldProvider, fieldNumberOfNodes });
		
		addMember(runningClusterListGrid);
	}
}
