package pegsystem.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;


/**
 * @Class Name	: ZipUtil.java
 * @Description : ZIP파일 압축/해제 Util Class
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
public class ZipUtil {

	// 압축대상경로
	private static String ZIP_FROM_PATH = "";
	
	
	
	
    /**
     * targetPath 디렉토리의 파일 모두를 zipFileName으로 압축한다.
     * @param zipFileName
     * @param targetPath
     * @return
     */
    public int zipFileGenerate(String targetPath, String zipFileName) {
    	ZIP_FROM_PATH = targetPath;
    	File targetDirectory = new File(targetPath);
    	if(targetDirectory.isDirectory()) {
    		File[] targetFiles = targetDirectory.listFiles();
    		String zipFilePath = targetPath + zipFileName;
    		
    		ZipOutputStream zipOut = null;
    		FileOutputStream fileOut = null;
    		try {
    			fileOut = new FileOutputStream(zipFilePath);
    			zipOut = new ZipOutputStream(fileOut);
    			
    			for(int i=0; i<targetFiles.length; i++) {
    				File target = targetFiles[i];
//    				makeZipFile(targetPath, target, zipOut);
    				makeZipFile("", target, zipOut);
    			}
    			
    		} catch(IOException e) {
    			e.printStackTrace();
    			return -999;
    		} finally {
    			if(zipOut != null) try { zipOut.close(); } catch (IOException e) { e.printStackTrace(); }
    			if(fileOut != null) try { fileOut.close(); } catch (IOException e) { e.printStackTrace(); }
    		}
    	}
    	return 1;
    }
    
    
    /**
     * ZipOutputStream를 넘겨 받아서 하나의 압축파일로 만든다.
     * @param parent 상위폴더명
     * @param file 압축할 파일
     * @param zipOut 압축전체스트림
     * @throws IOException
     */
    private void makeZipFile(String parent, File file, ZipOutputStream zipOut) {
        byte[] buf = new byte[4096];
        int read;
        BufferedInputStream bis = null;
        FileInputStream fis = null;
        String file_name = file.getName(); 
 
        try {
        	if(file.isFile()) {
        		ZipEntry entry = new ZipEntry(parent + file_name);
        		zipOut.putNextEntry(entry);
        		
        		fis = new FileInputStream(file);
        		bis = new BufferedInputStream(fis);
        		
        		while((read = bis.read(buf, 0, 2048)) != -1) {
        			zipOut.write(buf, 0, read);
        		}
        		
        		zipOut.flush();
        		zipOut.closeEntry();
        	} else if(file.isDirectory()) {
        		String filePath = file.getPath();
        		String parentString = filePath.replace(ZIP_FROM_PATH, "");
        		parentString = parentString.substring(0, parentString.length() - file_name.length());
        		ZipEntry entry = new ZipEntry(parentString + file.getName() + "/");
        		zipOut.putNextEntry(entry);
        		
        		String[] list = file.list();
        		if(list != null) {
        			int len = list.length;
        			for(int i=0; i<len; i++) {
        				String entryName = entry.getName();
        				makeZipFile(entryName, new File(file.getPath() + "/" + list[i]), zipOut);
        			}
        		}
        	}
        } catch(IOException e) {
        	e.printStackTrace();
        } finally {
        	if(bis != null) try { bis.close(); } catch (IOException e) { e.printStackTrace(); }
        	if(fis != null) try { fis.close(); } catch (IOException e) { e.printStackTrace(); }
        }
    }
    
    
    
    /**
     * 압축을 해제 한다
     * @param zip_name
     * @param dir_name
     */
    public boolean zipFileExtract(String zip_name, String dir_name) {
        boolean result = false;
        byte[] buf = new byte[4096];
        ZipEntry entry = null;
        ZipInputStream zipstream = null;
        FileOutputStream out = null;
        if(!(dir_name.charAt(dir_name.length() - 1) == '/')) dir_name += "/";
 
        File dir = new File(dir_name);
        if(dir.exists()) if(!dir.isDirectory()) dir.mkdirs();
        else return false;
//        boolean isDirExists = destDir.exists();
//        boolean isDirMake = destDir.mkdirs();
 
        try {
        	String full_path = dir_name + zip_name;
            zipstream = new ZipInputStream(new FileInputStream(full_path));
            while((entry = zipstream.getNextEntry()) != null) {
                int read = 0;
                File entryFile;
 
                // 디렉토리의 경우 폴더를 생성한다.
                if(entry.isDirectory()) {
                	File folder = new File(dir_name + entry.getName());
                    if(!folder.exists()) folder.mkdirs();
                    continue;
                } else {
                	entryFile = new File(dir_name + entry.getName());
                }
 
                if(!entryFile.exists()) {
//                	boolean isFileMake = entryFile.createNewFile();
                	entryFile.createNewFile();
                }
 
                out = new FileOutputStream(entryFile);
                while((read = zipstream.read(buf, 0, 2048)) != -1) {
                	out.write(buf, 0, read);
                }
 
                zipstream.closeEntry();
            }
            
            result = true;
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            if(out != null) try { out.close(); } catch (IOException e) { e.printStackTrace(); }
            if(zipstream != null) try { zipstream.close(); } catch (IOException e) { e.printStackTrace(); }
        }
 
        return result;
    }

}
