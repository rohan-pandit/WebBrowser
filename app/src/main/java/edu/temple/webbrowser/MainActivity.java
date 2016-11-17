package edu.temple.webbrowser;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.ArrayList;
import android.app.FragmentManager;
import android.view.MenuInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.net.Uri;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends AppCompatActivity {


    WebViewFragment webViewFrag;
    ArrayList<WebViewFragment> fragments;
    int currentIndex;
    FragmentManager fm;
    EditText editUrl;
    Button goButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fm = getFragmentManager();

        fragments = new ArrayList<>();

        webViewFrag = new WebViewFragment();

        editUrl = (EditText) findViewById(R.id.url);
        goButton = (Button) findViewById(R.id.goButton);

        Uri urlData = getIntent().getData();

        if(urlData != null){
            String url = urlData.toString();
            webViewFrag.setUserURL(url);
        }

        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                String userUrl = editUrl.getText().toString();

                if(userUrl.startsWith("https://")){
                    webViewFrag.setUserURL(userUrl);
                }else{
                    webViewFrag.setUserURL("https://" + userUrl);
                }
            }
        });

        fragments.add(webViewFrag);

        currentIndex = fragments.size() - 1;

        fm.beginTransaction()
                .add(R.id.webFrag, fragments.get(currentIndex))
                .commit();
        fm.executePendingTransactions();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater mf = getMenuInflater();
        mf.inflate(R.menu.action_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem){

        menuItem.getItemId();

        final WebViewFragment newFrag;

        editUrl.setText("");

        if(menuItem.getItemId() == R.id.backButton){
            if(currentIndex > 0){
                currentIndex--;
                fm.beginTransaction()
                        .replace(R.id.webFrag, fragments.get(currentIndex))
                        .commit();
                fm.executePendingTransactions();
            }
            return true;
        }else if(menuItem.getItemId() == R.id.nextButton){

            if(currentIndex < fragments.size() - 1){
                currentIndex++;
                fm.beginTransaction()
                        .replace(R.id.webFrag, fragments.get(currentIndex))
                        .commit();
                fm.executePendingTransactions();
            }
            return true;
        }else if(menuItem.getItemId() == R.id.newButton){

            newFrag = new WebViewFragment();

            editUrl = (EditText) findViewById(R.id.url);

            goButton = (Button) findViewById(R.id.goButton);

            goButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v){
                    String userUrl = editUrl.getText().toString();

                    if(userUrl.startsWith("https://")){
                        newFrag.setUserURL(userUrl);
                    }else{
                        newFrag.setUserURL("https://" + userUrl);
                    }
                }
            });

            fragments.add(newFrag);
            currentIndex++;

            fm.beginTransaction()
                    .replace(R.id.webFrag, fragments.get(currentIndex))
                    .commit();
            fm.executePendingTransactions();

            return true;
        }

        return super.onOptionsItemSelected(menuItem);
    }
}
