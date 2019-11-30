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
public class Madrid extends TabActivity {
    GalleryMadridAdapter adapter5;
    TextView textView;
    WebView web;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.madrid);

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
        web.loadUrl("https://weather.com/ko-KR/weather/today/l/66d93786ffcc98f9cd3bae34e03d05c3d4daa0178c2c4bffe4cfd354cda80400");
        webset.setBuiltInZoomControls(true);

        TabHost.TabSpec tabSpec3 = tabHost.newTabSpec("SONG3").setIndicator("숙박");
        tabSpec3.setContent(R.id.artist3);
        tabHost.addTab(tabSpec3);


        TabHost.TabSpec tabSpec4 = tabHost.newTabSpec("SONG4").setIndicator("맛집");
        tabSpec4.setContent(R.id.artist4);
        tabHost.addTab(tabSpec4);

        tabHost.setCurrentTab(0);

        button = (Button)findViewById(R.id.btn12);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), MadridReview.class);
                startActivity(i);
            }
        });

        adapter5 = new GalleryMadridAdapter(this);

        for(int i = 1; i < 5; i++){
            adapter5.addItem(getResources().getIdentifier("m"+i, "drawable",this.getPackageName()));
        }


        final ImageView iv = (ImageView)findViewById(R.id.img01);
        textView = (TextView)findViewById(R.id.sample);
        Gallery gallery = (Gallery)findViewById(R.id.gallery01);

        final ArrayList<String> arrayList = new ArrayList<>();

        arrayList.add("마요르 광장은 스페인 마드리드에 있는 광장이다. " +
                "푸에르타 델 솔 및 빌라 광장에서 몇 블록 떨어진 거리에 있다. 129m × 94m의 직사각형 모양을 하고 있으며" +
                " 광장에 접해있는 237개의 발코니를 가진 3층 건물에 둘러싸여있다.\n " );

        arrayList.add("마드리드 왕궁은 스페인의 왕실 공식 관저이다." +
                " 사실 마드리드 외곽의 작은 궁전인 사르수엘라 궁에 머물고 있다. " +
                "국가적 상황에만 사용이 되는 것이 원칙이다. 뿐만 아니라 서부 유럽을 통틀어서는 2,800여 개 이상의 방과 135,000 m²의 크기로 단연 최대의 크기를 자랑한다.");
        arrayList.add("레티로 공원은 스페인 마드리드에 위치한 공원이다." +
                "광대한 19세기 공원으로 선착장, 장미 정원, 수많은 분수와 동상이 있습니다");
        arrayList.add("프라도 미술관은 스페인 마드리드에 있는 세계적인 미술관 중 하나이다." +
                " 15세기 이후 스페인 왕실에서 수집한 미술 작품을 전시하고 있다.");

        gallery.setAdapter(adapter5);
        gallery.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> parent, View v, int position, long id){
                Integer galleryItem = adapter5.getGalleryItem(position);
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
