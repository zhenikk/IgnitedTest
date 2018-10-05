package com.eugens.ignitedtest.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.eugens.ignitedtest.datamodel.IDataModel
import com.eugens.ignitedtest.schedulers.ISchedulerProvider
import com.eugens.ignitedtest.utils.DateUtils
import com.eugens.ignitedtest.view.UIData
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.disposables.CompositeDisposable
import java.util.concurrent.TimeUnit


class MyViewModel(var dataModel: IDataModel, var schedulerProvider: ISchedulerProvider) : ViewModel() {

    companion object {
        const val TIMER_DELAY: Long = 30
    }

    private var disposables = CompositeDisposable()
    val dataList: MutableLiveData<List<String>> = MutableLiveData()
    val lastDate = MutableLiveData<String>()
    val isOldData = MutableLiveData<Boolean>()

    init {
        startTimer()
    }

    fun getActualData() {
        disposables.clear()

        disposables.add(
                dataModel.getServerData()
                        .map {
                            val newList = ArrayList<String>(it.listValues.size)
                            for (myInt in it.listValues) {
                                newList.add(myInt.toString())
                            }
                            UIData(newList, DateUtils.getFormattedDate(it.lastDate))
                        }
                        .compose(applySchedulers())
                        .subscribe({
                            startTimer()
                            dataList.postValue(it.dataList)
                            lastDate.postValue(it.formattedDate)
                        }, {
                        }))
    }


    private fun startTimer() {
        isOldData.postValue(null) //When we start timer we mean that current Data is Actual
        disposables.add(
                Observable.timer(TIMER_DELAY, TimeUnit.SECONDS)
                        .compose(applySchedulers())
                        .subscribe({
                            isOldData.postValue(true)
                        }, {
                        })
        )
    }

    override fun onCleared() {
        disposables.clear()
    }

    private fun <T> applySchedulers(): ObservableTransformer<T, T> {
        return ObservableTransformer { upstream ->
            upstream.subscribeOn(schedulerProvider.computation())
                    .observeOn(schedulerProvider.ui())
        }
    }
}