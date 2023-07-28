package de.deloma.tools.filedeleter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/**
 * A Dao for the class Ordner
 *
 * @author Sezin Maden
 * @author Amirhossein Vatani 2023
 */
public class OrdnerDaoImpl implements OrdnerDao
{

	/**
	 * Datei in der Gespeichert wird
	 *
	 */
	private final String config;

	/**
	 * Konstruktor
	 *
	 */
	public OrdnerDaoImpl(final String config)
	{
		this.config = config;
	}

	// Funktionen

	/**
	 * Ordner getter
	 *
	 * @return TreeSet mit allen gespeicherten Ordnern
	 */
	@Override
	public Set<Ordner> getAllOrdner()
	{
		
		final File file = this.getFile();

		final Set<Ordner> ordnerSet = new TreeSet<>();
		String line = null;

		try
		{
			
			//Write : 

			final FileReader fileReader = new FileReader(file);
			final BufferedReader bufferedReader = new BufferedReader(fileReader);

			while ((line = bufferedReader.readLine()) != null)
			{
				// true,H:\test\filedeleter\test10
				// index [0] = true
				// index [1] = H:\test\filedeleter\test10
				final String[] arrayLine = line.split(",");

				// System.out.println(arrayLine);

				// Checks if the first value is true or false in the line
				if (arrayLine.length > 1)
				{
					final boolean isActive = "true".equals(arrayLine[0]);
					final String path = arrayLine[1];

					if (!path.isEmpty())
						ordnerSet.add(new Ordner(path, isActive,true));
				}
				else
				{
					//  show error message
					throw new IllegalArgumentException("Invalid line format: " + line);
				}
			}

			bufferedReader.close();

		}
		catch (final IOException ex)
		{
			ex.printStackTrace();
		}
		return ordnerSet;
	}

	/**
	 * l�scht den Ordner aus der Datei
	 *
	 * @param zu l�schender Ordner
	 */
	@Override
	public void deleteOrdner(final Ordner ordner)
	{
		final Set<Ordner> ordnerSet = this.getAllOrdner();
		//  remove all child directory if selected item is parebt
		// ordner -> H:\test	
		 Set<Ordner> childOrdners = new HashSet<>();
		 
	 	// find existingOrdner in ordnerSet  -> H:\test\filedeleter
		    Iterator<Ordner> iterator = ordnerSet.iterator();
		    while (iterator.hasNext()) {
		        Ordner existingOrdner = iterator.next();
		    	/*
		    	 * H:\test\filedeleter start With  H:\test 
		    	 * So it's  H:\test\filedeleter a child of H:\test
		    	 */	 
		        if (existingOrdner.getPfad().startsWith(ordner.getPfad())) {
		            childOrdners.add(existingOrdner);
		        }
		    }
		ordnerSet.removeAll(childOrdners);
		
		this.writeSet(ordnerSet);

	}

