package de.deloma.tools.filedeleter;

import java.util.Set;

/**
 * an interface that a Ordner dao should implement
 * 
 * @author Sezin Maden
 * @author Amirhossein Vatani 2023
 */
public interface OrdnerDao
{

	/**
	 * Ordner getter
	 *
	 * @return Set mit allen gespeicherten Ordnern
	 */
	public Set<Ordner> getAllOrdner();

	/**
	 * l�scht den Ordner aus der Datei
	 *
	 * @param zu l�schender Ordner
	 */
	public void deleteOrdner(Ordner ordner);

	/**
	 * fuegt den Ordner in die Datgei ein
	 *
	 * @param Ordner der eingefuegt werden soll
	 */
	public void createOrdner(Ordner order);

	public void updateOrdner(Ordner ordner);

	public void deleteFiles();

	public boolean isAnyActive();

}
