package com.example.zombieplus.e2e;

import com.example.zombieplus.pages.LeadsPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LeadsTest extends BaseTest{
    private LeadsPage leadsPage;

    @BeforeEach
    public void setUpLeads() {
        leadsPage = new LeadsPage(driver);
    }

    @Test
    @DisplayName("Deve cadastrar um lead na fila de espera")
    public void CT01_Leads() {
            leadsPage.visit();
            leadsPage.openLeadModal();
            leadsPage.submitLeadForm("Qa tester", "test@qa.io");

            String expectedMessage = "Agradecemos por compartilhar seus dados conosco. Em breve, nossa equipe entrará em contato!";
            assertEquals(expectedMessage, leadsPage.haveText());
    }

    @Test
    @DisplayName("Nao deve cadastrar com email incorreto")
    public void CT02_Leads() {
        leadsPage.visit();
        leadsPage.openLeadModal();
        leadsPage.submitLeadForm("Qa tester", "test.qa.io");

        String expectedMessage = "Email incorreto";
        assertEquals(expectedMessage, leadsPage.alertHaveText());
    }

    @Test
    @DisplayName("Nao deve cadastrar quando o nome nao for preenchido")
    public void CT03_Leads() {
        leadsPage.visit();
        leadsPage.openLeadModal();
        leadsPage.submitLeadForm("", "test@qa.io");

        String expectedMessage = "Campo obrigatório";
        assertEquals(expectedMessage, leadsPage.alertHaveText());
    }

    @Test
    @DisplayName("Nao deve cadastrar quando o email nao for preenchido")
    public void CT04_Leads() {
        leadsPage.visit();
        leadsPage.openLeadModal();
        leadsPage.submitLeadForm("Qa tester", "");

        String expectedMessage = "Campo obrigatório";
        assertEquals(expectedMessage, leadsPage.alertHaveText());
    }

    @Test
    @DisplayName("Nao deve cadastrar quando nenhum campo é preenchido")
    public void CT05_Leads() {
        leadsPage.visit();
        leadsPage.openLeadModal();
        leadsPage.submitLeadForm("", "");

        List<String> expectedMessages = Arrays.asList("Campo obrigatório", "Campo obrigatório");
        String[] actualMessages = leadsPage.getAlertMessages().toArray(new String[0]);

        assertArrayEquals(expectedMessages.toArray(), actualMessages);
    }

}
