package Model;

public enum EnumOperation {
	
	ADD("add", 0L, "R", 0x20L),
	SUB("sub", 0L, "R", 0x22L),
	AND("and", 0L, "R", 0X24L),
	OR("or", 0L, "R", 0X25L),
	NOR("nor", 0L, "R", 0X27L),
	SLT("slt", 0L, "R", 0x2aL),
	LW("lw", 35L, "I", 100L), 
	SW("sw", 43L, "I", 100L),
	BEQ("beq", 4L, "I", 100L),
	ADDI("addi", 8L, "I", 100L),
	SLL("sll", 0L, "R", 100L),
	J("j", 2L, "J", 100L),
	JR("jr", 0L, "R", 0x08L),
	NOP("nop", 0L, "R", 100L);

	private String label;
	private Long opcode;
	private String format;
	private Long funct;

	EnumOperation(String label, Long opcode, String format, Long funct) {
		this.label = label;
		this.setOpcode(opcode);
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
	public Long getFunct() {
		return funct;
	}

	/**
	 * @param funct
	 *            the funct to set
	 */
	public void setFunct(Long funct) {
		this.funct = funct;
	}

	/**
	 * @return the opcode
	 */
	public Long getOpcode() {
		return opcode;
	}

	/**
	 * @param opcode the opcode to set
	 */
	public void setOpcode(Long opcode) {
		this.opcode = opcode;
	}
}
