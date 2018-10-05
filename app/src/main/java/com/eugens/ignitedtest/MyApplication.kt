package com.eugens.ignitedtest

import android.app.Application
import com.eugens.ignitedtest.server.IServer
import com.eugens.ignitedtest.server.MockServer
import com.eugens.ignitedtest.datamodel.DataModel
import com.eugens.ignitedtest.datamodel.IDataModel
import com.eugens.ignitedtest.schedulers.ISchedulerProvider
import com.eugens.ignitedtest.schedulers.SchedulerProvider
import io.reactivex.annotations.NonNull


class MyApplication : Application() {

    @NonNull
    private var dataModel: IDataModel
    @NonNull
    private var serverMock: IServer = MockServer()

    init {
        dataModel = DataModel(serverMock)
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