package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
/**
 * This class serves as the view that display the UI.
 * @author Nicolae Turcan
 *
 */
public class View {
	// All UI elements
	private JButton open;
	private JFileChooser fc;
	private String path;
	// Frame for the image
	private JFrame frame;
	private ImageIcon imgIcon;
	private JLabel lbl;
	// Panel that will display button beneath the image
	private JPanel buttonPanel; 
	// Panel that will display the image
	private JPanel imagePanel;
	private JButton helpButton;
	private JButton previousButton;
	private JButton nextButton;
	private JPopupMenu popupMenu;
	private JMenuItem next;
	private JMenuItem previous;
	private int width;
	private int height;
	/**
	 * Display the window that let's you select a folder containing the images.
	 */
	public View(){
		open = new JButton();
		// File chooser object
		fc =  new JFileChooser();
		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		fc.setCurrentDirectory(new java.io.File("."));
		fc.setDialogTitle("Please choose a folder with images");
		// Required part for the file chooser
		if (fc.showOpenDialog(open) == JFileChooser.APPROVE_OPTION){

		}
		// Sets the selected path
		setPath(this.fc.getSelectedFile().getAbsolutePath());
	}
	/**
	 * Creates the UI of the viewer.
	 * @param imageIcon image to be displayed
	 */
	public void createViewer(ImageIcon imageIcon){
		// Creates the frame
		frame = new JFrame("Photo Viewer");
		frame.setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		imgIcon = imageIcon;
		height = imgIcon.getImage().getHeight(null);
		width = imgIcon.getImage().getWidth(null);
		// Restricts the maximum size of the displayed image to 600 pixels in height or width (whichever is bigger)
		if (height>width){
			imgIcon.setImage(getScaledImage(imgIcon.getImage(), 600*width/height, 600));
		}
		else {
			imgIcon.setImage(getScaledImage(imgIcon.getImage(), 600, 600*height/width));
		}
		// Sets the label
		lbl = new JLabel();
		lbl.setIcon(imgIcon);
		// Sets the button panel and the buttons
		buttonPanel = new JPanel();
		imagePanel = new JPanel();
		imagePanel.setLayout(new GridBagLayout());
		imagePanel.add(lbl);
		helpButton = new JButton("Help");
		previousButton = new JButton("Previous");
		nextButton = new JButton("Next");
		// Adds everything to the button panel
		buttonPanel.add(previousButton);
		buttonPanel.add(nextButton);
		buttonPanel.add(helpButton);
		buttonPanel.setFocusable(true);
		buttonPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		// Adds everything to the frame
		frame.add(buttonPanel, BorderLayout.SOUTH);
		frame.add(imagePanel, BorderLayout.CENTER);
		frame.pack();
		// Sets frame size
		frame.setSize(750, 750);
		height = imgIcon.getImage().getHeight(null);
		width = imgIcon.getImage().getWidth(null);
		// Sets some padding to images
		frame.setMinimumSize(new Dimension(width + 50, height + 100));
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	/**
	 * Prepares and scales the image.
	 * @param srcImg image to be scaled
	 * @param width width of the image
	 * @param height height of the image
	 * @return
	 */
	private Image getScaledImage(Image srcImg, int width, int height){
		// Sizes the image
		BufferedImage resizedImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = resizedImg.createGraphics();
		// Draws and disposes the image
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2.drawImage(srcImg, 0, 0, width, height, null);
		g2.dispose();

		return resizedImg;
	}
	/**
	 * Adds a listener to the help button.
	 * @param listenerForHelpButton listener for the help button
	 */
	public void addHelpButtonListener(ActionListener listenerForHelpButton){
		helpButton.addActionListener(listenerForHelpButton);
	}
	/**
	 * Adds a mouse click listener to the frame.
	 * @param listenerForMouseClicks listens to mouse clicks
	 */
	public void addMouseClickListener(MouseListener listenerForMouseClicks){
		frame.addMouseListener(listenerForMouseClicks);
	}
	/**
	 * Adds a listener listening for the next button.
	 * @param listenerForNextButton listens for next button to be clicked
	 */
	public void addNextButtonListener(ActionListener listenerForNextButton){
		nextButton.addActionListener(listenerForNextButton);
	}
	/**
	 * Adds a listener listening for the previous button.
	 * @param listenerForPreviousButton listens for previous button to be clicked
	 */
	public void addPreviousButtonListener(ActionListener listenerForPreviousButton){
		previousButton.addActionListener(listenerForPreviousButton);
	}
	/**
	 * Adds a listener listening for keyboard input.
	 * @param listenerForKey listens for keyboard input
	 */
	public void addKeyboardListener(KeyListener listenerForKey){
		buttonPanel.addKeyListener(listenerForKey);
	}
	/**
	 * Adds a listener listening for mouse wheel events.
	 * @param listenerForMouseWheel listens for mouse wheel events
	 */
	public void addMouseWheelListener(MouseWheelListener listenerForMouseWheel){
		frame.addMouseWheelListener(listenerForMouseWheel);
	}
	/** 
	 * Adds a listener listening for the first option in the menu that appears after right clicking the image.
	 * @param listenerForNextButton listens for the first option in the menu that appears after right clicking the image
	 */
	public void addFirstMenuOptionListener(ActionListener listenerForNextButton){
		next.addActionListener(listenerForNextButton);
	}
	/**
	 * Adds a listener listening for the second option in the menu that appears after right clicking the image.
	 * @param listenerForPreviousButton listens for the second option in the menu that appears after right clicking the image
	 */
	public void addSecondMenuOptionListener(ActionListener listenerForPreviousButton){
		previous.addActionListener(listenerForPreviousButton);
	}
	/**
	 * Creates a menu when right clicking an image.
	 * @param e mouse event
	 */
	public void createPopupMenu(MouseEvent e){
		popupMenu = new JPopupMenu();
		// Adds two items to the menu
		next = new JMenuItem("Next");
		previous = new JMenuItem("Previous");
		popupMenu.add(next);
		popupMenu.add(previous);
		// Sets the coordinates of the menu
		popupMenu.show( e.getComponent(),
				e.getX(), e.getY() );
	}
	/**
	 * Displays the message when the help button is clicked.
	 */
	public void displayHelpMessage(){
		JOptionPane.showMessageDialog(null, "There are five ways to browse through images: \n1) Click Next/Previous buttons \n"
				+ "2) Press right/left arrows on the keyboard \n3)"
				+ " Scroll using your mouse in the north/south direction \n4) Click the right/left portion of the frame \n5) Right "
				+ "click the image and select next/previous");
		// Take focus from the button and apply it to the button panel so that the keyboard input will work
		buttonPanel.requestFocus();
	}
	/**
	 * Updates the image in the viewer.
	 * @param imageIcon the image to be displayed
	 */
	public void updateImage(ImageIcon imageIcon){
		// Sets the height and width
		height = imageIcon.getImage().getHeight(null);
		width = imageIcon.getImage().getWidth(null);
		// Scales the image to 600 pixels maximum each way
		if (height>width){
			imageIcon.setImage(getScaledImage(imageIcon.getImage(), 600*width/height, 600));
		}
		else {
			imageIcon.setImage(getScaledImage(imageIcon.getImage(), 600, 600*height/width));
		}
		// Updates the image
		lbl.setIcon(imageIcon);
		lbl.revalidate();
		height = imageIcon.getImage().getHeight(null);
		width = imageIcon.getImage().getWidth(null);
		// Gives image padding
		frame.setMinimumSize(new Dimension(width + 50, height + 100));
		// Take focus from the button and apply it to the button panel so that the keyboard input will work
		buttonPanel.requestFocus();
	}
	/**
	 * Getter for the images' path.
	 * @return returns the path of the images
	 */
	public String getPath() {
		return path;
	}
	/**
	 * Setter for the images' path.
	 * @param path of the images
	 */
	public void setPath(String path) {
		this.path = path;
	}
	/**
	 * Getter for the frame.
	 * @return
	 */
	public JFrame getFrame(){
		return frame;
	}
}
