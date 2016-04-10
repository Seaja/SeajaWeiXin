package com.demo.seajia.seajiaweixin.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ListView;

import com.demo.seajia.seajiaweixin.R;
import com.demo.seajia.seajiaweixin.activity.NewsRightActivity;
import com.demo.seajia.seajiaweixin.adapter.NewsAdapter;
import com.demo.seajia.seajiaweixin.bean.News;

import java.util.ArrayList;
import java.util.List;

public class LeftFragment extends Fragment {
    View view;
    List<News> newsList = new ArrayList<News>();
    ListView newsTitleList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_left, container, false);

        newsTitleList = (ListView) view.findViewById(R.id.id_news_title);

        newsList = getNews(newsList);
        NewsAdapter adapter = new NewsAdapter(view.getContext(), newsList);
        newsTitleList.setAdapter(adapter);


        return view;
    }

    private static List<News> getNews(List<News> newsList) {
        News news1 = new News();
        news1.setTitle("入华一个多月 看Apple Pay如何“马”口夺食");
        news1.setContent("http://tech.qq.com/a/20160408/007898.htm");
        news1.setIconID(R.drawable.head);
        newsList.add(news1);

        News news2 = new News();
        news2.setTitle("淘在路上否认C轮融资失败 新一轮融资有望中旬公布");
        news2.setContent("http://tech.qq.com/a/20160407/049237.htm");
        news2.setIconID(R.drawable.head1);
        newsList.add(news2);
        return newsList;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        final Boolean isTwoPane;

        //判断是手机还是平板电脑
        if (getActivity().findViewById(R.id.right_fragment) == null) {//表明没有得到RightFragment,即不是平板电脑。
            isTwoPane = false;
        } else {
            isTwoPane = true;
        }


        newsTitleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {  //选中ListView中的一个新闻的时候
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String contentURL = newsList.get(position).getContent();

                if (!isTwoPane) {  //这时候是手机，需要启动Activity
                    Intent intent = new Intent(view.getContext(), NewsRightActivity.class);
                    intent.putExtra("URL", contentURL);
                    startActivity(intent);

                } else {
                    WebView webView = (WebView) getActivity().findViewById(R.id.right_fragment).findViewById(R.id.webview);
                    webView.getSettings().setJavaScriptEnabled(true);
                    webView.setWebViewClient(new WebViewClient() {
                        @Override
                        public boolean shouldOverrideUrlLoading(WebView view, String url) {

                            view.loadUrl(url);
                            return true;
                        }
                    });

                    webView.loadUrl(contentURL);
                }

            }


        });


    }

}
