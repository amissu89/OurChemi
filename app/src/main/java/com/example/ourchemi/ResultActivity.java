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

    private DateObj dateObj = new DateObj();
    private String ddi;
    private String gapja;

    private TabLayout           tabLayout;

    private ShowResultFragment showResultFragment;
    private BigImageFragment bigImageFragment;
    private WebViewFragment webViewFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        tabLayout = (TabLayout)findViewById(R.id.tabs);
        ViewPager2 viewPager2 = (ViewPager2)findViewById(R.id.view_pager);

        ViewPager2Adapter pagerAdapter = new ViewPager2Adapter(this);
        viewPager2.setAdapter(pagerAdapter);

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                System.out.println("onPageSelected : " + position );
                switch(position)
                {
                    case 0:
                        showResultFragment = (ShowResultFragment) pagerAdapter.getFragment(position);
                        showResultFragment.setPerson(me, you);
                        System.out.println(showResultFragment.getParam());
                        break;
                    case 1:
                        bigImageFragment = (BigImageFragment) pagerAdapter.getFragment(position);
                        System.out.println(bigImageFragment.getParam());
                        break;
                    case 2:
                        webViewFragment = (WebViewFragment) pagerAdapter.getFragment(position);
                        System.out.println(webViewFragment.getParam());
                        break;
                    default:
                        System.out.println("default");
                }
            }
        });



        final List<String> tabElement = Arrays.asList("궁합결과", "Load Image", "WebView Text");

        new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                System.out.println("onConfigureTab : " + position);
                TextView textview = new TextView(ResultActivity.this);
                textview.setText(tabElement.get(position));
                tab.setCustomView(textview);
            }
        }).attach();

         /*Intent infos = getIntent();
        me = (Person)infos.getSerializableExtra("me");
        you = (Person)infos.getSerializableExtra("you");
        System.out.println(me.toString());
        System.out.println(you.toString());*/

        me = new Person();
        me.setBirthday(new DateObj(1989,11,6));
        me.setMbti("ENFJ");

        you = new Person();
        you.setBirthday(new DateObj(1985, 1, 12));
        you.setMbti("INFP");

        getZodiac(me);
        getZodiac(you);

        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executor.execute(() -> {
            try {
                me.copy(getKZodiac(me));
                you.copy(getKZodiac(you));
            } catch (IOException e) {
                e.printStackTrace();
            }
            handler.post(() -> {
                me.setCompleteInfo(true);
                you.setCompleteInfo(true);
                System.out.println("p1 : " + me.toString());
                System.out.println("p2 : " + you.toString());
            });
        });
    }

    public Person getKZodiac(Person p) throws IOException {

        String url = Constant.DDI_REQ_URL + p.getBirthday().toString();
        System.out.println(url);
        Document doc = Jsoup.connect(url).get();

        Elements elems = doc.select(".contents p b");
        for(Element elem : elems)
        {
            String str = elem.toString();
            if(str.contains("음력"))
            {
                System.out.println(elem.toString());
                Pattern pattern = Pattern.compile("\\d+");
                Matcher matcher = pattern.matcher(elem.toString());
                int cnt = 0;
                while(matcher.find()){
                    int val = Integer.parseInt(matcher.group(0));
                    if(cnt == 0){
                        dateObj.setYear(val);
                    }
                    else if(cnt == 1){
                        dateObj.setMonth(val);
                    }
                    else if(cnt == 2){
                        dateObj.setDay(val);
                        p.setLunarBirthday(dateObj);
                    }
                    cnt++;

                }
            }
            else if (str.contains("년")
                    && !str.contains("양력") && !str.contains("음력"))
            {
                System.out.println(elem.toString());
                Pattern pattern = Pattern.compile("[가-힣]{2}");
                Matcher matcher = pattern.matcher(elem.toString());
                while(matcher.find())
                {
                    gapja = matcher.group(0);
                    ddi = gapja.substring(1);

                    p.setDdi(ddi);
                    p.setGapja(gapja);
                    p.setCompleteInfo(true);
                    return p;
                }
            }
        }
        return null;
    }

    public void getZodiac(Person p)
    {
        DateObj obj = new DateObj(p.getBirthday().getMonth(),
                p.getBirthday().getDay());
        Constant.ZODIAC_MAP.forEach((key, value)->{
            if(obj.compareTo(value.getStart()) >= 0 &&
                    obj.compareTo(value.getEnd()) <= 0)
            {
                p.setZodiacSign(key);
            }

        });
        //염소자리일 경우 12.25 ~ 1.19는 해를 넘어가므로
        //추가 조건걸기
        if( obj.getMonth() == 1 && obj.getDay() <= 19 ||
        obj.getMonth() == 12 && obj.getDay()>=25){
            p.setZodiacSign(ZodiacType.ARIES.getName());
        }
    }
}