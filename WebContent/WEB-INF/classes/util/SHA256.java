package util;

import java.security.MessageDigest;

public class SHA256 {
	
	//이메일에 해쉬값을 적용해서 인증코드로 (링크타고 들어와서) 인증 가능하게끔 하는 것이 SHA-256
	
	public static String getSHA256(String input) { // input에 Email이 들어감
		StringBuffer result = new StringBuffer();
		
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] salt = "Hello! This is Salt.".getBytes(); //해커에 의해 해킹이 가능하기 때문에 솔트 값을 적용 (자신이 원하는 솔트값을 넣는게 일반적)
			digest.reset();
			digest.update(salt); // 솔트 값 적용
			byte[] chars = digest.digest(input.getBytes("UTF-8"));
			
			for(int i = 0 ; i < chars.length; i++) {
				String hex = Integer.toHexString(0xff & chars[i]);
				if(hex.length()==1) result.append("0"); // 1자리 일때 0이라도 붙여서 16진수로 변환
				result.append(hex);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result.toString();
	}
	
	

}
