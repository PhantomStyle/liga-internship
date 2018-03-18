package ru.liga.engine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.liga.App;
import ru.liga.engine.savers.Save;
import ru.liga.engine.savers.TextSaver;
import ru.liga.songtask.domain.Note;
import ru.liga.songtask.domain.SimpleMidiFile;

import java.util.HashMap;
import java.util.List;
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
        List<Note> notes = simpleMidiFile.vocalNoteList();
        Note minNote = notes.get(0);
        Note maxNote = notes.get(0);
        for (Note note : notes) {
            if (note.sign().higher(maxNote.sign())) {
                maxNote = note;
            }
            if (note.sign().lower(minNote.sign())) {
                minNote = note;
            }
            this.maxNote = maxNote;
            this.minNote = minNote;
        }
        return "<p>\r\nАнализ диапазона:" + "\r\n"
                + "верхняя: " + maxNote.sign().fullName() + "\r\n"
                + "нижняя: " + minNote.sign().fullName() + "\r\n"
                + "диапазон: " + maxNote.sign().diffInSemitones(minNote.sign()) + "\r\n<p>" + "\r\n";

    }

    public String noteDurationAnalysis(SimpleMidiFile simpleMidiFile) {
        StringBuilder result = new StringBuilder("Анализ длительности нот (мс):\r\n");
        List<Note> notes = simpleMidiFile.vocalNoteList();
        Map<Long, Integer> durations = new HashMap<>();
        for (Note note : notes) {
            if (durations.containsKey(note.durationTicks())) {
                int temp = durations.get(note.durationTicks());
                durations.put(note.durationTicks(), ++temp);
            } else {
                durations.put(note.durationTicks(), 1);
            }
        }
        for (Map.Entry<Long, Integer> entry : durations.entrySet()) {
            result.append((int) (entry.getKey() * simpleMidiFile.tickInMs()) + ": " + entry.getValue() + "\r\n");
        }
        return result.toString();
    }

    public String analysisByHeight(SimpleMidiFile simpleMidiFile) {
        StringBuilder result = new StringBuilder("<p>\r\nАнализ нот по высоте::");
        List<Note> notes = simpleMidiFile.vocalNoteList();
        Map<String, Integer> heights = new HashMap<>();
        for (Note note : notes) {
            if (heights.containsKey(note.sign().fullName())) {
                int temp = heights.get(note.sign().fullName());
                heights.put(note.sign().fullName(), ++temp);
            } else {
                heights.put(note.sign().fullName(), 1);
            }
        }
        for (Map.Entry<String, Integer> entry : heights.entrySet()) {
            result.append(entry.getKey() + ": " + entry.getValue() + "\r\n");
        }
        return result.toString();
    }

    public String intervalAnalysis(SimpleMidiFile simpleMidiFile) {
        StringBuilder result = new StringBuilder("<p>\r\nАнализ интервалов:");
        List<Note> notes = simpleMidiFile.vocalNoteList();
        Map<Integer, Integer> intervals = new HashMap<>();
        for (int i = 0; i < notes.size() - 1; i++) {
            int interval = notes.get(i).sign().diffInSemitones(notes.get(i + 1).sign());
            if (intervals.containsKey(interval)) {
                int temp = intervals.get(interval);
                intervals.put(interval, ++temp);
            } else {
                intervals.put(interval, 1);
            }
        }
        for (Map.Entry<Integer, Integer> entry : intervals.entrySet()) {
            result.append(entry.getKey() + ": " + entry.getValue() + "\r\n");
        }
        return result.toString();
    }
}
