package com.example.hello_world

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.hello_world.ui.theme.Hello_worldTheme

import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.File
import java.io.FileOutputStream
import java.io.IOException


fun downloadFile(url: String, outputFilePath: String) {
    val client = OkHttpClient()

    val request = Request.Builder()
        .url(url)
        .build()

    client.newCall(request).execute().use { response ->
        if (!response.isSuccessful) throw IOException("Unexpected response $response")

        val file = File(outputFilePath)
        FileOutputStream(file).use { fos ->
            fos.write(response.body?.bytes())
        }
    }
}


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()



        val fileUrl = "http://10.50.10.231:9876/download/66a1f4a72e5a885acbb249d6"
        val outputFilePath = "Elevit_Schaufenster_OHNEPromo_v003.mp4"
        val newVersion: Boolean = true
        var errorMsg: String = " "

        if (newVersion) {
            val downloader = AndroidDownloader(this)

            try {
                downloader.downloadFile(fileUrl, outputFilePath)
            } catch (e: Exception) {
                errorMsg = "Error: $e?.message"
            }
        } else {
            try {
                downloadFile(fileUrl, outputFilePath)
            } catch (e: Exception) {
                errorMsg = "Error: $e?.message"
            }
        }

        setContent {
            Hello_worldTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    DisplayErrorMsg(
                        errorMsg,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun DisplayErrorMsg(errorMsg: String, modifier: Modifier = Modifier) {
//    val fileUrl = "http://10.50.10.231:9876/download/66a1f4a72e5a885acbb249d6"
//    var errorMsg: String = " "
//
//    if (newVersion) {
//        //val downloader = AndroidDownloader(this)
//        //downloader.downloadFile(fileUrl)
//    } else {
//        val outputFilePath = "mf_new_file"
//
//        try {
//            downloadFile(fileUrl, outputFilePath)
//            //println("File downloaded successfully to $outputFilePath")
//        } catch (e: Exception) {
//            errorMsg = "Error: $e.message"
//            //println("Failed to download file: ${e.message}")
//        }
//    }

    Text(
        text = errorMsg,
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Hello_worldTheme {
        DisplayErrorMsg("Preview")
    }
}