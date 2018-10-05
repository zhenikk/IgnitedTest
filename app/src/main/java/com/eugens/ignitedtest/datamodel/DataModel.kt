package com.eugens.ignitedtest.datamodel

import com.eugens.ignitedtest.server.IServer
import io.reactivex.Observable


class DataModel(var server: IServer) : IDataModel {

    override fun getServerData(): Observable<ServerData> {
        return server.getDataObservable()
    }
}