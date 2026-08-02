package com.example.gameflix.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "games")
public class Game implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "genre")
    private String genre;
    @Column(name = "platform")
    private String platform;
    @Column(name = "title")
    private String title;
    @Column(name = "price")
    private String price;

    @ManyToMany(mappedBy = "games")
    @JsonIgnore
    private Set<Member> members = new HashSet<>();

    public long getId() { return id; }

    public void setId(long id) { this.id = id; }

    public String getGenre() { return genre; }

    public void setGenre(String genre) { this.genre = genre; }

    public String getPlatform() { return platform; }

    public void setPlatform(String platform) { this.platform = platform; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public String getPrice() { return price; }

    public void setPrice(String price) { this.price = price; }

    public Set<Member> getMembers() { return members; }

    public void setMembers(Set<Member> members) { this.members = members; }
}
