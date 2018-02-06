package rgt.controllers;

import java.util.ArrayList;
import java.util.List;

import rgt.application.ApplicationManager;
import rgt.data.ActionData;
import rgt.data.BusinessProcessData;
import rgt.data.StepData;

public class GenerateController {

	public void generateRequirementsTestData() {
		setBusinessProcess();
		setSteps();
		setActions();
	}

	public StringBuilder getOutputContent() {
		ApplicationManager manager = new ApplicationManager();
		
		List<BusinessProcessData> businessProcesses = manager.getAllBusinessProcesses();
		StringBuilder output = new StringBuilder();
		for(BusinessProcessData businessProcess: businessProcesses) {
			output.append(businessProcess.getVerbNounPhrase() + "\n");
			for(StepData step: manager.getAllSteps(businessProcess.getBusinessProcessId())) {
				output.append("    " + step.getVerbNounPhrase() + "\n");
				for(ActionData action: manager.getAllActions(step.getStepId())) {
					output.append("        " + action.getVerbNounPhrase() + "\n");
				}
			}
		}
		return output;
	}

	private void setBusinessProcess() {
		ApplicationManager manager = new ApplicationManager();
		
		BusinessProcessData businessProcess = new BusinessProcessData();
		businessProcess.setBusinessProcessId("1");
		businessProcess.setVerbNounPhrase("deposit money");
		businessProcess.setPriority(1);
		
		manager.addBusinessProcess(businessProcess);
	}
	
	private List<StepData> setSteps() {
		ApplicationManager manager = new ApplicationManager();
		List<StepData> steps = new ArrayList<StepData>();
		for(int i=1; i<=4; i++) {
			StepData step = new StepData();
			step.setStepId(Integer.toString(i));
			step.setVerbNounPhrase("step " + i);
			step.setBusinessProcessId("1");
			steps.add(step);
			manager.addStep(step);
		}
		return steps;
	}

	private List<ActionData> setActions() {
		ApplicationManager manager = new ApplicationManager();
		List<ActionData> actions = new ArrayList<ActionData>();
		for(int i=1; i<=4; i++) {
			ActionData action = new ActionData();
			action.setActionId(Integer.toString(i));
			action.setVerbNounPhrase("action " + i);
			action.setStepId(Integer.toString(i));
			actions.add(action);
			manager.addAction(action);
		}
		return actions;
	}

}
