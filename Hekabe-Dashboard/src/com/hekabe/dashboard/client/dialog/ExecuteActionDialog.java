package com.hekabe.dashboard.client.dialog;

import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.Window;

public class ExecuteActionDialog extends Window {
	Label lblAction;
	
	/**
	 * Creates Action dialog
	 * @param action
	 */
	public ExecuteActionDialog(String action) {
		setTitle("Please wait...");
		setIsModal(true);
		setShowModalMask(true);
		setShowCloseButton(false);
		setShowMinimizeButton(false);
		setHeaderIcon("[SKIN]/loadingSmall.gif");
		
		setPixelSize(320, 100);
		centerInPage();
		
		lblAction = new Label(action);
		lblAction.setHeight(16);
		lblAction.setAutoFit(true);
		
		addItem(lblAction);
		show();
	}
	
	/**
	 * If action was successfull, hide and destroy itself.
	 */
	public void onSuccess() {
		hide();
		destroy();
	}
	
	/**
	 * If action wasn't successfull, change title to "failed" and display close button.
	 */
	public void onFailure() {
		setTitle("failed");
		setShowCloseButton(true);
	}
}
