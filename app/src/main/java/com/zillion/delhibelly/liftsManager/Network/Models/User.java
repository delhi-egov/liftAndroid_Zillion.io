package com.zillion.delhibelly.liftsManager.Network.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class User {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("inspector")
    @Expose
    private Inspector inspector;
    @SerializedName("token")
    @Expose
    private String token;

    /**
     *
     * @return
     * The message
     */
    public String getMessage() {
        return message;
    }

    /**
     *
     * @param message
     * The message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     *
     * @return
     * The inspector
     */
    public Inspector getInspector() {
        return inspector;
    }

    /**
     *
     * @param inspector
     * The inspector
     */
    public void setInspector(Inspector inspector) {
        this.inspector = inspector;
    }

    /**
     *
     * @return
     * The token
     */
    public String getToken() {
        return token;
    }

    /**
     *
     * @param token
     * The token
     */
    public void setToken(String token) {
        this.token = token;
    }

}
