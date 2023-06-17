package com.mertoenjosh.questprovider.data.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.mertoenjosh.questprovider.data.database.QpDatabase
import com.mertoenjosh.questprovider.data.database.dao.AuthDao
import com.mertoenjosh.questprovider.data.network.apis.AuthApi
import com.mertoenjosh.questprovider.data.network.apis.QuestionApi
import com.mertoenjosh.questprovider.data.repositories.AuthRepoImpl
import com.mertoenjosh.questprovider.data.repositories.QuestionRepoImpl
import com.mertoenjosh.questprovider.data.util.Constants.BASE_URL
import com.mertoenjosh.questprovider.domain.repositories.AuthRepo
import com.mertoenjosh.questprovider.domain.repositories.QuestionRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {
        val logger = HttpLoggingInterceptor()
        logger.level = HttpLoggingInterceptor.Level.HEADERS
        logger.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
            .readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .addInterceptor(logger)
            .build()
    }

    @OptIn(ExperimentalSerializationApi::class)
    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        val contentType = "application/json".toMediaType()
        val json = Json {
            ignoreUnknownKeys = true
        }

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()
    }

    @Provides
    @Singleton
    fun provideQuestionApi(retrofit: Retrofit): QuestionApi {
        return retrofit.create(QuestionApi::class.java)
    }

    @Provides
    @Singleton
    fun provideAuthApi(retrofit: Retrofit) : AuthApi {
        return retrofit.create(AuthApi::class.java)
    }
    @Provides
    @Singleton
    fun providesAuthRepo(
        authApi: AuthApi,
        authDao: AuthDao
    ): AuthRepo {
        return AuthRepoImpl(authApi, authDao)
    }

    @Provides
    @Singleton
    fun providesQuestionRepo(
        questionApi: QuestionApi,
        qpDatabase: QpDatabase
    ): QuestionRepo {
        return QuestionRepoImpl(questionApi, qpDatabase)
    }
}