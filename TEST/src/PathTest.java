import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PathTest {
//한미글로벌 이파피루스 확장자변경
	public static void main(String[] args) {
		// TODO Auto-generated method stub
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

        try (FileOutputStream fos = new FileOutputStream("E:\\prosearch2.5\\dbcrawler\\config\\properties\bbs.properties")) {
            targetProperties.store(fos, null);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("날짜와 시간 정보가 b.properties 파일에 복사되었습니다.");
		
		
		String path = "\\\\59.10.116.26\\ECMStorage\\ECM\\sys\\DM\\2023\\10\\04\\00\\11\\57\\51\\64\\N현장 - CCTV 전송방식 검토서 CCTV전송방식보고서.ppdf";
		String title = "N현장 - CCTV 전송방식 검토서 CCTV전송방식보고서.hwp";	
		//String path = "pJPG"; //dwg제외
		path = path.substring(0,path.lastIndexOf("\\") + 1) + title;
		System.out.println(">>>>>>>>>>>"+path);
		String finalPath = "";
		
		if(path.toLowerCase().contains("pfile") || path.toLowerCase().contains("jpg") || path.toLowerCase().contains("jpeg") || path.toLowerCase().contains("png") ||
				path.toLowerCase().contains("pdf") || path.toLowerCase().contains("tif") || path.toLowerCase().contains("hwp") || path.toLowerCase().contains("ppt") 
				|| path.toLowerCase().contains("doc") || path.toLowerCase().contains("xls") || path.toLowerCase().contains("gif") || path.toLowerCase().contains("bmp")) {
			//여기 밑에 replace 부분 함수
			finalPath = replacePath(path);
			System.out.println("="+finalPath.replace("–", ""));
			//여기 pdf 등록함수
		} 
		
		//2개 이상일시..  dec 까지 다 지우고. 
		//아니 못찾을시.. 파일 없을 시 체크하는 함수. if 전체경로로 한 파일이 있을 시 return. 없을 시 경로 붙여서 한 번 더 체크하고 그래도 없을 시 원래 반납
	}
	
	public static String replacePath(String path) {
		
		String forPath = path.substring(0,path.indexOf("Storage\\")+7);
		//String forPath = path.substring(0,path.indexOf("SearchEngine"));
		System.out.println(forPath);
		
		String decPath = "\\SearchEngine\\Dec";
		String backPath = path.substring(path.indexOf("Storage\\")+7);
		System.out.println(backPath);
		if(backPath.toLowerCase().endsWith(".ppdf") ) {
			if(backPath.toLowerCase().endsWith(".pdf.ppdf")) {
				backPath = backPath.replace(".ppdf", "");
			} else {
				backPath = backPath.replace(".ppdf", ".pdf");
			}
			System.out.println(backPath);
		}

		if(backPath.toLowerCase().endsWith(".pfile") ) {
			if(backPath.toLowerCase().endsWith("\\.pfile") ) {
				backPath = existChk(forPath+decPath+backPath);
			}
			
			if(backPath.indexOf("JPG.pfile.pfile") > -1) {
				backPath = backPath.replace("JPG.pfile.pfile", "JPG.pfile.jpg");
			} else {
				backPath = backPath.replace(".pfile", "");
			}
			
		}
		
		if(backPath.toLowerCase().endsWith(".pjpg") ) {
			backPath = backPath.toLowerCase().replace(".pjpg", ".jpg");
		}
		if(backPath.toLowerCase().endsWith(".pjpeg") ) {
			backPath = backPath.replace(".pjpeg", ".jpeg");
		}
		if(backPath.toLowerCase().endsWith(".ppng") ) {
			backPath = backPath.replace(".ppng", ".png");
		}
		if(backPath.toLowerCase().endsWith(".ptif") ) {
			backPath = backPath.replace(".ptif", ".tif");
		}
		if(backPath.toLowerCase().endsWith(".ptiff") ) {
			backPath = backPath.replace(".ptiff", ".tiff");
		}
		if(backPath.toLowerCase().endsWith(".phwp") ) {
			backPath = backPath.replace(".phwp", ".hwp");
		}
		if(backPath.toLowerCase().endsWith(".phwpx") ) {
			backPath = backPath.replace(".phwpx", ".hwpx");
		}
		
		File file = new File(forPath+decPath+backPath);
		if(file.exists()) {
			return forPath+decPath+backPath;
		} else {
			String tempBackPath = backPath.replace("ECMStorage", "EcmStorage");		
			backPath = backPath.replace("ECMStorage", "EcmStorage");			
			File file2 = new File(forPath+decPath+tempBackPath);
			if(file2.exists()) {
				return forPath+decPath+tempBackPath;
			} else {
				return forPath+decPath+backPath;
			}
		}
	}
	
	public static String existChk(String fileName) {
		String[] extArr = {"pdf", "jpg", "tif", "png", "doc", "xls", "ppt"};
		String chkStr = "";
		
		for(String s : extArr) {
			chkStr = fileName.replace("\\.pfile", s);
			File file = new File(chkStr);
			if (file.exists()) {
				break;
	        } 
		}
		return chkStr;
	}

}
