package com.example.hello_world

import android.app.DownloadManager
import android.content.Context
import android.os.Environment
import androidx.core.net.toUri
import android.net.Uri

class AndroidDownloader(
    private val context: Context
): Downloader {

    private val downloadManager = context.getSystemService(DownloadManager::class.java)

    override fun downloadFile(url: String, outputFile: String): Long {
        val request = DownloadManager.Request(Uri.parse(url)) //url.toUri())
            .setMimeType("video/mp4")
            .setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI)
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            //.setTitle(outputFile)
            //.addRequestHeader("Authorization", "Bearer <token>")
            //.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, outputFile) //image.jpg")
            .setDestinationInExternalFilesDir(context,"library", outputFile)
        return downloadManager.enqueue(request)
    }
}