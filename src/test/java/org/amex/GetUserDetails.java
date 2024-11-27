package org.amex;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GetUserDetails {
    @Test
    public void GetUserDetails()
    {
        // Specify the base URL to the RESTful web service
        RestAssured.baseURI = "https://reqres.in/api/users";
        // Get the RequestSpecification of the request to be sent to the server
        RequestSpecification httpRequest = RestAssured.given();

        Response response = httpRequest.get("");

        // Get the status code of the request.
        //If request is successful, status code will be 200
        int statusCode = response.getStatusCode();

        // Assert that correct status code is returned.
        Assert.assertEquals(statusCode /*actual value*/, 200 /*expected value*/,
                "Correct status code returned");

    }

    @Test
    public void IteratingHeaders()
    { RestAssured.baseURI = "https://reqres.in/api/users";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.get("");
        // Get all the headers and then iterate over allHeaders to print each header
        Headers allHeaders = response.headers();
        // Iterate over all the Headers
        for(Header header : allHeaders) {
            System.out.println("Key: " + header.getName() + " Value: " + header.getValue());
        }
    }

    @Test
    public void GetUserHeaders() {
        RestAssured.baseURI = "https://reqres.in/api/users";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.get("");
// Access header with a given name.
        String contentType = response.header("Content-Type");
        System.out.println("Content-Type value: " + contentType);
// Access header with a given name.
        String serverType = response.header("Server");
        System.out.println("Server value: " + serverType);
// Access header with a given name. Header = Content-Encoding
        String acceptLanguage = response.header("Content-Encoding");
        System.out.println("Content-Encoding: " + acceptLanguage);
    }

    @Test
    public void GetUserResponseBody()
    {
        RestAssured.baseURI = "https://reqres.in/api/users";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.get("/Hyderabad");

        // Retrieve the body of the Response
        ResponseBody body = response.getBody();
        System.out.println(body);
        // By using the ResponseBody.asString() method, we can convert the  body
        // into the string representation.
        System.out.println("Response Body is: " + body.asString());
    }
    /*@Test
    public void VerifyUserDetailsJSON()
    {
        RestAssured.baseURI = "https://reqres.in/api/users";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.get("/Hyderabad");

        // First get the JsonPath object instance from the Response interface
        JsonPath jsonPathEvaluator = response.jsonPath();

        // Then simply query the JsonPath object to get a String value of the node
        // specified by JsonPath: City (Note: You should not put $. in the Java code)
        String city = jsonPathEvaluator.get("City");

        // Let us print the city variable to see what we got
        System.out.println("City received from Response " + city);

        // Validate the response
        Assert.assertEquals(city, "Hyderabad", "Correct city name received in the Response");

    }*/
    @Test
    public void DisplayAllNodesInUserAPI()
    {
        RestAssured.baseURI = "https://reqres.in/api/users";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.get("/Hyderabad");

        // First get the JsonPath object instance from the Response interface
        JsonPath jsonPathEvaluator = response.jsonPath();

        // Let us print the city variable to see what we got
        System.out.println("City received from Response " + jsonPathEvaluator.get("City"));

        // Print the temperature node
        System.out.println("Temperature received from Response " + jsonPathEvaluator.get("Temperature"));

        // Print the humidity node
        System.out.println("Humidity received from Response " + jsonPathEvaluator.get("Humidity"));

        // Print weather description
        System.out.println("Weather description received from Response " + jsonPathEvaluator.get("Weather"));

        // Print Wind Speed
        System.out.println("City received from Response " + jsonPathEvaluator.get("WindSpeed"));

        // Print Wind Direction Degree
        System.out.println("City received from Response " + jsonPathEvaluator.get("WindDirectionDegree"));
    }
}
