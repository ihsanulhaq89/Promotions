package com.af.promotions.interfaces;

public interface RestAPIListener {
    void onSuccess(Object responseObj);

    void onFailure(String localizedMessage);
}
