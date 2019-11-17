package com.kientran.myrecyclerview.dummy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<DummyItem> ITEMS = new ArrayList<DummyItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, DummyItem> ITEM_MAP = new HashMap<String, DummyItem>();

    private static final int COUNT = 25;

    static {
        // Add some sample items.
        for (int i = 1; i <= COUNT; i++) {
            addItem(createDummyItem(i));
        }
    }

    private static void addItem(DummyItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.title, item);
    }

    private static DummyItem createDummyItem(int position) {
        return new DummyItem(position%2==0, "Note: " + position, "2019-10-25",  makeDetails(position), position%2!=0);
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class DummyItem {
        public final Boolean isPriority;
        public final String title;
        public final String createdDate;
        public final String details;
        public  Boolean isFavourite;

        public DummyItem(Boolean isPriority, String title, String createdDate, String details, Boolean isFavourite) {
            this.isPriority = isPriority;
            this.title = title;
            this.createdDate = createdDate;
            this.details = details;
            this.isFavourite = isFavourite;
        }

        @Override
        public String toString() {
            return "DummyItem{" +
                    "isPriority=" + isPriority +
                    ", title='" + title + '\'' +
                    ", createdDate='" + createdDate + '\'' +
                    ", details='" + details + '\'' +
                    ", isFavourite=" + isFavourite +
                    '}';
        }
    }
}
