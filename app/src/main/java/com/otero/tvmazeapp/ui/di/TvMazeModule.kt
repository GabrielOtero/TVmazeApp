package com.otero.tvmazeapp.ui.di

import com.otero.tvmazeapp.BuildConfig
import com.otero.tvmazeapp.BuildConfig.API_END_POINT
import com.otero.tvmazeapp.data.ResponseHandler
import com.otero.tvmazeapp.data.TvMazeApi
import com.otero.tvmazeapp.data.datasource.TvShowRemoteDataSource
import com.otero.tvmazeapp.data.datasource.TvShowRemoteDataSourceImpl
import com.otero.tvmazeapp.data.mapper.TvShowDtoToTvShowModelMapper
import com.otero.tvmazeapp.data.mapper.TvShowSearchDtoToTvShowModelMapper
import com.otero.tvmazeapp.data.repository.TvShowRepository
import com.otero.tvmazeapp.data.repository.TvShowRepositoryImpl
import com.otero.tvmazeapp.domain.usecase.GetTvShowByTextUseCase
import com.otero.tvmazeapp.domain.usecase.GetTvShowByPage
import com.otero.tvmazeapp.domain.usecase.GetTvShowByPageUseCase
import com.otero.tvmazeapp.domain.usecase.GetTvShowByText
import com.otero.tvmazeapp.ui.home.HomeViewModel
import com.otero.tvmazeapp.ui.home.HomeViewState
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val KOIN_RETROFIT = "KOIN_RETROFIT"

@Suppress("RemoveExplicitTypeArguments", "USELESS_CAST")
val tvMazeModule = module {
    viewModel {
        HomeViewModel(
            get<GetTvShowByPageUseCase>(),
            get<GetTvShowByTextUseCase>(),
            get<HomeViewState>()
        )
    }

    factory {
        GetTvShowByPage(get<TvShowRepository>()) as GetTvShowByPageUseCase
    }

    factory {
        GetTvShowByText(get<TvShowRepository>()) as GetTvShowByTextUseCase
    }

    factory {
        TvShowRepositoryImpl(get<TvShowRemoteDataSource>()) as TvShowRepository
    }

    factory {
        TvShowRemoteDataSourceImpl(
            get<TvMazeApi>(),
            get<ResponseHandler>(),
            get<TvShowDtoToTvShowModelMapper>(),
            get<TvShowSearchDtoToTvShowModelMapper>()
        ) as TvShowRemoteDataSource
    }

    factory {
        ResponseHandler()
    }

    factory {
        TvShowDtoToTvShowModelMapper()
    }

    factory {
        TvShowSearchDtoToTvShowModelMapper()
    }

    factory {
        HomeViewState()
    }

    factory {
        get<Retrofit>(named(KOIN_RETROFIT)).create(TvMazeApi::class.java)
    }

    single(named(KOIN_RETROFIT)) {
        Retrofit.Builder()
            .baseUrl(API_END_POINT)
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor().apply {
                        if (BuildConfig.DEBUG) level = HttpLoggingInterceptor.Level.BODY
                    })
                    .build()
            )
            .build()
    }
}