package com.noob.rebirthsimulator.ui.home;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.noob.rebirthsimulator.AppData.Card;
import com.noob.rebirthsimulator.AppData.User;
import com.noob.rebirthsimulator.AppData.UserCard;
import com.noob.rebirthsimulator.CardDao;
import com.noob.rebirthsimulator.R;
import com.noob.rebirthsimulator.UserCardDao;
import com.noob.rebirthsimulator.UserDao;
import com.noob.rebirthsimulator.UserInformation;
import com.noob.rebirthsimulator.databinding.FragmentHomeBinding;
import com.noob.rebirthsimulator.MainActivity;

import java.util.List;
import java.util.Random;

import com.noob.rebirthsimulator.AppData.AppDatabase;

public class HomeFragment extends Fragment {
    //使用binging调用控件
    private FragmentHomeBinding binding;
    //实例一个userDao
    UserDao userDao;
    CardDao cardDao;
    UserCardDao userCardDao;

    //当前用户名
    String nowusername;

    //临时储存数据(未使用)
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

//生命周期；create
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);

        //viewmodel使用，已经废弃
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        final TextView textView = binding.textHome;
        final TextView waterview=binding.water;
        final TextView currency1view=binding.currency1;

        //Dao实例化
        userDao = AppDatabase.getInstance(getActivity().getApplicationContext()).userDao();
        cardDao=AppDatabase.getInstance(getActivity().getApplicationContext()).cardDao();
        userCardDao=AppDatabase.getInstance(getActivity().getApplicationContext()).userCardDao();

        //如果没有用户，自动创建一个用户
        if (userDao.getAll().isEmpty()){
            User user=new User();
            user.username="Guest";
            user.water=40000;
            user.fragment=100;
            user.iflogin=true;
            user.drawcounter=100;
            user.giftwater=1;
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
        binding.water.setText(String.valueOf(userDao.findByName(nowusername).water));
        //显示保底文本
        binding.textHome.setText("再抽取"+userDao.findByName(nowusername).drawcounter+"次，必出xxx！");
        //显示碎片数量
        binding.currency1.setText(String.valueOf(userDao.findByName(nowusername).fragment));
        //显示水晶券数量
        binding.currency2.setText(String.valueOf(userDao.findByName(nowusername).giftwater));

//switch按钮响应功能
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
                //引入抽卡保底数，水晶数，和碎片数。
                int counter=userDao.findByName(nowusername).drawcounter;
                int water=userDao.findByName(nowusername).water;
                int fragments=userDao.findByName(nowusername).fragment;
                if (water-280>=0) {
                    //抽一张卡
                    Card drawing=new Card();
                    drawing=getAcard(5,25,50,20);
                    //更新水晶
                    userDao.updatawater(nowusername,water-280);
                    if (counter == 1) {
                        drawing=getuppercard();
                        binding.cardresult.setText(drawing.cardname);
                        binding.cardresult.setTextColor(drawing.cardStar);
                        //更新保底数
                        userDao.updatadrawcounter(nowusername,100);
                    }
                    else {
                        if (drawing.cardStar == 4) {
                            //更新保底数
                            userDao.updatadrawcounter(nowusername,100);
                        }
                        else {
                            //更新保底数
                            userDao.updatadrawcounter(nowusername,counter-1);
                        }
                        if (drawing.cardname.equals("转生碎片*10")){
                            //更新碎片
                            userDao.updatafragment(nowusername,fragments+10);
                        }
                        //显示抽卡结果
                        binding.cardresult.setText(drawing.cardname);
                        binding.cardresult.setTextColor(setquality(drawing.cardStar));

                        //用户卡列表
                        List<UserCard> usercard=userCardDao.findByName(nowusername);
                        //抽到的卡加入用户卡
                        UserCard userCard0 = new UserCard();
                        userCard0.username = nowusername;
                        userCard0.cardname = drawing.cardname;
                        userCard0.cardstar = drawing.cardStar;
                        userCard0.cardvalue= drawing.cardvalue;
                        //如果卡不在用户卡表中则插入
                        if (userCardDao.findByUserAndName(nowusername,userCard0.cardname)==null) {
                            userCardDao.insertAll(userCard0);
                        }
                    }
                    //界面显示新值
                    binding.water.setText(String.valueOf(userDao.findByName(nowusername).water));
                    binding.currency1.setText(String.valueOf(userDao.findByName(nowusername).fragment));
                    binding.textHome.setText("再抽取"+userDao.findByName(nowusername).drawcounter+"次，必出xxx！");

                }
               else {
                    //抽卡失败,弹出对话框
                    AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
                            .setTitle("抽卡")//标题
                            .setMessage("水晶数量不足，请先获取水晶！")//内容
                            .setPositiveButton("确认",null)
                            .create();
                    alertDialog.getWindow().setGravity(Gravity.CENTER);
                    alertDialog.getWindow().setType(WindowManager.LayoutParams.FIRST_APPLICATION_WINDOW);
                    alertDialog.show();
                }
            }
        });
