package rgt.views.panels;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.UUID;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import rgt.constants.RGTButtons;
import rgt.constants.RGTConstants;
import rgt.constants.RGTEntities;
import rgt.controllers.EditController;
import rgt.data.ActionData;
import rgt.data.BusinessProcessData;
import rgt.data.StepData;
import rgt.views.RToolView;
import rgt.views.dialog.AddDialog;

public class AddPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JButton cancelButton;
	
	public AddPanel(String entity) {
		super();
		setLayout(new GridBagLayout());
		repaint();
		if(RGTEntities.BUSINESS_PROCESS.getEntity().equals(entity)) {
			initializeBusinessProcessTab();
		} else if(RGTEntities.STEP.getEntity().equals(entity)) {
			initializeStepTab();
		} else if(RGTEntities.ACTION.getEntity().equals(entity)) {
			initializeActionTab();
		}
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Container container = (Container)e.getSource();
				JDialog parentDialog = (JDialog) SwingUtilities.getWindowAncestor(container);
				parentDialog.dispose();
			}
		});
	}

	private void initializeActionTab() {
		setBorder(BorderFactory.createTitledBorder(RGTConstants.ADD_ACTION_DIALOG));
		
		GridBagConstraints constraints = new GridBagConstraints();

		JLabel verbNounLabel = new JLabel(RGTConstants.LABEL_VERB_NOUN);
		JTextField verbNounText = new JTextField(RGTConstants.VERB_NOUN_INPUT_SIZE);
		JLabel actionSequenceLabel = new JLabel(RGTConstants.LABEL_ACTION_SEQUENCE);
		JTextField actionSeqText = new JTextField(RGTConstants.ACTION_SEQUENCE_NUM_INPUT_SIZE);
		JLabel stepsComboLabel = new JLabel(RGTConstants.LABEL_COMBO_SELECT_STEPS);
		JComboBox<StepData> stepComboBox = new JComboBox<StepData>();
		
		EditController editCtrl = new EditController();
		List<StepData> steps = editCtrl.getAllSteps();
        
        for(StepData item: steps) {
        	// To display the verb-noun phrase in the combobox override the toString method in StepData  
        	stepComboBox.addItem(item);
        }
        
		JButton addButton = new JButton(RGTButtons.ADD.getButton());
		cancelButton = new JButton(RGTButtons.CANCEL.getButton());
		
		constraints.weightx = 0.5;
		constraints.weighty = 0.5;
		constraints.insets = new Insets(2,2,2,2);
		
		// First Column
		constraints.anchor = GridBagConstraints.LINE_END;
		
		constraints.gridx = 0;
		constraints.gridy = 0;
		add(verbNounLabel,constraints);

		constraints.gridx = 0;
		constraints.gridy = 1;
		add(actionSequenceLabel,constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 2;
		add(stepsComboLabel,constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 3;
		add(addButton,constraints);
		
		// Second Column
		constraints.anchor = GridBagConstraints.LINE_START;
		
		constraints.gridx = 1;
		constraints.gridy = 0;
		add(verbNounText,constraints);

		constraints.gridx = 1;
		constraints.gridy = 1;
		add(actionSeqText,constraints);

		constraints.gridx = 1;
		constraints.gridy = 2;
		add(stepComboBox,constraints);
		
		constraints.gridx = 1;
		constraints.gridy = 3;
		add(cancelButton,constraints);
		
		addButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if(null == verbNounText.getText() || verbNounText.getText().equals("")) {
						JOptionPane.showMessageDialog(null, RGTConstants.VALIDATION_MESSAGE_EMPTY_VERB_NOUN, RGTConstants.ERROR_TITLE, JOptionPane.ERROR_MESSAGE , null);
					}
					else {
						addAction();	
						showSuccessDialog(e);
					}
					
				}
				catch(NumberFormatException nfe) {
					JOptionPane.showMessageDialog(null, RGTConstants.VALIDATION_MESSAGE_SEQ_NUMBER, RGTConstants.ERROR_TITLE, JOptionPane.ERROR_MESSAGE , null);
				}
			}

			private void showSuccessDialog(ActionEvent e) {
				Container container = (Container)e.getSource();
				JDialog addDialogBox = (JDialog) SwingUtilities.getWindowAncestor(container);
				int dialogResult = JOptionPane.showConfirmDialog(addDialogBox, RGTConstants.MESSAGE_ACTION_ADD, RGTConstants.MESSAGE_ADD_TITLE, JOptionPane.YES_NO_OPTION);
				addDialogBox.dispose();	
				if(dialogResult == JOptionPane.YES_OPTION){
					refreshAddDialog(addDialogBox, RGTConstants.ACTION_TAB_INDEX);
				}
			}
			
			public void addAction() throws NumberFormatException {
				ActionData action = new ActionData();
				action.setActionId(UUID.randomUUID().toString());
				action.setActionSeqNumber(Integer.parseInt(actionSeqText.getText()));
				action.setStepId(stepComboBox.getSelectedItem().toString());
				action.setVerbNounPhrase(verbNounText.getText());
				
				EditController editController = new EditController();
				editController.addAction(action);
			}
		});
	}

	private void initializeStepTab() {
		setBorder(BorderFactory.createTitledBorder(RGTConstants.ADD_STEP_DIALOG));
		
		GridBagConstraints constraints = new GridBagConstraints();

		JLabel verbNounLabel = new JLabel(RGTConstants.LABEL_VERB_NOUN);
		JTextField verbNounText = new JTextField(RGTConstants.VERB_NOUN_INPUT_SIZE);
		JLabel stepSequenceLabel = new JLabel(RGTConstants.LABEL_STEP_SEQUENCE);
		JTextField stepSeqText = new JTextField(RGTConstants.STEP_SEQUENCE_NUM_INPUT_SIZE);
		JLabel businessProcessComboLabel = new JLabel(RGTConstants.LABEL_COMBO_SELECT_STEPS);
		JComboBox<BusinessProcessData> businessProcessesComboBox = new JComboBox<BusinessProcessData>();
		
		EditController editCtrl = new EditController();
		List<BusinessProcessData> businessProcesses = editCtrl.getAllBusinessProcesses();
        
        for(BusinessProcessData item: businessProcesses) {
        	// To display the verb-noun phrase in the combobox override the toString method in BusinessProcessData  
        	businessProcessesComboBox.addItem(item);
        }
		
		JButton addButton = new JButton(RGTButtons.ADD.getButton());
		cancelButton = new JButton(RGTButtons.CANCEL.getButton());
		
		constraints.weightx = 0.5;
		constraints.weighty = 0.5;
		constraints.insets = new Insets(2,2,2,2);
		
		// First Column
		constraints.anchor = GridBagConstraints.LINE_END;
		
		constraints.gridx = 0;
		constraints.gridy = 0;
		add(verbNounLabel,constraints);

		constraints.gridx = 0;
		constraints.gridy = 1;
		add(stepSequenceLabel,constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 2;
		add(businessProcessComboLabel,constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 3;
		add(addButton,constraints);
		
		// Second Column
		constraints.anchor = GridBagConstraints.LINE_START;
		
		constraints.gridx = 1;
		constraints.gridy = 0;
		add(verbNounText,constraints);

		constraints.gridx = 1;
		constraints.gridy = 1;
		add(stepSeqText,constraints);

		constraints.gridx = 1;
		constraints.gridy = 2;
		add(businessProcessesComboBox,constraints);

		constraints.gridx = 1;
		constraints.gridy = 3;
		add(cancelButton,constraints);
		
		addButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if(null == verbNounText.getText() || verbNounText.getText().equals("")) {
						JOptionPane.showMessageDialog(null, RGTConstants.VALIDATION_MESSAGE_EMPTY_VERB_NOUN, RGTConstants.ERROR_TITLE, JOptionPane.ERROR_MESSAGE , null);
					}
					else {
						addStep();	
						showSuccessDialog(e);
					}
				}
				catch(NumberFormatException nfe) {
					JOptionPane.showMessageDialog(null, RGTConstants.VALIDATION_MESSAGE_SEQ_NUMBER, RGTConstants.ERROR_TITLE, JOptionPane.ERROR_MESSAGE , null);
				}
			}

			private void showSuccessDialog(ActionEvent e) {
				Container container = (Container)e.getSource();
				JDialog addDialogBox = (JDialog) SwingUtilities.getWindowAncestor(container);
				int dialogResult = JOptionPane.showConfirmDialog(addDialogBox, RGTConstants.MESSAGE_STEP_ADD, RGTConstants.MESSAGE_ADD_TITLE, JOptionPane.YES_NO_OPTION);
				addDialogBox.dispose();	
				if(dialogResult == JOptionPane.YES_OPTION){
					refreshAddDialog(addDialogBox, RGTConstants.STEP_TAB_INDEX);
				}
			}
			
			private void addStep() throws NumberFormatException {
				StepData step = new StepData();
				step.setBusinessProcessId(businessProcessesComboBox.getSelectedItem().toString());
				step.setStepId(UUID.randomUUID().toString());
				step.setStepSeqNumber(Integer.parseInt(stepSeqText.getText()));
				step.setVerbNounPhrase(verbNounText.getText());

				EditController editController = new EditController();
				editController.addStep(step);
			}
		});
	}

	private void initializeBusinessProcessTab() {
		setBorder(BorderFactory.createTitledBorder(RGTConstants.ADD_BUSINESS_PROCESS_DIALOG));
		
		GridBagConstraints constraints = new GridBagConstraints();

		JLabel verbNounLabel = new JLabel(RGTConstants.LABEL_VERB_NOUN);
		JTextField verbNounText = new JTextField(RGTConstants.VERB_NOUN_INPUT_SIZE);
		JLabel priorityLabel = new JLabel(RGTConstants.LABEL_BUSINESS_PROCESS_PRIORITY);
		JComboBox<String> priorityCombo = new JComboBox<String>(RGTConstants.PRIORITY_VALUES); 
		JButton addButton = new JButton(RGTButtons.ADD.getButton());
		cancelButton = new JButton(RGTButtons.CANCEL.getButton());
		
		constraints.weightx = 0.5;
		constraints.weighty = 0.5;
		constraints.insets = new Insets(2,2,2,2);
		
		// First Column
		constraints.anchor = GridBagConstraints.LINE_END;
		
		constraints.gridx = 0;
		constraints.gridy = 0;
		add(verbNounLabel,constraints);

		constraints.gridx = 0;
		constraints.gridy = 1;
		add(priorityLabel,constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 2;
		add(addButton,constraints);
		
		// Second Column
		constraints.anchor = GridBagConstraints.LINE_START;
		
		constraints.gridx = 1;
		constraints.gridy = 0;
		add(verbNounText,constraints);

		constraints.gridx = 1;
		constraints.gridy = 1;
		add(priorityCombo,constraints);

		constraints.gridx = 1;
		constraints.gridy = 2;
		add(cancelButton,constraints);
		
		addButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(null == verbNounText.getText() || verbNounText.getText().equals("")) {
					JOptionPane.showMessageDialog(null, RGTConstants.VALIDATION_MESSAGE_EMPTY_VERB_NOUN, RGTConstants.ERROR_TITLE, JOptionPane.ERROR_MESSAGE , null);
				} else {
					addBusinessProcess();
					showSuccessDialog(e);
				}
			}

			private void showSuccessDialog(ActionEvent e) {
				Container container = (Container)e.getSource();
				JDialog addDialogBox = (JDialog) SwingUtilities.getWindowAncestor(container);
				
				int dialogResult = JOptionPane.showConfirmDialog(addDialogBox, RGTConstants.MESSAGE_BUSINESS_PROCESS_ADD, RGTConstants.MESSAGE_ADD_TITLE, JOptionPane.YES_NO_OPTION);
				addDialogBox.dispose();	
				if(dialogResult == JOptionPane.YES_OPTION){
					refreshAddDialog(addDialogBox, RGTConstants.BUSINESS_PROCESS_TAB_INDEX);
				}
			}
			private void addBusinessProcess() {
				BusinessProcessData businessProcess = new BusinessProcessData();
				businessProcess.setBusinessProcessId(UUID.randomUUID().toString());
				businessProcess.setVerbNounPhrase(verbNounText.getText());
				businessProcess.setPriority(Integer.parseInt(priorityCombo.getSelectedItem().toString()));
				
				EditController editController = new EditController();
				editController.addBusinessProcess(businessProcess);
			}
		});
	}

	public void refreshAddDialog(JDialog addDialogBox, int selectedTabIndex) {
		AddDialog addDialog = new AddDialog((RToolView)addDialogBox.getParent());
		addDialog.getTabbedPane().setSelectedIndex(selectedTabIndex);
		addDialog.setTitle(RGTConstants.ADD_DIALOG_BOX_TITLE);
		addDialog.setSize(RGTConstants.DIALOG_WIDTH, RGTConstants.DIALOG_HEIGHT);
		addDialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		addDialog.setLocation(dimension.width/2-addDialog.getSize().width/2, dimension.height/2-addDialog.getSize().height/2);
		addDialog.setVisible(true);
	}

}
