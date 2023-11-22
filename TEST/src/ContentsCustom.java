import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
//거래소 내용 커스텀
public class ContentsCustom {

	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub

		String aa = "{\"COMPS\":[{\"COMP_TYPE\":\"TEXT\",\"COMP_DETAIL\":{\"MENTIONS\":[],\"HASHTAGS\":[],\"CONTENTS\":\"asgdgsd\"}}]}";
		
		
		JSONObject obj = (JSONObject) new JSONParser().parse(aa);
		
		JSONArray jr = (JSONArray) obj.get("COMPS");
		
		Map<String, Object> toMap = (Map<String, Object>) jr.get(0);
		Map<String, String> finMap = (Map<String, String>) toMap.get("COMP_DETAIL");
		
		 
		
		
		System.out.println(finMap.get("CONTENTS"));
		
	}

}
