package it.dev.cleto;

import it.dev.cleto.report.Report;
import org.farng.mp3.MP3File;
import org.farng.mp3.TagConstant;
import org.farng.mp3.TagException;
import org.farng.mp3.TagOptionSingleton;
import org.farng.mp3.id3.AbstractID3v2;
import org.farng.mp3.id3.AbstractID3v2Frame;
import org.farng.mp3.id3.FrameBodyTIT2;
import org.farng.mp3.id3.ID3v1;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertTrue;

/**
 * Unit test for simple Reloaded.
 */
public class ReloadedTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        String FILE_URL = "https://www.unipg.it/files/pagine/410/4-PDF-A.pdf";
        String FILE_NAME = "/home/biadmin/Music/20190305_cr31.mp3";

        File file = new File(FILE_NAME);
        MP3File mp3file = null;
        try {
            mp3file = new MP3File(file);
            TagOptionSingleton.getInstance().setDefaultSaveMode(TagConstant.MP3_FILE_SAVE_WRITE);
            ID3v1 id3v1 = mp3file.getID3v1Tag();
            id3v1.setSongTitle(file.getName());
            AbstractID3v2 id3v2 = mp3file.getID3v2Tag();
            id3v2.setSongTitle(file.getName());
            AbstractID3v2Frame frame = id3v2.getFrame(Utils.TITLE_TAG);
            ((FrameBodyTIT2) frame.getBody()).setText(file.getName());
            mp3file.save();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (TagException e) {
            e.printStackTrace();
        }

        assertTrue(false);
    }

    @Test
    public void reportExists() {
        Report p = new Report();
        p.getReport();
    }

}
