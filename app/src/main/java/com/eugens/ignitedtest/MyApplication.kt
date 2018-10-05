package com.eugens.ignitedtest

import android.app.Application
import com.eugens.ignitedtest.server.IServer
import com.eugens.ignitedtest.server.MockServer
import com.eugens.ignitedtest.datamodel.DataModel
import com.eugens.ignitedtest.datamodel.IDataModel
import com.eugens.ignitedtest.db.IDataBase
import com.eugens.ignitedtest.db.MyDataBase
import com.eugens.ignitedtest.schedulers.ISchedulerProvider
import com.eugens.ignitedtest.schedulers.SchedulerProvider
import com.google.gson.Gson
import io.reactivex.annotations.NonNull


class MyApplication : Application() {

    @NonNull
    private lateinit var dataModel: IDataModel

    override fun onCreate() {
        super.onCreate()
        val dataBase: IDataBase = MyDataBase(this, Gson())
        val serverMock: IServer = MockServer()
        dataModel = DataModel(serverMock, dataBase)
    }

    @NonNull
    fun getDataModel(): IDataModel {
        return dataModel
    }

    @NonNull
    fun getSchedulerProvider(): ISchedulerProvider {
        return SchedulerProvider.instance
    }
}