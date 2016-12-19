package Json;

import java.util.List;

import DataBase.DbOperation;
import DataBase.Dbcontent;
import DataBase.Username;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonOperation {
	public String tojson(String datatype,String caller,String dest,String date){
		ObjectMapper mapper = new ObjectMapper();
       //JsonGenerator generator = mapper.getJsonFactory().createJsonGenerator(System.out,JsonEncoding.UTF8);
		DbOperation op = new DbOperation();
		try {
			if(datatype.equals("all")){
				List<Dbcontent> contents = op.getdbcontent(caller,dest,date);
				//System.out.println(mapper.writeValueAsString(contents));
				return mapper.writeValueAsString(contents);
			}else if(datatype.equals("caller_id_name")){
				List<Username> allcallers = op.getalluser("caller_id_name");
				//System.out.println(mapper.writeValueAsString(allcallers));
				return mapper.writeValueAsString(allcallers);
			}else if(datatype.equals("destination_number")){
				List<Username> alldest = op.getalluser("destination_number");
				//System.out.println(mapper.writeValueAsString(alldest));
				return mapper.writeValueAsString(alldest);
			}
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return null;
//       for(Dbcontent content:contents){
//          try {
//        	  //generator.writeObject(person);
//        	  //mapper.writeValue(System.out, content);
//        	  System.out.println(mapper.writeValueAsString(content)); 
//          } catch (JsonGenerationException e) {
//        	  // TODO Auto-generated catch block
//        	  e.printStackTrace();
//        	  System.out.println(e);
//          } catch (JsonMappingException e) {
//        	  // TODO Auto-generated catch block
//        	  e.printStackTrace();
//        	  System.out.println(e);
//          } catch (IOException e) {
//        	  // TODO Auto-generated catch block
//        	  e.printStackTrace();
//        	  System.out.println(e);
//           }
//          System.out.println("----------------"); 
//       }
	}
}
