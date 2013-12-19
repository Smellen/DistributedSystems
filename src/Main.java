import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;
public class Main {
	/**
	 * @param args
	 * @throws SocketException 
	 */
	public static void main(String[] args) throws SocketException {
		InetSocketAddress ellen = new InetSocketAddress("", 0);
		
		String[] testArray = new String[3];
		testArray[0] = "cat";
		testArray[1] = "dog";
		testArray[2] = "xbox";
		
		Node bootstrap = new Node("bootstrap");
		bootstrap.init(new DatagramSocket());
		
		Node cat = new Node("cat");
		cat.init(new DatagramSocket());
		int catport = cat.port;
		System.out.println("cat port = "+ catport);
		
		Node dog = new Node("dog");
		dog.init(new DatagramSocket());
		int dogport = dog.port;
		System.out.println("dog port = "+ dogport);
		
		Node xbox = new Node("xbox");
		xbox.init(new DatagramSocket());
		int xboxport = xbox.port;
		System.out.println("xbox port = "+ xboxport);
		
		cat.indexPage("www.faggotville.com", testArray);
		cat.joinNetwork(ellen, cat.keyword, "bootstrap");
		cat.search(testArray);
		cat.leaveNetwork(cat.hashCode(cat.keyword));
	}
}