package com.example.gameflix.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "members")
public class Member implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "member_name")
    private String memberName;
    @Column(name = "member_email")
    private String memberEmail;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "member_game",
            joinColumns = {@JoinColumn(name = "member_id")},
            inverseJoinColumns = {@JoinColumn(name = "game_id")})
    public Set<Game> games = new HashSet<>();

    public String getMemberName() { return memberName; }

    public void setMemberName(String memberName) { this.memberName = memberName; }

    public String getMemberEmail() { return memberEmail; }

    public void setMemberEmail(String memberEmail) { this.memberEmail = memberEmail; }

    public long getId() { return id; }

    public void setId(long id) { this.id = id; }

    public Set<Game> getGames() { return games; }

    public void setGames(Set<Game> games) { this.games = games; }
}
