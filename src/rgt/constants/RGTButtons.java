package rgt.constants;

public enum RGTButtons {
	ADD("Add"), 
	UPDATE("Update"), 
	DELETE("Delete"),
	EXPORT("Export Requirements"), 
	CANCEL("Cancel");
	
	private String button;
	
	RGTButtons(String button) {
		this.button = button;
	}
	
	public String getButton() {
		return button;
	}
}
