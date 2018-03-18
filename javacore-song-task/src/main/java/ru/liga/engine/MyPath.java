package ru.liga.engine;

public class MyPath {
    private final String inPath;
    private final String outPath;

    public MyPath(String inPath) {
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
