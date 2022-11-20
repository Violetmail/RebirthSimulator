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
import com.noob.rebirthsimulator.AppData.Card;
import com.noob.rebirthsimulator.AppData.RebirthText;
import com.noob.rebirthsimulator.RebithTextDao;
import com.noob.rebirthsimulator.databinding.FragmentNotificationsBinding;
public class NotificationsFragment extends Fragment {

    private FragmentNotificationsBinding binding;
    //实例一个Dao
    RebithTextDao rebithTextDao;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        NotificationsViewModel notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);
        //bingding绑定控件
        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        //
        rebithTextDao= AppDatabase.getInstance(getActivity().getApplicationContext()).rebithTextDao();
        //数据库判空，并插入数据
        if (rebithTextDao.getAll().isEmpty()){
            RebirthText rebirthText1=new RebirthText();
            rebirthText1.age=1;
            //TODO
            rebithTextDao.insertAll(rebirthText1);
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