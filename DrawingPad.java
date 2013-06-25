import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

class DrawingPad extends JPanel implements MouseMotionListener,MouseListener{
	private CreateDesign app;
	private double MouseX,MouseY;
	//private double OldMX,OldMY;
	private int textX,textY;
	protected BufferedImage img;	// for templates
	protected BufferedImage bi;		// for save design
	Graphics bufferedGraphics;
	public boolean brushToggle = true;
	public boolean clear = true;
	public boolean shape = false;
	public boolean text = false,draw = false;
	public Layer layer,head;
	/*
	Toolkit toolkit = Toolkit.getDefaultToolkit();		//for cursor
	Image image;
	Point hotspot;
	Cursor eraser,pen;
	*/
	
	public DrawingPad(CreateDesign app){
		//System.out.println("DrawingPad constructor");
		this.app = app;
		//MouseY = MouseX = 0;Toolkit.createCustomCursor()
		//OldMX = OldMY = 0;
		this.setVisible(true);
		this.addMouseMotionListener(this);
		this.addMouseListener(this);
		//this.setBackground(Color.WHITE);
		//this.getGraphics().setColor(Color.WHITE);
		//this.getGraphics().fillRect(0,0,this.getWidth(),this.getHeight());
		/*
		try{
			image = toolkit.getImage("Eraser.png");
			hotspot = new Point(0,28);
			eraser = toolkit.createCustomCursor(image,hotspot,"Eraser");
			image = toolkit.getImage("Marker-icon.png");
			hotspot = new Point(0,27);
			pen = toolkit.createCustomCursor(image,hotspot,"pen");
		}catch(Exception e){
			pen = eraser = Cursor.getDefaultCursor();
		}*/
	}
	
