package com.kh.reactbackend.service;

import com.kh.reactbackend.dto.MemberDto;
import org.springframework.stereotype.Service;


public interface MemberService {
    String createMember(MemberDto.Create createDto);
    MemberDto.Response loginMember(String userId, String userPwd);
    void deleteMember(String userId, String userPwd);
    MemberDto.Response updateMember(String userId, MemberDto.Update updateDto);
}
