package ru.mvvm.data.remote.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*

data class ContactRes(
    @SerializedName("id")
    @Expose
    val id: String,

    @SerializedName("name")
    @Expose
    val name: String? = null,

    @SerializedName("phone")
    @Expose
    val phone: String? = null,

    @SerializedName("height")
    @Expose
    val height: Float? = null,

    @SerializedName("biography")
    @Expose
    val biography: String? = null,

    @SerializedName("temperament")
    @Expose
    val temperament: Temperament,

    @SerializedName("educationPeriod")
    @Expose
    val educationPeriod: Period? = null
) {
    enum class Temperament(
        val type: String
    ) {
        @SerializedName("melancholic")
        Melancholic("melancholic"),
        @SerializedName("phlegmatic")
        Phlegmatic("phlegmatic"),
        @SerializedName("sanguine")
        Sanguine("sanguine"),
        @SerializedName("choleric")
        Choleric("choleric")
    }

    data class Period(
        @SerializedName("start")
        @Expose
        val start: Date,

        @SerializedName("end")
        @Expose
        val end: Date
    )
}