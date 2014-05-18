package com.gerime.repository;

import java.util.List;

import com.gerime.model.User;

public class UserRepository {
	public static boolean hasLogin() {
		List<User> resul = EntityManager.getSqlAdapter().findAll(User.class);
		return resul.size() > 0;
	}
}
