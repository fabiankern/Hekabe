package com.hekabe.dashboard.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.hekabe.dashboard.client.CommunicationService;

@SuppressWarnings("serial")
public class CommunicationServiceImpl extends RemoteServiceServlet implements
		CommunicationService {

	@Override
	public String startInstance(Integer anzahlInstanzen) {
		//escapeHtml(anzahlInstanzen);
		
		AWSConnector aws = new AWSConnector();
		//aws.startInstance(Integer.parseInt(anzahlInstanzen));
		aws.startInstance(anzahlInstanzen);
		return anzahlInstanzen.toString();
	}

	/**
	 * Escape an html string. Escaping data received from the client helps to
	 * prevent cross-site script vulnerabilities.
	 * 
	 * @param html the html string to escape
	 * @return the escaped string
	 */
/*	private String escapeHtml(String html) {
		if (html == null) {
			return null;
		}
		return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;")
				.replaceAll(">", "&gt;");
	}*/
}
