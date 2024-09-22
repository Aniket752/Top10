package com.aniket.top10.news;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.aniket.top10.R;
import com.aniket.top10.databinding.ActivityMainBinding;
import com.aniket.top10.news.adapter.TopNewsAdapter;
import com.aniket.top10.news.viewModel.TopNewsViewModel;

public class MainActivity extends AppCompatActivity implements TopNewsAdapter.ArticleAdapterCallback {

    private TopNewsViewModel viewModel;
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        viewModel =new ViewModelProvider(this).get(TopNewsViewModel.class);

        viewModel.getTopArticles();

        observeError();
        observeArticleData();



    }

    public void observeError(){
        viewModel.error.observe(this, s -> Toast.makeText(MainActivity.this,s,Toast.LENGTH_LONG).show());
    }

    public void observeArticleData(){
        viewModel.topNewsList.observe(this, topNewsEntities -> {
            if(topNewsEntities != null){
                TopNewsAdapter adapter = new TopNewsAdapter(topNewsEntities,MainActivity.this);
                binding.rvList.setAdapter(adapter);
                binding.rvList.setLayoutManager(new LinearLayoutManager(MainActivity.this));
            }
        });
    }

    @Override
    public void readArticle(String url) {
        try {
            System.out.println(url);
            Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(myIntent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, "No application can handle this request.",  Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }
}