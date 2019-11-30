package com.example.firetest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class SearchAdapter2 extends BaseAdapter {

    private Context context;
    private List<String> list;
    private LayoutInflater inflate;
    private ViewHolder viewHolder;
    private ImageView imageView;

    public SearchAdapter2(List<String> list, Context context) {
        this.list = list;
        this.context = context;
        this.inflate = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        final int pos = i;
        if(convertView == null){
            convertView = inflate.inflate(R.layout.row_listview,null);

            viewHolder = new ViewHolder();
            viewHolder.label = (TextView) convertView.findViewById(R.id.label);

            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)convertView.getTag();
        }

        // ImageView 인스턴스
        imageView = (ImageView) convertView.findViewById(R.id.image);

        // 리스트뷰의 아이템에 음식점의 이름에 따라서 이미지를 변경하도록 설정 한다. 수정해야할 부분
        if("태국".equals(list.get(i)))
            imageView.setImageResource(R.drawable.thai);
        else if("스페인".equals(list.get(i)))
            imageView.setImageResource(R.drawable.spain);
        else if("베트남".equals(list.get(i)))
            imageView.setImageResource(R.drawable.vietnam);

        // 리스트에 있는 데이터를 리스트뷰 셀에 뿌린다.
        viewHolder.label.setText(list.get(i));

        return convertView;
    }

    class ViewHolder{
        public TextView label;
    }
}
