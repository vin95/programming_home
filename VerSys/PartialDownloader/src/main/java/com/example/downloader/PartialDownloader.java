package com.example.downloader;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class PartialDownloader {
    public static void main(String[] args) {
        String fileURL = "http://speedtest.belwue.net/1G";
        String saveFilePath = "20MB_test_download";
        int maxBytes = 20 * 1024 * 1024;

        try {
            URL url = new URL(fileURL);
            HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
            httpConn.setRequestProperty("Range", "bytes=0-" + (maxBytes - 1));
            int responseCode = httpConn.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_PARTIAL) {
                try (InputStream inputStream = httpConn.getInputStream();
                     FileOutputStream outputStream = new FileOutputStream(saveFilePath)) {

                    byte[] buffer = new byte[4096];
                    int bytesRead;
                    int totalRead = 0;

                    while ((bytesRead = inputStream.read(buffer)) != -1 && totalRead < maxBytes) {
                        outputStream.write(buffer, 0, bytesRead);
                        totalRead += bytesRead;
                    }

                    System.out.println("Download abgeschlossen: " + totalRead + " Bytes");
                }
            } else {
                System.out.println("Server unterstützt keine Bereichsanfragen. Antwortcode: " + responseCode);
            }

            httpConn.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
