package tp2;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

import javax.imageio.ImageIO;

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
	  
	  public void Screen(){
		  try {
		        Robot robot = new Robot();

		        // Capture screen from the top left in 200 by 200 pixel size.
		        BufferedImage bufferedImage = robot.createScreenCapture(
		                new Rectangle(new Dimension(1400, 740)));

		        // The captured image will the written into a file called
		        // screenshot.png
		        File imageFile = new File("C:\\Users\\hp\\Downloads\\screenshot.png");
		        ImageIO.write(bufferedImage, "png",  imageFile);
		        envoiFileByte("C:\\Users\\hp\\Downloads\\screenshot.png");
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		  }
	  public void envoiFileByte(String path){
		  	 try{
        FileInputStream fs=new FileInputStream(path);
        byte b[]=new byte[1000000];
        fs.read(b,0,b.length);
         PrintStream pr=new PrintStream(sock.getOutputStream());
         pr.write(b, 0, b.length);
          }
		  	 catch(IOException e){
		  	 }
		      
		  }
public void envoiFile(String path) throws IOException{
	String data;
	String s = "";
		  try {	
			  PrintStream output=new PrintStream(sock.getOutputStream());

		      File myObj = new File(path);
		      Scanner myReader = new Scanner(myObj);
		      while (myReader.hasNextLine()) {
		      data = myReader.nextLine();
		  	 	output.println(data);

		      }
		      myReader.close();
		    } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
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

	  public static void main(String[] args) throws IOException
	  { 
	  ClientConnecte client=new ClientConnecte();
	  client.connexion("127.0.0.1", 10000);
	  client.envoiFileByte("C:\\Users\\hp\\Downloads\\Conception.pdf");
//client.Screen();
//	  client.recevoir();
	  client.fermer();
	  }
}