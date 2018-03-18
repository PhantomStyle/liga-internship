package ru.liga.junit.noteTests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import ru.liga.App;
import ru.liga.engine.Engine;

import java.io.IOException;

public class NoteTestApp {

    @Test
    public void lowestNoteTest() throws IOException {
        String[] args = "D:\\cranberries-zombie.mid analyze -f".split(" ");
        App.main(args);
        assertEquals(Engine.engine.getMinNote().sign().fullName(), "E2");
    }

    @Test
    public void highestNoteTest() throws IOException {
        String[] args = "D:\\cranberries-zombie.mid analyze -f".split(" ");
        App.main(args);
        System.out.println(Engine.engine.getMaxNote().sign().fullName());
        assertEquals(Engine.engine.getMaxNote().sign().fullName(), "D3");
    }

    @Test
    public void durationTest() throws IOException {
        String[] args = "D:\\cranberries-zombie.mid analyze -f".split(" ");
        App.main(args);
        assertEquals(Engine.simpleMidiFile.durationMs() / 1000, 310);
    }

    @Test
    public void amountOfNotesTest() throws IOException {
        String[] args = "D:\\cranberries-zombie.mid analyze -f".split(" ");
        App.main(args);
        assertEquals(Engine.simpleMidiFile.vocalNoteList().size(), 388);
    }
}
