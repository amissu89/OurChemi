package com.example.ourchemi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.util.Linkify;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ourchemi.interfaces.ChemResultEvent;
import com.example.ourchemi.models.Chemistry;
import com.example.ourchemi.models.DateObj;
import com.example.ourchemi.models.KZodiacType;
import com.example.ourchemi.models.MBTIType;
import com.example.ourchemi.models.Person;
import com.example.ourchemi.models.ZodiacType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements ChemResultEvent {

    public static final int BIRTHDAY_LEN = 8;
    public static final String EType = "E";
    public static final String IType = "I";
    public static final String SType = "S";
    public static final String NType = "N";
    public static final String TType = "T";
    public static final String FType = "F";
    public static final String JType = "J";
    public static final String PType = "P";

    public static final int CHECKED = 1;
    public static final int UNCHECKED = 0;

    private Person me;
    private Person you;
    private Chemistry chemistry;

    private TextView notice;

    private int isExtraversion;
    private int isSensing;
    private int isIntuition;
    private int isJudging;

    private boolean pressMeBtn;

    EditText birthDay;
    String preBirthday;


    private DateObj dateObj = new DateObj();
    private String ddi;
    private String gapja;

    ActivityResultLauncher<Intent> startActivityResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        isExtraversion = Constant.NOK;
        isSensing = Constant.NOK;
        isIntuition = Constant.NOK;
        isJudging = Constant.NOK;

        notice = (TextView) findViewById(R.id.tv_notice);

        birthDay = (EditText) findViewById(R.id.editTextDate);
        birthDay.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                preBirthday = charSequence.toString();
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().equals(preBirthday)) {
                    return;
                }

                if (charSequence.toString().length() < BIRTHDAY_LEN) {
                    return;
                }

                String strBirthDay = charSequence.toString();
                int year = Integer.parseInt(strBirthDay.substring(0, 4));
                int month = Integer.parseInt(strBirthDay.substring(4, 6));
                int day = Integer.parseInt(strBirthDay.substring(6, 8));

                if (pressMeBtn == true) {
                    me.setBirthday(new DateObj(year, month, day));
                } else {
                    you.setBirthday(new DateObj(year, month, day));
                }
                notice.setText("MBTI를 선택하세요");
                checkInputValidAll();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        me = new Person();
        you = new Person();
        pressMeBtn = false;

        if (me.isCompleteInfo() == false && you.isCompleteInfo() == false) {
            notice.setText(getString(R.string.me) + " 버튼을 클릭하세요.");
        }

        Pattern pattern = Pattern.compile("링크");
        Linkify.TransformFilter transformFilter = new Linkify.TransformFilter() {
            @Override
            public String transformUrl(Matcher matcher, String s) {
                return "";
            }
        };
        TextView tv_link = (TextView) findViewById(R.id.tv_mtbi_link);
        tv_link.setText("링크");

        Linkify.addLinks(tv_link, pattern, "https://www.16personalities.com/free-personality-test", null, transformFilter);

        startActivityResult = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                           System.out.println("go to result activity");
                        }
                    }
                });
    }

    public void initViewItem() {
        ((EditText) findViewById(R.id.editTextDate)).setText(null);
        ((RadioGroup) findViewById(R.id.rg_ei)).clearCheck();
        ((RadioGroup) findViewById(R.id.rg_sn)).clearCheck();
        ((RadioGroup) findViewById(R.id.rg_jp)).clearCheck();
        ((RadioGroup) findViewById(R.id.rg_tf)).clearCheck();
    }

    public void checkInputValidAll() {
        boolean me_validate1 = false;
        boolean me_validate2 = false;
        boolean me_valid = false;
        boolean you_validate1 = false;
        boolean you_validate2 = false;
        boolean you_valid = false;

        me_validate1 = checkBirthdayValidate(me);
        if (me.getMbti() != "")
            me_validate2 = true;

        me_valid = me_validate1 & me_validate2;

        you_validate1 = checkBirthdayValidate(you);
        if (you.getMbti() != "")
            you_validate2 = true;
        you_valid = you_validate1 & you_validate2;

        //나 ok, 상대방 nok
        //나 nok, 상대방 ok
        //나 ok, 상대방 ok
        if (me_valid && !you_valid) {
            notice.setText("너 버튼을 클릭하세요");
        } else if (!me_valid && you_valid) {
            notice.setText("나 버튼을 클릭하세요");
        } else if (me_valid && you_valid) {
            notice.setText("결과보기 버튼을 클릭하세요");

        }
    }

    /**
     * Called when the user touches the button
     */
    public void onClickMeBtn(View view) {
        initViewItem();
        pressMeBtn = true;
        if (me.isCompleteInfo() == false) {
            notice.setText("생년월일을 입력하세요.");
        }
        view.setSelected(!view.isSelected());
        Button youBtn = (Button) findViewById(R.id.you_btn);
        EditText et = (EditText)findViewById(R.id.editTextDate);
        et.setText("19891106");
        youBtn.setSelected(!view.isSelected());
    }

    public void onClickYouBtn(View view) {
        initViewItem();
        pressMeBtn = false;
        if (you.isCompleteInfo() == false) {
            notice.setText("생년월일을 입력하세요.");
        }
        view.setSelected(!view.isSelected());
        Button meBtn = (Button) findViewById(R.id.me_btn);
        meBtn.setSelected(!view.isSelected());

        EditText et = (EditText)findViewById(R.id.editTextDate);
        et.setText("19850112");
    }



    /**
     * Called when the user touches the button
     */
    public void showResult(View view) {
        getZodiac(me);
        getZodiac(you);

        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executor.execute(() -> {
            try {
                me.copyByVal(getKZodiac(me));
                you.copyByVal(getKZodiac(you));
            } catch (IOException e) {
                e.printStackTrace();
            }
            handler.post(() -> {
                me.setCompleteInfo(true);
                you.setCompleteInfo(true);
                System.out.println("me : " + me.toString());
                System.out.println("you : " + you.toString());
                calculate(me, you);
                onResult();
            });
        });
    }



    @Override
    public void onResult() {
        // Do something in response to button click
        //https://stickode.tistory.com/187 참고함
        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra("me", me);
        intent.putExtra("you", you);
        intent.putExtra("chemistry", chemistry);
        startActivityResult.launch(intent);
    }


    public void calculate(Person p1, Person p2)
    {
        //1. mbti 궁합 계산
        Integer mbti_res = getMbtiChemi(p1, p2);
        //2. 별자리 궁합 계산
        Integer zodiac_res = getZodiacChemi(p1,p2);
        //3. 띠 궁합 계산
        Integer kzodiac_res = getkZodiacChemi(p1, p2);
        Double res = 1 + (mbti_res + zodiac_res + kzodiac_res) * 0.33;

        chemistry = new Chemistry(p1, p2,
                mbti_res, zodiac_res, kzodiac_res, res);

        System.out.printf("%d, %d %d", mbti_res, zodiac_res, kzodiac_res);
    }

    public Integer getkZodiacChemi(Person p1, Person p2) {
        int p1_num = Constant.NOK;
        int p2_num = Constant.NOK;
        for(KZodiacType type : KZodiacType.values()) {
            if (p1_num == Constant.NOK && p1.getDdi().equals(type.getName())) {
                p1_num = type.getValue();
            }
            if (p2_num == Constant.NOK && p2.getDdi().equals(type.getName())) {
                p2_num = type.getValue();
            }
            if(p1_num != Constant.NOK && p2_num != Constant.NOK)
                break;
        }
        if(p1_num != Constant.NOK && p2_num != Constant.NOK)
            return Constant.KZODIAC_SCROE[p1_num][p2_num];
        else
            return Constant.NOK;
    }

    public Integer getZodiacChemi(Person p1, Person p2) {
        int p1_num = Constant.NOK;
        int p2_num = Constant.NOK;
        for(ZodiacType type : ZodiacType.values()) {
            if (p1_num == Constant.NOK && p1.getZodiacSign().equals(type.getName())) {
                p1_num = type.getValue();
            }
            if (p2_num == Constant.NOK && p2.getZodiacSign().equals(type.getName())) {
                p2_num = type.getValue();
            }
            if(p1_num != Constant.NOK && p2_num != Constant.NOK)
                break;
        }
        if(p1_num != Constant.NOK && p2_num != Constant.NOK)
            return Constant.ZODIAC_SCORE[p1_num][p2_num];
        else
            return Constant.NOK;
    }

    public Integer getMbtiChemi(Person p1, Person p2)
    {
        int p1_num = Constant.NOK;
        int p2_num = Constant.NOK;

        for(MBTIType type : MBTIType.values()) {
            if (p1_num == Constant.NOK && p1.getMbti().equals(type.getName())) {
                p1_num = type.getValue();
            }
            if (p2_num == Constant.NOK && p2.getMbti().equals(type.getName())) {
                p2_num = type.getValue();
            }
            if(p1_num != Constant.NOK && p2_num != Constant.NOK)
                break;
        }
        if(p1_num != Constant.NOK && p2_num != Constant.NOK)
            return Constant.MBTI_SCORE[p1_num][p2_num];
        else
            return Constant.NOK;
    }

    public boolean checkBirthdayValidate(Person p) {
        if (p.getBirthday().getYear() != Constant.NOK
                && p.getBirthday().getMonth() != Constant.NOK
                && p.getBirthday().getDay() != Constant.NOK)
            return true;
        else
            return false;
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
            p.setZodiacSign(ZodiacType.CARPRICORN.getName());
        }
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

    public void onRadioButtonClickedAll() {
        //각 그룹에서 1개씩 체크된 상태인지 검사
        if (isExtraversion != Constant.NOK
                && isSensing != Constant.NOK
                && isIntuition != Constant.NOK
                && isJudging != Constant.NOK) {
            String mbti = makeMBTI();

            if (pressMeBtn)
                me.setMbti(mbti);
            else
                you.setMbti(mbti);
            notice.setText("결과보기 버튼을 클릭하세요.");
            checkInputValidAll();
        }
    }

    public void onRadioButtonClicked_mbti(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.rb_e:
                isExtraversion = checked ? CHECKED : UNCHECKED;
                break;
            case R.id.rb_i:
                isExtraversion = checked ? UNCHECKED : CHECKED;
                break;
            case R.id.rb_s:
                isSensing = checked ? CHECKED : UNCHECKED;
                break;
            case R.id.rb_n:
                isSensing = checked ? UNCHECKED : CHECKED;
                break;
            case R.id.rb_t:
                isIntuition = checked ? CHECKED : UNCHECKED;
                break;
            case R.id.rb_f:
                isIntuition = checked ? UNCHECKED : CHECKED;
                break;
            case R.id.rb_j:
                isJudging = checked ? CHECKED : UNCHECKED;
                break;
            case R.id.rb_p:
                isJudging = checked ? UNCHECKED : CHECKED;
                break;
            default:
                System.out.println("NO RADIOBUTTON ID....");
        }
        onRadioButtonClickedAll();
    }

    public String makeMBTI() {
        String mbti = "";
        mbti = isExtraversion == CHECKED ? EType : IType;
        mbti += isSensing == CHECKED ? SType : NType;
        mbti += isIntuition == CHECKED ? TType : FType;
        mbti += isJudging == CHECKED ? JType : PType;
        return mbti;
    }
}