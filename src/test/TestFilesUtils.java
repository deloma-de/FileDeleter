package test;

import java.io.File;
import java.io.IOException;

/**
 * @author Amirhossein Vatani 2023
 */
public class TestFilesUtils
{
	public static final String CONFIG_TEST  = "src/test/config_test.txt";
	
	private static final String CONFIG_GET_ALL_ORDNER = "src/test/config_test_GetAllOrdner.txt";
	
	private static final String CONFIG_CREATE_ORDNER = "src/test/config_test_CreateOrdner.txt";
	
	private static final String CONFIG_DELETE_ORDNER = "src/test/config_test_DeleteOrdner.txt";
	
	private static final String CONFIG_DELETE_FILES = "src/test/config_test_DeleteFiles.txt";
	
	private static final String CONFIG_UPDATE_ORDNER = "src/test/config_test_UpdateOrdner.txt";
	
	public static void main(final String[] args) throws IOException {
		//deleteConfigTest();
		//createConfigTest();
		//TestFilesUtils.initGetAllOrdner();
		//initDeleteOrdner();
		//createConfigTest();
	}
	
	
	public static void initGetAllOrdner() throws IOException
	{
		TestUtil.copyFileContents(CONFIG_GET_ALL_ORDNER, CONFIG_TEST);

	}
	
	public static void initCreateOrdner() throws IOException
	{
		TestUtil.copyFileContents(CONFIG_CREATE_ORDNER, CONFIG_TEST);
	}
	
	public static void initDeleteOrdner() throws IOException
	{
		TestUtil.copyFileContents(CONFIG_DELETE_ORDNER, CONFIG_TEST);
	}
	
	public static void initUpdateOrdner() throws IOException
	{
		TestUtil.copyFileContents(CONFIG_UPDATE_ORDNER, CONFIG_TEST);
	}
	
	public static void initDeleteFiles() throws IOException
	{
		TestUtil.copyFileContents(CONFIG_DELETE_FILES, CONFIG_TEST);
	}
	
	
	public static void createConfigTest()
	{
		File f = new File(CONFIG_TEST); 
		TestUtil.createSysFile(f);
		System.out.println("create ConfigTest");
	}
	
	public static void deleteConfigTest()
	{
		File f = new File(CONFIG_TEST); 
		TestUtil.deleteSysFile(f);
		System.out.println("deleteConfigTest");
	}
	
}
