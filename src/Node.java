
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;


public class Node implements PeerSearchSimplified {
	int nodeID;
	String keyword;
	ArrayList<String> routingTable;
	ArrayList<String> urls;
	InetAddress ip;
	//UDPServer serverSocket;
	//UDPClient clientSocket;
	
	UDPServer serverSocket;
	DatagramSocket clientSocket;
	/* Static port number because each node needs to have a different port unmber */
	static int portNumber = 8767;
	
	
	public Node(String keyword){
		this.keyword = keyword; //Word
		nodeID = hashCode(keyword);//Hashed word
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
		return result; 
	}
	
	public boolean leaveNetwork(long network_id){
		boolean result = true;
		/*Creat JSON packet send it to all peers*/
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
		//listeningserverSocket = udp_socket;
			
		Thread thread1;
		try {
			thread1 = new Thread(new RunnableThread("ServerThread", portNumber, this) );
			thread1.start();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		System.out.println("Hi Luke");
		portNumber = portNumber+1;
	
		
	
	}
	public void addUrl(String url){
				urls.add(url);
				System.out.println("URL has been added to Node HashMap");
	}
	public void indexPage(String url, String[] unique_words){
		
		for(int i=0; i<unique_words.length; i++){
			/* If the unique word == node keyword then url is added to urls list */
			if(keyword.equalsIgnoreCase(unique_words[i])){
				urls.add(url);
				System.out.println("URL has been added to Node HashMap");
			}

		}
	}
	public SearchResult[] search(String[] words) {
		/*
		 * 	  String words; // strings matched for this url
   			  String[] url;   // url matching search query 
   			  long frequency;
		 
		  */
		String wordSearch = words[0];
		String[] urlSearch = new String[urls.size()];
		long freqSearch =0;
		
		for (int i = 0; i < urls.size(); i++) {
			System.out.println("" + urls.get(i));
			urlSearch[i] = urls.get(i);
		}
		SearchResult[] result= new SearchResult[1];
		
		return result;
	}	
	
}
