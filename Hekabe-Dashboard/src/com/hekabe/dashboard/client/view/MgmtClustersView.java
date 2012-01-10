package com.hekabe.dashboard.client.view;

import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tree.TreeGridField;

public class MgmtClustersView extends VLayout {
	private ListGrid runningClusterListGrid;
	private ListGridField fieldName;
	private ListGridField fieldProvider;
	private ListGridField fieldNumberOfNodes;

	public MgmtClustersView() {
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
