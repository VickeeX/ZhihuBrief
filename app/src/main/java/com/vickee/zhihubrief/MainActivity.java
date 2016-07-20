package com.vickee.zhihubrief;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.vickee.zhihubrief.NewsResult.NewsLatestResult;
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

    String BASE_URL = "http://news-at.zhihu.com";
    List<NewsLatestResult.StoriesBean> story;
    NewsLatestAdapter newsLatestAdapter;
    RecyclerView recyclerView;
    private ViewPager mViewpager;
    private List<ImageView> imageViews;
    private TextView mTextView;
    private LinearLayout linearLayout;
    private int[] bannerImages = {R.drawable.image1,R.drawable.image2,R.drawable.image3,R.drawable.image4};
    private BannerAdapter mAdapter;
    private BannerListener bannerListener;

    // 圆圈标志位
    private int pointIndex = 0;
    // 线程标志
    private boolean isStop = false;

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

        mViewpager = (ViewPager) findViewById(R.id.viewpager);
        linearLayout = (LinearLayout) findViewById(R.id.points);
        initVPData();
        initVPAction();
        new Thread(new Runnable() {

            @Override
            public void run() {
                while (!isStop) {
                    SystemClock.sleep(2000);
                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            mViewpager.setCurrentItem(mViewpager.getCurrentItem() + 1);
                        }
                    });
                }
            }
        }).start();


        recyclerView = (RecyclerView) findViewById(R.id.rv_latest_news);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerDecoration(this));
        story = new ArrayList<>();
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

    private void initVPData() {
        imageViews = new ArrayList<ImageView>();
        View view;
        LayoutParams params;
        for (int i = 0; i < bannerImages.length; i++) {
            // 设置广告图
            ImageView imageView = new ImageView(MainActivity.this);
            imageView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
            imageView.setBackgroundResource(bannerImages[i]);
            imageViews.add(imageView);
            // 设置圆圈点
            view = new View(MainActivity.this);
            params = new LayoutParams(5, 5);
            params.leftMargin = 10;
            view.setBackgroundResource(R.drawable.point_background);
            view.setLayoutParams(params);
            view.setEnabled(false);

            linearLayout.addView(view);
        }
        mAdapter = new BannerAdapter(imageViews);
        mViewpager.setAdapter(mAdapter);
    }

    private void initVPAction() {
        bannerListener = new BannerListener();
        mViewpager.setOnPageChangeListener(bannerListener);
        //取中间数来作为起始位置
        int index = (Integer.MAX_VALUE / 2) - (Integer.MAX_VALUE / 2 % imageViews.size());
        //用来出发监听器
        mViewpager.setCurrentItem(index);
        linearLayout.getChildAt(pointIndex).setEnabled(true);
    }

    class BannerListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrollStateChanged(int arg0) {
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageSelected(int position) {
            int newPosition = position % bannerImages.length;
            linearLayout.getChildAt(newPosition).setEnabled(true);
            linearLayout.getChildAt(pointIndex).setEnabled(false);
            // 更新标志位
            pointIndex = newPosition;

        }
    }

    @Override
    protected void onDestroy() {
        // 关闭定时器
        isStop = true;
        super.onDestroy();
    }
}
