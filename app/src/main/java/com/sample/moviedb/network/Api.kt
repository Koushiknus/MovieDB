package com.sample.moviedb.network

import com.itkacher.okhttpprofiler.OkHttpProfilerInterceptor
import com.sample.moviedb.BuildConfig
import okhttp3.ConnectionSpec
import okhttp3.OkHttpClient
import okhttp3.internal.Util
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit

class Api {

    companion object{
        private val DEFAULT_CONNECTION_SPECS = Util.immutableList(
            ConnectionSpec.MODERN_TLS, ConnectionSpec.CLEARTEXT
        )
        private const val BASE_URL = "https://api.themoviedb.org/3/movie/"

        fun getApiService(): ApiMethods {

            return createService(ApiMethods::class.java)
        }

        private fun <S> createService(serviceClass: Class<S>): S {

            val client = OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .connectionSpecs(DEFAULT_CONNECTION_SPECS)

            client.interceptors().add(HeaderInterceptor())

            if (BuildConfig.DEBUG) {
                val interceptor = HttpLoggingInterceptor()
                interceptor.level = HttpLoggingInterceptor.Level.BODY
                client.addInterceptor(interceptor)
                client.addInterceptor(OkHttpProfilerInterceptor())
            }

            val builder = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

            val adapter = builder.newBuilder().build()
            return adapter.create(serviceClass)
        }

        private class HeaderInterceptor : okhttp3.Interceptor {

            @Throws(IOException::class)
            override fun intercept(chain: okhttp3.Interceptor.Chain): okhttp3.Response {
                var request = chain.request()
                request = request.newBuilder()
                    .build()
                return chain.proceed(request)
            }

        }
    }




}