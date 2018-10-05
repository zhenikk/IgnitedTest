package com.eugens.ignitedtest.datamodel

import io.reactivex.Observable
import io.reactivex.annotations.NonNull

interface IDataModel {
    @NonNull
    fun getServerData(): Observable<ServerData>
}