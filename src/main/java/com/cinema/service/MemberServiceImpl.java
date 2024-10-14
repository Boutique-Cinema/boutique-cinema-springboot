package com.cinema.service;

import com.cinema.domain.Member;
import com.cinema.dto.member.AdminMemberListDTO;
import com.cinema.dto.member.MemberJoinDTO;
import com.cinema.repository.MemberRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MemberServiceImpl  implements MemberService{

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public MemberServiceImpl(MemberRepository memberRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.memberRepository = memberRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    // 저장 메서드
    public void save(MemberJoinDTO dto) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy/MM/dd");
        String formattedDate = LocalDate.now().format(formatter); // 오늘 날짜 포맷팅
        Member member = Member.builder()
                .id(dto.getId())
                .password(bCryptPasswordEncoder.encode(dto.getPassword()))
                .email(dto.getEmail())
                .name(dto.getName())
                .birth(dto.getBirth())
                .phone(dto.getPhone())
                .years14More(dto.getYears14More())
                .useTermsAgree(dto.getUseTermsAgree())
                .personalInfoAgree(dto.getPersonalInfoAgree())
                .joinDate(formattedDate)
                .build();

        memberRepository.save(member); // Member 객체 저장

    }

    // 아이디 중복 체크 메서드
    public boolean findById(String id) {
        Optional<Member> member = memberRepository.findById(id);
        return member.isEmpty(); // 존재하지 않으면 true, 존재하면 false
    }

    // 공통 변환 로직 추출
    private AdminMemberListDTO convertToDTO(Member member) {
        return AdminMemberListDTO.builder()  // 빌더를 사용하여 생성
                .id(member.getId())
                .name(member.getName())
                .birth(member.getBirth())
                .phone(member.getPhone())
                .email(member.getEmail())
                .joinDate(member.getJoinDate())
                .isDeleted(member.getIsDeleted())
                .isTreated(member.getIsTreated())
                .build(); // 빌더를 통해 객체 생성
    }

    // 전체 회원 목록 조회
    @Override
    public List<AdminMemberListDTO> getAllMembers() {
        return memberRepository.findAll()
                .stream()
                .map(this::convertToDTO)  // 공통 메서드 사용
                .collect(Collectors.toList());
    }

    // 검색 기능 구현 (이름 또는 이메일로 검색)
    @Override
    public List<AdminMemberListDTO> searchMembers(String keyword) {
        return memberRepository.findByNameContainingOrIdContaining(keyword, keyword)
                .stream()
                .map(this::convertToDTO)  // 공통 메서드 사용
                .collect(Collectors.toList());
    }
}
