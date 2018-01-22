package Model;


public class Operand {

	private EnumRegisters enumRegister;
	private String immediate;

	public Operand() {
		this.enumRegister = null;
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

}
