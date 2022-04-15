package com.example.ourchemi;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.util.Linkify;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ourchemi.interfaces.ChemResultEvent;
import com.example.ourchemi.models.Chemistry;
import com.example.ourchemi.models.DateObj;
import com.example.ourchemi.models.Person;
import com.example.ourchemi.observer.PersonInfo;
import com.example.ourchemi.observer.Publisher;
import com.example.ourchemi.repository.DBHelper;
import com.example.ourchemi.repository.User;
import com.fasterxml.jackson.core.JsonProcessingException;

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

    private DBHelper dbHelper;
    private SQLiteDatabase sqliteDb;
    ActivityResultLauncher<Intent> startActivityResult;
    private Publisher publisher;

    @SuppressLint("Range")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DBHelper(getApplicationContext());
        sqliteDb = dbHelper.getWritableDatabase();

        me = dbHelper.selectByName(sqliteDb, "me");
        you = dbHelper.selectByName(sqliteDb, "you");

        if(me.isCompleteInfo() == false || you.isCompleteInfo() == false)
        {
            dbHelper.onUpgrade(sqliteDb, 0,0);
            isExtraversion = Constant.NOK;
            isSensing = Constant.NOK;
            isIntuition = Constant.NOK;
            isJudging = Constant.NOK;

            me = new Person();
            you = new Person();
            pressMeBtn = false;
        }
        else
        {
            calculate(me, you);
            initViewItem(me);
            ((RadioButton) findViewById(R.id.rb_me)).setChecked(true);
            pressMeBtn = true;
        }

        publisher= new Publisher();
        publisher.attach(new PersonInfo(me));
        publisher.attach(new PersonInfo(you));
//
//        if(me == null || you == null)
//        {
//            isExtraversion = Constant.NOK;
//            isSensing = Constant.NOK;
//            isIntuition = Constant.NOK;
//            isJudging = Constant.NOK;
//
//            me = new Person();
//            you = new Person();
//            pressMeBtn = false;
//        }

