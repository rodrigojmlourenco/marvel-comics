package io.procrastination.skeleton.view.splash

import android.os.Handler
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SplashViewModel
@Inject constructor() : ViewModel() {

    internal val event = MutableLiveData<Event?>()

    init {
        Handler().postDelayed({ event.postValue(Event.Proceed) }, TimeUnit.SECONDS.toMillis(3))
    }

    internal fun consumeEvent() {
        event.postValue(null)
    }

    internal sealed class Event {
        object Proceed : Event()
        data class Error(val message: String, val isCritical: Boolean = false) : Event()
    }
}