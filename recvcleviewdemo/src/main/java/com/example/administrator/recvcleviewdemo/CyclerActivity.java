package com.example.administrator.recvcleviewdemo;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;


public class CyclerActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private List<DataBean> mListDatas = new ArrayList<DataBean>();
    private List<DataBean> mStraggerDatas = new ArrayList<DataBean>();

    private int[] mListIcons = new int[]{R.mipmap.g1,
            R.mipmap.g2,
            R.mipmap.g3,
            R.mipmap.g4,
            R.mipmap.g5,
            R.mipmap.g6,
            R.mipmap.g7,
            R.mipmap.g8,
            R.mipmap.g9,
            R.mipmap.g10,
            R.mipmap.g11,
            R.mipmap.g12,
            R.mipmap.g13,
            R.mipmap.g14,
            R.mipmap.g15,
            R.mipmap.g16,
            R.mipmap.g17,
            R.mipmap.g18,
            R.mipmap.g19,
            R.mipmap.g20,
            R.mipmap.g21,
            R.mipmap.g22,
            R.mipmap.g23,
            R.mipmap.g24,
            R.mipmap.g25,
            R.mipmap.g26,
            R.mipmap.g27,
            R.mipmap.g28,
            R.mipmap.g29};

    private int[] mStraggeredIcons = new int[]{R.mipmap.p1,
            R.mipmap.p2,
            R.mipmap.p3,
            R.mipmap.p4,
            R.mipmap.p5,
            R.mipmap.p6,
            R.mipmap.p7,
            R.mipmap.p8,
            R.mipmap.p9,
            R.mipmap.p10,
            R.mipmap.p11,
            R.mipmap.p12,
            R.mipmap.p13,
            R.mipmap.p14,
            R.mipmap.p15,
            R.mipmap.p16,
            R.mipmap.p17,
            R.mipmap.p18,
            R.mipmap.p19,
            R.mipmap.p20,
            R.mipmap.p21,
            R.mipmap.p22,
            R.mipmap.p23,
            R.mipmap.p24,
            R.mipmap.p25,
            R.mipmap.p26,
            R.mipmap.p27,
            R.mipmap.p28,
            R.mipmap.p29,
            R.mipmap.p30,
            R.mipmap.p31,
            R.mipmap.p32,
            R.mipmap.p33,
            R.mipmap.p34,
            R.mipmap.p35,
            R.mipmap.p36,
            R.mipmap.p37,
            R.mipmap.p38,
            R.mipmap.p39,
            R.mipmap.p40,
            R.mipmap.p41,
            R.mipmap.p42,
            R.mipmap.p43,
            R.mipmap.p44};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cycler);
        recyclerView = (RecyclerView) findViewById(R.id.rv);
        swipeRefreshLayout= (SwipeRefreshLayout) findViewById(R.id.sr1);

        swipeRefreshLayout.setOnRefreshListener(this);

        //1 模拟加载数据

        for (int i = 0; i < mListIcons.length; i++) {
            DataBean bean = new DataBean();
            bean.icon = mListIcons[i];
            bean.content = "内容-" + i;
            mListDatas.add(bean);
        }
        for (int i = 0; i < mStraggeredIcons.length; i++) {
            DataBean bean = new DataBean();
            bean.icon = mStraggeredIcons[i];
            bean.content = "内容-" + i;
            mStraggerDatas.add(bean);


        }


        //默认的是list效果

        initList();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_cycler, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.action_list:
                initList();
                return true;
            case R.id.action_grid:
                initGrid();
                return true;
            case R.id.action_stragger:
                initStragger();
                return true;

        }

        return super.onOptionsItemSelected(item);
    }

    private void initStragger() {
        //实现Stragger效果
        //可以垂直滑动，也可以水平滑动，数据方向加载
        //设置布局管理器
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(
                3,
                StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new StraggerdAdapter(
                this,
                mStraggerDatas));
    }

    private void initList() {

        //实现listView的效果
        //可以垂直滑动，也可以水瓶滑动，数据反向加载

        //设置布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        //设置adapter
        recyclerView.setAdapter(new ListAdapter(this, mListDatas));
    }

    private void initGrid() {

        //实现listView的效果
        //可以垂直滑动，也可以水瓶滑动，数据反向加载

        //设置布局管理器
//        GridLayoutManager layoutManager = new GridLayoutManager(this,
//                2, GridLayoutManager.VERTICAL, false);
        GridLayoutManager layoutManager=new GridLayoutManager(
                this,
                2,
                GridLayoutManager.VERTICAL,
                false
        );
        recyclerView.setLayoutManager(layoutManager);


        //设置adapter
        //adapter -->list数据
        recyclerView.setAdapter(new ListAdapter(this, mListDatas));

    }


    @Override
    public void onRefresh() {
        //下拉刷新时的回调
        new AsyncTask<Void, Void, Void>()
        {

            @Override
            protected Void doInBackground(Void... params)
            {
                try
                {
                    Thread.sleep(3000);
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }

                RecyclerView.Adapter adapter = recyclerView.getAdapter();

                if (adapter instanceof ListAdapter)
                {
                    //给list加载数据
                    for (int i = 0; i < mListIcons.length; i++)
                    {
                        DataBean bean = new DataBean();
                        bean.icon = mListIcons[i];
                        bean.content = "内容-" + i;

                        mListDatas.add(bean);

                    }
                } else if (adapter instanceof StraggerdAdapter)
                {
                    //给stragger加载数据
                    for (int i = 0; i < mStraggeredIcons.length; i++)
                    {
                        DataBean bean = new DataBean();
                        bean.icon = mStraggeredIcons[i];
                        bean.content = "内容-" + i;

                        mStraggerDatas.add(bean);
                    }
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid)
            {
                super.onPostExecute(aVoid);

                RecyclerView.Adapter adapter = recyclerView.getAdapter();
                //adatper刷新
                adapter.notifyDataSetChanged();
                //通知刷新的view刷新完成
                swipeRefreshLayout.setRefreshing(false);

            }
        }.execute();
    }

    private boolean isExit;//默认值false
    //private Boolean    对象类型
    //属性初始化回自动赋值
    //点击回退按钮默认执行该方法，目的在有限时间之内点击两次则退出


    //private Boolean isExit;//默认值
    @Override
    public void onBackPressed() {
        //super.onBackPressed();

        if(!isExit){
            Toast.makeText(this,"再点一次退出",Toast.LENGTH_LONG).show();
            isExit=true;
            /*Handler h=new Handler();
            h.postDelayed(new Runnable() {
                @Override
                public void run() {
                    isExit=false;
                }
            },3000);*/

            //handler关联的是主线程的Looper-->3秒以后给主线程发消息
            //搞清楚谁跟谁发消息

            Handler h1=new Handler(){
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    isExit=false;
                }
            };
            h1.sendEmptyMessageDelayed(1,2000);
        }else{
            // finish();
            super.onBackPressed();
        }
    }
    //doClick在主线程中调用就在主线程运行
    public void yiBuRenWu(){

        //启动异步任务
        //构建异步任务对象，并启动线程执行任务
        new DownTask().execute("a.jpg","b.jpg");//execute方法也是在主线程中运行的
        //execute方法的作用是执行任务
    }
    class DownTask extends AsyncTask<String,Integer,Object>{

        /**
         * 此方法运行与主线程，一般用于初始化，比如初始化控件*/
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.i("TAG","onPreExecute");
        }

        /**此方法运行在工作线程,
         * 当执行对象的execute方法是会自动执行此方法*/
        @Override
        protected Object doInBackground(String... params) {
            String name=Thread.currentThread().getName();
            Log.i("TAG","doInBackground");
            String f01=params[0];//a.jpg
            String f02=params[1];//b.jpg

            //1、根据名字下载文件
            //主线程是不会阻塞的
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //2、文件下载到本地之后需要做些什么？
            //2、将图片写到本地
            //3、返回本地图片的Uri（统一资源标识）
            File picDir=Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);






            return Uri.fromFile(new File(picDir,f01));

        }//此方法一定要重写，在父类中是抽象方法。

        //此方法运行结果如何交给主线程



        /**此方法运行在主线程，用于处理
         * doInbackground方法的执行结果
         * 如果不需要处理结果就不需要重写该结果
         *
         * 参数result为DoInBackGround返回结果
         * */
        @Override
        protected void onPostExecute(Object o) {

            Log.i("TAG","onPostExecute");
//            ImageView imageView = null;
//            imageView.setImageURI((URI)o);

            super.onPostExecute(o);
        }
    }

}
