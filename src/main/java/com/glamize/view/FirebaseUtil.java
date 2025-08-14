package com.glamize.view;

import com.google.gson.*;
import javafx.scene.image.Image;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.util.Base64;
import java.util.function.Consumer;

public class FirebaseUtil {

    private static final String PROJECT_ID = "glamizee";
    private static final String FIRESTORE_URL = "https://firestore.googleapis.com/v1/projects/" + PROJECT_ID + "/databases/(default)/documents/users/";
    private static final String FIREBASE_STORAGE_UPLOAD_URL = "https://firebasestorage.googleapis.com/v0/b/" + PROJECT_ID + ".appspot.com/o";
    private static final String STORAGE_BASE_URL = "https://firebasestorage.googleapis.com/v0/b/" + PROJECT_ID + ".appspot.com/o/";

    // 🔹 Fetch full user profile from Firestore
    public static void fetchUserProfile(String idToken, String localId, Consumer<JsonObject> callback) {
        try {
            URL url = new URL(FIRESTORE_URL + localId + "?access_token=" + idToken);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            JsonObject response = JsonParser.parseReader(reader).getAsJsonObject();
            reader.close();

            callback.accept(response);
        } catch (Exception e) {
            e.printStackTrace();
            callback.accept(null);
        }
    }

    // 🔹 Save user name (updates only 'name' field)
    public static void saveUserName(String idToken, String localId, String name, Consumer<Boolean> callback) {
        try {
            URL url = new URL(FIRESTORE_URL + localId + "?access_token=" + idToken);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("PATCH");
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/json");

            JsonObject fields = new JsonObject();
            JsonObject nameField = new JsonObject();
            nameField.addProperty("stringValue", name);
            fields.add("name", nameField);

            JsonObject root = new JsonObject();
            root.add("fields", fields);

            OutputStream os = conn.getOutputStream();
            os.write(root.toString().getBytes());
            os.flush();
            os.close();

            callback.accept(conn.getResponseCode() == 200);
        } catch (Exception e) {
            e.printStackTrace();
            callback.accept(false);
        }
    }

    // 🔹 Upload profile photo to Firebase Storage
    public static void uploadProfilePhoto(String idToken, String localId, File imageFile, Consumer<String> callback) {
        try {
            String fileName = "profile_" + localId + "_" + System.currentTimeMillis() + ".png";
            String encodedFileName = fileName.replace(" ", "%20");

            URL url = new URL(FIREBASE_STORAGE_UPLOAD_URL + "?name=" + encodedFileName);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("Authorization", "Bearer " + idToken);
            conn.setRequestProperty("Content-Type", "image/png");

            byte[] imageBytes = Files.readAllBytes(imageFile.toPath());
            OutputStream os = conn.getOutputStream();
            os.write(imageBytes);
            os.flush();
            os.close();

            if (conn.getResponseCode() == 200) {
                // Return the downloadable image URL
                String downloadUrl = STORAGE_BASE_URL + encodedFileName + "?alt=media";
                updateProfileImageUrl(idToken, localId, downloadUrl, success -> {
                    if (success) {
                        callback.accept(downloadUrl);
                    } else {
                        callback.accept(null);
                    }
                });
            } else {
                callback.accept(null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            callback.accept(null);
        }
    }

    // 🔹 Update Firestore with image URL
    private static void updateProfileImageUrl(String idToken, String localId, String imageUrl, Consumer<Boolean> callback) {
        try {
            URL url = new URL(FIRESTORE_URL + localId + "?access_token=" + idToken);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("PATCH");
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/json");

            JsonObject fields = new JsonObject();
            JsonObject imageField = new JsonObject();
            imageField.addProperty("stringValue", imageUrl);
            fields.add("profileImageUrl", imageField);

            JsonObject root = new JsonObject();
            root.add("fields", fields);

            OutputStream os = conn.getOutputStream();
            os.write(root.toString().getBytes());
            os.flush();
            os.close();

            callback.accept(conn.getResponseCode() == 200);
        } catch (Exception e) {
            e.printStackTrace();
            callback.accept(false);
        }
    }

    // 🔹 Fetch profile photo using direct image URL
    public static void fetchProfilePhoto(String imageUrl, Consumer<Image> callback) {
        try {
            Image image = new Image(imageUrl, true);
            callback.accept(image);
        } catch (Exception e) {
            e.printStackTrace();
            callback.accept(null);
        }
    }
}
