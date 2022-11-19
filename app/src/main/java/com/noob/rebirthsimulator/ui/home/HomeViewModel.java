package com.noob.rebirthsimulator.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

//保存碎片的货币
    private MutableLiveData<Integer>fragments;
    public LiveData<Integer> getFragments() {
        if (fragments==null){
            fragments=new MutableLiveData<>();
            fragments.setValue(0);
        }
        return fragments;
    }
    public void setfragments(int n){
        fragments.setValue(n);
    }
    MutableLiveData<String> fragmentstext=new MutableLiveData<>();
    public LiveData<String> getfragmentstext(){
        fragmentstext.setValue(""+getFragments().getValue());
        return fragmentstext;
    }

    //保存抽卡次数
    private MutableLiveData<Integer>drawcounter;
    public LiveData<Integer> getDrawcounter() {
        if (drawcounter==null){
            drawcounter=new MutableLiveData<>();
            drawcounter.setValue(100);
        }
        return drawcounter;
    }
    public void setdrawcounter(int n){
        drawcounter.setValue(n);
    }

    private final MutableLiveData<String> mText=new MutableLiveData<>();
    public LiveData<String> getText() {
        mText.setValue("再抽取"+getDrawcounter().getValue()+"次，必出xxx！");
        return mText;
    }

    public HomeViewModel( ) {

    }

}