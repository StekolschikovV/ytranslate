package info.stekolschikov.ytranslate;

import android.os.AsyncTask;
import android.util.Log;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by mk on 07.09.2016.
 */
public class YandexTranslate {

    String strToTranslate = "";
    String strTranslate = "";

    public void getStr(String s){
        this.strToTranslate = s;
    }
    public String retStr(){
        return strTranslate;
    }
//    public void translate() throws IOException {
//        String lang = "en";
//        String input = "hi";
//        String urlStr = "https://translate.yandex.net/api/v1.5/tr.json/translate?key=trnsl.1.1.20150627T071448Z.117dacaac1e63b79.6b1b4bb84635161fcd400dace9fb2220d6f344ef";
//        URL urlObj = new URL(urlStr);
//        HttpsURLConnection connection = (HttpsURLConnection)urlObj.openConnection();
//        connection.setRequestMethod("POST");
//        connection.setDoOutput(true);
//        DataOutputStream dataOutputStream = new DataOutputStream(connection.getOutputStream());
//        dataOutputStream.writeBytes("text=" + URLEncoder.encode(input, "UTF-8") + "&lang=" + lang);
//
//        InputStream response = connection.getInputStream();
//        String json = new java.util.Scanner(response).nextLine();
//        int start = json.indexOf("[");
//        int end = json.indexOf("]");
//        String translated = json.substring(start + 2, end - 1);
////        i++;
////        if (translated.equals(input) && i < 2) {
////            return translate("en", input);
////        } else return translated;
//
//        this.strTranslate = translated;
//    }
    public String translate(String lang, String input) throws IOException {
        String urlStr = "https://translate.yandex.net/api/v1.5/tr.json/translate?key=trnsl.1.1.20150627T071448Z.117dacaac1e63b79.6b1b4bb84635161fcd400dace9fb2220d6f344ef";
        URL urlObj = new URL(urlStr);
        HttpsURLConnection connection = (HttpsURLConnection)urlObj.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        DataOutputStream dataOutputStream = new DataOutputStream(connection.getOutputStream());
        dataOutputStream.writeBytes("text=" + URLEncoder.encode(input, "UTF-8") + "&lang=" + lang);

        InputStream response = connection.getInputStream();
        String json = new java.util.Scanner(response).nextLine();
        int start = json.indexOf("[");
        int end = json.indexOf("]");
        String translated = json.substring(start + 2, end - 1);
        int i = 1;
        if (translated.equals(input) && i < 2) {
            // if return equal of entered text - we need change direction of translation
            return translate("en", input);
        } else return translated;
    }

}
