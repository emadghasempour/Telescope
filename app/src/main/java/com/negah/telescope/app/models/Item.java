
package com.negah.telescope.app.models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Item {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("appname")
    @Expose
    private String appname;
    @SerializedName("vendor")
    @Expose
    private String vendor;
    @SerializedName("catID")
    @Expose
    private Integer catID;
    @SerializedName("catName")
    @Expose
    private String catName;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("icon")
    @Expose
    private String icon;
    @SerializedName("postType")
    @Expose
    private Integer postType;
    @SerializedName("rate")
    @Expose
    private Rate rate;

    /**
     * 
     * @return
     *     The title
     */
    public String getTitle() {
        return title;
    }

    /**
     * 
     * @param title
     *     The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 
     * @return
     *     The appname
     */
    public String getAppname() {
        return appname;
    }

    /**
     * 
     * @param appname
     *     The appname
     */
    public void setAppname(String appname) {
        this.appname = appname;
    }

    /**
     * 
     * @return
     *     The vendor
     */
    public String getVendor() {
        return vendor;
    }

    /**
     * 
     * @param vendor
     *     The vendor
     */
    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    /**
     * 
     * @return
     *     The catID
     */
    public Integer getCatID() {
        return catID;
    }

    /**
     * 
     * @param catID
     *     The catID
     */
    public void setCatID(Integer catID) {
        this.catID = catID;
    }

    /**
     * 
     * @return
     *     The catName
     */
    public String getCatName() {
        return catName;
    }

    /**
     * 
     * @param catName
     *     The catName
     */
    public void setCatName(String catName) {
        this.catName = catName;
    }

    /**
     * 
     * @return
     *     The price
     */
    public String getPrice() {
        return price;
    }

    /**
     * 
     * @param price
     *     The price
     */
    public void setPrice(String price) {
        this.price = price;
    }

    /**
     * 
     * @return
     *     The icon
     */
    public String getIcon() {
        return icon;
    }

    /**
     * 
     * @param icon
     *     The icon
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * 
     * @return
     *     The postType
     */
    public Integer getPostType() {
        return postType;
    }

    /**
     * 
     * @param postType
     *     The postType
     */
    public void setPostType(Integer postType) {
        this.postType = postType;
    }

    /**
     * 
     * @return
     *     The rate
     */
    public Rate getRate() {
        return rate;
    }

    /**
     * 
     * @param rate
     *     The rate
     */
    public void setRate(Rate rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return title;
    }
}
