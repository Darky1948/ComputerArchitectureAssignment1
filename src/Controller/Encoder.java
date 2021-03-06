/**
 * 
 */
package Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Model.Encoding;
import Model.EnumOperation;
import Model.Operand;
import Model.Statement;

/**
 * @author Darky
 *
 */
public class Encoder {
	
	/**
	 * Contains the encoded instruction.
	 */
	private ArrayList<String> encodedInstruction;
	
	/**
	 * Contains the encoded addresses of each instruction.
	 */
	private ArrayList<String> encodedAddresses;
	
	/**
	 * Default constructor.
	 */
	public Encoder() {
		this.encodedInstruction = new ArrayList<>();
		this.encodedAddresses = new ArrayList<>();
	}

	/**
	 * This function aim is to produce data for the output file that shall contain
	 * one line with a hexadecimal 32 bit number representing the encoded bit fields
	 * for every instruction in the input file.
	 * @return the encodedInstruction
	 */
	public ArrayList<String> getEncodedInstruction(Encoding encoding) {
		this.encodedInstruction = new ArrayList<>();
		
		ArrayList<Statement> statements = encoding.getStatements();
		HashMap<String, String> labelAddress = encoding.getLabelAddress();
		HashMap<EnumOperation, Long> positionBranch = encoding.getPositionBranch();
		
		Long addressLineCounter = 0L;
		Long index = 0L;
		try {
			for (Statement statement : statements) {
				String encodedInstr = ""; // String to concat all the computed encoded instruction
				
				// comment
				if(statement.isComment()) {
					this.encodedInstruction.add(null);
					this.encodedAddresses.add(null);
				} else {
					EnumOperation enumOperation = statement.getOperation();
					ArrayList<Operand> operands = statement.getOperands();
					
					if(enumOperation != null) {
						encodedInstr += "0x";
						Long opCode = (enumOperation.getOpcode() << 26); // shift left 26 bits
						
						// J type
						if(enumOperation.getFormat().equals("J")) {
							// Retrieving the label address values
							for (Map.Entry<EnumOperation, Long> entry : positionBranch.entrySet()) {
							    EnumOperation eo = entry.getKey();
							    
							    if(enumOperation.getOpcode().equals(eo.getOpcode())) {
							    	encodedInstr += Long.toHexString(opCode + entry.getValue());
							    }
							}
						}
						
						// I type
						if(enumOperation.getFormat().equals("I")) {
							Long rs = 0L;
	                        Long rt = 0L;
	                        String imm = "";
	                        
	                        if(statement.getOperands().size() < 3) { // instructions like lw $t1, 12($t2)
	                            rs += operands.get(1).getEnumRegister().getRegisterNumber();
	                            rt += operands.get(0).getEnumRegister().getRegisterNumber();
	                            imm += operands.get(1).getImmediate();
	                            encodedInstr += Long.toHexString(opCode + (rs << 21) + (rt << 16) + Long.parseLong(imm));
	                        }else { 
	                        	 if (operands.get(2).getLabel() != null) { // instructions like beq $t0, $t1, begin
	                                rs += operands.get(0).getEnumRegister().getRegisterNumber();
									rt += operands.get(1).getEnumRegister().getRegisterNumber();
									
									// Retrieving the label address values
									for (Map.Entry<String, String> entry : labelAddress.entrySet()) {
									    String label = entry.getKey();
									    String address = entry.getValue();
									    
									    if(label.equals(operands.get(2).getLabel())) {
									    	imm += Long.parseLong(address);
									    }
									}
									
									// Compute the number of instructions to jump counted from the instruction after the beq
									Long tmp = 0L;
									for (Map.Entry<EnumOperation, Long> entry : positionBranch.entrySet()) {
									    EnumOperation eo = entry.getKey();
									    
									    if(enumOperation.getOpcode().equals(eo.getOpcode())) {
									    	tmp += entry.getValue();
									    }
									}
									
									tmp = ((tmp - Long.valueOf(imm)));
									
									encodedInstr += Long.toHexString(opCode + (rs << 21) + (rt+1 << 16) + (-tmp));
									
								} else { 
									// instructions like addi $s1, $s1, 10
	                                 rs += operands.get(0).getEnumRegister().getRegisterNumber();
	                                 rt += operands.get(1).getEnumRegister().getRegisterNumber();
	                                 imm += operands.get(2).getImmediate();
	                                 encodedInstr += Long.toHexString(opCode + (rs << 21) + (rt << 16) + Long.parseLong(imm));
								}
	                        }
						}
						
						// R type
						if(enumOperation.getFormat().equals("R")) {
							if (enumOperation.getLabel().equals("nop")) { // Specific type of R nop always equals to 0
								encodedInstr += "0000000";
	                        } else if (enumOperation.getLabel().equals("jr")) { // jr jump register is R type specific kind.
	                        	// op rs 0 0 0 funct
	                        	encodedInstr += Long.toHexString(opCode + (operands.get(0).getEnumRegister().getRegisterNumber() << 21) + (0 << 16) + (0 << 11) + enumOperation.getFunct());
	                        } else {
	                        	Long funct = EnumOperation.getEnumByLabel(enumOperation.getLabel()).getFunct();
	                            Long rd = operands.get(0).getEnumRegister().getRegisterNumber();
	                            Long rs = operands.get(1).getEnumRegister().getRegisterNumber();
	                           
	                        	if(enumOperation.getLabel().equals("sll")) {
	                        		String imm =  operands.get(2).getImmediate();
	                        		encodedInstr += Long.toHexString(opCode + (rs << 16) + (rd << 11) + (Long.parseLong(imm) << 6) + funct);
	                        	}else {
	                        		Long rt = operands.get(2).getEnumRegister().getRegisterNumber();
	                        		encodedInstr += Long.toHexString(opCode + (rs << 21) + (rt << 16) + (rd << 11) + funct);
	                        	}
	                            
	                        }
	
						}
						
						// Add missing leading zeros.
						int pad = 10 - encodedInstr.length();
						if (pad > 0) {
							StringBuilder sb = new StringBuilder(encodedInstr);
							for (int k = 0; k < pad; k++) {
								sb.insert(2, 0);
							}
							encodedInstr = sb.toString();
						}
						
						// Add the encoded instruction to the list
						this.encodedInstruction.add(encodedInstr);
						
						String encodedAdr = "0x" + Long.toHexString(addressLineCounter * 4);
						int pad2 = 10 - encodedAdr.length();
						if (pad2 > 0) {
							StringBuilder sb = new StringBuilder(encodedAdr);
							for (int k = 0; k < pad2; k++) {
								sb.insert(2, 0);
							}
							encodedAdr = sb.toString();
						}
						
						this.encodedAddresses.add(encodedAdr);
						addressLineCounter++;
					}
				}
			}
		}catch (NullPointerException e) {
			System.out.println("Something went wrong nullpointer exception" + e.getStackTrace());
			this.encodedAddresses.add("NullPointer");
			this.encodedInstruction.add("NullPointer");
		}
		
		return encodedInstruction;
	}

	/**
	 * @param encodedInstruction the encodedInstruction to set
	 */
	public void setEncodedInstruction(ArrayList<String> encodedInstruction) {
		this.encodedInstruction = encodedInstruction;
	}

	/**
	 * @return the encodedAddresses
	 */
	public ArrayList<String> getEncodedAddresses() {
		return encodedAddresses;
	}

	/**
	 * @param encodedAddresses the encodedAddresses to set
	 */
	public void setEncodedAddresses(ArrayList<String> encodedAddresses) {
		this.encodedAddresses = encodedAddresses;
	}
	
	
	public void showEncodedInstruction () {
		for (String string : encodedInstruction) {
			if(string == null) {
				System.out.println("probably a comment ? ");
			}else {
				System.out.println(string);
			}
		}
	}
}
