package com.example.gameflix.service;

import com.example.gameflix.model.Member;
import com.example.gameflix.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public List<Member> getAllMembers() { return memberRepository.findAll(); }

    @Override
    public void saveMember(Member member) { this.memberRepository.save(member); }

    @Override
    public Member getMemberById(long id) {
        Optional<Member> optional = memberRepository.findById(id);
        Member member = null;
        if (optional.isPresent()) {
            member = optional.get();
        } else {
            throw new RuntimeException(" Member not found for id :: " + id);
        }
        return member;
    }

    @Override
    public void deleteMemberById(long id) { this.memberRepository.deleteById(id); }

    @Override
    public Page<Member> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.memberRepository.findAll(pageable);
    }
}
