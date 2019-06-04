package com.example.mymovie.data.remote.response


import android.os.Parcel
import android.os.Parcelable
import com.example.mymovie.ui.utils.Utils
import com.google.gson.annotations.SerializedName

class Upcoming() : Parcelable {
    @SerializedName("age_category")
    var ageCategory: String? = ""
    @SerializedName("description")
    var description: String? = ""
    @SerializedName("genre_ids")
    var genreIds: List<GenreId?>? = listOf()
    @SerializedName("id")
    var id: String? = ""
    @SerializedName("poster_path")
    var posterPath: String? = ""
    @SerializedName("presale_flag")
    var presaleFlag: Int? = 0
    @SerializedName("rate")
    var rate: Float? = 0.0F
    @SerializedName("title")
    var title: String? = ""
    @SerializedName("release_date")
    var releaseDate: Int? = 0


    var dateString: String? = ""
        get() {
            return releaseDate?.toLong()?.let { Utils.getDate(it, "DD MMM yyyy") }
        }

    var rateS: String? = rate?.toString()
        get() {
            return rate?.toString()
        }

    var rateAtBar: Float? = rate
        get() {
            return rate?.div(2)
        }

    constructor(parcel: Parcel) : this() {
        ageCategory = parcel.readString()
        description = parcel.readString()
        id = parcel.readString()
        posterPath = parcel.readString()
        presaleFlag = parcel.readValue(Int::class.java.classLoader) as? Int
        rate = parcel.readValue(Float::class.java.classLoader) as? Float
        title = parcel.readString()
        releaseDate = parcel.readValue(Int::class.java.classLoader) as? Int
    }

    override fun equals(other: Any?): Boolean {
        return this.id == (other as Upcoming?)?.id
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(ageCategory)
        parcel.writeString(description)
        parcel.writeString(id)
        parcel.writeString(posterPath)
        parcel.writeValue(presaleFlag)
        parcel.writeValue(rate)
        parcel.writeString(title)
        parcel.writeValue(releaseDate)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Upcoming> {
        override fun createFromParcel(parcel: Parcel): Upcoming {
            return Upcoming(parcel)
        }

        override fun newArray(size: Int): Array<Upcoming?> {
            return arrayOfNulls(size)
        }
    }

    override fun toString(): String {
        return "ageCategory : $ageCategory, description : $description,posterPath :$posterPath, presaleFlag : $presaleFlag,rate :$rate,rates : $rateS,title : $title," +
                "releaseDate : $releaseDate,dateString : $dateString"
    }

    override fun hashCode(): Int {
        var result = ageCategory?.hashCode() ?: 0
        result = 31 * result + (description?.hashCode() ?: 0)
        result = 31 * result + (genreIds?.hashCode() ?: 0)
        result = 31 * result + (id?.hashCode() ?: 0)
        result = 31 * result + (posterPath?.hashCode() ?: 0)
        result = 31 * result + (presaleFlag ?: 0)
        result = 31 * result + (rate?.hashCode() ?: 0)
        result = 31 * result + (title?.hashCode() ?: 0)
        result = 31 * result + (releaseDate ?: 0)
        return result
    }
}