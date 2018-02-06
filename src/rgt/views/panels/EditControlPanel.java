package rgt.views.panels;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import rgt.constants.RGTButtons;
import rgt.constants.RGTConstants;
import rgt.constants.RGTEntities;
import rgt.controllers.GenerateController;
import rgt.views.EditView;

public class EditControlPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	public EditControlPanel() {
		super();
		initializeControlPanel();
	}

	private void initializeControlPanel() {
		Dimension dim = getPreferredSize();
		dim.width = RGTConstants.EDIT_FRAME_CONTROL_PANEL_WIDTH;
		setPreferredSize(dim);
		
		setBorder(BorderFactory.createTitledBorder(RGTConstants.EDIT_FRAME_EDIT_OPTIONS));
		
		/** Set Layout **/
		setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		
		/**  Create components START **/
		// 1. Combobox for Add
        JComboBox<String> addCombo = new JComboBox<String>(RGTConstants.DROPDOWN_VALUES);
        
        // 2. Add Button
        JButton addButton = new JButton(RGTButtons.ADD.getButton());
        addButton.setToolTipText(RGTConstants.TOOL_TIP_ADD_BUTTON);
        addButton.addActionListener(new ActionListener() {
			//How to get the form data on event fire
			@Override
			public void actionPerformed(ActionEvent e) {
				if(addCombo.getSelectedItem().toString().equalsIgnoreCase(RGTEntities.BUSINESS_PROCESS.getEntity())){
					SwingUtilities.invokeLater(new Runnable(){

						@Override
						public void run() {
							/*EditView editFrame = new EditView();
							editFrame.setTitle(RGTConstants.EDIT_FRAME_TITLE);
							editFrame.setSize(RGTConstants.EDIT_FRAME_WIDTH, RGTConstants.EDIT_FRAME_HEIGHT);
							editFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
							editFrame.setResizable(false);
							editFrame.setVisible(true);
							
							GenerateController ctrl = new GenerateController();
							ctrl.generateRequirementsTestData();*/
						}
						
					});
				} else if(addCombo.getSelectedItem().toString().equalsIgnoreCase(RGTEntities.STEP.getEntity())){
					
				} else if(addCombo.getSelectedItem().toString().equalsIgnoreCase(RGTEntities.ACTION.getEntity())){
					
				}
				
			}
		});
        
        // 3. Combobox for Update
        JComboBox<String> updateCombo = new JComboBox<String>(RGTConstants.DROPDOWN_VALUES);
        
        // 4. Update Button
        JButton updateButton = new JButton(RGTButtons.UPDATE.getButton());
        updateButton.setToolTipText(RGTConstants.TOOL_TIP_UPDATE_BUTTON);
        
        // 5. Combobox for Delete
        JComboBox<String> deleteCombo = new JComboBox<String>(RGTConstants.DROPDOWN_VALUES);
        
        // 6. Delete Button
        JButton deleteButton = new JButton(RGTButtons.DELETE.getButton());
        deleteButton.setToolTipText(RGTConstants.TOOL_TIP_DELETE_BUTTON);
        
        // 7. Delete Button
        JButton exportButton = new JButton(RGTButtons.EXPORT.getButton());
        exportButton.setToolTipText(RGTConstants.TOOL_TIP_EXPORT_BUTTON);
        
		/** Add components to the container **/
		constraints.gridx = 0;
		constraints.gridy = 0;
		add(addCombo,constraints);

		constraints.gridx = 0;
		constraints.gridy = 1;
		add(addButton,constraints);

		constraints.gridx = 0;
		constraints.gridy = 2;
		add(updateCombo,constraints);

		constraints.gridx = 0;
		constraints.gridy = 3;
		add(updateButton,constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 4;
		add(deleteCombo,constraints);

		constraints.gridx = 0;
		constraints.gridy = 5;
		add(deleteButton,constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 6;
		add(exportButton,constraints);
	}
	
}
