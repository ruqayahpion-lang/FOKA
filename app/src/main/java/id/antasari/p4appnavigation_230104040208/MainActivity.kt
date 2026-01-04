package id.antasari.p4appnavigation_230104040208

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.fillMaxSize
import id.antasari.p4appnavigation_230104040208.nav.NavGraph

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            id.antasari.p4appnavigation_230104040208.ui.theme.P4appnavigation_230104040208Theme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    NavGraph() // 🔹 Ganti Greeting() dengan NavGraph()
                }
            }
        }
    }
}
