package supratimdas.data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader {
	
	public List<HashMap<String, String>> getJsonDataToMap() throws IOException
	{
		// read json file from the path and storing that into a string
		// in the path we are giving json file path of purchase order
		String jsonContent= FileUtils.readFileToString(new File(System.getProperty("user.dir")+"\\src\\test\\java\\supratimdas\\data\\PurchaseOrder.json"), StandardCharsets.UTF_8);
	
	// now we need to convert the json string to a hashmap
		
		ObjectMapper mapper= new ObjectMapper();
	
		List<HashMap<String, String>> data= mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>(){});
	
	     return data;
	}

}
