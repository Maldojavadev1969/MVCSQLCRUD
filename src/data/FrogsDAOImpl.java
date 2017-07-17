package data;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;

public class FrogsDAOImpl implements FrogDAO {
	private static final String FILE_NAME ="/WEB-INF/frogs.csv";
	private List<Frog> frogs = new ArrayList<>();
	/*
	 * Use Autowired to have Spring inject an instance
	 * of a WebApplicationContext into this object after
	 * creation.  We will use the WebApplicationContext to
	 * retrieve an ServletContext so we can read from a 
	 * file.
	 */
	@Autowired 
	private WebApplicationContext wac;

	/*
	 * The @PostConstruct method is called by Spring after 
	 * object creation and dependency injection
	 */
	@PostConstruct
	public void init() {
		// Retrieve an input stream from the servlet context
		// rather than directly from the file system
		try (
				InputStream is = wac.getServletContext().getResourceAsStream(FILE_NAME);
				BufferedReader buf = new BufferedReader(new InputStreamReader(is));
			) {
			String line = buf.readLine();
			while ((line = buf.readLine()) != null) {
				String[] tokens = line.split(",");
				String name = tokens[1];
				String lifespanYears = tokens[2];
				String region = tokens[3];
				frogs.add(new Frog(name, lifespanYears, region));
			}
		} catch (Exception e) {
			System.err.println(e);
		}
	}
	
	//***************   TEST AREA   ********************************
	//ObjectOutputStream is = wac.getServletContext().getResourceAsStream(FILE_NAME);
	//BufferedReader buf = new BufferedReader(new InputStreamReader(is));
	
	// the idea is to write a new file over the existing file
	// by updating, deleting or adding a frog to the array and then rewrite the file!

	 
	
	@Autowired
    ServletContext context;
	@Override
  
	    public void addFrog(Frog frog) {
			
			frogs.add(frog);
			
			///MVCFileCRUD/WebContent/WEB-INF/frogs.csv
			String path = "/WEB-INF/frogs.csv";
			String filePath = context.getRealPath(path);
			//****************************************
			
			
//			public void createStudentFile() {
//		        String filePath = wac.getServletContext().getRealPath(FILE_NAME);
//		        System.out.println(filePath);
//		        System.out.println("DAO: " + filePath);
//
//		        try {
//		            PrintWriter out = new PrintWriter(new FileWriter(filePath));
//		            for (Student s : studentList) {
//		                out.println(s.getFirstName() + "," + s.getLastName() + "," + s.getOtherInformation());
//
//		            }
//		            out.close();
//		        } catch (IOException ioe) {
//		            ioe.printStackTrace();
//		        }
			
			//*************************************
			
			
			
			
			
			
	        try {
	        	
	            PrintWriter pw = new PrintWriter(new FileWriter(filePath), false);
	            {
	            	for (Frog frog2 : frogs) {
	            		  pw.println("4," + frog2.getName() + "," + frog2. + "," + frogs.get(index).getRegion());
	  	                //pw.println(frog.getName());
					}
	            }
//	               pw.println("4," + index.getName() + "," + frogs.get(index).getlifespanYears() + "," + frogs.get(index).getRegion());
//	                //pw.println(frog.getName());
//	               
//	            }		
	           //pw.flush();
	           pw.close();
	        }
	        catch (Exception e) {
			System.err.println(e);
	        }
	    }
	    

	//**************       END OF TEST AREA   **********************************

	// Read operation of instance of Frog from data that has 
	// been retrieved from csv file!
	@Override
	public Frog getFrogByName(String name) {
		Frog f = null;
		for (Frog frog : frogs) {
			if (frog.getName().equalsIgnoreCase(name)) {
				f = frog;
				break;
			}
		}
		return f;
	}


	@Override
	public List<Frog> getAllFrogs() {
		// states is a list and now want to return the list
		return frogs;
	}
}

