/* Copyright © 2017- Kasan All Rights Reserved. */
package jp.co.kasan.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/**
 * パスワード関連のユーティリティ。
 * @author rued97
 */
public class PasswordUtils {

	/**
	 * パスワードをハッシュ化します。
	 * @param password 対象のパスワード
	 * @param salt ソルト
	 * @return ハッシュ化パスワード
	 */
	public static byte[] hash(String password, String salt) {

		char[] passwordCharArray = password.toCharArray();

		// ソルトはSHA-256でハッシュ化することで必ず32バイトになるようにしている。
		byte[] hashedSalt;
		try {
			MessageDigest messageDigest;
			messageDigest = MessageDigest.getInstance("SHA-256");
			messageDigest.update(salt.getBytes());
			hashedSalt = messageDigest.digest();
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}

		// ストレッチングのアルゴリズムPBKDF2を使用している。HMAC-SHA-256がPBKDF2内で使用されている。
		PBEKeySpec keySpec = new PBEKeySpec(passwordCharArray, hashedSalt, 10000, 256);
		SecretKeyFactory secretKeyFactory;
		try {
			secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
		SecretKey secretKey;
		try {
			secretKey = secretKeyFactory.generateSecret(keySpec);
		} catch (InvalidKeySpecException e) {
			throw new RuntimeException(e);
		}
		return secretKey.getEncoded();
	}

}
