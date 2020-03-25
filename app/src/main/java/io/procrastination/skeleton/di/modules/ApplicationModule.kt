package io.procrastination.skeleton.di.modules

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import io.procrastination.skeleton.R
import io.procrastination.skeleton.view.BonesApplication

@Module
object ApplicationModule {

    @Provides
    @JvmStatic
    fun provideContext(app: BonesApplication): Context = app.applicationContext

    @Provides
    @JvmStatic
    fun provideSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)
    }
}