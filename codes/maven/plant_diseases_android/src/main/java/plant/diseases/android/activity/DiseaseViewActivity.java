package plant.diseases.android.activity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Toast;
import com.zyt.android.html.SimpleWebChromeClient;
import com.zyt.android.html.SimpleWebViewClient;
import plant.diseases.android.R;

/**
 * Created by Timothy on 2014/6/28.
 */
public class DiseaseViewActivity extends BaseActivity {

	public static final String PARAM_ARTICLE_URL = "articleUrl";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_disease);
		initHeader();
		init();
	}

	private void init() {
		String articleUrl = getIntent().getStringExtra(PARAM_ARTICLE_URL);
		WebView webView = (WebView) findViewById(R.id.wvDisease);
		webView.getSettings().setUseWideViewPort(true);
		webView.getSettings().setJavaScriptEnabled(true);
		// webView.addJavascriptInterface(javaScriptInterface, "Android");
		webView.setWebChromeClient(new SimpleWebChromeClient());
		webView.setWebViewClient(new SimpleWebViewClient(this));
		webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
		try {
			// String articlePath = ArticlePageService.getLocalArticlePage(article);
			webLoad(webView, articleUrl);
		} catch (Exception e) {
			Toast.makeText(this, "目标文件不存在", Toast.LENGTH_SHORT);
		}

	}

	private void webLoad(WebView webView, String articlePath) {
		webView.loadUrl("file:///android_asset/html/" + articlePath);
	}
}
