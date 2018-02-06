package rgt.data;

public class BusinessProcessData extends VerbNounPhraseData {
	private String businessProcessId;
	private int priority;
	
	public String getBusinessProcessId() {
		return businessProcessId;
	}
	public void setBusinessProcessId(String businessProcessId) {
		this.businessProcessId = businessProcessId;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	
	@Override
	public String toString() {
		return this.getVerbNounPhrase();
	}
	
}
