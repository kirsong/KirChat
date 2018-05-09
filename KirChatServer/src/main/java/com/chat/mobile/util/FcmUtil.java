package com.chat.mobile.util;

import com.chat.mobile.model.FcmRequest;
import com.chat.mobile.model.FcmResult;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.gson.Gson;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.testng.internal.PropertyUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Properties;

public class FcmUtil {

    private HttpURLConnection httpURLConnection;

    //Http1.1 Auth 사용하지 않음
    @Deprecated
    public void initFirebaseAdminSDK() throws IOException {

        Resource res=new ClassPathResource("FCMserviceAccountKey.json");
        FileInputStream serviceAccount =
                new FileInputStream(res.getFile());

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://kirchat-8d87f.firebaseio.com")
                .build();

        FirebaseApp.initializeApp(options);
    }

    //Http1.1 Auth 사용하지 않음
    @Deprecated
    private static String getAccessToken() throws IOException {
        Resource res=new ClassPathResource("FCMserviceAccountKey.json");
        GoogleCredential googleCredential = GoogleCredential
                .fromStream(new FileInputStream(res.getFile()))
                .createScoped(Arrays.asList("https://www.googleapis.com/auth/firebase.messaging"));
        googleCredential.refreshToken();
        return googleCredential.getAccessToken();
    }

    //Http1.1 Auth 사용하지 않음
    @Deprecated
    public HttpURLConnection send() throws IOException {
        URL url = new URL("https://fcm.googleapis.com/v1/projects/kirchat-8d87f/messages:send HTTP/1.1");
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestProperty("Authorization", "Bearer " + getAccessToken());
        httpURLConnection.setRequestProperty("Content-Type", "application/json; UTF-8");
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setDoOutput(true);

        return httpURLConnection;
    }

    /**
     * 올드 버전의 HttpURLConnection 가져오기
     * @return
     * @throws IOException
     */
    public HttpURLConnection initFcmHttpConnection() throws IOException {

        InputStream io=PropertyUtils.class.getClassLoader().getResourceAsStream("fcm.properties");
        Properties props=new Properties();
        props.load(io);
        String fcmUrl=props.getProperty("fcm.sendUrl");
        String serverKey=props.getProperty("fcm.serverKey");

        URL url = new URL(fcmUrl);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestProperty("Authorization", "key="+serverKey);
        httpURLConnection.setRequestProperty("Content-Type", "application/json;UTF-8");
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setDoOutput(true);

        return httpURLConnection;
    }

    /**
     * 푸시 보내기
     * @param fcmRequest
     * @return
     * @throws IOException
     */
    public FcmResult sendFcmPush(FcmRequest fcmRequest) throws IOException {

        if (httpURLConnection==null){
            httpURLConnection=initFcmHttpConnection();
        }

        OutputStream os=httpURLConnection.getOutputStream();
        Gson gson=new Gson();
        String str=gson.toJson(fcmRequest);
        os.write(str.getBytes());
        os.flush();
        os.close();
        InputStream is=httpURLConnection.getInputStream();
        String responseStr=new String(getBytesByInputStream(is),"utf-8");
        FcmResult fcmResult=gson.fromJson(responseStr,FcmResult.class);
        return fcmResult;

    }

    private byte[] getBytesByInputStream(InputStream is) {
        byte[] bytes = null;
        BufferedInputStream bis = new BufferedInputStream(is);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        BufferedOutputStream bos = new BufferedOutputStream(baos);
        byte[] buffer = new byte[1024 * 8];
        int length = 0;
        try {
            while ((length = bis.read(buffer)) > 0) {
                bos.write(buffer, 0, length);
            }
            bos.flush();
            bytes = baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                bis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return bytes;
    }

}
