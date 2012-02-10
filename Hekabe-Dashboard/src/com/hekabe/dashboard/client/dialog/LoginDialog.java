package com.hekabe.dashboard.client.dialog;

import java.util.HashMap;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.hekabe.dashboard.client.CommunicationServiceAsync;
import com.hekabe.dashboard.client.Dashboard;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.widgets.Dialog;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.PasswordItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.KeyUpEvent;
import com.smartgwt.client.widgets.form.fields.events.KeyUpHandler;
import com.smartgwt.client.widgets.layout.VLayout;

public class LoginDialog extends Dialog {

	private DynamicForm dynamicForm;
	private TextItem txtUser;
	private PasswordItem txtPassword;
	private Label responseLabel;
	private IButton btnLogin;
	private VLayout layout;
	private Dashboard dashboard;
	private CommunicationServiceAsync rpcService;
	private HashMap<String, String> loginData;
	private String user;
	private String password;

	/**
	 * Creates LoginDialog
	 * @param dashboard
	 * @param rpcService
	 */
	public LoginDialog(Dashboard dashboard, CommunicationServiceAsync rpcService) {
		
		this.dashboard = dashboard;
		this.rpcService = rpcService;
		
		layout = new VLayout();		
		setTitle("Login");
		setHeight(160);
		setIsModal(true);
		setShowModalMask(true);
		setAutoFocus(false);
		setOverflow(Overflow.VISIBLE);
		setShowCloseButton(false);

		btnLogin = new IButton("Login");
		setToolbarButtons(btnLogin);
		setShowToolbar(true);
		
		dynamicForm = new DynamicForm();
		txtUser = new TextItem("txtUser", "User");
		txtPassword = new PasswordItem("txtPassword", "Password");
		responseLabel = new Label("");
		responseLabel.setStyleName("error");
		responseLabel.setHeight(14);
		
		dynamicForm.setFields(new FormItem[] { txtUser, txtPassword });
		dynamicForm.setAutoFocus(true);
		
		layout.addMember(dynamicForm);	
		layout.addMember(responseLabel);
		addItem(layout);
		
		bind();
	}

	/**
	 * Binds handlers.
	 */
	private void bind() {
		btnLogin.addClickHandler(new ClickHandler() {			
			public void onClick(ClickEvent event) {
				doDialogSubmit();
			}
		});

		txtUser.addKeyUpHandler(new KeyUpHandler() {			
			public void onKeyUp(KeyUpEvent event) {
				if(event.getKeyName().equals("Enter")) {
					doDialogSubmit();
				}									
			}
		});
		
		txtPassword.addKeyUpHandler(new KeyUpHandler() {			
			public void onKeyUp(KeyUpEvent event) {
				if(event.getKeyName().equals("Enter")) {
					doDialogSubmit();
				}									
			}
		});		
	}
		
	/**
	 * Dialog submit, checks user credentials
	 */
	private void doDialogSubmit() {
		btnLogin.setIcon("[SKIN]/loadingSmall.gif");
		loginData = new HashMap<String, String>();
		user = txtUser.getValueAsString();
		password = txtPassword.getValueAsString();
		loginData.put("user", user);
		loginData.put("password", password);	
		
		rpcService.login(loginData, new AsyncCallback<Boolean>() {						

			public void onFailure(Throwable caught) {
				btnLogin.setIcon(null);
				responseLabel.setContents("Server communication failed");
				responseLabel.setIcon("[SKIN]/actions/exclamation.png");				
			}

			public void onSuccess(Boolean result) {
				btnLogin.setIcon(null);
				if(result) {
					loginSuccesful();					
				} else {					
					loginFailed();
				}				
			}
		});
	}
	
	private void loginSuccesful() {
		dashboard.getSectionStack().expandSection(1);
		dashboard.getHeaderView().setUsername(user);
		dashboard.getHeaderView().showLogoutBox();
		hide();
	}
	
	private void loginFailed() {
		responseLabel.setContents("Login data wrong");
		responseLabel.setIcon("[SKIN]/actions/exclamation.png");
	}
	
	public void show() {
		txtUser.setValue("");
		txtPassword.setValue("");
		dynamicForm.focusInItem(txtUser);
		responseLabel.setContents("");
		responseLabel.setIcon(null);
		super.show();
	}
}
