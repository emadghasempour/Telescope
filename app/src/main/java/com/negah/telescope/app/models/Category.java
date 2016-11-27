
package com.negah.telescope.app.models;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Category {

    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("URL")
    @Expose
    private Object uRL;

    @SerializedName("ParentId")
    @Expose
    private Object parentId;
    @SerializedName("DisplayOrder")
    @Expose
    private Integer displayOrder;
    @SerializedName("Description")
    @Expose
    private String description;
    @SerializedName("KeyWords")
    @Expose
    private String keyWords;
    @SerializedName("CategoryType")
    @Expose
    private Integer categoryType;
    @SerializedName("IsMenu")
    @Expose
    private Boolean isMenu;
    @SerializedName("CategoryTypeSelected")
    @Expose
    private Integer categoryTypeSelected;
    @SerializedName("NewsId")
    @Expose
    private Integer newsId;
    @SerializedName("ImagePath")
    @Expose
    private String imagePath;
    @SerializedName("NewImage")
    @Expose
    private Object newImage;


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
     *     The Id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 
     * @return
     *     The name
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name
     *     The Name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * @return
     *     The uRL
     */
    public Object getURL() {
        return uRL;
    }

    /**
     * 
     * @param uRL
     *     The URL
     */
    public void setURL(Object uRL) {
        this.uRL = uRL;
    }



    /**
     * 
     * @return
     *     The parentId
     */
    public Object getParentId() {
        return parentId;
    }

    /**
     * 
     * @param parentId
     *     The ParentId
     */
    public void setParentId(Object parentId) {
        this.parentId = parentId;
    }

    /**
     * 
     * @return
     *     The displayOrder
     */
    public Integer getDisplayOrder() {
        return displayOrder;
    }

    /**
     * 
     * @param displayOrder
     *     The DisplayOrder
     */
    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
    }

    /**
     * 
     * @return
     *     The description
     */
    public String getDescription() {
        return description;
    }

    /**
     * 
     * @param description
     *     The Description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 
     * @return
     *     The keyWords
     */
    public String getKeyWords() {
        return keyWords;
    }

    /**
     * 
     * @param keyWords
     *     The KeyWords
     */
    public void setKeyWords(String keyWords) {
        this.keyWords = keyWords;
    }

    /**
     * 
     * @return
     *     The categoryType
     */
    public Integer getCategoryType() {
        return categoryType;
    }

    /**
     * 
     * @param categoryType
     *     The CategoryType
     */
    public void setCategoryType(Integer categoryType) {
        this.categoryType = categoryType;
    }

    /**
     * 
     * @return
     *     The isMenu
     */
    public Boolean getIsMenu() {
        return isMenu;
    }

    /**
     * 
     * @param isMenu
     *     The IsMenu
     */
    public void setIsMenu(Boolean isMenu) {
        this.isMenu = isMenu;
    }

    /**
     * 
     * @return
     *     The categoryTypeSelected
     */
    public Integer getCategoryTypeSelected() {
        return categoryTypeSelected;
    }

    /**
     * 
     * @param categoryTypeSelected
     *     The CategoryTypeSelected
     */
    public void setCategoryTypeSelected(Integer categoryTypeSelected) {
        this.categoryTypeSelected = categoryTypeSelected;
    }

    /**
     * 
     * @return
     *     The newsId
     */
    public Integer getNewsId() {
        return newsId;
    }

    /**
     * 
     * @param newsId
     *     The NewsId
     */
    public void setNewsId(Integer newsId) {
        this.newsId = newsId;
    }

    /**
     * 
     * @return
     *     The imagePath
     */
    public String getImagePath() {
        return imagePath;
    }

    /**
     * 
     * @param imagePath
     *     The ImagePath
     */
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    /**
     * 
     * @return
     *     The newImage
     */
    public Object getNewImage() {
        return newImage;
    }

    /**
     * 
     * @param newImage
     *     The NewImage
     */
    public void setNewImage(Object newImage) {
        this.newImage = newImage;
    }

}
