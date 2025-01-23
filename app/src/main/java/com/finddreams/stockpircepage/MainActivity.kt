package com.finddreams.stockpircepage

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.finddreams.stockpircepage.ui.theme.StockPircePageTheme
import com.finddreams.stockpircepage.vlayout.StockPriceVLayoutActivity

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StockPircePageTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        CenterAlignedTopAppBar(title = {
                            Text(text = "股票报价页面")
                        })
                    }
                ) { innerPadding ->
                    Spacer(Modifier.height(20.dp))
                    ContentView(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun ContentView(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    Column(modifier = modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Button(onClick = {
            StockPriceVLayoutActivity.startActivity(context,"恒生指数")
        }) {
            Text(
                text = "指数类型"
            )
        }
        Button(onClick = {
            StockPriceVLayoutActivity.startActivity(context,"腾讯控股")
        }) {
            Text(
                text = "股票类型"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    StockPircePageTheme {
        ContentView()
    }
}