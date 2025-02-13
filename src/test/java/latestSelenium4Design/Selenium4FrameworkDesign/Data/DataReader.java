package latestSelenium4Design.Selenium4FrameworkDesign.Data;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader {

	public List<HashMap<String, String>> getJsonDataToHashMap() throws IOException {
		// read json to string
		String jsonData = Files.readString(Path.of(System.getProperty("user.dir")
				+ "\\src\\test\\java\\latestSelenium4Design\\Selenium4FrameworkDesign\\Data\\PurchaseOrder.json"));
		// convert jsondata to hashmap using jackson databind api(do add dependency in pom from maven- name of it "jackson databind"
//		why this api, Jackson Databind is the best way to convert JSON to HashMap in Java. Handles both simple & nested JSON structures easily. 
//		No manual parsing needed – just use objectMapper.readValue().
		
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String,String>> data = mapper.readValue(jsonData, new TypeReference<List<HashMap<String,String>>>() {
		});
		return data;	

	}

}
