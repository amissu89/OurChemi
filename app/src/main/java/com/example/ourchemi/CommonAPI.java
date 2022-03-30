package com.example.ourchemi;

import android.content.Context;

import com.example.ourchemi.models.Chemistry;
import com.example.ourchemi.models.DateObj;
import com.example.ourchemi.models.Person;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class CommonAPI {
    public static Boolean validation(String birthday,
                              int isExtraversion,
                              int isSensing,
                              int isIntuition,
                              int isJudging)
    {
        if( birthday.length() != Constant.BIRTHDAY_LEN)
            return false;

        if (isExtraversion == Constant.NOK
                || isSensing == Constant.NOK
                || isIntuition == Constant.NOK
                || isJudging == Constant.NOK) {
            return false;
        }
        return true;
    }

    public static DateObj getDateObjFromEditText(String birthday)
    {
        int year = Integer.parseInt(birthday.substring(0, 4));
        int month = Integer.parseInt(birthday.substring(4, 6));
        int day = Integer.parseInt(birthday.substring(6, 8));

        return new DateObj(year, month, day);
    }


    public static String makeMBTI( int isExtraversion,
                                   int isSensing,
                                   int isIntuition,
                                   int isJudging){
        String mbti = "";
        int checked = Constant.CHECKED;
        mbti = isExtraversion   == checked ? Constant.EType : Constant.IType;
        mbti += isSensing       == checked ? Constant.SType : Constant.NType;
        mbti += isIntuition     == checked ? Constant.TType : Constant.FType;
        mbti += isJudging       == checked ? Constant.JType : Constant.PType;

        return mbti;
    }

    public static void saveThisStateToFile(Context cxt, Chemistry chemistry) throws JsonProcessingException {

        File file = new File(cxt.getFilesDir(), Constant.CONFIG_NAME);

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        ObjectMapper chemiMapper = new ObjectMapper();
        String chemiStr = chemiMapper.writeValueAsString(chemistry);
        byte[] bytesArray = chemiStr.getBytes(StandardCharsets.UTF_8);
        try (FileOutputStream fos = cxt.openFileOutput(file.getName(), Context.MODE_PRIVATE)) {
            fos.write(bytesArray);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Chemistry readStateFromFile(Context cxt) throws JsonProcessingException {
        File file = new File(cxt.getFilesDir(), Constant.CONFIG_NAME);
        if (!file.exists())
            return null;

        Chemistry chemistry = null;

        FileInputStream fis = null;
        try {
            fis = cxt.openFileInput(file.getName());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        InputStreamReader inputStreamReader =
                new InputStreamReader(fis, StandardCharsets.UTF_8);
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(inputStreamReader)) {
            String line = reader.readLine();
            while (line != null) {
                stringBuilder.append(line).append('\n');
                line = reader.readLine();
            }
        } catch (IOException e) {
            // Error occurred when opening raw file for reading.
            return null;

        } finally {
            String contents = stringBuilder.toString();
            System.out.println("contents : " + contents);
            ObjectMapper objectMapper = new ObjectMapper();
            chemistry = objectMapper.readValue(contents, Chemistry.class);
        }

        return chemistry;
    }

    public static boolean checkBirthdayValidate(Person p) {
        if (p.getBirthday().getYear() != Constant.NOK
                && p.getBirthday().getMonth() != Constant.NOK
                && p.getBirthday().getDay() != Constant.NOK)
            return true;
        else
            return false;
    }
}
