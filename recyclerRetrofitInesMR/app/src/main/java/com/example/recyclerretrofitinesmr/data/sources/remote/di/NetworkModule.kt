package com.example.recyclerretrofitinesmr.data.sources.remote.di;


import com.example.recyclerretrofitinesmr.data.sources.remote.DirectorService
import com.example.recyclerretrofitinesmr.data.sources.remote.ServiceInterceptor
import com.example.recyclerretrofitinesmr.utils.Constants
import com.google.gson.GsonBuilder
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideServiceInterceptor(): ServiceInterceptor = ServiceInterceptor()

    @Singleton
    @Provides
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient
                .Builder()
                .readTimeout(15, TimeUnit.SECONDS)
                .connectTimeout(15, TimeUnit.SECONDS)
                .build()
    }

    @Singleton
    @Provides
    fun provideConverterFactory(): GsonConverterFactory =
            GsonConverterFactory.create(GsonBuilder().setLenient().create())
    @Singleton
    @Provides
    fun provideRetrofit(
            okHttpClient:OkHttpClient,
            gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(gsonConverterFactory)
                .build()
    }

    @Singleton
    @Provides
    fun provideCurrencyService(retrofit: Retrofit): DirectorService =
            retrofit.create(DirectorService::class.java)


}
