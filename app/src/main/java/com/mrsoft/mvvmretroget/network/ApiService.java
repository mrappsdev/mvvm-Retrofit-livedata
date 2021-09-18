package com.mrsoft.mvvmretroget.network;

import com.mrsoft.mvvmretroget.model.NameListModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("api/php_api_demo.php")
    Call<List<NameListModel>> getNameList();
}
