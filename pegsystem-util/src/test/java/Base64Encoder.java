

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;


public class Base64Encoder {
	

	public static void main(String[] args) throws Exception {
		BASE64Encoder encoder = new BASE64Encoder();
		BASE64Decoder decoder = new BASE64Decoder();
		
		// pegsystem
//		String url = "jdbc:oracle:thin:@appeon.kr:1521:pegsys";
//		String user = "jungsan";
//		String pass = "jungsan";
		
		// 호서대
//		String url = "jdbc:oracle:thin:@134.75.122.30:1521/hoseo";
//		String user = "setts";
//		String pass = "ghtjsetts";
		
		// 선문대
//		String user = "yearend";
//		String pass = "ye1741";
//		
//		String url  = "jdbc:oracle:thin:@(DESCRIPTION=(LOAD_BALANCE=on)"
//					+ "(ADDRESS=(PROTOCOL=TCP)(HOST=203.230.13.69)(PORT=1521))"
//					+ "(ADDRESS=(PROTOCOL=TCP)(HOST=203.230.13.70)(PORT=1521))"
//					+ "(CONNECT_DATA=(SERVICE_NAME=EISDB)(SRVR = DEDICATED)))";
		
		// 아웃소싱
		String url = "jdbc:oracle:thin:@210.123.46.7:1521:kmudb";
		String user = "kyearend";
		String pass = "3KYEAREND)(098";
//		String url = "jdbc:oracle:thin:@192.168.1.10:1521:xe";
//		String user = "jungsan";
//		String pass = "pegsys6057";
		
		
		String encryptURL = encoder.encode(url.getBytes());
		String encryptUSER = encoder.encode(user.getBytes());
		String encrypPASS = encoder.encode(pass.getBytes());
		
		String decryptURL = new String(decoder.decodeBuffer(encryptURL));
		String decryptUSER = new String(decoder.decodeBuffer(encryptUSER));
		String decryptPASS = new String(decoder.decodeBuffer(encrypPASS));
		
		System.out.println("##############################################");
//		System.out.println(url);
		System.out.println("URL : " + encryptURL);
//		System.out.println(decryptURL);
		if(!url.equals(decryptURL)) System.out.println("URL 디코딩 실패");
		System.out.println("##############################################");
//		System.out.println(user);
		System.out.println("USER : " + encryptUSER);
//		System.out.println(decryptUSER);
		if(!user.equals(decryptUSER)) System.out.println("USER 디코딩 실패");
		System.out.println("##############################################");
//		System.out.println(pass);
		System.out.println("PASS :"
				+ " " + encrypPASS);
//		System.out.println(decryptPASS);
		if(!pass.equals(decryptPASS)) System.out.println("PASSWORD 디코딩 실패");
		System.out.println("##############################################");
	}
}
