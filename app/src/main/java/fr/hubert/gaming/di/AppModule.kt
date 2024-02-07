package fr.hubert.gaming.di

import CreateGameViewModel
import androidx.room.Room
import fr.hubert.gaming.data.database.GamingSphereDatabase
import fr.hubert.gaming.data.repository.GameRepository
import fr.hubert.gaming.data.repository.UserRepository
import fr.hubert.gaming.viewModel.LoginViewModel
import fr.hubert.gaming.network.LoginService
import fr.hubert.gaming.repository.LoginRepository
import fr.hubert.gaming.viewModel.CreateUserViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
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


    single {
        Room.databaseBuilder(get(), GamingSphereDatabase::class.java, "GaminDataBase")
            .fallbackToDestructiveMigration()
            .build()
    }

    single { get<GamingSphereDatabase>().userDao() }
    single { get<GamingSphereDatabase>().gameDao() }
    single {
        UserRepository(get()) // Ici, `get()` va automatiquement fournir UserDao
    }
    single {
        GameRepository(get()) // Ici, `get()` va automatiquement fournir UserDao
    }


//    viewModel {
//        LoginViewModel(loginRepository = get())
//
//    }
    viewModel { CreateUserViewModel(get()) }
    viewModel { LoginViewModel(get(), get()) }
    viewModel {
        CreateGameViewModel(get())
    }
}