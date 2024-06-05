package com.sparta.memo.entity;

import com.sparta.memo.dto.MemoRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Table(name = "memo") //매핑할 테이블의 이름을 지정
@NoArgsConstructor //기본생성자 애너테이션
@Entity //JPA가 관리할 수 있는 Entity 클래스 지정
public class Memo extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //메모끼리 구분하기 위한 것

    @Column(name = "USERNAME",nullable = false)
    private String username;

    @Column(name = "CONTENTS",nullable = false,length = 500)
    private String contents;

    public Memo(MemoRequestDto requestDto) {
        this.username = requestDto.getUsername();
        this.contents = requestDto.getContents();
    }

    public void update(MemoRequestDto requestDto) {
        this.username = requestDto.getUsername();
        this.contents = requestDto.getContents();
    }
}
