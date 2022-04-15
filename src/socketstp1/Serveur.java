package socketstp1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Serveur {

    ServerSocket ss;

	public Serveur(int port) {
		
		try {
			this.ss = new ServerSocket(port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
    public void servir() {
    	Socket sock;
    	   String text;
    	System.out.println("Le serveur est en attente ");
    	try {
      	sock=ss.accept();
        System.out.println("Le serveur a accepté la connexion avec "+sock.getInetAddress());
      //t=new Traitement(sock);
        BufferedReader entree= new BufferedReader(new InputStreamReader(sock.getInputStream()));
        text=entree.readLine();
        System.out.println(text);
        System.out.println("Le client cherche à récupérer le canal de communication ");
  	 	PrintStream sortie=new PrintStream(sock.getOutputStream());
  	 	System.out.println("Le client charche à envoyer la donnée au serveur " );
  	 	sortie.println(text+" a ete recu par le serveur");
		sock.close();}
    	catch(IOException e) {
			e.printStackTrace();

    		
    		
    	}
    	
//    	Socket cl;
//		try {
//			System.out.println("Waiting ........");
//
//			cl = ss.accept();
//			System.out.println("Accept connexion");
//
//    	InputStream in = cl.getInputStream();
//	    BufferedReader d=new BufferedReader(new InputStreamReader(in));
//        String ch=d.readLine();
//        System.out.println("le data recu: ");
//
//        System.out.println("le data recu: "+ch);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
    	
    }


}
