package tk.security.des;

import java.security.Key;
import java.security.Security;
import java.util.Date;
import java.util.Scanner;

import javax.crypto.Cipher;

/**
 * 
 * 加密和解密
 * 
 */
public class DesUtils {

	/** 字符串默认键 */
	private static String strDefaultKey = "toddler";

	/** 加密工具 */
	private Cipher encryptCipher = null;

	/** 解密工具 */
	private Cipher decryptCipher = null;

	// 验证码图片中可以出现的字符集，可以根据需要修�?
	private char mapTable[] = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
			'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
			'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8',
			'9'};

	public static String byteArr2HexStr(byte[] arrB) throws Exception {
		int iLen = arrB.length;
		// 每个byte用两个字符才能表示，�?��字符串的长度是数组长度的两�?
		StringBuffer sb = new StringBuffer(iLen * 2);
		for (int i = 0; i < iLen; i++) {
			int intTmp = arrB[i];
			// 把负数转换为正数
			while (intTmp < 0) {
				intTmp = intTmp + 256;
			}
			// 小于0F的数�?��在前面补0
			if (intTmp < 16) {
				sb.append("0");
			}
			sb.append(Integer.toString(intTmp, 16));
		}
		return sb.toString();
	}

	public static byte[] hexStr2ByteArr(String strIn) throws Exception {
		byte[] arrB = strIn.getBytes();
		int iLen = arrB.length;

		// 两个字符表示�?��字节，所以字节数组长度是字符串长度除�?
		byte[] arrOut = new byte[iLen / 2];
		for (int i = 0; i < iLen; i = i + 2) {
			String strTmp = new String(arrB, i, 2);
			arrOut[i / 2] = (byte) Integer.parseInt(strTmp, 16);
		}
		return arrOut;
	}

	public DesUtils() throws Exception {
		this(strDefaultKey);
	}

	@SuppressWarnings("restriction")
	public DesUtils(String strKey) throws Exception {
		Security.addProvider(new com.sun.crypto.provider.SunJCE());
		Key key = getKey(strKey.getBytes());

		encryptCipher = Cipher.getInstance("DES");
		encryptCipher.init(Cipher.ENCRYPT_MODE, key);

		decryptCipher = Cipher.getInstance("DES");
		decryptCipher.init(Cipher.DECRYPT_MODE, key);
	}

	public byte[] encrypt(byte[] arrB) throws Exception {
		return encryptCipher.doFinal(arrB);
	}

	public String encrypt(String strIn) throws Exception {
		return byteArr2HexStr(encrypt(strIn.getBytes()));
	}

	public byte[] decrypt(byte[] arrB) throws Exception {
		return decryptCipher.doFinal(arrB);
	}

	public String decrypt(String strIn) throws Exception {
		return new String(decrypt(hexStr2ByteArr(strIn)));
	}

	private Key getKey(byte[] arrBTmp) throws Exception {
		// 创建�?��空的8位字节数组（默认值为0�?
		byte[] arrB = new byte[8];

		// 将原始字节数组转换为8�?
		for (int i = 0; i < arrBTmp.length && i < arrB.length; i++) {
			arrB[i] = arrBTmp[i];
		}

		// 生成密钥
		Key key = new javax.crypto.spec.SecretKeySpec(arrB, "DES");

		return key;
	}

	public String randomPassword()
	  {
		  long temp = new Date().getTime();
		  String strResult = "";
		  for(int i = 0 ; i < 8 ; i++)
		  {
			  strResult += mapTable[(int) (temp*Math.random()%mapTable.length)];
		  }
		  return strResult;
	  }

	public static void main(String[] args) {
		// System.out.println(getLoginKey("6413000000"));
		Scanner scan = new Scanner(System.in);
		System.out.println("欢迎使用密码加密程序！！！请输入原始密码,并按回车");
		String str = scan.nextLine();
		try {
			DesUtils des = new DesUtils();// 自定义密�?
			String strIn = des.encrypt(str);
			System.out.println("加密后的字符" + strIn);
			System.out.println("解密后的字符" + des.decrypt(strIn));
//			for(int i = 0 ; i < 10; i ++)
//			{
//				System.out.println(des.randomPassword());
//			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
