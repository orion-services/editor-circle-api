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
package dev.orion.circle.service;

import javax.enterprise.context.ApplicationScoped;

import org.apache.commons.codec.digest.DigestUtils;

import dev.orion.circle.model.User;
import dev.orion.circle.repository.Repository;
import dev.orion.circle.repository.UserRepository;
import io.smallrye.mutiny.Uni;

/**
 * Implements the use cases for user entity.
 */
@ApplicationScoped
public class UserServiceImpl implements UserService {

    /** The minimum size of the password required. */
    private static final int SIZE_PASSWORD = 8;

    /** User repository. */
    private final Repository repository = new UserRepository();


    @Override
    public Uni<User> createUser(final String name,
            final String password) {
        Uni<User> user = null;
        if (name.isBlank() || password.isBlank()) {
            throw new IllegalArgumentException(
                "Blank arguments or invalid e-mail");
        } else {
            if (password.length() < SIZE_PASSWORD) {
                throw new IllegalArgumentException(
                        "Password less than eight characters");
            } else {
                user = repository.createUser(name,
                        DigestUtils.sha256Hex(password));
            }
        }
        return user;
    }


}
