package ru.liga;

import ru.liga.engine.Changer;
import ru.liga.engine.Engine;
import ru.liga.engine.MyPath;
import ru.liga.engine.savers.LogSaver;
import ru.liga.engine.savers.TextSaver;
import ru.liga.songtask.domain.SimpleMidiFile;

import java.io.*;

/**
 * Всего нот: 388
 * Длительность (сек): 310
 * <p>
 * Анализ диапазона:
 * верхняя: E2
 * нижняя: D3
 * диапазон: 10
 * <p>
 * Анализ длительности нот (мс):
 * 365: 384
 * 2926: 4
 * <p>
 * Анализ нот по высоте::C3: 94
 * A2: 2
 * F#2: 92
 * G2: 106
 * E2: 92
 * D3: 2
 * <p>
 * Анализ интервалов:0: 307
 * 1: 16
 * 2: 20
 */
public class App {


    public static void main(String[] args) throws IOException {
        Engine.myPath = new MyPath(args[0]);
        Engine.simpleMidiFile = new SimpleMidiFile(new File(Engine.myPath.getInPath()));

        String forSwitch = "";
        for (int i = 1; i < args.length; i++) {
            forSwitch += args[i] + " ";
        }
        forSwitch = forSwitch.trim();

        if (forSwitch.equals("analyze -f")) {
            Engine.saver = new TextSaver();
            Engine.saver.fullAnalysis(Engine.simpleMidiFile);
        }
        if (forSwitch.equals("analyze")) {
            Engine.saver = new LogSaver();
            Engine.saver.fullAnalysis(Engine.simpleMidiFile);
        }
        if (forSwitch.contains("change")) {
            Engine.changer = new Changer(args[3], args[5]);
            Engine.changer.perform(Engine.simpleMidiFile);
        }
    }
}
