package com.noob.rebirthsimulator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.noob.rebirthsimulator.R;
import com.noob.rebirthsimulator.databinding.ActivityLifeResultBinding;
import com.noob.rebirthsimulator.databinding.FragmentNotificationsBinding;
import com.noob.rebirthsimulator.ui.notifications.NotificationsFragment;

public class LifeResultActivity extends AppCompatActivity {
    //bingding
    private ActivityLifeResultBinding binding;
    //实例化Dao
    CardDao cardDao;
    UserDao userDao;
    UserCardDao userCardDao;
    RebithTextDao rebithTextDao;
    //实例一个转生类的对象
    NotificationsFragment notificationsFragment;
    //当前角色
    String nowcharacter;
    //临终外貌
    int deathAp;
    //临终智力
    int deatIg;
    //临终体力
    int deathPhy;
    //临终家境
    int deathUg;
    //快乐值
    int happy;
    //死亡年龄
    int deathage;
    //总评
    int resultvalue;

    //creat生命周期
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //实例bingding
        binding = ActivityLifeResultBinding.inflate(getLayoutInflater());
        //获取角色和相关数据
        Intent intent = getIntent();

        deathAp=intent.getIntExtra("deathAp",deathAp);
        deathUg=intent.getIntExtra("deathUg",deathUg);
        deathage=intent.getIntExtra("deathage",deathage);
        //改变界面
        binding.apview.setText("外貌："+deathAp);
        binding.igview.setText("智力："+deatIg);
        binding.deathview.setText("享年："+deathage);

        //再次重开按钮
        binding.rebirthagain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LifeResultActivity.this, Notification.class));
            }
        });

        //bingding显示界面
        setContentView(binding.getRoot());
    }
}