import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

public class FileSender {
	//public static	long starttime = 0;
	public static void main(String[] args) {
                long initialTime = System.nanoTime();
		FileSender nioClient = new FileSender();
		//starttime = System.nanoTime();
		SocketChannel socketChannel = nioClient.createChannel();
		nioClient.sendFile(socketChannel);
                long totalTime = System.nanoTime()- initialTime;
                System.out.println(totalTime);
	}

	/**
	* Establishes a socket channel connection
	*
	* @return
	*/
	public SocketChannel createChannel() {

		SocketChannel socketChannel = null;

		try {
			socketChannel = SocketChannel.open();
			SocketAddress socketAddress = new InetSocketAddress("127.0.0.1", 10343); //receiver's IP address
			socketChannel.connect(socketAddress);
			//System.out.println("Connected..Now sending the file");

		} catch (IOException e) {
			e.printStackTrace();
		}
		return socketChannel;
	}


	public void sendFile(SocketChannel socketChannel) {
		RandomAccessFile aFile = null;
		try {
			File file = new File("/Users/weizheng/Desktop/NetworkingPrj/Networking/UDP-file-transfer/123.jpg");
			aFile = new RandomAccessFile(file, "r");
			FileChannel inChannel = aFile.getChannel();
			ByteBuffer buffer = ByteBuffer.allocate(1024);



			while (inChannel.read(buffer) > 0) {
				buffer.flip();
				socketChannel.write(buffer);
				buffer.clear();
			}
			//long totalTime = System.nanoTime() - starttime;
			//System.out.println("Time Taken: " + totalTime);
			Thread.sleep(1000);
			//System.out.println("End of file reached..");
			socketChannel.close();
			aFile.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}