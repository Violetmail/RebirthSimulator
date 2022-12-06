package com.noob.rebirthsimulator.ui.dashboard;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.noob.rebirthsimulator.AppData.AppDatabase;
import com.noob.rebirthsimulator.AppData.Card;
import com.noob.rebirthsimulator.AppData.UserCard;
import com.noob.rebirthsimulator.CardDao;
import com.noob.rebirthsimulator.R;
import com.noob.rebirthsimulator.UserCardDao;
import com.noob.rebirthsimulator.UserDao;

import com.noob.rebirthsimulator.databinding.FragmentDashboardBinding;

import java.util.ArrayList;
import java.util.List;

public class DashboardFragment extends Fragment {
    //初始化控件
    private FragmentDashboardBinding binding;

    //实例Dao
    UserDao userDao;
    CardDao cardDao;
    UserCardDao userCardDao;

    //用户名
    String nowusername;
    //所有卡牌列表
    List<Card> Allcard;
    //用户卡牌列表
    List<UserCard> mydrawedcard;
    //实例化显示数据的数组
    List<CardData> CardList = new ArrayList<>();

    //定义MyViewHoder类
    class MyViewHoder extends RecyclerView.ViewHolder {
        Button card_image;
        Button card_value;

        public MyViewHoder(@NonNull View itemView) {
            super(itemView);
            card_image=itemView.findViewById(R.id.card_image);
            card_value =itemView.findViewById(R.id.card_value);
        }
    }

    //定义适配器
    class CardAdapter extends RecyclerView.Adapter<MyViewHoder> {
        @NonNull
        @Override
        public MyViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            //连接定义的card_list布局
            View view = View.inflate(getActivity().getApplicationContext(), R.layout.card_list, null);
            MyViewHoder myViewHoder = new MyViewHoder(view);
            return myViewHoder;
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHoder holder, int position) {
            //将CardData类的数据写入布局控件中
            CardData cardData=CardList.get(position);
            holder.card_image.setText(cardData.name);
            holder.card_value.setText(cardData.content);

            //初始化Dao
            userDao=AppDatabase.getInstance(getActivity().getApplicationContext()).userDao();
            cardDao=AppDatabase.getInstance(getActivity().getApplicationContext()).cardDao();
            userCardDao=AppDatabase.getInstance(getActivity().getApplicationContext()).userCardDao();

            //获取当前卡片名称
            String btnname=holder.card_image.getText().toString();
            //如果当前卡片能在usercard表中找到，则变色
            if (userCardDao.findByUserAndName(nowusername,btnname)!=null){
                //根据星级设置颜色
                switch (userCardDao.findByUserAndName(nowusername,btnname).cardstar){
                    case 5:
                        holder.card_image.setBackgroundColor(getResources().getColor(R.color.Pink_25dark));
                        break;
                    case 4:
                        holder.card_image.setBackgroundColor(getResources().getColor(R.color.Gold));
                        break;
                    case 3:
                        holder.card_image.setBackgroundColor(getResources().getColor(R.color.purple_500));
                        break;
                    case 2:
                        holder.card_image.setBackgroundColor(getResources().getColor(R.color.CornflowerBlue));
                        break;
                    case 1:
                        holder.card_image.setBackgroundColor(getResources().getColor(R.color.MediumSpringGreen));
                        break;
                }
                holder.card_value.setTextColor(getResources().getColor(R.color.CornflowerBlue));
                holder.card_value.setText("已拥有");
            }
            //否则卡片变灰,字体变灰
            else {
                holder.card_image.setBackgroundColor(getResources().getColor(R.color.gray));
                holder.card_value.setTextColor(getResources().getColor(R.color.black));
            }
            //卡片按钮点击事件
            holder.card_image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //文本为卡片名称则显示卡片属性
                    if (holder.card_image.getText().toString().equals(btnname)){
                    //获取卡片属性文本
                    String content="星级："+cardDao.findByName(btnname).cardStar+"\n智力："+cardDao.findByName(btnname).cardIg+
                            "\n外貌："+cardDao.findByName(btnname).cardAp+"\n体力："+cardDao.findByName(btnname).cardPhy+
                            "\n财富："+cardDao.findByName(btnname).cardUg;
                    holder.card_image.setText(content);
                    }
                    //不为卡片名称则显示卡片名称
                    else
                        holder.card_image.setText(btnname);
                }
            });
            //购买按钮点击事件
            holder.card_value.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //获取当前碎片数
                    int nowfragment=userDao.findByName(nowusername).fragment;
                    //获取当前卡片
                    Card nowcard=cardDao.findByName(btnname);
                    if (holder.card_value.getText().toString()!="已拥有"){
                        if (nowfragment<nowcard.cardvalue){
                            //购买失败,弹出对话框
                            AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
                                    .setTitle("卡片购买")//标题
                                    .setMessage("碎片数量不足，购买失败！")//内容
                                    .setPositiveButton("确认",null)
                                    .create();
                            alertDialog.getWindow().setGravity(Gravity.CENTER);
                            alertDialog.getWindow().setType(WindowManager.LayoutParams.FIRST_APPLICATION_WINDOW);
                            alertDialog.show();
                        }
                        else {
                            //可以购买，提示对话框
                            AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
                                    .setTitle("卡片购买")//标题
                                    .setMessage("确定要购买卡片：" + holder.card_image.getText().toString() + "吗？")//内容
                                    //确认按钮
                                    .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            //抽到的卡加入用户卡
                                            UserCard userCard = new UserCard();
                                            userCard.username = nowusername;
                                            userCard.cardname = nowcard.cardname;
                                            userCard.cardstar = nowcard.cardStar;
                                            userCard.cardvalue= nowcard.cardvalue;
                                            //如果卡不在用户卡表中则插入
                                            if (userCardDao.findByUserAndName(nowusername,userCard.cardname)==null) {
                                                userCardDao.insertAll(userCard);
                                            }
                                            //更新碎片数量
                                            userDao.updatafragment(nowusername,nowfragment-nowcard.cardvalue);
                                            //显示碎片数量
                                            binding.fragmentstext.setText("碎片："+userDao.findByName(nowusername).fragment);
                                            //改变字体颜色和内容
                                            holder.card_value.setText("已拥有");
                                            holder.card_value.setTextColor(getResources().getColor(R.color.CornflowerBlue));
                                            //改变卡片颜色
                                            //根据星级设置颜色
                                            switch (userCardDao.findByUserAndName(nowusername,btnname).cardstar){
                                                case 5:
                                                    holder.card_image.setBackgroundColor(getResources().getColor(R.color.Pink_25dark));
                                                    break;
                                                case 4:
                                                    holder.card_image.setBackgroundColor(getResources().getColor(R.color.Gold));
                                                    break;
                                                case 3:
                                                    holder.card_image.setBackgroundColor(getResources().getColor(R.color.purple_500));
                                                    break;
                                                case 2:
                                                    holder.card_image.setBackgroundColor(getResources().getColor(R.color.CornflowerBlue));
                                                    break;
                                                case 1:
                                                    holder.card_image.setBackgroundColor(getResources().getColor(R.color.MediumSpringGreen));
                                                    break;

                                            }
                                        }
                                    })
                                    //取消按钮
                                    .setNegativeButton("取消", null)
                                    .create();
                            alertDialog.getWindow().setGravity(Gravity.CENTER);
                            alertDialog.getWindow().setType(WindowManager.LayoutParams.FIRST_APPLICATION_WINDOW);
                            alertDialog.show();
                        }
                    }
                }
            });
        }
        @Override
        public int getItemCount() {
            return CardList.size();
        }
    }

    //实例化适配器
    CardAdapter cardAdapter ;


