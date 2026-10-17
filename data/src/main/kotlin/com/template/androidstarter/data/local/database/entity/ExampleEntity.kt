// EXAMPLE — replace with the project's first real entity. Room needs at least one entity, so delete
// this file only after adding your own (and remove ExampleDao and its line in AppDatabase).
package com.template.androidstarter.data.local.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Example table showing how an entity is declared. Entities stay in this module and are mapped to
 * domain models in `:core:data`.
 */
@Entity(tableName = "example")
data class ExampleEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val title: String,
)
