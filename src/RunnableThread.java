import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

class RunnableThread implements Runnable {

	Thread runner;
	DatagramSocket clientSocket;
	DatagramSocket serverSocket;
	String dataBeingSent;
	byte[] sendingBytes;
	Node yeah;
	public RunnableThread(String threadName, int portNumber, Node yeah) throws SocketException {
		this.yeah = yeah;
		serverSocket = new DatagramSocket(portNumber);
		runner = new Thread(this, threadName); // (1) Create a new thread.
		runner.start(); // (2) Start the thread.
	}

	public void run() {	
		try {
		 	byte[] receiveData = new byte[1024];             
		 	byte[] sendData = new byte[1024];             
	 
		while(true){                   
			DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);    
			//System.out.println("Before receive:");
			serverSocket.receive(receivePacket); 
			//System.out.println("after receive:");
			String sentence = new String(receivePacket.getData());
			System.out.println("RECEIVED: " + sentence);  
			String delims = ",";
			String[] packetData = sentence.split(delims);

			int type = Integer.parseInt(packetData[0]);
			//String newLine = System.getProperty("line.separator");
			System.out.println("Node Keyword = " + yeah.nodeID + "  Packet Keyword = " + packetData[1]);
			//if(yeah.nodeID == Integer.parseInt(packetData[1])){
				System.out.println("Keywords are equal");
				switch(type){
				case 1:
					System.out.println("Type 1 JOINING");
					System.out.println("Node " + packetData[3] +" has joined the network");
					/*
					 Type 
					 nodeID
					 targetID 
					 ip
					*/
					/*The target node will add the sender nodeID to it's routing table*/
					yeah.routingTable.add(packetData[3]);
					break;
					
				case 2:
					System.out.println("Type 2 JOINING_NETWORK_RELAY");
					/*
					 Type 
					 nodeID
					 targetID
					 gatewayID  
					*/
					
					break;
					
				case 3:
					//System.out.println("Type 3 ROUTING");
					/*
					 Type 
					 nodeID
					 targetID
					 gatewayID
					*/
					try {
						clientSocket = new DatagramSocket();
					} catch (SocketException e1) {
						e1.printStackTrace();
					}
					String[] notNeeded4= {"X"};
					Packet routingPacket = new Packet(3, yeah.nodeID, yeah.hashCode("bootstrap"), 
							yeah.nodeID, 0, "localhost", yeah.keyword,
						null, notNeeded4, notNeeded4, notNeeded4);
	
					dataBeingSent = routingPacket.sendingPacket();
					sendingBytes = dataBeingSent.getBytes();
					
					DatagramPacket ACKindexPacket = new DatagramPacket(sendingBytes, sendingBytes.length, yeah.ip, 8767);
					try {
						clientSocket.send(ACKindexPacket);
					} catch (IOException e) {
						e.printStackTrace();
					}
					clientSocket.close();
					
					break;
					
				case 4:
					System.out.println("Type 4 LEAVING");
					/*
					 Type 
					 nodeID
					*/
					/*Remove sender node from target nodes*/
					System.out.println("Node " + packetData[3] +" has left the network");
					yeah.routingTable.remove(0);
					
					break;
					
				case 5:
					System.out.println("Type 5 INDEX - RUNNABLETHRWAD");
	//				System.out.println("Type = "+packetData[0] + newLine + "targetID = "+packetData[1] + newLine + "SenderId = "+packetData[2]
	//						+ newLine+ "nodeID = "+packetData[3] + newLine + "gatewayID = "+packetData[4] + "ip = "+packetData[5] + newLine + "keyword = "+packetData[6]
	//								+ newLine + "word = "+packetData[7]);
					/*
					 Type 
					 targetID
					 senderID
					 keyword
					 urls
					*/
					/*Should be my bootstrapnode
					 * Add url given from packet to the target node urls array list
					 */
					System.out.println("Url being added:  " + packetData[8]);
					yeah.urls.add(packetData[8]);
					try {
						clientSocket = new DatagramSocket();
					} catch (SocketException e1) {
						e1.printStackTrace();
					}
					String[] notused= {"X"};
					Packet ACKINDEXPacket = new Packet(10, yeah.nodeID, yeah.hashCode("bootstrap"), 
							yeah.nodeID, 0, "localhost", yeah.keyword,
						null, notused, notused, notused);
	
					dataBeingSent = ACKINDEXPacket.sendingPacket();
					sendingBytes = dataBeingSent.getBytes();
					
					DatagramPacket ACKindePacket = new DatagramPacket(sendingBytes, sendingBytes.length, yeah.ip, 8767);
					try {
						clientSocket.send(ACKindePacket);
					} catch (IOException e) {
						e.printStackTrace();
					}
					clientSocket.close();
					
					
					break;
					
				case 6:
					System.out.println("Type 6 SEARCH");
					/*
					 Type 
					 word
					 nodeID
					 senderID
					*/
					String searchResponse[] = new String[(yeah.urls).size()];
					System.out.println("	Searched Word:  " + packetData[6]);
					for(int x = 0; x<(yeah.urls).size(); x++){
						System.out.println("		"+x +":" + yeah.urls.get(x));
						searchResponse[x] = yeah.urls.get(x);
					}
					try {
						clientSocket = new DatagramSocket();
					} catch (SocketException e1) {
						e1.printStackTrace();
					}
					String[] notNeeded= {"X"};
					Packet indexPacket = new Packet(6, yeah.nodeID, yeah.hashCode("bootstrap"), 
							yeah.nodeID, 0, "localhost", yeah.keyword,
						null, notNeeded, searchResponse, notNeeded);
					
					dataBeingSent = indexPacket.sendingPacket();
					sendingBytes = dataBeingSent.getBytes();
					
					DatagramPacket joinNetWorkPacket = new DatagramPacket(sendingBytes, sendingBytes.length, yeah.ip, 8767);
					try {
						clientSocket.send(joinNetWorkPacket);
					} catch (IOException e) {
						e.printStackTrace();
					}
					clientSocket.close();
					
					
				
					break;
					
				case 7:
					//System.out.println("Type 7 SEARCH_RESPONSE");
					/*
					 Type 
					 word
					 nodeID
					 senderID
					 sender_response
					*/
					try {
						clientSocket = new DatagramSocket();
					} catch (SocketException e1) {
						e1.printStackTrace();
					}
					String[] notNeede= {"X"};
					Packet serResPacket = new Packet(7, yeah.nodeID, yeah.hashCode("bootstrap"), 
							yeah.nodeID, 0, "localhost", yeah.keyword,
						null, notNeede, notNeede, notNeede);
					
					dataBeingSent = serResPacket.sendingPacket();
					sendingBytes = dataBeingSent.getBytes();
					
					DatagramPacket searchResPacket = new DatagramPacket(sendingBytes, sendingBytes.length, yeah.ip, 8767);
					try {
						clientSocket.send(searchResPacket);
					} catch (IOException e) {
						e.printStackTrace();
					}
					clientSocket.close();
					
					System.out.println("	Searched Word:  " + packetData[6]);
					for(int x = 0; x<(yeah.urls).size(); x++){
						System.out.println("		"+x +":" + yeah.urls.get(x));
						
					}
					
					break;
					
				case 8:
					//System.out.println("Type 8 PING");
					/*
					 Type 
					 targetID
					 senderID
					*/
					//String searchResponse[] = new String[(yeah.urls).size()];
					try {
						clientSocket = new DatagramSocket();
					} catch (SocketException e1) {
						e1.printStackTrace();
					}
					String[] notNeeded2= {"X"};
					Packet pingPacket = new Packet(8, yeah.nodeID, yeah.hashCode("bootstrap"), 
							yeah.nodeID, 0, "localhost", yeah.keyword,
						null, notNeeded2, notNeeded2, notNeeded2);
					
					dataBeingSent = pingPacket.sendingPacket();
					sendingBytes = dataBeingSent.getBytes();
					
					DatagramPacket PingPacket = new DatagramPacket(sendingBytes, sendingBytes.length, yeah.ip, 8767);
					try {
						clientSocket.send(PingPacket);
					} catch (IOException e) {
						e.printStackTrace();
					}
					clientSocket.close();
					try {
						clientSocket = new DatagramSocket();
					} catch (SocketException e1) {
						e1.printStackTrace();
					}
					String[] notNeeded3= {"X"};
					Packet ACKPacket = new Packet(9, yeah.nodeID, yeah.hashCode("bootstrap"), 
							yeah.nodeID, 0, "localhost", yeah.keyword,
						null, notNeeded3, notNeeded3, notNeeded3);
	
					dataBeingSent = ACKPacket.sendingPacket();
					sendingBytes = dataBeingSent.getBytes();
					
					DatagramPacket ackPacket = new DatagramPacket(sendingBytes, sendingBytes.length, yeah.ip, 8767);
					try {
						clientSocket.send(ackPacket);
					} catch (IOException e) {
						e.printStackTrace();
					}
					clientSocket.close();
					
					
					break;
					
				case 9:
					//System.out.println("Type 9 ACK");
					/*
					 Type 
					 nodeID
					*/
					try {
						clientSocket = new DatagramSocket();
					} catch (SocketException e1) {
						e1.printStackTrace();
					}
					String[] notNeeded31= {"X"};
					Packet ACKPacket2 = new Packet(9, yeah.nodeID, yeah.hashCode("bootstrap"), 
							yeah.nodeID, 0, "localhost", yeah.keyword,
						null, notNeeded31, notNeeded31, notNeeded31);
	
					dataBeingSent = ACKPacket2.sendingPacket();
					sendingBytes = dataBeingSent.getBytes();
					
					DatagramPacket ackPacket2 = new DatagramPacket(sendingBytes, sendingBytes.length, yeah.ip, 8767);
					try {
						clientSocket.send(ackPacket2);
					} catch (IOException e) {
						e.printStackTrace();
					}
					clientSocket.close();
					
					break;
					
				case 10:
					//System.out.println("Type 10 ACK_INDEX");
					/*
					 Type 
					 nodeID
					 keyword
					*/
					try {
						clientSocket = new DatagramSocket();
					} catch (SocketException e1) {
						e1.printStackTrace();
					}
					String[] notused2= {"X"};
					Packet ACKINDEXPacket2 = new Packet(10, yeah.nodeID, yeah.hashCode("bootstrap"), 
							yeah.nodeID, 0, "localhost", yeah.keyword,
						null, notused2, notused2, notused2);
	
					dataBeingSent = ACKINDEXPacket2.sendingPacket();
					sendingBytes = dataBeingSent.getBytes();
					
					DatagramPacket ACKindePacket2 = new DatagramPacket(sendingBytes, sendingBytes.length, yeah.ip, 8767);
					try {
						clientSocket.send(ACKindePacket2);
					} catch (IOException e) {
						e.printStackTrace();
					}
					clientSocket.close();
					
					break;
					
				default:
					//System.out.println("No such packet type");
					break;
				}
			//}
				
			InetAddress IPAddress =receivePacket.getAddress();                   
			int port = receivePacket.getPort();                   
			String capitalizedSentence =
			sentence.toUpperCase();                   
			sendData = capitalizedSentence.getBytes();                   
			DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);                   
			serverSocket.send(sendPacket);                
			} 	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}