package com.example.administrator.recvcleviewdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Administrator on 2016/8/8.
 */
public class StraggerdAdapter extends RecyclerView.Adapter<StraggerdAdapter.StraggerdHolder> {

    private Context mContext;
    private List<DataBean> mDatas;

    public StraggerdAdapter(Context mContext, List<DataBean> mDatas) {
        this.mContext = mContext;
        this.mDatas = mDatas;
    }


    @Override
    public StraggerdAdapter.StraggerdHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, R.layout.item_straggered, null);
        return new StraggerdHolder(view);
    }

    @Override
    public void onBindViewHolder(StraggerdHolder holder, int position) {
        holder.setData(mDatas.get(position));


    }

    @Override
    public int getItemCount() {
        if (mDatas != null) {
            return mDatas.size();
        } else {

            return 0;
        }
    }

    public class StraggerdHolder extends RecyclerView.ViewHolder {
        ImageView iv;
        TextView tv;

        public StraggerdHolder(View itemView) {
            super(itemView);
            iv = (ImageView) itemView.findViewById(R.id.item_list_iv);
            tv = (TextView) itemView.findViewById(R.id.item_list_tv);

        }

        public void setData(DataBean dataBean) {
            iv.setImageResource(dataBean.icon);
            tv.setText(dataBean.content);

        }

    }
}
