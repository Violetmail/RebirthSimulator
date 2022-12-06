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
import com.noob.rebirthsimulator.AppData.User;
import com.noob.rebirthsimulator.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
//DAO
    CardDao cardDao;
    UserDao userDao;
    RebithTextDao rebithTextDao;

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
        cardDao=AppDatabase.getInstance(this).cardDao();
        userDao = AppDatabase.getInstance(this).userDao();
        rebithTextDao=AppDatabase.getInstance(this).rebithTextDao();

        //清空卡牌表
        //cardDao.delete(cardDao.getAll());
        //数据库判空，并插入卡牌数据
        if (cardDao.getAll().isEmpty()) {
            Card topcard = new Card();
            topcard.cardname = "卡密";
            topcard.cardStar = 5;
            topcard.cardIg = 100;
            topcard.cardAp =100;
            topcard.cardPhy = 100;
            topcard.cardUg = 100;
            topcard.cardvalue=100;
            topcard.cardbackground="你是神";
            cardDao.insertAll(topcard);


            Card card1 = new Card();
            card1.cardname = "小岛秀夫";
            card1.cardStar = 4;
            card1.cardIg = 90;
            card1.cardAp =70;
            card1.cardPhy = 70;
            card1.cardUg = 90;
            card1.cardvalue=80;
            card1.cardbackground="你是一个知名游戏制作人，在全球享有盛誉，拥有大量的财富和顶级头脑";
            cardDao.insertAll(card1);

            Card card2 = new Card();
            card2.cardname = "莎士比亚";
            card2.cardStar = 3;
            card2.cardIg = 90;
            card2.cardAp =80;
            card2.cardPhy = 60;
            card2.cardUg = 60;
            card2.cardvalue=50;
            card2.cardbackground="你是历史上有名的剧作家，天赋异禀";
            cardDao.insertAll(card2);

            Card card3 = new Card();
            card3.cardname = "打工人";
            card3.cardStar = 2;
            card3.cardIg = 50;
            card3.cardAp =10;
            card3.cardPhy = 60;
            card3.cardUg = 80;
            card3.cardvalue=20;
            card3.cardbackground="你是现实世界中的一个普通人，但也是社会中不可缺少的一份子";
            cardDao.insertAll(card3);

            Card card4 = new Card();
            card4.cardname = "石头";
            card4.cardStar=1;
            card4.cardIg = 1;
            card4.cardAp =10;
            card4.cardPhy = 100;
            card4.cardUg = 10;
            card4.cardvalue=10;
            card4.cardbackground="你是一块无用的石头";
            cardDao.insertAll(card4);

        }

        //数据库判空，并插入转生文本数据
        if (rebithTextDao.getAll().isEmpty()) {
            RebirthText rebithText1 = new RebirthText();
            rebithText1.age =1;
            rebithText1.context = "1岁，你出生了，是个男孩";
            rebithText1.branchtext1 = "";
            rebithText1.branchtext2 = "";
            rebithText1.NeddPhy=0;
            rebithText1.NeddUg=0;
            rebithText1.NeddAp=0;
            rebithText1.NeddIg=0;
            rebithTextDao.insertAll(rebithText1);

            RebirthText rebithText2 = new RebirthText();
            rebithText2.age = 2;
            rebithText2.context = "2岁，你从小生活在农村";
            rebithText2.branchtext1="";
            rebithText2.branchtext2="";
            rebithTextDao.insertAll(rebithText2);

            RebirthText rebithText3 = new RebirthText();
            rebithText3.age = 3;
            rebithText3.context = "3岁，你的父母对你呵护尤佳";
            rebithText3.branchtext1="外出冒险";
            rebithText3.branchtext2="宅家派";
            rebithTextDao.insertAll(rebithText3);

            RebirthText rebithText4 = new RebirthText();
            rebithText4.age = 4;
            rebithText4.context = "4岁，你每天听外婆说睡前故事";
            rebithText4.branchtext1="";
            rebithText4.branchtext2="";
            rebithText4.NeddPhy=20;
            rebithTextDao.insertAll(rebithText4);

            RebirthText rebithText5 = new RebirthText();
            rebithText5.age=4;
            rebithText5.context="4岁，有天打雷了，劈断了你家的一棵树,你在树下冒险，不幸被劈中，你死了";
            rebithText5.branchtext1="";
            rebithText5.branchtext2="";
            rebithText5.NeddPhy=0;
            rebithTextDao.insertAll(rebithText5);

            RebirthText rebithText6=new RebirthText();
            rebithText6.age=5;
            rebithText6.context="5岁，你和野猪赛跑";
            rebithText6.branchtext1="";
            rebithText6.branchtext2="";
            rebithText6.NeddPhy=20;
            rebithTextDao.insertAll(rebithText6);

            RebirthText rebithText7 = new RebirthText();
            rebithText7.age=6;
            rebithText7.context="6岁，你患病了";
            rebithText7.NeddPhy=20;
            rebithText7.branchtext1="";
            rebithText7.branchtext2="";
            rebithTextDao.insertAll(rebithText7);

            RebirthText rebithText8 = new RebirthText();
            rebithText8.age=7;
            rebithText8.context="7岁，你死了";
            rebithText8.branchtext1="";
            rebithText8.branchtext2="";
            rebithTextDao.insertAll(rebithText8);
        }
/*




        RebirthText rebithText7 = new RebirthText();
        rebithText7.age=35;
        rebithText7.context="accident,work,marry";
        rebithText7.specialtext="";
        rebithTextDao.insertAll(rebithText7);

        RebirthText rebithText8 = new RebirthText();
        rebithText8.age=40;
        rebithText8.context="accident,work,marry";
        rebithText8.specialtext="";
        rebithTextDao.insertAll(rebithText8);

        RebirthText rebithText9 = new RebirthText();
        rebithText9.age=45;
        rebithText9.context="accident";
        rebithText9.specialtext="";
        rebithTextDao.insertAll(rebithText9);

        RebirthText rebithText10 = new RebirthText();
        rebithText10.age=50;
        rebithText10.context="accident,journey";
        rebithText10.specialtext="not play";
        rebithTextDao.insertAll(rebithText10);

        RebirthText rebithText11 = new RebirthText();
        rebithText11.age=55;
        rebithText11.context=",accident,working,";
        rebithText11.specialtext="not play";
        rebithTextDao.insertAll(rebithText11);

        RebirthText rebithText12 = new RebirthText();
        rebithText12.age=60;
        rebithText12.context="accident,working";
        rebithText12.specialtext="not play";
        rebithTextDao.insertAll(rebithText12);

        RebirthText rebithText13 = new RebirthText();
        rebithText13.age=65;
        rebithText13.context="accident,journey";
        rebithText13.specialtext="not play";
        rebithTextDao.insertAll(rebithText13);

        RebirthText rebithText14 = new RebirthText();
        rebithText14.age=70;
        rebithText14.context="accident,journey,ill";
        rebithText14.specialtext="not play";
        rebithTextDao.insertAll(rebithText14);

        RebirthText rebithText15= new RebirthText();
        rebithText15.age=75;
        rebithText15.context="accident,journey,ill";
        rebithText15.specialtext="not play";
        rebithTextDao.insertAll(rebithText15);

        RebirthText rebithText16= new RebirthText();
        rebithText16.age=80;
        rebithText16.context="accident,journey,ill";
        rebithText16.specialtext="";
        rebithTextDao.insertAll(rebithText16);
*/
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