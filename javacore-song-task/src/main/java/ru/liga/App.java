package ru.liga;

import ru.liga.engine.Changer;
import ru.liga.engine.Engine;
import ru.liga.engine.Path;
import ru.liga.engine.savers.LogSaver;
import ru.liga.engine.savers.Save;
import ru.liga.engine.savers.TextSaver;
import ru.liga.songtask.domain.SimpleMidiFile;

import java.io.*;

/**
 * Всего нот: 15
 * <p>
 * Анализ диапазона:
 * верхняя: E4
 * нижняя: F3
 * диапазон: 11
 * <p>
 * Анализ длительности нот (мс):
 * 4285: 10
 * 2144: 5
 * <p>
 * Анализ нот по высоте:
 * E4: 3
 * D4: 3
 * A3: 3
 * G3: 3
 * F3: 3
 * <p>
 * Анализ интервалов:
 * 2: 9
 * 5: 3
 * 11: 2
 */
public class App {


    public static void main(String[] args) throws IOException {
        Engine.path = new Path(args[0]);
        Engine.simpleMidiFile = new SimpleMidiFile(new File(Engine.path.getInPath()));

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
