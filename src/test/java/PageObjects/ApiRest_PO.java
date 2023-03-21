package PageObjects;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

public class ApiRest_PO {
    public Response response;
    public Response postMethod(String name, String email, String gender, String status) {
       String request = createUser(name,email,gender,status);

       response = given().headers("Authorization","Bearer a84f36d8a3244bdd4258541b10c4ce4e630053a013c7a91471940fc74657614b",
               "Content-Type","application/json","Accept", ContentType.JSON)
               .body(request).post();

       return response;
    }

    public String createUser(String name, String email, String gender, String status) {
        String body = "";
        try {
            body = new String(Files.readAllBytes(Paths.get(System.getProperty("user.dir")+ File.separator + "src/test/resources/data/userDetails.json")));
            body.replaceAll("setEmail",name);
            body.replaceAll("setName",email);
            body.replaceAll("setGender",gender);
            body.replaceAll("setStatus",status);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return body;
    }

    public Response getMethod() {
        response = given().headers("Authorization","Bearer a84f36d8a3244bdd4258541b10c4ce4e630053a013c7a91471940fc74657614b",
                "Content-Type","application/json","Accept", ContentType.JSON).get();
        return response;
    }
    public Response putMethod(String name, String gender, String status) {
        String userUpdate = userUpdate(name,gender,status);

        response = given().headers("Authorization","Bearer a84f36d8a3244bdd4258541b10c4ce4e630053a013c7a91471940fc74657614b",
                        "Content-Type","application/json","Accept", ContentType.JSON)
                .body(userUpdate).put();

        return response;
    }
    public String userUpdate(String name, String gender, String status) {
        String body = "";
        try {
            body = new String(Files.readAllBytes(Paths.get(System.getProperty("user.dir")+ File.separator + "src/test/resources/data/userUpdate.json")));
            body.replaceAll("updateName",name);
            body.replaceAll("updateGender",gender);
            body.replaceAll("updateStatus",status);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return body;
    }
    public Response postComment(String comment,String id) {
        String bodyComment = createComment(comment,id);

        response = given().headers("Authorization","Bearer a84f36d8a3244bdd4258541b10c4ce4e630053a013c7a91471940fc74657614b",
                        "Content-Type","application/json","Accept", ContentType.JSON)
                .body(bodyComment).post();

        return response;
    }

    public String createComment(String comment, String id) {
        String body = "";
        try {
            body = new String(Files.readAllBytes(Paths.get(System.getProperty("user.dir")+ File.separator + "src/test/resources/data/postComment.json")));
            body.replaceAll("comment",comment);
            body.replaceAll("post_id",id);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return body;
    }
    public Response deleteUser(String id) {
        String bodyID = getID(id);

        response = given().headers("Authorization","Bearer a84f36d8a3244bdd4258541b10c4ce4e630053a013c7a91471940fc74657614b",
                        "Content-Type","application/json","Accept", ContentType.JSON)
                .body(bodyID).delete();

        return response;
    }

    public String getID(String id) {
        String body = "";
        try {
            body = new String(Files.readAllBytes(Paths.get(System.getProperty("user.dir")+ File.separator + "src/test/resources/data/deleteUser.json")));
            body.replaceAll("userID",id);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return body;
    }
}
