package circle.service;


/**
 * Copyright 2021 Blockly Service @ https://github.com/orion-services/blockly
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



import javax.ws.rs.FormParam;

import circle.exception.NotFoundException;
import circle.model.User;
import circle.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User userToCreate(@FormParam("name") final String name, @FormParam("hashUser") final String hashUser) {
        final User createUser = new User();
        createUser.setHashUser(hashUser);
        createUser.setName(name);
        userRepository.save(createUser);
        return createUser;
    }

    public User userToFindByHash(@FormParam("hashUser") final String hashUser) {
        final Optional<User> findUser = Optional.ofNullable(userRepository.findByHashUser(hashUser));
        if (findUser.isEmpty()) {
            throw new NotFoundException("User not found");
        }
        return findUser.get();
    }

    public User userToFindByEmail(@FormParam("email") final String email) {
        final Optional<User> findUser = Optional.ofNullable(userRepository.findByEmail(email));
        if (findUser.isEmpty()){
            throw new NotFoundException("Email not found");
        }
        return findUser.get();
    }
}