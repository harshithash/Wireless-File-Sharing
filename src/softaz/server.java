
package softaz;


import java.io.FileOutputStream;  
import java.io.ObjectInputStream;  
import java.io.ObjectOutputStream;  
import java.net.ServerSocket;  
import java.net.Socket;  
import javax.swing.JProgressBar;
  
public class server  {  
   // public static final int PORT = 3332; 
    int port;
   // JProgressBar progress;
    public static final int BUFFER_SIZE = 10000000;  

    server(int port) {
        this.port=port;
       // this.progress=progress;
        //throw new UnsupportedOperationException("Not supported yet.");  
    }
  
    public void run() {  
       // while(true)
       // {
        try { 
            
            ServerSocket serverSocket = new ServerSocket(port);  
      System.out.println("hello");
          
            while (true) {  
                Socket s = serverSocket.accept();  
                saveFile(s);  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
   // }  
    }
    public void saveFile(Socket socket) throws Exception {  
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());  
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());  
        FileOutputStream fos = null;  
        byte [] buffer = new byte[BUFFER_SIZE];  
  
        // 1. Read file name.  
        Object o = ois.readObject();  
        String name;
  
        if (o instanceof String) {  
            fos = new FileOutputStream(o.toString());  
            name= o.toString();
            
        } else {  
            throwException("Something is wrong");  
        }  
        
        
        // 2. Read file to the end.  
        Integer bytesRead = 0,current=0;  
      
  
        do {  
            o = ois.readObject();  
  
            if (!(o instanceof Integer)) {  
                throwException("Something is wrong");  
            }  
  
            bytesRead = (Integer)o;  
            System.out.println("read:"+bytesRead);
            //current+=bytesRead;
          //  progress.setValue(current);
  
            o = ois.readObject();  
  
            if (!(o instanceof byte[])) {  
                throwException("Something is wrong");  
            }  
  
            buffer = (byte[])o;  
  
            // 3. Write data to output file.  
            fos.write(buffer, 0, bytesRead);  
            
        } while (bytesRead == BUFFER_SIZE);  
          
        System.out.println("File transfer success");  
          
        fos.close();  
  
        ois.close();  
        oos.close();  
    }  
  
    //public static void throwException(String message) throws Exception {  
      //  throw new Exception(message);  
//    }  

    private void throwException(String something_is_wrong) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
    