	public void paintComponent(Graphics g){
		//Fancy pens/erasers
		/*if(app.getSidePanel().buttons.buttonV[4]){
			setCursor(eraser);
		}else if (app.getSidePanel().buttons.buttonV[7]){
			setCursor(pen);
		}
		else{
			setCursor(Cursor.getDefaultCursor());
		}
		*/
		
		if(clear){
			//layering 1.0
			/*
			if(bi==null||true){
				bi = new BufferedImage(getWidth(),getHeight(),BufferedImage.TYPE_INT_ARGB);
				bufferedGraphics = bi.createGraphics();
			}*/
			if(layer== null){
				layer = new Layer(getWidth(), getHeight());
				bufferedGraphics = layer.getGraphics();
			}
			else {
				layer.clear();
				bufferedGraphics = layer.getGraphics();
			}
			super.paintComponent(g);
			clear = !clear;
			return;
		}
		super.paintComponent(g);		//clears the screen
		//layering 1.0
		//g.drawImage(bi,0,0,null);		//to prevent the bugged buttons at top left
		//g.drawImage(layer.getNode(),0,0,null);
		//layering 1.1
		//drawing multiple layers
		//System.out.println("PaintComponent");
		head = layer.getHead();
		do{
			g.drawImage(head.getNode(),0,0,null);
			System.out.println("Drawing layer " + head.getLayerName());
			if(head.hasNext()){
				head = head.next();
			}
			else break;
		}while(true);
		
		//drawBorder(g);
		
		//System.out.println("PaintComponent1");
		//set the pen color
		g.setColor(app.getSidePanel().sliders.getColor());
		bufferedGraphics.setColor(app.getSidePanel().sliders.getColor());
		
		//Paint Template
		if(img!=null){
			g.drawImage(img,(this.getWidth()-img.getWidth())/2,(this.getHeight()-img.getHeight())/2,null);
			bufferedGraphics.drawImage(img,(this.getWidth()-img.getWidth())/2,(this.getHeight()-img.getHeight())/2,null);
			img = null;
			return;
		}
		//printing text
		if(text&&draw){
			//System.out.println("Drawing Text");
			g.drawString(app.getSidePanel().text.text.getText(),textX,textY);
			bufferedGraphics.drawString(app.getSidePanel().text.text.getText(),textX,textY);
			draw = false;
			//text = draw = false;
			//app.getSidePanel().text.enter.setBackground(null);
			//app.getSidePanel().text.textToggle = false;
			return;
		}
		//drawing shapes
		if(shape&&draw){
			//System.out.println("Drawing Shape");
			if(app.getSidePanel().text.shapeList.getSelectedItem()==" "){
				return;
			}
			else if(app.getSidePanel().text.shapeList.getSelectedItem()=="Square"){
				g.fillRect(textX-app.getSidePanel().sliders.cursor*3/2,textY-app.getSidePanel().sliders.cursor*3/2,app.getSidePanel().sliders.cursor*3,app.getSidePanel().sliders.cursor*3);
				bufferedGraphics.fillRect(textX-app.getSidePanel().sliders.cursor*3/2,textY-app.getSidePanel().sliders.cursor*3/2,app.getSidePanel().sliders.cursor*3,app.getSidePanel().sliders.cursor*3);
				//shape = draw = false;
				//app.getSidePanel().text.useShape.setBackground(null);
				//app.getSidePanel().text.shapeToggle = false;
				return;
			}
			else if(app.getSidePanel().text.shapeList.getSelectedItem()=="Circle"){
				g.fillOval(textX-app.getSidePanel().sliders.cursor*3/2,textY-app.getSidePanel().sliders.cursor*3/2,app.getSidePanel().sliders.cursor*3,app.getSidePanel().sliders.cursor*3);
				bufferedGraphics.fillOval(textX-app.getSidePanel().sliders.cursor*3/2,textY-app.getSidePanel().sliders.cursor*3/2,app.getSidePanel().sliders.cursor*3,app.getSidePanel().sliders.cursor*3);
				//shape = draw = false;
				//app.getSidePanel().text.useShape.setBackground(null);
				//app.getSidePanel().text.shapeToggle = false;
				return;
			} 
			else if(app.getSidePanel().text.shapeList.getSelectedItem()=="Star"){
				try {
					Image star = ImageIO.read(new URL(app.getCodeBase(), "star.png"));
					g.drawImage(star,textX-star.getWidth(null)/2,textY-star.getHeight(null)/2,null);
					bufferedGraphics.drawImage(star,textX-star.getWidth(null)/2,textY-star.getHeight(null)/2,null);
				} catch (IOException e) {
					System.out.println(e);
				}
				//shape = draw = false;
				//app.getSidePanel().text.useShape.setBackground(null);
				//app.getSidePanel().text.shapeToggle = false;
				return;
			}
			else if(app.getSidePanel().text.shapeList.getSelectedItem()=="Rectangle"){
				g.fillRect(textX-app.getSidePanel().sliders.cursor*3/2,textY-app.getSidePanel().sliders.cursor*3/2,app.getSidePanel().sliders.cursor*3,app.getSidePanel().sliders.cursor*5);
				bufferedGraphics.fillRect(textX-app.getSidePanel().sliders.cursor*3/2,textY-app.getSidePanel().sliders.cursor*3/2,app.getSidePanel().sliders.cursor*3,app.getSidePanel().sliders.cursor*5);
				//shape = draw = false;
				//app.getSidePanel().text.useShape.setBackground(null);
				//app.getSidePanel().text.shapeToggle = false;
				return;
			}
			else {
				//do nothing
				shape = draw = false;
				app.getSidePanel().text.useShape.setBackground(null);
				app.getSidePanel().text.shapeToggle = false;
				return;
			}
			//g.drawString(app.getSidePanel().text.text.getText(),textX,textY);
			//bufferedGraphics.drawString(app.getSidePanel().text.text.getText(),textX,textY);
		}
		//Eraser
		if(app.getSidePanel().buttons.buttonV[4]){
			g.setColor(app.getMainPanel().getBackground());
			bufferedGraphics.setColor(app.getMainPanel().getBackground());
			g.fillRect((int)MouseX,(int)MouseY,app.getSidePanel().sliders.cursor,app.getSidePanel().sliders.cursor);
			bufferedGraphics.fillRect((int)MouseX,(int)MouseY,app.getSidePanel().sliders.cursor,app.getSidePanel().sliders.cursor);
			//g.setColor(app.getMainPanel().getBackground());
			//g.drawLine((int)OldMX,(int)OldMY,(int)MouseX,(int)MouseY);
			return;
		}
		
		if(app.getSidePanel().buttons.buttonV[0]){
			//MouseY = MouseX = Double.MAX_VALUE;
			return;
		}
		//Paint Pen
		if(app.getSidePanel().buttons.buttonV[7]){
			g.setColor(app.getSidePanel().sliders.getColor());
			bufferedGraphics.setColor(app.getSidePanel().sliders.getColor());
			if(brushToggle){
				g.fillOval((int)MouseX,(int)MouseY,app.getSidePanel().sliders.cursor,app.getSidePanel().sliders.cursor);
				bufferedGraphics.fillOval((int)MouseX,(int)MouseY,app.getSidePanel().sliders.cursor,app.getSidePanel().sliders.cursor);
			}
			else{
				g.fillRect((int)MouseX,(int)MouseY,app.getSidePanel().sliders.cursor,app.getSidePanel().sliders.cursor);		
				bufferedGraphics.fillRect((int)MouseX,(int)MouseY,app.getSidePanel().sliders.cursor,app.getSidePanel().sliders.cursor);
			}
			//g.drawLine((int)OldMX,(int)OldMY,(int)MouseX,(int)MouseY);
		}
	}

	
	private void drawBorder(Graphics g){
		g.setColor(Color.LIGHT_GRAY);
		g.drawLine(0,0,getWidth()-1,0);
		g.drawLine(getWidth()-1,0,getWidth()-1,getHeight()-1);
		g.drawLine(getWidth()-1,getHeight()-1,0,getHeight()-1);
		g.drawLine(0,0,0,getHeight()-1);
	}
	
