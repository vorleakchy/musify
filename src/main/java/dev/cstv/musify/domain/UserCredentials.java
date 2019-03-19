package dev.cstv.musify.domain;

import javax.persistence.*;

@Entity
@Table(name = "UserCredentials")
public class UserCredentials {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    private User user;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", nullable = false)
    private String email;

    @Transient
    private String verifyPassword;

    public UserCredentials() {
    }

    public UserCredentials(User user, String username, String password, String email, String verifyPassword) {
        this.user = user;
        this.username = username;
        this.password = password;
        this.email = email;
        this.verifyPassword = verifyPassword;
    }

    public UserCredentials(User user, String username, String password, String email) {
        this.user = user;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public UserCredentials(String username,String password,String email){

        this.username=username;
        this.password=password;
        this.email=email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getVerifyPassword() {
        return verifyPassword;
    }

    public void setVerifyPassword(String verifyPassword) {
        this.verifyPassword = verifyPassword;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
