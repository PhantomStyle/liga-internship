package ru.liga.engine.savers;

import ru.liga.engine.Engine;
import ru.liga.songtask.domain.SimpleMidiFile;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class TextSaver implements Save {

    @Override
    public void fullAnalysis(SimpleMidiFile simpleMidiFile) {

        try (PrintStream out = new PrintStream(new FileOutputStream(Engine.path.getOutPath() + "\\filename.txt"))) {
            out.print(Engine.engine.amountOfNotes(simpleMidiFile));
            out.print(Engine.engine.durability(simpleMidiFile));
            out.print(Engine.engine.rangeAnalysis(simpleMidiFile));
            out.print(Engine.engine.noteDurationAnalysis(simpleMidiFile));
            out.print(Engine.engine.analysisByHeight(simpleMidiFile));
            out.print(Engine.engine.intervalAnalysis(simpleMidiFile));
        } catch (FileNotFoundException e) {
            Engine.textSaverLogger.info(e.getMessage());
        }
    }

    public TextSaver() throws FileNotFoundException {
    }
}
