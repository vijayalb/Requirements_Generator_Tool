package rgt.controllers;

import java.util.List;

import rgt.application.ApplicationManager;
import rgt.data.ActionData;
import rgt.data.BusinessProcessData;
import rgt.data.StepData;

public class EditController {
	public void addBusinessProcess(BusinessProcessData businessProcess) {
		ApplicationManager manager = new ApplicationManager();
		manager.addBusinessProcess(businessProcess);
	}
	
	public void addStep(StepData step) {
		ApplicationManager manager = new ApplicationManager();
		manager.addStep(step);
	}
	
	public void addAction(ActionData action) {
		ApplicationManager manager = new ApplicationManager();
		manager.addAction(action);
	}
	
	public void updateBusinessProcess(BusinessProcessData businessProcess) {
		ApplicationManager manager = new ApplicationManager();
		manager.updateBusinessProcess(businessProcess);	
	}
	
	public void updateStep(StepData step) {
		ApplicationManager manager = new ApplicationManager();
		manager.updateStep(step);
	}
	
	public void updateAction(ActionData action) {
		ApplicationManager manager = new ApplicationManager();
		manager.updateAction(action);
	}

	public void deleteBusinessProcess(String businessProcessId) {
		ApplicationManager manager = new ApplicationManager();
		manager.deleteBusinessProcess(businessProcessId);
	}
	
	public void deleteStep(String stepId) {
		ApplicationManager manager = new ApplicationManager();
		manager.deleteStep(stepId);
	}
	
	public void deleteAction(String actionId) {
		ApplicationManager manager = new ApplicationManager();
		manager.deleteAction(actionId);
	}

	public List<BusinessProcessData> getAllBusinessProcesses() {
		ApplicationManager manager = new ApplicationManager();
		return manager.getAllBusinessProcesses();
	}
	
	public List<StepData> getAllSteps(String businessProcessId) {
		ApplicationManager manager = new ApplicationManager();
		return manager.getAllSteps(businessProcessId);
	}
	
	public List<ActionData> getAllActions(String stepId) {
		ApplicationManager manager = new ApplicationManager();
		return manager.getAllActions(stepId);
	}

	public BusinessProcessData getBusinessProcess(String businessProcessId) {
		ApplicationManager manager = new ApplicationManager();
		return manager.getBusinessProcess(businessProcessId);
	}
	
	public StepData getStep(String stepId) {
		ApplicationManager manager = new ApplicationManager();
		return manager.getStep(stepId);
	}
	
	public ActionData getAction(String actionId) {
		ApplicationManager manager = new ApplicationManager();
		return manager.getAction(actionId);
	}

	public List<StepData> getAllSteps() {
		ApplicationManager manager = new ApplicationManager();
		return manager.getAllSteps();
	}
}
