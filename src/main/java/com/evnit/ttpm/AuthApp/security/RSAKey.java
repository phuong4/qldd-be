package com.evnit.ttpm.AuthApp.security;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import org.apache.commons.codec.binary.Base64;

public class RSAKey {
	private static final String privateKey = "MIIBVAIBADANBgkqhkiG9w0BAQEFAASCAT4wggE6AgEAAkEAoavWyNzxuex7JkJcotS286rJixUc4T9KFtz/Re+I/b9VmvlDEDY016KxyjxFBWR+UygJnG9Boo0HC6oLz15DzQIDAQABAkAIVebOFm40KtgyoctK7fK833pivGTvzCaSxTQ4M+B5TLYeI3wlTT19XwiMmzdHdDoV9CXnYKcicgsDb7d49DihAiEA3JP5admqH20YpA11/Ld5LuBauvolajC+ojIZgAhIvxUCIQC7ojCxh6i8zdLd9XZctZle4+WjOGgbjBULkNp/Jn3f2QIhAMdYA+QhjpnwIoDAmYzW03IF2Hl6cC3T97Tlyh3nXrcxAiBKzJd4zknYKn0betUalFJ134gWD1CBWtCw8agI9EFN0QIgJQKT0NgHhyZq766q4RWBP/Tckkaj44NSnzrSb+zZ664=";
	private static final String publicKey = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAKGr1sjc8bnseyZCXKLUtvOqyYsVHOE/Shbc/0XviP2/VZr5QxA2NNeisco8RQVkflMoCZxvQaKNBwuqC89eQ80CAwEAAQ==";

	public static PrivateKey getPrivateKey() {
		try {
			// Base64 decode the result
			byte[] pkcs8EncodedBytes = Base64.decodeBase64(privateKey);
			// extract the private key
			PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(pkcs8EncodedBytes);
			KeyFactory kf = KeyFactory.getInstance("RSA");
			PrivateKey privKey = kf.generatePrivate(keySpec);
			return privKey;
		} catch (Exception e) {
			return null;
		}
	}

	public static PublicKey getPublicKey() {
		try {
			byte[] pkcs8EncodedBytes = Base64.decodeBase64(publicKey);
			X509EncodedKeySpec keySpecX509 = new X509EncodedKeySpec(pkcs8EncodedBytes);
			KeyFactory kf = KeyFactory.getInstance("RSA");
			PublicKey pubKey = kf.generatePublic(keySpecX509);
			return pubKey;
		} catch (Exception e) {
			return null;
		}

	}
}
