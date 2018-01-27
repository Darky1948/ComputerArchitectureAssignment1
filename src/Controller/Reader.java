package Controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import Model.Encoding;
import Model.EnumOperation;
import Model.EnumRegisters;
import Model.Operand;
import Model.Statement;

public class Reader {
	/**
	 * The path of the file to read.
	 */
	private String path;
	
	/**
	 * Address counter.
	 */
	private int parserAdrCounter = 0;
	
	/**
	 * Contains label as key and address as value.
	 */
	private HashMap<String, String> labelAddress;
	
	public Reader(String pPath) {
		this.path = pPath;
		this.labelAddress = new HashMap<>();
	}
	/*
	 * TODO :
	 * problème avec les immediates 
	 *  lw $t1, 12($t2) t2 bien set mais pas le immediate
	 *  addi $s1, $s1, 10 bien set mais pas le immediate 3 operands.
	 *  beq $t0, $t1, begin rajouter label dans operands.
	 */
	/**
	 * Parse the given file to fill the Statement object according to the specifications (either comments, labels or statement).
	 * @throws FileNotFoundException
	 */
	public Encoding parseFile() throws FileNotFoundException {
		// Variables
		File file = new File(this.path);
		ArrayList<Statement> statements = new ArrayList<>();
		
		// Get file into the scanner
		Scanner scanner = new Scanner(file);
		
		//While there is a next line do
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			
			if(line != null && !line.replaceAll("\\s+", "").isEmpty() && !line.replaceAll("\\s+", "").equals("")) {
				Statement statement = new Statement();
				
				// Comment ?
				boolean isComment = isLineComment(line.replaceAll("\\s+", ""));
				if(isComment) {
					statement.setComment(true);
					statement.setComment(line.replaceAll("\\s+", ""));
				}
				
				// Label alone ?
				boolean isLabel = isLineLabel(line.replaceAll("\\s+", ""));
				if(isLabel){
					statement.setLabel(true);
					statement.setLabel(line.replaceAll("\\s+|:", ""));
					// list to store label and compute its address with program counter
					labelAddress.put(statement.getLabel(), String.valueOf(this.parserAdrCounter * 4));
				}
				
				// Statement
				if(!isComment && !isLabel) {
					statement = isStatement(line);
					
				}
				
				statements.add(statement);
			}else{
				Statement statement = new Statement();
				statement.setEmptyLine(true);
				statements.add(statement);
			}
			
			this.parserAdrCounter++;
		}
		
		scanner.close();
		
		Encoding encoding = new Encoding(statements, labelAddress);
		