//Creat生命周期
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        //bingding绑定控件
        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        //Dao实例化
        userDao = AppDatabase.getInstance(getActivity().getApplicationContext()).userDao();
        cardDao=AppDatabase.getInstance(getActivity().getApplicationContext()).cardDao();
        userCardDao=AppDatabase.getInstance(getActivity().getApplicationContext()).userCardDao();
        //实例化适配器
        cardAdapter=new CardAdapter();
        //绑定适配器
        binding.cardlist.setAdapter(cardAdapter);

        ////设置recycleview布局 3列布局
        GridLayoutManager gridLayoutManager = new GridLayoutManager(
                getActivity().getApplicationContext(), 3, GridLayoutManager.VERTICAL, false);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        binding.cardlist.setLayoutManager(gridLayoutManager);

        //获得用户名
        nowusername=userDao.getloginuser(true).username;

        //显示碎片数
        binding.fragmentstext.setText("碎片："+userDao.findByName(nowusername).fragment);

        //全部按钮功能
        binding.allcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //全部卡列表
                Allcard=cardDao.getAll();
                //变色
                binding.allcard.setBackgroundColor(getResources().getColor(R.color.purple_500));
                binding.mycard.setBackgroundColor(getResources().getColor(R.color.gray));
                //清空CardList
                CardList.clear();
                //加入数据
                for (int i = 0; i < Allcard.size(); i++) {
                    CardData cardData=new CardData();
                    cardData.name=Allcard.get(i).cardname;
                    //写入属性
                    cardData.content="价格："+Allcard.get(i).cardvalue;
                    CardList.add(cardData);
                }
                //刷新适配器
                cardAdapter.notifyDataSetChanged();
            }
        });

        //已拥有按钮功能
        binding.mycard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //获得用户卡列表
                mydrawedcard=userCardDao.findByName(nowusername);
                //变色
                binding.allcard.setBackgroundColor(getResources().getColor(R.color.gray));
                binding.mycard.setBackgroundColor(getResources().getColor(R.color.purple_500));
                //清空CardList
                CardList.clear();
                //加入数据
                for (int i = 0; i < mydrawedcard.size(); i++) {
                    CardData cardData=new CardData();
                    cardData.name=mydrawedcard.get(i).cardname;
                    //写入属性
                    cardData.content="价格："+mydrawedcard.get(i).cardvalue;
                    CardList.add(cardData);
                }
                //刷新适配器
                cardAdapter.notifyDataSetChanged();
            }
        });

        //默认点击一次全部显示图鉴
        binding.allcard.callOnClick();

        View root = binding.getRoot();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}