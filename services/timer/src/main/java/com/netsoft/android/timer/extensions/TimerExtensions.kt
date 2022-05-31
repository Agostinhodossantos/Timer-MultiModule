package com.netsoft.android.timer.extensions

import android.os.Build

fun isOreoPlus() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.O
