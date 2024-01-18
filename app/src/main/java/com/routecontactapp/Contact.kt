package com.routecontactapp

import android.content.ClipDescription
import android.os.Parcelable
import android.text.Editable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Contact(
    var avatarName: String? = null,
    var avatarPhoneNumber: String? = null,
    var avatarDescription: String? = null,
    var avatarLogo: Int? = null
):Parcelable
