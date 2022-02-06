package esiea.metier;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class VoitureTest {

   Voiture voiture;

    @BeforeEach
    public void init(){
        voiture = new Voiture();
    }

    @Test
    @DisplayName("getId")
    public void getIdTest(){
        voiture.setId(2);
        assertThat(voiture.getId()).isEqualTo(2).isPositive();
    }

    @Test
    @DisplayName("getMarque")
    public void getMarqueTest(){
        voiture.setMarque("test");
        assertThat(voiture.getMarque()).contains("test");
    }

    @Test
    @DisplayName("getModele")
    public void getModeleTest(){
        voiture.setModele("test");
        assertThat(voiture.getModele()).contains("test");
    }

    @Test
    @DisplayName("getFinition")
    public void getFinitionTest(){
        voiture.setFinition("test");
        assertThat(voiture.getFinition()).contains("test");
    }

    @Test
    @DisplayName("getCarburant")
    public void getCarburantTest(){
        voiture.setCarburant(Voiture.Carburant.ELECTRIQUE);
        assertThat(voiture.getCarburant()).isEqualTo(Voiture.Carburant.ELECTRIQUE);
    }

    @Test
    @DisplayName("getKm")
    public void getKmTest(){
        voiture.setKm(300);
        assertThat(voiture.getKm()).isBetween(0, 5000);
    }

    @Test
    @DisplayName("getAnnee")
    public void getAnneeTest(){
        voiture.setAnnee(2000);
        assertThat(voiture.getAnnee()).isPositive().isEqualTo(2000);
    }

    @Test
    @DisplayName("getPrix")
    public void getPrixTest(){
        voiture.setPrix(30);
        assertThat(voiture.getPrix()).isBetween(0, 60000);
    }

    //Test for check() Method
    @Test
    @DisplayName("Retourne true si tous les attribues sont conformes")
    public void checkTest_When_attributes_are_conforms(){
        voiture.setId(2);
        voiture.setMarque("test");
        voiture.setModele("test");
        voiture.setFinition("test");
        voiture.setKm(300);
        voiture.setAnnee(1999);
        voiture.setPrix(30);
        voiture.setCarburant(Voiture.Carburant.ELECTRIQUE);

        boolean test = voiture.check();
        assertThat(test).isTrue();
    }

    @Test
    @DisplayName("Retourne false si l'un des attributs n'est pas conforme")
    public void checkTest_When_attributes_are_not_conforms(){

        voiture.setMarque("test");
        voiture.setModele("test");
        voiture.setFinition("test");
        voiture.setKm(300);
        voiture.setAnnee(1800);
        voiture.setPrix(30);
        voiture.setCarburant(Voiture.Carburant.ELECTRIQUE);

        boolean test = voiture.check();
        assertThat(test).isFalse();
    }

    //Test for getTypeDonnee() method
    @Test
    @DisplayName("Retourne la chaine 'string' si la String passée en paramètre correspond aux données du tableau 'strings'")
    public void getTypeDonneeTest_When_Donnee_is_String(){
        String donnee_string = "finition";
        String string = voiture.getTypeDonnee(donnee_string);
        assertThat(string).isNotNull().contains("string");
    }

    @Test
    @DisplayName("Retourne la chaine 'entier' si la String passée en paramètre correspond aux données du tableau 'entiers'")
    public void getTypeDonneeTest_When_Donnee_is_Entier(){
        String donnee_entier = "km";
        String entier = voiture.getTypeDonnee(donnee_entier);
        assertThat(entier).isEqualTo("entier");
    }

    @Test
    @DisplayName("Retourne une chaine vide si la String passée en paramètre est nulle")
    public void getTypeDonneeTest_When_Donnee_is_Null() {
        String donnee_null = null;
        String empty = voiture.getTypeDonnee(donnee_null);
        assertThat(empty).isEqualTo("").isEmpty();

    }
}