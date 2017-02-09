package com.example.bin.uichat;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Msg> msgList = new ArrayList<>();
    private EditText inputText;
    private Button send;
    private RecyclerView msgRecyclerView;
    private MsgAdapter adapter;
    private boolean type;
    private Msg msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initMsgs();
        initView();
    }

    private void initMsgs() {
        Msg msg1 = new Msg("Hello bin.", Msg.TYPE_RECEIVED);
        Msg msg2 = new Msg("ni hao ,qing wen ni shi shui?", Msg.TYPE_SENT);
        Msg msg3 = new Msg("我是隔壁老王啦！", Msg.TYPE_RECEIVED);
        msgList.add(msg1);
        msgList.add(msg2);
        msgList.add(msg3);
    }

    private void initView() {
        inputText = (EditText) findViewById(R.id.input_text);
        send = (Button) findViewById(R.id.send);
        msgRecyclerView = (RecyclerView) findViewById(R.id.msg_recycler_view);
        msgRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        adapter = new MsgAdapter(msgList);
        msgRecyclerView.setAdapter(adapter);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = inputText.getText().toString();
                if (!content.isEmpty()) {
                    if (!type){
                        type=true;
                        msg = new Msg(content, Msg.TYPE_SENT);
                    }else {
                        type=false;
                        msg = new Msg(content, Msg.TYPE_RECEIVED);
                    }
                    msgList.add(msg);
                    //刷新适配器
                    adapter.notifyItemInserted(msgList.size() - 1);
                    //滑动定位到最后一行
                    msgRecyclerView.scrollToPosition(msgList.size() - 1);
                    inputText.setText("");
                }
            }
        });
    }
}
