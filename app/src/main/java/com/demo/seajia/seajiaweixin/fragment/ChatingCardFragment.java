package com.demo.seajia.seajiaweixin.fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;

import com.demo.seajia.seajiaweixin.R;

/**
 * Created by S.F.S on 2016/4/7.
 */
public class ChatingCardFragment extends Fragment {
    private GridView gridView;
    int[] cardIcons = {
            R.drawable.chating_card_images_xml,
            R.drawable.chating_card_sight_xml,
            R.drawable.chating_card_packet_xml,
            R.drawable.chating_card_favorites_xml,
            R.drawable.chating_card_location_xml,
            R.drawable.chating_card_voice_call_xml,
            R.drawable.chating_card_contact_call_xml
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_card, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        gridView = (GridView) view.findViewById(R.id.id_chating_cardGridView);  //碎片得到控件，需要通过view.findViewById来获得

        MyAdapter adapter=new MyAdapter(getActivity().getApplicationContext(),cardIcons);
        gridView.setAdapter(adapter);

    }


    private class MyAdapter extends BaseAdapter {

        int[] cardIcons;
        Context context;

        public MyAdapter(Context context, int[] cardIcons) {
            this.context = context;
            this.cardIcons = cardIcons;
        }

        @Override
        public int getCount() {
            return cardIcons.length;
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

            ImageButton imageButton;
            TextView textView;
            View view;

            view=LayoutInflater.from(context).inflate(R.layout.fragment_card_item, parent, false);
                imageButton = (ImageButton) view.findViewById(R.id.id_card_icon);
            textView = (TextView) view.findViewById(R.id.id_card_text);

            switch(position){
                case 0:
                    textView.setText("图片");
                    imageButton.setBackgroundResource(cardIcons[position]);
                    break;
                case 1:
                    textView.setText("小视频");
                    imageButton.setBackgroundResource(cardIcons[position]);
                    break;
                case 2:
                    textView.setText("红包");
                    imageButton.setBackgroundResource(cardIcons[position]);
                    break;
                case 3:
                    textView.setText("我的收藏");
                    imageButton.setBackgroundResource(cardIcons[position]);
                    break;
                case 4:
                    textView.setText("位置");
                    imageButton.setBackgroundResource(cardIcons[position]);
                    break;
                case 5:
                    textView.setText("语音聊天");
                    imageButton.setBackgroundResource(cardIcons[position]);
                    break;
                case 6:
                    textView.setText("名片");
                    imageButton.setBackgroundResource(cardIcons[position]);
                    break;
            }
            return view;
        }
    }



}
