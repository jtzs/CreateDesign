import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class Layer{
	private int name;
	private BufferedImage Node;
	private Layer head,prev, next = null;

	//initial constructor
	public Layer(int width, int height){
		head = this;
		prev = null;
		Node = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		name = 0;
	}
	
	private Layer(Layer previous,Layer Head, int width, int height) {
		head = Head;
		prev = previous;
		name = prev.getLayerName() + 1;
		Node = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
	}
	
	public void setColor(Color newColor){
		//Node.getGraphics().setColor(Color.RED);
		Node.getGraphics().setColor(newColor);
		//System.out.println("Color is " + newColor);
	}

	public int getLayerName(){
		return name;
	}
	
	public Layer getHead(){
		return head;
	}
	
	public BufferedImage getNode(){
		return Node;
	}
	
	public Graphics getGraphics(){
		return Node.getGraphics();
	}
	
	public Layer next() {
		if(this.getLayerName() > 100){
			return this;
		}
		if(next==null){
			next = new Layer(this,head, Node.getWidth(), Node.getHeight());
		}
		return next;
	}

	public Layer prev() {
		if(prev==null){
			return this;
		}
		return prev;
	}

	public boolean clear() {
		Node = new BufferedImage(Node.getWidth(), Node.getHeight(), BufferedImage.TYPE_INT_ARGB);
		return true;
	}
	
	public boolean hasNext(){
		if(next==null){
			return false;
		}
		else
			return true;
	}
}
