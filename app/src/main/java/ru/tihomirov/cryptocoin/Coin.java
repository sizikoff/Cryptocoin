package ru.tihomirov.cryptocoin;

import com.google.gson.annotations.SerializedName;

public class Coin {
    public String id;
    public String name;
    public String symbol;
    @SerializedName("price_usd")
    public double priceUsd;
    @SerializedName("percent_change_1h")
    public double percentChange;
}

