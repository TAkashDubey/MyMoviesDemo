/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.mymovie.injection.component

import com.example.mymovie.MyMovieApp
import com.example.mymovie.injection.module.ActivityBindingModule
import com.example.mymovie.injection.module.AppModule
import com.example.mymovie.injection.module.MyMovieModule
import com.example.mymovie.injection.module.ViewModelModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
        modules = [
            AndroidSupportInjectionModule::class,
            AppModule::class,
            ViewModelModule::class,
            ActivityBindingModule::class,
            MyMovieModule::class]
)
interface AppComponent : AndroidInjector<MyMovieApp> {
    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<MyMovieApp>()
}
