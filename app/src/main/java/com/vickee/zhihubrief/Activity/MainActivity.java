package com.vickee.zhihubrief.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.vickee.zhihubrief.BeanResult.NewsLatestResult;
import com.vickee.zhihubrief.NewsLatestAdapter;
import com.vickee.zhihubrief.R;
import com.vickee.zhihubrief.widget.DividerDecoration;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "MainActivity";
    String BASE_URL = "http://news-at.zhihu.com";
    List<NewsLatestResult.StoriesBean> story;
    List<NewsLatestResult.TopStoriesBean> topStories;
    NewsLatestAdapter newsLatestAdapter;
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // includes the toolBar and drawer's initial in fact
        findById();

        //get the latest news and set data to the recyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerDecoration(this));
        story = new ArrayList<>();
        topStories = new ArrayList<>();

        queryLatestNews();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_latest_news) {
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public interface ZhihuLatestService {
        @GET("/api/4/news/latest")
        Call<NewsLatestResult> getResult();
    }

    public void findById() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        recyclerView = (RecyclerView) findViewById(R.id.rv_latest_news);

        setSupportActionBar(toolbar);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    public void queryLatestNews() {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build();

        ZhihuLatestService service = retrofit.create(ZhihuLatestService.class);
        Call<NewsLatestResult> call = service.getResult();
        call.enqueue(new Callback<NewsLatestResult>() {
            @Override
            public void onResponse(Call<NewsLatestResult> call, Response<NewsLatestResult> response) {
                if (response.isSuccessful()) {
                    NewsLatestResult result = response.body();
                    if (result != null) {
                        story = result.stories;
                        topStories = result.top_stories;
                        Log.e(TAG, "topStories:" + topStories.size());
                        if (story.size() != 0) {
                            newsLatestAdapter = new NewsLatestAdapter(MainActivity.this, story);
                            newsLatestAdapter.setOnItemClickListener(new NewsLatestAdapter.OnItemClickListener() {
                                @Override
                                public void onItemClick(View view, int position) {
                                    startActivity(new Intent(MainActivity.this, NewsActivity.class).putExtra("id", story.get(position).id));
                                }
                            });
                            recyclerView.setAdapter(newsLatestAdapter);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<NewsLatestResult> call, Throwable t) {
                Log.e("ZhihuLatestNews", "failed");
            }
        });
    }
}