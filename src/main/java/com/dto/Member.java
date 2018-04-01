package com.dto;

import java.io.Serializable;

public class Member implements Serializable {
    //身份
    private String identity;
    //消费
    private double consumption;
    //优惠金额
    private double preferentialAmount;


    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public double getConsumption() {
        return consumption;
    }

    public void setConsumption(double consumption) {
        this.consumption = consumption;
    }

    public double getPreferentialAmount() {
        return preferentialAmount;
    }

    public void setPreferentialAmount(double preferentialAmount) {
        this.preferentialAmount = preferentialAmount;
    }

    public void setAmount(double discount) {
        this.preferentialAmount = this.consumption * discount;
    }

}
