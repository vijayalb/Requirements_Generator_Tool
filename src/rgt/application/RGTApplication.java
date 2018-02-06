package rgt.application;

import java.util.List;

import rgt.data.ActionData;
import rgt.data.BusinessProcessData;
import rgt.data.StepData;

public class RGTApplication {
	private static RGTApplication applicationInstance = new RGTApplication();
	private List<BusinessProcessData> businessProcesses;
	private List<StepData> steps;
	private List<ActionData> actions;
	
	private RGTApplication() {}
	
	public static RGTApplication getApplicationInstance() {
		return applicationInstance;
	}
	
	public List<BusinessProcessData> getBusinessProcesses() {
		return businessProcesses;
	}
	public void setBusinessProcesses(List<BusinessProcessData> businessProcesses) {
		this.businessProcesses = businessProcesses;
	}
	public List<StepData> getSteps() {
		return steps;
	}
	public void setSteps(List<StepData> steps) {
		this.steps = steps;
	}
	public List<ActionData> getActions() {
		return actions;
	}
	public void setActions(List<ActionData> actions) {
		this.actions = actions;
	}
	
}
