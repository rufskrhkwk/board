package peg;


import java.io.IOException;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

@SuppressWarnings("restriction")
public class EncryptClass {

	public static void main(String[] args) throws Exception {
		String oracleURL = "jdbc:oracle:thin:@appeon.co.kr:1521:pegsys";
		String oracleUSER = "jungsan";
		String oraclePASS = "jungsan";
		
		String mariaURL = "jdbc:mysql://appeon.co.kr:3316/yeartaxdb";
		String mariaUSER = "peg_adjust2";
		String mariaPASS = "peg_adjust2";
		
		String postgresqlURL = "jdbc:postgresql://appeon.co.kr:65432/postgres";
		String postgresqlUSER = "yeartax";
		String postgresqlPASS = "peg1!2@3$";
		
		EncryptClass e = new EncryptClass();
		System.out.println("oracle url : " + e.propsEncrypt(oracleURL));
		System.out.println("oracle user : " + e.propsEncrypt(oracleUSER));
		System.out.println("oracle pass : " + e.propsEncrypt(oraclePASS));
		System.out.println("");
		System.out.println("maria url : " + e.propsEncrypt(mariaURL));
		System.out.println("maria user : " + e.propsEncrypt(mariaUSER));
		System.out.println("maria pass : " + e.propsEncrypt(mariaPASS));
		System.out.println("");
		System.out.println("postgresql url : " + e.propsEncrypt(postgresqlURL));
		System.out.println("postgresql user : " + e.propsEncrypt(postgresqlUSER));
		System.out.println("postgresql pass : " + e.propsEncrypt(postgresqlPASS));
		
		
		System.out.println("");
		System.out.println("");
		System.out.println("");
		
		System.out.println("oracle url : " + e.jndiEncrypt(oracleURL));
		System.out.println("oracle user : " + e.jndiEncrypt(oracleUSER));
		System.out.println("oracle pass : " + e.jndiEncrypt(oraclePASS));
		System.out.println("");
		System.out.println("maria url : " + e.jndiEncrypt(mariaURL));
		System.out.println("maria user : " + e.jndiEncrypt(mariaUSER));
		System.out.println("maria pass : " + e.jndiEncrypt(mariaPASS));
		System.out.println("");
		System.out.println("postgresql url : " + e.jndiEncrypt(postgresqlURL));
		System.out.println("postgresql user : " + e.jndiEncrypt(postgresqlUSER));
		System.out.println("postgresql pass : " + e.jndiEncrypt(postgresqlPASS));
		
		System.out.println("");
		System.out.println("");
		System.out.println("");
		
		System.out.println("oracle url : " + e.jndiDecrypt(e.jndiEncrypt(oracleURL)));
		System.out.println("oracle user : " + e.jndiDecrypt(e.jndiEncrypt(oracleUSER)));
		System.out.println("oracle pass : " + e.jndiDecrypt(e.jndiEncrypt(oraclePASS)));
		System.out.println("");
		System.out.println("maria url : " + e.jndiDecrypt(e.jndiEncrypt(mariaURL)));
		System.out.println("maria user : " + e.jndiDecrypt(e.jndiEncrypt(mariaUSER)));
		System.out.println("maria pass : " + e.jndiDecrypt(e.jndiEncrypt(mariaPASS)));
		System.out.println("");
		System.out.println("postgresql url : " + e.jndiDecrypt(e.jndiEncrypt(postgresqlURL)));
		System.out.println("postgresql user : " + e.jndiDecrypt(e.jndiEncrypt(postgresqlUSER)));
		System.out.println("postgresql pass : " + e.jndiDecrypt(e.jndiEncrypt(postgresqlPASS)));
	}
	
	
	
	public String propsEncrypt(String str) {
		StandardPBEStringEncryptor standardPBEStringEncryptor = new StandardPBEStringEncryptor();  
		standardPBEStringEncryptor.setAlgorithm("PBEWithMD5AndDES");  
		standardPBEStringEncryptor.setPassword("PEG_JASYPT_PASS");
		return standardPBEStringEncryptor.encrypt(str);
	}
	
	public String jndiEncrypt(String str) {
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(str.getBytes());
	}
	
	public String jndiDecrypt(String str) throws IOException {
		BASE64Decoder decoder = new BASE64Decoder();
		return new String(decoder.decodeBuffer(str));
	}
}
