package ru.liga.junit.noteTests;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import ru.liga.App;
import ru.liga.engine.Engine;

import java.io.IOException;

public class NoteTestApp {

    @Test
    public void lowestNoteTest() throws IOException {
        String[] args = "D:\\cranberries-zombie.mid analyze -f".split(" ");
        App.main(args);
        assertThat(Engine.engine.getMaxNote().sign().fullName().equals("E2"));
    }

    @Test
    public void highestNoteTest() throws IOException {
        String[] args = "D:\\cranberries-zombie.mid analyze -f".split(" ");
        App.main(args);
        assertThat(Engine.engine.getMaxNote().sign().fullName().equals("D3"));
    }

    @Test
    public void durationTest() throws IOException {
        String[] args = "D:\\cranberries-zombie.mid analyze -f".split(" ");
        App.main(args);
        assertThat(Engine.simpleMidiFile.durationMs() / 1000 == 310);
    }

    @Test
    public void amountOfNotesTest() throws IOException {
        String[] args = "D:\\cranberries-zombie.mid analyze -f".split(" ");
        App.main(args);
        assertThat(Engine.simpleMidiFile.vocalNoteList().size() == 388);
    }
}
