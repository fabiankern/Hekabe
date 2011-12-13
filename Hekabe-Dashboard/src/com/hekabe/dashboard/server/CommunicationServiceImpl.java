package com.hekabe.dashboard.server;

import java.io.IOException;
import java.io.InputStream;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.PropertiesCredentials;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2Client;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.hekabe.cassandra.cluster.EC2CassandraCluster;
import com.hekabe.dashboard.client.CommunicationService;

@SuppressWarnings("serial")
public class CommunicationServiceImpl extends RemoteServiceServlet implements
		CommunicationService {

	public String startInstance(Integer anzahlInstanzen) {
		//escapeHtml(anzahlInstanzen);
		
		AWSConnector aws = new AWSConnector();
		//aws.startInstance(Integer.parseInt(anzahlInstanzen));
		aws.startInstance(anzahlInstanzen);
		return anzahlInstanzen.toString();
	}

	public String demo(String amiID, String instanceSize, int numberOfInstances) {
		InputStream credentialsAsStream = Thread.currentThread()
				.getContextClassLoader()
				.getResourceAsStream("AwsCredentials.properties");
		AWSCredentials credentials = null;
		
		try {
			credentials = new PropertiesCredentials(credentialsAsStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		AmazonEC2 ec2USEast = new AmazonEC2Client(credentials);
		ec2USEast.setEndpoint("ec2.us-east-1.amazonaws.com");
		
		EC2CassandraCluster cluster = EC2CassandraCluster.initializeCluster(
				ec2USEast, "ami-1a0f3d6e", instanceSize, numberOfInstances, "HekabeCassandra73",
				"hekabetestkeypair2", "hekabetest", true);
		
		return null;
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
