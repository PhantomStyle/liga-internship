package ru.liga.engine;

public class Path {
    private final String inPath;
    private final String outPath;

    public Path(String inPath) {
        this.inPath = inPath.replaceAll("/", "\\");
        this.outPath = inPath.substring(0, inPath.lastIndexOf("\\"));
    }

    public String getOutPath() {
        return outPath;
    }

    public String getInPath() {
        return inPath;
    }
}
