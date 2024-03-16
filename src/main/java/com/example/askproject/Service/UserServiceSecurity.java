package com.example.askproject.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.askproject.Model.DAO.PageDAO;
import com.example.askproject.Model.DAO.UserDAO;
import com.example.askproject.Model.DTO.UserDTO;
import com.example.askproject.Model.Entity.PageEntity;
import com.example.askproject.Model.Entity.UserEntity;

@Service
public class UserServiceSecurity {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserDAO userDAO;
    @Autowired
    private PageDAO pageDAO;

    public void updateIsLoginByName(String name, Boolean isLogin) {
        UserEntity entity = userDAO.findByUserId(name);
        entity.setIsLogin(isLogin);
        userDAO.updateUser(entity);
    }

    public void joinUserDto(UserDTO dto) {
        dto.setUserRole("USER");
        if (dto.getUserId().equals("admin")) {
            dto.setUserRole("ADMIN");
        } else if (dto.getUserId().equals("manager")) {
            dto.setUserRole("MANAGER");
        }

        String rawPwd = dto.getUserPassword();
        String encodedPwd = bCryptPasswordEncoder.encode(rawPwd);
        dto.setUserPassword(encodedPwd);

        dto.setIsLogin(false);
        UserEntity entity = new UserEntity();
        PageEntity pageEntity = new PageEntity();
        pageEntity.setPageId(dto.getUserId());
        pageEntity.setPageTitle(dto.getUserId()+"'s Page");
        pageEntity.setPageComment("");
        entity.setUserEmail(dto.getUserEmail());
        entity.setUserNickname(dto.getUserNickname());
        entity.setUserPassword(dto.getUserPassword());
        entity.setUserRole(dto.getUserRole());
        entity.setUserId(dto.getUserId());
        pageDAO.insertPage(pageEntity);
        userDAO.updateUser(entity);
    }
    
    public void changePassword(UserDTO dto) {
        String rawPwd = dto.getUserPassword();
        String encodedPwd = bCryptPasswordEncoder.encode(rawPwd);
        dto.setUserPassword(encodedPwd);
        UserEntity userEntity = userDAO.findByUserId(dto.getUserId());
        userEntity.setUserPassword(dto.getUserPassword());
        userDAO.updateUser(userEntity);
    }

    public boolean verifyPassword(String userId, String rawPassword) {
        UserEntity userEntity = userDAO.findByUserId(userId);
        if (userEntity == null) {
            // 사용자가 존재하지 않을 경우
            return false;
        }
        // DB에 저장된 암호화된 비밀번호와 입력된 비밀번호를 비교
        return bCryptPasswordEncoder.matches(rawPassword, userEntity.getUserPassword());
    }
}
