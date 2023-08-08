package de.deloma.filedeleter;

import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Main class launches stage
 *
 * @author Sezin Maden
 * @author Amirhossein Vatani 2023
 */
public class Main extends Application
{
	/**
	 * main Method
	 *
	 * @param args
	 *
	 * @throws Exception
	 */
	public static void main(final String[] args) throws Exception
	{
		Application.launch(args);
	}
	/**
	 * starts the GUI
	 */
		@Override
	public void start(final Stage primaryStage) throws Exception
	{
		
		final Parent root = FXMLLoader.load(this.getClass().getResource("View.fxml"));
		final Scene scene = new Scene(root);
		primaryStage.setTitle("File Deleter");
		primaryStage.setScene(scene);
		primaryStage.show(); 
	} 
}