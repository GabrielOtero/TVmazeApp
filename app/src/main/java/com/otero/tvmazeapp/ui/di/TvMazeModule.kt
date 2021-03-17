package com.otero.tvmazeapp.ui.di

import androidx.room.Room
import com.otero.tvmazeapp.BuildConfig
import com.otero.tvmazeapp.BuildConfig.API_END_POINT
import com.otero.tvmazeapp.data.ResponseHandler
import com.otero.tvmazeapp.data.TvMazeApi
import com.otero.tvmazeapp.data.database.AppDatabase
import com.otero.tvmazeapp.data.datasource.*
import com.otero.tvmazeapp.data.mapper.*
import com.otero.tvmazeapp.data.repository.EpisodeRepository
import com.otero.tvmazeapp.data.repository.EpisodeRepositoryImpl
import com.otero.tvmazeapp.data.repository.TvShowRepository
import com.otero.tvmazeapp.data.repository.TvShowRepositoryImpl
import com.otero.tvmazeapp.data.utils.DefaultDispatcherProvider
import com.otero.tvmazeapp.data.utils.DispatcherProvider
import com.otero.tvmazeapp.domain.usecase.*
import com.otero.tvmazeapp.domain.usecase.interfaces.*
import com.otero.tvmazeapp.ui.detail.TvShowDetailViewModel
import com.otero.tvmazeapp.ui.detail.TvShowDetailViewState
import com.otero.tvmazeapp.ui.favorite.FavoriteViewModel
import com.otero.tvmazeapp.ui.favorite.FavoriteViewState
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
const val KOIN_ROOM = "KOIN_ROOM"

@Suppress("RemoveExplicitTypeArguments", "USELESS_CAST")
val tvMazeModule = module {
    viewModel {
        HomeViewModel(
                get<GetTvShowPagedUseCase>(),
                get<GetTvShowByTextUseCase>(),
                get<HomeViewState>()
        )
    }

    viewModel {
        TvShowDetailViewModel(
            get<GetTvShowByIdUseCase>(),
            get<GetEpisodeByShowUseCase>(),
            get<SaveFavoriteTvShowUseCase>(),
            get<GetFavoriteTvShowByIdUseCase>(),
            get<RemoveFavoriteTvShowByIdUseCase>(),
            get<TvShowDetailViewState>()
        )
    }

    viewModel {
        FavoriteViewModel(
            get<GetFavoriteListUseCase>(),
            get<FavoriteViewState>()
        )
    }

    factory {
        GetEpisodeByShow(
            get<EpisodeRepository>(),
            get<DispatcherProvider>()
        ) as GetEpisodeByShowUseCase
    }

    factory {
        RemoveFavoriteTvShowById(
            get<TvShowRepository>(),
            get<DispatcherProvider>()
        ) as RemoveFavoriteTvShowByIdUseCase
    }

    factory {
        GetFavoriteTvShowById(
            get<TvShowRepository>(),
            get<DispatcherProvider>()
        ) as GetFavoriteTvShowByIdUseCase
    }

    factory {
        GetFavoriteList(
            get<TvShowRepository>(),
            get<DispatcherProvider>()
        ) as GetFavoriteListUseCase
    }

    factory {
        SaveFavoriteTvShow(
            get<TvShowRepository>(),
            get<DispatcherProvider>()
        ) as SaveFavoriteTvShowUseCase
    }

    factory {
        GetTvShowById(get<TvShowRepository>()) as GetTvShowByIdUseCase
    }

    factory {
        GetTvShowPaged(get<TvShowRepository>()) as GetTvShowPagedUseCase
    }

    factory {
        GetTvShowByText(get<TvShowRepository>()) as GetTvShowByTextUseCase
    }

    factory {
        DefaultDispatcherProvider() as DispatcherProvider
    }

    factory {
        EpisodeRepositoryImpl(get<EpisodeRemoteDataSource>()) as EpisodeRepository
    }

    factory {
        TvShowRepositoryImpl(
            get<TvShowRemoteDataSource>(),
            get<TvShowLocalDataSource>(),
            get<TvShowPagingDataSource>()
        ) as TvShowRepository
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
        TvShowLocalDataSourceImpl(
            get<AppDatabase>(),
            get<TvShowDboToTvShowModelMapper>()
        ) as TvShowLocalDataSource
    }

    factory {
        EpisodeRemoteDataSourceImpl(
            get<TvMazeApi>(),
            get<ResponseHandler>(),
            get<EpisodeDtoToEpisodeModelMapper>()
        ) as EpisodeRemoteDataSource
    }

    factory {
        TvShowPagingDataSourceImpl(
            get<TvMazeApi>(),
            get<TvShowDtoToTvShowModelMapper>(),
            get<ResponseHandler>()
        ) as TvShowPagingDataSource
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
        TvShowDboToTvShowModelMapper()
    }

    factory {
        HomeViewState()
    }

    factory {
        TvShowDetailViewState()
    }

    factory {
        FavoriteViewState()
    }

    factory {
        get<Retrofit>(named(KOIN_RETROFIT)).create(TvMazeApi::class.java)
    }

    factory {
        get<AppDatabase>(named(KOIN_ROOM))
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

    single(named(KOIN_ROOM)) {
        Room.databaseBuilder(
            get(),
            AppDatabase::class.java,
            "database"
        ).build()
    }
}