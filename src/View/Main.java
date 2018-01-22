/**
 * 
 */
package View;

import java.io.FileNotFoundException;


import Controller.Encoding;
import Controller.Reader;

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
			System.out.println(args[0]);
			
			Reader reader = new Reader(args[0]);
			try {
				Encoding encoding = reader.parseFile();
				
			} catch (FileNotFoundException e) {
				System.out.println("File not found ! ");
			}
			// TODO encoding part 
			// TODO writting part generating 2 files
		}
	}

}
