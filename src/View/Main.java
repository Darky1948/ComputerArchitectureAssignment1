/**
 * 
 */
package View;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import Controller.Encoder;
import Controller.Reader;
import Controller.Writter;
import Model.Encoding;

/**
 * @author kviguier
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if(args != null && args[0] != null) {
			
			Reader reader = new Reader(args[0]);
			try {
				Encoding encoding = reader.parseFile();
				
				Encoder encoder = new Encoder();
				
				ArrayList<String> encodedInstructions = encoder.getEncodedInstruction(encoding);
				ArrayList<String> encodedAddresses = encoder.getEncodedAddresses();
				
				Writter writter = new Writter();
				
				writter.writeSmallFile(encodedInstructions);
				writter.writeFullFile(encodedInstructions, encoding, encodedAddresses);
				
			} catch (FileNotFoundException e) {
				System.out.println("File not found ! ");
			}
		}
	}

}
