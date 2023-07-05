package de.deloma.tools.filedeleter;

import javafx.scene.control.TreeItem;

public class TreeUtils 
{
	
	public static <T> TreeItem<T> findOrdner(TreeItem<T> item, T value)
	{
		// return if direct match
		if (item.getValue().equals(value))
			return item;
		else
			for(TreeItem<T> child : item.getChildren())
			{
				TreeItem<T> childItem = findOrdner(child, value);
				if (childItem != null)
					return childItem;
			}
		
		return null;
	}
	
	public static <T> void optimizeNode(TreeItem<T> node)
	{
	    for (TreeItem<T> child : node.getChildren()) {
	        optimizeNode(child);
	    }

	    if (node.getChildren().size() == 1) {
	        TreeItem<T> child = node.getChildren().get(0);
	        
	        node.setValue(child.getValue()); 
	        	        		        		        
	        node.getChildren().setAll(child.getChildren()); 

	    }
	    
	    
}
}
