package tp2;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Path;
import java.util.Arrays;

class ServeurConnecte extends Thread
{ ServerSocket serveur;
  static final int port=10000;

  public ServeurConnecte()
  { try
    { serveur=new ServerSocket(port);
    System.out.println("Le serveur est en écoute sur le "+port);
	this.start();
    }
    catch(IOException e) { System.exit(1); }
    
  }

  public void run()
  { 
Socket sock;
String text;
    try
    { while(true)
      { 
      	System.out.println("Le serveur est en attente ");
      	sock=serveur.accept();
        System.out.println("Le serveur a accepté la connexion avec "+sock.getInetAddress());
      //t=new Traitement(sock);
//        BufferedReader entree= new BufferedReader(new InputStreamReader(sock.getInputStream()));
//        text=entree.readLine();
//        System.out.println(text);
//        System.out.println("Le client cherche à récupérer le canal de communication ");
//  	 	PrintStream sortie=new PrintStream(sock.getOutputStream());
//  	 	System.out.println("Le client charche à envoyer la donnée au serveur " );
//  	 	sortie.println(text+" a ete recu par le serveur");
//		sock.close();
//   
        receiveFile(sock);

      }
    }
    catch(IOException e) { }
  }
  public void receiveFile(Socket sock) {
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
	   System.out.println("Recieved from client : "+res); 
	   
	   //echoing back to client
	   
		out.write(result);
	
	   System.out.println("Closing connection"); 

	   // close connection 
	  
		sock.close();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
	  
  }
}