//十连抽响应按钮
        binding.getten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int counter10=userDao.findByName(nowusername).drawcounter;
                int water10=userDao.findByName(nowusername).water;
                if (water10>=2800) {
                    //相当于点击十次 单抽
                    for (int i=0;i<10;i++){
                        binding.getone.callOnClick();
                    }
                    //更新界面
                    binding.textHome.setText("再抽取" + userDao.findByName(nowusername).drawcounter + "次，必出xxx！");
                    binding.water.setText(String.valueOf(userDao.findByName(nowusername).water));
                }
                else {
                    //抽卡失败,弹出对话框
                    AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
                            .setTitle("抽卡")//标题
                            .setMessage("水晶数量不足，请先获取水晶！")//内容
                            .setPositiveButton("确认",null)
                            .create();
                    alertDialog.getWindow().setGravity(Gravity.CENTER);
                    alertDialog.getWindow().setType(WindowManager.LayoutParams.FIRST_APPLICATION_WINDOW);
                    alertDialog.show();
                }
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
//抽一次卡,概率分别为 PS,PA,PB,PC
    private Card getAcard(int PS,int PA,int PB,int PC){
        int x=(int)(Math.random()*100);
        if (x<=PS){
            //获得随机数
            Random randindex=new Random();
            int index=randindex.nextInt(cardDao.findByStar(4).size());
            //返回随机的一个卡
            return cardDao.findByStar(4).get(index);
        }
        else if (x<=PS+PA){
            //获得随机数
            Random randindex=new Random();
            int index=randindex.nextInt(cardDao.findByStar(3).size());
            //返回随机的一个卡
            return cardDao.findByStar(3).get(index);
        }
        else if (x<=PS+PA+PB){
            //获得随机数
            Random randindex=new Random();
            int index=randindex.nextInt(cardDao.findByStar(2).size());
            //返回随机的一个卡
            return cardDao.findByStar(2).get(index);
        }
        else {
            //获得随机数
            Random randindex=new Random();
            int index=randindex.nextInt(cardDao.findByStar(1).size());
            //返回随机的一个卡
            return cardDao.findByStar(1).get(index);
        }
    }

//强制获得最强卡
private Card getuppercard(){
    //获得随机数
    Random randindex=new Random();
    int index=randindex.nextInt(cardDao.findByStar(4).size());
    //返回随机的一个卡名
    return cardDao.findByStar(4).get(index);
}

//根据品质rank设置卡片颜色属性
private int setquality(int rank){
        int[] color=new int[4];
    color[0]= this.getResources().getColor(R.color.black);
    color[1]= this.getResources().getColor(R.color.MediumSpringGreen);
    color[2]= this.getResources().getColor(R.color.CornflowerBlue);
    color[3]= this.getResources().getColor(R.color.Gold);
    int qualitycolor=0;
    switch (rank){
        case 4:
            qualitycolor= color[3];
            break;
        case 3:
            qualitycolor= color[2];
            break;
        case 2:
            qualitycolor= color[1];
            break;
        case 1:
            qualitycolor= color[0];
            break;
    }
    return qualitycolor;
}

    @Override
    public void onResume() {
        super.onResume();
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