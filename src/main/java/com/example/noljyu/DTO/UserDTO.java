package com.example.noljyu.DTO;

import com.example.noljyu.Entity.UserEntity;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private String id;
    private String pw;
    private String name;
    private String address;
    private String phone;
    private String nickname;
    private String myanimal;
    private String admin;
    private String petname;
    private String photo;

    public UserEntity toEntity() {
        return UserEntity.builder()
                .id(id)
                .pw(pw)
                .name(name)
                .address(address)
                .phone(phone)
                .nickname(nickname)
                .myanimal(myanimal)
                .admin(admin)
                .petname(petname)
                .photo(photo)
                .build();
    }

    public UserDTO(UserEntity entity) {
        this.id = entity.getId();
        this.pw = entity.getPw();
        this.name = entity.getName();
        this.address = entity.getAddress();
        this.phone = entity.getPhone();
        this.nickname = entity.getNickname();
        this.myanimal = entity.getMyanimal();
        this.admin = entity.getAdmin();
        this.petname = entity.getPetname();
        this.photo = entity.getPhoto();
    }
}
