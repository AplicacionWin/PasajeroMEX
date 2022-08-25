package com.nikola.user.ui.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.nikola.user.NewUtilsAndPref.sharedpref.PrefKeys;
import com.nikola.user.NewUtilsAndPref.sharedpref.PrefUtils;
import com.nikola.user.R;
import com.nikola.user.Utils.Const;
import com.nikola.user.network.ApiManager.APIClient;
import com.nikola.user.network.ApiManager.APIInterface;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Mahesh on 3/1/2017.
 */

public class PQRSActivity extends AppCompatActivity {

    @BindView(R.id.pqrs_back)
    ImageButton pqrsBack;
    @BindView(R.id.actionbar_lay)
    RelativeLayout actionbarLay;
    @BindView(R.id.webView)
    WebView webView;
    @BindView(R.id.web_loader)
    ProgressBar webLoader;
    @BindView(R.id.helpContent)
    TextView helpContent;
    APIInterface apiInterface;
    PrefUtils prefUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        setContentView(R.layout.pqrsview);
        ButterKnife.bind(this);
        apiInterface = APIClient.getClient().create(APIInterface.class);
        prefUtils = PrefUtils.getInstance(getApplicationContext());
        //https://aplicacionwin.com/cuestionario/conductor?id
        //https://www.aplicacionwin.com/subida-documentos?codPais=57&id=456
        String url  = "https://aplicacionwin.com/cuestionario/conductor?id="+(prefUtils.getIntValue(PrefKeys.USER_ID,0)+"&codPais=57");
        try {
        webView.getSettings().setJavaScriptEnabled(true); // enable javascript

        final Activity activity = this;

        webView.setWebViewClient(new WebViewClient() {
        });

            webView.loadUrl(url);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            Toast.makeText(this, getText(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
        }

        pqrsBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
