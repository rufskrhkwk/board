package pegsystem.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;


/**
 * @Class Name	: FileUtil.java
 * @Description : 파일 컨트롤 Util Class
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
public class FileUtil {

	
	/**
     * path에 해당하는 디렉토리가 없으면 디렉토리 생성
     * @param path
     */
    public void makeTemploryFolder(String path) {
    	File tempFolder = new File(path);
    	if(!tempFolder.isDirectory()) tempFolder.mkdirs();
    }
    
    
    /**
     * 폴더명에 쓸수없는 특수문자는 str로 치환해서 리턴
     * @param folderName : C:\abcde
     * @param str		 : _
     * @return
     */
    public String folderNameReplaceAll(String folderName, String str) {
    	if(folderName == null || "".equals(folderName)) {
    		return "";
    	} else {
    		folderName = folderName.replaceAll("\\\\", str);
    		folderName = folderName.replaceAll("/", str);
    		folderName = folderName.replaceAll(":", str);
    		folderName = folderName.replaceAll("[*]", str);
    		folderName = folderName.replaceAll("[?]", str);
    		folderName = folderName.replaceAll("\"", str);
    		folderName = folderName.replaceAll("<", str);
    		folderName = folderName.replaceAll(">", str);
    		folderName = folderName.replaceAll("[|]", str);
    		return folderName;
    	}
    }

    
    /**
     * targetDirectoryPath에 해당하는 디렉토리안의 파일, 하위 폴더까지 전부 삭제한다.
     * rootDirectoryDelete가 true이면 targetDirectoryPath까지 삭제
     * @param targetDirectoryPath
     * @param rootDirectoryDelete
     */
    public void deleteFileInDirectory(String targetDirectoryPath, boolean rootDirectoryDelete) { 
    	// 삭제 대상 ROOT 폴더
    	File file = new File(targetDirectoryPath);
    	
    	// 폴더내 파일을 배열로 가져온다.
    	File[] tempFile = file.listFiles();
    	if(tempFile.length > 0) {
    		for(int i=0; i < tempFile.length; i++) {
    			if(tempFile[i].isFile()) tempFile[i].delete();
    			else deleteFileInDirectory(tempFile[i].getPath(), false);	// 재귀함수
    			
    			tempFile[i].delete();
    		}
    		
    		// root 디렉토리 삭제여부
    		if(rootDirectoryDelete) file.delete();
    	}
    }
    
    
    /**
     * String을 downloadPath에 파일로 생성한다.
     * @param data
     * @param downloadPath : C:\tmp\aa.txt
     * @return
     */
    public int fileGenerate(String data, String downloadPath) {
		BufferedWriter bw = null;
		FileWriter fw = null;
		try {
			fw = new FileWriter(downloadPath);
			bw = new BufferedWriter(fw);
			bw.write(data);
		} catch(IOException e) {
			e.printStackTrace();
			return -999;
		} finally {
			if(bw != null) try{ bw.close(); } catch (IOException e) { e.printStackTrace(); }
			if(fw != null) try{ fw.close(); } catch (IOException e) { e.printStackTrace(); }
		}
		
		return 1;
	}
    
    
    /**
	 * MultipartFile객체를 File객체로 변환한다.
	 * @param multipartfile
	 * @return
	 */
    public static File multipartfileToFile(MultipartFile multipartfile) {
		File convertFile = null;
		try {
			convertFile = new File(multipartfile.getOriginalFilename());
			multipartfile.transferTo(convertFile);
		} catch(IOException ioe) {
			ioe.printStackTrace();
		}
		return convertFile;
	}
    
    
    /**
	 * 확장자 리턴
	 * @param multipartfile
	 * @return
	 */
    public static String getExtension(MultipartFile file) {
    	String fileName = file.getOriginalFilename();
    	String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
    	return ext;
    }
    
    
    
    
}