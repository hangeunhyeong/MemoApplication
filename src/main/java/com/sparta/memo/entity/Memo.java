package com.sparta.memo.entity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.memo.dto.MemoRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor //기본생성자 애너테이션
public class Memo {
    private Long id; //메모끼리 구분하기 위한 것
    private String username;
    private String contents;


    public Memo(MemoRequestDto requestDto) {
        this.username = requestDto.getUsername();
        this.contents = requestDto.getContents();
    }
}
