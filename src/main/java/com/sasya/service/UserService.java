package com.sasya.service;

import com.sasya.business.RegisterImpl;
import com.sasya.dto.SasyaResponse;
import com.sasya.models.Register;
import com.sasya.models.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.PermitAll;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.ResponseBuilder;

import javax.ws.rs.core.Response;


@Path("v1/user")
public class UserService {

    @Autowired
    private RegisterImpl registerImpl;

    @PermitAll
    @Path("/register")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(Register register){

        registerImpl.registerUser(register);
        SasyaResponse sasyaResponse = new SasyaResponse();
        sasyaResponse.setStatus("Success");
        sasyaResponse.setMessage("Registered Succesfully");
        ResponseBuilder builder = Response.status(Response.Status.OK);
        return builder.build();
    }

}
