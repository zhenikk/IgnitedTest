package com.eugens.ignitedtest.server

import com.eugens.ignitedtest.datamodel.ServerData
import io.reactivex.Observable

interface IServer{
    fun getDataObservable(): Observable<ServerData>
}