
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ExtExtract {
//json 에서 id 만 1000개씩 추출
    public static void main(String[] args) throws IOException {
        // 입력 JSON 파일 경로
        String inputFilePath = "C:\\Users\\은채\\Desktop\\20230904131358646-C.json";

        // ObjectMapper 초기화
        ObjectMapper objectMapper = new ObjectMapper();

        // id 값을 저장할 문자열 초기화
        StringBuilder text = new StringBuilder();

        // 1000개씩 처리할 카운터 초기화
        int count = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(inputFilePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                // JSON 문자열을 JsonNode로 파싱
                JsonNode jsonNode = objectMapper.readTree(line);

                // id 필드 추출
                String id = jsonNode.get("id").asText();

                // 문자열에 추가
                text.append("'").append(id).append("',").append(" ");

                // 카운터 증가
                count++;

                // 1000개씩 처리할 때마다 결과 출력
                if (count % 1000 == 0) {
                    System.out.println("Processed " + count + " records.");
                    // text 문자열 초기화 (메모리 절약을 위해)
                    text.append("\n");
                }
            }
            
            //File extFile = new File("C:\\Users\\\\은채\\\\Desktop\\test.txt");
            String extFile = "C:\\Users\\\\은채\\\\Desktop\\test.txt";
            
			try {
				FileOutputStream fileOutputStream = new FileOutputStream(extFile, true);
				
				try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(extFile))) {
		            writer.write(text.toString());
		        }
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
        }

        // 최종 결과 출력
        System.out.println("Final result:");
        //System.out.println(text.toString());
    }
}