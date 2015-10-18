/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.*;
import java.net.*;
import net.rudp.*;
/**
 *
 * @author wei
 */
public class HelloServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        
        
        int port = 10341;
        String fileName = "./myImg.jpg"; // can include file full path
        System.out.println("Ready to receive the file!");
        receiveAndCreate(port, fileName);
                
        
    }
    
    public static void receiveAndCreate(int port, String fileName) throws IOException {
        ReliableServerSocket serverSocket = new ReliableServerSocket(port);
        Socket connectionSocket = serverSocket.accept();
        File file = new File(fileName);
        FileOutputStream outToFile = new FileOutputStream(file);
        InputStream inputStream = connectionSocket.getInputStream();
        OutputStream outputStream = new FileOutputStream(file);
        int read = 0;
        byte[] bytes = new byte[1024];
        while((read = inputStream.read(bytes))!=-1){
            outputStream.write(bytes, 0, read);
            
        }
        System.out.println("End");
        
        //serverSocket.close();
        //inputStream.close();

        //outputStream.close();
        
        
        
        System.out.println("End of receiving");
        
    }
    
}
