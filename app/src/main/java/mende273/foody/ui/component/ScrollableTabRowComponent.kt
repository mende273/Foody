package mende273.foody.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import mende273.foody.ui.common.model.Tab
import mende273.foody.ui.common.preview.parameter.TabItemsParameterProvider
import mende273.foody.ui.theme.AccentColor
import mende273.foody.ui.theme.FoodyTheme

@Composable
fun ScrollableTabRowComponent(
    items: Array<Tab>,
    pagerState: PagerState,
    tabEvent: (Int) -> Unit
) {
    ScrollableTabRow(
        containerColor = MaterialTheme.colorScheme.background,
        contentColor = MaterialTheme.colorScheme.onBackground,
        selectedTabIndex = pagerState.currentPage,
        edgePadding = 0.dp,
        divider = { HorizontalDivider(color = MaterialTheme.colorScheme.background) },
        indicator = {
            TabIndicator(Modifier.tabIndicatorOffset(it[pagerState.currentPage]))
        }
    ) {
        items.forEachIndexed { index, item ->
            Tab(
                selected = index == pagerState.currentPage,
                text = { Text(text = item.tabName) },
                onClick = { tabEvent(index) }
            )
        }
    }
}

@Composable
private fun TabIndicator(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .wrapContentWidth(align = Alignment.CenterHorizontally)
            .padding(2.dp)
            .clip(CircleShape)
            .background(AccentColor)
            .size(10.dp)
    )
}

@PreviewLightDark
@Composable
private fun ScrollableTabComponentPreview(
    @PreviewParameter(TabItemsParameterProvider::class) items: Array<Tab>
) {
    FoodyTheme {
        ScrollableTabRowComponent(
            items = items,
            pagerState = rememberPagerState(pageCount = { 1 }),
            tabEvent = {}
        )
    }
}

@PreviewLightDark
@Composable
private fun TabIndicatorPreview() {
    TabIndicator()
}
