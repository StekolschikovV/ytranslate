package info.stekolschikov.ytranslate;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

    public TextView textViewTranslatedText;
    public TextView textViewTranslatedTextInput;
    YandexTranslate YT = new YandexTranslate();

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(1, 1, 1, "Английский -> Русский");
        menu.add(2, 2, 2, "Русский -> Английский");
        menu.add(3, 3, 3, "Выход");
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
        switch (item.getItemId()) {
            case 1:
                YT.lang = "ru";
                setEmptuTitle();
                break;
            case 2:
                YT.lang = "en";
                setEmptuTitle();
                break;
            case 3:
                System.exit(0);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }

    public void setEmptuTitle(){
        textViewTranslatedText = (TextView) findViewById(R.id.textViewTranslatedText);
        textViewTranslatedTextInput = (TextView) findViewById(R.id.textViewTranslatedTextInput);
        if (YT.lang == "ru"){
            textViewTranslatedTextInput.setHint("Введите текст тут!");
            textViewTranslatedText.setText("Выбрано: Английский -> Русский");
        } else {
            textViewTranslatedTextInput.setHint("Введите текст тут!");
            textViewTranslatedText.setText("Выбрано: Русский -> Английский");
        }

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        setEmptuTitle();

        Button btnTranslate = (Button) findViewById(R.id.btnTranslate);
        textViewTranslatedText = (TextView) findViewById(R.id.textViewTranslatedText);
        textViewTranslatedTextInput = (TextView) findViewById(R.id.textViewTranslatedTextInput);

        class echoInf extends MainActivity{
            public void setTextToActivity(String s){
                textViewTranslatedText = (TextView) findViewById(R.id.textViewTranslatedText);
                textViewTranslatedText.setText(s);
            }
        }

        View.OnClickListener OnClickListenerT = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.btnTranslate:
                        MyTextLoader textLoader = new MyTextLoader();
                        textLoader.execute(YT.lang.toString(), textViewTranslatedTextInput.getText().toString());
                        int i = 0;
                        while (true){
                            i++;
                            Boolean s = YT.strIsEmpty();
                            if(s == true){
                                Log.i("Boolean s", "true");
                                textViewTranslatedText.setText(YT.strTranslate);
                                YT.strTranslate = "";
                                break;
                            } else {
                                Log.i("Boolean s", "falsh");
                                try {
                                    Thread.sleep(500);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                            if(i == 2){
                                break;
                            }

                        }
                }
            }
        };

        btnTranslate.setOnClickListener(OnClickListenerT);
    }


    public class MyTextLoader extends AsyncTask<String, Void, Void> {
        @Override
        public Void doInBackground(String... params) {

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
        }

    }


















}
