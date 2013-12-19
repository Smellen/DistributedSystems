//import java.io.*;
import java.net.*;

class UDPServer { 
	
	DatagramSocket serverSocket;
	int portNum;
	
	public UDPServer(int portNum) throws SocketException{
		this.portNum = portNum;
		serverSocket = new DatagramSocket(portNum);
		
	}
 
 public void server() throws Exception {   
  
 
 
	 	byte[] receiveData = new byte[1024];             
	 	byte[] sendData = new byte[1024];             
 
	while(true){                   
		DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);    
		//System.out.println("Before receive:");
		serverSocket.receive(receivePacket); 
		//System.out.println("after receive:");
		String sentence = new String(receivePacket.getData());                   
		System.out.println("RECEIVED: " + sentence);                   
		InetAddress IPAddress =receivePacket.getAddress();                   
		int port = receivePacket.getPort();                   
		String capitalizedSentence =
		sentence.toUpperCase();                   
		sendData = capitalizedSentence.getBytes();                   
		DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);                   
		serverSocket.send(sendPacket);                
		}       
	} 
}