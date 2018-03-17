package ru.liga.engine.savers;

import ru.liga.engine.Engine;
import ru.liga.songtask.domain.SimpleMidiFile;

public class LogSaver implements Save {
    @Override
    public void fullAnalysis(SimpleMidiFile simpleMidiFile) {
        Engine.appLogger.info(Engine.engine.amountOfNotes(simpleMidiFile));
        Engine.appLogger.info(Engine.engine.durability(simpleMidiFile));
        Engine.appLogger.info(Engine.engine.rangeAnalysis(simpleMidiFile));
        Engine.appLogger.info(Engine.engine.noteDurationAnalysis(simpleMidiFile));
        Engine.appLogger.info(Engine.engine.analysisByHeight(simpleMidiFile));
        Engine.appLogger.info(Engine.engine.intervalAnalysis(simpleMidiFile));
    }

}
