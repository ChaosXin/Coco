package com.example.administrator.recvcleviewdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * @&#x6587;&#x4ef6;&#x540d;:ListAdapter
 * @&#x521b;&#x5efa;&#x8005;:&#x7530;&#x65b0;&#x671d;
 * @&#x521b;&#x5efa;&#x65f6;&#x95f4;: 2016/08/06
 * Created by Administrator on 2016/8/6.
 */
public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListHolder>{


    private Context mContext;
    private List<DataBean>mDatas;

    public ListAdapter(Context context,List<DataBean>datas){
        this.mContext=context;
        this.mDatas=datas;
    }

    @Override
    public ListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //当我们的itemview创建时的回调
        View view = View.inflate(mContext, R.layout.item_list,null);

        return new ListHolder(view);
    }

    @Override
    public void onBindViewHolder(ListHolder holder, int position) {
        //正在加载某个view的时候的回调
        holder.setData(mDatas.get(position));
    }

    @Override
    public int getItemCount() {
        //同listView
        //返回list有多少数据
        if (!(mDatas ==null)){
            return mDatas.size();
        }else{
            return 0;
        }



    }

    public class ListHolder extends RecyclerView.ViewHolder {
        ImageView iv;
        TextView tv;


        public ListHolder(View itemView) {
            super(itemView);

            iv= (ImageView) itemView.findViewById(R.id.item_list_iv);
            tv= (TextView) itemView.findViewById(R.id.item_list_tv);

        }

        public void setData(DataBean bean) {
            iv.setImageResource(bean.icon);
            tv.setText(bean.content);

        }
    }
}
