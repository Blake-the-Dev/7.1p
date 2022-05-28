package com.example.task71p.model;

public class Post {
    private int post_id;
    private String post_type;
    private String post_name;
    private String post_phone;
    private String post_description;
    private String post_date;
    private String post_location;

    public Post(String post_type, String post_name, String post_phone, String post_description, String post_date, String post_location)
    {
        this.post_type = post_type;
        this.post_name = post_name;
        this.post_phone = post_phone;
        this.post_description = post_description;
        this.post_date = post_date;
        this.post_location = post_location;
    }

    public Post(){

    }


    public int getPost_id(){
        return post_id;
    }

    public void setPost_id(int post_id){
        this.post_id = post_id;
    }


    public String getPost_type(){
        return post_type;
    }

    public void setPost_type(String post_type){
        this.post_type = post_type;
    }


    public String getPost_name(){
        return post_name;
    }

    public void setPost_name(String post_name){
        this.post_name = post_name;
    }


    public String getPost_phone(){
        return post_phone;
    }

    public void setPost_phone(String post_phone){
        this.post_phone = post_phone;
    }


    public String getPost_description(){
        return post_description;
    }

    public void setPost_description(String post_description){
        this.post_description = post_description;
    }


    public String getPost_date(){
        return post_date;
    }

    public void setPost_date(String post_date){
        this.post_date = post_date;
    }


    public String getPost_location(){
        return post_location;
    }

    public void setPost_location(String post_location){
        this.post_location = post_location;
    }

}
