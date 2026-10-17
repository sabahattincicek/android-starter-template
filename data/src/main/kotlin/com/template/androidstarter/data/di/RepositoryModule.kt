package com.template.androidstarter.data.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Hilt module binding the repository interfaces from `:domain` to their implementations in
 * `data/repository`. Add one `@Binds` function per repository, for example:
 *
 * ```
 * @Binds
 * abstract fun bindProfileRepository(impl: DefaultProfileRepository): ProfileRepository
 * ```
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule
