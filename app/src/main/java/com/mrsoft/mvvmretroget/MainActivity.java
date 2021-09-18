package com.mrsoft.mvvmretroget;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mrsoft.mvvmretroget.adapter.NameListAdapter;
import com.mrsoft.mvvmretroget.model.NameListModel;
import com.mrsoft.mvvmretroget.viewmodel.NameListViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity implements NameListAdapter.ItemClickListener{


    private List<NameListModel> nameListModel;
    private NameListAdapter nameListAdapter;
    private NameListViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        final TextView textView = findViewById(R.id.noResultTv);
        //LinearLayoutManager layoutManager =new GridLayoutManager(this,1);
        LinearLayoutManager layoutManager =new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        nameListAdapter = new NameListAdapter(this,nameListModel,this);
        recyclerView.setAdapter(nameListAdapter);

        viewModel = ViewModelProviders.of(this).get(NameListViewModel.class);
        viewModel.getNameListObserver().observe(this, new Observer<List<NameListModel>>() {
            @Override
            public void onChanged(List<NameListModel> nameModels) {
                if(nameModels != null) {
                    nameListModel = nameModels;
                    nameListAdapter.setNameList(nameModels);

                    //textView.setVisibility(View.GONE);
                } else {
                    textView.setVisibility(View.VISIBLE);
                }
            }
        });
        viewModel.makeApiCall();
    }

    @Override
    public void onNameClick(NameListModel name) {
        //Intent i = new Intent(MainActivity.this, ViewActivity.class);
        //i.putExtra("name", name); // sending our object. In Kotlin is the same
        //startActivity(i);
        Toast.makeText(this, "Clicked User Name is : " +name.getName(), Toast.LENGTH_SHORT).show();

    }
}