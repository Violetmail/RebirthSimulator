package com.noob.rebirthsimulator.ui.notifications;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.noob.rebirthsimulator.AppData.AppDatabase;
import com.noob.rebirthsimulator.AppData.RebirthText;
import com.noob.rebirthsimulator.AppData.UserCard;
import com.noob.rebirthsimulator.CardDao;
import com.noob.rebirthsimulator.LifeResultActivity;
import com.noob.rebirthsimulator.R;
import com.noob.rebirthsimulator.RebithTextDao;
import com.noob.rebirthsimulator.UserCardDao;
import com.noob.rebirthsimulator.UserDao;
import com.noob.rebirthsimulator.databinding.FragmentNotificationsBinding;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NotificationsFragment extends Fragment {
    //绑定控件
    private FragmentNotificationsBinding binding;
    //实例化Dao
    CardDao cardDao;
    UserDao userDao;
    UserCardDao userCardDao;
    RebithTextDao rebithTextDao;

    //用户名
    String nowusername;
    //当前角色
    public  String nowcharacter;
    //当前卡片的属性
    //卡片智力
    public int cardIg;
    //卡片外貌
    public int cardAp;
    //卡片体力
    public int cardPhy;
    //卡片财富
    public int cardUg;
    //当前文本的年龄
    public int nowage;
    //死亡年龄
    public  int deathage;

    //获取数据库所有的文本
    List<RebirthText> AllRebirthText=new ArrayList<>();
    //实例化显示文本数据的数组
    List<TextData> TextList=new ArrayList<>();
    //实例化显示文本数据的数组
    List<UserCard> CharacterList=new ArrayList<>();

//recycleview填入文本
    //定义MyViewHoder类
    class MyViewHoder extends RecyclerView.ViewHolder {
        //实例控件
        TextView rebirth_text;
        Button branch1;
        Button branch2;
        Button characterBtn;
        public MyViewHoder(@NonNull View itemView) {
            super(itemView);
            //初始化
            rebirth_text=itemView.findViewById(R.id.rebirth_text);
            branch1=itemView.findViewById(R.id.branch1);
            branch2=itemView.findViewById(R.id.branch2);
            characterBtn=itemView.findViewById(R.id.characterBtn);
        }
    }

    //定义适配器1
    class  RebirthAdapter1 extends RecyclerView.Adapter<MyViewHoder>{
        @NonNull
        @Override
        public MyViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            //连接定义的character_list布局
            View view = View.inflate(getActivity().getApplicationContext(), R.layout.character_list, null);
            MyViewHoder myViewHoder = new MyViewHoder(view);
            return myViewHoder;
        }
        @Override
        public void onBindViewHolder(@NonNull MyViewHoder holder, int position) {
            //初始化Dao
            userDao=AppDatabase.getInstance(getActivity().getApplicationContext()).userDao();
            rebithTextDao=AppDatabase.getInstance(getActivity().getApplicationContext()).rebithTextDao();
            userCardDao=AppDatabase.getInstance(getActivity().getApplicationContext()).userCardDao();
            cardDao=AppDatabase.getInstance(getActivity().getApplicationContext()).cardDao();
            //显示数据
            holder.characterBtn.setBackgroundColor(getResources().getColor(R.color.gray));
            holder.characterBtn.setText(CharacterList.get(position).cardname);
            //按钮点击
            holder.characterBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //改变按钮颜色
                    holder.characterBtn.setBackgroundColor(getResources().getColor(R.color.CornflowerBlue));
                    //确认是否转生的对话框
                    AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
                            .setTitle("转生")//标题
                            .setMessage("确认转生为Ta了吗？")//内容
                            //肯定按钮逻辑
                            .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    //确认当前角色
                                    nowcharacter=holder.characterBtn.getText().toString();
                                    cardIg=cardDao.findByName(nowcharacter).cardIg;
                                    cardAp=cardDao.findByName(nowcharacter).cardAp;
                                    cardPhy=cardDao.findByName(nowcharacter).cardPhy;
                                    cardUg=cardDao.findByName(nowcharacter).cardUg;
                                    //适配器2插入默认显示角色的背景身世
                                    TextData textData=new TextData();
                                    textData.age=0;
                                    textData.content =cardDao.findByName(nowcharacter).cardbackground;
                                    textData.branch1="";
                                    textData.branch2="";
                                    TextList.add(textData);

                                    //改变绑定适配器2，进行文本显示
                                    binding.rebirthlist.setAdapter(rebirthAdapter2);

                                    //改变按钮可见
                                    binding.plzchoose.setVisibility(View.INVISIBLE);
                                    binding.characterImg.setVisibility(View.VISIBLE);
                                    binding.continuebtn.setVisibility(View.VISIBLE);
                                }
                            })
                            //否定按钮逻辑
                            .setNegativeButton("重新选择", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    //改变按钮颜色
                                    holder.characterBtn.setBackgroundColor(getResources().getColor(R.color.gray));
                                }
                            })
                            .create();
                    alertDialog.getWindow().setGravity(Gravity.CENTER);
                    alertDialog.getWindow().setType(WindowManager.LayoutParams.FIRST_APPLICATION_WINDOW);
                    alertDialog.show();
                }
            });
        }
        @Override
        public int getItemCount() {
            return CharacterList.size();
        }
    }

    //定义适配器2
    class RebirthAdapter2 extends RecyclerView.Adapter<MyViewHoder>{
        @NonNull
        @Override
        public MyViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            //连接定义的rebirth_list布局
            View view = View.inflate(getActivity().getApplicationContext(), R.layout.rebirth_list, null);
            MyViewHoder myViewHoder = new MyViewHoder(view);
            return myViewHoder;
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHoder holder, int position) {
            //初始化Dao
            userDao = AppDatabase.getInstance(getActivity().getApplicationContext()).userDao();
            rebithTextDao = AppDatabase.getInstance(getActivity().getApplicationContext()).rebithTextDao();
            userCardDao = AppDatabase.getInstance(getActivity().getApplicationContext()).userCardDao();
            //将TextData类的数据写入
            TextData textData = TextList.get(position);
            holder.rebirth_text.setText(textData.content);
            //获得当前年龄
            nowage = textData.age;

            //分支若存在，则按钮可见
            if (!textData.branch1.equals("") || !textData.branch2.equals("")) {
                //改变可见，显示按钮
                holder.branch1.setVisibility(View.VISIBLE);
                holder.branch1.setText(textData.branch1);
                holder.branch2.setVisibility(View.VISIBLE);
                holder.branch2.setText(textData.branch2);
                //不可继续
                binding.continuebtn.setVisibility(View.INVISIBLE);
                //获取必要数据
                int giftwater=userDao.findByName(nowusername).giftwater;
                //分支一按钮事件
                holder.branch1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //根据按钮给予事件
                        ApplyBranch(textData.branch1,giftwater);
                        //显示继续,隐藏另一个按钮
                        binding.continuebtn.setVisibility(View.VISIBLE);
                        holder.branch2.setEnabled(false);
                    }
                });
                //分支二按钮事件
                holder.branch2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ApplyBranch(textData.branch2,giftwater);
                        //显示继续,隐藏另一个按钮
                        binding.continuebtn.setVisibility(View.VISIBLE);
                        holder.branch1.setEnabled(false);
                    }
                });
            }

            else {
                //如果文本有”你死了“，则触发死亡结局
                if(textData.content.contains("你死了")){
                    //改变按钮可见
                    binding.continuebtn.setVisibility(View.INVISIBLE);
                    binding.restartBtn.setVisibility(View.VISIBLE);
                    binding.resultBtn.setVisibility(View.VISIBLE);
                    //获得死亡年龄
                    deathage=nowage;
                    //获得转生碎片
                    int fragments=userDao.findByName(nowusername).fragment;
                    userDao.updatafragment(nowusername,fragments+10);
                    //清空数据
                    TextList.clear();
                }
                else {
                    //否则显示按钮
                    binding.continuebtn.setVisibility(View.VISIBLE);
                }
                //使得分支按钮不可见
                holder.branch1.setVisibility(View.GONE);
                holder.branch2.setVisibility(View.GONE);
            }

        }
        @Override
        public int getItemCount() {
            return TextList.size();
        }
    }

    //实例化适配器1 2
    RebirthAdapter1 rebirthAdapter1;
    RebirthAdapter2 rebirthAdapter2;

