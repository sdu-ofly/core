package com.ofly.core.utils;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Md5Hash;

import com.ofly.core.admin.vo.AccountVo;

/**
 * Author		：Logan715                
 * Create Date	：2017年6月4日 下午6:27:13
 * Introduction	：加密工具
 *
 */
public final class EndecryptUtils{
	
	/**
	 * Author		：Logan715                
	 * Create Date	：2017年6月4日 下午6:27:27
	 * History		: 2017年6月4日 下午6:27:27   Logan715   Created.
	
	 * Introduction	：MD5加密
	 * @param vo	: 帐号Vo AccountVo
	 * @return		: 加密后的帐号 AccountVo
	 *
	 */
	public static AccountVo md5Password(AccountVo vo){
		 SecureRandomNumberGenerator secureRandomNumberGenerator=new SecureRandomNumberGenerator(); 
	     String salt= secureRandomNumberGenerator.nextBytes().toHex(); 
	     //组合username,两次迭代，对密码进行加密 
	    String password_cipherText= new Md5Hash(vo.getPassword(),vo.getAccount()+salt,2).toBase64(); 
	    vo.setSalt(salt);
	    vo.setPassword(password_cipherText);
		return vo;
	}

}
