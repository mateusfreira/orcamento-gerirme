package com.gerime.repository;

import com.codeslap.persistence.SqlAdapter;

public class EntityManager {
	private static SqlAdapter sqlAdapter;

	public static void createEntityManager(SqlAdapter sqlAdapter) {
		EntityManager.setSqlAdapter(sqlAdapter);
	}

	public static SqlAdapter getSqlAdapter() {
		return sqlAdapter;
	}

	private static void setSqlAdapter(SqlAdapter sqlAdapter) {
		EntityManager.sqlAdapter = sqlAdapter;
	}
}
