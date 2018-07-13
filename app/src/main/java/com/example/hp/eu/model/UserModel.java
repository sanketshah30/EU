package com.example.hp.eu.model;

public class UserModel {
    private String first_name;
    private String last_name;
    private String phone_number;
    private String email_id;
    private String requestor_provider;
    private String profile_image;


    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getEmail_id() {
        return email_id;
    }

    public void setEmail_id(String email_id) {
        this.email_id = email_id;
    }

    public String getRequestor_provider() {
        return requestor_provider;
    }

    public void setRequestor_provider(String requestor_provider) {
        this.requestor_provider = requestor_provider;
    }

    public String getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(String profile_image) {
        this.profile_image = profile_image;
    }
}

