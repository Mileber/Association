package com.example.yutong.association;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AssociationContent {
    //TODO:get the data of association from database

    /**
     * An array of sample (dummy) items.
     */
    public static final List<Association> ITEMS = new ArrayList<Association>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, Association> ITEM_MAP = new HashMap<String, Association>();

    private static final int COUNT = 10;

    static {
        // Add some sample items.
        for (int i = 1; i <= COUNT; i++) {
            addItem(createAssociationItem(i));
        }
    }

    private static void addItem(Association item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    private static Association createAssociationItem(int position) {
        //TODO
        return new Association("上海大学社团aaaaaaaaaaa"+String.valueOf(position), "Item " + position, makeDetails(position));
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
