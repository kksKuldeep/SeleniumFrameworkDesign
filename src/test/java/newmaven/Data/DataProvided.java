package newmaven.Data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataProvided {
	
	public List<HashMap<String, String>> getJSonDataToMap(String filePath) throws IOException
	{
		// read Json data to String 
		String jsonContents=FileUtils.readFileToString(new File(filePath),
				StandardCharsets.UTF_8);
	
		
		//Converting String to HashMap
		ObjectMapper Mapper=new ObjectMapper();
		List<HashMap<String, String>> mData=Mapper.readValue(jsonContents, new TypeReference<List<HashMap<String, String>>>(){
		});
		return mData;
		
	}
	

}
