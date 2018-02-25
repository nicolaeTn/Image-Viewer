import java.io.IOException;
/**
 * This class sets up the model, view, and controller according to the Model-View-Presenter (MVP) pattern
 * and runs the image viewer.
 * @author Nicolae Turcan
 *
 */
public class Driver {

	public static void main(String[] args) throws IOException {
		view.View theView = new view.View(); // Sets up the view
		model.Model theModel = new model.Model(); // Sets up the model
		@SuppressWarnings("unused")
		controller.Controller theController = new controller.Controller(theView, theModel); // Sets up the controller
	}

}
