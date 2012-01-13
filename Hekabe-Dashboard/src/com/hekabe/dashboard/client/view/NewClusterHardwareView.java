package com.hekabe.dashboard.client.view;

import com.hekabe.dashboard.client.CommunicationServiceAsync;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ComboBoxItem;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.IntegerItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tab.TabSet;

public class NewClusterHardwareView extends VLayout {
	
	private Label lblProvider;
	private DynamicForm dynamicForm;
	private ComboBoxItem cbProvider;
	private TextItem txtAccessKey;
	private TextItem txtSecretAccessKey;
	private Label lblCluster;
	private DynamicForm dynamicForm_01;
	private IntegerItem intNumberOfInstances;
	private ComboBoxItem cbRegion;
	private ComboBoxItem cbInstanceSize;
	private Button btnSwitchToTab2;
	private NewClusterView newClusterView;
	private CommunicationServiceAsync rpcService;
	private TextItem txtIpAddresses1and1;
	private TextItem txtLoginName1and1;
	private TextItem txtPass1and1;

	public NewClusterHardwareView(NewClusterView newClusterView, CommunicationServiceAsync rpcService) {
		this.newClusterView = newClusterView;
		this.rpcService = rpcService;
		
		lblProvider = new Label("Provider");
		lblProvider.setHeight("30");
		addMember(lblProvider);
		
		dynamicForm = new DynamicForm();
		cbProvider = new ComboBoxItem("Provider", "Provider");
		cbProvider.setShowTitle(true);
		cbProvider.setTooltip("Choose Cloud-Provider.");
		cbProvider.setValueMap("Amazon EC2","1&1 Cloud");
		cbProvider.setDefaultToFirstOption(true);
		
		txtAccessKey = new TextItem("txtAccessKey", "Access Key");
		txtSecretAccessKey = new TextItem("txtSecretAccessKey", "Secret Access Key");
		txtIpAddresses1and1 = new TextItem("txtIpAddresses", "IPs");
		txtLoginName1and1 = new TextItem("txtLoginName1and1", "Login");
		txtPass1and1 = new TextItem("txtPass1and1", "Password");
		
		dynamicForm.setFields(new FormItem[] { cbProvider, txtAccessKey, txtSecretAccessKey, txtIpAddresses1and1, txtLoginName1and1, txtPass1and1 });
		addMember(dynamicForm);
		
		lblCluster = new Label("Cluster");
		lblCluster.setHeight("30");
		addMember(lblCluster);
		
		dynamicForm_01 = new DynamicForm();
		intNumberOfInstances = new IntegerItem();
		intNumberOfInstances.setTooltip("The number of instances you want to start.");
		intNumberOfInstances.setTitle("Number of instances");
		
		cbRegion = new ComboBoxItem("Region", "Region");
		cbRegion.setValueMap("US East (Virginia)",
							 "US West (Oregon)",
							 "US West (California)",
							 "EU West (Ireland)",
							 "Asia Pacific (Singapore)",
							 "Asia Pacific (Tokyo)");
		cbRegion.setDefaultToFirstOption(true);
		
		cbInstanceSize = new ComboBoxItem("InstanceSize", "Instance size");
		cbInstanceSize.setShowTitle(true);
		cbInstanceSize.setTooltip("Choose size of the instance(s).");
		cbInstanceSize.setValueMap("Large (m1.large)",
								   "Extra Large (m1.xlarge)",
								   "High-Memory Extra Large (m2.xlarge)",
								   "High-Memory Double Extra Large (m2.2xlarge)",
								   "High-Memory Quadruple Extra Large (m2.4xlarge)",
								   "High-CPU Extra Large (c1.xlarge)");
		cbInstanceSize.setDefaultToFirstOption(true);
		
		dynamicForm_01.setFields(new FormItem[] { intNumberOfInstances, cbRegion, cbInstanceSize });
		addMember(dynamicForm_01);
		
		btnSwitchToTab2 = new Button("Next");
		
		addMember(btnSwitchToTab2);
		
		bind();
		
		doShowAppropriateWidgets(cbProvider.getValue());
	}
	
	/**
	 * Binds handlers to widgets.
	 */
	private void bind() {
		btnSwitchToTab2.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				doSwitchToNextTab();
			}
		});
		
		cbProvider.addChangedHandler(new ChangedHandler() {			
			public void onChanged(ChangedEvent event) {
				doShowAppropriateWidgets(event.getValue());
			}
		});
	}

	/**
	 * Enables and switches to the next tab.
	 */
	private void doSwitchToNextTab() {
		TabSet tabSet = newClusterView.getTabSet();
		tabSet.enableTab(tabSet.getSelectedTabNumber() + 1);
		tabSet.selectTab(tabSet.getSelectedTabNumber() + 1);		
	}

	/**
	 * 
	 * @return Selected provider.
	 */
	public String getProvider() {
		return cbProvider.getValueAsString();
	}

	/**
	 * 
	 * @return Access key
	 */
	public String getAccessKey() {
		return txtAccessKey.getValueAsString();
	}
	
	/**
	 * 
	 * @return Secret access key
	 */
	public String getSecretAccessKey() {
		return txtSecretAccessKey.getValueAsString();
	}

	/**
	 * 
	 * @return Number of instances wanted to start.
	 */
	public int getNumberOfInstances() {
		return intNumberOfInstances.getValueAsInteger();
	}

	/**
	 * Only AWS
	 * @return Region where the instances should be started.
	 */
	public String getRegion() {
		return cbRegion.getValueAsString();
	}

	/**
	 * Only AWS
	 * @return AWS instance size.
	 */
	public String getInstanceSize() {
		return cbInstanceSize.getValueAsString();
	}
	
	private void showAwsWidgets() {
		if(txtIpAddresses1and1.isVisible()) txtIpAddresses1and1.hide();
		if(txtLoginName1and1.isVisible()) txtLoginName1and1.hide();
		if(txtPass1and1.isVisible()) txtPass1and1.hide();
		
		if(!txtAccessKey.isVisible()) txtAccessKey.show();
		if(!txtSecretAccessKey.isVisible()) txtSecretAccessKey.show();
		if(!cbRegion.isVisible()) cbRegion.show();
		if(!lblCluster.isVisible()) lblCluster.show();
		if(!intNumberOfInstances.isVisible()) intNumberOfInstances.show();
		if(!cbInstanceSize.isVisible()) cbInstanceSize.show();
	}

	private void show1and1Widgets() {
		if(txtAccessKey.isVisible()) txtAccessKey.hide();
		if(txtSecretAccessKey.isVisible()) txtSecretAccessKey.hide();
		if(cbRegion.isVisible()) cbRegion.hide();
		if(lblCluster.isVisible()) lblCluster.hide();
		if(intNumberOfInstances.isVisible()) intNumberOfInstances.hide();
		if(cbInstanceSize.isVisible()) cbInstanceSize.hide();
		
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