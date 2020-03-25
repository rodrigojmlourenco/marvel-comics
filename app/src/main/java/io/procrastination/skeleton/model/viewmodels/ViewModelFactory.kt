package io.procrastination.skeleton.model.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.procrastination.skeleton.view.splash.SplashViewModel
import java.lang.IllegalArgumentException
import javax.inject.Inject

class ViewModelFactory
@Inject constructor(
    private val splashViewModel: SplashViewModel
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when(modelClass){
            SplashViewModel::class.java -> splashViewModel as T
            else -> error("Unsupported ViewModel class ${modelClass.simpleName}")
        }
    }
}