package socketstp1;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
public class Client {
Socket s;

public Client(int port,String host) {
	
	try
    { 	System.out.println("Le client cherche à se connecter au serveur " + host + "@"+port);
    	s = new Socket(host, port);
    	System.out.println("Le client s'est connecté sur serveur " + host + "@"+port);
     
    }
    catch(IOException e) { }
}

void requete(String msg) {
	 try{
	  	 	System.out.println("Le client cherche à récupérer le canal de communication ");
	  	 	PrintStream output=new PrintStream(s.getOutputStream());
	  	 	System.out.println("Le client cherche à envoyer la donnée au serveur " );
	  	 	output.println(msg);
	  	 }
	  	 catch(IOException e){
	  	 }
	
}
public void recevoir(){
 	 try{
 		System.out.println("Le client cherche à récupérer le canal de communication ");
 		BufferedReader entree=new BufferedReader(new InputStreamReader(s.getInputStream()));
       String text=entree.readLine();
     	System.out.println("j'ai recu "+text);
     }
 	 catch(IOException e){
 	 }
 }
public void fermer(){
	    try{ 
	    	System.out.println("Le client ferme la connexion au serveur ");
	    	s.close();
			}
		catch(IOException e){
	 }
}	
}
