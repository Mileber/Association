package com.example.yutong.association;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yutong on 2016/2/13.
 */
public class PracticeContent {
    //TODO:get the data of practice activity from database

    /**
     * An array of sample (dummy) items.
     */
    public static final List<Practice> ITEMS = new ArrayList<Practice>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, Practice> ITEM_MAP = new HashMap<String, Practice>();

    private static final int COUNT = 25;

    static {
        // Add some sample items.
        for (int i = 1; i <= COUNT; i++) {
            addItem(createPracticeItem(i));
        }
    }

    private static void addItem(Practice item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    private static Practice createPracticeItem(int position) {
        //TODO
        return new Practice("上海大学社团aaaaaaaaaaa");
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }
}

