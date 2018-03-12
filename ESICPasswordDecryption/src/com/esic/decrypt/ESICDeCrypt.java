package com.esic.decrypt;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.servlet.ServletContext;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("esicDeCrypt")
public class ESICDeCrypt {

	@Value("${keyLocation}")
	private String keyLocation;

	@Autowired
	private ServletContext servletContext;

	private SecretKey getSecretKey() {
		SecretKey key = null;
		ObjectInputStream ois = null;
		FileInputStream fileInputStream = null;
		try {
			fileInputStream = new FileInputStream(new File(servletContext.getRealPath(keyLocation)));
			ois = new ObjectInputStream(fileInputStream);
			Object object = ois.readObject();
			key = (SecretKey) object;
		} catch (Exception ex) {
			key = null;
			System.out.println("Error - ESICDeCrpt - getSecretKey() : " + ex.getMessage());
		} finally {
			try {
				if (ois != null) {
					ois.close();
				}
				if (fileInputStream != null) {
					fileInputStream.close();
				}
			} catch (Exception ex) {
				System.out.println("Finally");
			}

		}
		return key;
	}

	public String getDecryptData(String username) {
		String decryptData = null;
		try {
			Cipher cipher = Cipher.getInstance("DES");
			cipher.init(Cipher.DECRYPT_MODE, getSecretKey());
			byte[] dec = Base64.decodeBase64(username);
			byte[] utf8 = cipher.doFinal(dec);
			decryptData = new String(utf8, "UTF8");
		} catch (Exception ex) {
			System.out.println("Error - ESICDeCrypt - getDecryptData() : " + ex.getMessage());
			decryptData = null;
		}
		return decryptData;
	}
}

/*
 * //byte[] dec = new sun.misc.BASE64Decoder().decodeBuffer(username); byte[]
 * message = username.getBytes("UTF-8"); String encoded =
 * DatatypeConverter.printBase64Binary(message); byte[] decoded =
 * DatatypeConverter.parseBase64Binary(encoded); System.out.println(encoded);
 * System.out.println(new String(decoded, "UTF-8")); Output: aGVsbG8gd29ybGQ=
 * hello world
 */
