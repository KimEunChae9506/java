package Sysget;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class FilePrint {

	public static void main(String[] args) throws IOException, ParseException {
		// TODO Auto-generated method stub
		//String str="1017000000@생활/주방용품:1017010000@생활용품:1017010800@우산|1030000000@패션/잡화:1030030000@패션의류/잡화:1030030400@패션소품:1030030403@우산/양산";
		
		String s = "aa@bb";
		
		String[] ss = s.split(",");
		
		System.out.println(ss[0]);
		
		
		
		
		String str = "";
		String str2 = "";
		
		File file2 = new File("E:\\proSearch2.5_ec\\dbcrawler\\json\\goods_himart\\backup\\cateTest.json");
		Charset ch = Charset.forName("utf-8");
		FileInputStream fin = new FileInputStream(file2);
		
		JSONParser jpp = new JSONParser();
		JSONArray oriJson = (JSONArray) jpp.parse(new InputStreamReader(fin,ch));
		
		
		List<HashMap<String, String>> list2 = new ArrayList<HashMap<String,String>>();
		
		list2 = (List<HashMap<String, String>>) oriJson;
		List<String> list3 = new ArrayList<String>();
		for(int i = 0; i < list2.size(); i++) {
			str = list2.get(i).get("CATEGORY");
			str2 = list2.get(i).get("GOODSNO");
			
			if(!"".equals(str)) {				
				int size = str.split(":").length;
				int multiSize = str.split("\\|").length;
				//System.out.println(size);
				
				String arr[] = new String[size];
				String multiArr[] = new String[multiSize];
				String multiArrSplit[]; //멀티 카테고리내 각각 카테고리 내에서 split(:)한거
				
				
				multiArr = str.split("\\|");
				
				String aa = "";
				String bb = "";
				String cc = "";
				String dd = "";
				
				String aaa[] = null;
				String bbb[]= null;
				String ccc[] = null;
				String ddd[]= null;
				//if(str.contains("|")) { //A:aa,aaa|B:bb

				aa = multiArr[0];
				aaa = aa.split(":");
				if(multiArr.length > 1) {
					bb = multiArr[1];
					bbb = bb.split(":");
				}
				
				if(multiArr.length > 2) {
					cc = multiArr[2];
					ccc = cc.split(":");
				}
				if(multiArr.length > 3) {
					dd = multiArr[3];
					ddd = dd.split(":");
					for(int k=0; k < multiArr.length; k++) {
						System.out.println(str2);
						System.out.println(multiArr[k]);
					}
				}
				
				String a1 = "";
				String b1 = "";
				String c1 = "";
				String d1 = "";
				
				a1 = aaa[0];
				
				if(!"".equals(bb)) {
					a1 += "|";
					a1 += bbb[0];
				}
				if(!"".equals(cc)) {
					a1 += "|";
					a1 += ccc[0];
				}
				if(!"".equals(dd)) {
					a1 += "|";
					a1 += ddd[0];
				}
				
				//System.out.println("===="+a1);
				//list3.add(a1);
			}
			//File file3 = new File("E:\\proSearch2.5_ec\\dbcrawler\\json\\goods_himart\\backup\\cateTest3.json");
			//file2.createNewFile();
			//FileUtils.copyInputStreamToFile(in, file2);
			
			/*
			StringBuffer sb = new StringBuffer();
			
			for(int j = 0; j < list3.size(); j++) {
				sb.append(list3.get(j));
				sb.append(System.getProperty("line.separator"));
			}
			Writer write = new OutputStreamWriter(new FileOutputStream(file3), "UTF-8");
		      write.write(sb.toString());
		      write.flush();
		      write.close();*/
		}
		
		int size = str.split(":").length;
		int multiSize = str.split("\\|").length;
		//System.out.println(size);
		
		String arr[] = new String[size];
		String multiArr[] = new String[multiSize];
		String multiArrSplit[]; //멀티 카테고리내 각각 카테고리 내에서 split(:)한거
		
		//List<String> list = new ArrayList<String>();
		multiArr = str.split("\\|");
		
		String aa = "";
		String bb = "";
		String cc = "";
		String dd = "";
		
		String aaa[] = null;
		String bbb[]= null;
		String ccc[] = null;
		String ddd[]= null;
		//if(str.contains("|")) { //A:aa,aaa|B:bb

		aa = multiArr[0];
		aaa = aa.split(":");
		
		if(multiArr.length > 1) {
			bb = multiArr[1];
			bbb = bb.split(":");
		}
		if(multiArr.length > 2) {
			cc = multiArr[3];
			ccc = cc.split(":");
		}
		if(multiArr.length > 3) {
			dd = multiArr[4];
			ddd = dd.split(":");
		}
		
		String a1 = "";
		String b1 = "";
		String c1 = "";
		String d1 = "";
		
		a1 = aaa[0];
		
		
		if(!"".equals(bb)) {
			a1 += "|";
			a1 += bbb[0];
		}
		if(!"".equals(cc)) {
			a1 += "|";
			a1 += ccc[0];
			
		}
		
		if(!"".equals(dd)) {
			a1 += "|";
			a1 += ddd[0];
		}
		
		//System.out.println("===="+a1);
		
		FileInput file = new FileInput();
		
		String url = "http://127.0.0.1:6501/goods_himart/restSearch.jsp?query=lg&index=goods_himart";
		String data = file.urlToFile(url);
		
		JsonParse jp = new JsonParse();
		List<Map<String,String>> list = jp.jsonToList(data);
		
		List<String> keyList = new ArrayList<String>();
		
		for(int i = 0; i < list.size(); i++) {
			keyList.addAll(list.get(i).keySet());
		}
		
		for(Map<String, String> map : list) {
			for(int j = 0; j < keyList.size(); j++) {
				String key = keyList.get(j);
				String value = map.get(keyList.get(j));
				if(value == null || "".equals(value)) {
					value = "null";
				}
				value = new HtmlTagRemove().tagRemove(value);
				System.out.println(key + " = " + value);
			}
		}

		

	}

}
