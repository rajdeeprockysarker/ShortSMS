package com.raj.shortsms

import android.content.Context
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class SmsViewModel
@ViewModelInject
    constructor(/*var sdsdsdsdsd: Context*/@ApplicationContext appContext: Context)
    : ViewModel() {

    var abc= MutableLiveData<Context>()


    var opop= Transformations.switchMap(abc){
       liveData {
           emit(get(appContext))
       }
    }


    fun getValSms(s:Context){
        this.abc.value=s
    }

}