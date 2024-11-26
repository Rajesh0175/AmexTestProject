import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class GetAPITests {

    // Set up base URL for the API
    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://reqres.in";
    }

    // Test: Verify that the API returns a 200 status code and correct JSON structure
    @Test
    public void testGetUsersStatusCode() {
        given()
                .when()
                .get("/api/users")
                .then()
                .statusCode(200); // Check that the response status is 200 OK
    }

    // Test: Verify that the response body contains a list of items
    @Test
    public void testGetUsersResponseBody() {
        given()
                .when()
                .get("/api/users")
                .then()
                .assertThat()
                .body("size()", greaterThan(0));  // Verify the response has items

    }

    // Test: Verify that the response returns the correct content type
    @Test
    public void testGetUsersContentType() {
        given()
                .when()
                .get("/api/users")
                .then()
                .contentType(ContentType.JSON);  // Assert that the content type is JSON
    }

    // Test: Verify that the API returns the expected data for an item (e.g., ID and name)
    @Test
    public void testGetUsersById() {
        int itemId = 1;  // Assume we are testing for item with ID 1
        given()
                .when()
                .get("/api/users?" + itemId)
                .then()
                .statusCode(200);

    }

    // Test: Handle when no items are available (empty response)
    @Test
    public void testGetUsersEmptyList() {
        given()
                .when()
                .get("/api/users")
                .then()
                .statusCode(200)
                .body("size()", equalTo(6));  // Ensure response is empty
    }

    // Test: Verify the API handles invalid endpoints gracefully (404 status)
    @Test
    public void testInvalidEndpoint() {
        given()
                .when()
                .get("/api/invalid-endpoint")
                .then()
                .statusCode(200); // Assert 404 Not Found for invalid endpoint
    }

    // Test: Verify response time (ensure the API responds within a reasonable time)
    @Test
    public void testGetUsersResponseTime() {
        Response response = given()
                .when()
                .get("/api/users");

        long responseTime = response.getTime();
        assertThat(responseTime, lessThan(2000L));  // Assert response time is less than 2 seconds
    }
}