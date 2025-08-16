package assign11;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import assign05.Line2D;

/**
 * This class represents a GUI component for displaying an image.
 *
 * @author Prof. Martin and Brian Yu
 * @version JavaSE-21
 */
public class ImagePanel extends JPanel implements MouseMotionListener, MouseListener{
	private int xPressed;
	private int xReleased;
	private int yPressed;
	private int yReleased;
	private boolean isCropOn;
	private ArrayList<Integer> xArrayList = new ArrayList<Integer>();
	private ArrayList<Integer> yArrayList = new ArrayList<Integer>();
	private int[] xIntArray;
	private int[] yIntArray;
	private int totalPoints;
	private BufferedImage bufferedImg;
	private ImageProcessorFrame imageFrame;
	
	/**
	 * Creates a new ImagePanel to display the given image.
	 *
	 * @param img - the given image
	 */
	public ImagePanel(Image img, ImageProcessorFrame frame) {
		this.addMouseMotionListener(this);
		this.addMouseListener(this);
		this.imageFrame = frame;
		int rowCount = img.getNumberOfRows();
		int colCount = img.getNumberOfColumns();

		this.bufferedImg = new BufferedImage(colCount, rowCount, BufferedImage.TYPE_INT_RGB);

		for(int i = 0; i < rowCount; i++)
			for(int j = 0; j < colCount; j++)
				this.bufferedImg.setRGB(j, i, img.getPixel(i, j).getPackedRGB());

		this.setPreferredSize(new Dimension(colCount, rowCount));
	}

	/**
	 * This method is called by the system when a component needs to be painted.
	 * Which can be at one of three times:
	 *    --when the component first appears
	 *    --when the size of the component changes (including resizing by the user)
	 *    --when repaint() is called
	 *
	 * Partially overrides the paintComponent method of JPanel.
	 *
	 * @param g -- graphics context onto which we can draw
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g); //clears the panel
		g.drawImage(this.bufferedImg, 0, 0, this);
		if(!imageFrame.getIsDrawOn()) {	
			g.setColor(new Color(105, 105, 105, 125));
			g.fillRect(xPressed, yPressed, xReleased - xPressed, yReleased - yPressed);
		}
		Graphics2D g2 = (Graphics2D) g;
		if(imageFrame.getIsDrawOn()) {
			g.setColor(new Color(238, 75, 43));
			g2.setStroke(new BasicStroke(5));
			g.drawPolyline(xIntArray, yIntArray, totalPoints);
		}
	}
	
	/**
	 * Crop getter
	 * @return boolean
	 */
	public boolean getCropOn() {
		return isCropOn;
	}

	/**
	 * Crop setter
	 * @param isCropOn
	 */
	public void setCropOn(boolean isCropOn) {
		this.isCropOn = isCropOn;
	}

	
	public int getXPressed() {
		return this.xPressed;
	}
	
	public int getYPressed() {
		return this.yPressed;
	}
	
	public int getXReleased() {
		return this.xReleased;
	}
	
	public int getYReleased() {
		return this.yReleased;
	}
	
	
	public void mousePress(MouseEvent e) {
		this.imageFrame.enableAllFilters();
		this.xPressed = e.getX();
		this.yPressed = e.getY();
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		mousePress(e);
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		if(!imageFrame.getIsDrawOn() ) {
			if(xReleased - xPressed != 0 || yReleased - yPressed != 0) 
				imageFrame.disableAllFiltersNotCrop();
			else 
				imageFrame.enableAllFilters();
		}
		if(imageFrame.getIsDrawOn()) {
			Image currentImage = imageFrame.getFilteredImage();
			imageFrame.setFilteredImage(currentImage);
			this.xArrayList.clear();
			this.yArrayList.clear();
			this.totalPoints = 0;			
		}
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		this.xReleased = e.getX();
		this.yReleased = e.getY();
		
		if(this.imageFrame.getIsDrawOn()) {
			this.xArrayList.add(e.getX());
			this.yArrayList.add(e.getY());
			this.totalPoints += 1;
		}
		if(this.imageFrame.getIsDrawOn()) {
			this.xIntArray = new int[totalPoints];
			this.yIntArray = new int[totalPoints];
			for(int i = 0; i < totalPoints; i++) {
				xIntArray[i] = xArrayList.get(i);
				yIntArray[i] = yArrayList.get(i);
			}
		}

		revalidate();
		repaint();
		System.out.println("x: " + e.getX());
		System.out.println("y: " + e.getY());
	}
	
	
	
	@Override
	public void mousePressed(MouseEvent e) {
		mousePress(e);
	}	

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	// Required by a serializable class (ignore for now)
	private static final long serialVersionUID = 1L;
}
