package ru.liga.engine;

import com.leff.midi.event.MidiEvent;
import com.leff.midi.event.NoteOff;
import com.leff.midi.event.NoteOn;
import com.leff.midi.event.meta.Tempo;
import ru.liga.songtask.domain.SimpleMidiFile;

import java.io.File;
import java.io.IOException;

public class Changer {

    private final int temp;
    private final int trans;

    public Changer(String trans, String temp) {
        this.temp = Integer.parseInt(temp);
        this.trans = Integer.parseInt(trans);
    }

    public void perform(SimpleMidiFile simpleMidiFile) throws IOException {
        for (MidiEvent event : simpleMidiFile.getMidiFormat().getTracks().get(1).getEvents()) {
            if (event.getClass().equals(NoteOn.class)) {
                NoteOn noteOn = (NoteOn) event;
                noteOn.setNoteValue(noteOn.getNoteValue() + trans);
            }
            if (event.getClass().equals(NoteOff.class)) {
                NoteOff noteOff = (NoteOff) event;
                noteOff.setNoteValue(noteOff.getNoteValue() + trans);
            }
        }
        for (MidiEvent event : simpleMidiFile.getMidiFormat().getTracks().get(0).getEvents()) {
            if (event.getClass().equals(Tempo.class)) {
                Tempo tempo = (Tempo) event;
                tempo.setBpm((tempo.getBpm() * (1 + (float) temp / 100)));
            }
        }
        try {
            simpleMidiFile.getMidiFormat().writeToFile(new File(Engine.path.getOutPath() + "\\zombie-trans2-tempo20.mid"));
        } catch (IOException e) {
            Engine.changerLogger.info(e.getMessage());
        }
    }

}
