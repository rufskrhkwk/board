package pegsystem.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.springframework.web.multipart.MultipartFile;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;


/**
 * 파일을 Base64 Encoding 처리.
 * Encoding된 String을 이미지로 변경
 * @author james
 */
public class ImageUtil {

	
	
	/**
	 * 파일 Full Name or 확장자.
	 * @param fileName	
	 * @return
	 */
	public boolean isImageFile(String fileName) {
		String imageFileExt = "";
		if(fileName.indexOf( ".") > 0) {
			imageFileExt = fileName.substring( fileName.lastIndexOf("."), fileName.length());
		} else {
			imageFileExt = fileName;
		}
		
		if("jpg".equalsIgnoreCase(imageFileExt) || "jpeg".equalsIgnoreCase(imageFileExt) 
			 ||"gif".equalsIgnoreCase(imageFileExt) || "png".equalsIgnoreCase(imageFileExt)) {
			return true;
		}
		
		return false;
	}
	
	
    /**
     * Decode string to image
     * @param imageString The string to decode
     * @return decoded image
     */
	public BufferedImage decodeToImage(String imageString) {
		ByteArrayInputStream bis = null;
        BufferedImage image = null;
        byte[] imageByte;
        try {
            BASE64Decoder decoder = new BASE64Decoder();
            imageByte = decoder.decodeBuffer(imageString);
            bis = new ByteArrayInputStream(imageByte);
            image = ImageIO.read(bis);
            bis.close();
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
        	if(bis != null) try { bis.close(); } catch(Exception e) { e.printStackTrace(); }
        }
        
        return image;
    }
    
	
    /**
     * 
     * Filt to Base64 Encoding
     * @param image
     * @param type
     * @return
     */
    public String encodeToString(File image, String type) {
    	BufferedImage originalImage = null;
		try {
			originalImage = ImageIO.read(image);
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	return encodeToString(originalImage, type);
    }

    
    /**
     * Encode image to string
     * @param image The image to encode
     * @param type jpeg, bmp, ...
     * @return encoded string
     */
	public String encodeToString(BufferedImage image, String type) {
        String imageString = null;
        ByteArrayOutputStream bos = null;
        try {
        	bos = new ByteArrayOutputStream();
            ImageIO.write(image, type, bos);
            byte[] imageBytes = bos.toByteArray();

            BASE64Encoder encoder = new BASE64Encoder();
            imageString = encoder.encode(imageBytes);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        	if(bos != null) try { bos.close(); } catch (IOException e) { e.printStackTrace(); }
        }
        
        return imageString;
    }
	
	
	/**
	 * 이미지 파일의 가로, 세로 사이즈 리턴
	 * 해당 파일이 이미지가 아니면 -1 리턴
	 * 
	 * 	type 0: 가로
	 * 		 1: 세로
	 * @param image
	 * @param type
	 * @return
	 */
	public int getImageSize(MultipartFile image, int type) {
		int result = 0;
		BufferedImage bufferedImage;
		try {
			bufferedImage = ImageIO.read(image.getInputStream());
			if(bufferedImage != null) {
				if(type < 1) result = bufferedImage.getWidth();	// 가로
				else result = bufferedImage.getHeight();		// 세로
			} else {
				result = -1;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	
	
	
//    public static void main (String args[]) throws IOException {
//    	// Image --> String
//    	BufferedImage img = ImageIO.read(new File("c:\\pdf\\bruno.jpg"));
//    	String imgstr = encodeToString(img, "jpg");
//    	System.out.println(imgstr);
//    	
//    	// String --> Image
//    	BufferedImage newImg = decodeToImage(imgstr);
//    	ImageIO.write(newImg, "jpg", new File("c:\\pdf\\CopyOfTestImage.jpg"));
//    }
}
