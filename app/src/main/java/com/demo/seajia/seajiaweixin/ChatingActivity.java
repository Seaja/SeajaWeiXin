package com.demo.seajia.seajiaweixin;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.demo.seajia.seajiaweixin.bean.Msg;

import java.util.ArrayList;
import java.util.List;

public class ChatingActivity extends AppCompatActivity {
    private EditText chatMsg;
    private ListView listView;
    private List<Msg> msgList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chating);

        initMsg();

        initView();

    }

    private void initMsg() {
        msgList = new ArrayList<Msg>();

        Msg msg1 = new Msg("你好吗？", Msg.TYPE_SEND, R.drawable.head);
        msgList.add(msg1);
        Msg msg2 = new Msg("我不好？", Msg.TYPE_RECEIVED, R.drawable.head1);
        msgList.add(msg2);
        Msg msg3 = new Msg("不会吧！", Msg.TYPE_SEND, R.drawable.head);
        msgList.add(msg3);

    }

    private void initView() {
        chatMsg = (EditText) findViewById(R.id.id_chatMsg);
        listView = (ListView) findViewById(R.id.id_chating_listView);
        listView.setDividerHeight(0);//去掉ListView默认的分割线
        MyAdapter adapter = new MyAdapter(this, msgList);
        listView.setAdapter(adapter);

    }

public void addMore(View view){
    FrameLayout frameLayout= (FrameLayout) findViewById(R.id.id_card_wrap);
    if(frameLayout.getVisibility()==View.GONE){
        frameLayout.setVisibility(View.VISIBLE);
    }else{
        frameLayout.setVisibility(View.GONE);

    }

}
    public void send(View view) {
        String msg = chatMsg.getText().toString();

        if(msg.equals("")||msg==null){
            chatMsg.setError("内容不能为空");
        }else{
            Msg msgObject = new Msg(msg, Msg.TYPE_SEND, 0);
            msgList.add(msgObject);
            MyAdapter adapter = new MyAdapter(this, msgList);
            listView.setAdapter(adapter);

            chatMsg.setText("");
        }

    }

    public class MyAdapter extends BaseAdapter {
        List<Msg> msgList = new ArrayList<Msg>();
        Context context;

        public MyAdapter(Context context, List<Msg> msgList) {
            this.msgList = msgList;
            this.context = context;
        }

        @Override
        public int getCount() {
            return msgList.size();
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
            Msg msgObject = msgList.get(position);


            if (convertView == null) {  //convertView会将之前加载好的布局（inflate加载的布局）进行缓存
                view = LayoutInflater.from(context).inflate(R.layout.chat_item, parent, false);

                viewHolder = new ViewHolder();

                viewHolder.me_chating = (LinearLayout) view.findViewById(R.id.id_me_chating);
                viewHolder.me_msg = (TextView) view.findViewById(R.id.id_me_chating_msg);
                viewHolder.me_headImg = (ImageView) view.findViewById(R.id.id_other_chating_head);


                viewHolder.other_chating = (LinearLayout) view.findViewById(R.id.id_other_chating);
                viewHolder.other_headImg = (ImageView) view.findViewById(R.id.id_other_chating_head);
                viewHolder.other_msg = (TextView) view.findViewById(R.id.id_other_chating_msg);
                view.setTag(viewHolder);//只有convertView是缓存的，现在通过setTag()将ViewHolder（id的选址过程）也缓存下来
            } else {
                view = convertView;
                viewHolder = (ViewHolder) view.getTag();
            }

            switch (msgObject.getType()) {
                case Msg.TYPE_SEND:
                    viewHolder.me_chating.setVisibility(View.VISIBLE);
                    viewHolder.other_chating.setVisibility(View.GONE);

                    viewHolder.me_headImg.setImageResource(msgObject.getHeadImg());
                    viewHolder.me_msg.setText(msgObject.getContent());
                    break;
                case Msg.TYPE_RECEIVED:
                    viewHolder.other_chating.setVisibility(View.VISIBLE);
                    viewHolder.me_chating.setVisibility(View.GONE);


                    viewHolder.other_headImg.setImageResource(msgObject.getHeadImg());
                    viewHolder.other_msg.setText(msgObject.getContent());
                    break;
            }

            return view;
        }
    }

    public class ViewHolder {
        LinearLayout me_chating;
        LinearLayout other_chating;

        TextView me_msg;
        TextView other_msg;

        ImageView me_headImg;
        ImageView other_headImg;

    }


}
