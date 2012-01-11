package com.hekabe.dashboard.client.view;

import com.hekabe.dashboard.client.presenter.NewClusterHardwarePresenter;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.HasClickHandlers;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ComboBoxItem;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.IntegerItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.layout.VLayout;

public class NewClusterHardwareView extends VLayout implements NewClusterHardwarePresenter.Display {
	
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

	public NewClusterHardwareView() {		
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
		
		dynamicForm.setFields(new FormItem[] { cbProvider, txtAccessKey, txtSecretAccessKey });
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
	}

	public HasClickHandlers getNextButton() {
		return btnSwitchToTab2;
	}

	public String getProvider() {
		return cbProvider.getValueAsString();
	}

	public String getSecretAccessKey() {
		return txtSecretAccessKey.getValueAsString();
	}

	public int getNumberOfInstances() {
		return intNumberOfInstances.getValueAsInteger();
	}

	public String getRegion() {
		return cbRegion.getValueAsString();
	}

	public String getInstanceSize() {
		return cbInstanceSize.getValueAsString();
	}
	
	public VLayout asVLayout() {
		return this;
	}
}