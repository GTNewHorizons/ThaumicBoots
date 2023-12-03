package thaumicboots.main.utils;

import java.util.Calendar;

public class CalendarHelper {

    private static final int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
    private static boolean isIrish = false;
    private static boolean isEaster = false;
    private static boolean isFree = false;
    private static boolean isChristmas = false;

    public static void calendar() {
        switch (month) {
            case 3:
                isIrish = true;
            case 4:
                isEaster = true;
                break;
            case 7:
                isFree = true; // AMERICA #1 BABY
                break;
            case 12:
                isChristmas = true;
                break;
        }
    }

    public static boolean isIrish() {
        return isIrish;
    }

    public static boolean isEaster() {
        return isEaster;
    }

    public static boolean isFree() {
        return isFree;
    }

    public static boolean isChristmas() {
        return isChristmas;
    }
}
