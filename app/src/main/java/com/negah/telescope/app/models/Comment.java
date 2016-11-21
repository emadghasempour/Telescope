
package com.negah.telescope.app.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Comment {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("userID")
    @Expose
    private Integer userID;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("avatar")
    @Expose
    private String avatar;
    @SerializedName("commnet")
    @Expose
    private String commnet;
    @SerializedName("hasRate")
    @Expose
    private String hasRate;
    @SerializedName("rates")
    @Expose
    private Rate rates;

    /**
     * 
     * @return
     *     The id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 
     * @return
     *     The userID
     */
    public Integer getUserID() {
        return userID;
    }

    /**
     * 
     * @param userID
     *     The userID
     */
    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    /**
     * 
     * @return
     *     The username
     */
    public String getUsername() {
        return username;
    }

    /**
     * 
     * @param username
     *     The username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 
     * @return
     *     The avatar
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * 
     * @param avatar
     *     The avatar
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    /**
     * 
     * @return
     *     The commnet
     */
    public String getCommnet() {
        return commnet;
    }

    /**
     * 
     * @param commnet
     *     The commnet
     */
    public void setCommnet(String commnet) {
        this.commnet = commnet;
    }

    /**
     * 
     * @return
     *     The hasRate
     */
    public String getHasRate() {
        return hasRate;
    }

    /**
     * 
     * @param hasRate
     *     The hasRate
     */
    public void setHasRate(String hasRate) {
        this.hasRate = hasRate;
    }

    /**
     * 
     * @return
     *     The rates
     */
    public Rate getRates() {
        return rates;
    }

    /**
     * 
     * @param rates
     *     The rates
     */
    public void setRates(Rate rates) {
        this.rates = rates;
    }

}
