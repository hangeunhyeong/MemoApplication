package com.sparta.memo.controller;

import com.sparta.memo.dto.MemoResponseDto;
import com.sparta.memo.dto.MemoRequestDto;
import com.sparta.memo.entity.Memo;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
public class MemoController {
    //데이터베이스 대신 HashMap을 이용
    private final Map<Long, Memo> memoList = new HashMap<>();

    @PostMapping("/memos")
    public MemoResponseDto createMemo(@RequestBody MemoRequestDto requestDto) {
        // RequestDto -> Entity (메모를 저장해야하므로)
        Memo memo = new Memo(requestDto);

        //Memo Max ID Check (메모가 중복이 되면 안돼서 데이터베이스에 있는 ID값에 +1을 해준다)
        Long maxId = memoList.size() > 0 ? Collections.max(memoList.keySet()) + 1 : 1;
        memo.setId(maxId);

        //DB 저장
        memoList.put(memo.getId(), memo);

        //Entity > ResponseDto
        MemoResponseDto memoresponseDto = new MemoResponseDto(memo);

        return memoresponseDto;
    }

    @GetMapping("/memos")
    public List<MemoResponseDto> getMemos() {
        // Map to List
        List<MemoResponseDto> responseList = new ArrayList<>();
        for(Memo requestMemo : memoList.values()) {
            MemoResponseDto a=new MemoResponseDto(requestMemo); // Memo 클래스를 MemoResponseDto 클래스로 바꾸기
            responseList.add(a);
        }
        
        return responseList;
        
    }

    @PutMapping("/memos/{id}")
    public Long editMemos(@PathVariable) {

    }
//
//    @DeleteMapping("/memos/{id}")
//    public Long deleteMemos(@PathVariable) {
//
//    }
}
