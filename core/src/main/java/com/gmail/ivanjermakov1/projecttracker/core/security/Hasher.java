package com.gmail.ivanjermakov1.projecttracker.core.security;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.SecureRandom;

public class Hasher {
	
	// The higher the number of iterations the more
	// expensive computing the hash is for us and
	// also for an attacker.
	private static final int iterations = 20 * 1000;
	private static final int saltLen = 32;
	private static final int desiredKeyLen = 256;
	
	/**
	 * Computes a salted PBKDF2 hash of given plaintext password
	 * suitable for storing in a database.
	 * Empty passwords are not supported.
	 */
	public static String getHash(String password) {
		try {
			byte[] salt;
			salt = SecureRandom.getInstance("SHA1PRNG").generateSeed(saltLen);
			// store the salt with the password
			return Base64.encodeBase64String(salt) + "$" + hash(password, salt);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Checks whether given plaintext password corresponds
	 * to a stored salted hash of the password.
	 */
	public static boolean check(String password, String stored) {
		String[] saltAndPass = stored.split("\\$");
		if (saltAndPass.length != 2) {
			throw new IllegalStateException(
					"The stored password have the form 'salt$hash'");
		}
		try {
			return hash(password, Base64.decodeBase64(saltAndPass[0])).equals(saltAndPass[1]);
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * Hashing using PBKDF2 from Sun, an alternative is https://github.com/wg/scrypt
	 * cf. http://www.unlimitednovelty.com/2012/03/dont-use-bcrypt.html
	 */
	private static String hash(String password, byte[] salt) throws Exception {
		if (password == null || password.length() == 0)
			throw new IllegalArgumentException("Empty passwords are not supported.");
		SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
		SecretKey key = f.generateSecret(new PBEKeySpec(
				password.toCharArray(), salt, iterations, desiredKeyLen)
		);
		return Base64.encodeBase64String(key.getEncoded());
	}
	
}