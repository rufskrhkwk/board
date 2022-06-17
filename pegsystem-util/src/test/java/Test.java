import pegsystem.jdbc.Encryptor;
import pegsystem.util.RandomStringBuilder;
import pegsystem.util.StringUtil;

public class Test {

	
	public static void main(String[] args) throws Exception {
		/*
		 * String defaultSecretKey = "asdfo23jda3sds"; Encryptor aes = new
		 * Encryptor(defaultSecretKey); String testid = "testid"; String a =
		 * aes.encrypt(testid); System.out.println("Id:" + a); String testpassword =
		 * "testpassword"; String b = aes.encrypt(testpassword);
		 * System.out.println("Pw:" + b); String c = aes.decrypt(a);
		 * System.out.println(c); String d = aes.decrypt(b); System.out.println(d);
		 */
		
		String str = StringUtil.lpad(2, 10);
		System.out.println(str);
	}
}
