package com.example.businesscardapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.businesscardapp.ui.theme.BusinessCardAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BusinessCardAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color(0xFFB3E5FC)) // Light blue background
                            .padding(16.dp)
                    ) {
                        Spacer(modifier = Modifier.height(16.dp))
                        CardHeader(name = "Bennett Fife")
                        ContactInfo(modifier = Modifier.align(Alignment.CenterHorizontally))
                    }
                }
            }
        }
    }
}

@Composable
fun CardHeader(name: String, modifier: Modifier = Modifier) {
    val scale by rememberInfiniteTransition(label = "").animateFloat(
        initialValue = 1f,
        targetValue = 1.05f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.padding(8.dp)
    ) {
        // Load and display the image
        Image(
            painter = painterResource(id = R.drawable.bennett), // Ensure the image name matches your resource
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(128.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primary)
                .scale(scale)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = name,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}

@Composable
fun ContactInfo(modifier: Modifier = Modifier) {
    val context = LocalContext.current

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.padding(8.dp)
    ) {
        ClickableText(
            text = buildAnnotatedString {
                append("Phone: ")
                withStyle(style = SpanStyle(color = Color.Blue)) {
                    append("555-555-5555")
                }
            },
            onClick = {
                val intent = Intent(Intent.ACTION_DIAL).apply {
                    data = Uri.parse("tel:5555555555")
                }
                context.startActivity(intent)
            },
            style = MaterialTheme.typography.bodyLarge.copy(fontSize = 20.sp, color = MaterialTheme.colorScheme.onBackground)
        )
        ClickableText(
            text = buildAnnotatedString {
                append("Email: ")
                withStyle(style = SpanStyle(color = Color.Blue)) {
                    append("bennett.fife@gmail.com")
                }
            },
            onClick = {
                val intent = Intent(Intent.ACTION_SENDTO).apply {
                    data = Uri.parse("mailto:bennett.fife@gmail.com")
                }
                context.startActivity(intent)
            },
            style = MaterialTheme.typography.bodyLarge.copy(fontSize = 20.sp, color = MaterialTheme.colorScheme.onBackground)
        )
        ClickableText(
            text = buildAnnotatedString {
                append("Github: ")
                withStyle(style = SpanStyle(color = Color.Blue)) {
                    append("bennettsf")
                }
            },
            onClick = {
                val intent = Intent(Intent.ACTION_VIEW).apply {
                    data = Uri.parse("https://github.com/bennettsf")
                }
                context.startActivity(intent)
            },
            style = MaterialTheme.typography.bodyLarge.copy(fontSize = 20.sp, color = MaterialTheme.colorScheme.onBackground)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BusinessCardPreview() {
    BusinessCardAppTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFB3E5FC)) // Light blue background
                    .padding(16.dp)
            ) {
                Spacer(modifier = Modifier.height(16.dp))
                CardHeader(name = "Bennett Fife")
                ContactInfo(modifier = Modifier.align(Alignment.CenterHorizontally))
            }
        }
    }
}
