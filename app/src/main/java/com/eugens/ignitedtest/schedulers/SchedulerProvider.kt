package com.eugens.ignitedtest.schedulers

import android.support.annotation.Nullable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.annotations.NonNull
import io.reactivex.schedulers.Schedulers


class SchedulerProvider private constructor() : ISchedulerProvider {

    @Nullable
    private var INSTANCE: SchedulerProvider? = null

    private object Holder {
        val INSTANCE = SchedulerProvider()
    }

    companion object {
        val instance: SchedulerProvider by lazy { Holder.INSTANCE }
    }

    @NonNull
    override fun computation(): Scheduler {
        return Schedulers.computation()
    }

    @NonNull
    override fun ui(): Scheduler {
        return AndroidSchedulers.mainThread()
    }
}