package info.stekolschikov.ytranslate;

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
import java.net.URL;
import java.net.URLEncoder;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {

    YandexTranslate YT = new YandexTranslate();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Button btnTranslate = (Button) findViewById(R.id.btnTranslate);
        final TextView textViewTranslatedText = (TextView) findViewById(R.id.textViewTranslatedText);

        View.OnClickListener OnClickListenerT = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.btnTranslate:
                        try {

                            textViewTranslatedText.setText(YT.translate("ru","привет"));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                }
            }
        };

        btnTranslate.setOnClickListener(OnClickListenerT);

    }
}
