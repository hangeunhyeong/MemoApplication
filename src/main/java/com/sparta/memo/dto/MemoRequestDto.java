package com.sparta.memo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
public class MemoRequestDto {
    private Long id;
    private String username;
    private String contents;
}
