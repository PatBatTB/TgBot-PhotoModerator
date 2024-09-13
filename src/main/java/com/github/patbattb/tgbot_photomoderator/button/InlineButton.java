package com.github.patbattb.tgbot_photomoderator.button;

import lombok.Getter;

@Getter
public enum InlineButton {
    YES("Yes", "a1_Yes"),
    NO("No", "a1_No"),
    CONFIRM("Confirm", "a2_Confirm");

    private final String text;
    private final String data;

    InlineButton(String text, String data) {
        this.text = text;
        this.data = data;
    }
}
