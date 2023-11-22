//package test;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.simple.parser.ParseException;

import Sysget.FileInput;
import Sysget.HtmlTagRemove;
import Sysget.JsonParse;

public class FileTst {

	public static void main(String[] args) throws IOException, ParseException {
		// TODO Auto-generated method stub
		System.out.println("++++��");
		String url = "http://127.0.0.1:6501/goods_himart/restSearch.jsp?query=����Ÿ��&index=goods_himart";
		
		FileInput file = new FileInput();
		String data = file.urlToFile(url); //url �����͸� �� �پ� ��Ʈ�����ۿ� �׾Ƽ� ��Ʈ������ ����
		
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
		for(Map<String, String> map : list) {
			for(Map.Entry<String, String> entry : map.entrySet()) {
				//System.out.println(entry.getValue());
			}
		}
		
	}

}
