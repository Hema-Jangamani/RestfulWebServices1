package com.cedrus.RestfulWebservices1.user;

import javax.persistence.*;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue
    private Integer id;

    @Size(min = 2, message = "Name should have atleast 2 characters")
    private String name;

    @Past
    private Date dob;

    @OneToMany(mappedBy = "user")
    private List<Post> posts;

    public User() {
    }

    public User(Integer id, String name, Date dob) {
        this.id = id;
        this.name = name;
        this.dob = dob;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dob=" + dob +
                '}';
    }
}
