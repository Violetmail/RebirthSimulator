package com.noob.rebirthsimulator.ui.home;

import android.content.DialogInterface;
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

import com.noob.rebirthsimulator.AppData.Card;
import com.noob.rebirthsimulator.AppData.User;
import com.noob.rebirthsimulator.AppData.UserCard;
import com.noob.rebirthsimulator.CardDao;
import com.noob.rebirthsimulator.R;
import com.noob.rebirthsimulator.UserCardDao;
import com.noob.rebirthsimulator.UserDao;
import com.noob.rebirthsimulator.UserInformation;
import com.noob.rebirthsimulator.databinding.FragmentHomeBinding;

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

    //抽奖概率
    int PS;
    int PA;
    int PB;
    int PC;

//生命周期；create
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);

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
        binding.textHome.setText("再抽取"+userDao.findByName(nowusername).drawcounter+"次，必出S级角色卡！");
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
                    PS=5;
                    PA=20;
                    PB=25;
                    PC=50;
                }
                else {
                    PS=2;
                    PA=8;
                    PB=10;
                    PC=80;
                }
            }
        });

//加水晶相应按钮
        binding.addwater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //获取水晶和水晶券数量
                int water=userDao.findByName(nowusername).water;
                int giftwater=userDao.findByName(nowusername).giftwater;
                if (giftwater>=1) {
                    AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
                            .setTitle("水晶券")//标题
                            .setMessage("确认使用水晶券吗？")//内容
                            .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    //更新
                                    User user = userDao.findByName(nowusername);
                                    user.water = water + 10000;
                                    user.giftwater = giftwater - 1;
                                    userDao.update(user);
                                    //界面更新
                                    binding.water.setText(String.valueOf(userDao.findByName(nowusername).water));
                                    binding.currency2.setText(String.valueOf(userDao.findByName(nowusername).giftwater));
                                }
                            })
                            .setNegativeButton("取消",null)
                            .create();
                    alertDialog.getWindow().setGravity(Gravity.CENTER);
                    alertDialog.getWindow().setType(WindowManager.LayoutParams.FIRST_APPLICATION_WINDOW);
                    alertDialog.show();
                }
                else {
                    //水晶券使用失败,弹出对话框
                    AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
                            .setTitle("水晶券")//标题
                            .setMessage("您还没有水晶券可以使用！")//内容
                            .setPositiveButton("确认", null)
                            .create();
                    alertDialog.getWindow().setGravity(Gravity.CENTER);
                    alertDialog.getWindow().setType(WindowManager.LayoutParams.FIRST_APPLICATION_WINDOW);
                    alertDialog.show();
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
                //清空十连显示文本
                for (int i=0;i<10;i++) {
                    TextView nowTextview;
                    switch (i) {
                        case 0:
                            nowTextview = binding.textView3;
                            break;
                        case 1:
                            nowTextview = binding.textView4;
                            break;
                        case 2:
                            nowTextview = binding.textView5;
                            break;
                        case 3:
                            nowTextview = binding.textView6;
                            break;
                        case 4:
                            nowTextview = binding.textView7;
                            break;
                        case 5:
                            nowTextview = binding.textView8;
                            break;
                        case 6:
                            nowTextview = binding.textView9;
                            break;
                        case 7:
                            nowTextview = binding.textView10;
                            break;
                        case 8:
                            nowTextview = binding.textView11;
                            break;
                        case 9:
                            nowTextview = binding.textView12;
                            break;
                        default:
                            nowTextview = binding.cardresult;
                    }
                    nowTextview.setText("");
                }
                //抽！
                if (water-280>=0) {
                    //抽一张卡
                    Card drawing=getAcard(PS,PA,PB,PC);
                    //更新水晶
                    userDao.updatawater(nowusername,water-280);
                    if (counter == 1) {
                        drawing=getScard();
                        //显示结果
                        setresultText(drawing,binding.cardresult);
                        //未拥有的卡加入用户表
                        card2user(drawing);
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
                        //显示抽卡结果
                        setresultText(drawing,binding.cardresult);

                        //未拥有的卡加入用户表
                        card2user(drawing);
                    }
                    //界面显示新值
                    binding.water.setText(String.valueOf(userDao.findByName(nowusername).water));
                    binding.textHome.setText("再抽取"+userDao.findByName(nowusername).drawcounter+"次，必出S级角色卡！");

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
                int counter10;
                int water10=userDao.findByName(nowusername).water;
                TextView nowTextview;
                //清除单抽显示
                binding.cardresult.setText("");
                //循环十次
                if (water10>=2800) {
                    //相当于点击十次 单抽
                    for (int i=0;i<10;i++){
                        counter10=userDao.findByName(nowusername).drawcounter;
                        water10=userDao.findByName(nowusername).water;
                        switch (i) {
                            case 0:
                                nowTextview=binding.textView3;
                                break;
                            case 1:
                                nowTextview=binding.textView4;
                                break;
                            case 2:
                                nowTextview=binding.textView5;
                                break;
                            case 3:
                                nowTextview=binding.textView6;
                                break;
                            case 4:
                                nowTextview=binding.textView7;
                                break;
                            case 5:
                                nowTextview=binding.textView8;
                                break;
                            case 6:
                                nowTextview=binding.textView9;
                                break;
                            case 7:
                                nowTextview=binding.textView10;
                                break;
                            case 8:
                                nowTextview=binding.textView11;
                                break;
                            case 9:
                                nowTextview=binding.textView12;
                                break;
                            default:
                                nowTextview=binding.cardresult;
                        }
                        //抽一张卡
                        Card drawing=getAcard(PS,PA,PB,PC);
                        //更新水晶
                        userDao.updatawater(nowusername,water10-280);
                        if (counter10== 1) {
                            drawing=getScard();
                            //结果
                            setresultText(drawing,nowTextview);
                            //未拥有的卡加入用户表
                            card2user(drawing);
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
                                userDao.updatadrawcounter(nowusername,counter10-1);
                            }
                            //显示抽卡结果
                            setresultText(drawing,nowTextview);

                            //未拥有的卡加入用户表
                            card2user(drawing);
                        }
                    }
                    //界面显示新值
                    binding.water.setText(String.valueOf(userDao.findByName(nowusername).water));
                    binding.textHome.setText("再抽取"+userDao.findByName(nowusername).drawcounter+"次，必出S级角色卡！");
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

//强制获得S卡
private Card getScard(){
    //获得随机数
    Random randindex=new Random();
    int index=randindex.nextInt(cardDao.findByStar(4).size());
    //返回随机的一个卡名
    return cardDao.findByStar(4).get(index);
}

//显示卡片数据
    private void setresultText(Card card,TextView textView){
        textView.setText(card.cardname);
        textView.setTextColor(setquality(card.cardStar));
    }

//抽到的卡加入用户表
    private void card2user(Card drawing){
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
        //显示变动的数据
        binding.water.setText(String.valueOf(userDao.findByName(nowusername).water));
        binding.currency1.setText(String.valueOf(userDao.findByName(nowusername).fragment));
        binding.currency2.setText(String.valueOf(userDao.findByName(nowusername).giftwater));
        binding.textHome.setText("再抽取"+userDao.findByName(nowusername).drawcounter+"次，必出S级角色卡！");
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