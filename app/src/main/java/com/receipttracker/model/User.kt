package com.receipttracker.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(
        tableName = "user",

        foreignKeys = arrayOf(
        ForeignKey(
            entity = Groups::class,
            parentColumns = arrayOf("group_id"),
            childColumns = arrayOf("user_group_id")
        )
    )
)

data class User (

    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id")
    var id: Int? = null,

    @SerializedName("first_name")
    val firstName: String,

    @SerializedName("last_name")
    val lastName: String,

    @SerializedName("username")
    val username: String,

    @SerializedName("created_at")
    val dateUserAccountCreated: Int? = null,

    @SerializedName("updated_at")
    val dateUserAccountLastUpdated: Int? = null,

    @ColumnInfo(name = "user_group_id")
    @SerializedName("user_group_id")
    val userGroupId: Int
)

