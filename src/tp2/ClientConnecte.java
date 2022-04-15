package tp2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class ClientConnecte {

Socket sock=null;
	  public void connexion(String host, int port) 
	  { try
	    { 	System.out.println("Le client cherche à se connecter au serveur " + host + "@"+port);
	    	sock = new Socket(host, port);
	    	System.out.println("Le client s'est connecté sur serveur " + host + "@"+port);
	     
	    }
	    catch(IOException e) { }
	  }
	  public void envoi(String data){
	  	 try{
	  	 	System.out.println("Le client cherche à récupérer le canal de communication ");
	  	 	PrintStream output=new PrintStream(sock.getOutputStream());
	  	 	System.out.println("Le client cherche à envoyer la donnée au serveur " );
	  	 	output.println(data);
	  	 }
	  	 catch(IOException e){
	  	 }
	      
	  }
	  
	  public void envoiFile(String path){
		  	 try{
FileInputStream fs=new FileInputStream(path);
byte b[]=new byte[2002];
fs.read(b,0,b.length);
PrintStream pr=new PrintStream(sock.getOutputStream());
pr.write(b, 0, b.length);
}
		  	 catch(IOException e){
		  	 }
		      
		  }
	  public void recevoir(){
	  	 try{
	  		System.out.println("Le client cherche à récupérer le canal de communication ");
	  		BufferedReader entree=new BufferedReader(new InputStreamReader(sock.getInputStream()));
	        String text=entree.readLine();
	      	System.out.println(text);
	      }
	  	 catch(IOException e){
	  	 }
	  }
	  public void fermer(){
	  	    try{ 
	  	    	System.out.println("Le client ferme la connexion au serveur ");
	  	    	sock.close();
	  			}
	  		catch(IOException e){
	  	 }
	  }	

	  public static void main(String[] args)
	  { 
	  ClientConnecte client=new ClientConnecte();
	  client.connexion("127.0.0.1", 10000);
	  client.envoi("C:\\Users\\hp\\Downloads\\Sans titre.png");
	  client.recevoir();
	  client.fermer();
	  }
}