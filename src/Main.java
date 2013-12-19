import java.net.DatagramSocket;
import java.net.SocketException;


public class Main {

	/**
	 * @param args
	 * @throws SocketException 
	 */
	public static void main(String[] args) throws SocketException {
		// TODO Auto-generated method stub

		Node cat = new Node("cat");
		Node dog = new Node("dog");
		Node xbox = new Node("xbox");
		
		cat.init(new DatagramSocket());
		dog.init(new DatagramSocket());
		xbox.init(new DatagramSocket());
		
		String test = "www.catsrus.com";
		String[] testArray = new String[2];
		testArray[0] = "cat";
		testArray[1] = "dog";
		testArray[2] = "dog";

//		cat.indexPage(test, testArray);
//		cat.search(testArray);
	}

}
