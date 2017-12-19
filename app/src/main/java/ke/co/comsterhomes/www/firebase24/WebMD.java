package ke.co.comsterhomes.www.firebase24;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebMD extends Activity {
    private WebView webView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_md);
        this.webView = (WebView) findViewById(R.id.webv2);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        WebViewClient webViewClient = new WebViewClient();
        webView.setWebViewClient(webViewClient);
        webView.loadUrl("https://www.webmd.com/drugs/2/index");
    }

    public boolean onKeyDown(final int keyCode, final KeyEvent event) {

        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
            webView.goBack();
//If there is history, then the canGoBack method will return ‘true’//
            return true;
        }

//If the button that’s been pressed wasn’t the ‘Back’ button, or there’s currently no
//WebView history, then the system should resort to its default behavior and return
//the user to the previous Activity//
        return super.onKeyDown(keyCode, event);
    }
}
