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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class LogRead {
//로그 중 에러부분만 담아 파일생성
	
	public static void main(String[] args) throws ParseException {
		LogRead k = new LogRead();
		k.run();
	}
	
	@SuppressWarnings("unchecked")
	public void run() throws  ParseException {
		//1. Ű�ٳ����� ������ ������ �ҷ��� string���� ����.
		File file = new File("E:\\prosearch1.0\\log\\elasticsearch\\prosearch.log");	
		String data = "";

		try {
			FileReader fr = new FileReader(file);
			StringBuffer bf = new StringBuffer();
			BufferedReader bfr = new BufferedReader(fr);
			
			String currLine = "";
			
			boolean flag = false;
			while ((currLine = bfr.readLine()) != null) {
				if(currLine.indexOf("ERROR") > -1 || currLine.indexOf("WARN") > -1) {
					flag = true;
				}
				if(currLine.indexOf("INFO") > -1) {
					flag = false;
				}
				if(flag) {
					System.out.println(currLine);
				}
				
				//bf.append(currLine.trim());
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
		
		
	}
	
	
}
