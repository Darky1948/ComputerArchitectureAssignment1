package Model;

import java.util.ArrayList;
import java.util.HashMap;

public class Encoding {
	
	/**
	 * List of statements.
	 */
	private ArrayList<Statement> statements;
	
	/**
	 * Contain all the label with its address.
	 */
	private HashMap<String, String> labelAddress;
	
	/**
	 * This store the position of the instruction that needs an address. exemple for jump or beq.
	 */
	private HashMap<EnumOperation, Long> positionBranch;
	
	/**
	 * Constructor with these given parameters.
	 * @param statements
	 * @param labelAddress
	 * @param positionBranch
	 */
	public Encoding(ArrayList<Statement> statements, HashMap<String, String> labelAddress, HashMap<EnumOperation, Long> positionBranch) {
		this.statements = statements;
		this.labelAddress = labelAddress;
		this.positionBranch = positionBranch;
	}

	/**
	 * @return the statements
	 */
	public ArrayList<Statement> getStatements() {
		return statements;
	}

	/**
	 * @param statements the statements to set
	 */
	public void setStatements(ArrayList<Statement> statements) {
		this.statements = statements;
	}

	/**
	 * @return the labelAddress
	 */
	public HashMap<String, String> getLabelAddress() {
		return labelAddress;
	}

	/**
	 * @param labelAddress the labelAddress to set
	 */
	public void setLabelAddress(HashMap<String, String> labelAddress) {
		this.labelAddress = labelAddress;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Encoding [statements=" + statements + ", labelAddress="
				+ labelAddress + "]";
	}

	/**
	 * @return the positionBranch
	 */
	public HashMap<EnumOperation, Long> getPositionBranch() {
		return positionBranch;
	}

	/**
	 * @param positionBranch the positionBranch to set
	 */
	public void setPositionBranch(HashMap<EnumOperation, Long> positionBranch) {
		this.positionBranch = positionBranch;
	}
		
}
