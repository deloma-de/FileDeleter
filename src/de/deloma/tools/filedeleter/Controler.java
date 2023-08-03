package de.deloma.tools.filedeleter;

import java.io.File;




import java.net.URL;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.TreeSet;

import javafx.beans.value.ChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBoxTreeItem;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.CheckBoxTreeCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.DirectoryChooser;
import javafx.util.StringConverter;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * Controler class for the GUI
 * 
 * @author Sezin Maden
 * @author Amirhossein Vatani 2023
 */
public class Controler implements Initializable
{

	/**
	 * Path of the config file
	 */
	
	static Node nodeImage; 
  	 
	private final static String CONFIG = "config.txt";
	
	private static DirectoryChooser dChooser;

	@FXML
	private TreeView<Ordner> treeView;

	@FXML
	private Button removeButton;
	@FXML
	private Button deleteButton;

	/*
	 * init methods
	 */

	/**
	 * init Controler
	 */
	@Override
	public void initialize(final URL location, final ResourceBundle resources)
	{
		this.initTreeView();
	}

	/**
	 * intitalizes the TreeView Element
	 */
	private void initTreeView()
	{
	Image folder = 	new Image( getClass().getResourceAsStream("img/warn_icon.png"));
		 
	
		this.removeButton.setDisable(true);

		OrdnerDaoImpl dao = new OrdnerDaoImpl(Controler.CONFIG);

		
		this.isAnyCheckBoxSelected();

		final Set<Ordner> ordSet = dao.getAllOrdner();
		
		// rootItem
	    final CheckBoxTreeItem<Ordner> root = new CheckBoxTreeItem<Ordner>(new Ordner(""));
	    
      	// add ordner to root
	     ordSet.forEach(o -> Controler.addOrdner(root,o));
	    
		    
	    // optimize nodes (not virtual root node)
	     root.getChildren().forEach(c ->  TreeUtils.optimizeNode(c));

	   	this.treeView.setRoot(root);
	    this.treeView.setShowRoot(false);

		

		// select listener
		this.treeView.getSelectionModel().selectedItemProperty().addListener((ChangeListener<TreeItem<Ordner>>) (observable, oldValue, newValue) -> {
			
			if (newValue != null && oldValue == null)
				Controler.this.removeButton.setDisable(false);
			else if (newValue == null && oldValue != null)
				Controler.this.removeButton.setDisable(true);

		});
		
		
		// checkbox listener
		this.treeView.getRoot().addEventHandler(CheckBoxTreeItem.<Ordner> checkBoxSelectionChangedEvent(), e -> {

			final CheckBoxTreeItem<Ordner> treeItem = e.getTreeItem();
			final Ordner ordner = treeItem.getValue();
			ordner.setActive(treeItem.isSelected());
			dao.updateOrdner(ordner);

			this.isAnyCheckBoxSelected();

		});
		
		// Rename the Ordner
	    treeView.setCellFactory(param -> new CheckBoxTreeCell<Ordner>() {
	        @Override
	        public void updateItem(Ordner ordner, boolean empty) {
	            super.updateItem(ordner, empty);	            
	            
	            if (empty || ordner == null) {
	                setText(null);
	            } else {

	                TreeItem<Ordner> item = this.getTreeItem();	      
	                 
	                String parentPath = item.getParent().getValue().toString();
	               // ordner.setConfig(item.isLeaf() ? true : false);
	                

	                
	                if(!TreeUtils.checkFolderExistence(item.getValue().toString() )) {
	                	
		                // set icon
		                setGraphic( new ImageView(folder));
		                
	                }
	                
	                setFont(Font.font(null, (item.isLeaf() ? FontWeight.BOLD : FontWeight.NORMAL) , USE_PREF_SIZE));
	                
	                if (!item.isLeaf()) {
	                    setTextFill(Color.GRAY);
	                }
                	
	                if(!(item.getParent() == null)) {	     	
	                	
	                	if(!(item.getParent().getValue().toString().equals(""))) {

	                		String fullPath =  item.getValue().toString();
	        	         
	        	         setText(TreeUtils.getRelativePath(parentPath+"\\", fullPath));
	        	         
	        //	         System.out.println("item : " + TreeUtils.getRelativePath(parentPath+"\\", fullPath)) ;
	                	} 
	                }       
	                
	             //   System.out.println( "     Existence    : " +TreeUtils.checkFolderExistence("H:\\test")) ;
	                
	                
	                System.out.println("item : " + item.getValue().toString() + "     Existence    : " +TreeUtils.checkFolderExistence(parentPath)) ;
	                 /*      System.out.println("item.getChildren : " +item.getChildren());
	                System.out.println("isLeaf : " +item.isLeaf());
	                System.out.println("getParent().getValue() : " + item.getParent().getValue());
	                System.out.println("getParent().isConfig() : " + item.getValue().isConfig());         
	                System.out.println("-------------------------" );   */
	                	

  	               	                
	            }
	        }
	    });
	}
	