//        //저장된 파일이 있었다면
////        try {
//
////            chemistry = CommonAPI.readStateFromFile(getApplicationContext());
////            if (chemistry != null) {
////                me = chemistry.getP1();
////                you = chemistry.getP2();
////                initViewItem(me);
////                ((RadioButton) findViewById(R.id.rb_me)).setChecked(true);
//            }
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }

        Cursor cursor = dbHelper.getAllData(sqliteDb);

        try {
            System.out.println("Print all saved users");
            while(cursor.moveToNext())
            {
                System.out.print("ID : " + cursor.getString(cursor.getColumnIndex(User.UserEntry.COL_ID)));
                System.out.print(", NAME : " + cursor.getString(cursor.getColumnIndex(User.UserEntry.COL_NAME)));
                System.out.print(", BIRTHDAY : " + cursor.getString(cursor.getColumnIndex(User.UserEntry.COL_BIRTHDAY)));
                System.out.print(", LUNAR : " + cursor.getString(cursor.getColumnIndex(User.UserEntry.COL_LUNAR_BIRTHDAY)));
                System.out.print(", MBTI : " + cursor.getString(cursor.getColumnIndex(User.UserEntry.COL_MBTI)));
                System.out.print(", DDI : " + cursor.getString(cursor.getColumnIndex(User.UserEntry.COL_DDI)));
                System.out.print(", GAPJA : " + cursor.getString(cursor.getColumnIndex(User.UserEntry.COL_GAPJA)));
                System.out.println(", ZODIAC : " + cursor.getString(cursor.getColumnIndex(User.UserEntry.COL_ZODIAC)));
            }
        } catch (Exception e)
            {
                System.out.println(e.getMessage());
            }



        /*if (savedInstanceState != null) {
            me = (Person) savedInstanceState.getSerializable("me");
            you = (Person) savedInstanceState.getSerializable("you");
            chemistry = (Chemistry) savedInstanceState.getSerializable("chemistry");
            notice.setText("savedInstanceState를 불러왔습니다.");
        }*/

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

                if (charSequence.toString().length() < Constant.BIRTHDAY_LEN) {
                    return;
                }

                String strBirthDay = charSequence.toString();
                int year = Integer.parseInt(strBirthDay.substring(0, 4));
                int month = Integer.parseInt(strBirthDay.substring(4, 6));
                int day = Integer.parseInt(strBirthDay.substring(6, 8));

                if (pressMeBtn == true) {
                    me.setBirthday(new DateObj(year, month, day));
                    publisher.notifyObserverList(me);
                } else {
                    you.setBirthday(new DateObj(year, month, day));
                    publisher.notifyObserverList(you);
                }
                notice.setText("MBTI를 선택하세요");
                checkInputValidAll();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        if (me.isCompleteInfo() == false && you.isCompleteInfo() == false) {
            notice.setText(getString(R.string.me) + " 버튼을 클릭하세요.");
        }

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

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putSerializable("me", me);
        outState.putSerializable("you", you);
        outState.putSerializable("chemistry", chemistry);

        Long res = -1L;
        if(dbHelper.isExistByName(sqliteDb, "me")) {
            res = dbHelper.updateColumnByName(sqliteDb,"me", me);
        }
        else{
            res = dbHelper.insertColumn(sqliteDb, "me", me);
        }

        if(dbHelper.isExistByName(sqliteDb, "you")) {
            res = dbHelper.updateColumnByName(sqliteDb,"you", you);
        }
        else{
            res = dbHelper.insertColumn(sqliteDb, "you", you);
        }

        try {
            CommonAPI.saveThisStateToFile(getApplicationContext(), chemistry);
        } catch (JsonProcessingException e) {

            e.printStackTrace();
        }
    }

    public void initViewItem() {
        ((EditText) findViewById(R.id.editTextDate)).setText(null);
        ((RadioGroup) findViewById(R.id.rg_ei)).clearCheck();
        ((RadioGroup) findViewById(R.id.rg_sn)).clearCheck();
        ((RadioGroup) findViewById(R.id.rg_jp)).clearCheck();
        ((RadioGroup) findViewById(R.id.rg_tf)).clearCheck();
    }

    public void initViewItem(Person p) {
        if(p.isCompleteInfo() == false)
        {
            initViewItem();
            return;
        }

        ((EditText) findViewById(R.id.editTextDate)).setText(p.getBirthday().toString());
        String mbti = p.getMbti();

        int checked = Constant.CHECKED;
        int unchecked = Constant.UNCHECKED;

        if (mbti.contains(Constant.EType))
        {
            ((RadioButton) findViewById(R.id.rb_e)).setChecked(true);
            isExtraversion = checked;
        }
        else
        {
            ((RadioButton) findViewById(R.id.rb_i)).setChecked(true);
            isExtraversion = unchecked;
        }

        if (mbti.contains(Constant.SType))
        {
            ((RadioButton) findViewById(R.id.rb_s)).setChecked(true);
            isSensing = checked;
        }
        else
        {
            ((RadioButton) findViewById(R.id.rb_n)).setChecked(true);
            isSensing = unchecked;
        }

        if (mbti.contains(Constant.TType)) {
            ((RadioButton) findViewById(R.id.rb_t)).setChecked(true);
            isIntuition = checked;
        }
        else{
            ((RadioButton) findViewById(R.id.rb_f)).setChecked(true);
            isIntuition = unchecked;
        }

        if (mbti.contains(Constant.JType)){
            ((RadioButton) findViewById(R.id.rb_j)).setChecked(true);
            isJudging = checked;
        }
        else{
            ((RadioButton) findViewById(R.id.rb_p)).setChecked(true);
            isJudging = unchecked;
        }
    }

    public void checkInputValidAll() {
        boolean me_validate1 = false;
        boolean me_validate2 = false;
        boolean me_valid = false;
        boolean you_validate1 = false;
        boolean you_validate2 = false;
        boolean you_valid = false;

        me_validate1 = CommonAPI.checkBirthdayValidate(me);
        if (me.getMbti() != "")
            me_validate2 = true;

        me_valid = me_validate1 & me_validate2;

        you_validate1 = CommonAPI.checkBirthdayValidate(you);
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
    public void showResult(View view) {
        Calculator.getZodiac(me);
        Calculator.getZodiac(you);

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

    public void visitMBTITest(View view){
        Intent link = new Intent(Intent.ACTION_VIEW, Uri.parse(Constant.MBTI_LINK));
        startActivity(link);
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

    public void calculate(Person p1, Person p2) {
        //1. mbti 궁합 계산
        Integer mbti_res = Calculator.getMbtiChemi(p1, p2);
        //2. 별자리 궁합 계산
        Integer zodiac_res = Calculator.getZodiacChemi(p1, p2);
        //3. 띠 궁합 계산
        Integer kzodiac_res = Calculator.getkZodiacChemi(p1, p2);
        Double res = 1 + (mbti_res + zodiac_res + kzodiac_res) * 0.33;

        chemistry = new Chemistry(p1, p2,
                mbti_res, zodiac_res, kzodiac_res, res);
    }

    public Person getKZodiac(Person p) throws IOException {

        String url = Constant.DDI_REQ_URL + p.getBirthday().toString();
        System.out.println(url);
        Document doc = Jsoup.connect(url).get();

        Elements elems = doc.select(".contents p b");
        for (Element elem : elems) {
            String str = elem.toString();
            if (str.contains("음력")) {
                System.out.println(elem.toString());
                Pattern pattern = Pattern.compile("\\d+");
                Matcher matcher = pattern.matcher(elem.toString());
                int cnt = 0;
                while (matcher.find()) {
                    int val = Integer.parseInt(matcher.group(0));
                    if (cnt == 0) {
                        dateObj.setYear(val);
                    } else if (cnt == 1) {
                        dateObj.setMonth(val);
                    } else if (cnt == 2) {
                        dateObj.setDay(val);
                        p.setLunarBirthday(dateObj);
                    }
                    cnt++;

                }
            } else if (str.contains("년")
                    && !str.contains("양력") && !str.contains("음력")) {
                System.out.println(elem.toString());
                Pattern pattern = Pattern.compile("[가-힣]{2}");
                Matcher matcher = pattern.matcher(elem.toString());
                while (matcher.find()) {
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
            String mbti = CommonAPI.makeMBTI(isExtraversion,
                    isSensing, isIntuition, isJudging);

            if (pressMeBtn)
                me.setMbti(mbti);
            else
                you.setMbti(mbti);
            notice.setText("결과보기 버튼을 클릭하세요.");
            checkInputValidAll();
        }
    }

    public void onRadioButtonClicked_meyou(View view){
        birthDay = (EditText) findViewById(R.id.editTextDate);

        if( !CommonAPI.validation(birthDay.getText().toString(),
                isExtraversion, isSensing, isIntuition, isJudging))
        {
            alert();
            ((RadioButton)view).setChecked(true);
            return;
        }

        switch(view.getId()){
            case R.id.rb_me:
            {
                saveInformation(you);
                pressMeBtn = true;
                initViewItem(me);
                break;
            }
            case R.id.rb_you:
            {
                saveInformation(me);
                pressMeBtn = false;
                initViewItem(you);
                break;
            }
            default:
                System.out.println("NO you or I : " + view.getId());
        }
    }

    public void saveInformation(Person p)
    {
        String mbti = CommonAPI.makeMBTI(isExtraversion, isSensing,isIntuition, isJudging);
        p.setMbti(mbti);
        p.setBirthday(CommonAPI.getDateObjFromEditText(birthDay.getText().toString()));
        p.setCompleteInfo(true);
    }


    public void onRadioButtonClicked_mbti(View view) {
        boolean isChecked = ((RadioButton) view).isChecked();
        int checked = Constant.CHECKED;
        int unchecked = Constant.UNCHECKED;
        switch (view.getId()) {
            case R.id.rb_e:
                isExtraversion = isChecked ? checked : unchecked;
                break;
            case R.id.rb_i:
                isExtraversion = isChecked ? unchecked : checked;
                break;
            case R.id.rb_s:
                isSensing = isChecked ? checked : unchecked;
                break;
            case R.id.rb_n:
                isSensing = isChecked ? unchecked : checked;
                break;
            case R.id.rb_t:
                isIntuition = isChecked ? checked : unchecked;
                break;
            case R.id.rb_f:
                isIntuition = isChecked ? unchecked : checked;
                break;
            case R.id.rb_j:
                isJudging = isChecked ? checked : unchecked;
                break;
            case R.id.rb_p:
                isJudging = isChecked ? unchecked : checked;
                break;
            default:
                System.out.println("NO RADIOBUTTON ID....");
        }
        onRadioButtonClickedAll();
    }

    public void alert()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("WARN");
        builder.setMessage("값을 확인하십시오. ");
        builder.setNeutralButton("닫기",
                new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getApplicationContext(),
                                "닫기선택", Toast.LENGTH_LONG).show();
                    }
                });
        builder.show();
    }
}