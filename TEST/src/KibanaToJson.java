import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class KibanaToJson {

	
	public static void main(String[] args) throws ParseException {
		KibanaToJson k = new KibanaToJson();
		k.run();
	}
	
	@SuppressWarnings("unchecked")
	public void run() throws  ParseException {
		//1. 키바나에서 복사한 파일을 불러와 string으로 생성.
		File file = new File("C:\\Users\\은채\\Documents\\카카오톡 받은 파일\\A.txt");	
		String data = "";

		try {
			FileReader fr = new FileReader(file);
			StringBuffer bf = new StringBuffer();
			BufferedReader bfr = new BufferedReader(fr);
			
			String currLine = "";
			while ((currLine = bfr.readLine()) != null) {
				bf.append(currLine.trim());
			}
			bfr.close();
			data = "[" + bf.toString()+ "]"; 
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//2. 생성한 string을 json 파싱 -> list에 담아줌.
		JSONParser jp2 = new JSONParser();
		JSONArray jr = (JSONArray) jp2.parse(data);
		
		List<HashMap<String, String>> list = new ArrayList<HashMap<String,String>>();
		
		for(HashMap<String, Object> map : (List<HashMap<String, Object>>) jr) {
			list.add((HashMap<String, String>) map.get("_source"));
		}	
		//3. list를 새 json 파일로 써줌
		try {
			FileOutputStream fileOutputStream = new FileOutputStream("C:\\Users\\은채\\Desktop\\영풍\\kibana2-C.json");	
			OutputStreamWriter out = new OutputStreamWriter(fileOutputStream, "UTF-8");
			BufferedWriter bfw = new BufferedWriter(out);
				
			for(int i = 0; i < list.size(); i++) {
				bfw.write(list.get(i).toString());
				bfw.write(System.lineSeparator());
				bfw.flush();
			}
			
			bfw.close();
				
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   
	}
	
	
}
