package APIs;

import io.restassured.RestAssured;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.config;
import static io.restassured.RestAssured.given;
import static io.restassured.config.EncoderConfig.encoderConfig;

public class API {

    public void setTestData(){
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.defaultParser = Parser.JSON;
        config = config().encoderConfig(encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false));

    }
   @Test
    public static JsonPath getAllPosts(){

        RestAssured.config = RestAssuredConfig.config().encoderConfig(encoderConfig()
                .appendDefaultContentCharsetToContentTypeIfUndefined(false));

        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
        Response getAllPostsresponse = given()
                .header("User-Agent", "PostmanRuntime/7.32.2")
                .header("Accept", "*/*")
                .header("Accept-Encoding", "gzip, deflate, br")
                .header("Connection", "keep-alive")
                .contentType(ContentType.JSON)
                .when()
                .get("/posts")
                .then()
                .extract()
                .response();

        String postmanToken = getAllPostsresponse.header("Postman-Token");
        String host = getAllPostsresponse.header("Host");

        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
        Response getAllPosts = given()
                .header("User-Agent", "PostmanRuntime/7.32.2")
                .header("Accept", "*/*")
                .header("Accept-Encoding", "gzip, deflate, br")
                .header("Connection", "keep-alive")
                //.header("Content-Type", "application/json")
                .contentType(ContentType.JSON)
                .when()
                .get("/posts")
                .then()
                .extract()
                .response();

        Assert.assertEquals(getAllPostsresponse.getStatusCode() , 200);
        String responseBody = getAllPostsresponse.getBody().asString();
        Assert.assertNotNull(responseBody);

        return getAllPosts.jsonPath();
    }
   @Test
    public static JsonPath getSpecificPost(){

        RestAssured.config = RestAssuredConfig.config().encoderConfig(encoderConfig()
                .appendDefaultContentCharsetToContentTypeIfUndefined(false));

        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
        Response getSpecificPostresponse = given()
                .header("User-Agent", "PostmanRuntime/7.32.2")
                .header("Accept", "*/*")
                .header("Accept-Encoding", "gzip, deflate, br")
                .header("Connection", "keep-alive")
//               .header("Content-Type", "application/json")
                .contentType(ContentType.JSON)
                .get("/posts/1")
                .peek()
                .then()
                .extract()
                .response();

        String postmanToken = getSpecificPostresponse.header("Postman-Token");
        String host = getSpecificPostresponse.header("Host");

        Response getSpecificPost = given()
                .header("Postman-Token", postmanToken)
                .header("Host", host)
                .header("Postman-Token", postmanToken)
                .header("Host", host)
                .header("User-Agent", "PostmanRuntime/7.32.2")
                .header("Accept", "*/*")
                .header("Accept-Encoding", "gzip, deflate, br")
                .header("Connection", "keep-alive")
                .contentType(ContentType.JSON)
                .get("/posts/1")
                .peek()
                .then()
                .extract()
                .response();

        Assert.assertEquals(getSpecificPost.getStatusCode() , 200);
        String responseBody = getSpecificPost.getBody().asString();
        Assert.assertTrue(responseBody.contains("posts"));

        return getSpecificPost.jsonPath();
    }
   @Test
    public Response createPosts() {
            // Define JSON object for the request body
        String requestBody = "{\"title\":\"foo\", \"body\":\"bar\", \"userId\":1}";
        Response createPostsresponse = given()
                    .header("Content-Type", "text/plain")
                    .header("User-Agent", "PostmanRuntime/7.32.2")
                    .header("Accept", "*/*")
                    .header("Accept-Encoding", "gzip, deflate, br")
                    .header("Connection", "keep-alive")
                    .header("Content-Type", "application/json")
                    .contentType(ContentType.JSON) // Specify content type as JSON
                    .body(requestBody) // Use requestBody
                    .post("https://jsonplaceholder.typicode.com/posts/1")
                    .then().extract().response();

       String postmanToken = createPostsresponse.header("Postman-Token");
       String contentLength = createPostsresponse.header("Content-Length");
       String host = createPostsresponse.header("Host");

       Response createPosts = given()
               .header("Postman-Token", postmanToken)
               .header("Content-Type", "text/plain")
               .header("Content-Length", contentLength)
               .header("Host", host)
               .header("User-Agent", "PostmanRuntime/7.32.2")
               .header("Accept", "*/*")
               .header("Accept-Encoding", "gzip, deflate, br")
               .header("Connection", "keep-alive")
               .header("Content-Type", "application/json")
               .contentType(ContentType.JSON) // Specify content type as JSON
               .body(requestBody) // Use requestBody
               .post("https://jsonplaceholder.typicode.com/posts/1")
               .then().extract().response();


       int statusCode = createPostsresponse.getStatusCode();
            Assert.assertEquals(statusCode, 201);
        return createPostsresponse;
    }
    @Test(enabled = true)
public void test() {

        Response createPostsreResponse = createPosts();
        }

}
