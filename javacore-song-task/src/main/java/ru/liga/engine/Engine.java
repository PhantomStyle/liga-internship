package ru.liga.engine;

import javafx.util.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.liga.App;
import ru.liga.engine.savers.Save;
import ru.liga.engine.savers.TextSaver;
import ru.liga.songtask.domain.Note;
import ru.liga.songtask.domain.SimpleMidiFile;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class Engine {

    public static final Engine engine = new Engine();
    public static final Logger appLogger = LoggerFactory.getLogger(App.class);
    public static final Logger textSaverLogger = LoggerFactory.getLogger(TextSaver.class);
    public static final Logger changerLogger = LoggerFactory.getLogger(Changer.class);
    public static Save saver;
    public static Changer changer;
    public static MyPath myPath;
    public static SimpleMidiFile simpleMidiFile;

    private Note maxNote;
    private Note minNote;


    public Note getMaxNote() {
        return maxNote;
    }

    public Note getMinNote() {
        return minNote;
    }

    public String amountOfNotes(SimpleMidiFile simpleMidiFile) {
        return "Всего нот: " + simpleMidiFile.vocalNoteList().size() + "\r\n";
    }

    /**
     * @param simpleMidiFile
     * @return durability of your midi file
     */
    public String durability(SimpleMidiFile simpleMidiFile) {
        return "Длительность (сек): " + simpleMidiFile.durationMs() / 1000 + "\r\n";
    }

    public String rangeAnalysis(SimpleMidiFile simpleMidiFile) {
        Note minNote = simpleMidiFile.vocalNoteList().stream().min(Comparator.comparing(Note::sign)).get();
        Note maxNote = simpleMidiFile.vocalNoteList().stream().max(Comparator.comparing(Note::sign)).get();
        this.minNote = minNote;
        this.maxNote = maxNote;
        return "<p>\r\nАнализ диапазона:" + "\r\n"
                + "верхняя: " + maxNote.sign().fullName() + "\r\n"
                + "нижняя: " + minNote.sign().fullName() + "\r\n"
                + "диапазон: " + maxNote.sign().diffInSemitones(minNote.sign()) + "\r\n<p>" + "\r\n";

    }

    public String noteDurationAnalysis(SimpleMidiFile simpleMidiFile) {
        StringBuilder result = new StringBuilder("Анализ длительности нот (мс):\r\n");
        Map<Long, Integer> durations = new HashMap<>();
        simpleMidiFile.vocalNoteList().stream()
                .map(Note::durationTicks)
                .map(durationTicks -> (long) (durationTicks * simpleMidiFile.tickInMs()))
                .map(duration -> {
                    int count = 0;
                    if (durations.containsKey(duration)) {
                        count = durations.get(duration);
                    }
                    return new Pair<>(duration, ++count);
                }).forEach(keyValue -> durations.put(keyValue.getKey(), keyValue.getValue()));

        for (Map.Entry<Long, Integer> entry : durations.entrySet()) {
            result.append((int) (entry.getKey() * simpleMidiFile.tickInMs()) + ": " + entry.getValue() + "\r\n");
        }
        return result.toString();
    }

    public String analysisByHeight(SimpleMidiFile simpleMidiFile) {
        StringBuilder result = new StringBuilder("<p>\r\nАнализ нот по высоте::");
        Map<String, Long> heights = new HashMap<>();
        simpleMidiFile.vocalNoteList().stream()
                .map(note -> {
                    long count = 0;
                    if (heights.containsKey(note.sign().fullName())) {
                        count = heights.get(note.sign().fullName());
                    }
                    return new Pair<>(note.sign().fullName(), ++count);
                }).forEach(keyValue -> heights.put(keyValue.getKey(), keyValue.getValue()));

        for (Map.Entry<String, Long> entry : heights.entrySet()) {
            result.append(entry.getKey() + ": " + entry.getValue() + "\r\n");
        }
        return result.toString();
    }

    public String intervalAnalysis(SimpleMidiFile simpleMidiFile) {
        StringBuilder result = new StringBuilder("<p>\r\nАнализ интервалов:");
        Map<Integer, Integer> intervals = new HashMap<>();
        final Note[] predNote = new Note[1];
        simpleMidiFile.vocalNoteList().stream()
                .filter(note -> {
                    if (predNote[0] == null) {
                        predNote[0] = note;
                        return false;
                    }
                    return true;
                })
                .map(note -> {
                    int count = 0;
                    int diff = note.sign().diffInSemitones(predNote[0].sign());
                    if (intervals.containsKey(diff)) {
                        count = intervals.get(diff);
                    }
                    predNote[0] = note;
                    return new Pair<>(diff, ++count);
                }).forEach(keyValue -> intervals.put(keyValue.getKey(), keyValue.getValue()));

        for (Map.Entry<Integer, Integer> entry : intervals.entrySet()) {
            result.append(entry.getKey() + ": " + entry.getValue() + "\r\n");
        }
        return result.toString();
    }
}
