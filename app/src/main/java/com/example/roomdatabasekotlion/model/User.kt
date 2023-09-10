package com.example.roomdatabasekotlion.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey


/**
 * @: This is a feature set It is ,  specific to the following code
 * @exception Entity : this is name table is sql
 *
 * @exception PrimaryKey : A property set to make a column Primary key (autoGenerate = true || false )
 */


// اسم الجدول حسب مبدأ sql
@Entity(tableName = "user_name")
data class User(
    //اعطاء id بشكل تلقائي
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val firstName: String,
    val lastName: String,
    val age: Int,
) : Parcelable {


    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(firstName)
        parcel.writeString(lastName)
        parcel.writeInt(age)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }
}
