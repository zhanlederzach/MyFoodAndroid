package kz.myfood.di

import io.reactivex.Single
import io.reactivex.SingleSource
import io.reactivex.SingleTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kz.myfood.ui.profile.ProfileViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val networkModule = module {

}

val viewModelModule = module (override = true) {
    viewModel { ProfileViewModel() }
}

fun <T> applySchedulersSingle(): SingleTransformer<T, T> {
    return object : SingleTransformer<T, T> {
        override fun apply(upstream: Single<T>): SingleSource<T> {
            return upstream.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        }
    }
}

val appModules = listOf(networkModule, viewModelModule)

