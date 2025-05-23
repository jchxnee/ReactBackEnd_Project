package com.kh.reactbackend.service;

import com.kh.reactbackend.dto.MemberDto;
import org.springframework.stereotype.Service;


public interface MemberService {
    String createMember(MemberDto.Create createDto);
}
