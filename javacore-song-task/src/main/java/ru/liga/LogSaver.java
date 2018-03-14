package ru.liga;

import ru.liga.songtask.content.Content;
import ru.liga.songtask.domain.SimpleMidiFile;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class LogSaver implements Save{
    Engine engine = new Engine();

    @Override
    public void fullAnalysis(SimpleMidiFile simpleMidiFile) {
        App.logger.info(engine.amountOfNotes(simpleMidiFile));
        App.logger.info(engine.durability(simpleMidiFile));
        App.logger.info(engine.rangeAnalysis(simpleMidiFile));
        App.logger.info(engine.noteDurationAnalysis(simpleMidiFile));
        App.logger.info(engine.analysisByHeight(simpleMidiFile));
        App.logger.info(engine.intervalAnalysis(simpleMidiFile));
    }

}
