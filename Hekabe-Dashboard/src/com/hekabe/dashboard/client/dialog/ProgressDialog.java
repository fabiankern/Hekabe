package com.hekabe.dashboard.client.dialog;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.Progressbar;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.layout.VLayout;

public class ProgressDialog extends Window {
	private Label currentLabel = null;
	private Progressbar progressbar;
	private VLayout layout;
	private int progress = 0;
	
	/**
	 * creates progress dialog
	 */
	public ProgressDialog() {
		
		setTitle("Please wait, starting cluster...");
		setIsModal(true);
		setShowModalMask(true);
		setShowCloseButton(false);
		setShowMinimizeButton(false);
		setHeaderIcon("[SKIN]/loadingSmall.gif");
		
		setPixelSize(350, 150);
		centerInPage();
		
		layout = new VLayout();
		layout.setHeight(100);
				
		progressbar = new Progressbar();
		progressbar.setHeight(30);
		progressbar.setWidth(300);
		progressbar.setMargin(2);
		progressbar.setLayoutAlign(Alignment.CENTER);
		progressbar.setPercentDone(progress);
		
		layout.addMember(progressbar);
		
		addItem(layout);
	}
	
	/**
	 * Sets current executed action
	 * @param action String
	 */
	public void setAction(String action) {
		Label label = new Label(action);
		label.setIconOrientation("right");
		label.setHeight(14);
		label.setMargin(1);
		
		if(currentLabel != null) {
			currentLabel.setIcon("[SKIN]/actions/approve.png");
		}
		
		currentLabel = label;
		
		layout.addMember(currentLabel);
	}
	
	/**
	 * Sets progress
	 * @param progress int
	 */
	public void setProgress(int progress) {
		progressbar.setPercentDone(progress);
	}
}
