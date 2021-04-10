package it.dev.cleto.utils;

import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

class UtilsTest {

    @Test
    void getVersion() {

        Calendar beforeV1 = new GregorianCalendar();
        beforeV1.set(Calendar.DAY_OF_MONTH, 30);
        beforeV1.set(Calendar.MONTH, 3);
        beforeV1.set(Calendar.YEAR, 2019);

        Calendar afterV1 = new GregorianCalendar();
        afterV1.set(Calendar.DAY_OF_MONTH, 30);
        afterV1.set(Calendar.MONTH, 3);
        afterV1.set(Calendar.YEAR, 2020);

        assertEquals(Utils.getVersion(beforeV1.getTime()), "v1");
        assertEquals(Utils.getVersion(afterV1.getTime()), "v2");
        assertEquals(Utils.getVersion(new Date()), "v3");
    }
}