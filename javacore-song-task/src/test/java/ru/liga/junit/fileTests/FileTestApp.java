package ru.liga.junit.fileTests;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import ru.liga.App;
import ru.liga.engine.Engine;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileTestApp {

    @Test
    public void savingTextFileTest() throws IOException {
        String[] args = "D:\\cranberries-zombie.mid analyze -f".split(" ");
        App.main(args);
        assertThat(Files.exists(Paths.get(Engine.myPath.getOutPath() + "\\filename.txt")));
    }

    @Test
    public void savingLogFileTest() throws IOException {
        String[] args = "D:\\cranberries-zombie.mid analyze".split(" ");
        App.main(args);
        assertThat(Files.exists(Paths.get(Engine.myPath.getOutPath() + "\\logFile.log")));
    }

    @Test
    public void savingMidFileTest() throws IOException {
        String[] args = "D:\\cranberries-zombie.mid change -trans 2 -tempo 20".split(" ");
        App.main(args);
        assertThat(Files.exists(Paths.get(Engine.myPath.getOutPath() + "\\zombie-trans2-tempo20.mid")));
    }

}
