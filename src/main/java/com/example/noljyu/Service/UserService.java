package com.example.noljyu.Service;

import com.example.noljyu.DTO.UserDTO;
import com.example.noljyu.Entity.PostEntity;
import com.example.noljyu.Entity.UserEntity;

import java.util.List;

public interface UserService {
    int idcount(String id);
    void insertq(UserEntity uentity);
    int nicknamecount(String nickname);
    String getAdminPassword();
    int usertotal();
    List<UserEntity> allout();
    void delete2(String id);
    int usersearchtotal();
    UserDTO getuser(String id);
    List<PostEntity> getmyposts(String loginId);
    int getgoodtotal(String loginId);
    UserDTO modify1(String id);
    void modifyWithPhoto(String id, String pw, String name, String address, String phone, String nickname, String myanimal, String petName, String photo);
    UserDTO delete1(String id);
    List<UserEntity> searcha(String cate, String keyword);
    boolean checkUserPetName(String id, String petName);
    void updatePassword(String id, String encodedPw);

}
