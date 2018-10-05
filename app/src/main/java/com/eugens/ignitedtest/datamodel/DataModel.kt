package com.eugens.ignitedtest.datamodel

import com.eugens.ignitedtest.db.DbData
import com.eugens.ignitedtest.db.IDataBase
import com.eugens.ignitedtest.server.IServer
import io.reactivex.*
import io.reactivex.Maybe


class DataModel(var server: IServer, var db: IDataBase) : IDataModel {

    override fun getServerData(): Observable<ServerData> {
        return server.getDataObservable().flatMap {
            db.storeData(DbData(it.listValues, it.lastDate))
            Observable.just(it)
        }
    }

    override fun getDbData(): Maybe<DbData> {
        val fetchedData = db.fetchData()
        return if (fetchedData == null) Maybe.empty<DbData>() else Maybe.just(fetchedData)
    }
}