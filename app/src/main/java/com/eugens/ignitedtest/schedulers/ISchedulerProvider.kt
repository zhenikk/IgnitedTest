package com.eugens.ignitedtest.schedulers

import io.reactivex.Scheduler
import io.reactivex.annotations.NonNull


 interface ISchedulerProvider{
    @NonNull
    fun computation(): Scheduler

    @NonNull
    fun ui(): Scheduler
}