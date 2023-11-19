package com.example.zombieplus.commons;

import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class Alerts {
    public String getAlertText(WebElement alertElement) {
        return alertElement.getText();
    }

    public List<String> getAlertMessages(List<WebElement> alertElements) {
        return alertElements.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }
}
