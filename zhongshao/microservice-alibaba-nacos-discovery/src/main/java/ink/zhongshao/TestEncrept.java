/**
 * 
 */
package ink.zhongshao;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

/**
 * @author zs
 * @date 2020年9月30日
 */
public class TestEncrept {

	 public static void main(String[] arg){
		 
	        StandardPBEStringEncryptor standardPBEStringEncryptor =new StandardPBEStringEncryptor();
	        /*配置文件中配置如下的算法:PBEWithMD5AndDES*/
	        standardPBEStringEncryptor.setAlgorithm("PBEWithMD5AndDES");
	        /*配置文件中配置的password:EWRREWRERWECCCXC*/
	        standardPBEStringEncryptor.setPassword("EWRREWRERWECCCXXXX");
	        /*要加密的文本*/
	        String name = standardPBEStringEncryptor.encrypt("root");
	        String password =standardPBEStringEncryptor.encrypt("root");
	        /*将加密的文本写到配置文件中*/
	        System.out.println("name="+name);
	        System.out.println("password="+password);
	        
	        
	        //name=2RlCNEBxi+G3jzVvQ01HnQ==
	        //password=eIQtVixBMIq+tdNy8+sRhA==
	    }

}
