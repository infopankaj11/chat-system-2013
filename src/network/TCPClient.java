package network;
//
//import java.io.BufferedInputStream;
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.io.ObjectInputStream;
//import java.io.ObjectOutputStream;
//import java.io.OutputStream;
//import java.io.PrintWriter;
//import java.net.InetAddress;
//import java.net.Socket;
//import java.net.UnknownHostException;
//
//public class TCPClient extends Thread{
//	

//	
//	public TCPClient(InetAddress addressServer,int portServer,File fileToSend){
//		this.portServer=portServer;
//		this.fileToSend=fileToSend;
//		try {
//			socketClient=new Socket(addressServer,portServer);
//		} catch (IOException e) {
//			System.out.println("Can not create socketClient!!");
//		}
//		this.start();
//	}
//	
//	public void run(){
//        byte[] buffer = new byte[8100];
//		FileInputStream fis = null;
//			try {
//				fis = new FileInputStream(fileToSend);
//			} catch (FileNotFoundException e) {
//				System.out.println("Cannot find the file");
//			}
//	        BufferedInputStream bis = new BufferedInputStream(fis);
//	        OutputStream os = null;
//				try {
//					os = socketClient.getOutputStream();
//				} catch (IOException e) {
//					System.out.println("I/O problem");
//				}		
//				try {
//					while (bis.read(buffer, 0, buffer.length) > 0){
//						os.write(buffer, 0, bis.read(buffer, 0, buffer.length));
//					    os.flush();
//					}
//				} catch (IOException e) {
//					System.out.println("I/O problem");
//				}
//		try {
//			socketClient.close();
//		} catch (IOException e) {
//			System.out.println("Can not close socketClient!");
//		}
//	}
//}


import java.io.DataInputStream;  
import java.io.DataOutputStream;  
import java.io.File;  
import java.io.FileInputStream;  
import java.io.FileNotFoundException;
import java.io.IOException;  
import java.net.InetAddress;
import java.net.Socket;  
import java.net.UnknownHostException;  
  
public class TCPClient extends Thread {  
  
    private Socket socket = null;  
    private String path = null;    
	private static int portServer;
	private Socket socketClient=null;
	private File fileToSend;
	
	public TCPClient(InetAddress addressServer,int portServer,File fileToSend){
		this.portServer=portServer;
		this.fileToSend=fileToSend;
		try {
			socketClient=new Socket(addressServer,portServer);
		} catch (IOException e) {
			System.out.println("Can not create socketClient!!");
	}
}
    
    private DataOutputStream getDataOutputStream() throws IOException {  
  
        return new DataOutputStream(socket.getOutputStream());  
  
    }  
  
    private DataInputStream getDataInputStream() throws IOException {  
  
        return new DataInputStream(socket.getInputStream());  
  
    }  
  
    private String getPath() {  
        return path;  
    }  
  
    public void setPath(String path) {  
        this.path = path;  
    }  
  
    public void run() {  
  
        try {  
  
            DataInputStream read = null;
			try {
				read = new DataInputStream(new FileInputStream(  
				        new File(getPath())));
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}  
  
            System.out.println(read.available());  
  
            DataOutputStream os = getDataOutputStream();  
  
            DataInputStream in = getDataInputStream();  
  
            String fileName = path.substring(path.lastIndexOf("//") + 1);
  
            System.out.println(fileName);  
  
            os.write((fileName + ";" + read.available()).getBytes("utf-8"));
  
            byte[] data = new byte[1024];  
  
            int len = in.read(data);  
  
            String start = new String(data, 0, len);  
  
            int sendCountLen = 0;  
  
//            if (start.equals("start")) {  
//  
//                new Thread(bar).start();
//  
//                while ((len = read.read(data)) != -1) {  
//  
//                    os.write(data, 0, len);  
//  
//                    sendCountLen += len;  
//  
//                    bar.setSendSize(sendCountLen);  
//  
//                }  
  
                try {
					os.flush();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}  
  
                try {
					os.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}  
  
                try {
					read.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}  
  
//            }  
//  
//        } catch (IOException e) {  
//  
//            e.printStackTrace();  
        } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {  
  
            try {  
                socket.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
  
        }  
  
    }  
}  