package com.hekabe.dashboard.client.view;

import com.hekabe.dashboard.client.CommunicationServiceAsync;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ComboBoxItem;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.layout.VLayout;

public class MgmtDetailNewNodeView extends VLayout {

	private Label lblNewNode;
	private DynamicForm dynamicForm;
	private ComboBoxItem cbProviderNodes;
	private TextItem txtAccessKeyNodes;
	private TextItem txtSecretAccessKeyNodes;
	private TextItem txtIpAddresses1and1;
	private TextItem txtLoginName1and1;
	private TextItem txtPass1and1;
	private ComboBoxItem cbRegionNodes;
	private MgmtDetailView mgmtDetailView;
	private CommunicationServiceAsync rpcService;

	public MgmtDetailNewNodeView(MgmtDetailView mgmtDetailView, CommunicationServiceAsync rpcService) {
		this.mgmtDetailView = mgmtDetailView;
		this.rpcService = rpcService;
		
		lblNewNode = new Label("New Node");
		lblNewNode.setHeight(30);
		lblNewNode.setWidth(520);
		lblNewNode.addStyleName("label");
		addMember(lblNewNode);
		
		dynamicForm = new DynamicForm();
		cbProviderNodes = new ComboBoxItem("cbProviderNodes", "Provider");
		cbProviderNodes.setValueMap("Amazon EC2","1&1 Cloud");
		cbProviderNodes.setDefaultToFirstOption(true);
		txtAccessKeyNodes = new TextItem("txtAccessKey", "Access Key");
		txtSecretAccessKeyNodes = new TextItem("txtSecretAccessKey", "Secret Access Key");
		txtIpAddresses1and1 = new TextItem("txtIpAddresses", "IPs");
		txtLoginName1and1 = new TextItem("txtLoginName1and1", "Login");
		txtPass1and1 = new TextItem("txtPass1and1", "Password");
		cbRegionNodes = new ComboBoxItem("Region", "Region");
		cbRegionNodes.setValueMap("US East (Virginia)",
							 "US West (Oregon)",
							 "US West (California)",
							 "EU West (Ireland)",
							 "Asia Pacific (Singapore)",
							 "Asia Pacific (Tokyo)");
		cbRegionNodes.setDefaultToFirstOption(true);
		
		dynamicForm.setFields(new FormItem[] { cbProviderNodes, txtAccessKeyNodes, 
				txtSecretAccessKeyNodes, txtIpAddresses1and1, txtLoginName1and1,
				txtPass1and1, cbRegionNodes });
		
		addMember(dynamicForm);
		
		bind();
		
		doShowAppropriateWidgets(cbProviderNodes.getValue());
	}
	
	private void bind() {
		cbProviderNodes.addChangedHandler(new ChangedHandler() {			
			public void onChanged(ChangedEvent event) {
				doShowAppropriateWidgets(event.getValue());
			}
		});
	}

	public String getProvider() {
		return cbProviderNodes.getValueAsString();
	}
	
	public String getAccessKey() {
		return txtAccessKeyNodes.getValueAsString();
	}
	
	public String getSecretAccessKey() {
		return txtSecretAccessKeyNodes.getValueAsString();
	}
	
	public String getIP() {
		return txtIpAddresses1and1.getValueAsString();
	}
	
	public String getLoginName() {
		return txtLoginName1and1.getValueAsString();
	}
	
	public String getLoginPass() {
		return txtPass1and1.getValueAsString();
	}
	
	public String getRegion() {
		return cbRegionNodes.getValueAsString();
	}

	private void showAwsWidgets() {
		if(txtIpAddresses1and1.isVisible()) txtIpAddresses1and1.hide();
		if(txtLoginName1and1.isVisible()) txtLoginName1and1.hide();
		if(txtPass1and1.isVisible()) txtPass1and1.hide();
		
		if(!txtAccessKeyNodes.isVisible()) txtAccessKeyNodes.show();
		if(!txtSecretAccessKeyNodes.isVisible()) txtSecretAccessKeyNodes.show();
		if(!cbRegionNodes.isVisible()) cbRegionNodes.show();
	}

	private void show1and1Widgets() {
		if(txtAccessKeyNodes.isVisible()) txtAccessKeyNodes.hide();
		if(txtSecretAccessKeyNodes.isVisible()) txtSecretAccessKeyNodes.hide();
		if(cbRegionNodes.isVisible()) cbRegionNodes.hide();
		
		if(!txtIpAddresses1and1.isVisible()) txtIpAddresses1and1.show();
		if(!txtLoginName1and1.isVisible()) txtLoginName1and1.show();
		if(!txtPass1and1.isVisible()) txtPass1and1.show();
	}

	private void doShowAppropriateWidgets(Object event) {
		if("Amazon EC2".equals(event)) {
			showAwsWidgets();
		} else if ("1&1 Cloud".equals(event)) {
			show1and1Widgets();
		}
	}
}