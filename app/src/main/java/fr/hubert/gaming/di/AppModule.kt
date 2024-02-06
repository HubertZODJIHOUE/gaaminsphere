package fr.hubert.gaming.di

import LoginViewModel
import fr.hubert.gaming.network.LoginService
import fr.hubert.gaming.repository.LoginRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {

    single {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }
    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl("https://10.33.1.202:8080/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single<LoginService> {
        get<Retrofit>().create(LoginService::class.java)
    }


    single {
        LoginRepository(loginService = get())
    }


    viewModel {
        LoginViewModel(loginRepository = get())
    }

}