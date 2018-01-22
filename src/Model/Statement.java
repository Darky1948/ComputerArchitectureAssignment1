/**
 * 
 */
package Model;

import java.util.ArrayList;

/**
 * @author kviguier
 *
 */
public class Statement {
	
	/**
	 * Whether it is a empty line.
	 */
	private boolean isEmptyLine;
	
	/**
	 * Whether it is a comment.
	 */
	private boolean isComment;
	
	/**
	 * Represent the comment a value.
	 */
	private String comment;
	
	/**
	 * Whether it is a label.
	 */
	private boolean isLabel;
	
	/**
	 * Represent the label value.
	 */
	private String label;
	
	/**
	 * Represent the opcode.
	 */
	private EnumOperation operation;
	
	/**
	 * Represent the operands.
	 */
	private ArrayList<Operand> operands;
	
	/**
	 * If there is an immediate value.
	 */
	private Integer immediate;
	
	
	public Statement() {
		this.setOperation(null);
		this.setOperands(null);
		this.label = null;
		this.comment = null;
		this.setImmediate(null);
	}

	/**
	 * @return the isEmptyLine
	 */
	public boolean isEmptyLine() {
		return isEmptyLine;
	}

	/**
	 * @param isEmptyLine the isEmptyLine to set
	 */
	public void setEmptyLine(boolean isEmptyLine) {
		this.isEmptyLine = isEmptyLine;
	}

	/**
	 * @return the isComment
	 */
	public boolean isComment() {
		return isComment;
	}

	/**
	 * @param isComment the isComment to set
	 */
	public void setComment(boolean isComment) {
		this.isComment = isComment;
	}

	/**
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * @param comment the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * @return the isLabel
	 */
	public boolean isLabel() {
		return isLabel;
	}

	/**
	 * @param isLabel the isLabel to set
	 */
	public void setLabel(boolean isLabel) {
		this.isLabel = isLabel;
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

	/**
	 * @return the operation
	 */
	public EnumOperation getOperation() {
		return operation;
	}

	/**
	 * @param operation the operation to set
	 */
	public void setOperation(EnumOperation operation) {
		this.operation = operation;
	}

	/**
	 * @return the immediate
	 */
	public Integer getImmediate() {
		return immediate;
	}

	/**
	 * @param immediate the immediate to set
	 */
	public void setImmediate(Integer immediate) {
		this.immediate = immediate;
	}

	/**
	 * @return the operands
	 */
	public ArrayList<Operand> getOperands() {
		return operands;
	}

	/**
	 * @param operands the operands to set
	 */
	public void setOperands(ArrayList<Operand> operands) {
		this.operands = operands;
	}
	
}
