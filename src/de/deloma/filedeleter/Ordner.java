package de.deloma.filedeleter;

import java.io.File;

/**
 * Representation of a diractory
 *
 * @author Sezin Maden
 * @author Amirhossein Vatani 2023
 */
public class Ordner implements Comparable<Ordner>
{
	private static final String FOLDER_DELIMITER = "\\";
	
	/**
	 * path of the directory
	 * 
	 */
	private String pfad;
	


	/**
	 * checked or unchecked path f�r l�schen
	 */
	private boolean active;
	
	/**
	 * Ordner is in config file and must be a leaf 
	 */
	 private boolean config ;

	/**
	 * constructor
	 */
	public Ordner(final String pfad, final boolean active,final boolean config)
	{
		this(pfad);		
		// Objects.requireNonNull(pfad, "pfad must not be null");
		this.active = active;
		this.config = config;
	}

	public Ordner(String pfad)
	{
		if (pfad == null)
			throw new IllegalArgumentException("pfad must not be null");
		this.pfad = pfad;
	}

	/*
	 * getter and setter
	 */

	public String getPfad()
	{
		return this.pfad;
	}
	
	public String getPfadSlash()
	{
		return this.pfad + FOLDER_DELIMITER;
	}

	public void setPfad(final String pfad)
	{
		this.pfad = pfad;
	}

	public boolean isActive()
	{
		return active;
	}

	public void setActive(boolean active)
	{
		this.active = active;
	}

	/*
	 * methods
	 */

	@Override
	public boolean equals(final Object obj)
	{

		if (!(obj instanceof Ordner))
			return false;

		Ordner objOrdner = (Ordner) obj;

		return this.pfad.equals(objOrdner.getPfad());
	}

	@Override
	public int hashCode()
	{
		int result = 17;
		result = 31 * result + this.pfad.hashCode();
		return result;
	}

	@Override
	public String toString()
	{
		return this.pfad;
	}

	@Override
	/**
	 * compares two Ordner objects returns 1 if the Argument is a sub directory
	 * returns -1 if this is sub directory of the Argument returns 0 if cOrdner
	 * elements are equal or not compareable
	 */
	public int compareTo(final Ordner o)
	{

		return this.pfad.compareToIgnoreCase(o.pfad);
	}
	
	public String getParentPath()
	{
		// H:/test/filedeleter
		
		int lastPathSeparatorIdx = this.pfad.lastIndexOf(FOLDER_DELIMITER);
		
		// H:/test
		if (lastPathSeparatorIdx != -1)
			return this.pfad.substring(0, lastPathSeparatorIdx);
		else
			return "";
	}

	public String getName() {
		// H:/test/filedeleter
		
		int lastPathSeparatorIdx = this.pfad.lastIndexOf(FOLDER_DELIMITER);
		
		// filedeleter
		if (lastPathSeparatorIdx != -1)
			return  this.pfad.substring(lastPathSeparatorIdx +1);
			
		return this.pfad;
		
	}

	public boolean isConfig() {
		return config;
	}

	public void setConfig(boolean config) {
		this.config = config;
	}
	
	 
    public boolean exists() {
        File folder = new File(pfad);
        return folder.exists() && folder.isDirectory() ;
    }


}