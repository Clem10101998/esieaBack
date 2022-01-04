package esiea.dao;

import esiea.metier.Voiture;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.sql.DataSource;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.sql.*;

@ExtendWith(MockitoExtension.class)
public class VoitureDAOTest {

    @Mock
    private Connection mockConnection;
    @Mock
    private PreparedStatement mockStmt;
    @Mock
    private ResultSet mockRes;

    @Before
    public void setUp() throws SQLException{
        MockitoAnnotations.initMocks(this);
    }

    Voiture voiture;
    VoitureDAO voitureDAO;

    @BeforeEach
    public void init(){
        voiture = new Voiture();
        voitureDAO = new VoitureDAO();

        //Définition d'une voiture
        voiture.setId(48);
        voiture.setMarque("Clem");
        voiture.setModele("Renault");
        voiture.setFinition("test");
        voiture.setKm(300);
        voiture.setAnnee(1900);
        voiture.setPrix(30);
        voiture.setCarburant(Voiture.Carburant.ELECTRIQUE);

    }

    /*@Test
    public void TestMockConnection() throws SQLException{
        //Mockito.when(mockConnection.prepareStatement(voitureDAO.getVoiture("2")).executeUpdate(Mockito.any())).thenReturn(2);
        Mockito.when(mockConnection.prepareStatement("select id from Voiture").executeQuery(Mockito.any()));
    }*/

    ////Test for getVoiture() method
    @Test
    @DisplayName("Récupération d'une voiture par rapport à son Id")
    public void getVoitureTest_When_id_is_specified() throws SQLException {

            Voiture [] voitures = {};
            voitures = voitureDAO.getVoiture("45");
            assertThat(voitures.length).isGreaterThanOrEqualTo(0);
            assertThat(voitures[0].getId()).isPositive().isGreaterThanOrEqualTo(1);
            System.out.println("Marque : " +voitures[0].getMarque());
    }

    @Test
    @DisplayName("Récupération de voitures par rapport à un filtre (Renault)")
    public void getVoitureTest_When_another_parameter_is_specified() throws SQLException {
        Voiture [] voitures = {};
        voitures = voitureDAO.getVoiture("Renault");
        assertThat(voitures.length).isGreaterThanOrEqualTo(0);
        for(int i =0; i < voitures.length; i++){
            System.out.println("ID voitures : "+voitures[i].getId()+" ");
        }
    }

    //Test for getVoitures() method
    @Test
    @DisplayName("Récupération de l'ensemble des voitures présentes dans la BDD")
    public void getVoituresTest_When_no_parameters_are_specified() throws SQLException {
        Voiture [] voitures = {};
        voitures = voitureDAO.getVoitures(null);
        assertThat(voitures.length).isPositive().isGreaterThanOrEqualTo(0);
        for(int i =0; i < voitures.length; i++){
            System.out.println("Marque voitures : "+voitures[i].getMarque()+" ");
            assertThat(voitures[i]).isExactlyInstanceOf(Voiture.class);
        }
    }

    //Test for ajouterVoiture() method
    @Test
    @DisplayName("Ajout d'une voiture dans la BDD")
    public void ajouterVoitureTest() throws SQLException, InterruptedException {

        voitureDAO.ajouterVoiture(voiture);

    }

    //Test for supprimerVoiture() method
    @Test
    @DisplayName("Supression d'une voiture de la BDD")
    public void supprimerVoitureTest() throws SQLException {

        voitureDAO.supprimerVoiture("12");

    }




}