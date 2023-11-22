//package kr.co.proten.custom.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kr.co.proten.common.util.FileUtil;
import kr.co.proten.common.util.StringUtil;
import kr.co.proten.custom.ClassName;
import kr.co.proten.filter.FileContent;

public class Extract implements ClassName{


	public List<HashMap<String, String>> customDataObject(String columnData) throws Exception {
		List<HashMap<String, String>> resultArr = new ArrayList();

		String[] columnDataArr = null;
		
		
		columnDataArr = StringUtil.split(columnData, ",");
		
		if (columnDataArr != null) {
			//List<String> attfileid = StringUtil.splitArray(columnDataArr[0], "^", 0);
			//List<String> fileInfo = StringUtil.splitArray(columnDataArr[1], "^", 0);
			
			for(int i = 0; i < columnDataArr.length; i++){ //배열 다시
				
				
				HashMap<String, String> fileData = new HashMap();
				String fId = columnDataArr[0];
				String fPath = columnDataArr[1];
				String fName = FileUtil.getFileName(fPath);
				fileData.put("id", fId);
				fileData.put("name", fName);
				fileData.put("summary", new FileContent(true).getFilterData(fPath).substring(0,10));
				fileData.put("attach", new FileContent(true).getFilterData(fPath));
				resultArr.add(fileData);
		
			}
			
			boolean flag = false;
			
			
			

			return resultArr;
		} else {
			return resultArr;
		}
		
		/**
		if (columnData == null) {
			return resultArr;
		} else {
			columnDataArr = StringUtil.split(columnData, "|");
			if (columnDataArr != null && columnDataArr.length >= 2) {
				List<String> attfileid = StringUtil.splitArray(columnDataArr[0], "^", 0);
				List<String> fileInfo = StringUtil.splitArray(columnDataArr[1], "^", 0);

				for (int idx = 0; idx < fileInfo.size(); ++idx) {
					HashMap<String, String> fileData = new HashMap();
					String fId = (String) attfileid.get(idx);
					String fPath = (String) fileInfo.get(idx);
					String fName = FileUtil.getFileName(fPath);
					String fExt = FileUtil.getFileExt(fName);
					fileData.put("attfileid", fId);
					fileData.put("attfilename", fName);
					fileData.put("attfileext", fExt);
					fileData.put("attach", new FileContent(true).getFilterData(fPath));
					resultArr.add(fileData);
				}

				return resultArr;
			} else {
				return resultArr;
			}
		}
		**/
	}
	
	public static void main(String args[]) throws Exception {
		Extract cus = new Extract();
		List<HashMap<String, String>> resultArr =  cus.customDataObject("ec^proten|E:\\�λ�����ҽ�\\�λ�����.txt^proten");
		System.out.println("#####################");
		System.out.println(resultArr.get(0));
	}

	@Override
	public String customData(String var1) throws Exception {
		// TODO Auto-generated method stub
		Extract cus = new Extract();
		List<HashMap<String, String>> resultArr =  cus.customDataObject("ec^proten|E:\\�λ�����ҽ�\\�λ�����.txt^proten");
		

		
	
		
		return resultArr.get(0).get("attach");
	}

	@Override
	public String customDataMemory(String var1, Map<String, String[]> var2) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> customDataAfter(String var1) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}