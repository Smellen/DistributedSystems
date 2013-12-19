import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

class RunnableThread implements Runnable {

	Thread runner;
	DatagramSocket serverSocket;
	Node yeah;
	public RunnableThread(String threadName, int portNumber, Node yeah) throws SocketException {
		this.yeah = yeah;
		serverSocket = new DatagramSocket(portNumber);
		runner = new Thread(this, threadName); // (1) Create a new thread.
		//System.out.println(runner.getName());
		runner.start(); // (2) Start the thread.
	}

	public void run() {
	//System.out.println(Thread.currentThread());
	
		
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
//			for(int i=0; i<packetData.length; i++){
//				System.out.println("PacketData["+i+"] = "+ packetData[i]);
//			}
			int type = Integer.parseInt(packetData[0]);
			//String newLine = System.getProperty("line.separator");
			System.out.println("Node Keyword = " + yeah.nodeID + "  Packet Keyword = " + packetData[1]);
			if(yeah.nodeID == Integer.parseInt(packetData[1])){
				System.out.println("Keywords are equal");
				switch(type){
				case 1:
					System.out.println("Type 1 JOINING");
	
					/*
					 Type 
					 nodeID
					 targetID 
					 ip
					*/
				
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
					System.out.println("Type 3 ROUTING");
					/*
					 Type 
					 nodeID
					 targetID
					 gatewayID
					*/
					
					break;
					
				case 4:
					System.out.println("Type 4 LEAVING");
					
					
					/*
					 Type 
					 nodeID
					*/
					System.out.println("Node " + packetData[3] +" has left the network");
					
					break;
					
				case 5:
					System.out.println("Type 5 INDEX");
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
					
					
					
					break;
					
				case 6:
					System.out.println("Type 6 SEARCH");
					/*
					 Type 
					 word
					 nodeID
					 senderID
					*/
					
					break;
					
				case 7:
					System.out.println("Type 7 SEARCH_RESPONSE");
					/*
					 Type 
					 word
					 nodeID
					 senderID
					 sender_response
					*/
					
					break;
					
				case 8:
					System.out.println("Type 8 PING");
					/*
					 Type 
					 targetID
					 senderID
					*/
					
					break;
					
				case 9:
					System.out.println("Type 9 ACK");
					/*
					 Type 
					 nodeID
					*/
					
					break;
					
				case 10:
					System.out.println("Type 10 ACK_INDEX");
					/*
					 Type 
					 nodeID
					 keyword
					*/
					
					break;
					
				default:
					System.out.println("No such packet type");
					break;
				}
			}
				
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