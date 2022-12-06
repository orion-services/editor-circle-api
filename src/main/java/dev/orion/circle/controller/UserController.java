/**
 * @License
 * Copyright 2022 Orion Services @ https://github.com/orion-services
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package dev.orion.circle.controller;

import javax.annotation.security.PermitAll;
import javax.validation.constraints.NotEmpty;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.faulttolerance.Retry;

import dev.orion.circle.model.User;
import dev.orion.circle.service.UserService;
import dev.orion.circle.service.UserServiceImpl;
import dev.orion.circle.exception.ApplicationException;
import io.smallrye.mutiny.Uni;

/**
 * User API.
 */
@Path("/api/user")
@PermitAll
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
@Produces(MediaType.APPLICATION_JSON)
public class UserController {

    /** Business logic. */
    private UserService userService = new UserServiceImpl();



    @POST
    @Retry(maxRetries = 1, delay = 2000)
    public Uni<User> create(
        @FormParam("name") @NotEmpty final String name,
        @FormParam("password") @NotEmpty final String password) {

        try {
            return userService.createUser(name, password)
                .log()
                .onItem().ifNotNull()
                    .transform(user -> user)
                .onFailure().transform(e -> {
                    throw new ApplicationException(e.getMessage(),
                            Response.Status.BAD_REQUEST);
                    });
        } catch (Exception e) {
            throw new ApplicationException(e.getMessage(),
                Response.Status.BAD_REQUEST);
        }
    }


}
