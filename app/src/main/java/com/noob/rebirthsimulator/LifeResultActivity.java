package com.noob.rebirthsimulator;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.noob.rebirthsimulator.AppData.AppDatabase;
import com.noob.rebirthsimulator.AppData.Card;
import com.noob.rebirthsimulator.AppData.Talent;
import com.noob.rebirthsimulator.databinding.ActivityLifeResultBinding;
import com.noob.rebirthsimulator.ui.notifications.NotificationsFragment;

import java.util.Random;

public class LifeResultActivity extends AppCompatActivity {
    //bingding
    private ActivityLifeResultBinding binding;
    //实例化Dao
    CardDao cardDao;
    UserDao userDao;
    UserCardDao userCardDao;
    RebithTextDao rebithTextDao;
    TalentDao talentDao;
    //实例一个转生类的对象
    NotificationsFragment notificationsFragment;
    //当前角色
    String nowcharacter;
    //临终外貌
    int deathAp;
    //临终智力
    int deathIg;
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
        //DAO
        cardDao=AppDatabase.getInstance(this).cardDao();
        talentDao= AppDatabase.getInstance(this).talentDao();
        //获取角色和相关数据
        Intent intent = getIntent();
        //当前角色
        nowcharacter=intent.getStringExtra("nowcharacter");
        //其他属性
        deathAp=intent.getIntExtra("deathAp",deathAp);
        deathIg=intent.getIntExtra("deathIg",deathIg);
        deathPhy=intent.getIntExtra("deathPhy",deathPhy);
        deathUg=intent.getIntExtra("deathUg",deathUg);
        deathage=intent.getIntExtra("deathage",deathage);
        //计算快乐
        happy=deathage*(deathUg+deathPhy)/2;
        //计算总评
        resultvalue=deathage*(deathAp+deathPhy+deathUg+deathIg)/4+happy;
        //设置评价
        String conculusion;
        if (deathage<10)
            conculusion="童年夭折";
        else if (deathage<50)
            conculusion="英年早逝";
        else if (deathage<100)
            conculusion="未尽余生";
        else
            conculusion="长寿者";

        //改变界面
        binding.liferesult.setText(conculusion);
        binding.apview.setText("外貌："+deathAp);
        binding.igview.setText("智力："+deathIg);
        binding.phyview.setText("体质： "+deathPhy);
        binding.ugview.setText("家境： "+deathUg);

        binding.happyview.setText("快乐： "+happy);
        if (nowcharacter.equals("肯尼迪")){
            binding.happyview.setText("快乐： 999999999");
        }

        binding.deathview.setText("享年： "+deathage);

        binding.resultview.setText("总评： "+resultvalue);

        //设置天赋按钮
        int id1=new Random().nextInt(talentDao.getall().size())+1;
        int id2=new Random().nextInt(talentDao.getall().size())+1;
        Talent talent1=talentDao.findbyid(id1);
        Talent talent2=talentDao.findbyid(id2);
        binding.talent1.setText(talent1.TalentContext);
        binding.talent2.setText(talent2.TalentContext);


        //点击操作1
        binding.talent1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //获得天赋对应的角色
                Card card=cardDao.findByName(nowcharacter);
                String property=talent1.TalentProperty;
                //改变的数值
                int value=talent1.modifyValue;
                switch (property){
                    case "Ap":
                        card.cardAp+=value;
                        break;
                    case "Ig":
                        card.cardIg+=value;
                        break;
                    case "Phy":
                        card.cardPhy+=value;
                        break;
                    case "Ug":
                        card.cardUg+=value;
                        break;
                }
                cardDao.updatecard(card);
                //提示文本
                Toast.makeText(LifeResultActivity.this, "天赋加成成功，可进图鉴查看！",Toast.LENGTH_SHORT).show();
                //改变按钮可见
                binding.talent1.setEnabled(false);
                binding.talent2.setVisibility(View.INVISIBLE);
            }
        });
        //点击操作2
        binding.talent2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //获得天赋对应的角色
                Card card=cardDao.findByName(nowcharacter);
                String property=talent2.TalentProperty;
                //改变的数值
                int value=talent2.modifyValue;
                switch (property){
                    case "Ap":
                        card.cardAp+=value;
                        break;
                    case "Ig":
                        card.cardIg+=value;
                        break;
                    case "Phy":
                        card.cardPhy+=value;
                        break;
                    case "Ug":
                        card.cardUg+=value;
                        break;
                }
                cardDao.updatecard(card);
                //提示文本
                Toast.makeText(LifeResultActivity.this, "天赋加成成功，可进图鉴查看！",Toast.LENGTH_SHORT).show();
                //改变按钮可见
                binding.talent2.setEnabled(false);
                binding.talent1.setVisibility(View.INVISIBLE);
            }
        });

        //再次重开按钮
        binding.rebirthagain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LifeResultActivity.this, MainActivity.class));
            }
        });

        //bingding显示界面
        setContentView(binding.getRoot());
    }
}