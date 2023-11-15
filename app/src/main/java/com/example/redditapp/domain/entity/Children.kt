package com.example.redditapp.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Children(
    val data: DataX,
    val kind: String
): Parcelable