package com.ericampire.app.model.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String userName;
    private String displayName;
    private String password;

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
        name = "user_group",
        joinColumns = @JoinColumn(name = "group_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id")
    )
    private List<Groupe> groups = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public User() {
    }

    public User(String userName, String name, String password) {
        this.userName = userName;
        this.displayName = name;
        this.password = password;
    }

    public List<Groupe> getGroups() {
        return groups;
    }

    public void setGroups(List<Groupe> groups) {
        this.groups = groups;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
