package com.example.hello_world

interface Downloader {
    fun downloadFile(url: String, outputFile: String): Long
}