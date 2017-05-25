package com.example.guswn_000.a170525inclass;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView tv;

    int index = 0;
    Handler mhandler = new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            tv.setText("숫자 : " + index);

        }
    }; // 메인에서 사용할 핸들러
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView)findViewById(R.id.text1);

    }


    class MyThread extends Thread
    {
        //만약여기서핸들러또만들면 그핸들러는 얘꺼다(따로따로 핸들러 가짐)

        @Override
        public void run() {
            super.run();
            for(index = 1 ; index < 11 ; index++)
            {
                try {
                    Thread.sleep(1000);
//                    tv.setText("숫자 : " + index); // 얘는 이런식으로 ui에 직접 접근 못한다.
//                    handler.post(new Runnable() {
//                        @Override
//                        public void run()
//                        {
//                            tv.setText("숫자 : " + index);
//
//                        }
//                    });//Runnable 객체를 메시지큐에 보냄




                    Message msg = mhandler.obtainMessage();//핸들러에있는메시지를가져와서 생성
                    //메세지에 정보를 넣어야된다
                    mhandler.sendMessage(msg); //empty메세지하면 key를 이용해서 호출함

                    //메시지 객체를 핸들러에 보내는 방법


                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    public void onClick(View v)
    {
        MyThread myThread = new MyThread();
        myThread.start();
    }

}
