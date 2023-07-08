package jp.co.yumemi.android.code_check.repository.detail

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.scaleIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material.LocalContentColor
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import jp.co.yumemi.android.code_check.theme.CodeCheckTheme

private sealed class CircleItem(
    val max: Long,
    val color: Color,
    val textColor: Color,
    val angleRatio: Float,
) {
    companion object {
        val ITEM_COUNT = 4
    }

    object Stars : CircleItem(
        max = 50_000,
        color = Color(0xFFFF4B4B),
        textColor = Color(0xFFFFD0D0),
        360f / ITEM_COUNT,
    )

    object Watchers : CircleItem(
        max = 3_000,
        color = Color(0xFF312DF8),
        textColor = Color(0xFFDBDAFF),
        360f / ITEM_COUNT,
    )

    object Forks : CircleItem(
        max = 10_000,
        color = Color(0xFF28ED30),
        textColor = Color(0xFFD8FFD9),
        360f / ITEM_COUNT,
    )

    object OpenIssues :
        CircleItem(
            max = 500,
            color = Color(0xFFF9941E),
            textColor = Color(0xFFFCEEDE),
            360f / ITEM_COUNT,
        )
}

@Composable
fun RepoCircleCard(
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
        CompositionLocalProvider(
            LocalTextStyle provides LocalTextStyle.current.copy(fontSize = 24.sp),
        ) {
            Box {

                RepoCircle(
                    modifier = Modifier.padding(horizontal = 64.dp, vertical = 16.dp),
                    stars = stars,
                    watchers = watchers,
                    forks = forks,
                    openIssues = openIssues,
                )

                RepoValueTexts(
                    modifier = Modifier.matchParentSize(),
                    stars = stars,
                    watchers = watchers,
                    forks = forks,
                    openIssues = openIssues,
                )

            }
        }
    }
}

@Composable
private fun RepoCircle(
    modifier: Modifier = Modifier,
    stars: Long,
    watchers: Long,
    forks: Long,
    openIssues: Long,
) {
    Box(modifier) {
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

@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun RepoValueTexts(
    modifier: Modifier = Modifier,
    stars: Long,
    watchers: Long,
    forks: Long,
    openIssues: Long,
) {
    var launched by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        launched = true
    }

    fun enterTransitionOf(delayMillis: Int, durationMillis: Int): EnterTransition {
        val floatSpec =
            tween<Float>(delayMillis = delayMillis, durationMillis = durationMillis)
        val intOffsetSpec =
            tween<IntOffset>(delayMillis = delayMillis, durationMillis = durationMillis)
        return fadeIn(floatSpec) + scaleIn(floatSpec) + slideInVertically(intOffsetSpec) { it / 2 }
    }

    val transitionDelay = 200
    val transitionDucration = 300

    Box(modifier) {
        AnimatedVisibility(
            visible = launched,
            enter = enterTransitionOf(
                transitionDelay,
                transitionDucration,
            ),
            modifier = Modifier.align(Alignment.TopEnd).offset((-10).dp, (10).dp)
        ) {
            Text(
                valueBoldText(
                    stars,
                    "\nstars",
                    color = CircleItem.Stars.textColor,
                ),
            )
        }
        AnimatedVisibility(
            visible = launched,
            enter = enterTransitionOf(
                transitionDelay + transitionDucration * 1 - 100,
                transitionDucration,
            ),
            modifier = Modifier.align(Alignment.BottomEnd).offset((-10).dp, (-10).dp),
        ) {
            Text(
                valueBoldText(
                    watchers,
                    "\nwatchers",
                    color = CircleItem.Watchers.textColor,
                ),
            )
        }
        AnimatedVisibility(
            visible = launched,
            enter = enterTransitionOf(
                transitionDelay + transitionDucration * 2 - 100,
                transitionDucration,
            ),
            modifier = Modifier.align(Alignment.BottomStart).offset((10).dp, (-10).dp),
        ) {
            Text(
                valueBoldText(
                    forks,
                    "\nforks",
                    color = CircleItem.Forks.textColor,
                ),
            )
        }
        AnimatedVisibility(
            visible = launched,
            enter = enterTransitionOf(
                transitionDelay + transitionDucration * 3 - 100,
                transitionDucration,
            ),
            modifier = Modifier.align(Alignment.TopStart).offset((10).dp, (10).dp),
        ) {
            Text(
                valueBoldText(
                    openIssues,
                    "\nopen issues",
                    color = CircleItem.OpenIssues.textColor,
                ),
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

@Composable
private fun valueBoldText(value: Long, text: String, color: Color = LocalContentColor.current) =
    buildAnnotatedString {
        withStyle(SpanStyle(color = color)) {
            withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                append("$value")
            }
            append(text)
        }
    }

@Preview
@Composable
fun RepoCircleCardPreview() {
    CodeCheckTheme {
        RepoCircleCard(
            stars = (CircleItem.Stars.max * 0.5f).toLong(),
            watchers = (CircleItem.Watchers.max * 0.6f).toLong(),
            forks = (CircleItem.Forks.max * 0.8f).toLong(),
            openIssues = (CircleItem.OpenIssues.max * 0.8f).toLong(),
        )
    }
}
