package com.solveapuzzledev.swaggerjava;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import com.wordnik.swagger.annotations.Authorization;
import com.wordnik.swagger.annotations.AuthorizationScope;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import static org.springframework.http.MediaType.*;
import static org.springframework.web.bind.annotation.RequestMethod.*;

@Controller
@RequestMapping(value = "/api/pet", produces = {APPLICATION_JSON_VALUE, APPLICATION_XML_VALUE})
@Api(value = "/pet", description = "Operations about pets")
public class SolveAPuzzleController {

  

  @RequestMapping(value = "/{petId}", method = GET)
  @ApiOperation(
          value = "Find pet by ID", notes = "Returns a pet when ID < 10. ID > 10 or nonintegers will simulate API " +
          "error conditions",
          response = Pet.class,
          authorizations = {
                  @Authorization( type = "apiKey", value = "api_key"),
                  @Authorization(type = "oauth2", value = "petstore_auth", scopes = {
                          @AuthorizationScope(scope = "write:pets", description = ""),
                          @AuthorizationScope(scope = "read:pets", description = "")
                  })})
  @ApiResponses(value = {
          @ApiResponse(code = 400, message = "Invalid ID supplied"),
          @ApiResponse(code = 404, message = "Pet not found")}
  )
  public ResponseEntity<Pet> getPetById(
          @ApiParam(value = "ID of pet that needs to be fetched", allowableValues = "range[1,5]", required = true)
          @PathVariable("petId") String petId)
          throws NotFoundException {
    Pet pet = new Pet();
    
    if (null != pet) {
      return Responses.ok(pet);
    } else {
      throw new NotFoundException(404, "Pet not found");
    }
  }
}
