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

public class GalleryDanangAdapter extends BaseAdapter {

    private Context mContext;
    LayoutInflater inflater;

    private List<Integer> galleryList = new ArrayList<Integer>();

    public GalleryDanangAdapter(Context mContext){
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


        ArrayList<String> galleryList = new ArrayList<>();
        galleryList.add("용 다리");
        galleryList.add("한 시장");
        galleryList.add("바나 힐");
        galleryList.add("마케 비치");
        galleryList.add("다낭 대성당");

        textView.setText(galleryList.get(position));

        return convertView;
    }
}

