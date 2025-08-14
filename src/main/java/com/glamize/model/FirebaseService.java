package com.glamize.model;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class FirebaseService {

    private static final String API_KEY = "AIzaSyC4ysTqPAl72pPo5b8UKHevIhu6LFZsTz8";

    public String signInWithEmailAndPassword(String email, String password) {
        try {
            URL url = new URL("https://identitytoolkit.googleapis.com/v1/accounts:signInWithPassword?key=" + API_KEY);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            String payload = String.format(
                "{\"email\":\"%s\",\"password\":\"%s\",\"returnSecureToken\":true}",
                email, password
            );

            try (OutputStream os = conn.getOutputStream()) {
                os.write(payload.getBytes());
                os.flush();
            }

            int responseCode = conn.getResponseCode();

            InputStream is = (responseCode == 200)
                ? conn.getInputStream()
                : conn.getErrorStream();

            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            if (responseCode == 200) {
                return "Login successful 🎉";
            } else {
                return "Login failed: " + parseErrorMessage(response.toString());
            }

        } catch (Exception e) {
            e.printStackTrace();
            return "Error occurred: " + e.getMessage();
        }
    }

    private String parseErrorMessage(String json) {
        if (json.contains("EMAIL_NOT_FOUND")) return "Email not found.";
        if (json.contains("INVALID_PASSWORD")) return "Invalid password.";
        if (json.contains("USER_DISABLED")) return "User account disabled.";
        return ("Unknown error.");
    }
}