	@Override
	public void mouseDragged(MouseEvent arg0) {
		//System.out.println("MouseDragged!!");
		do{
			MouseX = arg0.getX();
			MouseY = arg0.getY();
		}while(MouseX > app.getMainPanel().getWidth()-1);
		repaint();
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		//System.out.println("MouseMoved!!");
		//MouseX = arg0.getX();
		//MouseY = arg0.getY();
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		//System.out.println("Mouse Clicked!");
		if(app.getSidePanel().buttons.buttonV[0]){	//Get Color Button
			//layering 1.0
			//app.getSidePanel().sliders.setColor(new Color(bi.getRGB(arg0.getX(),arg0.getY()),true));
			app.getSidePanel().sliders.setColor(new Color(layer.getNode().getRGB(arg0.getX(),arg0.getY()),true));
			repaint();
		}
		else if(text){
			//for single use
			//app.getSidePanel().text.textToggle = false;
			//app.getSidePanel().text.enter.setBackground(null);
			draw = true;
			textX = arg0.getX();
			textY = arg0.getY();
			repaint();
		}
		else if(shape){
			//for single use
			//app.getSidePanel().text.shapeToggle = false;
			//app.getSidePanel().text.useShape.setBackground(null);
			draw = true;
			textX = arg0.getX();
			textY = arg0.getY();
			repaint();
		}
		else{}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		//MouseX = arg0.getX();
		//MouseY = arg0.getY();
		//repaint();
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		
	}

	protected void nextLayer(){
		layer = layer.next();
		bufferedGraphics = layer.getGraphics();
	}
	
	protected void prevLayer(){
		layer = layer.prev();
		bufferedGraphics = layer.getGraphics();
	}
}
