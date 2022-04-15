package tp2;

import java.io.*;
import java.net.*;

class Traitement extends Thread
{ Socket sock;
  BufferedReader entree;

  public Traitement(Socket socket)
  { sock=socket;
    try
    { entree=new BufferedReader(new InputStreamReader(sock.getInputStream()));
    }
    catch(IOException e) { }
    this.start();
  }

  public void run()
  { String text;
    try
    { text=entree.readLine();
      System.out.println(text);
      sock.close();
    }
    catch(IOException e) { }
  }
}