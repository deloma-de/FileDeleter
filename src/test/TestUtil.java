package test;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import de.deloma.filedeleter.Ordner;
/**
 * @author Amirhossein Vatani 2023
 */
public class TestUtil {

	public static Ordner getOrdner(String path, Set<Ordner> ordSet) {
		
		Iterator<Ordner> iterator = ordSet.iterator();
		   
		while (iterator.hasNext()) {
		   Ordner o = iterator.next();

		   if(o.getPfad().equals(path)) {		   
		      return o;		        
		   }
		}
		
		return null;
	}
	
	public static void createSysFile(File myObj) {	    
	    try {
	        if (myObj.createNewFile()) {
	       
	        } else {
	          System.out.println("File already exists.");
	        }
	      } catch (IOException e) {
	        e.printStackTrace();
	      }
		
	}
	
	public static void createSysFolder(File myObj) {	    
	    try {
	    	myObj.mkdir();
	        if (myObj.createNewFile()) {
	       
	        } else {
	          System.out.println("File already exists.");
	        }
	      } catch (IOException e) {
	        e.printStackTrace();
	      }
		
	}
	
	public static void deleteSysFile(File myObj) {	    
		    if (myObj.delete()) { 
		    } else {
		      System.out.println("Failed to delete the file.");
		    } 
		
	}
	
    public static void copyFileContents(String sourceFileName, String destinationFileName) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(sourceFileName));
             BufferedWriter writer = new BufferedWriter(new FileWriter(destinationFileName))) {

            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(line);
                writer.newLine();
            }
        }
    }
}
