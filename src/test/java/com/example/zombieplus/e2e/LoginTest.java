package com.example.zombieplus.e2e;

import com.example.zombieplus.pages.LoginPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class LoginTest extends BaseTest{
    private LoginPage loginPage;

    @BeforeEach
    public void setUpLogin() {
        this.loginPage = new LoginPage(driver);
    }

    @Test
    @DisplayName("Deve logar como administrador")
    public void CT01_Login() {
        loginPage.visit();
        loginPage.submit("admin@zombieplus.com", "pwd123");
        loginPage.isLoggedIn();
    }

    @Test
    @DisplayName("Nao deve logar com senha incorreta")
    public void CT02_Login() {
        loginPage.visit();
        loginPage.submit("admin@zombieplus.com", "abc123");

        String expectedMessage = "Oops!\nOcorreu um erro ao tentar efetuar o login. Por favor, verifique suas credenciais e tente novamente.";
        assertEquals(expectedMessage, loginPage.haveText());
    }

    @Test
    @DisplayName("Nao deve logar quando o email é invalido")
    public void CT03_Login() {
        loginPage.visit();
        loginPage.submit("test.com.br", "abc123");

        String expectedMessage = "Email incorreto";
        assertEquals(expectedMessage, loginPage.alertHaveText());
    }

    @Test
    @DisplayName("Nao deve logar quando o email nao é preenchido")
    public void CT04_Login() {
        loginPage.visit();
        loginPage.submit("", "abc123");

        String expectedMessage = "Campo obrigatório";
        assertEquals(expectedMessage, loginPage.alertHaveText());
    }

    @Test
    @DisplayName("Nao deve logar quando a senha nao é preenchida")
    public void CT05_Login() {
        loginPage.visit();
        loginPage.submit("admin@zombieplus.com", "");

        String expectedMessage = "Campo obrigatório";
        assertEquals(expectedMessage, loginPage.alertHaveText());
    }

    @Test
    @DisplayName("Nao deve logar quando nenhum campos é preenchido")
    public void CT06_Login() {
        loginPage.visit();
        loginPage.submit("", "");

        List<String> expectedMessages = Arrays.asList("Campo obrigatório", "Campo obrigatório");
        String[] actualMessages = loginPage.getAlertMessages().toArray(new String[0]);

        assertArrayEquals(expectedMessages.toArray(), actualMessages);
    }
}
