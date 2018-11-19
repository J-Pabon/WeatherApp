package jpabon.com.weatherapp.helpers;

import android.text.TextUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

public class StringHelper {
    public static String EncodeArrayOfIntegers(List<Integer> parameters) throws UnsupportedEncodingException {
        String uriString = URLEncoder.encode(TextUtils.join(",", parameters), "utf-8");
        return uriString;
    }
}
