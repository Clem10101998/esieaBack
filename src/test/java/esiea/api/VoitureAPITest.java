package esiea.api;

import esiea.dao.VoitureDAO;
import esiea.metier.Voiture;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.assertj.core.api.Assertions.*;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

import java.sql.SQLException;

public class VoitureAPITest extends JerseyTest {

    VoitureDAO voitureDAO = new VoitureDAO();
    Voiture voiture = new Voiture();
    VoitureAPI carAPI = new VoitureAPI();

    @Override
    protected Application configure() {
        return new ResourceConfig(VoitureAPI.class);
    }

    @Test
    @DisplayName("Récupération de toutes les voitures")
    public void getVoituresJson_whenCorrectRequest_thenResponseIsOkAndContainsJsonListOfAllVoitures() throws SQLException {
        Response response = target("/voiture/get/all").request().get();

        JSONArray liste = new JSONArray();
        JSONObject ret = new JSONObject();

        Voiture[] voitures = {};
        voitures = voitureDAO.getVoitures(null);

        String content = response.readEntity(String.class);

        for (Voiture v : voitures) {
            liste.put(v);
        }
        ret.put("voitures", liste);

        System.out.println("Code de la réponse : "+response.getStatus());
        System.out.println("Réponse Api : "+content);
        System.out.println("Comparaison avec la méthode dans VoitureDAO : "+ret.toString());

        assertThat(Response.Status.OK.getStatusCode()).isEqualTo(response.getStatus());
        assertThat(content).isNotNull().isEqualTo(ret.toString());

    }

    @Test
    @DisplayName("Filtrage des voitures")
    public void getVoituresJson_whenCorrectRequest_thenResponseIsOkAndContainsJsonListOfVoitures() throws SQLException {
        Response response = target("/voiture/get/Renault").request().get();

        JSONArray liste = new JSONArray();
        JSONObject ret = new JSONObject();

        Voiture[] voitures = {};
        voitures = voitureDAO.getVoiture("Renault");

        String content = response.readEntity(String.class);

        for (Voiture v : voitures) {
            liste.put(v);
        }
        ret.put("voitures", liste);

        System.out.println(content);
        System.out.println(ret);

        assertThat(Response.Status.OK.getStatusCode()).isEqualTo(response.getStatus());
        assertThat(content).isNotNull().isNotEmpty().isEqualTo(ret.toString());

    }

    @Test
    @DisplayName("Récupération d'une voiture par rapport à son Id")
    public void getVoituresJson_whenCorrectRequest_thenResponseIsOkAndContainsJsonListOfVoiture_Identified_By_An_Id() throws SQLException {
        Response response = target("/voiture/get/39").request().get();

        final String json = target("voiture/get/39").request().get(String.class);

        JSONArray liste = new JSONArray();
        JSONObject ret = new JSONObject();

        Voiture[] voiture = {};
        voiture = voitureDAO.getVoiture("39");

        String content = response.readEntity(String.class);

        for (Voiture v : voiture) {
            liste.put(v);
        }
        ret.put("voiture", liste);

        System.out.println(content);
        System.out.println(ret);
        System.out.println(voiture.toString());

        assertThat(Response.Status.OK.getStatusCode()).isEqualTo(response.getStatus());
        assertThat(content).isNotNull().isNotEmpty();

    }

    @Test
    @DisplayName("Retourne l'objet voiture depuis un JsonObject et vérifie la conformité des attributs")
    public void voituresFromJsonTest_when_attributes_are_conforms(){

        String jsonVoiture = "{\"marque\":\"JsonTest\",\"modele\":\"corvette\",\"finition\":\"test\",\"carburant\":\"E\",\"km\":30,\"annee\":1900,\"prix\":100}";
        JSONObject json = new JSONObject(jsonVoiture);

        voiture = carAPI.voitureFromJson(json);
        assertThat(voiture.check()).isTrue();

    }

    @Test
    @DisplayName("Création d'une voiture")
    public void addVoitureJsonTest() throws SQLException {

        String jsonVoiture = "{\"marque\":\"JsonTest\",\"modele\":\"corvette\",\"finition\":\"test\",\"carburant\":\"E\",\"km\":30,\"annee\":2000,\"prix\":100}";
        Response response = target("/voiture/add").request().post(Entity.json(jsonVoiture));

        System.out.println(response);
        assertThat(Response.Status.OK.getStatusCode()).isEqualTo(response.getStatus());

    }

    @Test
    @DisplayName("Suppression d'une voiture par rapport à son ID")
    public void deleteVoitureJsonTest() throws SQLException {

        Response response = target("/voiture/del").request().post(Entity.json("45"));

        System.out.println(response);
        assertThat(Response.Status.OK.getStatusCode()).isEqualTo(response.getStatus());
    }
}