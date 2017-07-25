package data;

public class Frog {
	private String name;
	private String lifespanYears;
	private String region;

	public Frog() {
	}
	
	// Frog constructor for initializing fields
	public Frog(String name, String lifespanYears, String region) {
		super();
		this.name = name;
		this.lifespanYears = lifespanYears;
		this.region = region;
		
	}

	public String getName() {
		return name;
	}
	public String getlifespanYears() {
		return lifespanYears;
	}
	public String getRegion() {
		return region;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void setLifespanYears(String lifespanYears) {
		this.lifespanYears = lifespanYears;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	@Override
	public String toString() {
		return "Frog [name=" + name + ", lifespanYears=" + lifespanYears + ", region=" + region + "]";
	}



}
