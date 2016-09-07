package info.stekolschikov.ytranslate;

import android.os.AsyncTask;
import android.util.Log;
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

/**
 * Created by mk on 07.09.2016.
 */
public class YandexTranslate {
    TextView textViewTranslatedTextInput = (TextView) MainActivity.findViewById(R.id.textViewTranslatedTextInput);
    public String lang = "ru";
    public String strToTranslate = "привет";
    public String strTranslate = "";

    public void getStr(String s){
        this.strToTranslate = s;
    }
    public String retStr(){
        return strTranslate;
    }




}
