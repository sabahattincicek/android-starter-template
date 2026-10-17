package com.template.androidstarter.core.data.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Hilt module binding the repository interfaces from `:core:domain` to their implementations in
 * `core/data/repository`. Add one `@Binds` function per repository, for example:
 *
 * ```
 * @Binds
 * abstract fun bindProfileRepository(impl: DefaultProfileRepository): ProfileRepository
 * ```
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule
