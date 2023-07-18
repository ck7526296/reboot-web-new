package com.reboot.rebootweb.common.api;

import retrofit2.Call;
import retrofit2.http.GET;

import java.util.Map;

public interface TestApi {
    @GET("/fapi/v1/listenKey")
    Call<Map<String,String>> key();
}
