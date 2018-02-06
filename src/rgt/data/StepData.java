package rgt.data;

public class StepData extends VerbNounPhraseData {
	private String stepId;
	private String businessProcessId;
	private int stepSeqNumber;
	
	public String getStepId() {
		return stepId;
	}
	public void setStepId(String stepId) {
		this.stepId = stepId;
	}
	public String getBusinessProcessId() {
		return businessProcessId;
	}
	public void setBusinessProcessId(String businessProcessId) {
		this.businessProcessId = businessProcessId;
	}
	public int getStepSeqNumber() {
		return stepSeqNumber;
	}
	public void setStepSeqNumber(int stepSeqNumber) {
		this.stepSeqNumber = stepSeqNumber;
	}

	@Override
	public String toString() {
		return this.getVerbNounPhrase();
	}
}
