package com.noob.rebirthsimulator.ui.home;

import android.widget.Button;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {
    public SavedStateHandle handle;

    private MutableLiveData<Integer>waternum;
    public LiveData<Integer> getWaternum() {
        if (waternum==null){
            waternum=new MutableLiveData<>();
            waternum.setValue(30000);
        }
        return waternum;
    }
    public void setWaternum(int n){
        waternum.setValue(n);
    }
    private final MutableLiveData<String> waternumText=new MutableLiveData<>();
    public LiveData<String> getwaternumtext() {
        waternumText.setValue(""+getWaternum().getValue());
        return waternumText;
    }

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

    private  MutableLiveData<String[]> usercard;
    public LiveData<String[]> getUsercard() {
        return usercard;
    }

    public void setUsercard(String[] cardname) {
        usercard.setValue(cardname);
    }


    private final MutableLiveData<String> mText=new MutableLiveData<>();
    public LiveData<String> getText() {
        mText.setValue("再抽取"+getDrawcounter().getValue()+"次，必出xxx！");
        return mText;
    }

    public HomeViewModel( ) {


    }

}