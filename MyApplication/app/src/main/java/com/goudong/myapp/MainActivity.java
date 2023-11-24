package com.goudong.myapp;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
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
    private  Button bnt_CenterImage;
    private List<Bean> data = new ArrayList<>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //1、所有控件变量。

        Button button1 = findViewById(R.id.btn_enter);
        Button button_dielog = findViewById(R.id.bnt_alertdielog);
        bnt_CenterImage = findViewById(R.id.btn_image);
        ed1 = findViewById(R.id.ed1);
        ed2 = findViewById(R.id.ed2);
        pb = findViewById(R.id.pb1);
        ImageView iv_top = findViewById(R.id.iv_top);
        Toolbar tb1 = findViewById(R.id.tb1);


        //2、ListView启动所需的数据填充
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



        //3、系统级通知栏的发通知权限设置

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
                .setSmallIcon(R.drawable.baseline_fingerprint_24)
                .setPriority(2)
                .setContentTitle("安全系统警告")
                .setContentText("请注意，已检测到高危风险程序段。请谨慎选择您的行为")
                .setAutoCancel(true)
                .build();





        //4、启动主页面顶部状态栏（之前通过处理已被禁掉）

        //setSupportActionBar(tb1);


        //5、主页面右上角表情包旋转

        iv_top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("ouyang", "onClick:rotate");
                Animation animation = AnimationUtils.loadAnimation(MainActivity.this,
                        R.anim.rotate);
                iv_top.startAnimation(animation);
            }
        });

        //6、主页面退出按键点击事件

        tb1.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("ouyang", "onClick: 主页面退出键被点击");
            }
        });


        //7、主页面下方登陆按钮点击事件

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

        //8、ObjectAnimator对象动画处理

        ObjectAnimator objectAnimator1 = ObjectAnimator.ofFloat(ed1,"alpha",0.5f,1f);
        ObjectAnimator objectAnimator2 = ObjectAnimator.ofFloat(ed2,"alpha",0.5f,1f);

        objectAnimator1.setDuration(1000);
        objectAnimator2.setDuration(1000);

        objectAnimator1.setRepeatMode(ValueAnimator.REVERSE);//从尾开始
        objectAnimator1.setRepeatCount(ValueAnimator.INFINITE);//无限循环
        objectAnimator2.setRepeatMode(ValueAnimator.REVERSE);//从尾开始
        objectAnimator2.setRepeatCount(ValueAnimator.INFINITE);//无限循环

        objectAnimator1.start();
        objectAnimator2.start();

        //9、根据8获得的ObjectAnimator类成员设置监听器。

        objectAnimator1.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationCancel(Animator animation) {
                super.onAnimationCancel(animation);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                super.onAnimationRepeat(animation);
            }

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
            }

            @Override
            public void onAnimationPause(Animator animation) {
                super.onAnimationPause(animation);
            }

            @Override
            public void onAnimationResume(Animator animation) {
                super.onAnimationResume(animation);
            }
        });

        //10、设置中央窗口浮动
        
        floatAnim(bnt_CenterImage,1);

        //11、


    }


/////////////////////////////////////外置的解决方法

    //1、主页面最下方“强行登陆”按钮点击事件：触发对话框
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

    //2、点击”强行登陆“之后再次点击“无视风险”按钮后弹窗设置
    public void popupwindow1(View view){
        View popupview = getLayoutInflater().inflate(R.layout.popupwindow_view, null);
        PopupWindow popupWindow = new PopupWindow(popupview, ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,true);
        Log.e("ouyang", "popupwindow1: 弹窗弹出");
        popupWindow.showAsDropDown(view,120,60,30);
    }

    //3、点击“强行登陆”之后点击“遥遥领先”发送系统级别通知栏信息
    public void SendNotification(View view) {
        manager_notice.notify(1,notification);
    }

    //4、实现永动浮动动画
    private void floatAnim(View view,int delay){
        List animators = new ArrayList<>();

        ObjectAnimator translationXAnim = ObjectAnimator.ofFloat(bnt_CenterImage, "translationX", -12.0f,12.0f,-12.0f);

        translationXAnim.setDuration(1500);

        translationXAnim.setRepeatMode(ValueAnimator.RESTART);

        translationXAnim.setRepeatCount(ValueAnimator.INFINITE);//无限循环

        translationXAnim.start();

        animators.add(translationXAnim);

        ObjectAnimator translationYAnim = ObjectAnimator.ofFloat(bnt_CenterImage, "translationY", -6.0f,6.0f,-6.0f);

        translationYAnim.setDuration(1000);

        translationYAnim.setRepeatCount(ValueAnimator.INFINITE);

        translationYAnim.setRepeatMode(ValueAnimator.RESTART);

        translationYAnim.start();

        animators.add(translationYAnim);

        AnimatorSet btnSexAnimatorSet = new AnimatorSet();

        btnSexAnimatorSet.playTogether(animators);

        btnSexAnimatorSet.setStartDelay(delay);

        btnSexAnimatorSet.start();

    }









    /**
     * A native method that is implemented by the 'myapp' native library,
     * which is packaged with this application.
     */



    public native String stringFromJNI();

}