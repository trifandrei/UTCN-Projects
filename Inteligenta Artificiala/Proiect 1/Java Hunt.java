package sbc12;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.PrintWriter;

import com.racersystems.jracer.RacerClient;

public class Main1 {

public static void main(String[] argv) {
    String ip = "127.0.0.1";
    int port = 8088;
   // String filename="\"/Applications/RacerPro 2.0 preview/examples/owl/people-pets.owl\"";
    String filename="\"C:/Users/trian/Desktop/Sbc/test.racer\"";
    
    RacerClient racer = new RacerClient(ip,port);
    try {
         racer.openConnection();
         racer.sendRaw("(racer-read-file " + filename + ")");
         
         String name="wolf";
         racer.sendRaw("(instance "+ name+ " animal)");
         
         name="moose";
         racer.sendRaw("(instance "+ name+ " animal)");
         name="elk";
         racer.sendRaw("(instance "+ name+ " animal)");
         
         name="desert_eagle";
         racer.sendRaw("(instance "+ name+ " weapon)");
         name="bow";
         racer.sendRaw("(instance "+ name+ " weapon)");
         name="ak47";
         racer.sendRaw("(instance "+ name+ " weapon)");
         name="caliber150mm";
         racer.sendRaw("(instance "+ name+ " caliber)");
         name="caliber88mm";
         racer.sendRaw("(instance "+ name+ " caliber)");
         name="caliber35mm";
         racer.sendRaw("(instance "+ name+ " caliber)");
         
   
         System.out.println(racer.sendRaw("(all-atomic-concepts)"+"\n"));
         System.out.println(racer.sendRaw("(concept-instances animal)"+"\n"));
         System.out.println(racer.sendRaw("(concept-instances caliber)"+"\n"));
         System.out.println(racer.sendRaw("(concept-instances weapon)"+"\n"));
         System.out.println(racer.sendRaw("(concept-instances terrain)"+"\n"));
         System.out.println(racer.sendRaw("(individual-fillers sniper has-caliber) "+"\n"));
         System.out.println(racer.sendRaw("(concept-instances foot-print)"+"\n"));
         System.out.println(racer.sendRaw("(concept-instances Country)"+"\n"));
         
         
         /*
         FileWriter fileWriter = new FileWriter("C:/Users/trian/Desktop/Sbc/test.racer", true); //Set true for append mode
         racer.out = new PrintWriter(fileWriter);
         racer.out.println("\n"+data); 
         racer.out.close();
*/
         racer.closeConnection();
    }
    catch (Exception e) {
        e.printStackTrace();
    }
}

}