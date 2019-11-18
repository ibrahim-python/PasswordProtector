package com.example.passwordprotector;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.example.passwordprotector.database1.PassWord;
import com.example.passwordprotector.database1.PassWordRepository;

import java.util.List;

public class PassWordViewModel extends AndroidViewModel {

    private PassWordRepository mRepository;

    private LiveData<List<PassWord>> mAllPassWords;

    public PassWordViewModel (Application application) {
        super(application);
        mRepository = new PassWordRepository(application);
        mAllPassWords = mRepository.getAllPassWords();
    }

    public LiveData<List<PassWord>> getAllPassWords() { return mAllPassWords; }

    public void insert(PassWord password) { mRepository.insert(password); }

    public void update(PassWord password) { mRepository.update(password); }

    public void delete(PassWord password) { mRepository.delete(password);}



}

