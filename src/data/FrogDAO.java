package data;

import java.util.List;

public interface FrogDAO {
	public Frog getFrogByName(String name);
	public void addFrog(Frog frog);
	public void deleteFrog(String frog);
	public void updateFrog(Frog frog);
	public List<Frog> getAllFrogs();
	public Frog getFrogById(int id);
	public void addFrogToDb(Frog frog);
	//public void deleteFrogFromDb(int id);
	
}
