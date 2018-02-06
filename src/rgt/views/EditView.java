package rgt.views;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JFrame;

import rgt.views.panels.EditControlPanel;
import rgt.views.panels.EditOutputPanel;

/* This view will be generated asynchronously on the click of generate button */
public class EditView extends JFrame{
	private static final long serialVersionUID = 1L;

	public EditView() {
		super();
		initializeEditView();
	}

	private void initializeEditView() {
		// Set Layout
		setLayout(new BorderLayout()); 

		// Create Components (in this case 2 panels) 
		EditOutputPanel outputPanel = new EditOutputPanel();
		EditControlPanel controlPanel = new EditControlPanel();
		
		// Add Components to the container
		Container container = getContentPane();
		container.add(outputPanel, BorderLayout.WEST);
		container.add(controlPanel, BorderLayout.EAST);
		
	}
}
