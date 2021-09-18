package com.mrsoft.mvvmretroget.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mrsoft.mvvmretroget.model.NameListModel;
import com.mrsoft.mvvmretroget.network.ApiService;
import com.mrsoft.mvvmretroget.network.RetroInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NameListViewModel extends ViewModel {

    private MutableLiveData<List<NameListModel>> nameList;

    public NameListViewModel(){
        nameList = new MutableLiveData<>();
    }

    public MutableLiveData<List<NameListModel>> getNameListObserver() {
        return nameList;

    }

    public void makeApiCall() {
        ApiService apiService = RetroInstance.getRetroClient().create(ApiService.class);
        Call<List<NameListModel>> call = apiService.getNameList();
        call.enqueue(new Callback<List<NameListModel>>() {
            @Override
            public void onResponse(Call<List<NameListModel>> call, Response<List<NameListModel>> response) {
                nameList.postValue(response.body());
            }

            @Override
            public void onFailure(Call<List<NameListModel>> call, Throwable t) {
                nameList.postValue(null);
            }
        });
    }
}
