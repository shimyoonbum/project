package com.example.firetest;

import android.app.TabActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("deprecation")
public class Barcelona extends TabActivity {
    public final static String VIDEO_URL = "https://www.youtube.com/watch?v=TylzXtQgIG8";
    public final static String VIDEO_URL2 = "https://www.youtube.com/watch?v=aSRp_9ewOuk";
    public final static String VIDEO_URL3 = "https://www.youtube.com/watch?v=vimd3NpeJXk";
    public final static String VIDEO_URL4 = "https://www.youtube.com/watch?v=V1v-KtinYh0&t=209s";

    GalleryBarcelonaAdapter adapter5;
    Button button;
    ImageButton Image,image2, image3, image4;
    WebView web;
    List<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.barcelona);

        TabHost tabHost = getTabHost();
        TabHost.TabSpec tabSpec = tabHost.newTabSpec("SONG").setIndicator("주요여행지");
        tabSpec.setContent(R.id.artist);
        tabHost.addTab(tabSpec);

        Image = (ImageButton)findViewById(R.id.ib1);
        Image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(VIDEO_URL));
                startActivity(intent);
            }
        });
        image2 = (ImageButton)findViewById(R.id.ib2);
        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(VIDEO_URL2));
                startActivity(intent);
            }
        });
        image3 = (ImageButton)findViewById(R.id.ib3);
        image3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(VIDEO_URL3));
                startActivity(intent);
            }
        });
        image4 = (ImageButton)findViewById(R.id.ib4);
        image4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(VIDEO_URL4));
                startActivity(intent);
            }
        });

        adapter5 = new GalleryBarcelonaAdapter(this);

        for(int i = 1; i < 5; i++){
            adapter5.addItem(getResources().getIdentifier("b"+i, "drawable",this.getPackageName()));
        }


        final ImageView iv = (ImageView)findViewById(R.id.img01);
        Gallery gallery = (Gallery)findViewById(R.id.gallery01);

        gallery.setAdapter(adapter5);
        final LinearLayout linearLayout = (LinearLayout)findViewById(R.id.l1);
        final LinearLayout linearLayout2 = (LinearLayout)findViewById(R.id.l2);
        final LinearLayout linearLayout3 = (LinearLayout)findViewById(R.id.l3);
        final LinearLayout linearLayout4 = (LinearLayout)findViewById(R.id.l4);

        gallery.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> parent, View v, int position, long id){
                Integer galleryItem = adapter5.getGalleryItem(position);
                iv.setImageResource(galleryItem);
                if(position == 0){
                    linearLayout.setVisibility(View.VISIBLE);
                    linearLayout2.setVisibility(View.GONE);
                    linearLayout3.setVisibility(View.GONE);
                    linearLayout4.setVisibility(View.GONE);

                }else if(position == 1){
                    linearLayout.setVisibility(View.GONE);
                    linearLayout2.setVisibility(View.VISIBLE);
                    linearLayout3.setVisibility(View.GONE);
                    linearLayout4.setVisibility(View.GONE);

                }else if(position == 2){
                    linearLayout.setVisibility(View.GONE);
                    linearLayout2.setVisibility(View.GONE);
                    linearLayout3.setVisibility(View.VISIBLE);
                    linearLayout4.setVisibility(View.GONE);

                }else if(position == 3){
                    linearLayout.setVisibility(View.GONE);
                    linearLayout2.setVisibility(View.GONE);
                    linearLayout3.setVisibility(View.GONE);
                    linearLayout4.setVisibility(View.VISIBLE);
                }
            }
        });

        TabHost.TabSpec tabSpec2 = tabHost.newTabSpec("SONG2").setIndicator("날씨");
        tabSpec2.setContent(R.id.artist2);
        tabHost.addTab(tabSpec2);

        web = (WebView)findViewById(R.id.artist2);
        web.setWebViewClient(new ViewClient());
        WebSettings webset = web.getSettings();
        web.loadUrl("https://weather.com/ko-KR/weather/hourbyhour/l/0ce8d487db0a25631ee2017ddbe068bfba0de2b24de23f0b82c95863724f3a86");
        webset.setBuiltInZoomControls(true);

        TabHost.TabSpec tabSpec3 = tabHost.newTabSpec("SONG3").setIndicator("숙박/항공권");
        tabSpec3.setContent(R.id.artist3);
        tabHost.addTab(tabSpec3);

        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                if(tabId.equals("SONG3")){
                    Intent intent1 = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.hotelscombined.co.kr/"));
                    startActivity(intent1);
                }
            }
        });

        TabHost.TabSpec tabSpec4 = tabHost.newTabSpec("SONG4").setIndicator("리뷰");
        tabSpec4.setContent(R.id.artist4);
        tabHost.addTab(tabSpec4);


        button = (Button)findViewById(R.id.btn11);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), BarcelonaReview.class);
                startActivity(i);
            }
        });

        tabHost.setCurrentTab(0);
    }
    //웹 브라우저를 띄우기 위해선 반드시 오버라이드 해야하는 메소드. 내부정의는 필요없음
    class ViewClient extends WebViewClient{
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return super.shouldOverrideUrlLoading(view, url);
        }
    }
}
