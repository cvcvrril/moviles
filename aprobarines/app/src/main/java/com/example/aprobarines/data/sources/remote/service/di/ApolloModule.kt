package com.example.aprobarines.data.sources.remote.service.di

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.network.okHttpClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object ApolloModule {

    //TODO: Cuando haya que meter lo de la autentificación, arreglar esto

    @Singleton
    @Provides
    fun createApolloClient(): ApolloClient {
        return ApolloClient.Builder()
            .serverUrl("http://192.168.1.140:8080/graphql")
            .okHttpClient(
                OkHttpClient.Builder()
                    .build()
            )
            .build()
    }
}
