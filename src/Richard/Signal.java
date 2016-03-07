package Richard;

public class Signal {
	
	private String name;
	private String direction;

	public Signal(String name, String direction){
		
		this.name = new String(name);
		this.direction = new String(direction);
	}
	
	public String getName(){
		return new String(name);
	}
	
	public String getDirection(){
		return new String(direction);
	}
}
