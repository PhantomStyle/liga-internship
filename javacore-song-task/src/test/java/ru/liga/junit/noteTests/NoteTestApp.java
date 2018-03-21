package ru.liga.junit.noteTests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import ru.liga.App;
import ru.liga.engine.Engine;

import java.io.IOException;

public class NoteTestApp {

    @Test
    public void whenTxtFileReturnLowestNote() throws IOException {
        String[] args = "src\\main\\resources\\cranberries-zombie.mid analyze -f".split(" ");
        App.main(args);
        assertEquals(Engine.engine.getMinNote().sign().fullName(), "D3");
    }

    @Test
    public void whenTxtFileReturnHighestNoteTest() throws IOException {
        String[] args = "src\\main\\resources\\cranberries-zombie.mid analyze -f".split(" ");
        App.main(args);
        System.out.println(Engine.engine.getMaxNote().sign().fullName());
        assertEquals(Engine.engine.getMaxNote().sign().fullName(), "E2");
    }

    @Test
    public void whenTxtFileReturnDuration() throws IOException {
        String[] args = "src\\main\\resources\\cranberries-zombie.mid analyze -f".split(" ");
        App.main(args);
        assertEquals(Engine.simpleMidiFile.durationMs() / 1000, 310);
    }

    @Test
    public void whenTxtFileReturnamountOfNotes() throws IOException {
        String[] args = "src\\main\\resources\\cranberries-zombie.mid analyze -f".split(" ");
        App.main(args);
        assertEquals(Engine.simpleMidiFile.vocalNoteList().size(), 388);
    }
}
