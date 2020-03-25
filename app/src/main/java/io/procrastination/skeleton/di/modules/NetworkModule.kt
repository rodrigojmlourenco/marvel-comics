package io.procrastination.skeleton.di.modules

import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.Reusable
import io.procrastination.skeleton.BuildConfig
import io.procrastination.skeleton.di.qualifiers.Endpoint
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import timber.log.Timber

@Module
object NetworkModule {

    @Provides
    @JvmStatic
    fun provideOkHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                Timber.tag("OkHttp").d(message)
            }
        })
    }

    @Provides
    @JvmStatic
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {

        if (BuildConfig.DEBUG)
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        else
            loggingInterceptor.level = HttpLoggingInterceptor.Level.NONE

        return OkHttpClient
            .Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    @JvmStatic
    fun provideMoshi(): Moshi {
        return Moshi.Builder().build()
    }

    @Provides
    @JvmStatic
    @Reusable
    fun provideRetrofitBuilder(
        @Endpoint mapiEndpoint: String,
        okHttpClient: OkHttpClient,
        moshi: Moshi
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(mapiEndpoint)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }
}