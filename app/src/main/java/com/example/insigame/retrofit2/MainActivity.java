package com.example.insigame.retrofit2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecycleAdapter adapter;
    private List<Contact> contacts;
    private ApiInterface apiInterface;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=findViewById(R.id.recyclerView);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        apiInterface =ApiClient.getApiClient().create(ApiInterface.class);

        Call<List<Contact>> call=apiInterface.getContacts();

        call.enqueue(new Callback<List<Contact>>() {
            @Override
            public void onResponse(Call<List<Contact>> call, Response<List<Contact>> response) {
                contacts=response.body();
                adapter=new RecycleAdapter(contacts);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Contact>> call, Throwable t) {

                Log.i("Ass",t.getMessage());
            }
        });


    }
}
