package pegsystem.web.view;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.AbstractView;


/**
 * @Class Name	: RawDataImageView.java
 * @Description : oracle Long Raw type image view
 * 				  bean으로 등록 후 BeanNameViewResolver를 이용해 사용한다.
 * 
 * @author	박진우(jwpark@pegsystem.co.kr)
 * @since	2018.07.18
 * @version	1.0
 * @see
 * Copyright (C) PEGSYSTEM <http://www.pegsystem.co.kr>
 * 
 * 
 * @Modification Information
 *	  Date		  Modifier		Comment
 *  ----------   ----------    -------------------
 *  2018.07.18	  박진우   		최초생성
 */
public class RawDataImageView extends AbstractView {
	
    @Override
    protected void renderMergedOutputModel(Map<String,Object> model , HttpServletRequest request , HttpServletResponse response) throws Exception{
    	byte[] image = (byte[]) model.get("image");
    	int size = request.getContentLength();
    	InputStream is = new ByteArrayInputStream(image);
    	
    	response.setContentType("image/jpeg");
    	response.setContentLength(size);
    	response.setHeader("Content-Disposition", "fileName=getImage.jpg");
    	response.setHeader("Content-Transfer-Encoding", "binary");

    	byte[] bytes = new byte[1024];
    	OutputStream os = response.getOutputStream();
    	while(true) {
    		int red = is.read(bytes, 0, bytes.length);
    		if(red < 0) break;
    		os.write(bytes, 0, red);
    	}
    	
    	os.flush();
    	os.close();
    	is.close();
    }
}
