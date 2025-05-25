package com.kh.reactbackend.controller;

import com.kh.reactbackend.dto.MemberDto;
import com.kh.reactbackend.entity.Member;
import com.kh.reactbackend.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5175")
public class MemberController {

    private final MemberService memberservice;

    @PostMapping
    public ResponseEntity<String> addMember(@RequestBody MemberDto.Create createDto) {
        String userId = memberservice.createMember(createDto);
        return ResponseEntity.ok(userId);
    }

    @PostMapping("/login")
    public ResponseEntity<MemberDto.Response> login(@RequestBody MemberDto.Login loginDto) {

        return ResponseEntity.ok(
                memberservice.loginMember(loginDto.getUserId(), loginDto.getUserPwd())
        );
    }

    @PostMapping("/delete")
    public ResponseEntity<Void> deleteMember(@RequestBody MemberDto.Delete request) {
        System.out.println("넘어온 아이디 : " + request.getUserId());
        System.out.println("넘어온 비밀번호 : " + request.getUserPwd());
        memberservice.deleteMember(request.getUserId(), request.getUserPwd());
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{userId}")
    public ResponseEntity<MemberDto.Response> updateMember(
            @PathVariable String userId, @RequestBody MemberDto.Update updateDto) {
        memberservice.updateMember(userId, updateDto);
        return ResponseEntity.ok().build();
    }
}
