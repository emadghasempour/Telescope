
package com.negah.telescope.app.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Market {

    @SerializedName("marketName")
    @Expose
    private String marketName;
    @SerializedName("marketIcon")
    @Expose
    private String marketIcon;
    @SerializedName("marketLink")
    @Expose
    private String marketLink;

    /**
     * 
     * @return
     *     The marketName
     */
    public String getMarketName() {
        return marketName;
    }

    /**
     * 
     * @param marketName
     *     The marketName
     */
    public void setMarketName(String marketName) {
        this.marketName = marketName;
    }

    /**
     * 
     * @return
     *     The marketIcon
     */
    public String getMarketIcon() {
        return marketIcon;
    }

    /**
     * 
     * @param marketIcon
     *     The marketIcon
     */
    public void setMarketIcon(String marketIcon) {
        this.marketIcon = marketIcon;
    }

    /**
     * 
     * @return
     *     The marketLink
     */
    public String getMarketLink() {
        return marketLink;
    }

    /**
     * 
     * @param marketLink
     *     The marketLink
     */
    public void setMarketLink(String marketLink) {
        this.marketLink = marketLink;
    }

}
