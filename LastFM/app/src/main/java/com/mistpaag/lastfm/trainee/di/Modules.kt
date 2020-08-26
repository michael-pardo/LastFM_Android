package com.mistpaag.lastfm.trainee.di

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.mistpaag.lastfm.trainee.data.remote.ApiService
import com.mistpaag.lastfm.trainee.data.repository.Repository
import com.mistpaag.lastfm.trainee.utils.Const
import com.mistpaag.lastfm.trainee.views.main.topArtist.TopArtistViewModel
import com.mistpaag.lastfm.trainee.views.main.TopTrackViewModel
import com.mistpaag.lastfm.trainee.views.main.detailWebview.DetailWebViewViewModel
import okhttp3.OkHttpClient
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import java.util.concurrent.TimeUnit

val appModule = module {
}


val mainVMModule = module {
    viewModel { TopArtistViewModel(get(), get()) }
    viewModel { TopTrackViewModel() }
    viewModel { DetailWebViewViewModel() }
}







val dataModule = module {
//    single {  Moshi.Builder()
//        .build() }

    single { Retrofit.Builder()
//        .addConverterFactory(MoshiConverterFactory.create(get()).asLenient())
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
//        .addConverterFactory(ScalarsConverterFactory.create())
        .baseUrl(Const.URL_BASE)
        .client(createOkHttpClient())
        .build()
    }

    single { get<Retrofit>().create(ApiService::class.java) }
    single { Repository(get()) }

}

fun createOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
        .connectTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS)
        .build()
}

