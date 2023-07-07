package de.deloma.tools.filedeleter;

import java.util.Comparator;

/**
 * A comperator for the Class Ordner
 * 
 * @author Sezin Maden
 * @author Amirhossein Vatani 2023
 */
public class DirectoryComparator implements Comparator<Ordner>
{

	@Override
	public int compare(final Ordner o1, final Ordner o2)
	{

		return o1.compareTo(o2);
	}

}