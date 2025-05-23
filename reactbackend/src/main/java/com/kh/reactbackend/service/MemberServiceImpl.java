package com.kh.reactbackend.service;

import com.kh.reactbackend.dto.MemberDto;
import com.kh.reactbackend.entity.Member;
import com.kh.reactbackend.enums.CommonEnums;
import com.kh.reactbackend.repository.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public String createMember(MemberDto.Create createDto) {
        Member member = createDto.toEntity(); //메모리 올라온 member
        memberRepository.save(member);
        return member.getUserId();
    }

    @Override
    public MemberDto.Response loginMember(String userId, String userPwd) {
        System.out.println("로그인시도 ID : " + userId);
        Member member = memberRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("아이디가 존재하지 않습니다."));

        if(!member.getUserPwd().equals(userPwd)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        MemberDto.Response response = new MemberDto.Response();
        response.setUser_id(member.getUserId());
        response.setUser_pwd(member.getUserPwd());
        response.setUser_name(member.getUserName());
        response.setEmail(member.getEmail());
        response.setPhone(member.getPhone());
        response.setAge(member.getAge());
        response.setFavorite(member.getFavorite());

        return response;
        }

    @Override
    public void deleteMember(String userId, String userPwd) {
        Member member = memberRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));

        System.out.println("넘어온 비밀번호 : " + userPwd);
        System.out.println("db의 비밀번호 : " + member.getUserPwd());
        if(!member.getUserPwd().equals(userPwd)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        member.setStatus(CommonEnums.Status.N);
    }

    @Override
    public MemberDto.Response updateMember(String userId, MemberDto.Update updateDto) {
        Member member = memberRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다."));

        member.updateMemberInfo(
                updateDto.getUser_name(),
                updateDto.getEmail(),
                updateDto.getPhone(),
                updateDto.getAge(),
                updateDto.getFavorite()
        );
        return MemberDto.Response.toDto(member);
    }

}

