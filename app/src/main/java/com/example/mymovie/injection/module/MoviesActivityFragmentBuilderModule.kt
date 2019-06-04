package com.example.mymovie.injection.module

import com.example.mymovie.ui.movies.ComingSoonFragment
import com.example.mymovie.ui.movies.NowShowingFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MoviesActivityFragmentBuilderModule {

    @ContributesAndroidInjector
    internal abstract fun comingSoonFragment(): ComingSoonFragment

    @ContributesAndroidInjector
    internal abstract fun nowShowingFragment(): NowShowingFragment
}