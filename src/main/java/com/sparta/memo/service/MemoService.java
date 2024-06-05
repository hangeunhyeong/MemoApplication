package com.sparta.memo.service;

import com.sparta.memo.dto.MemoRequestDto;
import com.sparta.memo.dto.MemoResponseDto;
import com.sparta.memo.entity.Memo;
import com.sparta.memo.repository.MemoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemoService {

    private final MemoRepository memoRepository;


    public MemoService(MemoRepository memoRepository) {
        this.memoRepository = memoRepository;
    }

//    @Autowired
//    public MemoService(ApplicationContext context) {
//        // 1. 'Bean' 이름으로 가져오기
//        this.memoRepository =(MemoRepository) context.getBean("memoRepository");
//        // 2. 'Bean' class 형식로 가져오기
//        this.memoRepository = context.getBean(MemoRepository.class);
//    }

    public MemoResponseDto createMemo(MemoRequestDto requestDto) {
        // RequestDto -> Entity
        Memo memo = new Memo(requestDto);

        // DB 저쟝
        Memo saveMemo = memoRepository.save(memo);

        // Entity -> ResponseDto
        return new MemoResponseDto(memo);
    }

    public List<MemoResponseDto> getMemos() {
//        return memoRepository.findAll().stream().map(m->new MemoResponseDto(m)).toList();
        return memoRepository.findAll().stream().map(MemoResponseDto::new).toList();
    }

    @Transactional
    public Long updateMemo(Long id, MemoRequestDto requestDto) {
        // 해당 메모가 DB에 존재하는지 확인
        Memo memo = findMemo(id);
        // 메모 내용 수정
        memo.update(requestDto); //변경 감지 -> 영속성컨텍스트에 존재해야함 -> transaction 필요
        return id;


    }

//    @Transactional // 메모 db에 직접 접근하는것이므로 필요없음
    public Long deleteMemo(Long id) {
        // 해당 메모가 DB에 존재하는지 확인
        Memo memo = findMemo(id);
        memoRepository.delete(memo);
        return id;
    }

    private Memo findMemo(Long id) {
        return memoRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("선택한 메모는 존재하지 않습니다"));
    }
}
