package com.vickee.zhihubrief.view.Activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.vickee.zhihubrief.entity.NewsContentResult;
import com.vickee.zhihubrief.R;

public class NewsActivity extends AppCompatActivity {
    String BASE_URL = "http://news-at.zhihu.com";
    private static final String TAG = "NewsActivity";
    static int newsId;
    private NewsContentResult result;
    private WebView wvNews;
    private WebSettings webSettings;


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
        newsId = NewsActivity.this.getIntent().getIntExtra("id", 0);
        Log.i(TAG, "id:" + newsId);
//        new Thread() {
//            @Override
//            public void run() {
//                QueryNews();
//                Log.i(TAG, "new thread");
//            }
//        }.start();

        wvNews = (WebView) findViewById(R.id.wv_news);
        webSettings = wvNews.getSettings();
        webSettings.setJavaScriptEnabled(true);
        wvNews.loadUrl("http://news-at.zhihu.com/api/4/news/"+newsId);
        //获取WebSettings对象,设置缩放
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setUseWideViewPort(true);
        wvNews.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.d("访问网址：", url);
                wvNews.loadUrl(url);
                return true;
            }
        });
    }


//    public interface ZhihuNewsService {
//        @GET("/api/4/news/{newsId}")
//        Call<NewsContentResult> getResult(@Path("newsId") int newsId);
//    }
//
//    public void QueryNews() {
//        Retrofit retrofit = new Retrofit.Builder()
//                .addConverterFactory(GsonConverterFactory.create())
//                .baseUrl(BASE_URL)
//                .build();
//
//        ZhihuNewsService service = retrofit.create(ZhihuNewsService.class);
//        Call<NewsContentResult> call = service.getResult(newsId);
//        call.enqueue(new Callback<NewsContentResult>() {
//
//            @Override
//            public void onResponse(Call<NewsContentResult> call, Response<NewsContentResult> response) {
//                if (response.isSuccessful()) {
//                    result = response.body();
//                    if (result != null) {
//                        Log.i(TAG, "title:" + result.title);
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<NewsContentResult> call, Throwable t) {
//                Log.e(TAG, "get news failed");
//            }
//        });
//    }
}
