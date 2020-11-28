package com.otero.tvmazeapp.ui.di

import com.otero.tvmazeapp.BuildConfig
import com.otero.tvmazeapp.BuildConfig.API_END_POINT
import com.otero.tvmazeapp.data.ResponseHandler
import com.otero.tvmazeapp.data.TvMazeApi
import com.otero.tvmazeapp.data.datasource.EpisodeRemoteDataSource
import com.otero.tvmazeapp.data.datasource.EpisodeRemoteDataSourceImpl
import com.otero.tvmazeapp.data.datasource.TvShowRemoteDataSource
import com.otero.tvmazeapp.data.datasource.TvShowRemoteDataSourceImpl
import com.otero.tvmazeapp.data.mapper.EpisodeDtoToEpisodeModelMapper
import com.otero.tvmazeapp.data.mapper.TvShowDetailDtoToTvShowDetailModelMapper
import com.otero.tvmazeapp.data.mapper.TvShowDtoToTvShowModelMapper
import com.otero.tvmazeapp.data.mapper.TvShowSearchDtoToTvShowModelMapper
import com.otero.tvmazeapp.data.repository.EpisodeRepository
import com.otero.tvmazeapp.data.repository.EpisodeRepositoryImpl
import com.otero.tvmazeapp.data.repository.TvShowRepository
import com.otero.tvmazeapp.data.repository.TvShowRepositoryImpl
import com.otero.tvmazeapp.domain.usecase.*
import com.otero.tvmazeapp.ui.detail.TvShowDetailViewModel
import com.otero.tvmazeapp.ui.detail.TvShowDetailViewState
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

    viewModel {
        TvShowDetailViewModel(
                get<GetTvShowByIdUseCase>(),
                get<GetEpisodeByShowUseCase>(),
                get<TvShowDetailViewState>()
        )
    }

    factory {
        GetEpisodeByShow(get<EpisodeRepository>()) as GetEpisodeByShowUseCase
    }

    factory {
        GetTvShowById(get<TvShowRepository>()) as GetTvShowByIdUseCase
    }

    factory {
        GetTvShowByPage(get<TvShowRepository>()) as GetTvShowByPageUseCase
    }

    factory {
        GetTvShowByText(get<TvShowRepository>()) as GetTvShowByTextUseCase
    }

    factory {
        EpisodeRepositoryImpl(get<EpisodeRemoteDataSource>()) as EpisodeRepository
    }

    factory {
        TvShowRepositoryImpl(get<TvShowRemoteDataSource>()) as TvShowRepository
    }

    factory {
        TvShowRemoteDataSourceImpl(
                get<TvMazeApi>(),
                get<ResponseHandler>(),
                get<TvShowDtoToTvShowModelMapper>(),
                get<TvShowSearchDtoToTvShowModelMapper>(),
                get<TvShowDetailDtoToTvShowDetailModelMapper>()
        ) as TvShowRemoteDataSource
    }

    factory {
        EpisodeRemoteDataSourceImpl(
                get<TvMazeApi>(),
                get<ResponseHandler>(),
                get<EpisodeDtoToEpisodeModelMapper>()
        ) as EpisodeRemoteDataSource
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
        TvShowDetailDtoToTvShowDetailModelMapper()
    }

    factory {
        EpisodeDtoToEpisodeModelMapper()
    }

    factory {
        HomeViewState()
    }

    factory {
        TvShowDetailViewState()
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