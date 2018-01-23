/**
 * 
 */
package View;

import java.io.FileNotFoundException;

import Controller.Encoder;
import Controller.Reader;
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
		if(args != null) {
			// TODO to del System.out.println(args[0]);
			
			Reader reader = new Reader(args[0]);
			try {
				Encoding encoding = reader.parseFile();
				
				Encoder encoder = new Encoder();
				
				encoder.getEncodedInstruction(encoding);
				
				encoder.showEncodedInstruction();
				
			} catch (FileNotFoundException e) {
				System.out.println("File not found ! ");
			}
			// TODO encoding part 
			// TODO writting part generating 2 files
		}
	}

}
