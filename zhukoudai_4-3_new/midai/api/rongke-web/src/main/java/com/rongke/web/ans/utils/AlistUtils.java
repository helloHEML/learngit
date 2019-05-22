package com.rongke.web.ans.utils;

import java.util.HashSet;
import java.util.List;

public class AlistUtils {

    public static List removeDuplicate(List list) {
        HashSet h = new HashSet(list);
        list.clear();
        list.addAll(h);
        return list;
    }
}
