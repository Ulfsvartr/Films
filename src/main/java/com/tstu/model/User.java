package com.tstu.model;

import com.tstu.model.enums.Role;

import javax.persistence.*;
import java.util.List;

@Entity(name="User")
@Table(name = "users")
public class User {
    private static long nextId=1;
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Transient
    private Role role;

    @OneToMany(mappedBy = "author")
    private List<Review> reviews;

    public User() {
    }

    public User(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.id=nextId++;
    }

    public User(long id, String username, String password, Role role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public User(long id, String username, String password, Role role, List<Review> reviews) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.reviews=reviews;
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username +
                '}';
    }
}
