package it.dev.cleto;

import it.dev.cleto.media.MP3Show;
import it.dev.cleto.report.Report;
import it.dev.cleto.utils.EShow;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


/**
 * Unit test for simple Reloaded.
 */
public class ReloadedTest {
    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }

    @Test
    public void reportExists() {
        Report p = new Report();
        assertNotNull(p.getReport());
    }



}
