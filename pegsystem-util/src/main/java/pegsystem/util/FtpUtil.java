package pegsystem.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @Class Name	: FtpUtil.java
 * @Description : FTP에 접속해 파일을 다운로드 받는 Util Class
 * 
 * @author	박진우(jwpark@pegsystem.co.kr)
 * @since	2018.03.29
 * @version	1.0
 * @see
 * Copyright (C) PEGSYSTEM <http://www.pegsystem.co.kr>
 * 
 * 
 * @Modification Information
 *	  Date		  Modifier		Comment
 *  ----------   ----------    -------------------
 *  2018.03.29	  박진우   		최초생성
 */
public class FtpUtil {

	
private final static Logger logger = LoggerFactory.getLogger(FtpUtil.class);
	
	private FTPClient ftpClient;
	private String ftp_server_ip;
	private int ftp_server_port;
	private String ftp_user_id;
	private String ftp_user_pass;
	
	public FtpUtil(String ftp_server_ip, int ftp_server_port, String ftp_user_id, String ftp_user_pass) {
		ftpClient = new FTPClient();
		this.ftp_server_ip		= ftp_server_ip;
		this.ftp_server_port	= ftp_server_port;
		this.ftp_user_id		= ftp_user_id;
		this.ftp_user_pass		= ftp_user_pass;
	}
	
	
	
	
	
	public boolean ftpConnectCheck() {
		return ftpClient.isConnected();
	}
	
	/**
	 * FTP 접속
	 * @return
	 */
	public boolean ftpConnect() {
		if(!ftpClient.isConnected()) {
			try {
				ftpClient = new FTPClient();
				ftpClient.setControlEncoding("UTF-8");
				ftpClient.connect(ftp_server_ip, ftp_server_port);
				
				int result_code = ftpClient.getReplyCode();
				if(FTPReply.isPositiveCompletion(result_code)) {
					ftpClient.setSoTimeout(5000);
					boolean isLogin = ftpClient.login(ftp_user_id, ftp_user_pass);
					if(isLogin) {
//					ftpClient.enterLocalActiveMode();
						ftpClient.enterLocalPassiveMode();
						ftpClient.setBufferSize(1024 * 1024);
						ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
					} else {
						logger.info("FTP 서버에 로그인할 수 없습니다.");
					}
				} else {
					ftpClient.disconnect();
					logger.info("FTP 서버에 연결할 수 없습니다.");
				}
			} catch (SocketException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return ftpClient.isConnected(); 
	}
	
	
	public void ftpLogout() {
		try {
			ftpClient.logout();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public boolean ftpDisconnect() {
		try {
			ftpClient.disconnect();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ftpClient.isConnected();
	}
	
	
	
	public int ftpDownload(String downloadPath, String filePath, String fileName) {
		int result = -1;
		BufferedOutputStream bos = null;
		File targetFolerName = null;
		File file = null;
		try {
			targetFolerName = new File(downloadPath);
			if(!targetFolerName.isDirectory()) targetFolerName.mkdirs();
			file = new File(downloadPath, fileName);
			
			ftpClient.changeWorkingDirectory(filePath);
			bos = new BufferedOutputStream(new FileOutputStream(file));
				
			boolean isSuccess = ftpClient.retrieveFile(fileName, bos);
			if(isSuccess) {
				result = 1;
			} else {
				logger.info("파일을 다운로드할 수 없습니다.");
				throw new Exception("파일을 다운로드할 수 없습니다.");
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(bos != null) try { bos.close(); } catch(Exception e) { e.printStackTrace(); }
		}
		
		return result;
	}
}
