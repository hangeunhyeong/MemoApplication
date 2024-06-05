package com.sparta.memo.repository;

import com.sparta.memo.entity.Memo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface MemoRepository extends JpaRepository<Memo, Long> {
    List<Memo> findAllByOrderByModifiedAtDesc(); //select * from memo order by modified desc
                                                // 최신순으로 모든 메모 가져오기 위한 메서드


    List<Memo> findAllByUsername(String username); // select * from memo where username='username'
                                                    // 특정 인물의 메모를 가져오기 위한 메서드
}