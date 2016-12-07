package com.akshay.demo;

import android.support.annotation.NonNull;
import android.text.format.DateUtils;
import android.util.SparseArray;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Utils {


    public static boolean isYesterday(long timestamp) {
        Calendar c1 = Calendar.getInstance(); // today
        c1.add(Calendar.DAY_OF_YEAR, -1); // yesterday

        Calendar c2 = Calendar.getInstance();
        c2.setTimeInMillis(timestamp); // your date

        return (c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR)
                && c1.get(Calendar.DAY_OF_YEAR) == c2.get(Calendar.DAY_OF_YEAR));
    }

    public static boolean isThisMonth(long timestamp) {
        Calendar c1 = Calendar.getInstance(); // today

        Calendar c2 = Calendar.getInstance();
        c2.setTimeInMillis(timestamp); // your date

        return (!DateUtils.isToday(timestamp) && !isYesterday(timestamp))
                && (c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR)
                && c1.get(Calendar.MONTH) == c2.get(Calendar.MONTH));
    }

    public static SparseArray<List<Message>> stubMessages() {
        SparseArray<List<Message>> messages = new SparseArray<>();

        Message cheese = buildMessage("Cheese", System.currentTimeMillis());
        Message milk = buildMessage("Milk", getYesterday());
        Message dinner = buildMessage("Dinner", getThisMonth());
        Message pasta = buildMessage("Pasta", getOlder());

        ArrayList<Message> list = new ArrayList<>();
        list.add(cheese);

        ArrayList<Message> list1 = new ArrayList<>();
        list1.add(milk);
        list1.add(dinner);
        list1.add(pasta);

        messages.put(0, list);
        messages.put(3, list1);

        return messages;
    }

    @NonNull
    private static Message buildMessage(String title, long time) {
        return new Message(title, time);
    }

    private static long getOlder() {
        Calendar c1 = Calendar.getInstance();
        c1.set(2016, Calendar.OCTOBER, 2);
        return c1.getTimeInMillis();
    }

    private static long getYesterday() {
        Calendar c1 = Calendar.getInstance();
        c1.set(2016, Calendar.NOVEMBER, 4);
        return c1.getTimeInMillis();
    }

    private static long getThisMonth() {
        Calendar c1 = Calendar.getInstance();
        c1.set(2016, Calendar.NOVEMBER, 2);
        return c1.getTimeInMillis();
    }

    /*public static List<SectionedRecyclerViewAdapter.Section> buildSections(ArrayList<Message> sCheeseStrings) {
        List<SectionedRecyclerViewAdapter.Section> sections = new ArrayList<>();
        int todayPos = 0, yesterdayPos = 0, monthPos = 0, olderPos = 0;

        for (int i = 0; i < sCheeseStrings.size(); i++) {
            Message message = sCheeseStrings.get(i);
            switch (message.getSectionType()) {
                case Message.TODAY:
                    todayPos++;
                    break;
                case Message.YESTERDAY:
                    yesterdayPos++;
                    break;
                case Message.MONTH:
                    monthPos++;
                    break;
                case Message.OLDER:
                    olderPos++;
                    break;
            }
        }
        Log.d("AJ", "buildSections: ");
        if (todayPos > 0)
            sections.add(new SectionedRecyclerViewAdapter.Section(0, "Today"));
        if (yesterdayPos > 0)
            sections.add(new SectionedRecyclerViewAdapter.Section(todayPos, "Yesterday"));
        if (monthPos > 0)
            sections.add(new SectionedRecyclerViewAdapter.Section(todayPos + yesterdayPos, "This Month"));
        if (olderPos > 0)
            sections.add(new SectionedRecyclerViewAdapter.Section(todayPos + yesterdayPos + monthPos, "Older"));
        return sections;
    }*/
}
