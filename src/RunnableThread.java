import java.net.SocketException;

class RunnableThread implements Runnable {

	Thread runner;
	UDPServer serverSocket;
	Node yeah;
	public RunnableThread() {
	}
	public RunnableThread(String threadName, int portNumber, Node yeah) throws SocketException {
		this.yeah = yeah;
		 serverSocket = new UDPServer(portNumber);
		runner = new Thread(this, threadName); // (1) Create a new thread.
		System.out.println(runner.getName());
		runner.start(); // (2) Start the thread.
	}

	public void run() {
	System.out.println(Thread.currentThread());
		
		try {
			serverSocket.server();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}