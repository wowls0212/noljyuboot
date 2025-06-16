package com.example.noljyu.Service;

import com.example.noljyu.DTO.UserDTO;
import com.example.noljyu.Entity.PostEntity;
import com.example.noljyu.Entity.UserEntity;
import com.example.noljyu.Repository.PostRepository;
import com.example.noljyu.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Override
    public int idcount(String id) {
        Integer count = userRepository.idcnt(id);
        return count != null ? count : 0;
    }


    @Override
    public void insertq(UserEntity uentity) {
        userRepository.save(uentity);
    }

    @Override
    public int nicknamecount(String nickname) {
        Integer count = userRepository.nickcnt(nickname);
        return count != null ? count : 0;
    }

    @Override
    public String getAdminPassword() {
        return userRepository.adpass();
    }

    @Override
    public int usertotal() {
        return userRepository.usertotal();
    }

    @Override
    public List<UserEntity> allout() {
        return userRepository.findAll();
    }

    @Override
    public void delete2(String id) {
        userRepository.deleteById(id);
    }

    @Override
    public int usersearchtotal() {
        return userRepository.usersearchtotal();
    }

    @Override
    public UserDTO getuser(String id) {
        UserEntity entity = userRepository.findOneById(id);
        if (entity == null) return null;
        return new UserDTO(entity);
    }

    @Override
    public List<PostEntity> getmyposts(String id) {
        return postRepository.findByIdOrderByPostdateDesc(id);
    }

    @Override
    public int getgoodtotal(String id) {
        return postRepository.sumGoodById(id);
    }

    @Override
    public UserDTO modify1(String id) {
        return getuser(id);
    }

    @Override
    public void modifyWithPhoto(String id, String pw, String name, String address, String phone, String nickname, String myanimal, String petName, String photo) {
        UserEntity entity = userRepository.findOneById(id);
        entity.setPw(pw);
        entity.setName(name);
        entity.setAddress(address);
        entity.setPhone(phone);
        entity.setNickname(nickname);
        entity.setMyanimal(myanimal);
        entity.setPetname(petName);
        entity.setPhoto(photo);
        userRepository.save(entity);
    }

    @Override
    public UserDTO delete1(String id) {
        return getuser(id);
    }

    @Override
    public List<UserEntity> searcha(String cate, String keyword) {
        switch (cate) {
            case "id":
                return userRepository.findByIdContaining(keyword);
            case "name":
                return userRepository.findByNameContaining(keyword);
            case "nickname":
                return userRepository.findByNicknameContaining(keyword);
            case "address":
                return userRepository.findByAddressContaining(keyword);
            case "myanimal":
                return userRepository.findByMyanimalContaining(keyword);
            default:
                return List.of();
        }
    }

    @Override
    public boolean checkUserPetName(String id, String petname) {
        return userRepository.findByIdAndPetname(id, petname) != null;
    }

    @Override
    public void updatePassword(String id, String encodedPw) {
        UserEntity user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        user.setPw(encodedPw);
        userRepository.save(user);
    }
}
