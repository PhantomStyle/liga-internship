package ru.liga;

import ru.liga.songtask.content.Content;
import ru.liga.songtask.domain.SimpleMidiFile;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class TextSaver implements Save{

    Engine engine = new Engine();

    @Override
    public void fullAnalysis(SimpleMidiFile simpleMidiFile){
        try (PrintStream out = new PrintStream(new FileOutputStream("E:\\FOR_WORK" +
                "\\Программирование\\Java\\Стажировка" +
                "\\liga-internship2\\javacore-song-task\\src\\main\\resources\\filename.txt"))) {
            out.print(engine.amountOfNotes(simpleMidiFile));
            out.print(engine.durability(simpleMidiFile));
            out.print(engine.rangeAnalysis(simpleMidiFile));
            out.print(engine.noteDurationAnalysis(simpleMidiFile));
            out.print(engine.analysisByHeight(simpleMidiFile));
            out.print(engine.intervalAnalysis(simpleMidiFile));
        } catch (FileNotFoundException e) {
            App.logger.info(e.getMessage() + "(from TextSaver)");
        }
    }

    public TextSaver() throws FileNotFoundException {
    }
}
