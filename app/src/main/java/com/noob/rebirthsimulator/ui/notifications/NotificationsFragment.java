package com.noob.rebirthsimulator.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.noob.rebirthsimulator.AppData.AppDatabase;
import com.noob.rebirthsimulator.AppData.RebirthText;
import com.noob.rebirthsimulator.AppData.User;
import com.noob.rebirthsimulator.RebithTextDao;
import com.noob.rebirthsimulator.databinding.FragmentNotificationsBinding;

public class NotificationsFragment extends Fragment {

    private FragmentNotificationsBinding binding;
    //
    RebithTextDao rebithTextDao;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        //数据库导入
        rebithTextDao= AppDatabase.getInstance(getActivity().getApplicationContext()).rebithTextDao();;


        if (rebithTextDao.getAll().isEmpty()) {


            RebirthText rebithText0 = new RebirthText();
            rebithText0.age=0;
            rebithText0.context="birth";
            rebithText0.specialtext="not birth";}

            RebirthText rebithText1 = new RebirthText();
            rebithText1.age=5;
            rebithText1.context="play";
            rebithText1.specialtext="not play";
            rebithTextDao.insertAll(rebithText1);

        RebirthText rebithText2 = new RebirthText();
        rebithText2.age=10;
        rebithText2.context="study";
        rebithText2.specialtext="";
        rebithTextDao.insertAll(rebithText2);

        RebirthText rebithText3 = new RebirthText();
        rebithText3.age=15;
        rebithText3.context="study,accident";
        rebithText3.specialtext="";
        rebithTextDao.insertAll(rebithText3);

        RebirthText rebithText4 = new RebirthText();
        rebithText4.age=20;
        rebithText4.context="work,accident,study";
        rebithText4.specialtext="";
        rebithTextDao.insertAll(rebithText4);

        RebirthText rebithText5 = new RebirthText();
        rebithText5.age=25;
        rebithText5.context="accident,work,marry";
        rebithText5.specialtext="";
        rebithTextDao.insertAll(rebithText5);
        RebirthText rebithText6 = new RebirthText();

        rebithText6.age=30;
        rebithText6.context="accident,work,marry";
        rebithText6.specialtext="";
        rebithTextDao.insertAll(rebithText6);

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


        View root = binding.getRoot();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}

