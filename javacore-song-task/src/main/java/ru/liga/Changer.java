package ru.liga;

import com.leff.midi.event.MidiEvent;
import com.leff.midi.event.NoteOn;
import com.leff.midi.event.meta.Tempo;
import ru.liga.songtask.domain.Note;
import ru.liga.songtask.domain.SimpleMidiFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Changer {

    String path = "E:/FOR_WORK/Программирование/Java/Стажировка/liga-internship2/javacore-song-task/src/main/resources/cranberries-zombie.mid";
    String path2 = "E:/FOR_WORK/Программирование/Java/Стажировка/liga-internship2/javacore-song-task/src/main/resources/zombie-trans2-tempo20.mid";
    SimpleMidiFile simpleMidiFile = new SimpleMidiFile(new File(path));

    public void perform(SimpleMidiFile simpleMidiFile) throws IOException {
        List<Note> notes = simpleMidiFile.vocalNoteList();

        for(MidiEvent event : simpleMidiFile.getMidiFormat().getTracks().get(1).getEvents()){
            if(event.getClass().equals(NoteOn.class)) {
                NoteOn noteOn = (NoteOn) event;
                noteOn.setNoteValue(noteOn.getNoteValue() + 2);
            }
        }
        for(MidiEvent event : simpleMidiFile.getMidiFormat().getTracks().get(0).getEvents()){
            if(event.getClass().equals(Tempo.class)) {
                Tempo tempo = (Tempo) event;
                tempo.setBpm((float) (tempo.getBpm() * 1.2));
            }
        }
        simpleMidiFile.getMidiFormat().writeToFile(new File(path2));
    }
}
