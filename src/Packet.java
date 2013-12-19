public class Packet {
	
	public String sendingPacket;

	//String nodeID;
	int type;
	int targetID;
	int senderID;
	int gatewayID;
	int nodeID;
	String ip;
	String keyword;
	String word;
	String[] urls;
	String[] search_response;
	String[] routing;
	
	public Packet(int type, int targetID, int senderID, int nodeID,
				  int gatewayID, String ip, String keyword, String word, 
				  String[] urls, String[] search_response, String[] routing ){
		this.type = type;
		this.targetID = targetID;
		this.senderID = senderID;
		this.nodeID = nodeID;
		this.gatewayID = gatewayID;
		this.ip = ip;
		this.keyword = keyword;
		this.word = word;
		this.urls = urls;
		this.search_response = search_response;
		this.routing = routing;
		
//		sendingPacket = type + ","
//						+ targetID + ","
//						+ senderID + ","
//						+ nodeID + ","
//						+ gatewayID + ","
//						+ ip + ","
//						+ keyword + ","
//						+word;
		
		switch(type){
		case 1:
			System.out.println("Type 1 JOINING");
			/*
			 Type 
			 nodeID
			 targetID 
			 ip
			*/
			sendingPacket = type + ","
					+ targetID + ","
					+ senderID + ","
					+ nodeID + ","
					+ gatewayID + ","
					+ ip + ","
					+ keyword + ","
					+word;
			break;
			
		case 2:
			System.out.println("Type 2 JOINING_NETWORK_RELAY");
			/*
			 Type 
			 nodeID
			 targetID
			 gatewayID  
			*/
			sendingPacket = type + ","
					+ targetID + ","
					+ senderID + ","
					+ nodeID + ","
					+ gatewayID + ","
					+ ip + ","
					+ keyword + ","
					+word;
			break;
			
		case 3:
			System.out.println("Type 3 ROUTING");
			/*
			 Type 
			 nodeID
			 targetID
			 gatewayID
			*/
			sendingPacket = type + ","
					+ targetID + ","
					+ senderID + ","
					+ nodeID + ","
					+ gatewayID + ","
					+ ip + ","
					+ keyword + ","
					+word;//+routing;
			for(int i=0; i<routing.length; i++){
				sendingPacket = sendingPacket +"," + routing[i];	
			}
			break;
			
		case 4:
			System.out.println("Type 4 LEAVING");
			/*
			 Type 
			 nodeID
			*/
			sendingPacket = type + ","
					+ targetID + ","
					+ senderID + ","
					+ nodeID + ","
					+ gatewayID + ","
					+ ip + ","
					+ keyword + ","
					+word;
			break;
			
		case 5:
			System.out.println("Type 5 INDEX");
			/*
			 Type 
			 targetID
			 senderID
			 keyword
			 urls
			*/
			sendingPacket = type + ","
					+ targetID + ","
					+ senderID + ","
					+ nodeID + ","
					+ gatewayID + ","
					+ ip + ","
					+ keyword + ","
					+word;//+urls;
			for(int i=0; i<routing.length; i++){
				sendingPacket = sendingPacket + "," +urls[i];	
			}
			break;
			
		case 6:
			System.out.println("Type 6 SEARCH");
			/*
			 Type 
			 word
			 nodeID
			 senderID
			*/
			sendingPacket = type + ","
					+ targetID + ","
					+ senderID + ","
					+ nodeID + ","
					+ gatewayID + ","
					+ ip + ","
					+ keyword + ","
					+word;
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
			sendingPacket = type + ","
					+ targetID + ","
					+ senderID + ","
					+ nodeID + ","
					+ gatewayID + ","
					+ ip + ","
					+ keyword + ","
					+word;//+search_response;
			for(int i=0; i<routing.length; i++){
				sendingPacket = sendingPacket +"," + search_response[i] ;	
			}
			break;
			
		case 8:
			System.out.println("Type 8 PING");
			/*
			 Type 
			 targetID
			 senderID
			*/
			sendingPacket = type + ","
					+ targetID + ","
					+ senderID + ","
					+ nodeID + ","
					+ gatewayID + ","
					+ ip + ","
					+ keyword + ","
					+word;
			break;
			
		case 9:
			System.out.println("Type 9 ACK");
			/*
			 Type 
			 nodeID
			*/
			sendingPacket = type + ","
					+ targetID + ","
					+ senderID + ","
					+ nodeID + ","
					+ gatewayID + ","
					+ ip + ","
					+ keyword + ","
					+word;
			break;
			
		case 10:
			System.out.println("Type 10 ACK_INDEX");
			/*
			 Type 
			 nodeID
			 keyword
			*/
			sendingPacket = type + ","
					+ targetID + ","
					+ senderID + ","
					+ nodeID + ","
					+ gatewayID + ","
					+ ip + ","
					+ keyword + ","
					+word;
			break;
			
		default:
			System.out.println("No such packet type");
			break;
		}
	}
	public String sendingPacket(){
		return sendingPacket;		
	}
}