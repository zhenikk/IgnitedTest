package com.eugens.ignitedtest.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.eugens.ignitedtest.datamodel.IDataModel
import com.eugens.ignitedtest.schedulers.ISchedulerProvider


@Suppress("UNCHECKED_CAST")
class MyViewModelFactory(var dataModel: IDataModel, var schedulerProvider: ISchedulerProvider) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MyViewModel(dataModel, schedulerProvider) as T
    }
}