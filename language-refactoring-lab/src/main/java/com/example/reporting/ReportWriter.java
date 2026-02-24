package com.example.reporting;

import java.io.*;

public class ReportWriter {
    public void write(File out, String content) {
        Writer w = null;
        try {
            w = new BufferedWriter(new FileWriter(out));
            w.write(content == null ? "" : content);
        } catch (IOException e) {
            throw new RuntimeException("Failed to write report: " + out, e);
        } finally {
            if (w != null) {
                try { w.close(); } catch (IOException ignored) {}
            }
        }
    }
}
