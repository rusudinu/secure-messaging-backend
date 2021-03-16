package com.codingshadows.securemessaging.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    private String messageText;
    private boolean seen;
    private List<User> seenList;

    public void addToSeenList(User user) {
        seenList.add(user);
    }
}
