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
package dev.orion.circle.repository;

import javax.enterprise.context.ApplicationScoped;

import dev.orion.circle.model.User;
import io.quarkus.hibernate.reactive.panache.Panache;
import io.smallrye.mutiny.Uni;

/**
 * Implements the repository pattern for the user entity.
 */
@ApplicationScoped
public class UserRepositoryImpl implements UserRepository {


    @Override
    public Uni<User> createUser(final String name,
            final String password) {
         return checkName(name)
                            .onItem().ifNotNull()
                                .failWith(new IllegalArgumentException(
                                    "The name already existis"))
                            .onItem().ifNull()
                                .switchTo(() -> persistUser(
                                    name, password));
                }


    private Uni<User> checkName(final String email) {
        return find("name", email).firstResult();
    }


    private Uni<User> persistUser(final String name,
            final String password) {
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        return Panache.<User>withTransaction(user::persist);
    }



}
