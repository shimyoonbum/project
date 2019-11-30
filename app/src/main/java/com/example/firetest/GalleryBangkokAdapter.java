package com.example.firetest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class GalleryBangkokAdapter extends BaseAdapter {

    private Context mContext;
    LayoutInflater inflater;

    private List<Integer> galleryList = new ArrayList<Integer>();

    public GalleryBangkokAdapter(Context mContext){
        this.mContext = mContext;
        inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public Integer getGalleryItem(int position){
        return galleryList.get(position);
    }

    //galleryList에 item 추가
    public void addItem(Integer integer){
        galleryList.add(integer);
    }

    public int getCount(){
        return galleryList.size();
    }

    public Object getItem(int position){
        return position;
    }

    public long getItemId(int position){
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        if(convertView == null){
            convertView = inflater.inflate(R.layout.gallery_item, parent, false);
        }

        ImageView imageview = (ImageView)convertView.findViewById(R.id.gimg01);

        TextView textView = (TextView) convertView.findViewById(R.id.text4);

        imageview.setImageResource(galleryList.get(position));


        ArrayList<String> galleryList2 = new ArrayList<>();
        galleryList2.add("카오산 로드");
        galleryList2.add("짜뚜짝 시장");
        galleryList2.add("방콕 왕궁");
        galleryList2.add("왓 아룬 새벽사원");
        galleryList2.add("시암 센터");
        galleryList2.add("아속 터미널21");

        textView.setText(galleryList2.get(position));

        return convertView;
    }
}

