package com.eugens.ignitedtest.datamodel

import com.eugens.ignitedtest.db.DbData
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.annotations.NonNull

interface IDataModel {
    @NonNull
    fun getServerData(): Observable<ServerData>
    fun getDbData(): Maybe<DbData>
}