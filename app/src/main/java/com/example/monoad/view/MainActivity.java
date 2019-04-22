package com.example.monoad.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;

import com.example.monoad.R;
import com.example.monoad.model.Apiclient_home;
import com.example.monoad.model.apiinterface_home;
import com.example.monoad.model.contact_category;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    AppCompatButton user,add,addcategory,addfirstcategory,delete,code;
    private List<contact_category> contactList;
    private apiinterface_home apiinterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializer();
        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startActivity(new Intent(MainActivity.this,Users.class));
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Add_annonce.class));

            }
        });
        addcategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchInfo();
            }
        });
        addfirstcategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, add_first_category.class));
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             startActivity(new Intent(MainActivity.this,delete.class));
            }
        });
        code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,add_code.class));
            }
        });
    }
    public void initializer(){

        user=(AppCompatButton)findViewById(R.id.appCompatButtonuser);
        add=(AppCompatButton)findViewById(R.id.appCompatButtonadd);
        addcategory=(AppCompatButton)findViewById(R.id.appCompatButtonaddcategory);
        addfirstcategory=(AppCompatButton)findViewById(R.id.appCompatButtonaddcategoryra);
        delete=(AppCompatButton)findViewById(R.id.appCompatButtondelete);
        code=(AppCompatButton)findViewById(R.id.appCompatButtoncode);
    }
    public void fetchInfo(){
        apiinterface= Apiclient_home.getapiClient().create(apiinterface_home.class);
        Call<List<contact_category>> call= apiinterface.getcontacts_category();
        call.enqueue(new Callback<List<contact_category>>() {
            @Override
            public void onResponse(Call<List<contact_category>> call, Response<List<contact_category>> response) {
                contactList = response.body();
                ArrayList<String> arrayList=new ArrayList<>();
                ArrayList<Integer>arrayList_id=new ArrayList<>();
                for (int i=0;i<contactList.size();i++){
                    arrayList.add(contactList.get(i).getname());
                    arrayList_id.add(contactList.get(i).getId());
                }
                Intent intent=new Intent(MainActivity.this,add_shop.class);
                intent.putStringArrayListExtra("category_list", arrayList);
                intent.putIntegerArrayListExtra("category_id",arrayList_id);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<List<contact_category>> call, Throwable t) {

            }
        });
    }
    }

