import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

class ColorPalette extends JPanel implements ActionListener {
	CreateDesign app;
	Color[][] palette = new Color[10][20];
	// JButton[][] paletteButtons = new JButton[20][10];
	JPanel[][] palettePanel = new JPanel[10][20];

	public ColorPalette(final CreateDesign app) {
		setLayout(new GridLayout(10, 20));
		this.app = app;
		for (int count = 0; count < palette.length; count++) {
			for (int count1 = 0; count1 < palette[count].length; count1++) {
				if (count1 > 10) {
					palette[count][count1] = new Color((20 - count1) / 2 * 25,
							(count) * 25, (count1 / 2) * 25);// RGB
				} else {
					palette[count][count1] = new Color((20 - count1) / 2 * 25,
							count * 25, 0);// RGB
				}
				// System.out.print(palette[count][count1]);
				/*
				 * paletteButtons[count][count1] = new JButton();
				 * paletteButtons[
				 * count][count1].setBackground(palette[count][count1]);
				 * System.out.print(count + " " + count1 + " ");
				 * paletteButtons[count][count1].addActionListener(this);
				 * add(paletteButtons[count][count1]);
				 */
				palettePanel[count][count1] = new JPanel();
				palettePanel[count][count1]
						.setBackground(palette[count][count1]);
				palettePanel[count][count1]
						.addMouseListener(new MouseListener() {
							@Override
							public void mouseClicked(MouseEvent e) {
								app.getSidePanel().sliders.setColor(((JPanel) e
										.getSource()).getBackground());
							}

							@Override
							public void mouseEntered(MouseEvent e) {

							}

							@Override
							public void mouseExited(MouseEvent e) {

							}

							@Override
							public void mousePressed(MouseEvent e) {

							}

							@Override
							public void mouseReleased(MouseEvent e) {

							}

						});
				add(palettePanel[count][count1]);
			}
			// System.out.println();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// System.out.println(e);
		app.getSidePanel().sliders.setColor(((JPanel) e.getSource())
				.getBackground());
	}
}
