package com.hekabe.dashboard.client.view;

import com.hekabe.dashboard.client.CommunicationServiceAsync;
import com.hekabe.dashboard.shared.parameter.StringParameter;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.CheckboxItem;
import com.smartgwt.client.widgets.form.fields.ComboBoxItem;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.IntegerItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ChangeEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangeHandler;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.form.validator.IntegerRangeValidator;
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
	private DynamicForm dynamicForm_02;
	private IntegerItem intNumberOfInstances;
	private ComboBoxItem cbRegion;
	private ComboBoxItem cbInstanceSize;
	private Button btnSwitchToTab2;
	private NewClusterView newClusterView;
	@SuppressWarnings("unused")
	private CommunicationServiceAsync rpcService;
	private TextItem txtIpAddresses1and1;
	private TextItem txtLoginName1and1;
	private TextItem txtPass1and1;
	private CheckboxItem checkBoxMultiRegion;
	private Label lblProviderMR;
	private DynamicForm dynamicFormMR;
	private ComboBoxItem cbProviderMR;
	private TextItem txtAccessKeyMR;
	private TextItem txtSecretAccessKeyMR;
	private TextItem txtIpAddresses1and1MR;
	private TextItem txtLoginName1and1MR;
	private TextItem txtPass1and1MR;
	private Label lblClusterMR;
	private DynamicForm dynamicForm_01MR;
	private IntegerItem intNumberOfInstancesMR;
	private ComboBoxItem cbRegionMR;
	private ComboBoxItem cbInstanceSizeMR;

	/**
	 * 
	 * @param newClusterView
	 * @param rpcService
	 */
	public NewClusterHardwareView(NewClusterView newClusterView, CommunicationServiceAsync rpcService) {
		this.newClusterView = newClusterView;
		this.rpcService = rpcService;
		
		lblProvider = new Label(StringParameter.PROVIDER);
		lblProvider.setHeight(30);
		lblProvider.setWidth(520);
		lblProvider.setStyleName("label");
		addMember(lblProvider);
		
		dynamicForm = new DynamicForm();
		cbProvider = new ComboBoxItem("Provider", StringParameter.PROVIDER);
		cbProvider.setShowTitle(true);
		cbProvider.setTooltip("Choose Cloud-Provider.");
		cbProvider.setValueMap(StringParameter.AMAZON_EC2, StringParameter.ONE_AND_ONE_CLOUD);
		cbProvider.setDefaultToFirstOption(true);
		
		txtAccessKey = new TextItem("txtAccessKey", StringParameter.ACCESS_KEY);
		txtSecretAccessKey = new TextItem("txtSecretAccessKey", StringParameter.SECRET_ACCESS_KEY);
		txtIpAddresses1and1 = new TextItem("txtIpAddresses", StringParameter.IPS);
		txtLoginName1and1 = new TextItem("txtLoginName1and1", StringParameter.LOGIN);
		txtLoginName1and1.setDefaultValue(StringParameter.ROOT);
		txtPass1and1 = new TextItem("txtPass1and1", StringParameter.PASSWORD);
		
		dynamicForm.setFields(new FormItem[] { cbProvider, txtAccessKey, txtSecretAccessKey, txtIpAddresses1and1, txtLoginName1and1, txtPass1and1 });
		addMember(dynamicForm);
		
		lblCluster = new Label(StringParameter.CLUSTER);
		lblCluster.setHeight(30);
		lblCluster.setWidth(520);
		lblCluster.setStyleName("label");
		addMember(lblCluster);
		
		dynamicForm_01 = new DynamicForm();
		intNumberOfInstances = new IntegerItem();
		intNumberOfInstances.setTooltip("The number of instances you want to start.");
		intNumberOfInstances.setTitle(StringParameter.NUMBER_OF_INSTANCES);
		intNumberOfInstances.setDefaultValue("1");
		intNumberOfInstances.setKeyPressFilter("[0-9]");
		
		cbRegion = new ComboBoxItem("Region", StringParameter.REGION);
		cbRegion.setValueMap(StringParameter.US_EAST_VIRGINIA,
							 StringParameter.US_WEST_OREGON,
							 StringParameter.US_WEST_CALIFORNIA,
							 StringParameter.EU_WEST_IRELAND,
							 StringParameter.ASIA_PACIFIC_SINGAPORE,
							 StringParameter.ASIA_PACIFIC_TOKYO,
							 StringParameter.SOUTH_AMERICA_SAO_PAULO);
		cbRegion.setDefaultToFirstOption(true);
		
		cbInstanceSize = new ComboBoxItem("InstanceSize", "Instance size");
		cbInstanceSize.setShowTitle(true);
		cbInstanceSize.setTooltip("Choose size of the instance(s).");
		cbInstanceSize.setValueMap(StringParameter.LARGE,
								   StringParameter.EXTRA_LARGE,
								   StringParameter.HIGH_MEMORY_EXTRA_LARGE,
								   StringParameter.HIGH_MEMORY_DOUBLE_EXTRA_LARGE,
								   StringParameter.HIGH_MEMORY_QUADRUPLE_EXTRA_LARGE,
								   StringParameter.HIGH_CPU_EXTRA_LARGE);
		cbInstanceSize.setDefaultToFirstOption(true);
			
		
		dynamicForm_01.setFields(new FormItem[] { intNumberOfInstances, cbRegion, cbInstanceSize });
		addMember(dynamicForm_01);
		
		dynamicForm_02 = new DynamicForm();
		checkBoxMultiRegion = new CheckboxItem("multiregionCluster", "Mulitregion Cluster?");
		checkBoxMultiRegion.setDefaultValue(false);
		
		dynamicForm_02.setFields(new FormItem[] { checkBoxMultiRegion });
		addMember(dynamicForm_02);
		
//		Multiregion part
		
		lblProviderMR = new Label("2nd Provider");
		lblProviderMR.setHeight(30);
		lblProviderMR.setWidth(520);
		lblProviderMR.setStyleName("label");
		addMember(lblProviderMR);
		
		dynamicFormMR = new DynamicForm();
		cbProviderMR = new ComboBoxItem("ProviderMR", "Provider");
		cbProviderMR.setShowTitle(true);
		cbProviderMR.setTooltip("Choose Cloud-Provider.");
		cbProviderMR.setValueMap(StringParameter.AMAZON_EC2,StringParameter.ONE_AND_ONE_CLOUD);
		cbProviderMR.setDefaultToFirstOption(true);
		
		txtAccessKeyMR = new TextItem("txtAccessKeyMR", "Access Key");
		txtSecretAccessKeyMR = new TextItem("txtSecretAccessKeyMR", "Secret Access Key");
		txtIpAddresses1and1MR = new TextItem("txtIpAddressesMR", "IPs");
		txtLoginName1and1MR = new TextItem("txtLoginName1and1MR", "Login");
		txtLoginName1and1MR.setDefaultValue(StringParameter.ROOT);
		txtPass1and1MR = new TextItem("txtPass1and1MR", "Password");
		
		dynamicFormMR.setFields(new FormItem[] { cbProviderMR, txtAccessKeyMR, txtSecretAccessKeyMR, txtIpAddresses1and1MR, txtLoginName1and1MR, txtPass1and1MR });
		addMember(dynamicFormMR);
		
		lblClusterMR = new Label("2nd Cluster");
		lblClusterMR.setHeight(30);
		lblClusterMR.setWidth(520);
		lblClusterMR.setStyleName("label");
		addMember(lblClusterMR);
		
		IntegerRangeValidator integerRangeValidator = new IntegerRangeValidator();
	    integerRangeValidator.setMin(1);
	    integerRangeValidator.setMax(20);
	    
		dynamicForm_01MR = new DynamicForm();
		intNumberOfInstancesMR = new IntegerItem();
		intNumberOfInstancesMR.setTooltip("The number of instances you want to start.");
		intNumberOfInstancesMR.setTitle("Number of instances");
		//intNumberOfInstancesMR.setValidators(integerRangeValidator);
		//intNumberOfInstancesMR.setValidateOnChange(true);
		intNumberOfInstancesMR.setDefaultValue("0");
		intNumberOfInstancesMR.setKeyPressFilter("[0-9]");
		
		cbRegionMR = new ComboBoxItem("RegionMR", "Region");
		cbRegionMR.setValueMap(StringParameter.US_EAST_VIRGINIA,
				 StringParameter.US_WEST_OREGON,
				 StringParameter.US_WEST_CALIFORNIA,
				 StringParameter.EU_WEST_IRELAND,
				 StringParameter.ASIA_PACIFIC_SINGAPORE,
				 StringParameter.ASIA_PACIFIC_TOKYO,
				 StringParameter.SOUTH_AMERICA_SAO_PAULO);
		cbRegionMR.setDefaultToFirstOption(true);
		
		cbInstanceSizeMR = new ComboBoxItem("InstanceSizeMR", "Instance size");
		cbInstanceSizeMR.setShowTitle(true);
		cbInstanceSizeMR.setTooltip("Choose size of the instance(s).");
		cbInstanceSizeMR.setValueMap(StringParameter.LARGE,
				   StringParameter.EXTRA_LARGE,
				   StringParameter.HIGH_MEMORY_EXTRA_LARGE,
				   StringParameter.HIGH_MEMORY_DOUBLE_EXTRA_LARGE,
				   StringParameter.HIGH_MEMORY_QUADRUPLE_EXTRA_LARGE,
				   StringParameter.HIGH_CPU_EXTRA_LARGE);
		cbInstanceSizeMR.setDefaultToFirstOption(true);
			
		
		dynamicForm_01MR.setFields(new FormItem[] { intNumberOfInstancesMR, cbRegionMR, cbInstanceSizeMR });
		addMember(dynamicForm_01MR);
		
		btnSwitchToTab2 = new Button("Next");
		addMember(btnSwitchToTab2);
		
		bind();
		
		doShowOrHideMultiRegionWidgets(checkBoxMultiRegion.getValueAsBoolean());
		doShowOrHideAppropriateWidgets(cbProvider.getValue());
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
		
		checkBoxMultiRegion.addChangeHandler(new ChangeHandler() {
			
			public void onChange(ChangeEvent event) {
				doShowOrHideMultiRegionWidgets((Boolean) event.getValue());
			}
		});
		
		cbProvider.addChangedHandler(new ChangedHandler() {			
			public void onChanged(ChangedEvent event) {
				doShowOrHideAppropriateWidgets(event.getValue());
			}
		});
		
		cbProviderMR.addChangedHandler(new ChangedHandler() {
			
			public void onChanged(ChangedEvent event) {
				doShowOrHideAppropriateWidgetsMR(event.getValue());
			}
		});
		
		TabSet tabSet = newClusterView.getTabSet();
		tabSet.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				if(intNumberOfInstancesMR.validate() == false) {
					TabSet tabSet = newClusterView.getTabSet();
					tabSet.disableTab(tabSet.getSelectedTabNumber() + 1);
				}
				
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
		return Integer.parseInt(intNumberOfInstances.getValueAsString());
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
	
	/**
	 * Only 1&1
	 * @return ip addresses divided by ;
	 */
	public String getIps() {
		return txtIpAddresses1and1.getValueAsString();
	}
	
	/**
	 * Only 1&1
	 * @return Login name
	 */
	public String getLoginName() {
		return txtLoginName1and1.getValueAsString();
	}
	
	/**
	 * Only 1&1
	 * @return Login password
	 */
	public String getPassword() {
		return txtPass1and1.getValueAsString();
	}
	
	/**
	 * 
	 * @return Start multiregion cluster?
	 */
	public boolean isMultiregionCluster() {
		return checkBoxMultiRegion.getValueAsBoolean();
	}
	
	/**
	 * 
	 * @return Selected multiregion provider.
	 */
	public String getMrProvider() {
		return cbProviderMR.getValueAsString();
	}

	/**
	 * 
	 * @return Multiregion Access key
	 */
	public String getMrAccessKey() {
		return txtAccessKeyMR.getValueAsString();
	}
	
	/**
	 * 
	 * @return Multiregion Secret access key
	 */
	public String getMrSecretAccessKey() {
		return txtSecretAccessKeyMR.getValueAsString();
	}

	/**
	 * 
	 * @return Multiregion Number of instances wanted to start.
	 */
	public int getMrNumberOfInstances() {
		return Integer.parseInt(intNumberOfInstancesMR.getValueAsString());
	}

	/**
	 * Only AWS
	 * @return Multiregion Region where the instances should be started.
	 */
	public String getMrRegion() {
		return cbRegionMR.getValueAsString();
	}

	/**
	 * Only AWS
	 * @return Multiregion AWS instance size.
	 */
	public String getMrInstanceSize() {
		return cbInstanceSizeMR.getValueAsString();
	}
	
	/**
	 * Only 1&1
	 * @return Multiregion ip addresses splitted by ;
	 */
	public String getMrIps() {
		return txtIpAddresses1and1MR.getValueAsString();
	}
	
	/**
	 * Only 1&1
	 * @return Multiregion Login name
	 */
	public String getMrLoginName() {
		return txtLoginName1and1MR.getValueAsString();
	}
	
	/**
	 * Only 1&1
	 * @return Multiregion Login password
	 */
	public String getMrPassword() {
		return txtPass1and1MR.getValueAsString();
	} 
	
	/**
	 * Show widgets necessary for AWS Cluster
	 */
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

	/**
	 * Show widgets necessary for 1&1 Cluster
	 */
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

	/**
	 * Checks cbProvider if Amazon EC2 or 1&1 Cloud is selected and shows appropriate widgets.
	 * @param event
	 */
	private void doShowOrHideAppropriateWidgets(Object event) {
		if("Amazon EC2".equals(event)) {
			showAwsWidgets();
		} else if ("1&1 Cloud".equals(event)) {
			show1and1Widgets();
		}
	}
	
	/**
	 * Show widgets necessary for AWS cluster in multiregion section
	 */
	private void showAwsWidgetsMR() {
		
		if(txtIpAddresses1and1MR.isVisible()) txtIpAddresses1and1MR.hide();
		if(txtLoginName1and1MR.isVisible()) txtLoginName1and1MR.hide();
		if(txtPass1and1MR.isVisible()) txtPass1and1MR.hide();
		
		if(!txtAccessKeyMR.isVisible()) txtAccessKeyMR.show();
		if(!txtSecretAccessKeyMR.isVisible()) txtSecretAccessKeyMR.show();
		if(!cbRegionMR.isVisible()) cbRegionMR.show();
		if(!lblClusterMR.isVisible()) lblClusterMR.show();
		if(!intNumberOfInstancesMR.isVisible()) intNumberOfInstancesMR.show();
		if(!cbInstanceSizeMR.isVisible()) cbInstanceSizeMR.show();
	}

	/**
	 * Show widgets necessary for 1&1 cluster in multiregion section
	 */
	private void show1and1WidgetsMR() {
		if(txtAccessKeyMR.isVisible()) txtAccessKeyMR.hide();
		if(txtSecretAccessKeyMR.isVisible()) txtSecretAccessKeyMR.hide();
		if(cbRegionMR.isVisible()) cbRegionMR.hide();
		if(lblClusterMR.isVisible()) lblClusterMR.hide();
		if(intNumberOfInstancesMR.isVisible()) intNumberOfInstancesMR.hide();
		if(cbInstanceSizeMR.isVisible()) cbInstanceSizeMR.hide();
		
		if(!txtIpAddresses1and1MR.isVisible()) txtIpAddresses1and1MR.show();
		if(!txtLoginName1and1MR.isVisible()) txtLoginName1and1MR.show();
		if(!txtPass1and1MR.isVisible()) txtPass1and1MR.show();
	}

	/**
	 * Checks cbProviderMR if Amazon EC2 or 1&1 Cloud is selected and shows appropriate widgets.
	 * @param event
	 */
	private void doShowOrHideAppropriateWidgetsMR(Object event) {
		if("Amazon EC2".equals(event)) {
			showAwsWidgetsMR();
		} else if ("1&1 Cloud".equals(event)) {
			show1and1WidgetsMR();
		}
	}
	
	/**
	 * hides all multriregion widgets if checkbox isn't checked
	 */
	private void hideMultiRegionWidgets() {		
		if(lblProviderMR.isVisible()) lblProviderMR.hide();
		if(dynamicFormMR.isVisible()) dynamicFormMR.hide();
		if(lblClusterMR.isVisible()) lblClusterMR.hide();
		if(dynamicForm_01MR.isVisible()) dynamicForm_01MR.hide();
		
	}
	/**
	 * show all multriregion widgets if checkbox is checked
	 */
	private void showMultiRegionWidgets() {		
		if(!lblProviderMR.isVisible()) lblProviderMR.show();
		if(!dynamicFormMR.isVisible()) dynamicFormMR.show();
		if(!lblClusterMR.isVisible()) lblClusterMR.show();
		if(!dynamicForm_01MR.isVisible()) dynamicForm_01MR.show();
	}
	
	/**
	 * Checks checkbox if multiregion is selected.
	 * @param isChecked
	 */
	private void doShowOrHideMultiRegionWidgets(Boolean isChecked) {		
		if(isChecked) {
			showMultiRegionWidgets();
			doShowOrHideAppropriateWidgetsMR(cbProviderMR.getValue());
		} else {
			hideMultiRegionWidgets();
		}
		
	}
}