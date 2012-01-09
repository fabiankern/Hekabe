package com.hekabe.dashboard.client.view;

import com.google.gwt.user.client.ui.Composite;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.tree.TreeGridField;

public class MgmtClustersView extends Composite {
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
		
		//runningClusterListGrid.setData(a());
		
		initWidget(runningClusterListGrid);
	}

	public ListGrid getPane() {
		return runningClusterListGrid;
	}
	
	public ListGridRecord[] a() {
		ListGridRecord[] result = new ListGridRecord[1];
		
		result[0].setAttribute("fieldName", "NAME");
		result[0].setAttribute("fieldProvider", "Provider");
		result[0].setAttribute("fieldNumberOfNodes", "NUMBER OF NODES");
		
		return result;
	}
}
