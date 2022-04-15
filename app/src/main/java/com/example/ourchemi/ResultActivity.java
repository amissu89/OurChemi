package com.example.ourchemi;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.example.ourchemi.interfaces.ChemResultEvent;
import com.example.ourchemi.models.Chemistry;
import com.example.ourchemi.models.DateObj;
import com.example.ourchemi.models.Person;
import com.example.ourchemi.models.ZodiacType;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ResultActivity extends AppCompatActivity {

    private Person me;
    private Person you;
    private Chemistry chemistry;

    private TabLayout       tabLayout;

    private ShowResultFragment  showResultFragment;
    private BigImageFragment    bigImageFragment;
    private WebViewFragment     webViewFragment;
    private BlankFragment       blankFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent infos = getIntent();
        me = (Person)infos.getSerializableExtra("me");
        you = (Person)infos.getSerializableExtra("you");
        chemistry = (Chemistry)infos.getSerializableExtra("chemistry");
        tabLayout = (TabLayout)findViewById(R.id.tabs);
        ViewPager2 viewPager2 = (ViewPager2)findViewById(R.id.view_pager);

        ViewPager2Adapter pagerAdapter = new ViewPager2Adapter(this);
        viewPager2.setAdapter(pagerAdapter);

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                switch(position)
                {
                    case 0:
                        showResultFragment = (ShowResultFragment) pagerAdapter.getFragment(position);
                        showResultFragment.setPerson(me, you, chemistry);
                        showResultFragment.showResult();
                        break;
                    case 1:
                        bigImageFragment = (BigImageFragment) pagerAdapter.getFragment(position);
                        break;
                    case 2:
                        webViewFragment = (WebViewFragment) pagerAdapter.getFragment(position);
                        break;
                    case 3:
                        blankFragment = (BlankFragment) pagerAdapter.getFragment(position);
                        break;
                    default:
                        System.out.println("default");
                }
            }
        });

        final List<String> tabElement = Arrays.asList("궁합결과", "Load Image", "WebView Text", "Blank");

        new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                TextView textview = new TextView(ResultActivity.this);
                textview.setText(tabElement.get(position));
                tab.setCustomView(textview);
            }
        }).attach();
    }
}