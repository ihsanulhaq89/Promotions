
package com.af.promotions.models;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Promotion implements Serializable {

    @SerializedName("button")
    @Expose
    private Object button;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("footer")
    @Expose
    private String footer;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("title")
    @Expose
    private String title;

    /**
     * 
     * @return
     *     The button
     */
    public Object getButton() {
        return button;
    }

    /**
     * 
     * @param button
     *     The button
     */
    public void setButton(Object button) {
        this.button = button;
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
     *     The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 
     * @return
     *     The footer
     */
    public String getFooter() {
        return footer;
    }

    /**
     * 
     * @param footer
     *     The footer
     */
    public void setFooter(String footer) {
        this.footer = footer;
    }

    /**
     * 
     * @return
     *     The image
     */
    public String getImage() {
        return image;
    }

    /**
     * 
     * @param image
     *     The image
     */
    public void setImage(String image) {
        this.image = image;
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

}
