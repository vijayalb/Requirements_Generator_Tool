package rgt.application;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import rgt.data.ActionData;
import rgt.data.BusinessProcessData;
import rgt.data.StepData;

public class ApplicationManager {
	
	/* Updates the business process by using the given businessProcessData */
	public void updateBusinessProcess(BusinessProcessData businessProcessData) {
		RGTApplication app = RGTApplication.getApplicationInstance();
		List<BusinessProcessData> businessProcesses = app.getBusinessProcesses();
		
		ListIterator<BusinessProcessData> businessProcessIterator = businessProcesses.listIterator();
		while(businessProcessIterator.hasNext()) {
			BusinessProcessData businessProcess = businessProcessIterator.next();
			if(businessProcessData.getBusinessProcessId().equals(businessProcess.getBusinessProcessId())) {
				businessProcessIterator.set(businessProcessData);
				break;
			}
		}
		
		app.setBusinessProcesses(businessProcesses);
	}
	
	/* Updates the steps by using the given stepData */
	public void updateStep(StepData stepData) {
		RGTApplication app = RGTApplication.getApplicationInstance();
		List<StepData> steps = app.getSteps();
		
		ListIterator<StepData> stepIterator = steps.listIterator();
		
		while(stepIterator.hasNext()) {
			StepData step = stepIterator.next();
			if(stepData.getStepId().equals(step.getStepId())) {
				stepIterator.set(stepData);
				break;
			}
		}

		app.setSteps(steps);
	}
	
	/* Updates the actions by using the given actionData */
	public void updateAction(ActionData actionData) {
		RGTApplication app = RGTApplication.getApplicationInstance();
		List<ActionData> actions = app.getActions();
		
		ListIterator<ActionData> actionIterator = actions.listIterator();
		
		while(actionIterator.hasNext()) {
			ActionData action = actionIterator.next();
			if(actionData.getActionId().equals(action.getActionId())) {
				actionIterator.set(actionData);
				break;
			}
		}
		
		app.setActions(actions);
	}
	
	/* Deletes the business process by using businessProcessId */
	public void deleteBusinessProcess(String businessProcessId) {
		RGTApplication app = RGTApplication.getApplicationInstance();
		List<BusinessProcessData> businessProcesses = app.getBusinessProcesses();
		
		Iterator<BusinessProcessData> iterator = businessProcesses.iterator();
		while(iterator.hasNext()) {
			BusinessProcessData businessProcess = iterator.next();
			if(businessProcessId.equals(businessProcess.getBusinessProcessId())) {
				iterator.remove();
				for(StepData step: getAllSteps(businessProcessId)) {
					deleteStep(step.getStepId());
				}
				break;
			}
		}
		
		app.setBusinessProcesses(businessProcesses);
	}
	
	/* Deletes the step by using stepId */
	public void deleteStep(String stepId) {
		RGTApplication app = RGTApplication.getApplicationInstance();
		List<StepData> steps = app.getSteps();
		
		Iterator<StepData> iterator = steps.iterator();
		while(iterator.hasNext()) {
			StepData step = iterator.next();
			if(stepId.equals(step.getStepId())) {
				iterator.remove();
				for(ActionData action: getAllActions(stepId)) {
					deleteAction(action.getActionId());
				}
				break;
			}
		}
		
		app.setSteps(steps);
	}
	
	/* Deletes the action by using actionId */
	public void deleteAction(String actionId) {
		RGTApplication app = RGTApplication.getApplicationInstance();
		List<ActionData> actions = app.getActions();

		Iterator<ActionData> iterator = actions.iterator();
		while(iterator.hasNext()) {
			ActionData action = iterator.next();
			if(actionId.equals(action.getActionId())) {
				iterator.remove();
				break;
			}
		}
		
		app.setActions(actions);
	}
	
	/* Gets the business process data by using businessProcessId if it exists. Otherwise returns null */
	public BusinessProcessData getBusinessProcess(String businessProcessId) {
		RGTApplication app = RGTApplication.getApplicationInstance();
		BusinessProcessData businessProcessData = null;
		for(BusinessProcessData businessProcess: app.getBusinessProcesses()) {
			if(businessProcessId.equals(businessProcess.getBusinessProcessId())) {
				businessProcessData = businessProcess;
				break;
			}
		}
		return businessProcessData;
	}
	
	/* Gets the step data by using stepId if it exists. Otherwise returns null */
	public StepData getStep(String stepId) {
		RGTApplication app = RGTApplication.getApplicationInstance();
		StepData stepData = null;
		for(StepData step: app.getSteps()) {
			if(stepId.equals(step.getStepId())) {
				stepData = step;
				break;
			}
		}
		return stepData;
	}
	
	/* Gets the action data by using actionId if it exists. Otherwise returns null */
	public ActionData getAction(String actionId) {
		RGTApplication app = RGTApplication.getApplicationInstance();
		ActionData actionData = null;
		for(ActionData action: app.getActions()) {
			if(actionId.equals(action.getActionId())) {
				actionData = action;
				break;
			}
		}
		return actionData;
	}
	
	/* Adds the new business process provided by the user to the existing business process list */
	public void addBusinessProcess(final BusinessProcessData newBusinessProcess) {
		RGTApplication app = RGTApplication.getApplicationInstance();
		List<BusinessProcessData> existingBusinessProcesses = app.getBusinessProcesses();
		if(null == existingBusinessProcesses) {
			existingBusinessProcesses = new ArrayList<BusinessProcessData>();
		}
		existingBusinessProcesses.add(newBusinessProcess);
		app.setBusinessProcesses(existingBusinessProcesses);
	}
	
	/* Adds the new step provided by the user to the existing step list */
	public void addStep(final StepData newStep) {
		RGTApplication app = RGTApplication.getApplicationInstance();
		List<StepData> existingSteps = app.getSteps();
		if(null == existingSteps) {
			existingSteps = new ArrayList<StepData>();
		}
		existingSteps.add(newStep);
		
		app.setSteps(existingSteps);
	}

	/* Adds the new action provided by the user to the existing action list */
	public void addAction(final ActionData newAction) {
		RGTApplication app = RGTApplication.getApplicationInstance();
		List<ActionData> existingActions = app.getActions();
		if(null == existingActions) {
			existingActions = new ArrayList<ActionData>();
		}
		existingActions.add(newAction);
		
		app.setActions(existingActions);
	}

	/* Returns all the business processes in the application */
	public List<BusinessProcessData> getAllBusinessProcesses() {
		return RGTApplication.getApplicationInstance().getBusinessProcesses();
	}

	/* Returns all the steps for a given business process */
	public List<StepData> getAllSteps(String businessProcessId) {
		List<StepData> steps = RGTApplication.getApplicationInstance().getSteps();
		List<StepData> stepsForBusinessProcess = new ArrayList<StepData>();
		
		for(StepData step: steps) {
			if(step.getBusinessProcessId().equals(businessProcessId)) {
				stepsForBusinessProcess.add(step);
			}
		}
		return stepsForBusinessProcess;
	}
	
	/* Returns all the actions for a given step */
	public List<ActionData> getAllActions(String stepId) {
		List<ActionData> actions = RGTApplication.getApplicationInstance().getActions();
		List<ActionData> actionsForStep = new ArrayList<ActionData>();
		
		for(ActionData action: actions) {
			if(action.getStepId().equals(stepId)) {
				actionsForStep.add(action);
			}
		}
		return actionsForStep;
	}

	public List<StepData> getAllSteps() {
		return RGTApplication.getApplicationInstance().getSteps();
	}
}
