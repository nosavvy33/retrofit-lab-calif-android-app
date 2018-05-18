package com.undead.nosavvy.lab_calif_network.models;

/**
 * Created by nosavvy on 5/14/18.
 */
import com.google.gson.annotations.SerializedName;
public class LoginResponse {
    @SerializedName("username")
    private String username;

    public LoginResponse(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @SerializedName("password")
    private String password;

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
}
