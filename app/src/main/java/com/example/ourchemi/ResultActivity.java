package com.example.ourchemi;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ourchemi.models.DateObj;
import com.example.ourchemi.models.Person;
import com.example.ourchemi.models.ZodiacType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class ResultActivity extends AppCompatActivity {

    Person me;
    Person you;

    private TextView htmlDoc;
    private String htmlContentInStringFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Person p1 = new Person();
        p1.setYear(1989);
        p1.setMonth(11);
        p1.setDay(6);
        p1.setMbti("ENFJ");

        Person p2 = new Person();
        p2.setYear(1982);
        p2.setMonth(1);
        p2.setDay(12);
        p2.setMbti("INFP");

        /*Intent infos = getIntent();
        me = (Person)infos.getSerializableExtra("me");
        you = (Person)infos.getSerializableExtra("you");
        System.out.println(me.toString());
        System.out.println(you.toString());*/
        getZodiac(p1);
        System.out.println("[p1]  " + p1.getZodiacSign());
        getZodiac(p2);
        System.out.println("[P2]  " + p2.getZodiacSign());

        JsoupAsyncTask jsoupAsyncTask = new JsoupAsyncTask();
        jsoupAsyncTask.execute(Constant.DDI_REQ_URL, "19891106");

    }

    private class JsoupAsyncTask extends AsyncTask<String, String, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(String... params) {
            try {
                /*
                <article class="contents">
                    <p>
                        <b>양력 1989년 11월 6일</b>은<br>
                        <b>음력 1989년 10월 8일</b>입니다.<br>
                        <b>평달</b>이고 <b>기사(己巳)년</b>입니다.
                    </p>
                    <p class="blue">
                        <a href="/cal/lunar_solar/list/1008?y=1">여기를 누르면 연도별 양력도 볼 수 있습니다.</a>
                    </p>
			    </article>
                * */
                String url = params[0] + params[1];
                Document doc = Jsoup.connect(url).get();

                Elements elems = doc.select(".contents p b");
                for(Element elem : elems)
                {
                    System.out.println(elem.toString());
                }

                /*for (Element link : links) {
                    htmlContentInStringFormat += (link.attr("abs:href")
                            + "(" + link.text().trim() + ")\n");
                }*/

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            //htmlDoc.setText(htmlContentInStringFormat);
        }
    }

    public void getZodiac(Person p)
    {
        DateObj obj = new DateObj(p.getMonth(), p.getDay());
        Constant.ZODIAC_MAP.forEach((key, value)->{
            System.out.println("key : " + key);
            if(obj.compareTo(value.getStart()) >= 0 &&
                    obj.compareTo(value.getEnd()) <= 0)
            {
                p.setZodiacSign(key);
            }

        });
        //염소자리일 경우 12.25 ~ 1.19는 해를 넘어가므로
        //추가 조건걸기
        if( (p.getMonth() == 1 && p.getDay() <= 19 ) ||
        p.getMonth() == 12 && p.getDay()>=25){
            p.setZodiacSign(ZodiacType.ARIES.getName());
        }
    }

    public void getKZodiac(Person p)
    {
        DateObj obj = new DateObj(p.getMonth(), p.getDay());
        Constant.ZODIAC_MAP.forEach((key, value)->{
            System.out.println("key : " + key);
            if(obj.compareTo(value.getStart()) >= 0 &&
                    obj.compareTo(value.getEnd()) <= 0)
            {
                p.setZodiacSign(key);
            }

        });
        //염소자리일 경우 12.25 ~ 1.19는 해를 넘어가므로
        //추가 조건걸기
        if( (p.getMonth() == 1 && p.getDay() <= 19 ) ||
                p.getMonth() == 12 && p.getDay()>=25){
            p.setZodiacSign(ZodiacType.ARIES.getName());
        }
    }

}