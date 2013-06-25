import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import javax.imageio.ImageIO;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

/*
 * Creates a button panel
 * Where the buttons are as follows
 * #0	get colour
 * #1	load template(not in use)
 * #2	clear page
 * #3	save image
 * #4	erase
 * #5	load template(Colour inclusive)
 * #6	Change brush
 * #7	Draw
 * #8	previous layer
 * #9	next layer
 */
class ButtonPanel extends JPanel implements ActionListener {
	protected CreateDesign app;
	JComboBox templateDropdown = new JComboBox();
	String[] templates = { "Round Neck", "Singlet", "34 Shirt","34 Shirt Type 2" };
	protected JButton[] button = new JButton[10];
	protected boolean[] buttonV = new boolean[10];
	protected String template;
	protected TextField picName;
	protected String pictureName;
	protected JLabel layer;
	private Layer head = null;
	final Color selected = new Color(250, 200, 200);
	GridBagLayout GBLayout = new GridBagLayout();
	GridBagConstraints GBC = new GridBagConstraints();

	// Connection DBC;

	public ButtonPanel(CreateDesign app) {
		setLayout(GBLayout);
		GBC.gridwidth = GridBagConstraints.REMAINDER;
		GBC.fill = GridBagConstraints.HORIZONTAL;
		this.app = app;

		picName = new TextField("Default Picture Name");
		GBLayout.setConstraints(picName, GBC);
		add(picName);

		// Load a template
		templateDropdown = new JComboBox(templates);
		templateDropdown.setSelectedIndex(0);
		GBLayout.setConstraints(templateDropdown, GBC);
		add(templateDropdown);

		//#1
		button[1] = new JButton("Load Template");
		/*
		 * button[1] = new JButton("Load Template");
		 * button[1].addActionListener(this);
		 * GBLayout.setConstraints(button[1],GBC);
		 * add(button[1]);
		 */

		//#5
		button[5] = new JButton("Load Template");
		button[5].addActionListener(this);
		GBLayout.setConstraints(button[5], GBC);
		add(button[5]);

		layer = new JLabel("0"); //layer name
		GBC.gridwidth = 1;
		GBLayout.setConstraints(layer, GBC);
		add(layer);

		//#8
		button[8] = new JButton("Previous Layer");
		button[8].addActionListener(this);
		GBC.gridwidth = 1;
		GBLayout.setConstraints(button[8], GBC);
		add(button[8]);

		//#9
		button[9] = new JButton("Next Layer");
		button[9].addActionListener(this);
		GBC.gridwidth = GridBagConstraints.REMAINDER;
		GBLayout.setConstraints(button[9], GBC);
		add(button[9]);

		//#0
		// Get the Color from the DrawingPad
		button[0] = new JButton("Get Color");
		button[0].addActionListener(this);
		GBLayout.setConstraints(button[0], GBC);
		add(button[0]);

		//#2
		button[2] = new JButton("Clear Page");
		button[2].addActionListener(this);
		GBLayout.setConstraints(button[2], GBC);
		add(button[2]);

		//#3
		button[3] = new JButton("Save Design");
		button[3].addActionListener(this);
		GBLayout.setConstraints(button[3], GBC);
		add(button[3]);

		//#6
		button[6] = new JButton("Change Brush");
		button[6].addActionListener(this);
		GBLayout.setConstraints(button[6], GBC);
		add(button[6]);

		//#4
		button[4] = new JButton("Erase");
		button[4].addActionListener(this);
		GBLayout.setConstraints(button[4], GBC);
		add(button[4]);

		//#7
		button[7] = new JButton("Draw");
		button[7].addActionListener(this);
		GBLayout.setConstraints(button[7], GBC);
		add(button[7]);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
//#0
		if (e.getActionCommand() == "Get Color") {
			if (!buttonV[0]) {// if not selected
				for (int count = 0; count < buttonV.length; count++) {
					buttonV[count] = false;
					button[count].setBackground(null);
				}
				buttonV[0] = true;
				button[0].setBackground(selected);
			} else {
				for (int count = 0; count < buttonV.length; count++) {
					buttonV[count] = false;
					button[count].setBackground(null);
				}
			}
/*
//#1
		} else if (e.getActionCommand() == "Load Template") {
			for (int count = 0; count < buttonV.length; count++) {
				buttonV[count] = false;
				button[count].setBackground(null);
			}
			buttonV[1] = !buttonV[1];
			button[1].setBackground(selected);
			if (templateDropdown.getSelectedItem() == " ") {
				button[1].setBackground(null);
				buttonV[1] = !buttonV[1];
				return;
			} else if (templateDropdown.getSelectedItem() == "Round Neck") {
				template = "RoundNeckTemplate.png";
			} else if (templateDropdown.getSelectedItem() == "Singlet") {
				template = "SingletTemplate.png";
			} else if (templateDropdown.getSelectedItem() == "34 Shirt") {
				template = "34shirt.png";
			} else if (templateDropdown.getSelectedItem() == "34 Shirt Type 2") {
				template = "34shirt2.png";
			}
			try {
				URL url = new URL(app.getCodeBase(), template);
				app.getMainPanel().img = ImageIO.read(url);
				app.getMainPanel().repaint();
				button[1].setBackground(null);
				buttonV[1] = !buttonV[1];
			} catch (IOException exception) {
				System.out.println(exception);
			}
*/
		}
//#2
		else if (e.getActionCommand() == "Clear Page") {
			for (int count = 0; count < buttonV.length; count++) {
				if(count == 7)continue;//skip the draw button
				buttonV[count] = false;
				button[count].setBackground(null);
			}
			app.getMainPanel().clear = true;
			buttonV[2] = !buttonV[2];
			button[2].setBackground(selected);
			button[2].setBackground(null);
			buttonV[2] = !buttonV[2];
			app.getMainPanel().repaint();
		}
//#3
		else if (e.getActionCommand() == "Save Design") {
			for (int count = 0; count < buttonV.length; count++) {
				buttonV[count] = false;
				button[count].setBackground(null);
			}
			buttonV[3] = !buttonV[3];
			button[3].setBackground(selected);
			try {
				//For sending the image to a database
				/*
				Class.forName("com.mysql.jdbc.Driver");
				Connection DBC =getConnection();
				File outputfile = new File("products",app.getParameter("name")+""+Calendar.getInstance().getTimeInMillis()+".png");
				ImageIO.write(app.getMainPanel().bi, "png", outputfile);
				FileInputStream FIS = new FileInputStream(outputfile);

				PreparedStatement query = DBC.prepareStatement("INSERT INTO inspired_wear2.product	(itemName, itemDescription, quantity, picture, price, color, size, OwnerID, gender, fileName, filetype, fileSize, category) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)");
				query.setString(1,app.getParameter("name")+""+Calendar.getInstance().getTimeInMillis());
				query.setString(2,"Drawn using Create Design");
				query.setLong(3,0);
				query.setBinaryStream(4,(InputStream)FIS,(int)outputfile.length());
				query.setLong(5,15);
				query.setString(6,"White");
				query.setString(7,"Medium");
				query.setString(8,app.getParameter("name"));
				query.setString(9,"Unisex");
				query.setString(10,app.getParameter("name")+Calendar.getInstance().getTimeInMillis()+".png");
				query.setString(11,"image/png");
				query.setLong(12,(int)outputfile.length());
				query.setBoolean(13,true); //System.out.println(query);
				if(query.executeUpdate()>0){
					System.out.println("File saved successfully"); }else
						System.out.println("Sorry, database error");
				DBC.close();query.close();
				*/

				if (picName.getText().equals("Default Picture Name")) {
					pictureName = "God is Awesome";
				} else{
					pictureName = picName.getText();
				}
				File outputfile =  new File("products", pictureName + ""+ Calendar.getInstance().getTimeInMillis() + ".png");
				BufferedImage outputimage = new BufferedImage(app.getMainPanel().getWidth(), app.getMainPanel().getHeight(), BufferedImage.TYPE_INT_ARGB);
				/*do{
					outputimage.getGraphics().drawImage(head.getNode(),0,0,null);
					head = head.next();
				}while(head.hasNext());*/
				//outputGraphics
				//preparing image for printing
				//layering 1.0
				//outputimage.getGraphics().drawImage(app.getMainPanel().bi,0,0,null);
				//outputimage.getGraphics().drawImage(app.getMainPanel().layer.getNode(),0,0,null);
				//layering 1.1
				head = app.getMainPanel().layer.getHead();
				while(true){
					outputimage.getGraphics().drawImage(head.getNode(),0,0,null);
					System.out.println("Printing layer " + head.getLayerName());
					if(head.hasNext()){
						head = head.next();
					}
					else{
						break;
					}
				}
				//In the case where the products folder is not found
				try {
					ImageIO.write(outputimage, "png", outputfile);
				} catch(NullPointerException e1){
					System.out.println(0);
					e1.printStackTrace();
					try{
						System.out.println("2nd TRY!!");
						outputfile = new File(pictureName + ""+ Calendar.getInstance().getTimeInMillis() + ".png");
						ImageIO.write(outputimage, "png", outputfile);
					}catch(Exception e2){
						System.out.println("0.1");
						e2.printStackTrace(System.out);
					}
				} catch (FileNotFoundException e1) {
					System.out.println(1);
					e1.printStackTrace(System.out);
					try{
						outputfile = new File(pictureName + ""+ Calendar.getInstance().getTimeInMillis() + ".png");
						ImageIO.write(outputimage, "png", outputfile);
					}catch(Exception e2){
						System.out.println("1.1");
						e2.printStackTrace(System.out);
					}
				} catch(Exception e1){
					throw e1;
				}
			} catch (IOException exception) {
				exception.printStackTrace(System.out);
			} catch(Exception exception){
				exception.printStackTrace(System.out);
			}
/*
			//For sending the image to database
			catch (SQLException exception) {
				exception.printStackTrace(System.out);
			} catch (ClassNotFoundException exception) {
				exception.printStackTrace(System.out);
			}
*/
			finally{
				buttonV[3] = !buttonV[3];
				button[3].setBackground(null);
			}
		}
//#4
		else if (e.getActionCommand() == "Erase") {
			buttonV[4] = !buttonV[4];
			if (!buttonV[4]) {
				button[4].setBackground(null);
			} else {
				button[4].setBackground(selected);
				for (int count = 0; count < buttonV.length; count++) {
					if (count == 4)
						continue;
					buttonV[count] = false;
					button[count].setBackground(null);
				}
			}
		}
//#5
		else if (e.getActionCommand() == "Load Template") {
			if (templateDropdown.getSelectedItem() == " ") {
				button[1].setBackground(null);
				buttonV[1] = !buttonV[1];
				return;
			} else if (templateDropdown.getSelectedItem() == "Round Neck") {
				template = "RoundNeckTemplate.png";
			} else if (templateDropdown.getSelectedItem() == "Singlet") {
				template = "SingletTemplate.png";
			} else if (templateDropdown.getSelectedItem() == "34 Shirt") {
				template = "34shirt.png";
			} else if (templateDropdown.getSelectedItem() == "34 Shirt Type 2") {
				template = "34shirt2.png";
			}
			URL url;
			BufferedImage img;
			try {
				if(app.equals(new JApplet()) ){
					url = new URL(app.getCodeBase(), template);
					img = ImageIO.read(url);
				}
				else {
					img = ImageIO.read(new File(template));
				}
				for (int count = 0; count < img.getWidth(null); count++) {
					for (int count1 = 0; count1 < img.getHeight(null); count1++) {
						if (img.getRGB(count, count1) == Color.WHITE.getRGB()) {
							img.setRGB(count, count1,
									app.getSidePanel().sliders.getColor()
											.getRGB());
						}

					}
				}
				app.getMainPanel().img = img;
				app.getMainPanel().repaint();
			} catch (IOException exception) {
				exception.printStackTrace(System.out);
			} catch (NullPointerException exception){
				exception.printStackTrace(System.out);
			}
			for (int count = 0; count < buttonV.length; count++) {
				buttonV[count] = false;
				button[count].setBackground(null);
			}
			buttonV[5] = !buttonV[5];
			button[5].setBackground(selected);
			buttonV[5] = !buttonV[5];
			button[5].setBackground(null);
		}
//#6
		else if (e.getActionCommand() == "Change Brush") {
			for (int count = 0; count < buttonV.length; count++) {
				buttonV[count] = false;
				button[count].setBackground(null);
			}
			buttonV[6] = !buttonV[6];
			button[6].setBackground(selected);
			app.getMainPanel().brushToggle = !app.getMainPanel().brushToggle;
			buttonV[6] = !buttonV[6];
			button[6].setBackground(null);
		}
//#7
		else if (e.getActionCommand() == "Draw") {
			buttonV[7] = !buttonV[7];
			if (buttonV[7]) {
				for (int count = 0; count < buttonV.length; count++) {
					if (count == 7)
						continue;
					buttonV[count] = false;
					button[count].setBackground(null);
				}
				button[7].setBackground(selected);
			} else {
				for (int count = 0; count < buttonV.length; count++) {
					buttonV[count] = false;
					button[count].setBackground(null);
				}
			}
		} 
//#9
		else if(e.getActionCommand() == "Next Layer"){
			System.out.print("From layer " + app.getMainPanel().layer.getLayerName());
			//app.getMainPanel().layer = app.getMainPanel().layer.next();
			app.getMainPanel().nextLayer();
			System.out.println(" to layer " + app.getMainPanel().layer.getLayerName());
			layer.setText(app.getMainPanel().layer.getLayerName()+"");
		} 
//#8
		else if(e.getActionCommand() == "Previous Layer"){
			System.out.print("From layer " + app.getMainPanel().layer.getLayerName());
			//app.getMainPanel().layer = app.getMainPanel().layer.prev();
			app.getMainPanel().prevLayer();
			System.out.println(" to layer " + app.getMainPanel().layer.getLayerName());
			layer.setText(app.getMainPanel().layer.getLayerName()+"");
		} else {
		}
	}

/*
	//For sending the image to the database 
	 public Connection getConnection() throws SQLException {
		 Connection conn = null;
		 Properties connectionProps = new Properties(); connectionProps.put("user", "login"); 
		 connectionProps.put("password", "login");
		 connectionProps.put("database_name", "inspired_wear2");
		 connectionProps.put("driver","com.mysql.jdbc.Driver");
		 conn = DriverManager.getConnection("jdbc:mysql://172.21.151.62:3306/", connectionProps);
		 System.out.println("Connected to database");
		 return conn;
		 }
*/
}