	public static void addOrdner(TreeItem<Ordner> root, Ordner ordner)
	{

		// H:/test/filedeleter
		String pfad = ordner.getPfad();
		
		// [H:, test, filedeleter]
		String[] parts = pfad.split("\\\\");
		
		// 1. H:
		// 2. test
		// 3. filedeleter
		
		String fullPath = "";
		for(String part : parts)
		{
			if (!fullPath.isEmpty())
				fullPath += "\\";
			
			fullPath += part;
			
			
			// 1. H:
			// 2. H:/test
			// 3. H:/test/filedeleter

			// find respective folder in root
			Ordner fullPathOrdner = new Ordner(fullPath);

			// check if already exists
			TreeItem<Ordner> item = TreeUtils.findOrdner(root, fullPathOrdner);

			if(item == null)
			{
				// System.out.println("addOrdner add: " + fullPath);
				
				// find parent
				Ordner parentOrdner = new Ordner(fullPathOrdner.getParentPath());

				TreeItem<Ordner> parentItem = TreeUtils.findOrdner(root, parentOrdner);
				
				if(parentItem == null)
					parentItem = root;
				
				CheckBoxTreeItem<Ordner> ordnerItem = new CheckBoxTreeItem<Ordner>(fullPathOrdner);
				ordnerItem.setExpanded(true);
				
				
				if (ordner.equals(fullPathOrdner))
					ordnerItem.setSelected(ordner.isActive());
				
				
				
				
				parentItem.getChildren().add(ordnerItem);
				
			}
			
			
			
		}
		

	}
	

	/*
	 * private methods
	 */

	/**
	 * adds a directory to a TreeView
	 *
	 * @param root
	 * @param current
	 * @param o
	 */
	private static void add(final TreeItem<Ordner> current, final Ordner o)
	{
		if (current.isLeaf())
		{
			final CheckBoxTreeItem<Ordner> item = Controler.statusCheckedItem(o);

			current.getChildren().add(item);
			return;
		}

		boolean addBetweenFlag = false;
		TreeItem<Ordner> addOver = null;

		final ObservableList<TreeItem<Ordner>> children = current.getChildren();
		final Iterator<TreeItem<Ordner>> i = children.iterator();

		while (i.hasNext())
		{
			final TreeItem<Ordner> child = i.next();

			if (child.getValue().equals(o))
				return;

			// add to child if o is a subdirectory of child
			if (child.getValue().compareTo(o) > 0)
			{
				Controler.add(child, o);
				return;
			}

			// marks that o needs to be added between child and current,
			// because child is a sub directory of o
			if (o.compareTo(child.getValue()) > 0)
			{
				addBetweenFlag = true;
				addOver = child;
			}
		}

		// adds o between child and current
		if (addBetweenFlag)
		{
			final CheckBoxTreeItem<Ordner> item = Controler.statusCheckedItem(o);
			Controler.addBetween(addOver, item);
			return;
		}
		final CheckBoxTreeItem<Ordner> item = Controler.statusCheckedItem(o);
		children.add(item);
		
		
		
		
	}


