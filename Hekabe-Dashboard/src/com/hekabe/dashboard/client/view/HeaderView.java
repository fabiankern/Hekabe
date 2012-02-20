package com.hekabe.dashboard.client.view;

import com.hekabe.dashboard.client.Dashboard;
import com.hekabe.dashboard.client.dialog.LoginDialog;
import com.hekabe.dashboard.shared.parameter.StringParameter;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class HeaderView extends HLayout {

	private Dashboard dashboard;
	private Label lblHekabeDashboard;
	private Label lblUser;
	private Button btnLogout;
	private VLayout layoutUser;
	private String user;
	
	/**
	 * Creates header view
	 * @param dashboard
	 */
	public HeaderView(Dashboard dashboard) {
		this.dashboard = dashboard;
		
		setPixelSize(1000, 60);
		lblHekabeDashboard = new Label(StringParameter.HEKABE_DASHBOARD);
		lblHekabeDashboard.setWidth(800);
		lblHekabeDashboard.setHeight(60);
		lblHekabeDashboard.setStyleName("header");
		
		layoutUser = new VLayout();
		layoutUser.setLayoutAlign(Alignment.RIGHT);
		
		btnLogout = new Button("Logout");
		btnLogout.setLayoutAlign(Alignment.RIGHT);
		
		lblUser = new Label(StringParameter.LOGGED_IN_AS);
		lblUser.setHeight(25);
		lblUser.setIcon("[SKIN]/headerIcons/home.png");
		lblUser.setAlign(Alignment.RIGHT);
		
		layoutUser.addMember(btnLogout);
		layoutUser.addMember(lblUser);
		
		addMember(lblHekabeDashboard);
		addMember(layoutUser);
		
		layoutUser.hide();
		
		bind();
	}
	
	/**
	 * binds handler
	 */
	private void bind() {
		btnLogout.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				// reset layout
				dashboard.getMgmtView().getClusterView().clearForm();
				layoutUser.hide();
				setUsername("");
				// new login
				doShowLoginDialog();
			}
		});
	}

	/**
	 * shows login dialog
	 */
	protected void doShowLoginDialog() {
		LoginDialog loginDialog = dashboard.getLoginDialog();		
		loginDialog.show();
	}

	/**
	 * sets username
	 * @param username
	 */
	public void setUsername(String username) {
		this.user = username;
		StringBuilder sb = new StringBuilder(StringParameter.LOGGED_IN_AS);
		sb.append(username);
		
		lblUser.setContents(sb.toString());
	}
	
	/**
	 * returns username
	 * @return
	 */
	public String getUsername() {
		return user;
	}
	
	/**
	 * shows logout box
	 */
	public void showLogoutBox() {
		layoutUser.show();
	}
	
}
