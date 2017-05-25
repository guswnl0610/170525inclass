package com.example.guswn_000.a170525inclass;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Main3Activity extends AppCompatActivity
{
    EditText e1,e2;
    Handler mainhandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            String name2 = (String)msg.obj;
            e2.setText(name2);//이제 끗!
        }
    };//메인핸들러


    @Override
    protected void onDestroy() {
        super.onDestroy();
        subThread.subHandler.getLooper().quit();
    }//이러면 루퍼도 종료

    myThread subThread = new myThread();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        e1 = (EditText)findViewById(R.id.edittext1);
        e2 = (EditText)findViewById(R.id.edittext2);
        subThread.start();
    }

    public void onClick(View v)
    {
        String name = e1.getText().toString();
        //메시지만들기
        Message msg = Message.obtain();
        msg.obj = name;
        //메시지 보내기
        subThread.subHandler.sendMessage(msg); //이제 subhandler의 핸들메시지로 ㄱㄱ


    }


    class myThread extends Thread
    {
        //통신하려니까 handler를 만들어준다 속에다넣으면 안이쁘니까 밖에다 만들었음
        SubHandler subHandler = new SubHandler();
        @Override
        public void run()
        {
            Looper.prepare();//루퍼생성
            Looper.loop();//끌떄 루퍼도 반드시 종료시켜야함


        }
    }

    class SubHandler extends Handler
    {
        @Override
        public void handleMessage(Message msg)
        {
            String name = (String)msg.obj;
            name = "Hello " + name;

            //이제 메시지 만들어서 메인핸들러로 보냄
            Message msg2 = Message.obtain();
            msg2.obj = name;
            mainhandler.sendMessage(msg2);//메인핸들러로 ㄱㄱ
        }
    }
}
