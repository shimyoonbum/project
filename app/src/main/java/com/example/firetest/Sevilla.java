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
public class Sevilla extends TabActivity {
    GallerySevillaAdapter adapter;
    TextView textView;
    WebView web;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sevilla);

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

        button = (Button)findViewById(R.id.btn13);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), SevillaReview.class);
                startActivity(i);
            }
        });

        tabHost.setCurrentTab(0);

        adapter = new GallerySevillaAdapter(this);

        for(int i = 1; i < 5; i++){
            adapter.addItem(getResources().getIdentifier("s"+i, "drawable",this.getPackageName()));
        }


        final ImageView iv = (ImageView)findViewById(R.id.img01);
        textView = (TextView)findViewById(R.id.sample);
        Gallery gallery = (Gallery)findViewById(R.id.gallery01);

        final ArrayList<String> arrayList = new ArrayList<>();

        arrayList.add("1929년에 열린 에스파냐·아메리카 박람회장으로 건축가 아니발 곤살레스(Aníbal González)가 만들었다. " +
                " 반달 모양의 광장을 둘러싼 건물 양쪽에 탑이 있고, " +
                "건물 앞에는 강이 흐른다. 광장 쪽 건물 벽면에는 에스파냐 각지의 " +
                " 역사적 사건들이 타일 모자이크로 묘사되어 있다. " +
                "역사적 사건들이 타일 모자이크로 묘사되어 있다. 조지 루카스의 영화 《스타워즈 에피소드 2 클론의 습격》의 배경이 되기도 했다. \n " );

        arrayList.add("태국 최대의 재래 시장인 짜뚜짝 시장에는 식품, 의류, 생활 용품 등 각 종류별로 " +
                "없는게 없는 곳이다. 상점이 약 15000여개가 따닥 붙어 재래 시장을 이루었으며, 물품의 가격" +
                "역시 굉장히 저렴한 편으로 많은 외국인들이 기념품을 사러 방문하는 곳 중 하나이다.");
        arrayList.add("역대 국왕들이 살던 궁전인 방콕의 왕궁으로 약 71만평의 달하는 규모로 현재 많은 외국인들이" +
                "구경을 하기 위해 왕궁으로 방문한다.");
        arrayList.add("방콕의 아침을 밝히는 새벽사원으로도 유명한 왓 아룬은 태국 10바트 짜리에 새겨져 있는 탑으로" +
                "태국 석탑 기술의 완성도를 보여주며 경사가 있기는 하지만 기념 사진 촬영이 허락되어 관광객들에게 인기가" +
                "많은 사원이다. 새벽 뿐만 아니라 밤에 와도 화려한 조명이 관광객들의 이목을 집중하게 만든다.");

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
