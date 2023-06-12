package com.technews.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

//@Entity marks this as a persistable object, so that the User class can map to a table.
@Entity
//@JsonIgnoreProperties specifies properties ("hibernateLazyInitializer" and "handler" properties in this case) that should be ignored when serializing this object to JSON.
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
//@Table specifies the name of the table that this class maps to.
//If this annotation isn't present, the table name will be the class name by default.
@Table(name="user")
    public class User implements Serializable {
    //Id annotation denotes that id will be used as the unique identifier
    @Id
    //GeneratedValue denotes that it will be a generated value.
    //GenerationType.AUTO denotes that the number should be automatically generated
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String username;
    @Column(unique = true)
    private String email;
    private String password;
    //@Transient signals to Spring Data JPA that this data is NOT to be persisted in the database, because we don't need or want a user's logged-in status to persist in the data.
    @Transient
    boolean loggedIn;

    //FetchType of EAGER, meaning that this list will gather all of its necessary information immediately after being created,
    //while the variables designated as LAZY only gather information as they need it.
    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Post> posts;
    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Vote> votes;
    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comment> comments;

    public User() {
    }

    public User(Integer id, String username, String email, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public List<Vote> getVotes() {
        return votes;
    }

    public void setVotes(List<Vote> votes) {
        this.votes = votes;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return loggedIn == user.loggedIn &&
                Objects.equals(id, user.id) &&
                Objects.equals(username, user.username) &&
                Objects.equals(email, user.email) &&
                Objects.equals(password, user.password) &&
                Objects.equals(posts, user.posts) &&
                Objects.equals(votes, user.votes) &&
                Objects.equals(comments, user.comments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, email, password, loggedIn, posts, votes, comments);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", loggedIn=" + loggedIn +
                ", posts=" + posts +
                ", votes=" + votes +
                ", comments=" + comments +
                '}';
    }
}
