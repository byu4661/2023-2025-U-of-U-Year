 package assign11;

/**
 * @author Brian Yu and Ruffus Harper 
 * @version JavaSE-21 2025.04.10
 * This class handles JMenuItems that are added to the main panel containing a fileMenu, 
 * filterMenu, and an editMenu with different options implemented.
 */

import java.io.File;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ImageProcessorFrame extends JFrame implements ActionListener, ChangeListener {

	private static final long serialVersionUID = 1L;
	private JMenuItem openItem;
	private JMenuItem saveItem;
	private JMenuItem redBlueSwap;
	private JMenuItem blackWhite;
	private JMenuItem rotateClockwise;
	private JMenuItem customMirror;
	private JMenuItem coolBlue;
	private JMenuItem brightness;
	private JSlider brightnessSlider;
	private JMenuItem crop;
	private JMenuItem undo;
	private JMenuItem draw;

	

	private Image filteredImage;
	private JPanel panel;
	private ImagePanel imagePanel;
	private JPanel sliderPanel;

	private boolean brightnessOn;
	private boolean imageOpened;
	private boolean isDrawOn;
	
	private ArrayList<Image> undoArray = new ArrayList<Image>();

	/**
	 * Creates a new ImageProcessorFrame GUI 
	 */
	public ImageProcessorFrame() {
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.panel = new JPanel();
		panel.setPreferredSize(new Dimension(600, 600));

		JMenuBar menuBar = new JMenuBar(); 
		setJMenuBar(menuBar);

		JMenu fileMenu = new JMenu("File");
		JMenu filterMenu = new JMenu("Filters");
		JMenu editMenu = new JMenu("Edit");
		
		this.sliderPanel = new JPanel();

		this.openItem = new JMenuItem("Open new image file");
		this.openItem.addActionListener(this);
		fileMenu.add(this.openItem); //Adds the Open option to the File dropdown 

		this.saveItem = new JMenuItem("Save filtered image file");
		this.saveItem.addActionListener(this);
		fileMenu.add(this.saveItem); //Adds the Save option to the File dropdown

		//Creates and adds red-blue filter to Filters
		this.redBlueSwap = new JMenuItem("Red and Blue Swap");
		initializeMenuItem(redBlueSwap, "Swaps the red and blue amounts on the RGB model for color");
		filterMenu.add(this.redBlueSwap);
		
		//Creates and adds blackWhite filter to Filters dropdown
		this.blackWhite = new JMenuItem("Black and White Swap");
		initializeMenuItem(this.blackWhite, "Swap Black and White RGB Values");
		filterMenu.add(this.blackWhite);

		//Creates and adds rotateClockwise filter to Filters dropdown
		this.rotateClockwise = new JMenuItem("Rotate Clockwise");
		initializeMenuItem(this.rotateClockwise, "Rotate the Image Clockwise");
		filterMenu.add(this.rotateClockwise);

		//Creates and adds customMirror filter to Filters dropdown
		this.customMirror = new JMenuItem("Mirror Image");
		initializeMenuItem(this.customMirror, "Mirror the image");
		filterMenu.add(this.customMirror);

		//Creates and adds coolBlue filter to Filters dropdown
		this.coolBlue = new JMenuItem("Blue Filter");
		initializeMenuItem(coolBlue, "Turns the image a cool blue");
		filterMenu.add(this.coolBlue);
		
		this.brightness = new JMenuItem("Brightness");
		initializeMenuItem(this.brightness, "Control the image brightness using the slider below");
		filterMenu.add(this.brightness);
		
		this.brightnessSlider = new JSlider(-200, 200, 0);
		this.brightnessSlider.setPreferredSize(new Dimension(1000, 50));
		this.brightnessSlider.setMajorTickSpacing(50);
		this.brightnessSlider.setMinorTickSpacing(10);
		this.brightnessSlider.createStandardLabels(50, -200);
		this.brightnessSlider.setPaintLabels(true);
		this.brightnessSlider.setPaintTicks(true);
		
		sliderPanel.add(this.brightnessSlider);
		this.brightnessSlider.setEnabled(false);
		
		this.crop = new JMenuItem("Crop Image");
		initializeMenuItem(this.crop, "Crop the image using the area provided");
		filterMenu.add(this.crop);
		
		this.undo = new JMenuItem("Undo");
		initializeMenuItem(this.undo, "Undo the previously applied filter");
		editMenu.add(this.undo);
		
		this.draw = new JMenuItem("Pointer");
		initializeMenuItem(this.draw, "This is my custom filter!");
		editMenu.add(this.draw);
		

		menuBar.add(fileMenu);
		menuBar.add(filterMenu);
		menuBar.add(editMenu);
		

		//Set all boolean variables to false
		//Once imageOpened == true, then we can apply filters
		this.imageOpened = false;
		this.saveItem.setEnabled(false);

		panel.add(menuBar);
		panel.setLayout(new BorderLayout());

		
		this.setContentPane(panel);
		this.setJMenuBar(menuBar);
		this.pack();
	}

	/**
	 * Method handles events in the ImageProcessorFrame GUI for choosing an initial picture to apply filters, applying the filters,
	 * as well as saving the image to file.
	 * Can only apply filters AFTER an image has been opened.
	 * Can only save files after opening an image AND applying a filter.
	 * 
	 */
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		JFileChooser chooser = new JFileChooser();
		//Saves image after opening and applying a filter
		if (src == saveItem & imageOpened) {
			chooser.setFileFilter(new FileNameExtensionFilter("JPG Images", ".jpg"));
			chooser.setDialogTitle("Select the location for the new file.");

			if (chooser.showSaveDialog(null) != JFileChooser.APPROVE_OPTION) {
				JOptionPane.showMessageDialog(null, "Save file cancelled");
				return;
			}
			// The image object that is drawn to
			BufferedImage img = new BufferedImage(this.panel.getWidth(), this.panel.getHeight(),
					BufferedImage.TYPE_INT_RGB);
			this.panel.paint(img.getGraphics());
			//Try catch handles if the user quits the save program while trying to save
			try {
				ImageIO.write(img, "jpg", chooser.getSelectedFile());
			} catch (IOException ex) {
				JOptionPane.showMessageDialog(null, "The drawing cannot be written to file.");
			}
			
			//Opens image and allows for saving	 
		} else if (src == openItem) {
			if(imageOpened) //If an image is already opened and needing to be replaced
				panel.remove(imagePanel);
			this.imageOpened = true;
			chooser.setSelectedFile(new File(""));
			chooser.setFileFilter(
					new FileNameExtensionFilter("JPG, GIF, and PNG", "jpg", "gif", "png"));
			chooser.setDialogTitle("Select the location for the new file.");
			if (chooser.showSaveDialog(null) != JFileChooser.APPROVE_OPTION) {
				JOptionPane.showMessageDialog(null, "Open file cancelled");
				return;
			}
			//After opening image, allow applying filters
			this.redBlueSwap.setEnabled(true);
			this.blackWhite.setEnabled(true);
			this.rotateClockwise.setEnabled(true);
			this.customMirror.setEnabled(true);
			this.coolBlue.setEnabled(true);
			this.brightness.setEnabled(true);
			this.draw.setEnabled(true);
			
			String filePath = chooser.getSelectedFile().getAbsolutePath();
			this.filteredImage = new Image(filePath);
			this.imagePanel = new ImagePanel(filteredImage, this);
			panel.add(imagePanel);
			
			setContentPane(panel); // panel refers to the ImagePanel object created for the image file opened
			revalidate();
		} 
		//Code below handles filter applied events
		else if (src == this.redBlueSwap & imageOpened) {
			this.undoArray.add(filteredImage.copyImage());
			
			this.filteredImage.redBlueSwapFilter();
			updateFrame();
			
		} else if (src == this.blackWhite & imageOpened) {
			this.undoArray.add(filteredImage.copyImage());
			
			this.brightnessOn = false;
			panel.remove(sliderPanel);
			
			this.filteredImage.blackAndWhiteFilter();
			updateFrame();
			
		} else if (src == this.rotateClockwise & imageOpened) {
			this.undoArray.add(filteredImage.copyImage());
			
			this.filteredImage.rotateClockwiseFilter();
			updateFrame();
			
		} else if (src == this.customMirror & imageOpened) {
			this.undoArray.add(filteredImage.copyImage());

			this.brightnessOn = false;
			panel.remove(sliderPanel);
			
			this.filteredImage.customFilter();
			updateFrame();
			
		} else if (src == this.coolBlue & imageOpened) {
			this.undoArray.add(filteredImage.copyImage());
			
			this.filteredImage.coolBlueFilter();
			updateFrame();
			
		} else if(src == this.brightness & imageOpened) {
			if(!brightnessOn) {
				panel.add(sliderPanel, BorderLayout.SOUTH);
				brightnessOn = true;
				this.brightnessSlider.setEnabled(true);
				this.brightnessSlider.addChangeListener(this);
			}
			else {
				panel.remove(sliderPanel);
				brightnessOn = false;
			}
			this.revalidate();
			
		} else if(src == this.crop & imageOpened) {		
			this.undoArray.add(filteredImage.copyImage());
			
			this.filteredImage.cropImageFilter(imagePanel.getXPressed(), imagePanel.getYPressed(), imagePanel.getXReleased(), imagePanel.getYReleased());
			
			enableAllFilters();
			updateFrame();

		} else if(src == this.undo & imageOpened) {
			this.filteredImage = undoArray.get(undoArray.size() - 1);
			updateFrame();
			
			if(undoArray.size() > 0)
				this.undoArray.removeLast();
			if(undoArray.size() == 0)
				this.undo.setEnabled(false);
			
		} else if(src == this.draw & imageOpened) {
			if(isDrawOn)
				isDrawOn = false;
			else 
				isDrawOn = true;
			this.undoArray.add(filteredImage.copyImage());
			updateFrame();
		}
	}
	
	private void initializeMenuItem(JMenuItem menuItem, String toolTipText) {
		menuItem.setToolTipText(toolTipText);
		menuItem.addActionListener(this);;
		menuItem.setEnabled(false);
	}
	
	//change filters to apply to imagePanel and remove imagePanel when applying new filters
	@Override
	public void stateChanged(ChangeEvent e) {
		JSlider src = (JSlider) e.getSource(); // A JSlider is the only possible source of the change event.
		if (!src.getValueIsAdjusting()) { // Do not set size if the user is still dragging the slider.
			int val = (int) src.getValue();
			if (src == this.brightnessSlider) {
				this.undoArray.add(filteredImage.copyImage());
				this.filteredImage.brightnessFilter(val);
				updateFrame();
			}
		}
	}
	
	/**
	 * Helper method that removes previous panel to prevent unintended clutter, while allowing for saving after 
	 * a filter has been applied.
	 * Creates a new Image
	 */
	public void updateFrame() {
		panel.remove(imagePanel);
		this.saveItem.setEnabled(true);
		this.undo.setEnabled(true);
		this.imagePanel = new ImagePanel(filteredImage, this);
		panel.add(imagePanel);
		this.brightnessOn = false;
		panel.remove(sliderPanel);
		this.setContentPane(panel);
		this.revalidate();
	}
	
	
	
	/**
	 * This method invokes the cropImageFilter from Image.java, to be used in ImagePanel.java 
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 */
	public void cropPanel(int x1, int y1, int x2, int y2) {
		this.undoArray.add(filteredImage.copyImage());
		this.filteredImage.cropImageFilter(x1, y1, x2, y2);
		updateFrame();
	}
	
	public void disableAllFiltersNotCrop() {
		this.redBlueSwap.setEnabled(false);
		this.blackWhite.setEnabled(false);
		this.rotateClockwise.setEnabled(false);
		this.customMirror.setEnabled(false);
		this.coolBlue.setEnabled(false);
		this.brightness.setEnabled(false);
		this.crop.setEnabled(true);
		this.undo.setEnabled(false);
		this.draw.setEnabled(false);
	}
	
	public void enableAllFilters() {
		this.redBlueSwap.setEnabled(true);
		this.blackWhite.setEnabled(true);
		this.rotateClockwise.setEnabled(true);
		this.customMirror.setEnabled(true);
		this.coolBlue.setEnabled(true);
		this.brightness.setEnabled(true);
		this.crop.setEnabled(false);
		this.undo.setEnabled(true);
		this.draw.setEnabled(true);
	}
	
	public boolean getIsDrawOn() {
		return this.isDrawOn;
	}
	
	public Image getFilteredImage() {
		return this.filteredImage;
	}
	
	public void setFilteredImage(Image filteredImage) {
		this.filteredImage = filteredImage;
	}
	
	
	
}
