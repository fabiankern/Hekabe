package com.hekabe.aws;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import com.sshtools.j2ssh.SshClient;
import com.sshtools.j2ssh.authentication.PublicKeyAuthenticationClient;
import com.sshtools.j2ssh.session.SessionChannelClient;
import com.sshtools.j2ssh.transport.IgnoreHostKeyVerification;
import com.sshtools.j2ssh.transport.publickey.InvalidSshKeyException;
import com.sshtools.j2ssh.transport.publickey.SshPrivateKey;
import com.sshtools.j2ssh.transport.publickey.SshPrivateKeyFile;

public class InstanceUpdater extends Thread{
	
	private CassandraInstance instance;
	
	public InstanceUpdater(CassandraInstance instance){
		this.instance = instance;
	}
	
	public void run(){
		updateInstance();
	}
	
	public void updateInstance(){	
		String ip = instance.getInstanceReference().getPublicIpAddress();
		//wait the instance to come up
		while(ip==null){
			System.out.println("ip is " + ip + ". Instance is not up yet, waiting another 10 sec");
			try {
				TimeUnit.SECONDS.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			ip = instance.getInstanceReference().getPublicIpAddress();
		}
		System.out.println("instance up and running, waiting 30 sec to complete configuration");
		try {
			TimeUnit.SECONDS.sleep(30);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		configMultiRegion(ip);
		System.out.println("configured instance");
		
	}
	
	private static void configMultiRegion(String ip){
		File keyFile = new File(
				"F:\\Programmieren\\workspace\\awsTesting\\src\\hekabe.pem");
		try {

			PublicKeyAuthenticationClient authClient = new PublicKeyAuthenticationClient();
			SshPrivateKeyFile sshPrivKeyFile = SshPrivateKeyFile.parse(keyFile);
			SshPrivateKey sshPrivKey = sshPrivKeyFile.toPrivateKey("");
			authClient.setKey(sshPrivKey);
			authClient.setUsername("ubuntu");
			 System.out.println("authentication possible? " + authClient.canAuthenticate());

			SshClient sshClient = new SshClient();
			sshClient.connect(ip, new IgnoreHostKeyVerification());
			sshClient.authenticate(authClient);
			System.out.println("connecting to " + ip + " as ubuntu");
			SessionChannelClient channel = sshClient.openSessionChannel();
			channel.executeCommand("rm testskript*");
			sshClient.openChannel(channel);
			channel.executeCommand("wget http://dl.dropbox.com/u/3027291/testskript.py");
			
			try {
				TimeUnit.SECONDS.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			sshClient.openChannel(channel);
			channel.executeCommand("sudo chmod a+x testskript.py");
			sshClient.openChannel(channel);
			channel.executeCommand("sudo python testskript.py " + ip);
			System.out.println("set listen_address to " + ip);
		} catch (InvalidSshKeyException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
