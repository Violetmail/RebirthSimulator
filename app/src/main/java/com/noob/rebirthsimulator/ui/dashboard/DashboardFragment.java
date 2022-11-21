package com.noob.rebirthsimulator.ui.dashboard;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.noob.rebirthsimulator.AppData.AppDatabase;
import com.noob.rebirthsimulator.CardDao;
import com.noob.rebirthsimulator.R;
import com.noob.rebirthsimulator.UserCardDao;
import com.noob.rebirthsimulator.UserDao;

import com.noob.rebirthsimulator.databinding.FragmentDashboardBinding;

import java.util.List;

public class DashboardFragment extends Fragment {
    private FragmentDashboardBinding binding;
    //实例cardDao
    UserDao userDao;
    CardDao cardDao;
    UserCardDao userCardDao;

    //用户名
    String nowusername;
    //所有卡牌列表
    List Allcard;
    //用户卡牌列表
    List mydrawedcard;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        //bingding绑定控件
        binding = FragmentDashboardBinding.inflate(inflater, container, false);

        //Dao实例化
        userDao = AppDatabase.getInstance(getActivity().getApplicationContext()).userDao();
        cardDao=AppDatabase.getInstance(getActivity().getApplicationContext()).cardDao();
        userCardDao=AppDatabase.getInstance(getActivity().getApplicationContext()).userCardDao();



        //获得用户名
        nowusername=userDao.getloginuser(true).username;

        //显示碎片数
        binding.fragmentstext.setText("碎片："+userDao.findByName(nowusername).fragment);

        //卡列表
        Allcard=cardDao.getAllcardname();
        mydrawedcard=userCardDao.getAllcardname(nowusername);

        //全部按钮功能
        binding.allcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //变色
                binding.allcard.setBackgroundColor(getResources().getColor(R.color.purple_500));
                binding.mycard.setBackgroundColor(getResources().getColor(R.color.gray));
                //按钮一
                if (mydrawedcard.contains(Allcard.get(0))){
                    binding.btn1.setVisibility(View.VISIBLE);
                    binding.btn1.setBackgroundColor(getResources().getColor(R.color.CornflowerBlue));

                }
                else {
                    binding.btn1.setVisibility(View.VISIBLE);
                }
                //按钮二
                if (mydrawedcard.contains(Allcard.get(1))){
                    binding.btn2.setVisibility(View.VISIBLE);
                    binding.btn2.setBackgroundColor(getResources().getColor(R.color.CornflowerBlue));
                }
                else {
                    binding.btn2.setVisibility(View.VISIBLE);
                }
                //按钮3
                if (mydrawedcard.contains(Allcard.get(2))){
                    binding.btn3.setVisibility(View.VISIBLE);
                    binding.btn3.setBackgroundColor(getResources().getColor(R.color.CornflowerBlue));
                }
                else {
                    binding.btn3.setVisibility(View.VISIBLE);
                }
                //按钮4
                if (mydrawedcard.contains(Allcard.get(3))){
                    binding.btn4.setVisibility(View.VISIBLE);
                    binding.btn4.setBackgroundColor(getResources().getColor(R.color.CornflowerBlue));
                }
                else {
                    binding.btn4.setVisibility(View.VISIBLE);
                }

            }
        });

        //初始默认点击一次全部显示图鉴
        binding.allcard.callOnClick();

        //已拥有按钮功能
        binding.mycard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //变色
                binding.allcard.setBackgroundColor(getResources().getColor(R.color.gray));
                binding.mycard.setBackgroundColor(getResources().getColor(R.color.purple_500));
                //按钮一
                if (mydrawedcard.contains(Allcard.get(0))){
                    binding.btn1.setVisibility(View.VISIBLE);
                    binding.btn1.setBackgroundColor(getResources().getColor(R.color.CornflowerBlue));

                }
                else {
                    binding.btn1.setVisibility(View.GONE);
                }
                //按钮二
                if (mydrawedcard.contains(Allcard.get(1))){
                    binding.btn2.setVisibility(View.VISIBLE);
                    binding.btn2.setBackgroundColor(getResources().getColor(R.color.CornflowerBlue));
                }
                else {
                    binding.btn2.setVisibility(View.GONE);
                }
                //按钮3
                if (mydrawedcard.contains(Allcard.get(2))){
                    binding.btn3.setVisibility(View.VISIBLE);
                    binding.btn3.setBackgroundColor(getResources().getColor(R.color.CornflowerBlue));
                }
                else {
                    binding.btn3.setVisibility(View.GONE);
                }
                //按钮4
                if (mydrawedcard.contains(Allcard.get(3))){
                    binding.btn4.setVisibility(View.VISIBLE);
                    binding.btn4.setBackgroundColor(getResources().getColor(R.color.CornflowerBlue));
                }
                else {
                    binding.btn4.setVisibility(View.GONE);
                }

            }
        });

        View root = binding.getRoot();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}