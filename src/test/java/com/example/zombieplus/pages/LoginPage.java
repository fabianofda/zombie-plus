package com.example.zombieplus.pages;

import com.example.zombieplus.commons.Alerts;
import com.example.zombieplus.commons.Toast;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class LoginPage {

    public final WebDriver driver;

    @FindBy(css = ".login-form")
    public WebElement loginForm;

    @FindBy(css = "input[placeholder='E-mail']")
    public WebElement inputEmail;

    @FindBy(css = "input[placeholder='Senha']")
    public WebElement inputSenha;

    @FindBy(xpath = "//button[text()='Entrar']")
    public WebElement buttonEntrar;

    @FindBy(css = "a[href='/logout']")
    public WebElement logoutLink;

    @FindBy(css = ".toast")
    public WebElement toast;

    @FindBy(css = "span[class$=alert]")
    public WebElement alert;

    @FindAll(@FindBy(css = "span[class$=alert]"))
    private List<WebElement> alertElements;

    protected final Toast toastComponent;
    protected final Alerts alertsComponent;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.toastComponent = new Toast(driver);
        this.alertsComponent = new Alerts();
    }

    public void visit() {
        driver.get("http://localhost:3000/admin/login");
        assert (loginForm).isDisplayed();
    }

    public void submit(String email, String senha) {
        inputEmail.sendKeys(email);
        inputSenha.sendKeys(senha);
        buttonEntrar.click();
    }

    public void isLoggedIn() {
        logoutLink.isDisplayed();
    }

    public String haveText() {
        return toastComponent.getToastText(toast);
    }

    public String alertHaveText() {
        return alertsComponent.getAlertText(alert);
    }

    public List<String> getAlertMessages() {
        return alertsComponent.getAlertMessages(alertElements);
    }
}
