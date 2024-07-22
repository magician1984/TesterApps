package idv.bruce.posservicetester.domain.presenter.page

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import idv.bruce.posservicetester.core.data.StateRecord
import idv.bruce.posservicetester.domain.presenter.widget.WidgetHistory

@Composable
fun HistoryPage(modifier: Modifier, list: List<StateRecord>) {

    val scrollState = rememberLazyListState()

    LazyColumn(
        modifier = modifier,
        state = scrollState,
        contentPadding = PaddingValues(vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(list) {
            WidgetHistory(
                time = it.time,
                data = it.data
            )
        }
    }
}

@Preview(backgroundColor = 0xFFFFFFFF, showBackground = true, showSystemUi = true)
@Composable
private fun Preview() {
    HistoryPage(modifier = Modifier, list = listOf(StateRecord(), StateRecord()))
}