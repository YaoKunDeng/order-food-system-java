package com.dgut.orderfoodsystem;

import com.dgut.orderfoodsystem.utils.ApiResponse;
import com.dgut.orderfoodsystem.utils.Geocoding;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.Map;

/**
 * @Description TODO
 * @Author akun
 * @Data 2023-4-22
 */
@SpringBootTest
public class GeocodingTest {
    @Test
    public void testGeocoding() throws IOException {
        String address = "广东省茂名市电白区旦场镇旦场中学";
        ApiResponse response = Geocoding.addressExchange(address);
        Map data = (Map) response.getData();
        Object longitude = data.get("longitude");
        System.out.println(longitude);
    }

    @Test
    public void testDistance(){
        double lat1 = Double.valueOf("21.521444");
        double long1 = Double.valueOf("111.101002");
        double lat2 =Double.valueOf("21.52121");
        double long2 = Double.valueOf("111.1245");
        double distance = Geocoding.distance(lat1, long1, lat1, long2);
        System.out.println(distance);
    }
}
