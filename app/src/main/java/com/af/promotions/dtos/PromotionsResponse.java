
package com.af.promotions.dtos;

import java.util.ArrayList;
import java.util.List;

import com.af.promotions.models.Promotion;
import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class PromotionsResponse {

    @SerializedName("promotions")
    @Expose
    private List<Promotion> promotions = new ArrayList<Promotion>();

    /**
     * 
     * @return
     *     The promotions
     */
    public List<Promotion> getPromotions() {
        return promotions;
    }

    /**
     * 
     * @param promotions
     *     The promotions
     */
    public void setPromotions(List<Promotion> promotions) {
        this.promotions = promotions;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
