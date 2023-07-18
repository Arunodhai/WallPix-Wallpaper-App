package com.example.wallpaperapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ArrayList<ImageModel> modelClasslist;
    private RecyclerView recyclerView;
    Adapter adapter;
    CardView mnature,mspace,mabstract,mblack,mtrending,mcars;
    EditText editText;
    ImageButton search;


    private static final int EXTERNAL_STORAGE_PERMISSION_CONSTANT = 100;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        recyclerView= findViewById(R.id.recyclerview);
        mnature=findViewById(R.id.nature);
        mspace=findViewById(R.id.space);
        mabstract=findViewById(R.id.abstrct);
        mblack=findViewById(R.id.black);
        mtrending=findViewById(R.id.trending);
        mcars=findViewById(R.id.cars);
        editText=findViewById(R.id.edittext);
        search=findViewById(R.id.search);


        modelClasslist= new ArrayList<>();
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setHasFixedSize(true);
        adapter=new Adapter(getApplicationContext(),modelClasslist);
        recyclerView.setAdapter(adapter);
        findphotos();
        mnature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String query = "nature";
                getsearchimage(query);
                InputMethodManager manager
                        = (InputMethodManager)
                        getSystemService(
                                Context.INPUT_METHOD_SERVICE);
                manager
                        .hideSoftInputFromWindow(
                                view.getWindowToken(), 0);
            }
        });

        mspace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String query = "space";
                getsearchimage(query);
                InputMethodManager manager
                        = (InputMethodManager)
                        getSystemService(
                                Context.INPUT_METHOD_SERVICE);
                manager
                        .hideSoftInputFromWindow(
                                view.getWindowToken(), 0);
            }
        });
        mabstract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String query = "abstract";
                getsearchimage(query);
                InputMethodManager manager
                        = (InputMethodManager)
                        getSystemService(
                                Context.INPUT_METHOD_SERVICE);
                manager
                        .hideSoftInputFromWindow(
                                view.getWindowToken(), 0);
            }
        });
        mblack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String query = "black";
                getsearchimage(query);
                InputMethodManager manager
                        = (InputMethodManager)
                        getSystemService(
                                Context.INPUT_METHOD_SERVICE);
                manager
                        .hideSoftInputFromWindow(
                                view.getWindowToken(), 0);
            }
        });

        mcars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String query = "cars";
                getsearchimage(query);
                InputMethodManager manager
                        = (InputMethodManager)
                        getSystemService(
                                Context.INPUT_METHOD_SERVICE);
                manager
                        .hideSoftInputFromWindow(
                                view.getWindowToken(), 0);
            }
        });

        mtrending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findphotos();
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String query= editText.getText().toString().trim().toLowerCase();
                if(query.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Enter something",Toast.LENGTH_SHORT).show();
                }
                else{
                    getsearchimage(query);
                }
            }
        });



    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void getsearchimage(String query) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);

        ApiUtilities.getApiInterface().getSearchImage(query,1,80).enqueue(new Callback<SearchModel>() {
            @Override
            public void onResponse(Call<SearchModel> call, Response<SearchModel> response) {
                modelClasslist.clear();
                if(response.isSuccessful()){
                    modelClasslist.addAll(response.body().getPhotos());
                    adapter.notifyDataSetChanged();
                }
                else{
                    Toast.makeText(getApplicationContext(),"Not able to get",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SearchModel> call, Throwable throwable) {

            }
        });
    }

    private void findphotos() {
        modelClasslist.clear();
        ApiUtilities.getApiInterface().getImage(1,80).enqueue(new Callback<SearchModel>() {
            @Override
            public void onResponse(Call<SearchModel> call, Response<SearchModel> response) {
                modelClasslist.clear();
                if(response.isSuccessful()){
                    modelClasslist.addAll(response.body().getPhotos());
                    adapter.notifyDataSetChanged();
                }
                else{
                    Toast.makeText(getApplicationContext(),"Not able to get",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SearchModel> call, Throwable throwable) {

            }
        });

    }
}