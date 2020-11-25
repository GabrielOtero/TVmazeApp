package com.otero.tvmazeapp.ui.di

import com.otero.tvmazeapp.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


@Suppress("RemoveExplicitTypeArguments", "USELESS_CAST")
val tvMazeModule = module {
    viewModel {
        HomeViewModel()
    }
}