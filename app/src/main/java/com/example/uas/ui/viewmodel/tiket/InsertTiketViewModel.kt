package com.example.uas.ui.viewmodel.tiket

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uas.model.Tiket
import com.example.uas.repository.TiketRepository
import kotlinx.coroutines.launch

//mengelola status UI terkait insert tiket
class InsertTiketViewModel (private val tiket: TiketRepository): ViewModel(){
    //Menyimpan status UI yang berisi data tiket yang akan diinsert
    var uiState by mutableStateOf(InsertTiketUiState())
        private set
    //memperbarui state UI ketika ada perubahan data tiket
    fun updateInsertTiketState(insertTiketUiEvent: InsertTiketUiEvent){
        uiState = InsertTiketUiState(insertTiketUiEvent = insertTiketUiEvent)
    }

    //untuk menyisipkan tiket baru ke dalam database
    //menggunakan repository tiket untuk memanggil metode insertTiket
    suspend fun insertTiket() {
        viewModelScope.launch {
            try {
                println("Inserting Tiket: ${uiState.insertTiketUiEvent}")
                tiket.insertTiket(uiState.insertTiketUiEvent.toTiket())
                println("Insert berhasil")
            } catch (e: Exception) {
                println("Insert gagal: ${e.message}")
                e.printStackTrace()
            }
        }
    }

    //untuk memperbarui tiket yang sudah ada menggunakan ID tiket
    suspend fun updateTiket(id_tiket: String){
        viewModelScope.launch {
            try {
                tiket.updateTiket(id_tiket, uiState.insertTiketUiEvent.toTiket())
            } catch (e: Exception){
                e.printStackTrace()
            }
        }
    }
}
//mengonversi objek Tiket menjadi InsertTiketUiState untuk memperbarui UI
fun Tiket.toUiStateTiket(): InsertTiketUiState = InsertTiketUiState(
    insertTiketUiEvent = toInsertTiketUiEvent()
)
//ntuk proses insert atau pembaruan data tiket di database
fun InsertTiketUiEvent.toTiket(): Tiket = Tiket (
    id_tiket = id_tiket,
    id_penayangan = id_penayangan,
    jumlah_tiket = jumlah_tiket,
    total_harga = total_harga,
    status_pembayaran = status_pembayaran
)

//untuk memperbarui data UI dengan informasi tiket
fun Tiket.toInsertTiketUiEvent(): InsertTiketUiEvent = InsertTiketUiEvent (
    id_tiket = id_tiket,
    id_penayangan = id_penayangan,
    jumlah_tiket = jumlah_tiket,
    total_harga = total_harga,
    status_pembayaran = status_pembayaran
)

//Menyimpan status UI terkait operasi penyisipan tiket
data class InsertTiketUiState(
    val insertTiketUiEvent: InsertTiketUiEvent = InsertTiketUiEvent()
)

//Menyimpan data tiket yang akan disisipkan atau diperbarui
data class InsertTiketUiEvent(
    val id_tiket: String = " ",
    val id_penayangan: String = " ",
    val jumlah_tiket: String = " ",
    val total_harga: String = " ",
    val status_pembayaran: String = " "
)