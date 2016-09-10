package info.stekolschikov.ytranslate;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.TextView;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import javax.net.ssl.HttpsURLConnection;

public class YandexTranslate {

    public String lang = "ru";
    public String strToTranslate = "привет";
    public String strTranslate = "";

    public void getStr(String s){
        this.strToTranslate = s;

    }
    public String retStr(){
        return strTranslate;
    }

    public boolean strIsEmpty(){
        int s = strTranslate.length();
        if (s == 0){
            return false;
        } else {
            return true;
        }
    }



}
