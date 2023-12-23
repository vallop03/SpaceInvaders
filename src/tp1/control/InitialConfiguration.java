package tp1.control;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;


public class InitialConfiguration {
	
	public static final InitialConfiguration NONE = new InitialConfiguration();
	private List<String> descriptions;
	
	private InitialConfiguration() {}
	
	private InitialConfiguration(List<String> descriptions) {
		this.descriptions = descriptions;
	}
	
	public static InitialConfiguration readFromFile(String filename) throws FileNotFoundException, IOException 
	{
		try (BufferedReader in = new BufferedReader(new FileReader(filename)))
		{
			 String l = in.readLine();
			 List<String> config = new ArrayList<String>();
			 while (l != null) 
			 {
				 config.add(l);
				 l = in.readLine();
			 }
			 return new InitialConfiguration(config);
		}
	}
	
	public List<String> getShipDescription(){
		return Collections.unmodifiableList(descriptions);
	}
	
	
}