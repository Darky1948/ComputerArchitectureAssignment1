package Model;


public class Operand {

	private EnumRegisters enumRegister;
	private String immediate;
	private String label;

	public Operand() {
		this.enumRegister = null;
		this.immediate = null;
		this.setLabel(null);
	}

	public EnumRegisters getEnumRegister() {
		return enumRegister;
	}

	public void setEnumRegister(EnumRegisters enumRegister) {
		this.enumRegister = enumRegister;
	}

	public String getImmediate() {
		return immediate;
	}

	public void setImmediate(String immediate) {
		this.immediate = immediate;
	}

	@Override
	public String toString() {
		return "Operand [enumRegister=" + enumRegister.getLabel() + ", immediate=" + immediate + "]";
	}

	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * @param label the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}	

}
