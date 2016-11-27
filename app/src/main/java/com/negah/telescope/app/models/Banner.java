
package com.negah.telescope.app.models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Banner {

    @SerializedName("catID")
    @Expose
    private String catID;
    @SerializedName("catName")
    @Expose
    private String catName;
    @SerializedName("childType")
    @Expose
    private String childType;
    @SerializedName("icon")
    @Expose
    private String icon;
    @SerializedName("showtype")
    @Expose
    private Integer showtype;
    @SerializedName("bannerpath")
    @Expose
    private String bannerpath;
    @SerializedName("row")
    @Expose
    private Integer row;

    public Integer getRow() {
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
    }

    /**
     * 
     * @return
     *     The catID
     */
    public String getCatID() {
        return catID;
    }

    /**
     * 
     * @param catID
     *     The catID
     */
    public void setCatID(String catID) {
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
     *     The childType
     */
    public String getChildType() {
        return childType;
    }

    /**
     * 
     * @param childType
     *     The childType
     */
    public void setChildType(String childType) {
        this.childType = childType;
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
     *     The showtype
     */
    public Integer getShowtype() {
        return showtype;
    }

    /**
     * 
     * @param showtype
     *     The showtype
     */
    public void setShowtype(Integer showtype) {
        this.showtype = showtype;
    }

    /**
     * 
     * @return
     *     The bannerpath
     */
    public String getBannerpath() {
        return bannerpath;
    }

    /**
     * 
     * @param bannerpath
     *     The bannerpath
     */
    public void setBannerpath(String bannerpath) {
        this.bannerpath = bannerpath;
    }


    @Override
    public String toString() {
        return catName;
    }
}
