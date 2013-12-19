import com.google.gson.*;

public class Packet {

	//String nodeID;
	int type;
	int targetID;
	int senderID;
	int gatewayID;
	int nodeID;
	//ip left
	String keyword;
	String word;
	String[] urls;
	String[] search_response;
	String[] routing;
	
	
	public Packet(int type, int targetID, int senderID, int nodeID,
				  int gatewayID, String keyword, String word, 
				  String[] urls, String[] search_response, String[] routing ){
		this.type = type;
		this.targetID = targetID;
		this.senderID = senderID;
		this.nodeID = nodeID;
		this.gatewayID = gatewayID;
		this.keyword = keyword;
		this.word = word;
		this.urls = urls;
		this.search_response = search_response;
		this.routing = routing;
		
		switch(type){
		case 1:
			System.out.println("1 JOINING");
			/*
			 Type 
			 nodeID
			 targetID 
			*/
		case 2:
			System.out.println("2 JOINING_NETWORK_RELAY");
			/*
			 Type 
			 nodeID
			 targetID
			 gatewayID
			  
			*/
		case 3:
			System.out.println("3 ROUTING");
			/*
			 Type 
			 nodeID
			 targetID
			 gatewayID
			*/
		case 4:
			System.out.println("4 LEAVING");
			/*
			 Type 
			 nodeID
			*/
		case 5:
			System.out.println("5 INDEX");
			/*
			 Type 
			 targetID
			 senderID
			 keyword
			 urls
			*/
		case 6:
			System.out.println("6 SEARCH");
			/*
			 Type 
			 word
			 nodeID
			 senderID
			*/
		case 7:
			System.out.println("7 SEARCH_RESPONSE");
			/*
			 Type 
			 word
			 nodeID
			 senderID
			 sender_response
			*/
		case 8:
			System.out.println("8 PING");
			/*
			 Type 
			 targetID
			 senderID
			*/
		case 9:
			System.out.println("9 ACK");
			/*
			 Type 
			 nodeID
			*/
		case 10:
			System.out.println("10 ACK_INDEX");
			/*
			 Type 
			 nodeID
			 keyword
			*/
			
		default:
			
		}
	}
	
//	public void toJSON(String message){
//		Gson gson = new Gson();
//		gson.toJson(message);
//	}
//	public void fromJSON(int type){
//		switch(type){
//		case 0:
//			System.out.println("0 JOINING_NETWORK_SIMPLIFIED");
//		case 1:
//			System.out.println("1 JOINING_NETWORK_RELAY_SIMPLIFIED");
//		case 2:
//			System.out.println("2 ROUTING_INFO");
//		case 3:
//			System.out.println("3 LEAVING_NETWORK");
//			
//		default:
//			
//		}
//		
//		
//	}

}
