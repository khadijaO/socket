package tp2;

import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.*;

class ServeurConnecte extends Thread
{ ServerSocket serveur;
  static final int port=10000;

  public ServeurConnecte()
  { try
    { serveur=new ServerSocket(port);
    System.out.println("-----------Le serveur est en écoute sur le "+port);
	this.start();
    }
    catch(IOException e) { System.exit(1); }
    
  }

  public void run()
  { Socket sock;
   // Traitement t;
   String text;

    try
    { while(true)
      { 
      	System.out.println("-----------Le serveur est en attente ");
      	sock=serveur.accept();
        System.out.println("-----------Le serveur a accepté la connexion avec "+sock.getInetAddress());
     
      this.receiveByteFile(sock);

      }
    }
    catch(IOException e) { }
  }

  public void receive(Socket sock) {
//	   t=new Traitement(sock);
	  String text;
    BufferedReader entree;
	try {
		entree = new BufferedReader(new InputStreamReader(sock.getInputStream()));
		List<String> data = new ArrayList<>();
        for (String line; (line = entree.readLine()) != null;) {
            data.add(line);
        }
      CreateFile("C:\\Users\\hp\\Downloads\\testReceived.txt", data);
      System.out.println("-----------Le client cherche à récupérer le canal de communication  ");
	 	PrintStream sortie=new PrintStream(sock.getOutputStream());
	   sock.close();
	} catch (IOException e) {
		e.printStackTrace();
	}
   
  }
  public void receiveByteFile(Socket sock) {
	   InputStream in ; 
	   OutputStream out ;
	// takes input from the client socket 
	   try {
		in = new DataInputStream(sock.getInputStream());
	//writes on client socket
	   out = new DataOutputStream(sock.getOutputStream());

	   // Receiving data from client
	   ByteArrayOutputStream baos = new ByteArrayOutputStream();
	   byte buffer[] = new byte[1024];
	  
		baos.write(buffer, 0 , in.read(buffer));
	
	   
	   byte result[] = baos.toByteArray();
	   FileOutputStream fs = new FileOutputStream(new File("C:\\Users\\hp\\Downloads\\received.png"));
	   BufferedOutputStream  bs = new BufferedOutputStream(fs);
	    bs.write(buffer);
	    bs.close();
	    bs=null;
	   String res = Arrays.toString(result);
	  
	   
		out.write(result);
	
	   System.out.println("------------Closing connection"); 
	  
		sock.close();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
	  
}
  
     public void CreateFile(String path, List<String> data) {
	  
	    File myObj = new File(path);
	    try {
	        FileWriter myWriter = new FileWriter(path);
	        for  (int i=0;i<data.size();i++) {
	             myWriter.write(data.get(i)+"\r\n");
	        }
	        System.out.println("Votre fichier est recu avec succes , check it in "+path);
	        myWriter.close();
	      } catch (IOException e) {
	        System.out.println("An error occurred.");
	        e.printStackTrace();
	      }
	  
	}
}
