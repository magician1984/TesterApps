package idv.bruce.posservicetester.domain.presenter.widget

import android.icu.text.SimpleDateFormat
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import idv.bruce.posservicetester.core.theme.PT1TesterTheme
import java.util.Locale

private const val TIME_FORMAT = "yyyy-MM-dd HH:mm:ss"

@Composable
fun WidgetHistory(modifier: Modifier = Modifier, time: Long, data: String) {
    Card(modifier = modifier, elevation = CardDefaults.cardElevation()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(8.dp)
        ) {
            Text(text = time.toTimeString())
            Text(text = data)
        }
    }
}

@Preview(backgroundColor = 0xFFFFFFFF, showBackground = true, showSystemUi = true)
@Composable
private fun Preview(){
    PT1TesterTheme {
        WidgetHistory(modifier = Modifier, time = System.currentTimeMillis(),  data = "test")
    }
}

private fun Long.toTimeString(): String {
    return SimpleDateFormat(TIME_FORMAT, Locale.getDefault()).format(this)
}

