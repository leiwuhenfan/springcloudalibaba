/**
 * 
 */
package dd;

import java.io.UnsupportedEncodingException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Base64Utils;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.nacos.common.util.Md5Utils;

/**
 * @author zs
 * @date 2021年4月10日
 */
public class TESt {

	public static void main(String[] args) throws UnsupportedEncodingException {

		String charset = "UTF-8";
		// 生成需要验证签名的对象

		Long createTime = System.currentTimeMillis();

		Long expireTime = createTime + (1000 * 60 * 60 * 24);

		BusinessToken bt = new BusinessToken();
		bt.setCreateTime(createTime);
		bt.setExpireTime(expireTime);

		String unique = "123456";

		String key = unique + String.valueOf(createTime) + String.valueOf(expireTime);
		String sign = Md5Utils.getMD5(key, "UTF-8");

		System.out.println("sign   :" + sign);

		bt.setUnique(unique).setSign(sign);

		String js = JSONObject.toJSONString(bt);

		System.out.println("js     :" + js);

		String base64 = Base64Utils.encodeToString(js.getBytes(charset));

		System.out.println("base64 :" + base64);
		
		String resbase64 = TESt.reverse(base64, 0, 2);
		
		System.out.println("bas64re:"+resbase64);
		
		System.out.println("base64 :"+TESt.reverseArray(resbase64, 0, 2));
		

		String reverse = StringUtils.reverse(base64);

		System.out.println("reverse:" + reverse);
		
		
		StringBuilder ssss = new StringBuilder("1234567");
		//ssss.reverse();
		System.out.println(ssss.replace(0, 2, "@@"));
		

	}

	public static String reverse(String str, int startIndex, int endIndex) {
		if (str != null) {
			StringBuilder builder = new StringBuilder(str.length());

			// 第一部分：
			builder.append(str.substring(0, startIndex));
			// 第二部分
			for (int i = endIndex; i >= startIndex; i--) {
				builder.append(str.charAt(i));
			}
			// 第三部分
			builder.append(str.substring(endIndex + 1));

			return builder.toString();
		} else {
			return null;
		}
	}

	 public static String reverseArray(String str,int startIndex,int endIndex){
	        if(str != null && str.length() != 0){
	            char[] arr = str.toCharArray();
	            //需要调转的部分
	           for(int x = startIndex, y = endIndex; y > x;y--,x++){
	               char temp = arr[x];
	               arr[x] = arr[y];
	               arr[y] = temp;
	           }
	           return  String.valueOf(arr);

	        }return null;
	    }

	
}

//

class BaseToken {
	// 创建时间(毫秒)1618058970000
	private Long createTime;
	// 到期时间(毫秒)1618058975000【有效期:1618058975000-1618058970000=5000(5秒)】(不过期可以自定义0)
	private Long expireTime;

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
		// return this;
	}

	public Long getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(Long expireTime) {
		this.expireTime = expireTime;
		// return this;
	}

}

class BusinessToken extends BaseToken {

	private String unique;
	private String sign;

	public void setCreateTime(Long createTime) {
		super.setCreateTime(createTime);
	}

	public void setExpireTime(Long expireTime) {
		super.setExpireTime(expireTime);
	}

	public String getUnique() {
		return unique;
	}

	public BusinessToken setUnique(String unique) {
		this.unique = unique;
		return this;
	}

	public String getSign() {
		return sign;
	}

	public BusinessToken setSign(String sign) {
		this.sign = sign;
		return this;
	}

}