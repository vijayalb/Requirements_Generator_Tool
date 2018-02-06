package rgt.views.panels;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.TextArea;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import rgt.constants.RGTConstants;
import rgt.controllers.GenerateController;

public class EditOutputPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	public EditOutputPanel() {
		super();
		initializeOutputPanel();
	}

	private void initializeOutputPanel() {
		Dimension dim = getPreferredSize();
		dim.width = RGTConstants.EDIT_FRAME_OUTPUT_PANEL_WIDTH;
		setPreferredSize(dim);
		
		setBorder(BorderFactory.createTitledBorder(RGTConstants.EDIT_FRAME_OUTPUT_PANEL));
		
		// Set Layout
		setLayout(new BorderLayout());
		
		// Create components
		TextArea outputArea = new TextArea();
		outputArea.setEditable(false);
		outputArea.setText(new GenerateController().getOutputContent().toString());
		// Add components to the container
		add(outputArea, BorderLayout.CENTER);
	}
	
}
