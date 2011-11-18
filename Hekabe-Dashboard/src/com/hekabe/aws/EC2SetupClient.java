package com.hekabe.aws;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.binary.Base64;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.PropertiesCredentials;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2Client;
import com.amazonaws.services.ec2.model.CreateTagsRequest;
import com.amazonaws.services.ec2.model.DescribeImagesRequest;
import com.amazonaws.services.ec2.model.DescribeImagesResult;
import com.amazonaws.services.ec2.model.DescribeInstancesRequest;
import com.amazonaws.services.ec2.model.DescribeInstancesResult;
import com.amazonaws.services.ec2.model.Filter;
import com.amazonaws.services.ec2.model.Image;
import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.Reservation;
import com.amazonaws.services.ec2.model.RunInstancesRequest;
import com.amazonaws.services.ec2.model.RunInstancesResult;
import com.amazonaws.services.ec2.model.Tag;

public class EC2SetupClient {
 
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		InputStream credentialsAsStream = Thread.currentThread()
				.getContextClassLoader()
				.getResourceAsStream("AwsCredentials.properties");
		AWSCredentials credentials = null;
		try {
			credentials = new PropertiesCredentials(credentialsAsStream);
		} catch (IOException e) {
			e.printStackTrace();
		}

		AmazonEC2 ec2 = new AmazonEC2Client(credentials);
		ec2.setEndpoint("ec2.us-east-1.amazonaws.com");

		//getAMIId(ec2);
		launchCluster(ec2);
		//terminateInstance(ec2, "i-23ff4640");
	}

	public static void terminateInstance(AmazonEC2 ec2, String... instanceIds) {

		DescribeInstancesRequest describeInstance = new DescribeInstancesRequest().withFilters(new Filter("key-name").withValues("hekabe"));
		DescribeInstancesResult describeInstanceResult = ec2
				.describeInstances(describeInstance);
		List<Reservation> reservations = describeInstanceResult
				.getReservations();
		for (Reservation r : reservations) {
			List<Instance> instances = r.getInstances();
			for (Instance i : instances) {
				System.out.println(i.getPublicIpAddress());
			}
		}
		// TerminateInstancesRequest terminateRequest = new
		// TerminateInstancesRequest().withInstanceIds(instanceIds);
		// ec2.terminateInstances(terminateRequest);
	}

	public static void getAMIId(AmazonEC2 ec2) {
		DescribeImagesRequest imageRequest = new DescribeImagesRequest()
				.withOwners("620704289608");

		DescribeImagesResult imageResult = ec2.describeImages(imageRequest);

		List<Image> images = imageResult.getImages();
		for (Image i : images) {
			System.out.println(i.getImageId() + " " + i.getName() + " "
					+ i.getDescription());
		}
	}

	public static void launchCluster(AmazonEC2 ec2) {

		ArrayList<CassandraInstance> cassandraInstances = new ArrayList<CassandraInstance>();
		String userData = "-n 'mynewcluster' -s 2";
 
		// CREATE EC2 INSTANCES
		RunInstancesRequest runInstancesRequest = new RunInstancesRequest()
				.withInstanceType("m1.large").withImageId("ami-edc30384")
				.withMinCount(2).withMaxCount(2)
				.withSecurityGroupIds("sg-7dafb914").withKeyName("hekabe")
				.withUserData(Base64.encodeBase64String(userData.getBytes()));
 
		RunInstancesResult runInstances = ec2.runInstances(runInstancesRequest);
		
		// TAG EC2 INSTANCES

		List<Instance> instances = runInstances.getReservation().getInstances();
		int idx = 1;
		for (Instance instance : instances) {
			CreateTagsRequest createTagsRequest = new CreateTagsRequest();
			createTagsRequest.withResources(instance.getInstanceId()) //
					.withTags(new Tag("Name", "hekabe-test" + idx));
			ec2.createTags(createTagsRequest);
			idx++;
			//update config for every instance 
			System.out.println("Instance with id " + instance.getInstanceId() + " startet");
			cassandraInstances.add(new CassandraInstance(instance.getInstanceId(), ec2));
		}
		for(CassandraInstance ci : cassandraInstances){
			ci.updateConfiguration();
		}
	}
}