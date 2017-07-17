package data;

import java.util.List;

public interface FrogDAO {
	public Frog getFrogByName(String name);
	public void addFrog(Frog frog);
	public List<Frog> getAllFrogs();
}
