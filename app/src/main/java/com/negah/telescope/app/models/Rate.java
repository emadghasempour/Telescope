
package com.negah.telescope.app.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Rate {

    @SerializedName("UI")
    @Expose
    private Integer uI;
    @SerializedName("UX")
    @Expose
    private Integer uX;
    @SerializedName("usibility")
    @Expose
    private Integer usibility;
    @SerializedName("total")
    @Expose
    private Integer total;
    @SerializedName("from")
    @Expose
    private Integer from;

    /**
     * 
     * @return
     *     The uI
     */
    public Integer getUI() {
        return uI;
    }

    /**
     * 
     * @param uI
     *     The UI
     */
    public void setUI(Integer uI) {
        this.uI = uI;
    }

    /**
     * 
     * @return
     *     The uX
     */
    public Integer getUX() {
        return uX;
    }

    /**
     * 
     * @param uX
     *     The UX
     */
    public void setUX(Integer uX) {
        this.uX = uX;
    }

    /**
     * 
     * @return
     *     The usibility
     */
    public Integer getUsibility() {
        return usibility;
    }

    /**
     * 
     * @param usibility
     *     The usibility
     */
    public void setUsibility(Integer usibility) {
        this.usibility = usibility;
    }

    /**
     * 
     * @return
     *     The total
     */
    public Integer getTotal() {
        return total;
    }

    /**
     * 
     * @param total
     *     The total
     */
    public void setTotal(Integer total) {
        this.total = total;
    }

    /**
     *
     * @return
     *     The from
     */
    public Integer getFrom() {
        return from;
    }

    /**
     *
     * @param from
     *     The from
     */
    public void setFrom(Integer from) {
        this.from = from;
    }

}
