package tp1.control;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class InitialConfiguration {
	
	public static final InitialConfiguration NONE = new InitialConfiguration();
	private List<String> descriptions;
	
	private InitialConfiguration() {}
	
	private InitialConfiguration(List<String> descriptions) {
		this.descriptions = descriptions;
	}
	
	public static InitialConfiguration readFromFile(String filename) throws FileNotFoundException, IOException 
	{
		
	}
	
	public List<String> getShipDescription(){
		return Collections.unmodifiableList(descriptions);
	}
	
	
}