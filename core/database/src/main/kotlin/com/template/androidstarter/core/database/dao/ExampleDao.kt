// EXAMPLE — replace together with ExampleEntity.
package com.template.androidstarter.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.template.androidstarter.core.database.entity.ExampleEntity
import kotlinx.coroutines.flow.Flow

/**
 * Example DAO showing an observable query and a write operation.
 */
@Dao
interface ExampleDao {

    /** Emits all rows, and again whenever the table changes. */
    @Query("SELECT * FROM example")
    fun observeAll(): Flow<List<ExampleEntity>>

    /** Inserts the rows, or updates the ones whose id already exists. */
    @Upsert
    suspend fun upsert(entities: List<ExampleEntity>)
}
