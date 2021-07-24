package lesson4hw;

import kotlin.text.UStringsKt;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Уникальные слова и подсчет кол-ва неуникальных
        String[] wordsList = {"Test1", "Test2", "Test1", "Test3", "Test2", "Test4", "Test3", "Test5", "Test6", "Test4", "Test7", "Test8", "Test4"};
        ArrayList<String> wordsArrayList = new ArrayList<>(Arrays.asList(wordsList));
        Set<String> uniqueWords = new HashSet<>(wordsArrayList);

        HashMap<String, Integer> wordCount = new HashMap<>();

        Iterator<String> wordIterator = wordsArrayList.iterator();
        while (wordIterator.hasNext()) {
            String word = wordIterator.next();
            if (wordCount.get(word) == null) {
                wordCount.put(word, 1);
            } else {
                wordCount.put(word, wordCount.get(word) + 1);
            }
        }

        System.out.println("Исходный массив" + wordsArrayList);
        System.out.println("Уникальные слова" + uniqueWords);
        System.out.println("Количество уникальных слов" + wordCount);


        // Телефонный справочник

        Phonebook phoneBook = new Phonebook();
        phoneBook.add("123", "Test");
        phoneBook.add("312", "Test1");
        phoneBook.add("321", "Test2");
        phoneBook.add("322", "Test3");
        phoneBook.add("234", "Test");

        System.out.println();
        phoneBook.get("Test");
        System.out.println();
        phoneBook.get("Test2");
        System.out.println();
        phoneBook.get("Test8");
    }
}
