package Controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import Model.Encoding;
import Model.Operand;
import Model.Statement;

public class Writter {
	
	private final String FILE_FULL_NAME = "resources/outputFull.txt";
	
	private final String FILE_SMALL_NAME = "resources/outputSmall.txt";
	
	/**
	 * Default constructor.
	 */
	public Writter() {
		
	}

	
	/**
	 * File contains :
	 *  The address of the instruction, in 32 bit hexadecimal format
	 *	The encoded instruction, 32 bits in hexadecimal format
	 *	The label, if any
	 *	The instruction and its operands
	 *	The optional comment
	 * @param encodedInstructions
	 * @param encoding
	 * @param encodedAddresses
	 */
	public void writeFullFile(ArrayList<String> encodedInstructions, Encoding encoding, ArrayList<String> encodedAddresses) {
		int address = 0;
		int index = 0;
		ArrayList<Statement> statements = encoding.getStatements();
		ArrayList<String> dataToWrite = new ArrayList<>();
		
		for (Statement statement : statements) {
			String tmp = "";
			
			if(statement.isEmptyLine()) {
				tmp += "\n";
			}
			
			// Comment
			if(statement.isComment() && statement.getOperation() == null) {
				tmp += "\t\t\t\t\t\t" + statement.getComment() + "\n";
				index++;
			}
			
			// Label
			if(statement.isLabel() && statement.getOperation() == null) {
				tmp += "\t\t\t\t\t\t" + statement.getLabel() + ":\n";
			}
			
			// Statements
			if(statement.getOperation() != null) {
				if(encodedInstructions.get(index) != null && encodedAddresses.get(index) != null) {
					tmp += encodedAddresses.get(index) + " " + encodedInstructions.get(index) + "\t";
				}
				tmp += "\t\t\t" + getInstructionLine(statement) + "\n";
				index++;
			}
			
			dataToWrite.add(tmp);
			address++;
		}
		
		// Create the file each time
		File fileFull = new File(FILE_FULL_NAME);
		
		String pathFull = fileFull.getPath();

		BufferedWriter bwFull = null;
		FileWriter fwFull = null;
		
		try {
			fwFull = new FileWriter(pathFull);
			bwFull = new BufferedWriter(fwFull);
			
			// Writing content
			for (String data : dataToWrite) {
				if(data != null) {
					bwFull.write(data);
				}
			}
			
			System.out.println("Done");

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				if (bwFull != null) {
					bwFull.close();
				}
				
				if (fwFull != null) {
					fwFull.close();
				}

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}
	}
	
	/**
	 * This function construct the instruction line.
	 * @param statement
	 * @return String
	 */
	private String getInstructionLine(Statement statement) {
		String tmp = "";
		
		if(statement.getOperation() != null) {
			if(statement.isLabel()) {
				tmp += statement.getLabel() + ":\t";
			}
			tmp += statement.getOperation().getLabel() + " ";
			if(statement.getOperands() != null) {
				for (Operand operand : statement.getOperands()) {
					if(operand.getEnumRegister() != null && operand.getImmediate() == null) {
						tmp += operand.getEnumRegister().getLabel() + ", ";
					}
					
					if(operand.getEnumRegister() != null && operand.getImmediate() != null) {
						tmp += operand.getImmediate() + "(" + operand.getEnumRegister().getLabel() + "), ";
					}
					
					if(operand.getImmediate() != null && operand.getEnumRegister() == null) {
						tmp += operand.getImmediate() + ", ";
					}
					
					if(operand.getLabel() != null) {
						tmp += operand.getLabel() + "  ";
					}
				}
			}
			
			if(statement.isComment()) {
				tmp += statement.getComment();
			}
		}
		
		return tmp.substring(0, tmp.length() - 2);
	}
	
	/**
	 * File contains one line with a hexadecimal 32 bit number representing the encoded bit fields for every instruction in the input file.
	 */
	public void writeSmallFile(ArrayList<String> encodedInstructions) {
		// Create the file each time
		File fileFull = new File(FILE_SMALL_NAME);
		
		String pathFull = fileFull.getPath();

		BufferedWriter bwFull = null;
		FileWriter fwFull = null;
		
		try {
			fwFull = new FileWriter(pathFull);
			bwFull = new BufferedWriter(fwFull);
			
			// Writing content
			for (String enString : encodedInstructions) {
				if(enString != null) {
					bwFull.write(enString);
					bwFull.newLine();
				}
			}
			
			System.out.println("Done");

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				if (bwFull != null) {
					bwFull.close();
				}
				
				if (fwFull != null) {
					fwFull.close();
				}

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}
	}
	
}
