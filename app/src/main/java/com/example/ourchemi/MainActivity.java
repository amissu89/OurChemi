package com.example.ourchemi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.util.Linkify;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.ourchemi.models.DateObj;
import com.example.ourchemi.models.Person;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.transform.Result;

public class MainActivity extends AppCompatActivity {

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

    private TextView notice;

    private int isExtraversion;
    private int isSensing;
    private int isIntuition;
    private int isJudging;

    private boolean pressMeBtn;

    EditText birthDay;
    String preBirthday;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        isExtraversion = Constant.NOK;
        isSensing = Constant.NOK;
        isIntuition = Constant.NOK;
        isJudging = Constant.NOK;

        notice = (TextView) findViewById(R.id.tv_notice);

        birthDay = (EditText)findViewById(R.id.editTextDate);
        birthDay.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                preBirthday = charSequence.toString();
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.toString().equals(preBirthday)){
                    return;
                }

                if(charSequence.toString().length() < BIRTHDAY_LEN ){
                    return;
                }

                String strBirthDay = charSequence.toString();
                int year = Integer.parseInt(strBirthDay.substring(0, 4));
                int month = Integer.parseInt(strBirthDay.substring(4, 6));
                int day = Integer.parseInt(strBirthDay.substring(6,8));

                if(pressMeBtn == true)
                {
                    me.setBirthday(new DateObj(year, month, day));
                }
                else{
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

        if(me.isCompleteInfo() == false && you.isCompleteInfo() == false)
        {
            notice.setText(getString(R.string.me) + " 버튼을 클릭하세요.");
        }

        Pattern pattern = Pattern.compile("링크");
        Linkify.TransformFilter transformFilter = new Linkify.TransformFilter() {
            @Override
            public String transformUrl(Matcher matcher, String s) {
                return "";
            }
        };
        TextView tv_link = (TextView)findViewById(R.id.tv_mtbi_link);
        tv_link.setText("링크");

        Linkify.addLinks(tv_link, pattern, "https://www.16personalities.com/free-personality-test", null, transformFilter);

    }

    public void initViewItem(){
        ((EditText) findViewById(R.id.editTextDate)).setText(null);
        ((RadioGroup) findViewById(R.id.rg_ei)).clearCheck();
        ((RadioGroup) findViewById(R.id.rg_sn)).clearCheck();
        ((RadioGroup) findViewById(R.id.rg_jp)).clearCheck();
        ((RadioGroup) findViewById(R.id.rg_tf)).clearCheck();
    }

    public void checkInputValidAll(){
        boolean me_validate1 = false;
        boolean me_validate2 = false;
        boolean me_valid = false;
        boolean you_validate1 = false;
        boolean you_validate2 = false;
        boolean you_valid = false;

        me_validate1 = checkBirthdayValidate(me);
        if(me.getMbti() != "")
            me_validate2 = true;

        me_valid = me_validate1 & me_validate2;

        you_validate1 = checkBirthdayValidate(you);
        if(you.getMbti() != "")
            you_validate2 = true;
        you_valid = you_validate1 & you_validate2;

        //나 ok, 상대방 nok
        //나 nok, 상대방 ok
        //나 ok, 상대방 ok
        if(me_valid && !you_valid)
        {
            notice.setText("너 버튼을 클릭하세요");
        }
        else if(!me_valid && you_valid)
        {
            notice.setText("나 버튼을 클릭하세요");
        }
        else if(me_valid && you_valid)
        {
            notice.setText("결과보기 버튼을 클릭하세요");

        }
    }

    /** Called when the user touches the button */
    public void onClickMeBtn(View view) {
        initViewItem();
        pressMeBtn = true;
        if(me.isCompleteInfo() == false)
        {
            notice.setText("생년월일을 입력하세요.");
        }
        view.setSelected(!view.isSelected());
        Button youBtn = (Button)findViewById(R.id.you_btn);
        youBtn.setSelected(!view.isSelected());
    }

    public void onClickYouBtn(View view) {
        initViewItem();
        pressMeBtn = false;
        if(you.isCompleteInfo() == false)
        {
            notice.setText("생년월일을 입력하세요.");
        }
        view.setSelected(!view.isSelected());
        Button meBtn = (Button)findViewById(R.id.me_btn);
        meBtn.setSelected(!view.isSelected());
    }

    public boolean checkBirthdayValidate(Person p){
        if(p.getBirthday().getYear() != Constant.NOK
                && p.getBirthday().getMonth() != Constant.NOK
                && p.getBirthday().getDay() != Constant.NOK)
            return true;
        else
            return false;
    }

    /** Called when the user touches the button */
    public void showResult(View view) {
        // Do something in response to button click
        Intent infos = new Intent(this, ResultActivity.class);
        infos.putExtra("me", me);
        infos.putExtra("you", you);
        startActivity(infos);
    }

    public void onRadioButtonClickedAll(){
        //각 그룹에서 1개씩 체크된 상태인지 검사
        if(isExtraversion != Constant.NOK
            && isSensing != Constant.NOK
            && isIntuition != Constant.NOK
            && isJudging != Constant.NOK)
        {
            String mbti = makeMBTI();

            if(pressMeBtn)
                me.setMbti(mbti);
            else
                you.setMbti(mbti);
            notice.setText("결과보기 버튼을 클릭하세요.");
            checkInputValidAll();
        }
    }

    public void onRadioButtonClicked_mbti(View view){
        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()){
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
                isIntuition = checked ? CHECKED: UNCHECKED;
                break;
            case R.id.rb_f:
                isIntuition = checked? UNCHECKED: CHECKED;
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

    public String makeMBTI(){
        String mbti = "";
        mbti = isExtraversion == CHECKED ? EType : IType;
        mbti += isSensing == CHECKED ? SType: NType;
        mbti += isIntuition == CHECKED ? TType : FType;
        mbti += isJudging == CHECKED ? JType : PType;
        return mbti;
    }
}