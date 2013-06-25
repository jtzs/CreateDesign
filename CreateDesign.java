/*
 * Program written by Joshua Tan 
 * Original Purpose was a Clothes Designing program for NTU CIDP
 * After the course was completed, I took it upon my self to 
 * further develop the program with the intention of furthering
 * my skills in programming.
 */

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JApplet;
import javax.swing.JFrame;

//import com.mysql.jdbc.Driver;

public class CreateDesign extends JApplet {
	private SidePanel sidePanel;
	private DrawingPad MainPanel;

	public CreateDesign() {
		// GridBagConstraints c = new GridBagConstraints();
		// getContentPane().setLayout(new GridBagLayout());
		Container container = getContentPane();
		container.setLayout(new BorderLayout());
		sidePanel = new SidePanel(this);
		MainPanel = new DrawingPad(this);

		// container.add(new JLabel("West",JLabel.CENTER),BorderLayout.WEST);
		// container.add(new JLabel("North",JLabel.CENTER),BorderLayout.NORTH);
		// container.add(new JLabel("South",JLabel.CENTER),BorderLayout.SOUTH);
		// container.add(MainPanel,BorderLayout.CENTER);
		container.add(sidePanel, BorderLayout.EAST);
		container.add(MainPanel, BorderLayout.CENTER);
		// c.fill = GridBagConstraints.BOTH;
		// c.anchor = GridBagConstraints.CENTER;
		// getContentPane().add(MainPanel,c);
		// c.fill = GridBagConstraints.BOTH;
		// c.anchor = GridBagConstraints.EAST;
		// getContentPane().add(SidePanel,c);
		container.addComponentListener(new ComponentListener(){
			@Override
			public void componentResized(ComponentEvent e) {
				// TODO Auto-generated method stub
				//prints height and width upon resizing
				//System.out.println("The Height is " + e.getComponent().getHeight() + " and the width is " + e.getComponent().getWidth());
			}

			@Override
			public void componentMoved(ComponentEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void componentShown(ComponentEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void componentHidden(ComponentEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		super.stop();
		destroy();
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
		System.out.println("NOOOOOOOOOOOOOOOOOOOOOOooooooooooooooooooooooooooooo");
		sidePanel = null;
		MainPanel = null;
		System.gc();
	}

	@Override
	public void init() {
		super.init();
		//CreateDesign Applet = new CreateDesign();
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		// frame.setSize(Toolkit.getDefaultToolkit().getScreenSize().width/2,Toolkit.getDefaultToolkit().getScreenSize().height/2
		// );
		// frame.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width/4,
		// Toolkit.getDefaultToolkit().getScreenSize().height/4);
		frame.setSize(840,750);
		frame.setLocation(0, 0);
		frame.setVisible(true);
		CreateDesign Applet = new CreateDesign();
		frame.getContentPane().add(Applet);
		Applet.init();
	}

	public SidePanel getSidePanel() {
		return sidePanel;
	}

	public DrawingPad getMainPanel() {
		return MainPanel;
	}
}


