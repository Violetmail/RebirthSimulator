package com.noob.rebirthsimulator.ui.home;

import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateViewModelFactory;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;

import com.noob.rebirthsimulator.MainActivity;
import com.noob.rebirthsimulator.R;
import com.noob.rebirthsimulator.databinding.FragmentHomeBinding;

import java.util.Objects;

public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);


        sharedPreferences= getActivity().getSharedPreferences("drawcard",MODE_PRIVATE);
        editor=sharedPreferences.edit();

        final TextView textView = binding.textHome;
        final TextView waterview=binding.water;
        final TextView currency1view=binding.currency1;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

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
                int water=homeViewModel.getWaternum().getValue();
                int fragments=homeViewModel.getFragments().getValue();
                String[] card;
                if (water-280>=0) {
                    homeViewModel.setWaternum(water-280);
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
                    binding.cardresult1.setText("");
                    binding.cardresult2.setText("");
                    binding.cardresult3.setText("");
                    binding.cardresult4.setText("");
                    binding.cardresult5.setText("");
                    binding.cardresult6.setText("");
                    binding.cardresult7.setText("");
                    binding.cardresult8.setText("");
                    binding.cardresult9.setText("");
                    binding.cardresult10.setText("");
                    homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
                    homeViewModel.getwaternumtext().observe(getViewLifecycleOwner(), waterview::setText);
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
                int counter=homeViewModel.getDrawcounter().getValue();
                String[] card;
                int quality;
                binding.cardresult.setText("");
                if (homeViewModel.getDrawcounter().getValue()-10>0){
                    homeViewModel.setdrawcounter(counter-10);
                }
                else{
                    homeViewModel.setdrawcounter(90+counter);
                }
                homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
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
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
//