
package com.negah.telescope.app.models;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class PostDetail {

    @SerializedName("id")
    @Expose
    private String id;
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
    @SerializedName("markets")
    @Expose
    private List<Market> markets = new ArrayList<Market>();
    @SerializedName("shortReview")
    @Expose
    private String shortReview;
    @SerializedName("fullReview")
    @Expose
    private String fullReview;
    @SerializedName("rates")
    @Expose
    private List<Rate> rates = new ArrayList<Rate>();
    @SerializedName("screenshot")
    @Expose
    private List<String> screenshot = new ArrayList<String>();
    @SerializedName("postType")
    @Expose
    private Integer postType;
    @SerializedName("writer")
    @Expose
    private String writer;
    @SerializedName("imagePath")
    @Expose
    private String imagePath;
    @SerializedName("speciality")
    @Expose
    private Integer speciality;
    @SerializedName("visitcount")
    @Expose
    private String visitcount;


    /**
     * 
     * @return
     *     The id
     */
    public String getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    public void setId(String id) {
        this.id = id;
    }

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
     *     The markets
     */
    public List<Market> getMarkets() {
        return markets;
    }

    /**
     * 
     * @param markets
     *     The markets
     */
    public void setMarkets(List<Market> markets) {
        this.markets = markets;
    }

    /**
     * 
     * @return
     *     The shortReview
     */
    public String getShortReview() {
        return shortReview;
    }

    /**
     * 
     * @param shortReview
     *     The shortReview
     */
    public void setShortReview(String shortReview) {
        this.shortReview = shortReview;
    }

    /**
     * 
     * @return
     *     The fullReview
     */
    public String getFullReview() {
        return fullReview;
    }

    /**
     * 
     * @param fullReview
     *     The fullReview
     */
    public void setFullReview(String fullReview) {
        this.fullReview = fullReview;
    }

    /**
     * 
     * @return
     *     The rates
     */
    public List<Rate> getRates() {
        return rates;
    }

    /**
     * 
     * @param rates
     *     The rates
     */
    public void setRates(List<Rate> rates) {
        this.rates = rates;
    }

    /**
     * 
     * @return
     *     The screenshot
     */
    public List<String> getScreenshot() {
        return screenshot;
    }

    /**
     * 
     * @param screenshot
     *     The screenshot
     */
    public void setScreenshot(List<String> screenshot) {
        this.screenshot = screenshot;
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



    @Override
    public String toString() {
        return vendor;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }



    public void setSpeciality(Integer speciality) {
        this.speciality = speciality;
    }

    public void setVisitcount(String visitcount) {
        this.visitcount = visitcount;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getImagePath() {
        return imagePath;
    }



    public Integer getSpeciality() {
        return speciality;
    }

    public String getVisitcount() {
        return visitcount;
    }

    public String getWriter() {
        return writer;
    }
}
