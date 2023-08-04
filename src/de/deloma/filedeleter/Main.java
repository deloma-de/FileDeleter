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
	
	/*
	 * test icon TreeItem 
	 */
	 Image nodeImage = new Image( getClass().getResourceAsStream("img/folder_icon.png"));

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
	
/*	 @Override
	    public void start(Stage primaryStage) {
	        
	        TreeItem<String> treeItemRoot = new TreeItem<> ("Root");
	        
	        TreeItem<String> nodeItemA = new TreeItem<>("Item A");
	        TreeItem<String> nodeItemB = new TreeItem<>("Item B");
	        TreeItem<String> nodeItemC = new TreeItem<>("Item C");
	        treeItemRoot.getChildren().addAll(nodeItemA, nodeItemB, nodeItemC);
	        
	        TreeItem<String> nodeItemA1 = new TreeItem<>("Item A1", 
	                new ImageView(nodeImage));
	        
	        
	        
	        TreeItem<String> nodeItemA2 = new TreeItem<>("Item A2", 
	                new ImageView(nodeImage));
	        TreeItem<String> nodeItemA3 = new TreeItem<>("Item A3", 
	                new ImageView(nodeImage));
	        
	        nodeItemA.getChildren().addAll(nodeItemA1, nodeItemA2, nodeItemA3);
	        
	        TreeView<String> treeView = new TreeView<>(treeItemRoot);
	        StackPane root = new StackPane();
	        root.getChildren().add(treeView);
	        
	        Scene scene = new Scene(root, 300, 250);
	        
	        primaryStage.setTitle("java-buddy.blogspot.com");
	        primaryStage.setScene(scene);
	        primaryStage.show();
	    }
*/
}