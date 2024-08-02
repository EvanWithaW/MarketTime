package com.evanwithaw.markettime.utils;

import com.evanwithaw.markettime.MarketTime;

import java.util.Calendar;

public class scheduler {

    public static int getTime() {
        Calendar cal = Calendar.getInstance();
        int hours = cal.get(Calendar.HOUR_OF_DAY);
//        getLogger().info("Hours: " + hours);
        return hours;
    }

    public static int timeUntilClosing() {
        int currentTime = getTime();
        int marketCloseTime = MarketTime.getInstance().getMarketConfigHandler().getMarketCloseTime();

        if (currentTime <= marketCloseTime) {
            return marketCloseTime - currentTime;
        } else {
            return (24 - currentTime) + marketCloseTime;
        }
    }

}
