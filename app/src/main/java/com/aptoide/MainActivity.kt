package com.aptoide

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import com.aptoide.ui.screens.AptoideNavHost
import com.aptoide.ui.theme.AptoideTheme
import com.aptoide.ui.theme.TopAppBarScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
             AptoideTheme {

                Column {
                    Row {
                        TopAppBarScreen()
                    }
                    Row {
                         AptoideNavHost()
                    }
                }
            }
        }
    }
}

