package com.noob.rebirthsimulator;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.noob.rebirthsimulator.AppData.AppDatabase;
import com.noob.rebirthsimulator.AppData.Card;
import com.noob.rebirthsimulator.AppData.RebirthText;
import com.noob.rebirthsimulator.AppData.Talent;
import com.noob.rebirthsimulator.AppData.User;
import com.noob.rebirthsimulator.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
//DAO
    CardDao cardDao;
    UserDao userDao;
    RebithTextDao rebithTextDao;
    TalentDao talentDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        com.noob.rebirthsimulator.databinding.ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        //Dao实例化
        cardDao = AppDatabase.getInstance(this).cardDao();
        userDao = AppDatabase.getInstance(this).userDao();
        rebithTextDao = AppDatabase.getInstance(this).rebithTextDao();
        talentDao = AppDatabase.getInstance(this).talentDao();

        //清空卡牌表
        //cardDao.delete(cardDao.getAll());
        //数据库判空，并插入卡牌数据
        if (cardDao.getAll().isEmpty()) {
            Card topcard = new Card();
            topcard.cardname = "卡密";
            topcard.cardStar = 5;
            topcard.cardIg = 100;
            topcard.cardAp = 100;
            topcard.cardPhy = 100;
            topcard.cardUg = 100;
            topcard.cardvalue = 100;
            topcard.cardbackground = "氪金入手的外挂角色，全属性均为最高";
            cardDao.insertAll(topcard);


            Card card1 = new Card();
            card1.cardname = "小岛秀夫";
            card1.cardStar = 4;
            card1.cardIg = 90;
            card1.cardAp = 70;
            card1.cardPhy = 70;
            card1.cardUg = 90;
            card1.cardvalue = 80;
            card1.cardbackground = "你是一个知名游戏制作人，在全球享有盛誉，拥有大量的财富和顶级头脑";
            cardDao.insertAll(card1);

            Card card2 = new Card();
            card2.cardname = "曹操";
            card2.cardStar = 3;
            card2.cardIg = 80;
            card2.cardAp = 60;
            card2.cardPhy = 50;
            card2.cardUg = 50;
            card2.cardvalue = 50;
            card2.cardbackground = "你是中国历史上著名的人物，深谋远虑并且居安思危";
            cardDao.insertAll(card2);

            Card card2_1 = new Card();
            card2_1.cardname = "薛定谔";
            card2_1.cardStar = 3;
            card2_1.cardIg = 90;
            card2_1.cardAp = 80;
            card2_1.cardPhy = 50;
            card2_1.cardUg = 40;
            card2_1.cardvalue = 70;
            card2_1.cardbackground = "你是历史上有名科学家，和猫有不解之缘";
            cardDao.insertAll(card2_1);

            Card card3 = new Card();
            card3.cardname = "打工人";
            card3.cardStar = 2;
            card3.cardIg = 50;
            card3.cardAp = 40;
            card3.cardPhy = 40;
            card3.cardUg = 30;
            card3.cardvalue = 20;
            card3.cardbackground = "你是现实世界中的一个普通人，但也是社会中不可缺少的一份子";
            cardDao.insertAll(card3);

            Card card3_1 = new Card();
            card3_1.cardname = "明就仁波切";
            card3_1.cardStar = 2;
            card3_1.cardIg = 50;
            card3_1.cardAp = 40;
            card3_1.cardPhy = 10;
            card3_1.cardUg = 80;
            card3_1.cardvalue = 40;
            card3_1.cardbackground = "你是一个禅学大师";
            cardDao.insertAll(card3_1);

            Card card4 = new Card();
            card4.cardname = "石头";
            card4.cardStar = 1;
            card4.cardIg = 0;
            card4.cardAp = 0;
            card4.cardPhy = 100;
            card4.cardUg = 0;
            card4.cardvalue = 10;
            card4.cardbackground = "你是一块无用的石头";
            cardDao.insertAll(card4);

        }

        //数据库判空，并插入转生文本数据
        if (rebithTextDao.getAll().isEmpty()) {
            RebirthText rebithText1 = new RebirthText();
            rebithText1.age = 1;
            rebithText1.context = "1岁，你出生了，是个男孩";
            rebithText1.branchtext1 = "";
            rebithText1.branchtext2 = "";
            rebithText1.NeddPhy = 0;
            rebithText1.NeddUg = 0;
            rebithText1.NeddAp = 0;
            rebithText1.NeddIg = 0;
            rebithTextDao.insertAll(rebithText1);

            RebirthText rebithText11 = new RebirthText();
            rebithText11.age = 1;
            rebithText11.context = "1岁，你出生了，是个女孩";
            rebithText11.branchtext1 = "";
            rebithText11.branchtext2 = "";
            rebithText11.NeddPhy = 0;
            rebithText11.NeddUg = 0;
            rebithText11.NeddAp = 0;
            rebithText11.NeddIg = 0;
            rebithTextDao.insertAll(rebithText11);

            RebirthText rebithText2 = new RebirthText();
            rebithText2.age = 2;
            rebithText2.context = "2岁，你从小生活在农村";
            rebithText2.branchtext1 = "";
            rebithText2.branchtext2 = "";
            rebithText2.NeddPhy = 0;
            rebithText2.NeddUg = 0;
            rebithText2.NeddAp = 0;
            rebithText2.NeddIg = 0;
            rebithTextDao.insertAll(rebithText2);

            RebirthText rebithText21 = new RebirthText();
            rebithText21.age = 2;
            rebithText21.context = "2岁，你在大城市中生活，父母忙于工作，由爷爷奶奶照顾你";
            rebithText21.branchtext1 = "";
            rebithText21.branchtext2 = "";
            rebithText21.NeddPhy = 0;
            rebithText21.NeddUg = 50;
            rebithText21.NeddAp = 0;
            rebithText21.NeddIg = 0;
            rebithTextDao.insertAll(rebithText21);

            RebirthText rebithText3 = new RebirthText();
            rebithText3.age = 3;
            rebithText3.context = "3岁，你的父母对你呵护尤佳";
            rebithText3.branchtext1 = "外出冒险";
            rebithText3.branchtext2 = "宅家";
            rebithText3.NeddPhy = 0;
            rebithText3.NeddUg = 0;
            rebithText3.NeddAp = 0;
            rebithText3.NeddIg = 0;
            rebithTextDao.insertAll(rebithText3);

            RebirthText rebithText4_1 = new RebirthText();
            rebithText4_1.age = 4;
            rebithText4_1.context = "4岁，有天打雷了，劈断了你家的一棵树,你在树下不幸被劈中，你死了";
            rebithText4_1.branchtext1 = "";
            rebithText4_1.branchtext2 = "";
            rebithText4_1.NeddPhy = 10;
            rebithText4_1.NeddUg = 50;
            rebithText4_1.NeddAp = 0;
            rebithText4_1.NeddIg = 0;
            rebithTextDao.insertAll(rebithText4_1);

            RebirthText rebithText4_2 = new RebirthText();
            rebithText4_2.age = 4;
            rebithText4_2.context = "4岁，你每天都要听睡前故事";
            rebithText4_2.branchtext1 = "";
            rebithText4_2.branchtext2 = "";
            rebithText4_2.NeddPhy = 0;
            rebithText4_2.NeddUg = 0;
            rebithText4_2.NeddAp = 0;
            rebithText4_2.NeddIg = 0;
            rebithTextDao.insertAll(rebithText4_2);

            RebirthText rebithText5_1 = new RebirthText();
            rebithText5_1.age = 5;
            rebithText5_1.context = "5岁，你爱玩玩具赛车";
            rebithText5_1.branchtext1 = "";
            rebithText5_1.branchtext2 = "";
            rebithText5_1.NeddPhy = 20;
            rebithText5_1.NeddUg = 0;
            rebithText5_1.NeddAp = 0;
            rebithText5_1.NeddIg = 0;
            rebithTextDao.insertAll(rebithText5_1);

            RebirthText rebithText6_1 = new RebirthText();
            rebithText6_1.age = 6;
            rebithText6_1.context = "6岁，你患病了";
            rebithText6_1.branchtext1 = "多喝热水";
            rebithText6_1.branchtext2 = "花钱治疗";
            rebithText6_1.NeddPhy = 20;
            rebithText6_1.NeddUg = 0;
            rebithText6_1.NeddAp = 0;
            rebithText6_1.NeddIg = 0;
            rebithTextDao.insertAll(rebithText6_1);

            RebirthText rebithText7_1 = new RebirthText();
            rebithText7_1.age = 7;
            rebithText7_1.context = "7岁，你死了";
            rebithText7_1.branchtext1 = "";
            rebithText7_1.branchtext2 = "";
            rebithText7_1.NeddPhy = 80;
            rebithText7_1.NeddUg = 0;
            rebithText7_1.NeddAp = 0;
            rebithText7_1.NeddIg = 0;
            rebithTextDao.insertAll(rebithText7_1);

            RebirthText rebithText7_2 = new RebirthText();
            rebithText7_2.age = 7;
            rebithText7_2.context = "7岁，你痊愈了但是身体消瘦";
            rebithText7_2.branchtext1 = "";
            rebithText7_2.branchtext2 = "";
            rebithText7_2.NeddPhy = 70;
            rebithText7_2.NeddUg = 50;
            rebithText7_2.NeddAp = 0;
            rebithText7_2.NeddIg = 0;
            rebithTextDao.insertAll(rebithText7_2);

            RebirthText rebithText8_1 = new RebirthText();
            rebithText8_1.age = 8;
            rebithText8_1.context = "8岁，你的识字量达到了1000";
            rebithText8_1.branchtext1 = "";
            rebithText8_1.branchtext2 = "";
            rebithText8_1.NeddPhy = 20;
            rebithText8_1.NeddUg = 0;
            rebithText8_1.NeddAp = 0;
            rebithText8_1.NeddIg = 0;
            rebithTextDao.insertAll(rebithText8_1);

            RebirthText rebithText9_1 = new RebirthText();
            rebithText9_1.age = 9;
            rebithText9_1.context = "9岁，你喜欢上了你的同学";
            rebithText9_1.branchtext1 = "";
            rebithText9_1.branchtext2 = "";
            rebithText9_1.NeddPhy = 20;
            rebithText9_1.NeddUg = 0;
            rebithText9_1.NeddAp = 0;
            rebithText9_1.NeddIg = 0;
            rebithTextDao.insertAll(rebithText9_1);

            RebirthText rebithText10_1 = new RebirthText();
            rebithText10_1.age = 10;
            rebithText10_1.context = "10岁，你喜欢的同学转学了";
            rebithText10_1.branchtext1 = "";
            rebithText10_1.branchtext2 = "";
            rebithText10_1.NeddPhy = 20;
            rebithText10_1.NeddUg = 0;
            rebithText10_1.NeddAp = 0;
            rebithText10_1.NeddIg = 0;
            rebithTextDao.insertAll(rebithText10_1);

            RebirthText rebithText11_1 = new RebirthText();
            rebithText11_1.age = 11;
            rebithText11_1.context = "11岁，你发现家里在经营一个拍卖行";
            rebithText11_1.branchtext1 = "去拍卖会";
            rebithText11_1.branchtext2 = "假装无视";
            rebithText11_1.NeddPhy = 20;
            rebithText11_1.NeddUg = 0;
            rebithText11_1.NeddAp = 0;
            rebithText11_1.NeddIg = 0;
            rebithTextDao.insertAll(rebithText11_1);

            RebirthText rebithText12_1 = new RebirthText();
            rebithText12_1.age = 12;
            rebithText12_1.context = "12岁，你学会了骑自行车，并到处乱骑";
            rebithText12_1.branchtext1 = "";
            rebithText12_1.branchtext2 = "";
            rebithText12_1.NeddPhy = 20;
            rebithText12_1.NeddUg = 0;
            rebithText12_1.NeddAp = 0;
            rebithText12_1.NeddIg = 0;
            rebithTextDao.insertAll(rebithText12_1);

            RebirthText rebithText13_1 = new RebirthText();
            rebithText13_1.age = 13;
            rebithText13_1.context = "13岁，你家道中落";
            rebithText13_1.branchtext1 = "";
            rebithText13_1.branchtext2 = "";
            rebithText13_1.NeddPhy = 20;
            rebithText13_1.NeddUg = 0;
            rebithText13_1.NeddAp = 0;
            rebithText13_1.NeddIg = 0;
            rebithTextDao.insertAll(rebithText13_1);

            RebirthText rebithText14_1 = new RebirthText();
            rebithText14_1.age = 14;
            rebithText14_1.context = "14岁，父母把你寄居在叔叔婶婶家";
            rebithText14_1.branchtext1 = "";
            rebithText14_1.branchtext2 = "";
            rebithText14_1.NeddPhy = 20;
            rebithText14_1.NeddUg = 0;
            rebithText14_1.NeddAp = 0;
            rebithText14_1.NeddIg = 0;
            rebithTextDao.insertAll(rebithText14_1);

            RebirthText rebithText15_1 = new RebirthText();
            rebithText15_1.age = 15;
            rebithText15_1.context = "15岁，你得了抑郁症";
            rebithText15_1.branchtext1 = "";
            rebithText15_1.branchtext2 = "";
            rebithText15_1.NeddPhy = 20;
            rebithText15_1.NeddUg = 0;
            rebithText15_1.NeddAp = 0;
            rebithText15_1.NeddIg = 0;
            rebithTextDao.insertAll(rebithText15_1);

            RebirthText rebithText16_1 = new RebirthText();
            rebithText16_1.age = 16;
            rebithText16_1.context = "16岁，你死了";
            rebithText16_1.branchtext1 = "";
            rebithText16_1.branchtext2 = "";
            rebithText16_1.NeddPhy = 20;
            rebithText16_1.NeddUg = 0;
            rebithText16_1.NeddAp = 0;
            rebithText16_1.NeddIg = 0;
            rebithTextDao.insertAll(rebithText16_1);
        }

        //数据库判空，插入天赋
        if (talentDao.getall().isEmpty()) {
            Talent talent1 = new Talent();
            talent1.TalentContext = "祖国人（你是个超级英雄，智力永久降低20）";
            talent1.TalentProperty="Ig";
            talent1.modifyValue = -20;
            talentDao.insertAll(talent1);

            Talent talent2 = new Talent();
            talent2.TalentContext = "幸存者（体力永久提高20）";
            talent2.TalentProperty="Phy";
            talent2.modifyValue = 20;
            talentDao.insertAll(talent2);

            Talent talent3 = new Talent();
            talent3.TalentContext = "薄命（体力永久降低10）";
            talent3.TalentProperty="Phy";
            talent3.modifyValue = -10;
            talentDao.insertAll(talent3);

            Talent talent4 = new Talent();
            talent4.TalentContext = "氪金转生（财富永久提升30）";
            talent4.TalentProperty="Ug";
            talent4.modifyValue = 30;
            talentDao.insertAll(talent4);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.setting,menu);
        return true;
    }
    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.about:
                Intent intent1=new Intent(MainActivity.this,AboutActivity.class);
                startActivity(intent1);
                break;
            case R.id.setting:
                Intent intent2=new Intent(MainActivity.this,setActivity.class);
                startActivity(intent2);
                break;
            case R.id.loginout:
                finish();
        }
        return  true;
    }
}