import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


class SliderPanel extends JPanel {
	private JSlider RedSlider;
	private JSlider BlueSlider;
	private JSlider GreenSlider;
	private JSlider CursorSlider;
	private JPanel currentbox;
	private GridBagLayout GBLayout = new GridBagLayout();
	private GridBagConstraints GBC = new GridBagConstraints();
	public int red = 0, blue = 0, green = 0, cursor = 10;

	public SliderPanel() {
		setLayout(GBLayout);
		currentbox = new JPanel();
		// currentbox.setPreferredSize(new
		// Dimension(getWidth(),getHeight()/10));
		GBC.gridheight = 1;
		GBC.gridwidth = GridBagConstraints.REMAINDER;
		currentbox.setBackground(new Color(red, green, blue));
		add(currentbox, GBC);
		GBC.gridheight = 7;
		GBC.gridwidth = 1;
		// SLIDERS
		RedSlider = new JSlider(SwingConstants.VERTICAL, 0, 255, 0);
		RedSlider.setMinorTickSpacing(5);
		RedSlider.setPaintTicks(true);
		RedSlider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent arg0) {
				red = ((JSlider) arg0.getSource()).getValue();
				// System.out.println(red + "\t" + green + "\t" + blue);
				currentbox.setBackground(getColor());
			}
		});
		add(RedSlider, GBC);
		BlueSlider = new JSlider(SwingConstants.VERTICAL, 0, 255, 0);
		BlueSlider.setMinorTickSpacing(5);
		BlueSlider.setPaintTicks(true);
		BlueSlider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent arg0) {
				blue = ((JSlider) arg0.getSource()).getValue();
				// System.out.println(red + "\t" + green + "\t" + blue);
				currentbox.setBackground(getColor());
			}
		});
		add(BlueSlider, GBC);
		GreenSlider = new JSlider(SwingConstants.VERTICAL, 0, 255, 0);
		GreenSlider.setMinorTickSpacing(5);
		GreenSlider.setPaintTicks(true);
		GreenSlider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent arg0) {
				green = ((JSlider) arg0.getSource()).getValue();
				// System.out.println(red + "\t" + green + "\t" + blue);
				currentbox.setBackground(getColor());
			}
		});
		add(GreenSlider, GBC);
		CursorSlider = new JSlider(SwingConstants.VERTICAL, 1, 20, 10);
		CursorSlider.setMinorTickSpacing(2);
		CursorSlider.setPaintTicks(true);
		CursorSlider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent arg0) {
				cursor = ((JSlider) arg0.getSource()).getValue();
			}
		});
		add(CursorSlider, GBC);
		// END OF SLIDERS

	}

	public void setColor(Color newColor) {
		// System.out.println("Color set");
		red = newColor.getRed();
		RedSlider.setValue(red);
		blue = newColor.getBlue();
		BlueSlider.setValue(blue);
		green = newColor.getGreen();
		GreenSlider.setValue(green);
	}

	public Color getColor() {
		return new Color(red, green, blue);
	}
}
