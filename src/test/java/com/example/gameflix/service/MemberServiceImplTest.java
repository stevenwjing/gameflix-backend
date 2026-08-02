package com.example.gameflix.service;

import com.example.gameflix.model.Member;
import com.example.gameflix.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class MemberServiceImplTest {
    @Autowired
    private MemberRepository repository;

    @Test
    void getAllMembers() {
        List<Member> items = repository.findAll();
        assertEquals(2, items.size());   // 2 members in your DB
    }

    @Test
    public void testFindOne() {
        Member member = repository.findById(1L).get();   // member id 1
        assertEquals("David Peslak", member.getMemberName());
    }
}
