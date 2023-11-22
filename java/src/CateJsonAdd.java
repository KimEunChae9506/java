import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;



public class CateJsonAdd {

	public static void main(String[] args) throws IOException, ParseException {
		// TODO Auto-generated method stub
	
	
		File file = new File("E:\\proSearch2.5_ec\\dbcrawler\\json\\goods_himart\\backup\\cateTest.json");
		Charset ch = Charset.forName("utf-8");
		FileInputStream fin = new FileInputStream(file);
		
		JSONParser jp = new JSONParser();
		JSONArray oriJson = (JSONArray) jp.parse(new InputStreamReader(fin,ch));
		
		
		List<HashMap<String, String>> list = new ArrayList<HashMap<String,String>>();
		
		list = (List<HashMap<String, String>>) oriJson;
	
		for(int i = 0; i < list.size(); i++) {
			String str = list.get(i).get("CATEGORY");
			
			if(!"".equals(str)) {
				String[] multiArr = str.split("\\|");
				String arr[] = CateJsonAdd.split(multiArr[0]);			
				
				String kk = "";
				
				String kk2 = "";
				String kk3 = "";
				String kk4 = "";
				
				String aa = "";
				
				String aa2 = "";
				String aa3 = "";
				String aa4 = "";
				String bb = "";
				
				String bb2 = "";
				String bb3 = "";
				String bb4 = "";
				String cc = "";
				
				String cc2 = "";
				String cc3 = "";
				String cc4 = "";
				
				
				if(arr.length > 0) {
					kk = arr[0];
					kk2 = arr[1];
					
					if(arr.length > 2) {
						kk3 = arr[2];
					}
					if(arr.length > 3) {
						kk4 = arr[3];
					}
					
				}
				
				String arr2[] = null;
				if(multiArr.length > 1) {
					arr2 = CateJsonAdd.split(multiArr[1]);	
					aa = "|"+arr2[0];
					aa2 = "|"+arr2[1];
					aa3 = "|"+arr2[2];
					if(arr2.length == 4) {
						aa4 = "|"+arr2[3];
					}
					
				}
				String arr3[] = null;
				if(multiArr.length > 2) {
					arr3 = CateJsonAdd.split(multiArr[2]);	
					bb = "|"+arr3[0];
					bb2 = "|"+arr3[1];
					bb3 = "|"+arr3[2];
					
					if(arr3.length == 4) {
						bb4 = "|"+arr3[3];
					}
				}
				String arr4[] = null;
				if(multiArr.length > 3) {
					arr4 = CateJsonAdd.split(multiArr[3]);	
					cc = "|"+arr4[0];
					cc2 = "|"+arr4[1];
					cc3 = "|"+arr4[2];
					
					if(arr4.length == 4) {
						cc4 = "|"+arr4[3];
					}
				}
				
				list.get(i).put("dept1", kk);
				
				if(arr.length > 1) {
					list.get(i).put("dept2", kk2);
					list.get(i).put("dept3", kk3);
					list.get(i).put("dept4", kk4);
				} 
//				if(arr.length > 2) {
//					list.get(i).put("dept3", kk3+aa3+bb3+cc3);
//					list.get(i).put("dept4", "");
//				} 
//				if (arr.length > 3) {					
//					list.get(i).put("dept4", kk4+aa4+bb4+cc4);
//				}
			}
			
		}
		
		//list.get(0).put("dept1", value);
		System.out.println(list.size());
	
		JSONArray oriJson2 = (JSONArray) list;
		StringBuffer sb = new StringBuffer();
		
		for(int i = 0; i < list.size(); i++) {
			sb.append(list.get(i));
			sb.append(System.getProperty("line.separator"));
		}
		//FileOutputStream outFile = new FileOutputStream(sb.toString());
		//OutputStreamWriter out = new OutputStreamWriter(outFile);
	
		//InputStream in = new 
		System.out.println(22);
		File file2 = new File("E:\\proSearch2.5_ec\\dbcrawler\\json\\goods_himart\\backup\\cateTest3.json");
		//file2.createNewFile();
		//FileUtils.copyInputStreamToFile(in, file2);
		System.out.println(33);
		Writer write = new OutputStreamWriter(new FileOutputStream(file2), "UTF-8");
	      write.write(sb.toString());
	      write.flush();
	      write.close();
		
	}
	
	public static String[] split(String str) {
		int size = str.split(":").length;
		int multiSize = str.split("\\|").length;
		//System.out.println(size);
		
		String arr[] = new String[size];
		String multiArr[] = new String[multiSize];
		String multiArrSplit[]; //��Ƽ ī�װ��� ���� ī�װ� ������ split(:)�Ѱ�
		arr = str.split(":");	
		/**
		List<String> list = new ArrayList<String>();
		multiArr = str.split("\\|");
		
		String aa = "";
		String bb = "";
		String cc = "";
		String dd = "";
		
		String aaa[] = null;
		String bbb[]= null;
		String ccc[] = null;
		String ddd[]= null;
		if(str.contains("|")) { //A:aa,aaa|B:bb

		aa = multiArr[0];
		aaa = aa.split(":");
		bb = multiArr[1];
		bbb = bb.split(":");
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
		a1 += bbb[0];
		
		if(!"".equals(cc)) {
			a1 += ccc[0];
		}
		if(!"".equals(dd)) {
			a1 += ddd[0];
		}
		
		//System.out.println("===="+a1);
		
		
			//System.out.println(str);
			String fir = "";
			String sec = "";
			for(int i = 0; i < multiArr.length; i++) { //A:aa
				multiArrSplit = multiArr[i].split(":"); //A,aa
				for(int j = 0; j < multiArrSplit.length; j++) {
					fir += multiArrSplit[j];
					if(i < multiArrSplit.length) {
						fir += "|"; // A|B
						//System.out.println("=========="+fir);
					}
				}
			}
		} else {
			arr = str.split(":");		
		}
		
		//1011000000@TV/�����/��Ź��/������:1011030000@��Ź��:1011030100@�Ϲݼ�Ź��(1)
		**/
		
		return arr;
	}

}
