import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;


public class TCPServer {
        /*
	public static void main(String[] args) {
		TCPServer nioServer = new TCPServer();
		//long startTime = System.nanoTime();
		SocketChannel socketChannel = nioServer.createServerSocketChannel();
		nioServer.readFileFromSocket(socketChannel);
		//long estimatedTime = System.nanoTime() - startTime;
		//System.out.println("Time Taken: " + estimatedTime);
	}
        */
	public SocketChannel createServerSocketChannel() {

		ServerSocketChannel serverSocketChannel = null;
		SocketChannel socketChannel = null;
		try {
			serverSocketChannel = ServerSocketChannel.open();
			serverSocketChannel.socket().bind(new InetSocketAddress(10343));
			socketChannel = serverSocketChannel.accept();
			System.out.println("Connection established...." + socketChannel.getRemoteAddress());

		} catch (IOException e) {
			e.printStackTrace();
		}

		return socketChannel;
	}

	/**
	* Reads the bytes from socket and writes to file
	*
	* @param socketChannel
	*/
	public void readFileFromSocket(SocketChannel socketChannel) {
		RandomAccessFile aFile = null;
                FileChannel fileChannel = null;
                
		try {
			aFile = new RandomAccessFile("./myImg_Action2.jpg", "rw");
			ByteBuffer buffer = ByteBuffer.allocate(1024);
			fileChannel = aFile.getChannel();
			while (socketChannel.read(buffer) > 0) {
				buffer.flip();
				fileChannel.write(buffer);
				buffer.clear();
			}
			Thread.sleep(1000);
			fileChannel.close();
			System.out.println("End of file reached..Closing channel");
			socketChannel.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 

	}
}