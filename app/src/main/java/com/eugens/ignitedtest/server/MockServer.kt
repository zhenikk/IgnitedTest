package com.eugens.ignitedtest.server

import com.eugens.ignitedtest.datamodel.ServerData
import io.reactivex.Observable
import java.util.*
import kotlin.collections.ArrayList

class MockServer : IServer {

    companion object {
        const val RANDOM_NUMBERS_COUNT = 6
    }

    override fun getDataObservable(): Observable<ServerData> {
        val time = System.currentTimeMillis()
        return Observable.just(ServerData(getRandomDataList(), time))
    }

    private fun getRandomDataList(): List<Int> {
        val list = ArrayList<Int>()
        val random = Random()
        for (i in 1..RANDOM_NUMBERS_COUNT) {
            list.add(random.nextInt(100))
        }
        return list
    }
}