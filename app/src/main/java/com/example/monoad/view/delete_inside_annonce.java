package com.example.monoad.view;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.monoad.Adapter.RecyclerAdapter_deletefirst;
import com.example.monoad.Adapter.RecyclerAdapter_deletesecond;
import com.example.monoad.R;
import com.example.monoad.model.Apiclient_home;
import com.example.monoad.model.apiinterface_home;
import com.example.monoad.model.contact_category;
import com.example.monoad.model.contact_inside_annonce;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class delete_inside_annonce extends AppCompatActivity {
    Toolbar toolbar;
    ProgressBar progressBar;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerAdapter_deletesecond recyclerAdapter_secondry;
    private List<contact_inside_annonce> contactList;
    private apiinterface_home apiinterface;
    int id = 0;
    String name;
    int sec_id;
    Typeface myTypeface;
    Intent intent;
    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_first);
        progressBar = (ProgressBar) findViewById(R.id.progressBar_subject);
        textView = (TextView) findViewById(R.id.toolbar_title);
        progressBar.setVisibility(View.VISIBLE);
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        intent = getIntent();
        name = intent.getStringExtra("name");
        id = intent.getIntExtra("id", 0);

        myTypeface = Typeface.createFromAsset(getAssets(), "fonts/flat.ttf");
        textView.setText("مسح اعلان من قسم");
        textView.setTypeface(myTypeface);

        this.setTitle("");
        toolbar.setNavigationIcon(R.drawable.ic_keyboard_arrow_left_black_24dp);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                }
        );

        recyclerView = (RecyclerView) findViewById(R.id.review);
        progressBar = (ProgressBar) findViewById(R.id.progressBar_subject);
        progressBar.setVisibility(View.VISIBLE);
        layoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        fetchInfo();
    }

    public void fetchInfo() {
        apiinterface = Apiclient_home.getapiClient().create(apiinterface_home.class);
        Call<List<contact_inside_annonce>> call = apiinterface.getcontacts_inside_annonce();
        call.enqueue(new Callback<List<contact_inside_annonce>>() {
            @Override
            public void onResponse(Call<List<contact_inside_annonce>> call, Response<List<contact_inside_annonce>> response) {
                contactList = response.body();
                progressBar.setVisibility(View.GONE);
                recyclerAdapter_secondry = new RecyclerAdapter_deletesecond(delete_inside_annonce.this, contactList);
                recyclerView.setAdapter(recyclerAdapter_secondry);


            }

            @Override
            public void onFailure(Call<List<contact_inside_annonce>> call, Throwable t) {

            }
        });
    }
}