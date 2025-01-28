package com.example.uas.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.uas.ui.CustomWidget.TopAppBarHomeMenu

@Composable
fun HomeMenuView(
    onFilmClick: () -> Unit,
    onPenayanganClick: () -> Unit,
    onStudioClick: () -> Unit,
    onTiketClick: () -> Unit
){
    Scaffold(
        topBar = {
            TopAppBarHomeMenu()
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        // Gradasi warna vertikal dari merah ke hitam
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color(0xFF95BEFA), // Biru muda
                                Color(0xFF022D7C)  // Biru tua
                            ),
                            startY = 0f,
                            endY = Float.POSITIVE_INFINITY
                        )
                    )
                    .padding(paddingValues)
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                //button film
                Button(
                    onClick = onFilmClick,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Gray,
                        contentColor = Color.Black
                    ),
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(0.8f)
                        .clip(RoundedCornerShape(12.dp)) // Rounded corners
                        .shadow(8.dp, RoundedCornerShape(12.dp)) // Add shadow for elevation
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "Manage Film",
                            modifier = Modifier.padding(end = 8.dp),
                            tint = Color.Black
                        )
                        Text(
                            text = "Manage Film",
                            fontSize = 25.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))


                }
            }
        }
    )
}