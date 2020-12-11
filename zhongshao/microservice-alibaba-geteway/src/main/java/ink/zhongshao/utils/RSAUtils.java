/**
 * 
 */
package ink.zhongshao.utils;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zs
 * @date 2020年12月11日
 */
public class RSAUtils {
	public static final String KEY_ALGORITHM = "RSA";
	public static final String SIGNATURE_ALGORITHM = "MD5withRSA";

	private static final String PUBLIC_KEY = "RSAPublicKey";
	private static final String PRIVATE_KEY = "RSAPrivateKey";

	public static byte[] decryptBASE64(String key) {
		return Base64.decodeBase64(key);
	}

	public static String encryptBASE64(byte[] bytes) {
		return Base64.encodeBase64String(bytes);
	}

	/**
	 * 用私钥对信息生成数字签名
	 *
	 * @param data       加密数据
	 * @param privateKey 私钥
	 * @return
	 * @throws Exception
	 */
	public static String sign(byte[] data, String privateKey) throws Exception {
		// 解密由base64编码的私钥
		byte[] keyBytes = decryptBASE64(privateKey);
		// 构造PKCS8EncodedKeySpec对象
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
		// KEY_ALGORITHM 指定的加密算法
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		// 取私钥匙对象
		PrivateKey priKey = keyFactory.generatePrivate(pkcs8KeySpec);
		// 用私钥对信息生成数字签名
		Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
		signature.initSign(priKey);
		signature.update(data);
		return encryptBASE64(signature.sign());
	}

	/**
	 * 校验数字签名
	 *
	 * @param data      加密数据
	 * @param publicKey 公钥
	 * @param sign      数字签名
	 * @return 校验成功返回true 失败返回false
	 * @throws Exception
	 */
	public static boolean verify(byte[] data, String publicKey, String sign) throws Exception {
		// 解密由base64编码的公钥
		byte[] keyBytes = decryptBASE64(publicKey);
		// 构造X509EncodedKeySpec对象
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
		// KEY_ALGORITHM 指定的加密算法
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		// 取公钥匙对象
		PublicKey pubKey = keyFactory.generatePublic(keySpec);
		Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
		signature.initVerify(pubKey);
		signature.update(data);
		// 验证签名是否正常
		return signature.verify(decryptBASE64(sign));
	}

