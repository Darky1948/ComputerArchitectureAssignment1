package Controller;

import java.util.ArrayList;
import java.util.HashMap;

import Model.Statement;

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
	 * Constructor with these given parameters.
	 * @param statements
	 * @param labelAddress
	 */
	public Encoding(ArrayList<Statement> statements, HashMap<String, String> labelAddress) {
		this.statements = statements;
		this.labelAddress = labelAddress;
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
		
}
