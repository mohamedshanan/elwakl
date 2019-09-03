package com.shannan.nakollaol.models

import android.os.Parcel
import com.shannan.nakollaol.core.extension.empty
import com.shannan.nakollaol.core.platform.KParcelable
import com.shannan.nakollaol.core.platform.parcelableCreator
import com.shannan.nakollaol.domain.interactor.Sandwich as DSandwich

data class SandwichModel(val name: String,
                         val photoUrl: String?,
                         val price: Double,
                         val extras: List<ExtraModel> = emptyList()) : KParcelable {

    private constructor(p: Parcel) : this(name = p.readString(), photoUrl = p.readString(), price = p.readDouble())

    override fun writeToParcel(dest: Parcel, flags: Int) {
        with(dest) {
            writeString(name)
            writeString(photoUrl)
            writeDouble(price)
        }
    }

    companion object {
        fun empty() = SandwichModel(String.empty(), null, 0.0)
        fun fromDomain(dSandwich: DSandwich): SandwichModel = SandwichModel(dSandwich.name, dSandwich.photoUrl,
                dSandwich.price, dSandwich.extras.map { ExtraModel.fromDomain(it) }
        )

        @JvmField
        val CREATOR = parcelableCreator(::SandwichModel)
    }
}

