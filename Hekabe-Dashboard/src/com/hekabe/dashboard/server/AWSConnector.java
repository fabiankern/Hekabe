package com.hekabe.dashboard.server;

import java.io.IOException;
import java.util.List;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.PropertiesCredentials;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2Client;
import com.amazonaws.services.ec2.model.CreateTagsRequest;
import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.RunInstancesRequest;
import com.amazonaws.services.ec2.model.RunInstancesResult;
import com.amazonaws.services.ec2.model.Tag;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

@SuppressWarnings("serial")
public class AWSConnector extends RemoteServiceServlet {
	private AWSCredentials credentials;
	
	public AWSConnector() {
		
	}
	
	public void startInstance(int anzahlInstanzen) {
		try {
			System.out.println(this.getClass().getName());
			credentials = new PropertiesCredentials(
			           	//AWSConnector.class.getResourceAsStream("/AwsCredentials.properties"));
						//AWSConnector.class.getResourceAsStream("AwsCredentials.properties"));
						//AWSCtype filter textonnector.class.getResourceAsStream("/war/WEB-INF/AwsCredentials.properties"));
						this.getClass().getClassLoader().getResourceAsStream("AwsCredentials.properties"));
		} catch (IOException e1) {
			System.out.println("Credentials were not properly entered into AwsCredentials.properties.");
			System.out.println(e1.getMessage());
			System.exit(-1);
		}
		
		AmazonEC2 ec2 = new AmazonEC2Client(credentials);
		ec2.setEndpoint("ec2.us-east-1.amazonaws.com");

		// CREATE EC2 INSTANCES
		RunInstancesRequest runInstancesRequest = new RunInstancesRequest()
		    .withInstanceType("m1.large")
		    .withImageId("ami-edc30384")
		    .withMinCount(anzahlInstanzen)
		    .withMaxCount(anzahlInstanzen)
		    .withSecurityGroupIds("Cassandra")
		    .withKeyName("hekabe")
		    .withUserData("fkern")
		;

		RunInstancesResult runInstances = ec2.runInstances(runInstancesRequest);

		// TAG EC2 INSTANCES
		List<Instance> instances = runInstances.getReservation().getInstances();
		int idx = 1;
		for (Instance instance : instances) {
		  CreateTagsRequest createTagsRequest = new CreateTagsRequest();
		  createTagsRequest.withResources(instance.getInstanceId()) //
		      .withTags(new Tag("Name", "fabian-test-" + idx));
		  ec2.createTags(createTagsRequest);

		  idx++;
		}
	}
}