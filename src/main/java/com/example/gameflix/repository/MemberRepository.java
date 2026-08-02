package com.example.gameflix.repository;

import com.example.gameflix.model.Game;
import com.example.gameflix.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    // Optional<Member> findByMemberId(Long id);
}
