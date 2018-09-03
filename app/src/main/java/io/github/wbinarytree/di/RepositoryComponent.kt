package io.github.wbinarytree.di

import dagger.Component

/**
 * Created by yaoda on 1/30/18.
 */
@Component(modules = [RepositoryModule::class])
interface RepositoryComponent {
    interface Injectable {
        fun inject(component: RepositoryComponent)
    }
}