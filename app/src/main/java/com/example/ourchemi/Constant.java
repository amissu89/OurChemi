package com.example.ourchemi;

import com.example.ourchemi.models.DateObj;
import com.example.ourchemi.models.DateRange;
import com.example.ourchemi.models.DdiType;
import com.example.ourchemi.models.FiveElement;
import com.example.ourchemi.models.KZodiac;
import com.example.ourchemi.models.KZodiacType;
import com.example.ourchemi.models.ZodiacType;

import java.util.HashMap;
import java.util.Map;

public final class Constant {
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

    public static final int BIRTHDAY_LEN = 8;
    public static final String CONFIG_NAME = "config.json";
    public static final int NOK = -1;
    public final static Map<String, KZodiac> DDI_MAP = new HashMap<String, KZodiac>(){
        {
            put(KZodiacType.MOUSE.getName(),
                    new KZodiac(DdiType.MOUSE.getName(), false, FiveElement.WATER.getName()));
            put(KZodiacType.COW.getName(),
                    new KZodiac(DdiType.COW.getName(), true, FiveElement.SOIL.getName()));
            put(KZodiacType.TIGER.getName(),
                    new KZodiac(DdiType.TIGER.getName(), false, FiveElement.TREE.getName()));
            put(KZodiacType.RABBIT.getName(),
                    new KZodiac(DdiType.RABBIT.getName(), true, FiveElement.TREE.getName()));
            put(KZodiacType.DRAGON.getName(),
                    new KZodiac(DdiType.DRAGON.getName(), false, FiveElement.SOIL.getName()));
            put(KZodiacType.SNAKE.getName(),
                    new KZodiac(DdiType.SNAKE.getName(), true, FiveElement.FIRE.getName()));
            put(KZodiacType.HORSE.getName(),
                    new KZodiac(DdiType.HORSE.getName(), false, FiveElement.FIRE.getName()));
            put(KZodiacType.SHEEP.getName(),
                    new KZodiac(DdiType.SHEEP.getName(), true, FiveElement.SOIL.getName()));
            put(KZodiacType.MONKEY.getName(),
                    new KZodiac(DdiType.MONKEY.getName(), false, FiveElement.GOLD.getName()));
            put(KZodiacType.CHICKEN.getName(),
                    new KZodiac(DdiType.CHICKEN.getName(), true, FiveElement.GOLD.getName()));
            put(KZodiacType.DOG.getName(),
                    new KZodiac(DdiType.DOG.getName(), false, FiveElement.SOIL.getName()));
            put(KZodiacType.PIG.getName(),
                    new KZodiac(DdiType.PIG.getName(), true, FiveElement.WATER.getName()));
        }
    };

    public final static Map<String, DateRange> ZODIAC_MAP = new HashMap<String, DateRange>()
    {
        {
            put(ZodiacType.ARIES.getName(),
                    new DateRange(new DateObj(3,21), new DateObj(4,19)));
            put(ZodiacType.TAURUS.getName(),
                    new DateRange(new DateObj(4,20), new DateObj(5,20)));
            put(ZodiacType.GEMINI.getName(),
                    new DateRange(new DateObj(5,21), new DateObj(6,21)));
            put(ZodiacType.CANCER.getName(),
                    new DateRange(new DateObj(6,22), new DateObj(7,22)));
            put(ZodiacType.LEO.getName(),
                    new DateRange(new DateObj(7,23), new DateObj(8,22)));
            put(ZodiacType.VIRGO.getName(),
                    new DateRange(new DateObj(8,23), new DateObj(9,23)));
            put(ZodiacType.LIBRA.getName(),
                    new DateRange(new DateObj(9,24), new DateObj(10,22)));
            put(ZodiacType.SCORPIO.getName(),
                    new DateRange(new DateObj(10,23), new DateObj(11,22)));
            put(ZodiacType.SAGITTARIUS.getName(),
                    new DateRange(new DateObj(11,23), new DateObj(12,24)));
            put(ZodiacType.CARPRICORN.getName(),
                    new DateRange(new DateObj(12,25), new DateObj(1,19)));
            put(ZodiacType.AQUARIUS.getName(),
                    new DateRange(new DateObj(1,20), new DateObj(2,18)));
            put(ZodiacType.PISCES.getName(),
                    new DateRange(new DateObj(2,19), new DateObj(3,20)));
        }
    };

    public final static int BEST = 100;
    public final static int GOOD = 80;
    public final static int SOSO = 70;
    public final static int BAD = 60;
    public final static int WORST = 50;

