package com.example.ourchemi;

import com.example.ourchemi.models.DateObj;
import com.example.ourchemi.models.KZodiacType;
import com.example.ourchemi.models.MBTIType;
import com.example.ourchemi.models.Person;
import com.example.ourchemi.models.ZodiacType;

public class Calculator {
    public static void getZodiac(Person p) {
        DateObj obj = new DateObj(p.getBirthday().getMonth(),
                p.getBirthday().getDay());

        Constant.ZODIAC_MAP.forEach((key, value) -> {
            if (obj.compareTo(value.getStart()) >= 0 &&
                    obj.compareTo(value.getEnd()) <= 0) {
                p.setZodiacSign(key);
            }

        });
        //염소자리일 경우 12.25 ~ 1.19는 해를 넘어가므로
        //추가 조건걸기
        if (obj.getMonth() == 1 && obj.getDay() <= 19 ||
                obj.getMonth() == 12 && obj.getDay() >= 25) {
            p.setZodiacSign(ZodiacType.CARPRICORN.getName());
        }
    }

    public static Integer getMbtiChemi(Person p1, Person p2) {
        int p1_num = Constant.NOK;
        int p2_num = Constant.NOK;

        for (MBTIType type : MBTIType.values()) {
            if (p1_num == Constant.NOK && p1.getMbti().equals(type.getName())) {
                p1_num = type.getValue();
            }
            if (p2_num == Constant.NOK && p2.getMbti().equals(type.getName())) {
                p2_num = type.getValue();
            }
            if (p1_num != Constant.NOK && p2_num != Constant.NOK)
                break;
        }
        if (p1_num != Constant.NOK && p2_num != Constant.NOK)
            return Constant.MBTI_SCORE[p1_num][p2_num];
        else
            return Constant.NOK;
    }

    public static Integer getZodiacChemi(Person p1, Person p2) {
        int p1_num = Constant.NOK;
        int p2_num = Constant.NOK;
        for (ZodiacType type : ZodiacType.values()) {
            if (p1_num == Constant.NOK && p1.getZodiacSign().equals(type.getName())) {
                p1_num = type.getValue();
            }
            if (p2_num == Constant.NOK && p2.getZodiacSign().equals(type.getName())) {
                p2_num = type.getValue();
            }
            if (p1_num != Constant.NOK && p2_num != Constant.NOK)
                break;
        }
        if (p1_num != Constant.NOK && p2_num != Constant.NOK)
            return Constant.ZODIAC_SCORE[p1_num][p2_num];
        else
            return Constant.NOK;
    }

    public static Integer getkZodiacChemi(Person p1, Person p2) {
        int p1_num = Constant.NOK;
        int p2_num = Constant.NOK;
        for (KZodiacType type : KZodiacType.values()) {
            if (p1_num == Constant.NOK && p1.getDdi().equals(type.getName())) {
                p1_num = type.getValue();
            }
            if (p2_num == Constant.NOK && p2.getDdi().equals(type.getName())) {
                p2_num = type.getValue();
            }
            if (p1_num != Constant.NOK && p2_num != Constant.NOK)
                break;
        }
        if (p1_num != Constant.NOK && p2_num != Constant.NOK)
            return Constant.KZODIAC_SCROE[p1_num][p2_num];
        else
            return Constant.NOK;
    }
}
