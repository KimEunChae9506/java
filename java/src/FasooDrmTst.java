//package test;

import java.io.File;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasoo.adk.packager.WorkPackager;

import kr.co.proten.common.ArgsInfo;
import kr.co.proten.common.util.FileUtil;
import kr.co.proten.common.util.StringUtil;
import kr.co.proten.custom.ClassName;
import kr.co.proten.filter.FileContent;

public class FasooDrmTst implements ClassName{
	private static final Logger log = LoggerFactory.getLogger(FasooDrmTst.class);
	private static FileContent fileContent = null;
	
	public static boolean init = false;
	public static String drm_fsdinit_path = "\\\\10.1.63.93\\c$\\fsdinit";	
	
	public static String DRMFileDir = "C:\\Users\\??�?\\Desktop\\?��?��?��?���??��\\drm?��?��";
	
	@Override
	public String customData(String fileFullPath) throws Exception {
		String retData = "";
		//System.loadLibrary("D:\\fasoo\\key\\lib\\64\\f_fcwpkg_jni.dll");
		if(!FasooDrmTst.init) {
			FasooDrmTst.DRMFileDir =ArgsInfo.homePath+"drmfile"+FileUtil.fileseperator+ArgsInfo.selectid+FileUtil.fileseperator;
			File file = new File(FasooDrmTst.DRMFileDir);
			file.mkdirs();
			log.debug("Drm File Dir is : " + FasooDrmTst.DRMFileDir);
			//FasooDrmTst.drm_fsdinit_path = StringUtil.checkNull(System.getProperty("fsdinit_path"));
			FasooDrmTst.drm_fsdinit_path = StringUtil.checkNull(drm_fsdinit_path);
			log.debug("fsdinit_path : " + FasooDrmTst.drm_fsdinit_path);
			if("".equals(FasooDrmTst.drm_fsdinit_path )) {
				System.out.println("Error -Dfsdinit_path is null");
				System.exit(-1);
			}
			FasooDrmTst.fileContent =  new FileContent(true); 
			FasooDrmTst.fileContent.setFilterRoot(ArgsInfo.homePath+"filter"+FileUtil.fileseperator+ArgsInfo.selectid+FileUtil.fileseperator);
			FasooDrmTst.init = true;
		}
		try {
			File fileData = new File(fileFullPath);
			if(!fileData.exists()) {
				log.debug("File Not Find :"+fileData.getAbsolutePath());
				return "";
			}
			boolean bret = false;
			boolean iret = false; 
			int retVal = 0;

			WorkPackager objWorkPackager = new WorkPackager();
			//objWorkPackager.setCharset("eucKR");
			objWorkPackager.SetLogInfo(40, "./logs/");

			
			objWorkPackager.setOverWriteFlag(false);

			retVal = objWorkPackager.GetFileType(fileFullPath);
			log.debug("The file type is " + FileTypeStr(retVal) + "["+retVal+"]"+" .");  
			log.debug("Start file filter.");
			if (retVal == 103) {
				
				Hashtable htable = objWorkPackager.GetFileHeader(fileFullPath);
				String SOURCE_SERVERID     = htable.get("CPID").toString();
				String SOURCE_SECURITYCODE = htable.get("misc2-info").toString();

				log.debug("SERVERID : " + SOURCE_SERVERID);
				log.debug("SECURITYCODE : " + SOURCE_SECURITYCODE);
				log.debug("fileFullPath : " + fileFullPath);

				
				iret = objWorkPackager.IsSupportFile(FasooDrmTst.drm_fsdinit_path,
								SOURCE_SERVERID,
								fileFullPath);
				
				log.debug("Support extension check  : "+ iret );
				log.debug("FasooDrmTst.DRMFileDir+fileData.getName()	 : "+ FasooDrmTst.DRMFileDir+fileData.getName()	 );
				
				
				//if (iret) {
					
					bret = objWorkPackager.DoExtract(
							FasooDrmTst.drm_fsdinit_path,					
											SOURCE_SERVERID,				
											fileFullPath,			
											FasooDrmTst.DRMFileDir+fileData.getName()		
											);
					
					log.debug("Decrypt Result : " + bret);
					log.debug("Decrypt Doc : " + objWorkPackager.getContainerFilePathName());
					log.debug("Error Code : " + objWorkPackager.getLastErrorNum());
					log.debug("Error Value : " + objWorkPackager.getLastErrorStr());
					retData = fileContent.getFilterData(FasooDrmTst.DRMFileDir+fileData.getName());
					File _extFile = new File(FasooDrmTst.DRMFileDir+fileData.getName());
					if(_extFile.exists()) {
						//_extFile.delete();
					}
           /*
				}
				else {
					System.out.println("If the extension is not supported, it cannot be decrypted.["+ iret +"]");
				}
        */
			}
			else {
				log.debug("It is not an FSN file.["+ retVal + "]");
				retData = fileContent.getFilterData(fileFullPath);
			}
		} catch (Exception e) {
			log.debug("File Drm Error ["+e.toString()+"]");
			
			return "";
		} finally{

		}
		
		return retData;
	}
	
	@Override
	public String customDataMemory(String data, Map<String, String[]> resultMemory) throws Exception {
		// TODO Auto-generated method stub
		return "";
	}
	@Override
	public List<String> customDataAfter(String data) throws Exception {
		// TODO Auto-generated method stub
		return new ArrayList<String>();
	}

	@Override
	public String customDataObject(String data) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public static String FileTypeStr(int i) {
		String ret = null;
		switch(i)
		{
	    	case 20 : ret = "20"; break;
	    	case 21 : ret = "21";  break;
	    	case 22 : ret = "22"; break;
	    	case 29 : ret = "29";  break;
	    	case 26 : ret = "26";       	break;
	    	case 105: ret = "105";  	break;
	    	case 106: ret = "106";			break;	    	
	    	case 101: ret = "101";   	break;
	    	case 104: ret = "104";    	break;
	    	case 103: ret = "103.";       	break;
		}
		return ret;		
	}
}
