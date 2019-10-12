package com.sample.moviedb.di

import com.itkacher.okhttpprofiler.OkHttpProfilerInterceptor
import com.sample.moviedb.base.Constants
import com.sample.moviedb.network.ApiMethods
import dagger.Module
import dagger.Provides
import dagger.Reusable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
object AppModule {

    @Provides
    @JvmStatic
    @Reusable
    internal fun providesOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val builder = OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .addInterceptor(OkHttpProfilerInterceptor())

        return builder.build()
    }

    @Reusable
    @Provides
    @JvmStatic
    internal fun provideRetrofitInstance( okHttpClient : OkHttpClient): ApiMethods {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(ApiMethods::class.java)
    }

    /* @Provides
     @JvmStatic
     @Reusable
     internal fun provideApiService(retrofit: Retrofit): ApiMethods {
         return retrofit.create<ApiMethods>(ApiMethods::class.java!!)
     }*/
/*
    @Reusable
    @Provides
    @JvmStatic
    internal fun provideRequestOptions(): RequestOptions {
        return RequestOptions
            .placeholderOf(R.drawable.logo)
            .error(R.drawable.logo)
    }

    @Reusable
    @Provides
    @JvmStatic
    internal fun provideGlideInstance(
        application: Application,
        requestOptions: RequestOptions
    ): RequestManager {
        return Glide.with(application)
            .setDefaultRequestOptions(requestOptions)
    }

    @Reusable
    @Provides
    @JvmStatic
    internal fun provideAppDrawable(application: Application): Drawable? {
        return ContextCompat.getDrawable(application, R.drawable.logo)
    }*/


}
