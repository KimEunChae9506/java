import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.OutputStream;
import java.util.HashSet;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class UniquePathsToExcel {
//json to excel. +중복제거
    public static void main(String[] args) {
        String jsonFilePath = "C:\\Users\\은채\\Desktop\\test.json";
        String excelFilePath = "C:\\Users\\은채\\Desktop\\gensol\\output.xlsx";
  
        try {
            JSONParser parser = new JSONParser();
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("Path Data");
            Set<String> uniquePaths = new HashSet<>();

            try (BufferedReader br = new BufferedReader(new FileReader(jsonFilePath))) {
                String line;

                while ((line = br.readLine()) != null) {
                    JSONObject jsonObject = (JSONObject) parser.parse(line);

                    String path = (String) jsonObject.get("path");

                    // 중복 체크 후 고유한 "path" 값만 추가
                    if (!uniquePaths.contains(path)) {
                        uniquePaths.add(path);
                        Row row = sheet.createRow(sheet.getLastRowNum() + 1);
                        Cell cell = row.createCell(0);
                        cell.setCellValue(path);
                    }
                }
            }

            try (FileOutputStream fileOut = new FileOutputStream(excelFilePath)) {
                workbook.write(fileOut);
            }

            System.out.println("Excel 파일이 생성되었습니다.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}