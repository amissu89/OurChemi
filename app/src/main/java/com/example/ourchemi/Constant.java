package com.example.ourchemi;

import com.example.ourchemi.models.DateObj;
import com.example.ourchemi.models.DateRange;
import com.example.ourchemi.models.ZodiacType;

import java.util.HashMap;
import java.util.Map;

public final class Constant {

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
