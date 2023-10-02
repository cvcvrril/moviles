package com.example.formulariobien.utils

import android.content.Context
import androidx.annotation.StringRes
import java.security.AccessControlContext

class StringProvider (val context: Context) {

    companion object{
        fun instance(context: Context): StringProvider = StringProvider(context)
    }

    fun getString(@StringRes stringRes: Int): String{
        return context.getString(stringRes)
    }
}