
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import Sysget.FileInput;
import Sysget.JsonParse;
//api json 결과에서 원하는 컬럼만 출력

public class FileTst {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException, ParseException {
	
		String url = "http://127.0.0.1:7501/goods_himart/restSearch.jsp?query=W17KT&index=goods_himart";
		
		FileInput file = new FileInput();
		String data = file.urlToFile(url); //url �����͸� �� �پ� ��Ʈ�����ۿ� �׾Ƽ� ��Ʈ������ ����
		
		JSONParser jp2 = new JSONParser();
		JSONObject json = (JSONObject) jp2.parse(data);
		JSONArray jr = (JSONArray) json.get("result");
		//List<Object> llist = (List<Object>) json.get("result");
		
		for(int i = 0; i < jr.size(); i++) {
			JSONObject json2 = (JSONObject) jr.get(i);
			JSONArray jr2 = (JSONArray) json2.get("items");
			
			List<Map<String, String>> map = (List<Map<String, String>>) jr2;
			System.out.println(map.get(i).get("BRNDNM"));
		}
		
		
		System.out.println(json);
		
		System.out.println(json.get("result"));
		System.out.println(jr);

		JsonParse jp = new JsonParse();
		List<Map<String,String>> list = jp.jsonToList(data); //���̽� �������� ��ȯ�� �����͸� �� �������� �ٲ� ����Ʈ�� ����. ����Ʈ 1 : �� N
		
		List<String> keyList = new ArrayList<String>();
		
		for(int i = 0; i < list.size(); i++) {
			keyList.addAll(list.get(i).keySet()); //���̽� �������� ��� Ű���� ����
		}
		
		/**
		for(Map<String, String> map : list) {
			//Map<String, String> map = list.get(0); // ���� ����� 1�̴ϱ� ���� for �� �� ��������
			for(int j = 0; j < keyList.size(); j++) {
				String key = keyList.get(j);
				String value = map.get(keyList.get(j));
				if(value == null || "".equals(value)) {
					value = "null";
				}
				value = new HtmlTagRemove().tagRemove(value); //html �±װ� ������ ����
				System.out.println(key + " = " + value);
			}
		}
		**/
		
		String tst = "";
		for(Map<String, String> map : list) {
			for(Map.Entry<String, String> entry : map.entrySet()) {
				tst += entry.getValue();
				tst += "^";
				
			}
			
			System.out.println(tst);
		}
		
	}

}
