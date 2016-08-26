
package com.example.administrator.yibu;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class YiBuActivity extends AppCompatActivity {

    private Button btn;

    private ImageView imv;
    private String ip="192.168.249.1";
    private int port=8888;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yibu);
        btn=(Button)findViewById(R.id.button);
        imv= (ImageView) findViewById(R.id.imageView);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("Tag","fasong");
                new MsgTask().execute("nihao","nizhendehaoma");

            }

        });
    }

    class MsgTask extends AsyncTask<String,Void,Integer>{


        @Override
        protected Integer doInBackground(String... strings) {
            Log.i("TAG", "MsgTask");
            DataOutputStream dos=null;
            Socket socket=null;
            //建立socket连接
            Log.i("Tag","kaishijianlilianjie");
            try {
                socket=new Socket(ip,port);
                Log.i("Tag","jianlichenggong");
                //获得输出流
                dos=new DataOutputStream(
                        socket.getOutputStream()
                );
                //写数据
                dos.writeUTF(strings[0] + "/" + strings[1]);
                dos.flush();
                return 200;

            } catch (IOException e) {
                e.printStackTrace();
                return 500;

            }finally {
                //释放资源
                if(dos!=null){
                    try {
                        dos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if(socket!=null){
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        @Override
        protected void onPostExecute(Integer integer) {
            if(integer==200){
                showMsg("写入OK");

            }else{
                showMsg("写入失败");
            }
        }
    }
    private void showMsg(String text){
        Toast.makeText(YiBuActivity.this,text,Toast.LENGTH_SHORT).show();
    }

}
