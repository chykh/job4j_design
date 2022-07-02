package ru.job4j.question;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        int added = 0;
        int changed = 0;
        int deleted = 0;
        int c = 0;

        Map<Integer, String> map = new HashMap<>();
        for (User prev : previous) {
            map.put(prev.getId(), prev.getName());
        }

        for (User curr : current) {

            c = curr.getId();
            if (map.get(c) == null) {
                added++;
            }

            if (map.get(c) != null && !curr.getName().equals(map.get(c))) {
                changed++;
            }

        }

        deleted = map.size() - current.size() + added;

        return new Info(added, changed, deleted);
    }

}