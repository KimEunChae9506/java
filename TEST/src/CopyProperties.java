import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class CopyProperties {
//properties 내용 복사
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	        // a.properties 파일에서 날짜와 시간 정보 읽기
	        Properties sourceProperties = new Properties();
	        try (FileInputStream fis = new FileInputStream("E:\\prosearch2.5\\dbcrawler\\config\\properties\\@statistics.properties")) {
	            sourceProperties.load(fis);
	        } catch (IOException e) {
	            e.printStackTrace();
	            return;
	        }

	        // 날짜와 시간 정보 가져오기
	        String dateTime = sourceProperties.getProperty("@datetime");

	        // b.properties 파일에 날짜와 시간 정보 쓰기
	        Properties targetProperties = new Properties();
	        targetProperties.setProperty("@datetime", dateTime);

	        try (FileOutputStream fos = new FileOutputStream("E:\\prosearch2.5\\dbcrawler\\config\\properties\\bbs.properties")) {
	            targetProperties.store(fos, null);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }

	        System.out.println("날짜와 시간 정보가 b.properties 파일에 복사되었습니다.");
	        
	      
	    }

}
