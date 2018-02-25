package model;

import java.awt.Image;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

import javax.swing.ImageIcon;
/**
 * This class serves as the model that holds data.
 * @author Nicolae Turcan
 *
 */
public class Model {
	// List that will hold images
	ArrayList<ImageIcon> images = new ArrayList<ImageIcon>();

	// Integer that will hold the current image index
	int currentImageIndex;

	/**
	 * Stores the images given a path.
	 * @param path path of the folder with images
	 * @throws IOException
	 */
	public void storeImages(String path) throws IOException {
		// Checks the format
		try (Stream<Path> filePathStream=Files.walk(Paths.get(path))) {
			filePathStream.forEach(filePath -> {
				if (Files.isRegularFile(filePath)) {
					Image img2 = new ImageIcon(filePath.toString()).getImage();
					// Adds the image
					images.add(new ImageIcon(img2));
				}
			});
		}
	}
	/**
	 * Method used to return the first image.
	 * @return returns the first image
	 */
	public ImageIcon getFirstImage(){
		currentImageIndex = 0;
		return images.get(0);
	}
	/**
	 * Method used to return the next image.
	 * @return returns the next image
	 */
	public ImageIcon getNextImage(){
		if (currentImageIndex < images.size() - 1){
			currentImageIndex++;
		}
		return images.get(currentImageIndex);
	}
	/**
	 * Method used to return the previous image.
	 * @return returns the previous image
	 */
	public ImageIcon getPreviousImage(){
		if (currentImageIndex >0){
			currentImageIndex--;
		}
		return images.get(currentImageIndex);
	}
}
