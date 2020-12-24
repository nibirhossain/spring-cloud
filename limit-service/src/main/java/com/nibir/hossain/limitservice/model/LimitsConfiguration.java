package com.nibir.hossain.limitservice.model;

/*
 * Created by Nibir Hossain on 21.12.20
 */
public class LimitsConfiguration {
    private int minimum;
    private int maximum;

    public LimitsConfiguration() {

    }

    public LimitsConfiguration(int minimum, int maximum) {
        this.minimum = minimum;
        this.maximum = maximum;
    }

    public int getMinimum() {
        return minimum;
    }

    public void setMinimum(int minimum) {
        this.minimum = minimum;
    }

    public int getMaximum() {
        return maximum;
    }

    public void setMaximum(int maximum) {
        this.maximum = maximum;
    }
}
