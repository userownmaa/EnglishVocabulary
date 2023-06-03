package com.example.englishvocabulary;

public class Index {
    private static int indexLevel = 0;
    private static int indexTopic = 0;

    public static int getIndexLevel() {
        return indexLevel;
    }

    public static void setIndexLevel(int indexLevel) {
        Index.indexLevel = indexLevel;
    }

    public static int getIndexTopic() {
        return indexTopic;
    }

    public static void setIndexTopic(int indexTopic) {
        Index.indexTopic = indexTopic;
    }
}
