package jp.co.yumemi.android.code_check.repository.detail

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import jp.co.yumemi.android.code_check.theme.CodeCheckTheme

private sealed class CircleItem(
    val max: Long,
    val color: Color,
    val angleRatio: Float,
) {
    companion object {
        val ITEM_COUNT = 4
    }

    object Stars : CircleItem(max = 50_000, color = Color(0xFFFF4B4B), 360f / ITEM_COUNT)
    object Watchers : CircleItem(max = 3_000, color = Color(0xFF312DF8), 360f / ITEM_COUNT)
    object Forks : CircleItem(max = 10_000, color = Color(0xFF28ED30), 360f / ITEM_COUNT)
    object OpenIssues : CircleItem(max = 500, color = Color(0xFFF9941E), 360f / ITEM_COUNT)
}

@Composable
fun RepoCircle(
    modifier: Modifier = Modifier,
    stars: Long,
    watchers: Long,
    forks: Long,
    openIssues: Long,
) {

    Surface(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colors.surface,
        contentColor = MaterialTheme.colors.onSurface,
    ) {
        Box(Modifier.padding(16.dp)) {
            Canvas(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f / 1f),
                onDraw = {
                    var start = 0f
                    var sweep = CircleItem.Stars.angleRatio * limitValue(
                        stars / CircleItem.Stars.max.toFloat(),
                        0.05f,
                        1.00f
                    )
                    drawCircleItemArc(CircleItem.Stars, start, sweep)

                    start += sweep
                    sweep = CircleItem.Watchers.angleRatio * limitValue(
                        watchers / CircleItem.Watchers.max.toFloat(),
                        0.05f,
                        1.00f
                    )
                    drawCircleItemArc(CircleItem.Watchers, start, sweep)

                    start += sweep
                    sweep = CircleItem.Forks.angleRatio * limitValue(
                        forks / CircleItem.Forks.max.toFloat(),
                        0.05f,
                        1.00f
                    )
                    drawCircleItemArc(CircleItem.Forks, start, sweep)

                    start += sweep
                    sweep = CircleItem.OpenIssues.angleRatio * limitValue(
                        openIssues / CircleItem.OpenIssues.max.toFloat(),
                        0.05f,
                        1.00f
                    )
                    drawCircleItemArc(CircleItem.OpenIssues, start, sweep)
                },
            )
        }
    }
}

private fun DrawScope.drawCircleItemArc(
    circleItem: CircleItem,
    startAngle: Float,
    sweepAngle: Float,
    strokeWidth: Float = size.width * 0.15f,
) {
    rotate(-90f) {
        drawArc(
            style = Stroke(strokeWidth, cap = StrokeCap.Butt),
            color = circleItem.color,
            startAngle = startAngle,
            sweepAngle = sweepAngle,
            useCenter = false,
            topLeft = Offset(strokeWidth / 2, strokeWidth / 2),
            size = size.let {
                Size(
                    it.width - strokeWidth,
                    it.height - strokeWidth
                )
            },
        )
    }
}

private fun limitValue(value: Float, min: Float, max: Float): Float {
    return when {
        value > max -> max
        value < min -> min
        else -> value
    }
}

@Preview
@Composable
fun RepoCirclePreview() {
    CodeCheckTheme {
        RepoCircle(
            stars = (CircleItem.Stars.max * 0.5f).toLong(),
            watchers = (CircleItem.Watchers.max * 0.6f).toLong(),
            forks = (CircleItem.Forks.max * 0.8f).toLong(),
            openIssues = (CircleItem.OpenIssues.max * 0.8f).toLong(),
        )
    }
}
