package com.weavus.board.repo;

import com.weavus.board.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MemberRepository extends JpaRepository<Member,String> {

    @Query(value ="select * from member where id = ? and password = ?",nativeQuery = true)
    public Member findByIdAndPw(String id, String password);





}
