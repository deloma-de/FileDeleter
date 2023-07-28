package test;

import java.io.File;
import java.io.IOException;
import java.util.Set;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import de.deloma.tools.filedeleter.Ordner;
import de.deloma.tools.filedeleter.OrdnerDaoImpl;


class OrdnerDaoImplTest {
	
	private static OrdnerDaoImpl testerDao;

	@BeforeEach
	void setup() 
	{	
		TestFilesUtils.createConfigTest();
		
		testerDao = new OrdnerDaoImpl(TestFilesUtils.CONFIG_TEST);
	}
	
	@AfterEach
	void clear()
	{
		TestFilesUtils.deleteConfigTest();
		
		testerDao = null;
		
		
	}
	


	/*
	 * GetAllOrdner
	 */
	@Test
	void testGetAllOrdner() throws IOException {
		TestFilesUtils.initGetAllOrdner();
		
		// test getAllOrdner
	    Set<Ordner> ordSet = testerDao.getAllOrdner(); 
	    Assert.assertEquals(3, ordSet.size());
	    
	    Ordner o1 = TestUtil.getOrdner("G:\\deloma\\deloma_1" , ordSet) ;
	    Assert.assertNotNull(o1);
	    Assert.assertEquals(true , o1.isActive());
	    
	    Ordner o2 = TestUtil.getOrdner("G:\\deloma\\deloma_2" , ordSet) ;
	    Assert.assertNotNull(o2);
	    Assert.assertEquals(false , o2.isActive());
	    
	    Ordner o3 = TestUtil.getOrdner("G:\\deloma\\deloma_3" , ordSet) ;	    
	    Assert.assertNotNull(o3);
	    Assert.assertEquals(true , o3.isActive());	   
	    
	}

	/*
	 * CreateOrdner
	 */
	 @Test
	void testCreateOrdner() {
		 
		Set<Ordner> ordSet;
		
		// load config with 1-3 ordners
		// Test ordne1
		Ordner ordner1 = new Ordner("G:\\deloma\\deloma_1");
		
		testerDao.createOrdner(ordner1);
		
		ordSet = testerDao.getAllOrdner();
		 
		Assert.assertNotNull(TestUtil.getOrdner(ordner1.getPfad(), ordSet));
		Assert.assertEquals(1 , ordSet.size());
		
		// Test ordne2 
		Ordner ordne2 = new Ordner("G:\\deloma\\deloma_2");
		
		testerDao.createOrdner(ordne2);
		
		ordSet = testerDao.getAllOrdner();
		 
		Assert.assertNotNull(TestUtil.getOrdner(ordne2.getPfad(), ordSet));
		Assert.assertEquals(2, ordSet.size());
		
		// Test ordne3
		Ordner ordner3 = new Ordner("G:\\deloma\\deloma_3");
	    
		testerDao.createOrdner(ordner3);

	    ordSet = testerDao.getAllOrdner();   
	    
	    Assert.assertNotNull(TestUtil.getOrdner(ordner3.getPfad(), ordSet));
		Assert.assertEquals(3, ordSet.size());
		
	}
	


	/*
	 * DeleteOrdner
	 */
	@Test
	void testDeleteOrdner() throws IOException 
	{
		TestFilesUtils.initDeleteOrdner();
		
		Set<Ordner> ordSet = testerDao.getAllOrdner(); 
		
	    Assert.assertEquals(3 , ordSet.size()); 
		
	    // test delete Ordner 4
		Ordner ordner4 = new Ordner("G:\\deloma\\deloma_4");
	    testerDao.deleteOrdner(ordner4);
	    
	    ordSet = testerDao.getAllOrdner(); 
	    
	    Assert.assertNull(TestUtil.getOrdner(ordner4.getPfad(), ordSet));
	    Assert.assertEquals(2 , ordSet.size()); 
	    
	    // test delete Ordner 5
	    Ordner ordner5 = new Ordner("G:\\deloma\\deloma_5");
	    testerDao.deleteOrdner(ordner5);
	    
	    ordSet = testerDao.getAllOrdner(); 
	    
	    Assert.assertNull(TestUtil.getOrdner(ordner5.getPfad(), ordSet));
	    Assert.assertEquals(1 , ordSet.size());
	    
	    // test delete Ordner 6
	    Ordner ordner6 = new Ordner("G:\\deloma\\deloma_6");
	    testerDao.deleteOrdner(ordner6);
	    
	    ordSet = testerDao.getAllOrdner(); 
	    
	    Assert.assertNull(TestUtil.getOrdner(ordner6.getPfad(), ordSet));
	    Assert.assertEquals(0 , ordSet.size());
	    
	   	   
	}
	 

