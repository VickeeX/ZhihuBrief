package com.vickee.zhihubrief.view.Activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

import com.vickee.zhihubrief.R;
import com.vickee.zhihubrief.entity.NewsContentResult;
import com.vickee.zhihubrief.presenter.StoryDetailPresenter;
import com.vickee.zhihubrief.view.view.IStoryDetailView;

public class StoryDetailActivity extends AppCompatActivity implements IStoryDetailView {
    private static final String TAG = "StoryDetailActivity";
    private WebView wvNews;
    private StoryDetailPresenter presenter = new StoryDetailPresenter(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity);
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

        wvNews = (WebView) findViewById(R.id.wv_news);
        WebSettings webSettings = wvNews.getSettings();
        webSettings.setJavaScriptEnabled(true);
//        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
//        webSettings.setAppCacheEnabled(true);
//        webSettings.setDatabaseEnabled(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setDefaultTextEncodingName("utf-8");

        presenter.getStoryDetail();
    }

    @Override
    public void loadContentData(NewsContentResult contentResult) {
        String head = "<head>\n" +
                "<meta charset=\"utf-8\">\n" +
                "<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge,chrome=1\">\n" +
                "<title>" + contentResult.title + "</title>\n" +
                "<meta name=\"viewport\" content=\"user-scalable=no, width=device-width\">\n" +
                "<link rel=\"stylesheet\" href=\"" + "http://7xk54v.com1.z0.glb.clouddn.com/app/bb/css/zhihu.css" + "\">\n" +
                "<style type=\"text/css\"></style>\n" +
                "<base target=\"_blank\">\n" +
                "</head>";
        String bodyStart = "<body>";
        String bodyEnd = "</body>";
        wvNews.loadData(head + bodyStart + contentResult.body.replaceAll("<div class=\"img-place-holder\"></div>", "") + bodyEnd, "text/html; charset=uft-8", "utf-8");
    }


    @Override
    public void getNewsFailed() {
        Toast.makeText(this, "Sorry for getting news failed.", Toast.LENGTH_LONG).show();
    }

    @Override
    public int getId() {
        return StoryDetailActivity.this.getIntent().getIntExtra("id", 0);
    }
}
