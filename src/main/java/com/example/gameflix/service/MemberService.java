package com.example.gameflix.service;

import com.example.gameflix.model.Member;
import org.springframework.data.domain.Page;

import java.util.List;

public interface MemberService {
    List<Member> getAllMembers();

    void saveMember(Member member);
    Member getMemberById(long id);
    void deleteMemberById(long id);
    Page<Member> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