	/*
	 * UpdateOrdner
	 */
	@Test
	void testUpdateOrdner() throws IOException
	{
		TestFilesUtils.initUpdateOrdner();
		
	
		Set<Ordner> ordSet = testerDao.getAllOrdner(); 

	    // test update Ordner 7 -> true,G:\deloma\deloma_7 
		  Ordner o7 = TestUtil.getOrdner("G:\\deloma\\deloma_7" , ordSet);
		  Assert.assertEquals(true ,o7.isActive());
		  
		  o7.setActive(false);
		  
		  testerDao.updateOrdner(o7);
			
		  ordSet = testerDao.getAllOrdner(); 
 
		  Ordner o7new = TestUtil.getOrdner(o7.getPfad() , ordSet);
		  Assert.assertEquals(false ,o7new.isActive());
		  
		
	    // test update Ordner 8 -> true,G:\deloma\deloma_8 
		  Ordner o8 = TestUtil.getOrdner("G:\\deloma\\deloma_8" , ordSet);
		  Assert.assertEquals(true ,o8.isActive());
		  
		  o8.setActive(false);
		  
		  testerDao.updateOrdner(o8);
			
		  ordSet = testerDao.getAllOrdner(); 
 
		  Ordner o8new = TestUtil.getOrdner(o8.getPfad() , ordSet);
		  Assert.assertEquals(false ,o8new.isActive());
		  
	    // test update Ordner 9 -> false,G:\deloma\deloma_9 
		  Ordner o9 = TestUtil.getOrdner("G:\\deloma\\deloma_9" , ordSet);
		  Assert.assertEquals(false ,o9.isActive());
		  
		  o9.setActive(true);
		  
		  testerDao.updateOrdner(o9);
			
		  ordSet = testerDao.getAllOrdner(); 
 
		  Ordner o9new = TestUtil.getOrdner(o9.getPfad() , ordSet);
		  Assert.assertEquals(true ,o9new.isActive());
	}

	/*
	 * DeleteFiles
	 */
	@Test
	void testDeleteFiles() throws IOException 
	{
		TestFilesUtils.initDeleteFiles();

		Set<Ordner> ordSet = testerDao.getAllOrdner();
		
		//create Folder H:\\test\\deloma
		Ordner deloma = new Ordner("H:\\test\\deloma");
		File folderDeloma = new File(deloma.getPfad());
		TestUtil.createSysFolder(folderDeloma);

		/*
		 * First scenario :
		 *  -H: 
		 * 	  --deloma
		 * 			  ---deloma10
		 * 						 ----delomaText1.txt
		 */
		//create Folder H:\\test\\deloma\\deloma_10
		Ordner deloma10 = TestUtil.getOrdner("H:\\test\\deloma\\deloma_10" , ordSet);
		File folderDeloma10 = new File(deloma10.getPfad());
		TestUtil.createSysFolder(folderDeloma10);
		
			//create File H:\\test\\deloma\\deloma_10\\delomaText1.txt
			File delomaText1 = new File("H:\\test\\deloma\\deloma_10\\delomaText1.txt");
			TestUtil.createSysFile(delomaText1);
			
			// test delete deloma_10 
			 testerDao.deleteFiles();
			 
			 Assert.assertEquals(false , delomaText1.exists());
			 
			 Assert.assertEquals(true , folderDeloma10.exists());

			 Assert.assertEquals(true , folderDeloma.exists());
				
		/*
		 * Second scenario:
		 *  -H: 
		 * 	  --deloma
		 * 			  ---deloma11            
		 * 						----deloma_11_1
		 * 									  	-----delomaText2.txt
		 *   					----delomaText3.txt
		 */
		//create Folder H:\\test\\deloma\\deloma_11
		Ordner deloma11 = TestUtil.getOrdner("H:\\test\\deloma\\deloma_11" , ordSet);
		File deloma11File = new File(deloma11.getPfad());
		TestUtil.createSysFolder(deloma11File);	
		
			//create Folder H:\\test\\deloma\\deloma_11\\deloma11_1
			Ordner deloma11_1 = new Ordner("H:\\test\\deloma\\deloma_11\\deloma11_1");
			File deloma11_1File = new File(deloma11_1.getPfad());
			TestUtil.createSysFolder(deloma11_1File);

				//create File H:\\test\\deloma\\deloma_11\\deloma11_1\\delomaText2.txt
				File delomaText2 = new File("H:\\test\\deloma\\deloma_11\\deloma11_1\\delomaText2.txt");
				TestUtil.createSysFile(delomaText2);
			
			//create File H:\\test\\deloma\\deloma_11\\delomaText3.txt
			File fileDelomaText3 = new File("H:\\test\\deloma\\deloma_11\\delomaText3.txt");
			TestUtil.createSysFile(fileDelomaText3);
			
			// test delete deloma_11 
			 testerDao.deleteFiles();
			 
			 Assert.assertEquals(false , delomaText2.exists());
			 
			 Assert.assertEquals(false , deloma11_1File.exists());

			 Assert.assertEquals(false , fileDelomaText3.exists());
			 
			 Assert.assertEquals(true , deloma11File.exists());
			 
			 Assert.assertEquals(true , folderDeloma.exists());

		/*
		 * Third scenario:
		 *  -H: 
		 * 	  --deloma
		 *  		  ---deloma12 (false)			
	 	 * 						 ----delomaText4.txt dff
		 */
		//create Folder H:\\test\\deloma\\deloma_12
		Ordner deloma12 = TestUtil.getOrdner("H:\\test\\deloma\\deloma_12" , ordSet);
		File deloma12File = new File(deloma12.getPfad());
		TestUtil.createSysFolder(deloma12File);
		
			//create File H:\\test\\deloma\\deloma_12\\delomaText4.txt
			File delomaText4 = new File("H:\\test\\deloma\\deloma_12\\delomaText4.txt");
			TestUtil.createSysFile(delomaText4);
			
			// test delete deloma_10 
			 testerDao.deleteFiles();
			 
			 Assert.assertEquals(true , delomaText4.exists());
			 
			 Assert.assertEquals(true , folderDeloma10.exists());

			 Assert.assertEquals(true , folderDeloma.exists());
	
	
		// clear			 
		TestUtil.deleteSysFile(folderDeloma);
	}
}
