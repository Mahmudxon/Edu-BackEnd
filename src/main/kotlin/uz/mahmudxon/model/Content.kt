package uz.mahmudxon.model

import com.google.gson.annotations.SerializedName
import org.jetbrains.exposed.sql.Table

data class Content(
    val id: Int,
    @SerializedName("topic_id")
    val topicId: Int,
    var content: String,
    @SerializedName("order_id")
    val order: Int,
    @SerializedName("type_id")
    val type: Int
) {
    object Type {
        const val TEXT = 1
        const val IMAGE = 2
        const val VIDEO = 3
        const val AUDIO = 4
        const val PDF = 5
    }
}

object Contents : Table("contents") {
    val id = integer("id").autoIncrement()
    val topicId = integer("topic_id")
    val content = text("content")
    val order = integer("order_id")
    val type = integer("type_id")
    override val primaryKey = PrimaryKey(id)
}