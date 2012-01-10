package com.hekabe.dashboard.client.view;

import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ComboBoxItem;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
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

	public MgmtDetailNewNodeView() {
		
		lblNewNode = new Label("New Node");
		lblNewNode.setHeight(30);
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
	}
}
