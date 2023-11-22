import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class FtpTest {
	public static Session session = null;
	public static Channel channel = null;
	public static ChannelSftp channelSftp = null;

	public static void main(String[] args) {
		
		try {
			//SFTP 서버 접속
			//접속할 SFTP 서버 IP, SFTP 포트, 계정 ID, 계정비밀번호, Pem Key
			sftpInit("192.168.0.19", 22, "demo", "vmfhxps1!", "");
			
			//SFTP서버 경로내 파일 찾기 (C300.D20221219.*인 파일명을 찾는다.)
			//String fileName = sftpSearchFile("/workspace/real/C300.D20221219");
			//System.out.println(fileName);
			
			//SFTP 파일 다운로드
			//다운로드 SFTP 서버 경로, 다운로드 받을 로컬 파일 경로
			sftpFileDownload("/home/demo/prosearch2.5/test5.pdf", "E:\\prosearch2.5\\");
			
			//SFTP 파일 업로드
			//업로드 SFTP 서버 경로, 업로드할 파일 경로, 업로드 파일명
			//sftpFileUpload("/workspace/real/", "/ediya/work/", "C300.D20221219");
			
			ArrayList<String> files = new ArrayList<String>();
			files.add("D500.D221219_1");
			files.add("D500.D221219_2");
			files.add("D500.D221219_3");
            
			//SFTP 다중 파일 업로드
			//업로드 SFTP 서버 경로, 업로드할 파일 경로, 업로드 파일명 리스트
			//sftpMultiFileUpload("/workspace/real/", "/ediya/work/", files);

		}catch(Exception e) {
			System.out.println(e);
		}finally {
			//sftpUtil.disconnect();
		}	
	}
	
	/**
	 * SFTP 접속
	 * 
	 * @param ip
	 * @param port
	 * @param id
	 * @param pw
	 * @param privateKey
	 */
	public static void sftpInit(String ip, int port, String id, String pw, String privateKey) throws Exception {
		String connIp = ip;		//접속 SFTP 서버 IP
		int connPort = port;	//접속 PORT
		String connId = id;		//접속 ID
		String connPw = pw;		//접속 PW
		int timeout = 10000; 	//타임아웃 10초
		
		JSch jsch = new JSch();
		try {
			InetAddress local;
            local = InetAddress.getLocalHost();

            //key 인증방식일경우
            if(null != privateKey && !"".equals(privateKey)) {
            	jsch.addIdentity(privateKey);
            }
            
            //세션객체 생성 
            session = jsch.getSession(connId, connIp, connPort);
            
            if(null == privateKey || "".equals(privateKey)) {
            	session.setPassword(connPw); //password 설정
            }
            
            //세션관련 설정정보 설정
            java.util.Properties config = new java.util.Properties();
            
            //호스트 정보 검사하지 않는다.
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);
            session.setTimeout(timeout); //타임아웃 설정
            
            //log.info("connect.. " + connIp);
            System.out.println("connect.. " + connIp);
            session.connect();	//접속
            System.out.println(1);
            channel = session.openChannel("sftp");	//sftp 채널 접속
            channel.connect();
            
		} catch (JSchException e) {
            //log.error(e);
			System.out.println(e);
            throw e;
        } catch (Exception e) {
            //log.error(e);
        	System.out.println(e);
            throw e;
		}
		channelSftp = (ChannelSftp) channel;
	}
	
	/**
	 * SFTP 서버 파일 다운로드 
	 * @param downloadPath
	 * @param localFilePath
	 */
	public static void sftpFileDownload(String downloadPath, String localFilePath) throws Exception {
		byte[] buffer = new byte[1024];
        BufferedInputStream bis = null;
        OutputStream os = null;
        BufferedOutputStream bos = null;
        
        try {
            //SFTP 서버 파일 다운로드 경로
            String cdDir = downloadPath.substring(0, downloadPath.lastIndexOf("/") + 1);
            //파일명
            String fileName = downloadPath.substring(downloadPath.lastIndexOf("/") + 1, downloadPath.length());
            
            channelSftp.cd(cdDir);

            File file = new File(downloadPath);
            bis = new BufferedInputStream(channelSftp.get(fileName));

            //파일 다운로드 SFTP 서버 -> 다운로드 서버
            File newFile = new File(localFilePath+fileName);
            os = new FileOutputStream(newFile);
            bos = new BufferedOutputStream(os);
            
            int readCount;
            
            while ((readCount = bis.read(buffer)) > 0) {
                bos.write(buffer, 0, readCount);
            }
            
            //log.debug("sftpFileDownload success.. ");
            System.out.println("sftpFileDownload success.. ");
        } catch (Exception e) {
        	//log.error(e);
        	System.out.println(e);
            throw e;
        } finally {
        	try {
				bis.close();
				bos.close();
	            os.close();
			} catch (IOException e) {
				//log.error(e);
				System.out.println(e);
			}
		}
	}


}
