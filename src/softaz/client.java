/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package softaz;

import java.io.File;  
import java.io.FileInputStream;  
import java.io.IOException;
import java.io.ObjectInputStream;  
import java.io.ObjectOutputStream;  
import java.net.Socket;  
import java.util.Arrays;  
import java.lang.*;  
import java.util.Scanner;  
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JProgressBar;
//import static sun.net.www.protocol.http.AuthCacheValue.Type.Server;

 
public class client {  
    int port;
    String ip;
    String path;
    File file;
    int curr;
  // JProgressBar progress;
    
    public client(int port,String ip,String path)
    {
        curr=0;
        this.port=port;
        this.ip=ip;
        this.path=path;
    //   this.progress=progress;
    }
    public void connectx() throws IOException {  
        //File file;  
       String file_name=path;
        file = new File(file_name);  
        Socket socket = null;  
       // System.out.println("hello");
        try {
            socket = new Socket(ip, port);
        } catch (IOException ex) {
            Logger.getLogger(client.class.getName()).log(Level.SEVERE, null, ex);
        }
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());  
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());  
  
        oos.writeObject(file.getName());         
      //  oos.writeObject(file.getSize());
        //Thread T = new Progbar(this , this.progress);
         //T.start();
      
          FileInputStream fis = new FileInputStream(file);  
        byte [] buffer = new byte[100000000];  
       Integer bytesread = 0;
  
        while ((bytesread = fis.read(buffer)) > 0) { 
            curr+=bytesread;
            oos.writeObject(bytesread);  
            oos.writeObject(Arrays.copyOf(buffer, buffer.length));  
        }  
  
        oos.close();  
        ois.close();  
        System.exit(0);      
}  

   public int gettotal() {
        //throw new UnsupportedOperationException("Not supported yet.");
        int total=0;
      total= (int) file.length();
      return total;
      
    }

   public int getdata() {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    return curr;
    }
  
}  
  
    
