package io.procrastination.skeleton.model.viewmodels

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ViewModelScheduler @Inject constructor() {

    fun io(): Scheduler = Schedulers.io()

    fun mainThread(): Scheduler = AndroidSchedulers.mainThread()

    fun computation(): Scheduler = Schedulers.computation()

    fun trampoline(): Scheduler = Schedulers.trampoline()

    val observeOn: Scheduler
        get() = mainThread()

    val subscribeOn: Scheduler
        get() = io()
}
