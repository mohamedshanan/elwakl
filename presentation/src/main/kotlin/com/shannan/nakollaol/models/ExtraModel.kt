package com.shannan.nakollaol.models

import android.os.Parcel
import com.shannan.nakollaol.core.platform.KParcelable
import com.shannan.nakollaol.core.platform.parcelableCreator
import com.shannan.nakollaol.domain.extension.empty
import com.shannan.nakollaol.domain.interactor.Extra as DExtra

data class ExtraModel(val name: String,
                      val photoUrl: String?,
                      val price: Double) : KParcelable {

    private constructor(p: Parcel) : this(name = p.readString(), photoUrl = p.readString(), price = p.readDouble())

    override fun writeToParcel(dest: Parcel, flags: Int) {
        with(dest) {
            writeString(name)
            writeString(photoUrl)
            writeDouble(price)
        }
    }

    companion object {
        fun empty() = ExtraModel(String.empty(), null, 0.0)
        fun fromDomain(dExtra: DExtra): ExtraModel = ExtraModel(dExtra.name, dExtra.photoUrl, dExtra.price)
        @JvmField
        val CREATOR = parcelableCreator(::ExtraModel)

    }
}

