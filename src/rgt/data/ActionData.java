package rgt.data;

public class ActionData extends VerbNounPhraseData {
	private String actionId;
	private String stepId;
	private int actionSeqNumber;

	public String getActionId() {
		return actionId;
	}
	public void setActionId(String actionId) {
		this.actionId = actionId;
	}	
	public String getStepId() {
		return stepId;
	}
	public void setStepId(String stepId) {
		this.stepId = stepId;
	}
	public int getActionSeqNumber() {
		return actionSeqNumber;
	}
	public void setActionSeqNumber(int actionSeqNumber) {
		this.actionSeqNumber = actionSeqNumber;
	}
	@Override
	public String toString() {
		return this.getVerbNounPhrase();
	}
	
}
