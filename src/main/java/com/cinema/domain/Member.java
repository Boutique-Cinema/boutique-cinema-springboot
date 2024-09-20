package com.cinema.domain;

import java.util.Date;

public class Member {
    private String mId;             // 회원 아이디
    private String mPassword;       // 회원 비밀번호
    private String mBirthday;       // 회원 생년월일
    private String mName;           // 회원 성명
    private String mEmail;          // 회원 이메일
    private String mPhoneNum;       // 회원 휴대폰번호
    private int isDeleted;          // 탈퇴여부
    private int isTreated;          // 우대여부
    private int personalInfoAgree;  // 개인정보 동의
    private int years14MoreAgree;   // 14세 이상 동의
    private int useTermsAgree;      // 이용약관 동의
    private Date joinDate;          // 회원 가입일
}