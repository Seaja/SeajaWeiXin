package com.demo.seajia.seajiaweixin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.demo.seajia.seajiaweixin.R;
import com.demo.seajia.seajiaweixin.bean.News;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by S.F.S on 2016/4/8.
 */
public class NewsAdapter extends BaseAdapter {
    Context context;
    List<News> newsList = new ArrayList<News>();

    public NewsAdapter(Context context, List<News> newsList) {
        this.context = context;
        this.newsList = newsList;
    }

    @Override
    public int getCount() {
        return newsList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.adapter_news_title, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.textView = (TextView) view.findViewById(R.id.id_news_title_text);
            viewHolder.imageView = (ImageView) view.findViewById(R.id.id_news_title_icon);

            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();

        }


        viewHolder.textView.setText(newsList.get(position).getTitle());
        viewHolder.imageView.setImageResource(newsList.get(position).getIconID());
        return view;
    }

    private class ViewHolder {
        TextView textView;
        ImageView imageView;
    }
}
