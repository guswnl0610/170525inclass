package com.example.guswn_000.a170525inclass;

import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public abstract class Main4 extends AppCompatActivity {
    TextView tv;
    ProgressBar pb;
    myTask task1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task);
        tv = (TextView) findViewById(R.id.textView);
        pb = (ProgressBar) findViewById(R.id.progress);

    }


    public void onClick(View v)
    {
        if(v.getId() == R.id.btn1)
        {
            task1 = new myTask();
            task1.execute(0);

        }
        else
        {
            task1.cancel(true);
//            task1 = null;
        }
    }

    //1.초기화 , 얘는 ui 직접 건들 영역

    class myTask extends AsyncTask<Integer, Integer, Void> {


        @Override
        protected Void doInBackground(Integer... params) {
            for (int i = 1; i < 101; i++) {
                try {
                    Thread.sleep(200);
                    //0.2초씩 업데이트, 인자 여러개 가능 publish 불렀으니까 3으로
                    publishProgress(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            return null;
        }

        //1.초기화 , 얘는 ui 직접 건들 영역
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pb.setProgress(0);
            tv.setTextColor(Color.RED);
            tv.setText("진행률 : 0 %");
        }

        //3.
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            pb.setProgress(values[0]);
            tv.setText("진행률 : " + values[0] + "%");
        }

        //4.

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            pb.setProgress(100);
            tv.setText("완료되었습니다.");
            tv.setTextColor(Color.BLUE);

        }


    }
}
