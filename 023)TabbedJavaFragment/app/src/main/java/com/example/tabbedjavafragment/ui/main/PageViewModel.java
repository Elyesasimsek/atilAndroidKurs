package com.example.tabbedjavafragment.ui.main;

import static androidx.lifecycle.Transformations.map;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PageViewModel extends ViewModel {

    private  MutableLiveData<String> nameInput = new MutableLiveData<>();
    public  void setName(String name){
        nameInput.setValue(name);
    }

    public LiveData<String> getName(){
        return nameInput;
    }
}