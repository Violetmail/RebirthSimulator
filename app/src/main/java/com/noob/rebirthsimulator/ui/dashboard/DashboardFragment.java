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
import com.noob.rebirthsimulator.CardDao;
import com.noob.rebirthsimulator.UserDao;

import com.noob.rebirthsimulator.AppData.User;
import com.noob.rebirthsimulator.CardDao;

import com.noob.rebirthsimulator.databinding.FragmentDashboardBinding;

public class DashboardFragment extends Fragment {
    private FragmentDashboardBinding binding;
    //实例一个cardDao

    UserDao userDao;
    CardDao cardDao;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        //bingding绑定控件
        binding = FragmentDashboardBinding.inflate(inflater, container, false);

        //Dao实例化
        userDao = AppDatabase.getInstance(getActivity().getApplicationContext()).userDao();
        cardDao=AppDatabase.getInstance(getActivity().getApplicationContext()).cardDao();

        //创建卡片
        if (cardDao.getAll().isEmpty()) {
            Card card1 = new Card();
            card1.cardname = "1";
            card1.cardStar = "5";
            card1.cardIg = "100";
            card1.cardPhy = "100";
            card1.cardUg = "100";

            Card card2 = new Card();
            card2.cardname = "2";
            card2.cardStar = "4";
            card2.cardIg = "90";
            card2.cardPhy = "90";
            card2.cardUg = "90";

            Card card3 = new Card();
            card3.cardname = "3";
            card3.cardStar = "3";
            card3.cardIg = "80";
            card3.cardPhy = "80";
            card3.cardUg = "80";

            Card card4 = new Card();
            card4.cardname = "4";
            card4.cardStar = "2";
            card4.cardIg = "70";
            card4.cardPhy = "70";
            card4.cardUg = "70";

            Card card5 = new Card();
            card5.cardname = "5";
            card5.cardStar = "1";
            card5.cardIg = "60";
            card5.cardPhy = "60";
            card5.cardUg = "60";

            Card card6 = new Card();
            card6.cardname = "6";
            card6.cardStar = "5";
            card6.cardIg = "100";
            card6.cardPhy = "100";
            card6.cardUg = "100";

            Card card7 = new Card();
            card7.cardname = "7";
            card7.cardStar = "4";
            card7.cardIg = "90";
            card7.cardPhy = "90";
            card7.cardUg = "90";

            Card card8 = new Card();
            card8.cardname = "8";
            card8.cardStar = "3";
            card8.cardIg = "80";
            card8.cardPhy = "80";
            card8.cardUg = "80";

            Card card9 = new Card();
            card9.cardname = "9";
            card9.cardStar = "2";
            card9.cardIg = "70";
            card9.cardPhy = "70";
            card9.cardUg = "70";

            Card card10 = new Card();
            card10.cardname = "10";
            card10.cardStar = "1";
            card10.cardIg = "60";
            card10.cardPhy = "60";
            card10.cardUg = "60";

            Card card11 = new Card();
            card11.cardname = "11";
            card11.cardStar = "5";
            card11.cardIg = "100";
            card11.cardPhy = "100";
            card11.cardUg = "100";

            Card card12 = new Card();
            card12.cardname = "12";
            card12.cardStar = "4";
            card12.cardIg = "90";
            card12.cardPhy = "90";
            card12.cardUg = "90";

            Card card13 = new Card();
            card13.cardname = "13";
            card13.cardStar = "3";
            card13.cardIg = "80";
            card13.cardPhy = "80";
            card13.cardUg = "80";

            Card card14 = new Card();
            card14.cardname = "14";
            card14.cardStar = "2";
            card14.cardIg = "70";
            card14.cardPhy = "70";
            card14.cardUg = "70";

            Card card15 = new Card();
            card15.cardname = "15";
            card15.cardStar = "1";
            card15.cardIg = "60";
            card15.cardPhy = "60";
            card15.cardUg = "60";

            Card card16 = new Card();
            card16.cardname = "16";
            card16.cardStar = "5";
            card16.cardIg = "100";
            card16.cardPhy = "100";
            card16.cardUg = "100";

            Card card17 = new Card();
            card17.cardname = "17";
            card17.cardStar = "4";
            card17.cardIg = "90";
            card17.cardPhy = "90";
            card17.cardUg = "90";

            Card card18 = new Card();
            card18.cardname = "18";
            card18.cardStar = "3";
            card18.cardIg = "80";
            card18.cardPhy = "80";
            card18.cardUg = "80";

            Card card19 = new Card();
            card19.cardname = "19";
            card19.cardStar = "2";
            card19.cardIg = "70";
            card19.cardPhy = "70";
            card19.cardUg = "70";

            Card card20 = new Card();
            card20.cardname = "20";
            card20.cardStar = "1";
            card20.cardIg = "60";
            card20.cardPhy = "60";
            card20.cardUg = "60";

            Card card21 = new Card();
            card21.cardname = "21";
            card21.cardStar = "5";
            card21.cardIg = "100";
            card21.cardPhy = "100";
            card21.cardUg = "100";

            Card card22 = new Card();
            card22.cardname = "22";
            card22.cardStar = "4";
            card22.cardIg = "90";
            card22.cardPhy = "90";
            card22.cardUg = "90";

            Card card23 = new Card();
            card23.cardname = "23";
            card23.cardStar = "3";
            card23.cardIg = "80";
            card23.cardPhy = "80";
            card23.cardUg = "80";

            Card card24 = new Card();
            card24.cardname = "24";
            card24.cardStar = "2";
            card24.cardIg = "70";
            card24.cardPhy = "70";
            card24.cardUg = "70";

            Card card25 = new Card();
            card25.cardname = "25";
            card25.cardStar = "1";
            card25.cardIg = "60";
            card25.cardPhy = "60";
            card25.cardUg = "60";

            Card card26 = new Card();
            card26.cardname = "26";
            card26.cardStar = "5";
            card26.cardIg = "100";
            card26.cardPhy = "100";
            card26.cardUg = "100";

            Card card27 = new Card();
            card27.cardname = "27";
            card27.cardStar = "4";
            card27.cardIg = "90";
            card27.cardPhy = "90";
            card27.cardUg = "90";

            Card card28 = new Card();
            card28.cardname = "28";
            card28.cardStar = "3";
            card28.cardIg = "80";
            card28.cardPhy = "80";
            card28.cardUg = "80";

            Card card29 = new Card();
            card29.cardname = "29";
            card29.cardStar = "2";
            card29.cardIg = "70";
            card29.cardPhy = "70";
            card29.cardUg = "70";

            Card card30 = new Card();
            card30.cardname = "30";
            card30.cardStar = "1";
            card30.cardIg = "60";
            card30.cardPhy = "60";
            card30.cardUg = "60";
        }
        //cardDao实例化
        cardDao= AppDatabase.getInstance(getActivity().getApplicationContext()).cardDao();
        //数据库判空，并插入数据
       if (cardDao.getAll().isEmpty()){
            Card card1=new Card();
            card1.cardname="xxx";
            //TODO
            cardDao.insertAll(card1);
        }
        View root = binding.getRoot();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}