package com.example.zombieplus.pages;

import com.example.zombieplus.commons.Alerts;
import com.example.zombieplus.commons.Toast;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LeadsPage {

    @FindBy(css = "input[placeholder='Informe seu nome']")
    public WebElement inputNome;

    @FindBy(css = "input[placeholder='Informe seu email']")
    public WebElement inputEmail;

    @FindBy(xpath = "//button[contains(text(), 'Aperte o play...')]")
    public WebElement buttonPlay;

    @FindBy(xpath = "//div[@data-testid='modal']/h2")
    public WebElement modal;

    @FindBy(xpath = "//button[text()='Quero entrar na fila!']")
    public WebElement buttonEntrarNaFila;

    @FindBy(css = ".toast")
    public WebElement toast;

    @FindBy(css = "span[class$=alert]")
    public WebElement alert;

    @FindAll(@FindBy(css = "span[class$=alert]"))
    private List<WebElement> alertElements;

    private final WebDriver driver;
    private final Toast toastComponent;
    private final Alerts alertsComponent;

    public LeadsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.toastComponent = new Toast(driver);
        this.alertsComponent = new Alerts();
    }

    public void visit() {
        driver.get("http://localhost:3000");
        assertEquals("Zombie+ | Mais que um streaming, uma experiência arrepiante!", driver.getTitle());
    }

    public void openLeadModal() {
        buttonPlay.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(modal));

        assertEquals("Fila de espera", modal.getText());
    }

    public void submitLeadForm(String nome, String email){
        inputNome.sendKeys(nome);
        inputEmail.sendKeys(email);
        buttonEntrarNaFila.click();
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
