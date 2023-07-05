package de.deloma.tools.filedeleter;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Main class launches stage
 *
 * @author Sezin Maden
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