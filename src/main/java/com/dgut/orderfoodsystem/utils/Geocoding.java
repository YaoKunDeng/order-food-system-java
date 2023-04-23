package com.dgut.orderfoodsystem.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;


/**
 * @Description TODO
 * @Author akun
 * @Data 2023-4-22
 */
public class Geocoding {


    public static ApiResponse addressExchange(String address) throws IOException {
        ApiResponse<Map> mapApiResponse = new ApiResponse<>();
        String apiKey = "51ac4a3c853109db2c5255ef920b00ed";
        Map<String, String> map = new HashMap<String, String>();
        String url = "https://restapi.amap.com/v3/geocode/geo?key=" + apiKey +
                "&address=" + URLEncoder.encode(address, "UTF-8");
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod("GET");
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder responseBuilder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            responseBuilder.append(line);
        }
        String response = responseBuilder.toString();
        connection.disconnect();
        // 解析响应结果

        JSONObject jsonObject = JSONObject.parseObject(response);
        String status = jsonObject.getString("status");
        if ("1".equals(status)) {
            JSONArray geocodes = jsonObject.getJSONArray("geocodes");
            if (geocodes != null && !geocodes.isEmpty()) {
                JSONObject geocode = geocodes.getJSONObject(0);
                String location = geocode.getString("location");
                String[] locations = location.split(",");
                if (locations.length >= 2) {
                    double longitude = Double.parseDouble(locations[0]);
                    double latitude = Double.parseDouble(locations[1]);

                    map.put("longitude",String.valueOf(longitude));
                    map.put("latitude",String.valueOf(latitude));
                    mapApiResponse.setCode(200);
                    mapApiResponse.setData(map);
                    mapApiResponse.setMessage("解析地址成功!");
                }
            }
        } else {
            mapApiResponse.setCode(500);
            mapApiResponse.setData(map);
            mapApiResponse.setMessage("解析地址失败!");
        }



        return mapApiResponse;
    }

    //计算经纬度得距离
    private static final double EARTH_RADIUS = 6371; // 地球半径，单位千米

    public static double distance(double lat1, double lon1, double lat2, double lon2) {
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = EARTH_RADIUS * c;

        return Math.abs(distance);
    }

}
