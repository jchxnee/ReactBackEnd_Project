package com.kh.reactbackend.dto;

import com.kh.reactbackend.entity.Member;
import lombok.*;

public class MemberDto {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Create{

        private String user_id;
        private String user_pwd;
        private String user_name;
        private String email;
        private String phone;
        private Integer age;

        public Member toEntity() {
            return Member.builder()
                    .userId(this.user_id)
                    .userPwd(this.user_pwd)
                    .userName(this.user_name)
                    .email(this.email)
                    .phone(this.phone)
                    .age(this.age)
                    .build();
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Update{
        private String user_id;
        private String user_pwd;
        private String user_name;
        private String email;
        private String phone;
        private Integer age;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Response{

        private String user_id;
        private String user_pwd;
        private String user_name;
        private String email;
        private String phone;
        private Integer age;

        public static Response toDto(Member member) {
            return Response.builder()
                    .user_id(member.getUserId())
                    .user_pwd(member.getUserPwd())
                    .user_name(member.getUserName())
                    .email(member.getEmail())
                    .phone(member.getPhone())
                    .age(member.getAge())
                    .build();
        }
    }
}
