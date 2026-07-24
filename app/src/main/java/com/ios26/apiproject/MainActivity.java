package com.ios26.apiproject;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProgressBar progressbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recView);
        progressbar = findViewById(R.id.progressbar);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        progressbar.setVisibility(VISIBLE);
        fetchComplexData();
    }

    private void fetchComplexData() {
        ApiService apiService = RetrofitClient.getApiService();
        Call<ProductResponse> call = apiService.getProductsData();

        call.enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                if (response.isSuccessful() && response.body() != null) {

                    ProductResponse productResponse = response.body();
                    
                    ComplexAdapter adapter = new ComplexAdapter(productResponse.getProducts());
                    recyclerView.setAdapter(adapter);
                    progressbar.setVisibility(GONE);
                } else {
                    Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
