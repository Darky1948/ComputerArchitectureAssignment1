package Controller;

import java.util.ArrayList;

import Model.Encoding;

public class Writter {
	
	/**
	 * Contains all the encoded instructions (hex).
	 */
	private ArrayList<String> encodedInstructions;
	
	/**
	 * Encoding object.
	 */
	private Encoding encoding;
	
	/**
	 * Writter constructor with these given parameters.
	 * @param encodedInstructions
	 * @param encoding
	 */
	public Writter(ArrayList<String> encodedInstructions, Encoding encoding) {
		this.encodedInstructions = encodedInstructions;
		this.encoding = encoding;
	}

	
	/**
	 * File contains :
	 *  The address of the instruction, in 32 bit hexadecimal format
	 *	The encoded instruction, 32 bits in hexadecimal format
	 *	The label, if any
	 *	The instruction and its operands
	 *	The optional comment
	 */
	public void writeFullFile() {
		
	}
	
	
	/**
	 * File contains one line with a hexadecimal 32 bit number representing the encoded bit fields for every instruction in the input file.
	 */
	public void writeSmallFile() {
		
	}
	
	
	/**
	 * @return the encodedInstructions
	 */
	public ArrayList<String> getEncodedInstructions() {
		return encodedInstructions;
	}

	/**
	 * @return the encoding
	 */
	public Encoding getEncoding() {
		return encoding;
	}
	
}