	/**
	 * Helper Method Checks the Status of Folder in file and sets the same value
	 * into a variable for next Object
	 *
	 * @param o
	 * @return an Item Object
	 *
	 */
	private static CheckBoxTreeItem<Ordner> statusCheckedItem(final Ordner o)
	{
		final CheckBoxTreeItem<Ordner> item = new CheckBoxTreeItem<Ordner>(o);
		item.setSelected(o.isActive());
		return item;
	}

	/**
	 * adds newItem between addOver and his parent
	 *
	 * @param addOver
	 * @param newItem
	 */
	private static void addBetween(final TreeItem<Ordner> addOver, final TreeItem<Ordner> newItem)
	{

		final ObservableList<TreeItem<Ordner>> children = addOver.getParent().getChildren();
		children.add(newItem);
		children.remove(addOver);
		newItem.getChildren().add(addOver);

		return;
	}

	/**
	 * Checks if all CheckBoxes are unchecked then the delete files button is
	 * disabled
	 */
	private void isAnyCheckBoxSelected()
	{
		final OrdnerDaoImpl dao = new OrdnerDaoImpl(Controler.CONFIG);
		this.deleteButton.setDisable(!dao.isAnyActive());
	}

	/**
	 * deletes the content of saved files
	 */
	public void deleteFiles()
	{

		final Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmation Dialog");
		alert.setHeaderText(null);
		alert.setContentText("Are you sure you want to delete all files in the 'black / bold' marked leaf directories?");

		final Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK)
		{

			// ... user chose OK

			final OrdnerDaoImpl ordDao = new OrdnerDaoImpl(Controler.CONFIG);
			ordDao.deleteFiles();
			

		}
		else
		{
			// ... user chose CANCEL or closed the dialog
		}

	}

	/**
	 * opens up the file browser to add a file to the list of files
	 */
	public void addFile()
	{
		
		final DirectoryChooser chooser = this.getChooser();
		final File dir = chooser.showDialog(null);
		if (dir != null)
		{

			final OrdnerDaoImpl dao = new OrdnerDaoImpl(Controler.CONFIG);
			Ordner newOrdner = new Ordner(dir.getAbsolutePath());
			newOrdner.setActive(true);
			
			// setConfig
			dao.createOrdner(newOrdner);

			// refresh list view
			this.initTreeView();
		}
	}

	/**
	 * removes the chosen file from the list of files
	 */
	public void removeFile()
	{
		// remove directory
		final Ordner ord = this.treeView.getSelectionModel().selectedItemProperty().getValue().getValue();
		
		final OrdnerDaoImpl dao = new OrdnerDaoImpl(Controler.CONFIG);
		dao.deleteOrdner(ord);

		// refresh listView
		this.initTreeView();
	}

	private DirectoryChooser getChooser()
	{
		
		if (Controler.dChooser != null)
		{
			return Controler.dChooser;
		}
		else
		{
			return new DirectoryChooser();
		}
	}

	/*
	 * Debugging Methods
	 */

	/**
	 * prints a Textual representation of a TreeView Object
	 *
	 * @param treeView
	 */
	@SuppressWarnings("unused")
	private static void printTree(final TreeView<Ordner> treeView)
	{
		final Iterator<TreeItem<Ordner>> i = treeView.getRoot().getChildren().iterator();
		while (i.hasNext())
		{
			Controler.printTreeHelp(i.next());
		}
	}

	/**
	 * prints a Textual representation of a TreeItem Object and his Children
	 *
	 * @param current
	 */
	private static void printTreeHelp(final TreeItem<Ordner> current)
	{
		final Iterator<TreeItem<Ordner>> i = current.getChildren().iterator();
		System.out.println(current.getValue());
		while (i.hasNext())
		{
			Controler.printTreeHelp(i.next());
		}
		System.out.println("go to parent");
	}
	
	
	
	

}
