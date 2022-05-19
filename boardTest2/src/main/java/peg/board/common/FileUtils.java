package peg.board.common;

import java.io.File;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import lombok.extern.slf4j.Slf4j;
import peg.board.vo.FileVO;


@Component
@Slf4j
public class FileUtils {
	public List<FileVO> paseFileInfo(int idx, MultipartHttpServletRequest multipartRequest) throws Exception{
		if(multipartRequest == null) {
			return null;
		}
		
		List<FileVO> fileList = new ArrayList<FileVO>();
		//당일 날짜로 새로운 폴더를 만들고 그 폴더 안에 저장함.
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyyMMdd");
		ZonedDateTime current = ZonedDateTime.now();
		String path = "C:/eGovFrame-3.10.0/upload" + current.format(format);
		File file = new File(path);
		
		if(file.exists()==false) {
			file.mkdir();
		}
		
		Iterator<String> iterator = multipartRequest.getFileNames();
		
		String newfilename, originalfileName, extension;
		extension = "undefined";
		
		while(iterator.hasNext()) {
			List<MultipartFile> list = multipartRequest.getFiles(iterator.next());
			for(MultipartFile multipartFile : list) {
				if(multipartFile.getSize() > 0) {
					//파일 확장자 확인 : nio, Apache Tika로 확인
					extension = FilenameUtils.getExtension(multipartFile.getOriginalFilename());
					log.debug("==============================================");
					log.debug("file extension : " + extension);
					newfilename = Long.toBinaryString(System.nanoTime());// 중복되는 파일이름을 막기위해 나노초 사용
					FileVO filevo = new FileVO();
					filevo.setIdx(idx);
					filevo.setOriginalName(multipartFile.getOriginalFilename());
					filevo.setFilename(newfilename);
					filevo.setFilepath(path+"/" + newfilename +"."+ extension);
					fileList.add(filevo);
					log.debug("board idx : " + idx);
					log.debug("file idx : " + filevo.getIdx());
					log.debug("file path : " + filevo.getFilepath());
					log.debug("==============================================");
					
					file = new File(path + "/" + newfilename+"."+ extension);
					multipartFile.transferTo(file);//업로드 된 파일을 위치에 저장
				}
			}
		}
		return fileList;
	}
}