    public final static int[][] MBTI_SCORE = new int[][]{
            {GOOD,GOOD,GOOD,BEST,GOOD,BEST,GOOD,GOOD,WORST,WORST,WORST,WORST,WORST,WORST,WORST,WORST},
            {GOOD,GOOD,BEST,GOOD,BEST,GOOD,GOOD,GOOD,WORST,WORST,WORST,WORST,WORST,WORST,WORST,WORST},
            {GOOD,BEST,GOOD,GOOD,GOOD,GOOD,GOOD,BEST,WORST,WORST,WORST,WORST,WORST,WORST,WORST,WORST},
            {BEST,GOOD,GOOD,GOOD,GOOD,GOOD,GOOD,GOOD,BEST,WORST,WORST,WORST,WORST,WORST,WORST,WORST},
            {GOOD,BEST,GOOD,GOOD,GOOD,GOOD,GOOD,BEST,SOSO,SOSO,SOSO,SOSO,BAD,BAD,BAD,BAD},
            {BEST,GOOD,GOOD,GOOD,GOOD,GOOD,BEST,GOOD,SOSO,SOSO,SOSO,SOSO,SOSO,SOSO,SOSO,SOSO},
            {GOOD,GOOD,GOOD,GOOD,GOOD,BEST,GOOD,GOOD,SOSO,SOSO,SOSO,SOSO,BAD,BAD,BAD,BEST},
            {GOOD,GOOD,BEST,GOOD,BEST,GOOD,GOOD,GOOD,SOSO,SOSO,SOSO,SOSO,BAD,BAD,BAD,BAD},
            {WORST,WORST,WORST,BEST,SOSO,SOSO,SOSO,SOSO,BAD,BAD,BAD,BAD,SOSO,BEST,SOSO,BEST },
            {WORST,WORST,WORST,WORST,SOSO,SOSO,SOSO,SOSO,BAD,BAD,BAD,BAD,BEST,SOSO,BEST,SOSO},
            {WORST,WORST,WORST,WORST,SOSO,SOSO,SOSO,SOSO,BAD,BAD,BAD,BAD,SOSO,BEST,SOSO,BEST},
            {WORST,WORST,WORST,WORST,SOSO,SOSO,SOSO,SOSO,BAD,BAD,BAD,BAD,BEST,SOSO,BEST,SOSO},
            {WORST,WORST,WORST,WORST,BAD,SOSO,BAD,BAD,SOSO,BEST,SOSO,BEST,GOOD,GOOD,GOOD,GOOD},
            {WORST,WORST,WORST,WORST,BAD,SOSO,BAD,BAD,BEST,SOSO,BEST,SOSO,GOOD,GOOD,GOOD,GOOD},
            {WORST,WORST,WORST,WORST,BAD,SOSO,BAD,BAD,SOSO,BEST,SOSO,BEST,GOOD,GOOD,GOOD,GOOD},
            {WORST,WORST,WORST,WORST,BAD,SOSO,BEST,BAD,BEST,SOSO,BEST,SOSO,GOOD,GOOD,GOOD,GOOD}
    };

    public final static int[][] ZODIAC_SCORE = new int[][]{
            {BAD,WORST,GOOD,BAD,BEST,SOSO,GOOD,BAD,BEST,BAD,SOSO,SOSO}, //ARIES
            {WORST,SOSO,WORST,BEST,SOSO,BEST,SOSO,GOOD,WORST,BEST,BAD,GOOD}, //TAURUS
            {BEST,WORST,SOSO,SOSO,GOOD,SOSO,BEST,WORST,SOSO,SOSO,GOOD,BAD}, //GEMINI
            {BAD,BEST,SOSO,SOSO,WORST,BEST,BAD,BEST,BAD,GOOD,WORST,BEST}, //CANCER
            {BEST,SOSO,GOOD,WORST,BAD,WORST,BEST,BAD,BEST,WORST,SOSO,WORST}, //LEO
            {SOSO,BEST,SOSO,BEST,WORST,SOSO,SOSO,GOOD,BAD,BEST,WORST,GOOD},//VIRGO
            {GOOD,SOSO,BEST,BAD,BEST,SOSO,SOSO,WORST,SOSO,BAD,BEST,GOOD}, //LIBRA
            {BAD,GOOD,WORST,BEST,BAD,GOOD,WORST,GOOD,WORST,BEST,SOSO,BEST},//SCORPIO
            {BEST,WORST,SOSO,BAD,BEST,BAD,SOSO,WORST,BAD,SOSO,BEST,SOSO},//SAGITARIUS
            {BAD,BEST,SOSO,GOOD,WORST,BEST,BAD,BEST,SOSO,SOSO,SOSO,GOOD},//CARPRICORN
            {SOSO,BAD,GOOD,WORST,SOSO,WORST,BEST,SOSO,BEST,SOSO,BAD,BAD},//AQUARIUS
            {SOSO,GOOD,BAD,BEST,WORST,GOOD,GOOD,BEST,SOSO,GOOD,BAD,SOSO}//PISCES
    };

    public final static int[][] KZODIAC_SCROE = new int[][]{
            {GOOD,BEST,GOOD,BAD,BEST,GOOD,WORST,BAD,BEST,BAD,GOOD,GOOD},//자
            {BEST,GOOD,GOOD,GOOD,BAD,BEST,BAD,WORST,GOOD,BEST,BAD,GOOD},//축
            {GOOD,GOOD,GOOD,GOOD,GOOD,BAD,BEST,GOOD,WORST,BAD,BEST,BEST},//인
            {BAD,GOOD,GOOD,GOOD,BAD,GOOD,BAD,BEST,BAD,WORST,BEST,BEST},//묘
            {BEST,BAD,GOOD,BAD,BAD,GOOD,GOOD,GOOD,BEST,BEST,WORST,BAD},//진
            {GOOD,BEST,BAD,GOOD,GOOD,GOOD,GOOD,GOOD,BAD,BEST,BAD,WORST},//사
            {WORST,BAD,BEST,BAD,GOOD,GOOD,BAD,BEST,GOOD,GOOD,BEST,GOOD},//오
            {BAD,WORST,GOOD,BEST,GOOD,GOOD,BEST,GOOD,GOOD,GOOD,BAD,BEST},//미
            {BEST,GOOD,WORST,BAD,BEST,BAD,GOOD,GOOD,GOOD,GOOD,GOOD,BAD},//신
            {BAD,BEST,BAD,WORST,BEST,BEST,GOOD,GOOD,GOOD,BAD,BAD,GOOD},//유
            {GOOD,BAD,BEST,BEST,WORST,BAD,BEST,BAD,GOOD,BAD,GOOD,GOOD},//술
            {GOOD,GOOD,BEST,BEST,BAD,WORST,GOOD,BEST,BAD,GOOD,GOOD,BAD}//해
    };

    public final static String DDI_REQ_URL = "https://superkts.com/cal/solar_lunar/";
    public final static String MBTI_LINK = "https://www.16personalities.com/free-personality-test";
}
