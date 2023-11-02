package com.goudong.myapp;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;

import com.goudong.myapp.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    // Used to load the 'myapp' library on application startup.
    static {
        System.loadLibrary("myapp");
    }


    private ActivityMainBinding binding;
    private NotificationManager manager_notice;
    private Notification notification;
    private EditText ed1;
    private EditText ed2;
    private ProgressBar pb;
    private List<Bean> data = new ArrayList<>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

/*
        ///////////////////////载入初始数据给Bean那边，用于ListView
        for(int i=0;i<100;i++){
            Bean bean = new Bean();
            bean.setName("ouyang"+i);
            data.add(bean);
        }
        RecyclerView recyclerView = findViewById(R.id.rv);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        MyAdapter myAdapter = new MyAdapter(data,this);
        recyclerView.setAdapter(myAdapter);

        myAdapter.setRecyclerItemClickListener(new MyAdapter.OnRecyclerItemClickListener() {
            @Override
            public void OnRecyclerItemClick(int position) {
                Log.e("ouyang", "OnRecyclerItemClick: "+ position);
            }
        });

 */



        ///////////////////////////////////////////////////////////////////////////////////////
        //获取通知管理器的系统级权限!!!
        manager_notice = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);

        //当版本数符合条件的时候，创建channel类中对象，设置重要程度，并赋值给管理器对象
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel notification_channel = new NotificationChannel("OYANG",
                    "测试通知", NotificationManager.IMPORTANCE_HIGH);
            manager_notice.createNotificationChannel(notification_channel);
        }

        //根据权限新建一个通知类的通知对象。[链式结构书写，直接.加函数]
        notification = new NotificationCompat.Builder(this,"OYANG")
                .setSmallIcon(R.drawable.boerbute)
                .setPriority(2)
                .setContentTitle("安全系统警告")
                .setContentText("请注意，已检测到高危风险程序段。请谨慎选择您的行为")
                .setAutoCancel(true)
                .build();

        /////////////////////////////////////////////////////////////////////////////////////////





        // Example of a call to a native method

        Button button1 = findViewById(R.id.btn_enter);
        Button button_dielog = findViewById(R.id.bnt_alertdielog);
        ed1 = findViewById(R.id.ed1);
        ed2 = findViewById(R.id.ed2);
        pb = findViewById(R.id.pb1);
        ImageView iv_top = findViewById(R.id.iv_top);

        Toolbar tb1 = findViewById(R.id.tb1);
        //setSupportActionBar(tb1);

        iv_top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("ouyang", "onClick:rotate");
                Animation animation = AnimationUtils.loadAnimation(MainActivity.this,
                        R.anim.rotate);
                iv_top.startAnimation(animation);
            }
        });

        //主页面退出按键
        tb1.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("ouyang", "onClick: 主页面退出键被点击");
                //popupview.isDirty();
            }
        });


        //点击事件
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text1 = ed1.getText().toString();
                String text2 = ed2.getText().toString();
                Log.e("ouyang", "onClick: 用户名："+text1);
                Log.e("ouyang", "onClick: 密码:"+text2);
                pb.setVisibility(View.VISIBLE);

            }
        });





    }






/////////////////////////////////////外置的解决方法

    //触发对话框
    public void onClick_dialog(View view) {

        View dialogview = getLayoutInflater().inflate(R.layout.dialog_view, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.mipmap.ic_launcher)
                .setTitle("登陆失败")
                .setMessage("您的用户名或密码错误，请重试！")

                //使用自定义布局
                .setView(dialogview)

                //确定按钮
                .setPositiveButton("遥遥领先！", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.e("ouyang", "onClick: 通知栏点击了确定");
                        SendNotification(view);
                    }
                })

                //取消按钮
                .setNegativeButton("iphone手机，重新输入", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.e("ouyang", "onClick: 通知栏点击了取消");
                    }
                })

                //中间按钮
                .setNeutralButton("无视风险，强行登陆", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.e("ouyang", "onClick: 通知栏点击了中间");
                        View view1 = getLayoutInflater().inflate(R.layout.dialog_view,null);
                        popupwindow1(view1);
                    }
                })


                .create()
                .show();
    }

    //弹窗设置
    public void popupwindow1(View view){
        View popupview = getLayoutInflater().inflate(R.layout.popupwindow_view, null);
        PopupWindow popupWindow = new PopupWindow(popupview, ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,true);
        Log.e("ouyang", "popupwindow1: 弹窗弹出");
        popupWindow.showAsDropDown(view,120,60,30);
    }


    public void SendNotification(View view) {
        manager_notice.notify(1,notification);
    }











    /**
     * A native method that is implemented by the 'myapp' native library,
     * which is packaged with this application.
     */



    public native String stringFromJNI();

}