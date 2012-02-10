package com.hekabe.dashboard.client.dialog;

import java.util.HashMap;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.hekabe.dashboard.client.CommunicationServiceAsync;
import com.hekabe.dashboard.shared.parameter.YCSBParameter;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.CheckboxItem;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.LinkItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ChangeEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangeHandler;

public class BenchmarkDialog extends Window {
	private static final String URL = "http://217.160.94.220:8080/Download/download?filename=";
	
	CheckboxItem chkLoad;
	IButton btnStartBenchmark;
	TextItem txtFilename;
	TextItem txtRecordCount;
	TextItem txtOperationCount;
	TextItem txtWorkload;
	TextItem txtReadAllFields;
	TextItem txtReadProportion;
	TextItem txtScanProportion;
	TextItem txtInsertProportion;
	TextItem txtRequestDistribution;
	TextItem txtInsertStart;
	TextItem txtInsertCount;
	LinkItem linkResult;
	DynamicForm form;
	
	String user;
	String tag;
	long timestamp;
	CommunicationServiceAsync rpcService;
	
	HashMap<String, String> parameters;
	
	/**
	 * Creates Benchmark dialog
	 * @param user
	 * @param timestamp
	 * @param rpcService
	 */
	public BenchmarkDialog(String user, long timestamp, CommunicationServiceAsync rpcService) {
		this.user = user;
		this.timestamp = timestamp;
		this.rpcService = rpcService;
		this.tag = user + "_" + timestamp;
		
		setTitle("YCSB Benchmark");
		setPixelSize(270, 420);
		setAutoCenter(true);
		
		parameters = new HashMap<String, String>();
		form = new DynamicForm();
		
		txtFilename = new TextItem("txtFilename", "Filename");
		chkLoad = new CheckboxItem("chkLoad", "Load");
		txtRecordCount = new TextItem("txtRecordCount", "Record count");
		txtOperationCount = new TextItem("txtOperationCount", "Operation count");
		txtWorkload = new TextItem("txtWorkload", "Workload");
		txtReadAllFields = new TextItem("txtReadAllFields", "Read all fields");
		txtReadProportion = new TextItem("txtReadProportion", "Read proportion");
		txtScanProportion = new TextItem("txtScanProportion", "Scan proportion");
		txtInsertProportion = new TextItem("txtInsertProportion", "Insert proportion");
		txtRequestDistribution = new TextItem("txtRequestDistribution", "Request Distribution");
		txtInsertStart = new TextItem("txtInsertStart", "Insert start");
		txtInsertCount = new TextItem("txtInsertCount", "Insert count");
		btnStartBenchmark = new IButton("Start benchmark");
		linkResult = new LinkItem("Download");
		
		form.setFields(new FormItem[] { txtFilename, chkLoad, txtRecordCount, txtOperationCount, txtWorkload, 
				txtReadAllFields, txtReadProportion, txtScanProportion, txtInsertProportion,
				txtRequestDistribution, txtInsertStart, txtInsertCount, linkResult });
		
		addItem(form);
		addItem(btnStartBenchmark);
		
		bind();
		
		initPresetValues();
	}

	/**
	 * Binds handlers.
	 */
	private void bind() {
		txtFilename.addChangeHandler(new ChangeHandler() {
			
			public void onChange(ChangeEvent event) {
				if(event.getValue() == null) {
					linkResult.setLinkTitle("");
					linkResult.setValue("");
				} else {
					StringBuilder sb = new StringBuilder(URL);
					sb.append(tag).append("_").append(event.getValue()).append(".csv");
					linkResult.setLinkTitle(sb.toString());
					linkResult.setValue(sb.toString());
				}
			}
		});
		
		btnStartBenchmark.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				doStartBenchmark();
			}
		});
	}

	/** 
	 * Starts benchmarking
	 */
	protected void doStartBenchmark() {
		readParameters();
		
		StringBuilder sb = new StringBuilder(tag);
		sb.append("_").append(txtFilename.getValueAsString());
		
		parameters.put("filename", sb.toString());
		
		rpcService.startBenchmark(user, timestamp, parameters, new AsyncCallback<String>() {
			
			public void onSuccess(String result) {
				SC.say("doStartBenchmark success");
			}
			
			public void onFailure(Throwable caught) {
				SC.say("doStartBenchmark failed");
			}
		});
	}
	
	/**
	 * Read parameters
	 */
	private void readParameters() {
		parameters.put(YCSBParameter.RECORD_COUNT, txtRecordCount.getValueAsString());
		parameters.put(YCSBParameter.OPERATION_COUNT, txtOperationCount.getValueAsString());
		parameters.put(YCSBParameter.WORKLOAD, txtWorkload.getValueAsString());
		parameters.put(YCSBParameter.READ_ALL_FIELDS, txtReadAllFields.getValueAsString());
		parameters.put(YCSBParameter.READ_PROPORTION, txtReadProportion.getValueAsString());
		parameters.put(YCSBParameter.SCAN_PROPORTION, txtScanProportion.getValueAsString());
		parameters.put(YCSBParameter.INSERT_PROPORTION, txtInsertProportion.getValueAsString());
		parameters.put(YCSBParameter.REQUEST_DISTRIBUTION, txtRequestDistribution.getValueAsString());
		parameters.put(YCSBParameter.INSERT_START, txtInsertStart.getValueAsString());
		parameters.put(YCSBParameter.INSERT_COUNT, txtInsertCount.getValueAsString());
		String chkLoadValue = null;
		
		if(chkLoad.getValueAsBoolean()) {
			chkLoadValue = "true";
		} else {
			chkLoadValue = "false";
		}
		parameters.put(YCSBParameter.LOAD, chkLoadValue);
	}

	/**
	 * Set default values.
	 */
	private void initPresetValues() {
		txtRecordCount.setValue("1000");
		txtOperationCount.setValue("1000");
		txtWorkload.setValue("com.yahoo.ycsb.workloads.CoreWorkload");
		txtReadAllFields.setValue("true");
		txtReadProportion.setValue("0");
		txtScanProportion.setValue("0");
		txtInsertProportion.setValue("0.5");
		txtRequestDistribution.setValue("zipfian");
		txtInsertStart.setValue("0");
		txtInsertCount.setValue("1000");
		chkLoad.setValue(false);
	}
}