	/**
	 * fuegt den Ordner in die Datei ein
	 *
	 * @param Ordner der eingefuegt werden soll
	 */
	@Override
	public void createOrdner(final Ordner ordner)
	{
		/*
		 * 1. 
		 * 
		 * config: G:\
		 * ordner: G:\Test\
		 * 
		 * 2.
		 * 
		 * config: H:\Test\
		 * ordner: H:\
		 */
		final Set<Ordner> ordnerSet = this.getAllOrdner();
	    Iterator<Ordner> iterator = ordnerSet.iterator();
	    
	    while (iterator.hasNext()) {
	    	
	    	// 1. G:\
	    	// 2. H:\Test\
	        Ordner existingOrdner = iterator.next();	
	        
	        // check if ordner is parent
	        
	        // 1. G:\ starts with G:\Test\ false
	        // 2. H:\Test\ starts with H:\ true
	        boolean existingOrdnerChildOfOrdner = existingOrdner.getPfadSlash().startsWith(ordner.getPfadSlash());
	        
	        // check if ordner is child
	        
	        // 1. G:\Test\ starts with G:\ true
	        // 2. H:\ starts with H:\Test\ false
	        boolean ordnerChildOfExistingOrdner = ordner.getPfadSlash().startsWith(existingOrdner.getPfadSlash());
	        
	        
	        
	        // if ordner is parent or child - remove existing one
	        if (existingOrdnerChildOfOrdner|| ordnerChildOfExistingOrdner) 		        	
	        	iterator.remove();

	       
	    }

				
			// Added the folder sent through the param
			ordnerSet.add(ordner);
			// The set of all folders will be saved into file
			this.writeSet(ordnerSet);
	}
	
	
	@Override
	public void updateOrdner(final Ordner ordner)
	{
		final Set<Ordner> ordnerSet = this.getAllOrdner();

		final Ordner fileOrdner = ordnerSet.stream().filter(o -> o.equals(ordner)).findFirst().orElse(null);
		if (fileOrdner != null)
			fileOrdner.setActive(ordner.isActive());
		// else
		// throw IllegalArgumentException("The ordner is not in the set!");

		this.writeSet(ordnerSet);
		System.out.println("Update file : " + ordnerSet );
	}

	/**
	 * Schreibt das set in config.txt
	 *
	 * @param ordnerSet
	 */
	private void writeSet( Set<Ordner> ordnerSet)
	{
		final File file = this.getFile();
		
		ordnerSet = new TreeSet<>(ordnerSet);

		try
		{
			final PrintWriter pw = new PrintWriter(file.getPath());
			pw.close();
			final BufferedWriter writer = new BufferedWriter(new FileWriter(file));

			final Iterator<Ordner> i = ordnerSet.iterator();

			while (i.hasNext())
			{
				final Ordner ordner = i.next();
				ordner.setConfig(true);
				writer.write(ordner.isActive() + "," + ordner.getPfad());
				writer.newLine();
			}

			writer.close();

		}
		catch (final IOException ex)
		{
			ex.printStackTrace();
		}
	}

	private File getFile()
	{

		final File file = new File(this.config);

		try
		{
			file.createNewFile();
		}
		catch (final IOException ex)
		{
			ex.printStackTrace();
		}

		return file;
	}

	@Override
	public void deleteFiles()
	{
		final Set<Ordner> ordnerSet = this.getAllOrdner();

		final Iterator<Ordner> i = ordnerSet.iterator();
		while (i.hasNext())
		{
			final Ordner checkedOrdner = i.next();
			if (checkedOrdner.isActive() && checkedOrdner.isConfig())
			
				OrdnerDaoImpl.rekursiveDelete(new File(checkedOrdner.getPfad()), 0);
		}
	}

	/**
	 * rekursively deletes the contend of a file
	 *
	 * @param File
	 * @param j = 0 falls Ordner nicht mit geloescht weden soll
	 */
	private static void rekursiveDelete(final File file, final int j)
	{
		if (file.isDirectory())
		{
			final File[] listFiles = file.listFiles();

			for (int i = 0; i < listFiles.length; i++)
			{
				OrdnerDaoImpl.rekursiveDelete(listFiles[i], j + 1);

			}
		}

		if (!file.isDirectory() || j != 0)
		{
			try
			{
				file.delete();
				System.out.println("delete");
			}
			catch (final Exception e)
			{
				System.out.println(e.getMessage());
			}
		}
	}

	/**
	 * Checks the status of a directory (Checked or unchecked)
	 *
	 * @return boolean
	 */
	@Override
	public boolean isAnyActive()
	{
		final Set<Ordner> ordnerSet = this.getAllOrdner();

		final Iterator<Ordner> i = ordnerSet.iterator();
		while (i.hasNext())
		{
			final Ordner checkedOrdner = i.next();
			if (checkedOrdner.isActive())
				return true;

		}
		return false;
	}
}
