package com.ofly.core.utils;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Md5Hash;

import com.ofly.core.admin.vo.AccountVo;

public final class EndecryptUtils{
	
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
