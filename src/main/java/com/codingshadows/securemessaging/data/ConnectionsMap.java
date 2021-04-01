package com.codingshadows.securemessaging.data;

import com.codingshadows.securemessaging.model.MutableInt;

import java.util.HashMap;
import java.util.Map;

public class ConnectionsMap {
    public static Map<String, String> connectionsMap = new HashMap<>();
    public static Map<String, MutableInt> connectionsCounterMap = new HashMap<>();
}
