package Model;

public enum EnumOperation {
	
	ADD("add", "0000", "0x00", "R", "20"),
	SUB("sub", "0000", "0x00", "R", "22"),
	AND("and", "0000", "0x00", "R", "24"),
	OR("or", "0000", "0x00", "R", "25"),
	NOR("nor", "0000", "0x00", "R", "27"),
	SLT("slt", "0000", "0x00", "R", "2A"),
	LW("lw", "100011", "0x23", "I", "NA"), 
	SW("sw", "101011", "0x2b", "I", "NA"),
	BEQ("beq", "0000", "0x04", "I", "NA"),
	ADDI("addi", "8", "0x08", "I", "NA"),
	SLL("sll", "0000", "0x00", "R", "00"),
	J("j", "0000", "0x02", "J", "NA"),
	JR("jr", "0000", "0x00", "R", "08"),
	NOP("nop", "0000", "0x00000000", "R", "NA");

	private String label;
	private String value;
	private String hex;
	private String format;
	private String funct;

	EnumOperation(String label, String value, String hex, String format,
			String funct) {
		this.label = label;
		this.value = value;
		this.hex = hex;
		this.format = format;
		this.funct = funct;
	}

	/**
	 * Return the EnumOperation for the given label.
	 * 
	 * @param label
	 * @return EnumOperationValue
	 */
	public static EnumOperation getEnumByLabel(String label) {
		for (EnumOperation e : values()) {
			if (e.label.equals(label)) {
				return e;
			}
		}
		return null;
	}

	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * @param label
	 *            the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * @return the hex
	 */
	public String getHex() {
		return hex;
	}

	/**
	 * @param hex
	 *            the hex to set
	 */
	public void setHex(String hex) {
		this.hex = hex;
	}

	/**
	 * @return the format
	 */
	public String getFormat() {
		return format;
	}

	/**
	 * @param format
	 *            the format to set
	 */
	public void setFormat(String format) {
		this.format = format;
	}

	/**
	 * @return the funct
	 */
	public String getFunct() {
		return funct;
	}

	/**
	 * @param funct
	 *            the funct to set
	 */
	public void setFunct(String funct) {
		this.funct = funct;
	}
}
