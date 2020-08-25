package com.mistpaag.lastfm.trainee.di

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.mistpaag.lastfm.trainee.utils.Const
import com.mistpaag.lastfm.trainee.views.main.topArtist.TopArtistViewModel
import com.mistpaag.lastfm.trainee.views.main.TopTrackViewModel
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

import java.util.concurrent.TimeUnit

val appModule = module {
}


val mainVMModule = module {
    viewModel { TopArtistViewModel() }
    viewModel { TopTrackViewModel() }
}





val dataModule = module {
    single {  Moshi.Builder()
        .build() }

    single { Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(get()).asLenient())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
//        .addConverterFactory(ScalarsConverterFactory.create())
        .baseUrl(Const.BASE_URL)
        .client(createOkHttpClient())
        .build()
    }
//    single { get<Retrofit>().create(ApiService::class.java) }
//    single { get<Retrofit>().create(SessionService::class.java) }
//
//    single { Room.databaseBuilder(get(), ProtocolDB::class.java, Const.dbName).build() }
//
//    single { get<ProtocolDB>().mainDao }
//    single { get<ProtocolDB>().sessionDao }

}

fun createOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
        .connectTimeout(1200, TimeUnit.SECONDS)
        .readTimeout(1200, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS)
        .build()
}

