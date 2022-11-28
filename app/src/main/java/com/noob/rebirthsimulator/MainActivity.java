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
import com.noob.rebirthsimulator.AppData.User;
import com.noob.rebirthsimulator.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
//DAO
    CardDao cardDao;
    UserDao userDao;

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

        //清空卡牌表
        //cardDao.delete(cardDao.getAll());
        //数据库判空，并插入数据
        if (cardDao.getAll().isEmpty()) {
            Card topcard = new Card();
            topcard.cardname = "MAX";
            topcard.cardStar = 5;
            topcard.cardIg = 100;
            topcard.cardAp =100;
            topcard.cardPhy = 100;
            topcard.cardUg = 100;
            topcard.cardvalue=100;
            cardDao.insertAll(topcard);


            Card card1 = new Card();
            card1.cardname = "S";
            card1.cardStar = 4;
            card1.cardIg = 90;
            card1.cardAp =80;
            card1.cardPhy = 90;
            card1.cardUg = 50;
            card1.cardvalue=80;
            cardDao.insertAll(card1);

            Card card2 = new Card();
            card2.cardname = "A";
            card2.cardStar = 3;
            card2.cardIg = 90;
            card1.cardAp =70;
            card2.cardPhy = 60;
            card2.cardUg = 80;
            card2.cardvalue=50;
            cardDao.insertAll(card2);

            Card card3 = new Card();
            card3.cardname = "B";
            card3.cardStar = 2;
            card3.cardIg = 50;
            card1.cardAp =10;
            card3.cardPhy = 60;
            card3.cardUg = 80;
            card3.cardvalue=20;
            cardDao.insertAll(card3);

            Card card0 = new Card();
            card0.cardname = "转生碎片*10";
            card0.cardStar=1;
            card0.cardvalue=0;
            card0.cardvalue=10;
            cardDao.insertAll(card0);

            Card cardx = new Card();
            cardx.cardname = "白给！";
            cardx.cardStar=1;
            cardx.cardvalue=0;
            cardx.cardvalue=1;
            cardDao.insertAll(cardx);
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