		return encoding;
	}
	
	/**
	 * Determine whether the current line is a comment.
	 * @param line
	 * @return boolean
	 */
	private boolean isLineComment(String line) {
		if(line != null && !line.equals(" ") && !line.isEmpty()) {
			if(String.valueOf(line.charAt(0)).equals("#")) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Determine whether the current line is a label.
	 * @param line
	 * @return boolean
	 */
	private boolean isLineLabel(String line) {
		if(line != null && !line.equals(" ") && !line.isEmpty()) {
			if(line.contains(":")) {
				int index = line.indexOf(":");
				if(index == line.length() -1) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Determine whether the current line is a Statement.
	 * @param line
	 * @return Statement
	 */
	private Statement isStatement(String line) {
		Statement statement = new Statement();
		Integer indexOfColon = null;
		Integer indexOfHashTag = null;

		// Is first position a label ?
		if (!String.valueOf(line.charAt(0)).equals(" ") && line.contains(":")) {
			// That means yes
			indexOfColon = line.indexOf(":");
			statement.setLabel(line.substring(0, indexOfColon));
			statement.setLabel(true);
			labelAddress.put(statement.getLabel(), String.valueOf(parserAdrCounter * 4));
		}

		// Do we have an optional comment ?
		if (line.contains("#")) {
			// That means yes
			indexOfHashTag = line.indexOf("#");
			statement.setComment(line.substring(indexOfHashTag, line.length()));
		}

		// We want only the instruction string
		if (indexOfColon != null) {
			line = line.substring(indexOfColon + 1, line.length());
		}
		if (indexOfHashTag != null) {
			line = line.substring(0, indexOfHashTag);
		}

		// J-type - op label
		if (!line.contains(",")) {
            if(line.contains("nop")){
                String op = line.replaceAll("\\s+", "");

                statement.setOperation(EnumOperation.getEnumByLabel(op));
                statement.setOperands(null);
            } else {
                String[] strs = line.split(" ");
                strs[0] = strs[0].replaceAll("\t","");
                String op = strs[0];
                String label = strs[1];

                // We create operation object and search it by its label
               EnumOperation enumOperation = EnumOperation.getEnumByLabel(op);
				// Jump
                if(enumOperation.getLabel().equals("j")) {
                    statement.setOperation(enumOperation);
                    statement.setOperands(null);
                    // define label operand
                    ArrayList<Operand> operands = new ArrayList<>();
                    Operand operand = new Operand();
                    
                    operand.setEnumRegister(null);
                    operand.setLabel(label);
                    
                    operands.add(operand);
                    statement.setOperands(operands);
                    
                } // Jump register
                else {
                    // Operands
                	statement.setOperation(enumOperation);
                    ArrayList<Operand> operands = new ArrayList<>();
                    Operand operand = new Operand();

                    operand.setEnumRegister(EnumRegisters.getEnumByLabel(label));
                    operands.add(operand);

                    statement.setOperands(operands);
                }
            }

		}

		// Then it can be either I-type or R-type
		if (line.contains(",")) {
			// We look first for the operation.
			line = line.replaceAll("\t","");
			String[] strs = line.split(",");
			
			// Retrieving the operation
			String[] firstPart = strs[0].split(" ");
			String op = firstPart[0].replaceAll("\\s+", "");
			
			EnumOperation enumOperation = EnumOperation.getEnumByLabel(op);
			
			statement.setOperation(enumOperation);
			
			// I-Type - op rt rs imm
			if(enumOperation.getFormat().equals("I")) {
				ArrayList<Operand> operands = new ArrayList<>();
				
				// Retrieving the operands
				ArrayList<String> oprds = new ArrayList<String>();
				for (int i = 1; i < firstPart.length; i++) {
					if(!firstPart[i].equals("")) {
						oprds.add(firstPart[i].replaceAll("\\s+", ""));
					}
				}
				oprds.add(strs[1].replaceAll("\\s+", ""));
				
				if(strs.length == 3) {
					oprds.add(strs[2].replaceAll("\\s+", ""));
				}
				
				for (String string : oprds) {
					Operand operand = new Operand();
					// Check whether it is a immediate value
                    if(string.matches("^[0-9]*$")) {
                        operand.setImmediate(string);
					}else {
						if(string.contains("(") && string.contains(")")) {;
							String tmp = string.substring(3, string.length() - 1);
							operand.setEnumRegister(EnumRegisters.getEnumByLabel(tmp));
							String immediate = string.substring(0, 2);
							operand.setImmediate(immediate);
						}else {
							operand.setEnumRegister(EnumRegisters.getEnumByLabel(string));
							if(operand.getEnumRegister() == null) {
								operand.setLabel(string);
							}
						}
					}
					// Add it to the operands list
					operands.add(operand);
				}
				// Set the operands to the instruction
				statement.setOperands(operands);
			}
			
			// R-type - op rd rs rt
			if(enumOperation.getFormat().equals("R")) {
				ArrayList<Operand> operands = new ArrayList<>();
				
				// Retrieving the operands
				ArrayList<String> oprds = new ArrayList<String>();
				oprds.add(firstPart[1].replaceAll("\\s+", ""));
				oprds.add(strs[1].replaceAll("\\s+", ""));
				oprds.add(strs[2].replaceAll("\\s+", ""));
				
				// SLL 
				if(enumOperation.getLabel().equals("sll")) {
					for (int i = 0; i < oprds.size(); i++) {
						Operand operand = new Operand();
						if(i == 2) { // last value is immediate
							operand.setImmediate(oprds.get(i));
						}else {
							operand.setEnumRegister(EnumRegisters.getEnumByLabel(oprds.get(i)));
						}
						operands.add(operand);
					}
				}else {
					
					for (String string : oprds) {
						Operand operand = new Operand();
						operand.setEnumRegister(EnumRegisters.getEnumByLabel(string));
						operands.add(operand);
					}
				}
				
				// Set the operands to the instruction
				statement.setOperands(operands);
				
			}
		}

		return statement;
	}
}
