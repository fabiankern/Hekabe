package com.hekabe.dashboard.client.view;

import com.hekabe.dashboard.client.CommunicationServiceAsync;
import com.hekabe.dashboard.shared.parameter.StringParameter;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ComboBoxItem;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tab.TabSet;

public class NewClusterCassandraView extends VLayout {

	private Label lblNewLabel;
	private DynamicForm dynamicForm_1;
	private TextItem txtClusterName;
	private ComboBoxItem cbCassVersion;
	private ComboBoxItem cbPartitioner;
	private Button btnSwitchToTab3;
	private NewClusterView newClusterView;
	@SuppressWarnings("unused")
	private CommunicationServiceAsync rpcService;

	/**
	 * 
	 * @param newClusterView
	 * @param rpcService
	 */
	public NewClusterCassandraView(NewClusterView newClusterView, CommunicationServiceAsync rpcService) {
		this.newClusterView = newClusterView;
		this.rpcService = rpcService;
		
		lblNewLabel = new Label("Cluster");
		lblNewLabel.setHeight(30);
		lblNewLabel.setWidth(520);
		lblNewLabel.setStyleName("label");
		addMember(lblNewLabel);
		
		dynamicForm_1 = new DynamicForm();
		txtClusterName = new TextItem("newTextItem_1", "Cluster Name");
		cbCassVersion = new ComboBoxItem("cbCassVersion", "Cassandra Version");
		cbCassVersion.setValueMap(StringParameter.ONE_POINT_ZERO);
		cbCassVersion.setDefaultToFirstOption(true);
		cbPartitioner = new ComboBoxItem("newComboBoxItem_3", "Partitioner");
		cbPartitioner.setValueMap(StringParameter.RANDOM_PARTITIONER,StringParameter.BYTE_ORDERED_PARTITIONER);
		cbPartitioner.setDefaultToFirstOption(true);
		
		dynamicForm_1.setFields(new FormItem[] { txtClusterName, cbCassVersion, cbPartitioner });
		addMember(dynamicForm_1);
		
		btnSwitchToTab3 = new Button("Next");
		
		addMember(btnSwitchToTab3);
		
		bind();
	}

	/**
	 * 
	 */
	private void bind() {
		btnSwitchToTab3.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				doSwitchToNextTab();
			}
		});
	}

	/**
	 * 
	 */
	private void doSwitchToNextTab() {
		TabSet tabSet = newClusterView.getTabSet();
		tabSet.enableTab(tabSet.getSelectedTabNumber() + 1);
		tabSet.selectTab(tabSet.getSelectedTabNumber() + 1);
	}

	/**
	 * 
	 * @return
	 */
	public String getClusterName() {
		return txtClusterName.getValueAsString();
	}

	/**
	 * 
	 * @return
	 */
	public String getCassandraVersion() {
		return cbCassVersion.getValueAsString();
	}

	/**
	 * 
	 * @return
	 */
	public String getPartitioner() {
		return cbPartitioner.getValueAsString();
	}
	
	
}
