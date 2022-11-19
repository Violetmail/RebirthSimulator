package com.noob.rebirthsimulator.ui.home;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.noob.rebirthsimulator.AppData.User;
import com.noob.rebirthsimulator.CardDao;
import com.noob.rebirthsimulator.DrawDao;
import com.noob.rebirthsimulator.R;
import com.noob.rebirthsimulator.UserDao;
import com.noob.rebirthsimulator.UserInformation;
import com.noob.rebirthsimulator.databinding.FragmentHomeBinding;

import java.util.Objects;
import com.noob.rebirthsimulator.AppData.AppDatabase;

public class HomeFragment extends Fragment {
    //使用binging调用控件
    private FragmentHomeBinding binding;
    //实例一个userDao
    UserDao userDao;
    CardDao cardDao;
    DrawDao drawDao;

    //当前用户名
    String nowusername;

    //临时储存数据
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

//oncreatView
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);


        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);


        final TextView textView = binding.textHome;
        final TextView waterview=binding.water;
        final TextView currency1view=binding.currency1;
        //初始化viewmodel
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        //Dao实例化
        userDao = AppDatabase.getInstance(getActivity().getApplicationContext()).userDao();
        cardDao=AppDatabase.getInstance(getActivity().getApplicationContext()).cardDao();
        drawDao=AppDatabase.getInstance(getActivity().getApplicationContext()).drawDao();


        //如果没有用户，自动创建一个用户
        if (userDao.getAll().isEmpty()){
            User user=new User();
            user.username="Guest";
            user.water=40000;
            user.fragment=0;
            user.iflogin=true;
            userDao.insertAll(user);
            nowusername="Guest";
        }
        //获得登陆用户，如果未登陆，使用第一个用户"Guest"
        if (userDao.getloginuser(true)==null){
            userDao.updatelogin(true,"Guest");
            nowusername="Guest";
        }
        else
            nowusername=userDao.getloginuser(true).username;

        //显示水晶数量
        binding.water.setText(String.valueOf(userDao.getwater(nowusername).water));

        binding.cheat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (compoundButton.isChecked()){
                    Toast.makeText(getActivity().getApplicationContext(), "运气提高了！",Toast.LENGTH_SHORT).show();
                }
            }
        });

 //单抽响应按钮
        binding.getone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int counter=homeViewModel.getDrawcounter().getValue();

                int water=userDao.getwater(nowusername).water;
                int fragments=homeViewModel.getFragments().getValue();
                String[] card;
                if (water-280>=0) {
                    //更新水晶
                    userDao.updatawater(nowusername,water-280);
                    if (counter == 1) {
                        card = getuppercard();
                        binding.cardresult.setText(card[0]);
                        binding.cardresult.setTextColor(setquality(card[1]));
                        homeViewModel.setdrawcounter(100);
                    }
                    else {
                        card = getonecard();
                        if (card[1] == "Scard") {
                            homeViewModel.setdrawcounter(100);
                        }
                        else {
                            homeViewModel.setdrawcounter(counter - 1);
                        }
                        if (Objects.equals(card[0], "转生碎片*10")){
                            homeViewModel.setfragments(fragments+10);
                        }
                        binding.cardresult.setText(card[0]);
                        binding.cardresult.setTextColor(setquality(card[1]));
                    }
                    homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
                    binding.water.setText(String.valueOf(userDao.getwater(nowusername).water));
                    homeViewModel.getfragmentstext().observe(getViewLifecycleOwner(), currency1view::setText);
                }
               else {
                    Toast.makeText(getActivity().getApplicationContext(), "水晶不足，无法抽取！", Toast.LENGTH_SHORT).show();
                }

            }
        });
//十连抽响应按钮
        binding.getten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }

        });

//跳转到用户界面
        binding.userButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(),UserInformation.class));
            }
        });



        View root = binding.getRoot();
        return root;
    }

//抽取一次，概率分别为5% 25% 50% 20%,返回卡片数组，卡片名和品质Rank
private String[] getonecard(){
    String[] card1 ;
    String[] card2 ;
    String[] card3 ;
    String[] card4;
    String[] cardtext = new String[2];
    card1= this.getResources().getStringArray(R.array.itemcardbox);
    card2= this.getResources().getStringArray(R.array.normalcardbox);
    card3= this.getResources().getStringArray(R.array.speicalcardbox);
    card4=this.getResources().getStringArray(R.array.Uppercardbox);

    int x=(int)(Math.random()*100);
    if (x<=5){
        int id = (int) (Math.random()*(card4.length));//随机产生一个顶级卡
        cardtext[0] = card4[id];
        cardtext[1]="Scard";
        return cardtext;
    }
    else if (x<=30){
        int id = (int) (Math.random()*(card3.length));//随机产生一个精英卡
        cardtext[0] = card3[id];
        cardtext[1]="Acard";
        return cardtext;
    }
    else if (x<=80){
        int id = (int) (Math.random()*(card2.length));//随机产生一个普通卡
        cardtext[0] = card2[id];
        cardtext[1]="Bcard";
        return cardtext;
    }
    else{
        int id = (int) (Math.random()*(card1.length));//随机产生一个道具
        cardtext[0] = card1[id];
        cardtext[1]="Ccard";
        return cardtext;
    }

}
//强制获得最强卡
private String[] getuppercard(){
    String[] card ;
    String[] cardtext = new String[2];
    card=this.getResources().getStringArray(R.array.Uppercardbox);
    int id = (int) (Math.random()*(card.length));//随机产生一个顶级卡
    cardtext[0] = card[id];
    cardtext[1]="Scard";
    return cardtext;

}

//根据品质rank设置卡片颜色属性
private int setquality(String rank){
        int[] color=new int[4];
    color[0]= this.getResources().getColor(R.color.black);
    color[1]= this.getResources().getColor(R.color.MediumSpringGreen);
    color[2]= this.getResources().getColor(R.color.CornflowerBlue);
    color[3]= this.getResources().getColor(R.color.Gold);
    int qualitycolor=0;
    switch (rank){
        case "Scard":
            qualitycolor= color[3];
            break;
        case "Acard":
            qualitycolor= color[2];
            break;
        case "Bcard":
            qualitycolor= color[1];
            break;
        case "Ccard":
            qualitycolor= color[0];
            break;
    }
    return qualitycolor;
}

    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences prefs= getActivity().getPreferences(MODE_PRIVATE);
        String username=prefs.getString("username","");
        String password=prefs.getString("password","");
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
//