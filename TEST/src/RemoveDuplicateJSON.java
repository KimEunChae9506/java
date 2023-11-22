
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.util.HashSet;
import java.util.Set;
//json 파일에 중복 된 행 지워줌
public class RemoveDuplicateJSON {
    public static void main(String[] args) {
        String inputFilePath = "C:\\Users\\은채\\Desktop\\이파피루스\\wrongFile.json";
        String outputFilePath = "C:\\Users\\은채\\Desktop\\이파피루스\\output.json";
        // 경로에 \\ 2번 써줘야
        Set<String> uniquePaths = new HashSet<>();
        JSONArray resultArray = new JSONArray();

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                JSONObject jsonObject = new JSONObject(line);
                String path = jsonObject.getString("id");
                if (uniquePaths.add(path)) {
                    resultArray.put(jsonObject);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {
            for (int i = 0; i < resultArray.length(); i++) {
                writer.write(resultArray.getJSONObject(i).toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Duplicates removed and saved to " + outputFilePath);
    }
}