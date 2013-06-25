import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;


class TextPanel extends JPanel implements ActionListener {
	CreateDesign app;
	protected TextField text;
	JComboBox shapeList;
	JButton enter, useShape;
	boolean textToggle = false, shapeToggle = false;
	String[] shapeStrings = { "Square", "Circle", "Star", "Rectangle" };
	GridBagLayout GBLayout = new GridBagLayout();
	GridBagConstraints GBC = new GridBagConstraints();

	public TextPanel(CreateDesign app) {
		this.app = app;
		// setLayout(new GridLayout(2,1));
		setLayout(GBLayout);
		text = new TextField("Text to be Displayed");
		enter = new JButton("Click to input Text");
		enter.addActionListener(this);
		GBC.gridwidth = GridBagConstraints.REMAINDER;
		GBC.fill = GridBagConstraints.HORIZONTAL;
		GBLayout.setConstraints(text, GBC);
		add(text);
		GBLayout.setConstraints(enter, GBC);
		add(enter);

		// insert shapes
		shapeList = new JComboBox(shapeStrings);
		shapeList.setSelectedIndex(0);
		useShape = new JButton("Use Shape");
		useShape.addActionListener(this);
		GBC.gridwidth = 3;
		GBLayout.setConstraints(shapeList, GBC);
		add(shapeList);
		GBC.gridwidth = 2;
		GBLayout.setConstraints(useShape, GBC);
		add(useShape);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == "Click to input Text") {
			app.getMainPanel().text = !app.getMainPanel().text;
			textToggle = !textToggle;
			// System.out.println(app.getMainPanel().text + " " + textToggle);
			if (textToggle) {
				enter.setBackground(app.getSidePanel().buttons.selected);
			} else
				enter.setBackground(null);
		} else if (e.getActionCommand() == "Use Shape") {
			app.getMainPanel().shape = !app.getMainPanel().shape;
			shapeToggle = !shapeToggle;
			// System.out.println(app.getMainPanel().shape + " " + shapeToggle);
			if (shapeToggle) {
				useShape.setBackground(app.getSidePanel().buttons.selected);
			} else
				useShape.setBackground(null);
		}

	}

}