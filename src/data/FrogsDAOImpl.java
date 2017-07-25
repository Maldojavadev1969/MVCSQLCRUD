package data;

import java.io.BufferedReader;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;

//import com.skilldistillery.film.data.Film;

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
	
	//********* get data from frog database ************************************
	private static String url = "jdbc:mysql://localhost:3306/Frogs";
	private String user = "froguser";
	private String pass = "froguser";
	
	public FrogsDAOImpl() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.err.println("Error loading MySQL Driver!!!");
		}
	}
		
		//working with a command object and want to return a frog
		// to display on the result1.jsp
		public Frog getFrogById(int id) {
			Frog frog = null;
			try {
				Connection conn = DriverManager.getConnection(url, user, pass);
				String sql = "SELECT id, name, lifespan, region FROM frog WHERE id = ?";
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setInt(1, id);
				ResultSet rs = stmt.executeQuery();
				if (rs.next()) {
					// get film columns from DB result set and load into Frog object constructor
					frog = new Frog(rs.getString(1), rs.getString(2), rs.getString(3));
				}
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return frog;
		}
	
		
	
	//************ testing area to get frogs from database ************************
		
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

	// the idea is to write a new file over the existing file
	// by updating, deleting or adding a frog to the array and then rewrite the file!

	@Autowired
    ServletContext context;
	@Override
  
	    public void addFrog(Frog frog) {
			
		// this will add a frog to the List
			frogs.add(frog);
		// add a frog to the apache tomcat server csv file.. 
		// will not see changes on the local csv file !!!!!!!
			String path = "/WEB-INF/frogs.csv";
			String filePath = wac.getServletContext().getRealPath(path);
			System.out.println(filePath);

	        try {
	     	
	            PrintWriter pw = new PrintWriter(new FileWriter(filePath), false);
	            {
	            		int i = 0;
	            		pw.println("id,name,lifespanYears,region");
	            		
	            		for (Frog frog1 : frogs) {
	            		  pw.println(i + "," + frog1.getName() + "," + frog1.getlifespanYears() + "," + frog1.getRegion());
	            		  i++;
	            		}
	            }

	           pw.close();
	        }
	        catch (IOException ioe) {
	        	ioe.printStackTrace();	        
	        	}
	    }

	// Read operation of instance of Frog from data that has 
	// been retrieved from csv file!
	@Override
	public Frog getFrogByName(String name) {
		Frog f = null;
		for (Frog frog : frogs) {
			if (frog.getName().equalsIgnoreCase(name)) {
				f = frog;
				//remove frog if delete is clicked
				//if(delete is clicked)
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
	
	@Autowired
    ServletContext context2;
	
	@Override
	public void deleteFrog(String name ) {
			
			
			String path = "/WEB-INF/frogs.csv";
			String filePath = wac.getServletContext().getRealPath(path);
			System.out.println(filePath);

	        try {
	        	
	        	//have  user input frog to be removed!
	        //change this to a regular for loop
	        	
	        	
	        	for (Frog frog : frogs) {
	    			if (frog.getName().equalsIgnoreCase(name)) {
	    				//remove frog from Array list
	    	        	frogs.remove(frog);
	    	        	//frog.get
	    				break;
	    			}
	    		}
	        	
	        	//now print out new csv file with frog removed
	            PrintWriter pw = new PrintWriter(new FileWriter(filePath), false);
	            {
	            	int i = 0;
            		
	            	pw.println("id,name,lifespanYears,region");
            		for (Frog frog1 : frogs) {
            		  pw.println(i + "," + frog1.getName() + "," + frog1.getlifespanYears() + "," + frog1.getRegion());
            		  i++;
            		}
	            }

	           pw.close();
	        }
	        catch (IOException ioe) {
	        	ioe.printStackTrace();	        
	        	}
	    }
	
	@Autowired
    ServletContext context3;
	@Override
  
	    public void updateFrog(Frog frog) {
		
		// list.set(N, obj) --- assigns object to position n in arrray
		
		 String fr = frog.getName();
		 System.out.println(fr);
		 
		 for (int i = 0; i < frogs.size(); i++) {
			 //frog myFrog = new frog();
				if (frogs.get(i).getName().equalsIgnoreCase(fr)) {
					frogs.set(i, frog);

					break;
				}
		 }
		
		//*****************************************************
		// add a frog to the apache tomcat server csv file.. 
		// will not see changes on the local csv file !!!!!!!
			String path = "/WEB-INF/frogs.csv";
			String filePath = wac.getServletContext().getRealPath(path);
			System.out.println(filePath);
			
	        try {
	     	
	            PrintWriter pw = new PrintWriter(new FileWriter(filePath), false);
	            {
	            
	            	int i = 0;
	            	pw.println("id,name,lifespanYears,region");
            		
            		for (Frog frog1 : frogs) {
            		  pw.println(i + "," + frog1.getName() + "," + frog1.getlifespanYears() + "," + frog1.getRegion());
            		  i++;
            		  }
	            }

	           pw.close();
	        }
	        catch (IOException ioe) {
	        	ioe.printStackTrace();	        
	        	}
	    }
	
	

}

