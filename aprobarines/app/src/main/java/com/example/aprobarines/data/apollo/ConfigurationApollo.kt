package org.example.frontend.data.apollo

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.network.okHttpClient
import okhttp3.OkHttpClient

class ConfigurationApollo {

    //TODO: Cuando haya que meter lo de la autentificación, arreglar esto

    fun createApolloClient(): ApolloClient {
        return ApolloClient.Builder()
            .serverUrl("http://localhost:8080/graphql")
            .okHttpClient(
                OkHttpClient.Builder()
                    .build()
            )
            .build()
    }
}
