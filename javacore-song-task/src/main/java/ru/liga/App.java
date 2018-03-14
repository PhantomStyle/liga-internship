package ru.liga;

import ru.liga.songtask.content.Content;
import ru.liga.songtask.domain.Note;
import ru.liga.songtask.domain.SimpleMidiFile;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    protected static Logger logger = LoggerFactory.getLogger(App.class);

    private static Save saver;

    public static void main(String[] args) throws IOException {

        SimpleMidiFile simpleMidiFile = new SimpleMidiFile(new File(args[0] + "\\cranberries-zombie.mid"));
        String forSwitch = "";
        for(int i = 1; i < args.length; i++){
           forSwitch += args[i] + " ";
        }
        forSwitch = forSwitch.trim();

        if(forSwitch.equals("analyze -f")){
            saver = new TextSaver();
            saver.fullAnalysis(simpleMidiFile);
        }
        if(forSwitch.equals("analyze")){
            saver = new LogSaver();
            saver.fullAnalysis(simpleMidiFile);
        }
        if(forSwitch.equals("change -trans 2 -tempo 20")){
            Changer changer = new Changer(args[0]);
            changer.perform(simpleMidiFile);
        }
    }
}
