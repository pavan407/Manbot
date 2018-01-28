package com.rapidprototyping.manbot.repo;

import com.rapidprototyping.manbot.model.user.User;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Pavan C (pavan407)
 */
public interface UserRepository extends CrudRepository<String, User>
{}
