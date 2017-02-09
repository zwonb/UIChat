package com.example.bin.uichat;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by bin on 2017/2/8.
 */

public class MsgAdapter extends RecyclerView.Adapter<MsgAdapter.ViewHolder> {

    private List<Msg> mMsgList;

    public MsgAdapter(List<Msg> mMsgList) {
        this.mMsgList = mMsgList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.msg_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Msg msg = mMsgList.get(position);
        if (msg.getType() == Msg.TYPE_RECEIVED) {
            //收到消息
            holder.leftLayout.setVisibility(View.VISIBLE);
            holder.leftMsg.setText(msg.getContent());
            holder.rightLayout.setVisibility(View.GONE);
        } else if (msg.getType() == Msg.TYPE_SENT) {
            //发送消息
            holder.rightLayout.setVisibility(View.VISIBLE);
            holder.rightMsg.setText(msg.getContent());
            holder.leftLayout.setVisibility(View.GONE);
        }

        holder.leftLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                int i = holder.getLayoutPosition(); //获取点击是哪个item，不能放在点击外面
//                mMsgOnItemLongClick.onItemLongClick(i);
                removeItem(i);
                return false;
            }
        });
        holder.rightLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                int i = holder.getLayoutPosition();
//                mMsgOnItemLongClick.onItemLongClick(i);
                removeItem(i);
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mMsgList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private LinearLayout leftLayout;
        private LinearLayout rightLayout;
        private TextView leftMsg;
        private TextView rightMsg;

        ViewHolder(View itemView) {
            super(itemView);
            leftLayout = (LinearLayout) itemView.findViewById(R.id.left_layout);
            rightLayout = (LinearLayout) itemView.findViewById(R.id.right_layout);
            leftMsg = (TextView) itemView.findViewById(R.id.left_msg);
            rightMsg = (TextView) itemView.findViewById(R.id.right_msg);
        }
    }

//    private MsgOnItemLongClick mMsgOnItemLongClick;

//    public void setmMsgOnItemLongClick(MsgOnItemLongClick MsgOnItemLongClick) {
//        this.mMsgOnItemLongClick = MsgOnItemLongClick;
//    }

    //长按接口
//    interface MsgOnItemLongClick {
//        void onItemLongClick(int position);
//    }

    //删除单个item
    public void removeItem(int i) {
        mMsgList.remove(i);
        notifyItemRemoved(i);
    }
}
