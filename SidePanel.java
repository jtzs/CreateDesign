import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

class SidePanel extends JPanel {
	ColorPalette palette;
	SliderPanel sliders;
	TextPanel text;
	ButtonPanel buttons;

	public SidePanel(CreateDesign app) {
		palette = new ColorPalette(app);
		sliders = new SliderPanel();
		text = new TextPanel(app);
		buttons = new ButtonPanel(app);
		GridBagLayout GBLayout = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		setLayout(GBLayout);
		c.anchor = GridBagConstraints.NORTH;
		c.gridheight = 8;
		c.gridwidth = GridBagConstraints.REMAINDER;
		GBLayout.setConstraints(sliders, c);
		add(sliders);
		c.gridheight = 4;
		GBLayout.setConstraints(palette, c);
		add(palette);
		c.gridheight = 2;
		GBLayout.setConstraints(text, c);
		add(text);
		c.gridheight = 4;
		GBLayout.setConstraints(sliders, c);
		add(buttons);
	}
}
