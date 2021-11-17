package com.arsh.lastfmclient.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.arsh.lastfmclient.domain.usecase.home.GetHomeUseCase

class HomeViewModelFactory (
    private val getHomeUseCase: GetHomeUseCase
) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(getHomeUseCase) as T

    }
}