import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;


public class Node implements PeerSearchSimplified {
	int nodeID;
	String keyword;
	ArrayList<String> routingTable;
	ArrayList<String> urls;
	InetAddress ip;	
	public int port;
	UDPServer serverSocket;
	DatagramSocket clientSocket;
	/* Static port number because each node needs to have a different port unmber */
	static int portNumber = 8767;
	//final int bootStrapPort = 8767;
	
	
	public Node(String keyword){
		this.keyword = keyword; //Word
		nodeID = hashCode(keyword);//Hashed word
		port = portNumber;
		
		//All nodes using local host
		try {
			ip = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		urls = new ArrayList<String>();
	}
	
	public long joinNetwork(InetSocketAddress bootstrap_node, String identifier, String target_identifier){
		long result = 0;	
		try {
			clientSocket = new DatagramSocket();
		} catch (SocketException e1) {
			e1.printStackTrace();
		}
		String[] notNeeded= {"X"};
		Packet indexPacket = new Packet(1, hashCode("bootstrap"), nodeID, 
				nodeID, 0, "localhost", keyword,
			null, notNeeded, notNeeded, notNeeded);
		
		String dataBeingSent = indexPacket.sendingPacket();
		byte[] sendingBytes = dataBeingSent.getBytes();
		
		DatagramPacket joinNetWorkPacket = new DatagramPacket(sendingBytes, sendingBytes.length, ip, 8767);
		try {
			clientSocket.send(joinNetWorkPacket);
		} catch (IOException e) {
			e.printStackTrace();
		}
		clientSocket.close();
		return result; 
	}
	
	public boolean leaveNetwork(long network_id){
		try {
			clientSocket = new DatagramSocket();
		} catch (SocketException e1) {
			e1.printStackTrace();
		}
		boolean result = true;
		/*Creat  packet send it to all peers*/
		
		String[] notNeeded= {"X"};
		int ntNeeded = 0;
		Packet indexPacket = new Packet(4, hashCode("bootstrap"), hashCode(keyword), 
				nodeID, ntNeeded, "localhost", keyword,
				keyword, notNeeded, notNeeded, notNeeded);
		
		String dataBeingSent = indexPacket.sendingPacket();
		byte[] sendingBytes = dataBeingSent.getBytes();
		
		DatagramPacket leavePacket = new DatagramPacket(sendingBytes, sendingBytes.length, ip, 8767);
		try {
			clientSocket.send(leavePacket);
		} catch (IOException e) {
			e.printStackTrace();
		}
		clientSocket.close();
		return result;	
	}
	
	public int hashCode(String str) {
		int hash = 0;
		for (int i = 0; i < str.length(); i++) {
			hash = hash * 31 + str.charAt(i);
		}
		return Math.abs(hash);
	}	
	public void init(DatagramSocket udp_socket){
			clientSocket = udp_socket;
			
		Thread thread1;
		try {
			thread1 = new Thread(new RunnableThread("ServerThread", portNumber, this) );
			thread1.start();
		} catch (SocketException e) {
			e.printStackTrace();
		}
		portNumber++;
		//System.out.println("port = " + portNumber);
	}
	public void addUrl(String url){//testing reasons only
				urls.add(url);
				System.out.println("URL has been added to Node HashMap");
	}
	
	public void indexPage(String url, String[] unique_words){
		try {
			clientSocket = new DatagramSocket();
		} catch (SocketException e1) {
			e1.printStackTrace();
		}
		String[] urls= {url};
		String[] notNeeded= {"X"};
		
		Packet indexPacket = new Packet(5, hashCode(unique_words[1]), hashCode(keyword), nodeID, 0, "localhost", keyword,
				unique_words[1], urls, notNeeded, notNeeded);
		
		String dataBeingSent = indexPacket.sendingPacket();
		byte[] sendingBytes = dataBeingSent.getBytes();
		
		DatagramPacket indexP = new DatagramPacket(sendingBytes, sendingBytes.length, ip, 8767);
		try {
			clientSocket.send(indexP);
		} catch (IOException e) {
			e.printStackTrace();
		}
		clientSocket.close();

	}
	public SearchResult[] search(String[] words) {

	for(int i=0; i<words.length; i++){
		
		try {
			clientSocket = new DatagramSocket();
		} catch (SocketException e1) {
			e1.printStackTrace();
		}
		String[] notNeeded= {"  "};
		Packet searchPacket = new Packet(6, hashCode("bootstrap"), hashCode(keyword), 
				nodeID, 0, "localhost", words[i],
				keyword, notNeeded, notNeeded, notNeeded);

		String dataBeingSent = searchPacket.sendingPacket();
		byte[] sendingBytes = dataBeingSent.getBytes();

		DatagramPacket searchResultPacket = new DatagramPacket(sendingBytes, sendingBytes.length, ip, 8767);
		try {
			clientSocket.send(searchResultPacket);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		searchPacket = null;
		clientSocket.close();
	}//For loop
		SearchResult[] result= new SearchResult[1];
		return result;
	}	
}