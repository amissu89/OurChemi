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

    public final static String DDI_REQ_URL = "https://superkts.com/cal/solar_lunar/";
}
