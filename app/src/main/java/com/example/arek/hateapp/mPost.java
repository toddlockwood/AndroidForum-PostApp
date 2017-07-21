package com.example.arek.hateapp;

/**
 * Created by Arek on 13.07.2017.
 */

public class mPost {

    private  String login;
    private  String post;

    public mPost(String login, String post) {
        this.login=login;
        this.post=post;

    }

    public  String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }


    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }


}
