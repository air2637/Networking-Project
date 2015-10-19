/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author wei
 */
import java.io.*;
import java.net.*;
import net.rudp.*;

public class HelloClient {
    public static void main(String[] args) throws Exception {
    
        String host = "127.0.0.1";
        int port = 10341;
        System.out.println("Ready to send the file!");
        String fileName = "/Users/weizheng/Desktop/NetworkingPrj/Networking/UDP-file-transfer/123.jpg";
        long initialTime = System.nanoTime();
        createAndSend(host, port, fileName);
        long totalTime = System.nanoTime()- initialTime;
        System.out.println(totalTime);
    }
    
    public static void createAndSend(String host, int port, String fileName) throws IOException{
        File file = new File(fileName);
        InputStream inFromFile = new FileInputStream(file);
       // ByteArrayOutputStream bos = new ByteArrayOutputStream();
        
        
        
        ReliableSocket clientSocket = new ReliableSocket(host, port);
        
        ReliableSocketOutputStream outputStream = (ReliableSocketOutputStream) clientSocket.getOutputStream();
        
        byte[] buf = new byte[1024];
        for(int readNum; (readNum=inFromFile.read(buf))!=-1;){
            outputStream.write(buf,0,readNum);
        }
       
        //clientSocket.close();
        //System.out.println("End of sending");
    }
}