	public static byte[] decryptByPrivateKey(byte[] data, String key) throws Exception {
		// 对密钥解密
		byte[] keyBytes = decryptBASE64(key);
		// 取得私钥
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
		// 对数据解密
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.DECRYPT_MODE, privateKey);
		return cipher.doFinal(data);
	}

	/**
	 * 解密<br>
	 * 用私钥解密
	 *
	 * @param data
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static byte[] decryptByPrivateKey(String data, String key) throws Exception {
		return decryptByPrivateKey(decryptBASE64(data), key);
	}

	/**
	 * 解密<br>
	 * 用公钥解密
	 *
	 * @param data
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static byte[] decryptByPublicKey(byte[] data, String key) throws Exception {
		// 对密钥解密
		byte[] keyBytes = decryptBASE64(key);
		// 取得公钥
		X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		Key publicKey = keyFactory.generatePublic(x509KeySpec);
		// 对数据解密
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.DECRYPT_MODE, publicKey);
		return cipher.doFinal(data);
	}

	/**
	 * 加密<br>
	 * 用公钥加密
	 *
	 * @param data
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptByPublicKey(String data, String key) throws Exception {
		// 对公钥解密
		byte[] keyBytes = decryptBASE64(key);
		// 取得公钥
		X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		Key publicKey = keyFactory.generatePublic(x509KeySpec);
		// 对数据加密
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);
		return cipher.doFinal(data.getBytes());
	}

	/**
	 * 加密<br>
	 * 用私钥加密
	 *
	 * @param data
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptByPrivateKey(byte[] data, String key) throws Exception {
		// 对密钥解密
		byte[] keyBytes = decryptBASE64(key);
		// 取得私钥
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
		// 对数据加密
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.ENCRYPT_MODE, privateKey);
		return cipher.doFinal(data);
	}

	/**
	 * 取得私钥
	 *
	 * @param keyMap
	 * @return
	 * @throws Exception
	 */
	public static String getPrivateKey(Map<String, Key> keyMap) throws Exception {
		Key key = (Key) keyMap.get(PRIVATE_KEY);
		return encryptBASE64(key.getEncoded());
	}

	/**
	 * 取得公钥
	 *
	 * @param keyMap
	 * @return
	 * @throws Exception
	 */
	public static String getPublicKey(Map<String, Key> keyMap) throws Exception {
		Key key = keyMap.get(PUBLIC_KEY);
		return encryptBASE64(key.getEncoded());
	}

	/**
	 * 初始化密钥
	 *
	 * @return
	 * @throws Exception
	 */
	public static Map<String, Key> initKey() throws Exception {
		KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
		keyPairGen.initialize(1024);
		KeyPair keyPair = keyPairGen.generateKeyPair();
		Map<String, Key> keyMap = new HashMap<>(2);
		keyMap.put(PUBLIC_KEY, keyPair.getPublic());// 公钥
		keyMap.put(PRIVATE_KEY, keyPair.getPrivate());// 私钥
		return keyMap;
	}

	public static void main(String[] args) throws Exception {
		Map<String, Key> keyMap = initKey();
		System.out.println("公钥：" + getPublicKey(keyMap));
		System.out.println("私钥：" + getPrivateKey(keyMap));
		String pwd = "! @#$%^&*()_+-=";
		System.out.println("加密后：");
		byte[] encryptData = encryptByPublicKey(pwd, getPublicKey(keyMap));// 公钥加密
		String encryptStr = encryptBASE64(encryptData);
		System.out.println(encryptStr);
		
		
		System.out.println("解密后：");
		byte[] decryptData = decryptByPrivateKey(decryptBASE64(encryptStr), getPrivateKey(keyMap));// 私钥解密
		System.out.println(new String(decryptData));
		
//公钥：MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC6J98RRpEYY0H3JDmXtZ35lrbhSXwiw941Nf6XobRFpRdzqNU87LvRIzJmOrOuI/buIbqYW5j1r63MZmX0otj6JrREOQM/pKK0MpXLQpokhxP/Q2iUsPMJmBns6o/0Szf6wnO6AMuLJVQ3EREMFXL6G6koFFWv6e9EoKIF27U8ewIDAQAB
//私钥：MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBALon3xFGkRhjQfckOZe1nfmWtuFJfCLD3jU1/pehtEWlF3Oo1Tzsu9EjMmY6s64j9u4huphbmPWvrcxmZfSi2PomtEQ5Az+korQylctCmiSHE/9DaJSw8wmYGezqj/RLN/rCc7oAy4slVDcREQwVcvobqSgUVa/p70SgogXbtTx7AgMBAAECgYANmC/QISkjcMw2cizKiXbZ7Um5thYcq9UkaacIB8UUePP3fFLmOhuVME7T7GGGzBWSWmA4FChdeNDmZmv3q5TQv5XW9kr8uWkg8j8hOG/f2+tNklI/bJWEMs+Pc8zkeX7V2p8xQZRVi1EqUcxiNlJGsLAGqii1UHEijuM+wjx4IQJBAP4nAaxd9WkqA7Ygf/uRQkFjQVvIUG7WxE4YU4wzOo64TpFSRjZkF+w1x4YDKlqa/mmPVJ2EO2239WrSj4uypMkCQQC7glGgVRvIQ2UZf7MR6ipHqSxvEhsG5qHqRfHEZsROdF4gYtKjsyGCk0agMsnxYG8S/DpCwMtlufvfYdPyVo0jAkEAqybNg6xKpiV7Fl1QkrNBsRMMYqjFMb9Dt2u+Z73gA1iu4EJNsjc5vQKCeQcMsLOL/L5fNyxjcQHfLa7aqDMHsQJAaLynWZaQmTFEBJU28hfJnBOcFUKYxCcJZDHDlQAOQQBxH0fz/qptWmFnfs4/zMsf4Avxgx3rvxu+3Azp9aBw3wJAUfn9S9KkXJBmuioj07EtbP2YZwNyztxpyQ/+TL5O2jUQk4sknWFg4PqhC2sXA/iThM3I9LB1Fnv8wzMvM10itQ==
//加密后：ahbm0g5t8ngvZCx621xu3BXtcxIrbADNHPGm9QfzApLoW5olsygR82mugm+WmFIg5t4Cji1NLplcaSOHaUV9LhzPH54Fojn8POKCS+rHg9nTqoAaU3gPSok9+j0QfKYRhqpdcDFxXFms8WfYL0+t9Pjfh8VhOvw8+AtdG+vhLcA=
//解密后：pwd		
		
	}
}