package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
/**
 * This class serves as the controller that coordinates the interaction between view and model.
 * @author Nicolae Turcan
 *
 */
public class Controller {

	// Reference to view and model
	private view.View theView;
	private model.Model theModel;

	/**
	 * Sets up and prepares the references to view and model objects. Adds listeners to view.
	 * @param theView establishes the reference to model
	 * @param theModel establishes the reference to view
	 * @throws IOException
	 */
	public Controller(view.View theView, model.Model theModel) throws IOException{

		// Sets up and prepares view and model objects
		this.theView = theView;
		this.theModel = theModel;
		this.theModel.storeImages(this.theView.getPath());
		this.theView.createViewer(theModel.getFirstImage());

		// Adds listeners to the view
		this.theView.addNextButtonListener(new NextListener());
		this.theView.addPreviousButtonListener(new PreviousListener());
		this.theView.addKeyboardListener(new KeyInputListener());
		this.theView.addMouseWheelListener(new MouseScrollListener());
		this.theView.addMouseClickListener(new MouseClickListener());
		this.theView.addHelpButtonListener(new HelpListener());
	}
	/**
	 * Listener that is used to go to the next image.
	 * @author Nicolae Turcan
	 *
	 */
	class NextListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			theView.updateImage(theModel.getNextImage());
		}
	}
	/**
	 * Listener that is used to display the help message.
	 * @author Nicolae Turcan
	 *
	 */
	class HelpListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			theView.displayHelpMessage();
		}
	}
	/**
	 * Listener that is used to go to the previous image.
	 * @author Nicolae Turcan
	 *
	 */
	class PreviousListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			theView.updateImage(theModel.getPreviousImage());
		}
	}

	/**
	 * Listener that listens to keyboard input.
	 * @author Nicolae Turcan
	 *
	 */
	class KeyInputListener implements KeyListener{

		@Override
		public void keyTyped(KeyEvent e) {
			// Empty
		}

		/**
		 * Updates the image based on the key pressed.
		 */
		@Override
		public void keyPressed(KeyEvent e) {
			// Goes to next image
			if (e.getKeyCode() == KeyEvent.VK_RIGHT){
				theView.updateImage(theModel.getNextImage());
			}
			// Goes to previous image
			else if (e.getKeyCode() == KeyEvent.VK_LEFT){
				theView.updateImage(theModel.getPreviousImage());
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// Empty	
		}
	}
	/**
	 * Listener that listens to mouse scrolls.
	 * @author Nicolae Turcan
	 *
	 */
	class MouseScrollListener implements MouseWheelListener{

		@Override
		public void mouseWheelMoved(MouseWheelEvent e) {
			// Goes to next image
			if (e.getUnitsToScroll() > 0){
				theView.updateImage(theModel.getNextImage());
			}
			// Goes to previous image
			else if (e.getUnitsToScroll() < 0){
				theView.updateImage(theModel.getPreviousImage());
			}
		}	
	}
	/**
	 * Listener that listens to mouse clicks.
	 * @author Nicolae Turcan
	 *
	 */
	class MouseClickListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			// If left clicked
			if(e.getButton() == MouseEvent.BUTTON1){
				// If the right side of the image viewer is clicked go to the next image
				if ( e.getX() > theView.getFrame().getBounds().width/2){
					theView.updateImage(theModel.getNextImage());
				}
				// If the left side of the image viewer is clicked go to the previous image
				else {
					theView.updateImage(theModel.getPreviousImage());
				}
			}
			// If right clicked
			if (e.getButton() == MouseEvent.BUTTON3){
				// Displays the menu after the right click of the mouse
				theView.createPopupMenu(e);
				theView.addFirstMenuOptionListener(new NextListener());
				theView.addSecondMenuOptionListener(new PreviousListener());
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

	}	
}
