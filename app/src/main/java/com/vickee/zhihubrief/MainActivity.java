package com.vickee.zhihubrief;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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

import com.vickee.zhihubrief.NewsResult.NewsLatestResult;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
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

    String BASE_URL = "http://news-at.zhihu.com";
    List<Bitmap> bitmap;
    Handler handler;
    NewsLatestAdapter newsLatestAdapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        recyclerView = (RecyclerView) findViewById(R.id.rv_latest_news);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 0x123) {
//Adapter
                }
            }
        };
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
            queryLatestNews();
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

    public void queryLatestNews() {
        bitmap = new ArrayList<>();
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
                        final List<NewsLatestResult.StoriesBean> story = result.stories;
                        if (story.size() != 0) {
////                            final List<String> images = new ArrayList<>();
//                            for (int i = 0; i < story.size(); i++) {
////                                下载每个story下的images的第一张图片，并加入图片列表
//                                final int finalI = i;
//                                new Thread() {
//                                    public void run() {
//                                        try {
//                                            URL url = new URL(story.get(0).images.get(0));
//                                            InputStream inputStream = url.openStream();
//                                            bitmap.add(BitmapFactory.decodeStream(inputStream));
//                                            handler.sendEmptyMessage(0x123);
//                                            inputStream.close();
//                                            inputStream = url.openStream();
//
//                                            OutputStream outputStream = openFileOutput("image" + finalI + ".png", MODE_PRIVATE);
//                                            byte[] buff = new byte[1024];
//                                            int hasRead = 0;
//                                            while ((hasRead = inputStream.read(buff)) > 0) {
//                                                outputStream.write(buff, 0, hasRead);
//                                            }
//                                            inputStream.close();
//                                            outputStream.close();
//
//                                        } catch (Exception e) {
//                                            e.printStackTrace();
//                                        } finally {
//                                            Log.e("UrlThread", "run: end");
//                                        }
//                                    }
//                                }.start();
//                                Log.e("LatestNews", "Type:" + story.get(0).type);
//                                Log.e("LatestNews", "Id:" + story.get(0).id);
//                            }

                            new Thread() {
                                public void run() {
                                    try {
                                        URL url = new URL(story.get(0).images.get(0));
                                        InputStream inputStream = url.openStream();
                                        bitmap.add(BitmapFactory.decodeStream(inputStream));
                                        handler.sendEmptyMessage(0x123);
                                        inputStream.close();
                                        inputStream = url.openStream();

                                        OutputStream outputStream = openFileOutput("image0.png", MODE_PRIVATE);
                                        byte[] buff = new byte[1024];
                                        int hasRead = 0;
                                        while ((hasRead = inputStream.read(buff)) > 0) {
                                            outputStream.write(buff, 0, hasRead);
                                        }
                                        inputStream.close();
                                        outputStream.close();

                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    } finally {
                                        Log.e("UrlThread", "run: end");
                                    }
                                }
                            }.start();
                            newsLatestAdapter = new NewsLatestAdapter(MainActivity.this, story, bitmap);
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
