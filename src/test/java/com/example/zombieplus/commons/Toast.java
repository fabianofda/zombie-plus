package com.example.zombieplus.commons;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Toast {
    private WebDriver driver;

    public Toast(WebDriver driver) {
        this.driver = driver;
    }

    public String getToastText(WebElement toastElement) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(toastElement));

        String toastText = toastElement.getText();

        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".toast")));

        return toastText;
    }

}
