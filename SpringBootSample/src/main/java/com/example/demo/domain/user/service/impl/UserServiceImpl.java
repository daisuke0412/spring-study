package com.example.demo.domain.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.user.model.MUser;
import com.example.demo.domain.user.service.UserService;
import com.example.demo.repository.UserMapper;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper mapper;
    
    @Autowired
    private PasswordEncoder encoder;

    /** ユーザー登録 */
    @Override
    public void signup(MUser user) {
        user.setDepartmentId(1); // 部署
        user.setRole("ROLE_GENERAL"); // ロール
        
        //パスワード暗号化
        String rawPassword = user.getPassword();
        user.setPassword(encoder.encode(rawPassword));
        
        mapper.insertOne(user);
    }
    
    /** ユーザー取得 */
    @Override
    public List<MUser> getUsers(MUser user){
    	return mapper.findMany(user);
    };
    
    /** ユーザー取得（１件）*/
    @Override
    public MUser getUserOne(String userId){
    	return mapper.findOne(userId);
    }
    
    /** ユーザー更新（１件） */
    @Transactional
    @Override
    public void updateUserOne(String userId, String password, String userName) {
    	
    	//パスワード暗号化
    	String encryptPassword = encoder.encode(password);
    	mapper.updateOne(userId, encryptPassword, userName);
    	
    	//例外を起こす処理
//    	int i =1/0;
    }
    
    /** ユーザー削除（１件） */
    @Override
    public void deleteUserOne(String userId) {
    	mapper.deleteOne(userId);
    }
    
    /** ログインユーザー取得 */
    @Override
    public MUser getLoginUser(String userId) {
    	return mapper.findLoginUser(userId);
    }
    
}
