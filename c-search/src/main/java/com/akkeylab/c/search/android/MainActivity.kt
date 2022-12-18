package com.akkeylab.c.search.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.akkeylab.c.search.Greeting
import com.akkeylab.c.search.SearchCorporate
import kotlinx.coroutines.*

@Composable
fun ApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        darkColors(
            primary = Color(0xFFBB86FC),
            primaryVariant = Color(0xFF3700B3),
            secondary = Color(0xFF03DAC5)
        )
    } else {
        lightColors(
            primary = Color(0xFF6200EE),
            primaryVariant = Color(0xFF3700B3),
            secondary = Color(0xFF03DAC5)
        )
    }
    val typography = Typography(
        body1 = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp
        )
    )
    val shapes = Shapes(
        small = RoundedCornerShape(4.dp),
        medium = RoundedCornerShape(4.dp),
        large = RoundedCornerShape(0.dp)
    )

    MaterialTheme(
        colors = colors,
        typography = typography,
        shapes = shapes,
        content = content
    )
}

class MainActivity : ComponentActivity() {
    private val scope = MainScope()
    private val searchCorporate = SearchCorporate()
    private val stringConverter = StringConverter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            var responseText by remember {
                mutableStateOf("No search result")
            }

            var searchText by remember {
                mutableStateOf("")
            }

            ApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Box(modifier = Modifier.fillMaxSize()) {
                        ResponseViewer(
                            text = responseText,
                            modifier = Modifier
                                .fillMaxWidth()
                                .align(Alignment.Center)
                        )

                        Row(
                            modifier = Modifier.align(Alignment.BottomCenter),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            SearchField(
                                text = searchText,
                                modifier = Modifier
                                    .wrapContentWidth()
                            ) {
                                searchText = stringConverter.toFullWidth(it)
                            }
                            SearchButton(
                                modifier = Modifier
                                    .width(100.dp)
                                    .padding(8.dp)
                            ) {
                                responseText = "Now Loading"
                                scope.launch {
                                    kotlin.runCatching {
                                        responseText = searchCorporate.search(searchText)
                                    }.onFailure {
                                        responseText = "Network Error"
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        scope.cancel()
    }
}

@Composable
fun ResponseViewer(text: String, modifier: Modifier = Modifier) {
    Text(
        modifier = modifier,
        text = text,
        textAlign = TextAlign.Center
    )
}

@Composable
fun SearchButton(modifier: Modifier = Modifier, onClick: () -> Unit) {
    Button(
        modifier = modifier,
        onClick = onClick
    ) {
        Text("Search")
    }
}

@Composable
fun SearchField(text: String, modifier: Modifier = Modifier, onValueChange: (String) -> Unit) {
    OutlinedTextField(
        value = text,
        onValueChange = onValueChange,
        modifier = modifier
    )
}

@Preview
@Composable
fun ResponseViewerPreview() {
    ApplicationTheme {
        ResponseViewer(text = "Now Loading")
    }
}

@Preview
@Composable
fun SearchButtonPreview() {
    ApplicationTheme {
        SearchButton {}
    }
}

@Preview
@Composable
fun SearchFieldPreview() {
    ApplicationTheme {
        SearchField("input") {}
    }
}
