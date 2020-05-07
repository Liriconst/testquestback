package com.example.demo.model;

import javax.persistence.*;

@Entity
@Table(name = "usr_token")
public class UsrToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long usrId;
    private String token;

    public UsrToken() {}

    public UsrToken(String token, long usrId) {
        this.token = token;
        this.usrId = usrId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUsrId() {
        return usrId;
    }

    public void setUsrId(long usrId) {
        this.usrId = usrId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
