package com.example.uas.ui.CustomWidget

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TopAppBarHomeMenu(
    modifier: Modifier = Modifier
) {
    //untuk memberikan latar belakang pada elemen UI di dalamnya
    Surface(
        color = Color.Gray
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Color.DarkGray
                )
                .padding(50.dp),
            contentAlignment = Alignment.Center
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier.weight(1f) // Rata kiri dengan ruang fleksibel
                ) {
                    Text(
                        text = "CINEMA`S",
                        fontSize = 35.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onPrimary // Warna teks
                    )
                    Text(
                        text = "Nantikan Film Terbaru Lainnya!!!",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Normal,
                        color = MaterialTheme.colorScheme.onPrimary // Warna teks
                    )
                }

            }
        }
    }
}