//creat生命周期
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
       //实例bingding
        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        //实例化Dao
        userDao = AppDatabase.getInstance(getActivity().getApplicationContext()).userDao();
        userCardDao=AppDatabase.getInstance(getActivity().getApplicationContext()).userCardDao();
        rebithTextDao= AppDatabase.getInstance(getActivity().getApplicationContext()).rebithTextDao();
        //获得用户名
        nowusername=userDao.getloginuser(true).username;
        //获取所有的文本
        AllRebirthText=rebithTextDao.getAll();

        //实例适配器
        rebirthAdapter1=new RebirthAdapter1();
        rebirthAdapter2=new RebirthAdapter2();

        //绑定适配器1,显示角色选择
        binding.rebirthlist.setAdapter(rebirthAdapter1);
        //默认显示底部数据
        binding.rebirthlist.scrollToPosition(rebirthAdapter1.getItemCount()-1);



        //设置布局
        GridLayoutManager gridLayoutManager = new GridLayoutManager(
                getActivity().getApplicationContext(), 1, GridLayoutManager.VERTICAL, false);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        binding.rebirthlist.setLayoutManager(gridLayoutManager);

        //适配器1插入数据
        CharacterList=userCardDao.findByName(nowusername);


        //继续按钮,点击加一岁
        binding.continuebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    //增加一条数据
                TextData textData = new TextData();
                nowage=nowage+1;
                //设置索引
                int TextIndex=0;
                List<Integer> list=new ArrayList<>();
                //根据文本对属性的需要获得相应的索引号
                if (cardPhy==0){
                    TextIndex=0;
                }
                else {
                    for (int i = 1; i < rebithTextDao.fingByAge(nowage).size(); i++) {
                        if (cardPhy >= rebithTextDao.fingByAge(nowage).get(i).NeddPhy) {
                            list.add(i);
                            TextIndex = list.get(new Random().nextInt(list.size()));
                        }
                    }
                }
                //应用索引
                textData.age = rebithTextDao.fingByAge(nowage).get(TextIndex).age;
                textData.content = rebithTextDao.fingByAge(nowage).get(TextIndex).context;
                textData.branch1 = rebithTextDao.fingByAge(nowage).get(TextIndex).branchtext1;
                textData.branch2 = rebithTextDao.fingByAge(nowage).get(TextIndex).branchtext2;
                TextList.add(textData);


                //刷新适配器
                rebirthAdapter2.notifyDataSetChanged();
                //binding.rebirthlist.scrollToPosition(rebirthAdapter2.getItemCount()-1);

            }
        });

        //重新开始按钮
        binding.restartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //改变适配器
                binding.rebirthlist.setAdapter(rebirthAdapter1);
                //设置按钮可见
                binding.characterImg.setVisibility(View.INVISIBLE);
                binding.resultBtn.setVisibility(View.INVISIBLE);
                binding.restartBtn.setVisibility(View.INVISIBLE);
                binding.plzchoose.setVisibility(View.VISIBLE);
                //清空TextList
                TextList.clear();
            }
        });

        //人生总结按钮
        binding.resultBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转到人生总结界面
                //传递数据到人生总结
                Intent intent=new Intent(getActivity(),LifeResultActivity.class);
                intent.putExtra("nowcharacter",nowcharacter);
                intent.putExtra("deathage",deathage);
                intent.putExtra("deathAp",cardAp);
                intent.putExtra("deathIg",cardIg);
                intent.putExtra("deathPhy",cardPhy);
                intent.putExtra("deathUg",cardUg);
                startActivity(intent);
            }
        });

        //角色图片按钮
        binding.characterImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });


        View root = binding.getRoot();
        return root;
    }

    //分支按钮函数
    private  void ApplyBranch(String branch,int giftwater){
        switch (branch) {
            //根据按钮给予事件
            case "宅家派":
                cardIg = cardIg + 10;
                break;
            case "花钱治疗":
                cardPhy=80;
                break;
            case "外出冒险":
                cardPhy = 0;
                break;
            case "多喝热水":
                cardPhy=0;
                break;
            case "去拍卖会":
                userDao.updatagiftwater(nowusername,giftwater+1);
                AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
                        .setTitle("")//标题
                        .setMessage("你获得了一张水晶券！")//内容
                        //肯定按钮逻辑
                        .setPositiveButton("确认", null)
                        .create();
                alertDialog.getWindow().setGravity(Gravity.CENTER);
                alertDialog.getWindow().setType(WindowManager.LayoutParams.FIRST_APPLICATION_WINDOW);
                alertDialog.show();
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}

