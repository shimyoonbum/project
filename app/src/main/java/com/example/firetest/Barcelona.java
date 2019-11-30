package com.example.firetest;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import java.util.ArrayList;

@SuppressWarnings("deprecation")
public class Barcelona extends TabActivity {
    GalleryBarcelonaAdapter adapter;
    TextView textView;
    WebView web;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.barcelona);

        TabHost tabHost = getTabHost();
        TabHost.TabSpec tabSpec = tabHost.newTabSpec("SONG").setIndicator("주요여행지");
        tabSpec.setContent(R.id.artist);
        tabHost.addTab(tabSpec);

        TabHost.TabSpec tabSpec2 = tabHost.newTabSpec("SONG2").setIndicator("날씨");
        tabSpec2.setContent(R.id.artist2);
        tabHost.addTab(tabSpec2);

        web = (WebView)findViewById(R.id.artist2);
        web.setWebViewClient(new ViewClient());
        WebSettings webset = web.getSettings();
        web.loadUrl("https://weather.com/ko-KR/weather/hourbyhour/l/0ce8d487db0a25631ee2017ddbe068bfba0de2b24de23f0b82c95863724f3a86");
        webset.setBuiltInZoomControls(true);

        TabHost.TabSpec tabSpec3 = tabHost.newTabSpec("SONG3").setIndicator("숙박");
        tabSpec3.setContent(R.id.artist3);
        tabHost.addTab(tabSpec3);


        TabHost.TabSpec tabSpec4 = tabHost.newTabSpec("SONG4").setIndicator("맛집");
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

        adapter = new GalleryBarcelonaAdapter(this);

        for(int i = 1; i < 5; i++){
            adapter.addItem(getResources().getIdentifier("b"+i, "drawable",this.getPackageName()));
        }


        final ImageView iv = (ImageView)findViewById(R.id.img01);
        textView = (TextView)findViewById(R.id.sample);
        Gallery gallery = (Gallery)findViewById(R.id.gallery01);

        final ArrayList<String> arrayList = new ArrayList<>();

        arrayList.add("사그라다 파밀리아 성당은 스페인 바르셀로나에 짓고 있는 로마 가톨릭 성당이다. " +
                "'사그라다'는 스페인어로 신성한 또는 성스러운이라는 뜻을 가졌으며," +
                " 파밀리아는 가족을 뜻하기 때문에 성가족성당이라고도 불린다. 카탈루냐 출신의 건축가 안토니 가우디가 설계하고 직접 건축을 책임졌다. \n " );

        arrayList.add("구엘 공원은 스페인 바르셀로나에 있는 공원이다. 1984년 유네스코 세계유산에 등록되었다." +
                " 안토니 가우디의 작품 중 하나이다. 카르멜 언덕 위에 있으며, 공용 공원과 초등학교가 위치해 있다." +
                " 에우제비 구에이를 기리기 위해 1914년에 완성되었다.");
        arrayList.add("카사밀라는 익히 라 페드레라로도 불리고 있으며, " +
                "안토니오 가우디의 작품으로 바르셀로나 중심가인 파세오 데 그라시아 거리에 위치해 있다." +
                " 이며 1906년 설계를 시작해 1912년에 완공된 고급 연립주택이다. 1984년 유네스코 세계 문화 유산으로 지정되었다.");
        arrayList.add("카사 바트요는 스페인 바르셀로나에 있는 안토니 가우디가 설계한 건축물 중 하나로, " +
                "2005년 유네스코 세계 문화 유산에 등록되었다. 이는 앞서 해당 부지에 있었던 Emilio Sala Cortés의 건물을 리모델링한 것이다.");

        gallery.setAdapter(adapter);
        gallery.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> parent, View v, int position, long id){
                Integer galleryItem = adapter.getGalleryItem(position);
                iv.setImageResource(galleryItem);
                textView.setText(arrayList.get(position));
            }
        });
    }

    class ViewClient extends WebViewClient{

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return super.shouldOverrideUrlLoading(view, url);
        }
    }
}
