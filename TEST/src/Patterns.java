import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.*;

public class Patterns {
//정규식
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String filePath = "C:\\Users\\은채\\Desktop\\gensol\\test.txt"; // 실제 파일 경로로 변경해야 합니다.

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            StringBuilder textBuilder = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                textBuilder.append(line);
                textBuilder.append("\n"); // 각 라인을 읽어올 때 줄 바꿈 문자 추가
            }

            String fileContent = textBuilder.toString();
       
            String pattern = "errorPath::(.*?)";
            Pattern regex = Pattern.compile(pattern);
            Matcher matcher = regex.matcher(fileContent);

            while (matcher.find()) {
                String errorMessage = matcher.group(1);
                System.out.println(errorMessage);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

	}

}
