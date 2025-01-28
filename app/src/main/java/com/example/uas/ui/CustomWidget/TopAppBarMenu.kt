package com.example.uas.ui.CustomWidget

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.uas.R

@Composable
fun TopAppBarHomeMenu(
    modifier: Modifier = Modifier
) {
    Surface(
        color = Color(0xFF001B48), // Biru tua
        modifier = Modifier
            .fillMaxWidth()
            .clip(
                RoundedCornerShape(
                    bottomEnd = 50.dp    // Membulatkan sudut bawah kanan
                )
            )
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF001B48)) // Biru tua sama seperti Surface
                .padding(50.dp)
                .clip(
                    RoundedCornerShape(
                        topStart = 0.dp,
                        topEnd = 100.dp,
                        bottomEnd = 100.dp,
                        bottomStart = 0.dp
                    )
                ),
            contentAlignment = Alignment.Center
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = "CINEMA`S",
                        fontSize = 35.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                    Text(
                        text = "Nantikan Film Terbaru Lainnya!!!",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Normal,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }

                // Logo berbentuk lingkaran di sebelah kanan
                Image(
                    painter = painterResource(id = R.drawable.logo), // Ganti dengan ID gambar Anda
                    contentDescription = "Logo",
                    modifier = Modifier
                        .size(80.dp)
                        .clip(CircleShape)
                        .border(2.dp, Color.White, CircleShape)
                        .padding(4.dp)
                )
            }
        }
    }
}
