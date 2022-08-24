package ru.bibaboba.core_utils;

import androidx.annotation.NonNull;
import java.util.ArrayList;
import java.util.Locale;

public class Searcher<T extends Searchable> {

    public ArrayList<T> search(String request, @NonNull T[] data){

        ArrayList<T> result = new ArrayList<>();

        for (T element : data) {
            if (element.containRequest(this::isContainsRequest, request))
                result.add(element);
        }

        return result;
    }

    private boolean isContainsRequest(String request, String whereSearch) {

        String lowerCaseWhereSearch = whereSearch.toLowerCase();
        String[] singleWords = getSingleWords(lowerCaseWhereSearch);

        for (String singleWord : singleWords) {

            double quantity = 0;
            int length = Math.min(request.length(), singleWord.length());

            for (int i = 0; i < length; i++) {
                if (request.charAt(i) != singleWord.charAt(i))
                    quantity++;
            }

            if (quantity / length < 0.25 || lowerCaseWhereSearch.contains(request.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    private String[] getSingleWords(String s){
        int amount = getAmountOfWords(s);
        String[] singleWords = new String[amount];
        int j = 0;
        for(int i = 0; i < amount;i++) {
            StringBuilder singleWordRequest = new StringBuilder();

            while (j<s.length() && s.charAt(j) == ' ') {
                ++j;
            }

            while (j != s.length() && s.charAt(j) != ' ') {
                singleWordRequest.append(s.charAt(j));
                ++j;
            }

            singleWords[i] = singleWordRequest.toString();
        }
        return singleWords;
    }

    private int getAmountOfWords(String request) {

        int count = 1;
        boolean flag = false;

        for(int i = 0; i < request.length(); i++) {
            if(request.charAt(i) == ' ')
                flag = true;

            if(flag && request.charAt(i) != ' ') {
                ++count;
                flag = false;
            }
        }
        return count;
    }
}
