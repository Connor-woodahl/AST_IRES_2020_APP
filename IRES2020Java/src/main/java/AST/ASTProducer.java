package AST;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import org.apache.commons.io.FileUtils;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.printer.YamlPrinter;

/*
* Author: Connor Woodahl
* Assignment Title: Evaluations Assignment BaylorIRES 2020
* Assignment Description: This program produces an abstract syntax tree from all found .java files
* in a given directory and all subdirectories
* Class: ASTProducer
* Due Date: 12/31/2019
* Date Created: 12/20/2019
* Date Last Modified: 12/30/2019
*/

public class ASTProducer {
	
	/* Main
	*
	* This is the driver that will implement the ASTProducer's intended description.
	*  parameters:
	*      args -- the command line arguments; an array of String values
	*  return value: N/A
	*/	
	
	// All commented out code was left in to show my methods of testing the AST 
	public static void main(String [] args) throws IOException {

		 // takes the path argument
		 File myDir = new File(args[0]);
		 
		 // gets the list of .java files to make AST's from
	     Collection<File> files = FileUtils.listFiles(myDir, new String[] {"java"}, true);
	     
	     // for each file in the collection 
	     for(File filer: files) {
	    	 
	    	 // create an ast file to hold the ast
			 File n = new File(filer.getAbsolutePath().concat(".ast"));
			 n.createNewFile();
			 
			 // Commenting out this code but can be used to see the text of the ast produced
			 // Will print it out to a file  with the extension .txt
			 
			 //			 File m = new File(filer.getAbsolutePath().concat(".txt"));
			 //			 m.createNewFile();
						 
			 // Get the contents of the file into a string
			 String read = readAllBytesJava7(filer.getAbsolutePath());
			 
			 // make the ast using javaparser stored in compUnit cu
			 CompilationUnit cu;
			 try {
				  cu = StaticJavaParser.parse(read);
				  
				  // creates a printer to put the ast into the newly created file
				  YamlPrinter printer = new YamlPrinter(true);
				 
				  // print the ast to the file
				  BufferedWriter writer = new BufferedWriter(new FileWriter(n));
				  writer.write(printer.output(cu));
				  writer.close();
				  
				  // this will be commented out but can show that the Compilation unit can reproduce 
				  // the code it read if I just print out the cu plainly
				  
				  //				  System.out.println(cu);
				  
				  // Commenting out this code but can be used to see the text of the ast produced
				  
				  //				  BufferedWriter writerTXT = new BufferedWriter(new FileWriter(m));
				  //				  writerTXT.write(printer.output(cu));
				  //				  writerTXT.close();
				  
			 }catch(Exception e) {
				 System.out.println(e.getMessage());
			 }
    	 }
	}
	// this function reads the file's code into a string to use for javaparser
	private static String readAllBytesJava7(String filePath) {
	    String content = "";
	    try{
	        content = new String(Files.readAllBytes(Paths.get(filePath)));
	    } 
	    catch (IOException e) {
	        e.printStackTrace();
	    }
	    return content;
	}
}