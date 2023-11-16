package com.example.recyclerretrofitinesmr.data.sources.remote.di;


import com.example.recyclerretrofitinesmr.data.sources.remote.ServiceInterceptor
import com.example.recyclerretrofitinesmr.utils.Constants
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideServiceInterceptor(): ServiceInterceptor = ServiceInterceptor()

    @Singleton
    @Provides
    fun provideHttpClient(serviceInterceptor: ServiceInterceptor): OkHttpClient {
        return OkHttpClient
                .Builder()
                .readTimeout(15, TimeUnit.SECONDS)
                .connectTimeout(15, TimeUnit.SECONDS)
                .addInterceptor(serviceInterceptor)
                .build()
    }

    @Singleton
    @Provides
    fun provideConverterFactory(): GsonConverterFactory =
            GsonConverterFactory.create()

    @Singleton
    @Provides
    fun provideConverterMoshiFactory(): MoshiConverterFactory =
            MoshiConverterFactory.create()

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
    fun provideCurrencyService(retrofit: Retrofit): DogService =
            retrofit.create(DogService::class.java)


}
