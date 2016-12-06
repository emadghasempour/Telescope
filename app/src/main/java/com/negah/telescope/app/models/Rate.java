
package com.negah.telescope.app.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Rate {

    @SerializedName("UI")
    @Expose
    private Float uI;
    @SerializedName("UX")
    @Expose
    private Float uX;
    @SerializedName("usibility")
    @Expose
    private Float usibility;
    @SerializedName("total")
    @Expose
    private Float total;
    @SerializedName("from")
    @Expose
    private Integer from;

    /**
     * 
     * @return
     *     The uI
     */
    public Float getUI() {
        return uI;
    }

    /**
     *
     * @param uI
     *     The UI
     */
    public void setUI(Float uI) {
        this.uI = uI;
    }

    /**
     * 
     * @return
     *     The uX
     */
    public Float getUX() {
        return uX;
    }

    /**
     *
     * @param uX
     *     The UX
     */
    public void setUX(Float uX) {
        this.uX = uX;
    }

    /**
     * 
     * @return
     *     The usibility
     */
    public Float getUsibility() {
        return usibility;
    }

    /**
     *
     * @param usibility
     *     The usibility
     */
    public void setUsibility(Float usibility) {
        this.usibility = usibility;
    }

    /**
     * 
     * @return
     *     The total
     */
    public Float getTotal() {
        return total;
    }

    /**
     *
     * @param total
     *     The total
     */
    public void setTotal(Float total) {
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
