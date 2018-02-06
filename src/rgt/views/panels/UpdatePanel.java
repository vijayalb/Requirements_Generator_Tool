package rgt.views.panels;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

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
import rgt.controllers.EditController;
import rgt.data.BusinessProcessData;
import rgt.views.RToolView;
import rgt.views.dialog.UpdateDialog;

public class UpdatePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JLabel updateBusinessProcessLabel;
	private JComboBox<BusinessProcessData> updateBusinessProcessCombo;
	private JLabel verbNounLabel;
	private JTextField verbNounText;
	private JLabel priorityLabel;
	private JComboBox<String> priorityCombo;
	private JButton updateButton;
	private JButton cancelButton;
	
	public UpdatePanel() {
		super();
		initializeUpdatePanel();
	}

	private void initializeUpdatePanel() {	
		setBorder(BorderFactory.createTitledBorder(RGTConstants.UPDATE_DIALOG));
		setLayout(new GridBagLayout());
		
		initializeCombo();
        
        updateBusinessProcessCombo.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent event) {
				if (event.getStateChange() == ItemEvent.SELECTED) {
					BusinessProcessData selectedBusinessProcess = (BusinessProcessData)event.getItem();
					EditController editCtrl = new EditController();
					BusinessProcessData businessProcess = editCtrl.getBusinessProcess(selectedBusinessProcess.getBusinessProcessId());
					removeOldComponents();
					displaySelectedBusinessProcess(businessProcess);
					revalidate();
				}
			}

			private void removeOldComponents() {
				if(null != verbNounLabel) {
					remove(verbNounLabel);
				}
				if(null != verbNounText) {
					remove(verbNounText);
				}
				if(null != priorityLabel) {
					remove(priorityLabel);
				}
				if(null != priorityCombo) {
					remove(priorityCombo);
				}
				if(null != updateButton) {
					remove(updateButton);
				}
				if(null != cancelButton) {
					remove(cancelButton);
				}
			}
		}); 
	}

	private void initializeCombo() {
		EditController editCtrl = new EditController();
		List<BusinessProcessData> businessProcesses = editCtrl.getAllBusinessProcesses();
		
		updateBusinessProcessLabel = new JLabel(RGTConstants.LABEL_COMBO_SELECT_BP);
        updateBusinessProcessCombo = new JComboBox<BusinessProcessData>();
        
        for(BusinessProcessData item: businessProcesses) {
        	// To display the verb-noun phrase in the combobox override the toString method in BusinessProcessData  
        	updateBusinessProcessCombo.addItem(item);
        }
        
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.weightx = 0.5;
		constraints.weighty = 0.5;
		constraints.insets = new Insets(2,2,2,2);
		
		// First Column
		constraints.anchor = GridBagConstraints.LINE_END;
        constraints.gridx = 0;
        constraints.gridy = 0;
        add(updateBusinessProcessLabel, constraints);

        constraints.anchor = GridBagConstraints.LINE_START;
        constraints.gridx = 1;
        constraints.gridy = 0;
        add(updateBusinessProcessCombo, constraints);
	}

	protected void displaySelectedBusinessProcess(BusinessProcessData businessProcess) {
		GridBagConstraints constraints = new GridBagConstraints();

		verbNounLabel = new JLabel(RGTConstants.LABEL_VERB_NOUN);
		verbNounText = new JTextField(businessProcess.getVerbNounPhrase(), RGTConstants.VERB_NOUN_INPUT_SIZE);
		priorityLabel = new JLabel(RGTConstants.LABEL_BUSINESS_PROCESS_PRIORITY);
		priorityCombo = new JComboBox<String>(RGTConstants.PRIORITY_VALUES); 
		priorityCombo.setSelectedItem(Integer.toString(businessProcess.getPriority()));
		updateButton = new JButton(RGTButtons.UPDATE.getButton());
		cancelButton = new JButton(RGTButtons.CANCEL.getButton());

		constraints.weightx = 0.5;
		constraints.weighty = 0.5;
		constraints.insets = new Insets(2,2,2,2);
		
		// First Column
		constraints.anchor = GridBagConstraints.LINE_END;
		
		constraints.gridx = 0;
		constraints.gridy = 3;
		add(verbNounLabel,constraints);

		constraints.gridx = 0;
		constraints.gridy = 4;
		add(priorityLabel,constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 5;
		add(updateButton,constraints);
		
		// Second Column
		constraints.anchor = GridBagConstraints.LINE_START;
		
		constraints.gridx = 1;
		constraints.gridy = 3;
		add(verbNounText,constraints);

		constraints.gridx = 1;
		constraints.gridy = 4;
		add(priorityCombo,constraints);

		constraints.gridx = 1;
		constraints.gridy = 5;
		add(cancelButton,constraints);

		updateButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				updateBusinessProcess(businessProcess);
				showSuccessDialog(e);
			}

			private void showSuccessDialog(ActionEvent e) {
				Container container = (Container)e.getSource();
				JDialog updateDialogBox = (JDialog) SwingUtilities.getWindowAncestor(container);
				
				int dialogResult = JOptionPane.showConfirmDialog(updateDialogBox, RGTConstants.MESSAGE_BUSINESS_PROCESS_UPDATE, RGTConstants.MESSAGE_UPDATE_TITLE, JOptionPane.YES_NO_OPTION);
				updateDialogBox.dispose();
				if(dialogResult == JOptionPane.YES_OPTION){
					UpdateDialog updateDialog = new UpdateDialog((RToolView)updateDialogBox.getParent());
					updateDialog.setTitle(RGTConstants.UPDATE_DIALOG_BOX_TITLE);
					updateDialog.setSize(RGTConstants.DIALOG_WIDTH, RGTConstants.DIALOG_HEIGHT);
					Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
					updateDialog.setLocation(dimension.width/2-updateDialog.getSize().width/2, dimension.height/2-updateDialog.getSize().height/2);
					updateDialog.setVisible(true);
					updateDialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				}
			}

			private void updateBusinessProcess(BusinessProcessData businessProcess) {
				String updatedVNPhrase = verbNounText.getText();
				int priority = Integer.parseInt(priorityCombo.getSelectedItem().toString());
				
				BusinessProcessData updatedBusinessProcess = new BusinessProcessData();
				updatedBusinessProcess.setBusinessProcessId(businessProcess.getBusinessProcessId());
				updatedBusinessProcess.setVerbNounPhrase(updatedVNPhrase);
				updatedBusinessProcess.setPriority(priority);
				
				EditController editCtrl = new EditController();
				editCtrl.updateBusinessProcess(updatedBusinessProcess);
			}
		});
		
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Container container = (Container)e.getSource();
				JDialog parentDialog = (JDialog) SwingUtilities.getWindowAncestor(container);
				parentDialog.setVisible(false);
			}
		});
	}
}
