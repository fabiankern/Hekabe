package com.hekabe.aws;

import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.model.DescribeInstancesRequest;
import com.amazonaws.services.ec2.model.DescribeInstancesResult;
import com.amazonaws.services.ec2.model.Instance;

public class CassandraInstance{

	private String instanceId;
	private AmazonEC2 ec2;
	
	public CassandraInstance(String instanceId, AmazonEC2 ec2){
		this.instanceId = instanceId;
		this.ec2 = ec2;
	}
	
	public Instance getInstanceReference(){
		DescribeInstancesRequest instanceRequest = new DescribeInstancesRequest();
		instanceRequest.getInstanceIds().add(instanceId);
		DescribeInstancesResult instanceResult = ec2.describeInstances(instanceRequest);
		Instance instance = instanceResult.getReservations().get(0).getInstances().get(0);
		return instance;
	}
	
	public void updateConfiguration(){
		new InstanceUpdater(this).start();
	}
	
}
