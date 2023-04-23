package day03.ex03;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class DownloadThread extends Thread {
    private final List<String> urls;
    private final int threadId;
    private final int numThreads;

    public DownloadThread(List<String> urls, int threadId, int numThreads) {
        this.urls = urls;
        this.threadId = threadId;
        this.numThreads = numThreads;
    }

    @Override
    public void run() {
        try {
            for (int i = threadId; i < urls.size(); i += numThreads) {
                String url = urls.get(i);
                downloadFile(url, i);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void downloadFile(String url, int num) throws IOException {
        System.out.println("Thread-" + (threadId + 1) + " start downloading file number " + (num + 1));

        URL urlObj = new URL(url);
        InputStream inputStream = urlObj.openStream();

        String filename = getFilenameFromUrl(url);

        File directory = new File(System.getProperty("user.dir") + File.separator + "downloads");
        if (!directory.exists()) {
            if (!directory.mkdir()) {
                System.out.println("Error: unable to create new directory");
            }
        }
        String savePath = directory.getAbsolutePath() + File.separator + filename;

        FileOutputStream outputStream = new FileOutputStream(savePath);

        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }

        inputStream.close();
        outputStream.close();

        System.out.println("Thread-" + (threadId + 1) + " finish downloading file number " + (num + 1));
    }

    private String getFilenameFromUrl(String url) {
        int lastSlashIndex = url.lastIndexOf('/');
        if (lastSlashIndex >= 0 && lastSlashIndex < url.length() - 1) {
            return url.substring(lastSlashIndex + 1);
        } else {
            throw new IllegalArgumentException("Error: Invalid URL: " + url);
        }
    }
}
