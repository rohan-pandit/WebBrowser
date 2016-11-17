package edu.temple.webbrowser;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.app.Activity;
import android.webkit.WebViewClient;


public class WebViewFragment extends Fragment {

    View v;
    WebView webView;
    String userUrl;

    public WebViewFragment() {
        // Required empty public constructor
    }

    @Override public void onAttach(Activity act){
        super.onAttach(act);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_web_view, container, false);

        webView = (WebView) v.findViewById(R.id.webView);

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        webView.setWebViewClient(new WebViewClient());

        if(userUrl != null) {
            webView.loadUrl(userUrl);
        }

        return v;

    }

    public void setUserURL(String url){
        webView.loadUrl(url);
        userUrl = url;

    }

}
