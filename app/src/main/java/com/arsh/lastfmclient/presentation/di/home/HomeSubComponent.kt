package com.arsh.lastfmclient.presentation.di.home

import com.arsh.lastfmclient.presentation.di.details.DetailsScope
import com.arsh.lastfmclient.presentation.home.HomeActivity
import dagger.Subcomponent


@HomeScope
@Subcomponent(modules = [HomeModule::class])
interface HomeSubComponent {
    fun inject(homeActivity: HomeActivity)

    @Subcomponent.Factory
    interface Factory{
        fun create() : HomeSubComponent
    }
}