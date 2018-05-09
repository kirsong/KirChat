package com.chat.mobile.util;

import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {

    public static String createDateFormat(){
        return new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
    }


}
