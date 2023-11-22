

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class FileInput {

	public String urlToFile(String addr) throws IOException{
		String data = "";
		try {
			URL url = new URL(addr);
			
			InputStream inputStream = url.openStream();
			
			StringBuffer bf = new StringBuffer();
			
			BufferedReader bfr = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
			
			String currLine = "";
			
			while((currLine = bfr.readLine()) != null) {
				bf.append(currLine);
			}
			
			data = bf.toString();
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}
}
