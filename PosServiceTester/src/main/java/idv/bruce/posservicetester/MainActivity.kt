package idv.bruce.posservicetester

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

class MainActivity : ComponentActivity() {
    companion object {
        private const val TAG = "MainActivity"
    }

    private val container: Container = Container(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        container.onActivityCreated()

        setContent {
            container.uiProvider.Draw(onBackPressedDispatcher = onBackPressedDispatcher)
        }
    }

}