package com.noob.rebirthsimulator.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.noob.rebirthsimulator.AppData.AppDatabase;
import com.noob.rebirthsimulator.AppData.Card;
import com.noob.rebirthsimulator.AppData.User;
import com.noob.rebirthsimulator.CardDao;
import com.noob.rebirthsimulator.databinding.FragmentDashboardBinding;

public class DashboardFragment extends Fragment {
    private FragmentDashboardBinding binding;
    //实例一个cardDao
    CardDao cardDao;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        //bingding绑定控件
        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        //cardDao实例化
        cardDao= AppDatabase.getInstance(getActivity().getApplicationContext()).cardDao();
        //数据库判空，并插入数据
       /* if (cardDao.getAll().isEmpty()){
            Card card1=new Card();
            card1.cardname="xxx";
            //TODO
            cardDao.insertAll(card1);
        }*/
        View root = binding.getRoot();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}