package com.ohyooo.kmm.ui.counter

import android.os.Bundle
import androidx.activity.ComponentActivity
import com.ohyooo.kmm.ui.Platform

class CounterActivity : ComponentActivity() {

    private val platform = Platform()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "Kotlin Demo - ${platform.platform}"
    }

}