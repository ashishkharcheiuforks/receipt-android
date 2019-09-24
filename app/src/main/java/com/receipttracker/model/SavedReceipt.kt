package com.receipttracker.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import org.json.JSONException
import org.json.JSONObject
import java.time.LocalDateTime

/*    val price: String,
    val receiptId: Int = 0,
    val date: String,
    val location: String,
    val service: String*/
@Entity(
    tableName = "receipts",
    foreignKeys = arrayOf(
        ForeignKey(
            entity = User::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("user_id_for_receipt")
        )
    )
)
class SavedReceipt(

    @PrimaryKey(autoGenerate = true)  @ColumnInfo(name = "receipt_id") @NonNull
    var receiptId: Int? = null,

    val dateCreated: Int = 0,
    val dateOfLastUpdate: Int = 0,
    val notes: String = "",
    val merchant: String = "",
    val price: Double = 0.0,
    val date: String = "",

    val location: String = "",

    val receiptServiceType: String


    val purchaseDate: Int,

    val merchant: String,

    val amount: Float,

    val notes: String,

    val receiptCreatedAt: Int,

    val receiptUpdatedAt: Int,

    @ColumnInfo(name = "user_id_for_receipt")
    val userId : Int


)
//Receipt Media (Picture of the receipt). Has the same primarykey as saved receipt.
@Entity(
    tableName = "receipt_media",

    foreignKeys = arrayOf (
        ForeignKey(
            entity = SavedReceipt::class,
            parentColumns = arrayOf("receipt_id"),
            childColumns = arrayOf("receipt_id")
        )
    )
)
class ReceiptMedia(

    @PrimaryKey(autoGenerate = false) @ColumnInfo(name = "id")
    var id: Int? = null,

    @ColumnInfo(name = "receipt_id")
    var receiptId: Int,

    @ColumnInfo(name = "photo_url")
    var receiptPhotoUrl: String,

    @ColumnInfo(name = "description")
    var description: String
)

enum class ReceiptServiceType (service: String) {
    Business("business"),
    Food("food"),
    Shopping("shopping"),
    Travel("travel")
}