package info.stekolschikov.ytranslate;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

import javax.net.ssl.HttpsURLConnection;
public class MainActivity extends AppCompatActivity {
//    MyTask mt;
    YandexTranslate YT = new YandexTranslate();
    TextView textViewTranslatedText;
    TextView textViewTranslatedTextInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Button btnTranslate = (Button) findViewById(R.id.btnTranslate);
        textViewTranslatedText = (TextView) findViewById(R.id.textViewTranslatedText);
        textViewTranslatedTextInput = (TextView) findViewById(R.id.textViewTranslatedTextInput);

        View.OnClickListener OnClickListenerT = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.btnTranslate:
//                        YT.strToTranslate = textViewTranslatedTextInput.getText().toString();
//                        mt = new MyTask();
//                        mt.execute();

                        MyTextLoader textLoader = new MyTextLoader();
                        textLoader.execute("ru","hi");
                        textViewTranslatedText.setText(YT.strTranslate);
                        break;
                }
            }
        };

        btnTranslate.setOnClickListener(OnClickListenerT);

    }



//    class MyTask extends AsyncTask<Void, Void, Void> {
//
//        @Override
//        protected Void doInBackground(Void... params) {
//            YT.getStr("");
//            String lang = "en";
////            String input = "привет";
////            String lang = YT.lang.toString();
//            Log.i("lang", lang);
//            String input = YT.strToTranslate;
//            Log.i("input", input);
//            try {
//                String urlStr = "https://translate.yandex.net/api/v1.5/tr.json/translate?key=trnsl.1.1.20150627T071448Z.117dacaac1e63b79.6b1b4bb84635161fcd400dace9fb2220d6f344ef";
//                URL urlObj = new URL(urlStr);
//                HttpsURLConnection connection = (HttpsURLConnection)urlObj.openConnection();
//                connection.setRequestMethod("POST");
//                connection.setDoOutput(true);
//                DataOutputStream dataOutputStream = new DataOutputStream(connection.getOutputStream());
//                dataOutputStream.writeBytes("text=" + URLEncoder.encode(input, "UTF-8") + "&lang=" + lang);
//
//                InputStream response = connection.getInputStream();
//                String json = new java.util.Scanner(response).nextLine();
//                int start = json.indexOf("[");
//                int end = json.indexOf("]");
//                String translated = json.substring(start + 2, end - 1);
//                YT.strTranslate = translated;
//                Log.i("translated", translated);
//
//            } catch (UnsupportedEncodingException e) {
//                e.printStackTrace();
//            } catch (ProtocolException e) {
//                e.printStackTrace();
//            } catch (MalformedURLException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            return null;
//        }
//    }
    private class MyTextLoader extends AsyncTask<String, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //запустить индикацию загрузки
        }
        @Override
        protected Void doInBackground(String... params) {
            Log.i("sss", params[0]);
            try {
                String urlStr = "https://translate.yandex.net/api/v1.5/tr.json/translate?key=trnsl.1.1.20150627T071448Z.117dacaac1e63b79.6b1b4bb84635161fcd400dace9fb2220d6f344ef";
                URL urlObj = new URL(urlStr);
                HttpsURLConnection connection = (HttpsURLConnection)urlObj.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                DataOutputStream dataOutputStream = new DataOutputStream(connection.getOutputStream());
                dataOutputStream.writeBytes("text=" + URLEncoder.encode(params[1], "UTF-8") + "&lang=" + params[0]);

                InputStream response = connection.getInputStream();
                String json = new java.util.Scanner(response).nextLine();
                int start = json.indexOf("[");
                int end = json.indexOf("]");
                String translated = json.substring(start + 2, end - 1);
                YT.strTranslate = translated;
                Log.i("translated", translated);
                YT.strTranslate = translated;

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
            //params[0] - Ваша ссылка
            //Получение данных
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            //завершить индикацию загрузки
            //Устанавливаете необходимый текст
        }
    }
}
