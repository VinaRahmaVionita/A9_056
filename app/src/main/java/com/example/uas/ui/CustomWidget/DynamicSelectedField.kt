package com.example.uas.ui.CustomWidget

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DynamicSelectTextField(
    selectedValue: String,
    options: List<String>,
    label: String,
    onValueChangedEvent: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    // State untuk melacak apakah dropdown sedang terbuka
    var expanded by remember { mutableStateOf(false) }

    // Dropdown Menu Box
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = modifier
    ) {
        // Outlined Text Field yang menjadi anchor untuk dropdown
        OutlinedTextField(
            readOnly = true,
            value = selectedValue,
            onValueChange = {}, // Tidak perlu aksi saat TextField diubah (read-only)
            label = { Text(text = label) },
            placeholder = {
                if (selectedValue.isEmpty()) Text("Select an option")
            },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = expanded
                )
            },
            colors = OutlinedTextFieldDefaults.colors(),
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth()
        )

        // Validasi: Jika options kosong, tampilkan teks
        if (options.isEmpty()) {
            ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                DropdownMenuItem(
                    text = { Text(text = "No options available") },
                    onClick = { expanded = false }
                )
            }
        } else {
            // Exposed Dropdown Menu dengan daftar opsi
            ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                options.forEach { option ->
                    DropdownMenuItem(
                        text = { Text(text = option) },
                        onClick = {
                            expanded = false
                            onValueChangedEvent(option) // Pilihan dikirim ke parent composable
                        }
                    )
                }
            }
        }
    }
}
