databaseChangeLog = {

	changeSet(author: "jacob (generated)", id: "1427034488544-1") {
		createTable(tableName: "CRISIS") {
			column(autoIncrement: "true", name: "ID", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "CONSTRAINT_7")
			}

			column(name: "VERSION", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "DESCRIPTION", type: "VARCHAR(255)")

			column(name: "HEADER", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "jacob (generated)", id: "1427034488544-2") {
		createTable(tableName: "PERSON") {
			column(autoIncrement: "true", name: "ID", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "CONSTRAINT_8")
			}

			column(name: "VERSION", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "HOME_SHIP_ID", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "NAME", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "jacob (generated)", id: "1427034488544-3") {
		createTable(tableName: "PERSON_SOLVED_CRISIS") {
			column(name: "CRISIS_ID", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "PERSON_ID", type: "BIGINT") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "jacob (generated)", id: "1427034488544-4") {
		createTable(tableName: "SHIP") {
			column(autoIncrement: "true", name: "ID", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "CONSTRAINT_2")
			}

			column(name: "VERSION", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "CREWSIZE", type: "INT") {
				constraints(nullable: "false")
			}

			column(name: "DESCRIPTION", type: "VARCHAR(255)")

			column(name: "NAME", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "PRODUCTION_DATE", type: "TIMESTAMP") {
				constraints(nullable: "false")
			}

			column(name: "SHIPTYPE", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "jacob (generated)", id: "1427034488544-5") {
		createTable(tableName: "SHIP_AFFECTED_BY") {
			column(name: "SHIP_ID", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "CRISIS_ID", type: "BIGINT") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "jacob (generated)", id: "1427034488544-6") {
		addPrimaryKey(columnNames: "PERSON_ID, CRISIS_ID", constraintName: "CONSTRAINT_5", tableName: "PERSON_SOLVED_CRISIS")
	}

	changeSet(author: "jacob (generated)", id: "1427034488544-7") {
		addPrimaryKey(columnNames: "SHIP_ID, CRISIS_ID", constraintName: "CONSTRAINT_C", tableName: "SHIP_AFFECTED_BY")
	}

	changeSet(author: "jacob (generated)", id: "1427034488544-13") {
		createIndex(indexName: "UK_S1M9G2DAF9L5POIAS03R67VB_INDEX_2", tableName: "SHIP", unique: "true") {
			column(name: "NAME")
		}
	}

	changeSet(author: "jacob (generated)", id: "1427034488544-8") {
		addForeignKeyConstraint(baseColumnNames: "HOME_SHIP_ID", baseTableName: "PERSON", baseTableSchemaName: "PUBLIC", constraintName: "FK_FX9WYIBISA2OAQJCHK6EGBXVD", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "SHIP", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "jacob (generated)", id: "1427034488544-9") {
		addForeignKeyConstraint(baseColumnNames: "CRISIS_ID", baseTableName: "PERSON_SOLVED_CRISIS", baseTableSchemaName: "PUBLIC", constraintName: "FK_C7BP7W8ICNO2RE6MQ9JQFFO72", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "CRISIS", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "jacob (generated)", id: "1427034488544-10") {
		addForeignKeyConstraint(baseColumnNames: "PERSON_ID", baseTableName: "PERSON_SOLVED_CRISIS", baseTableSchemaName: "PUBLIC", constraintName: "FK_E5QY8E7T7PSDG34PPMR56BMK9", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "PERSON", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "jacob (generated)", id: "1427034488544-11") {
		addForeignKeyConstraint(baseColumnNames: "CRISIS_ID", baseTableName: "SHIP_AFFECTED_BY", baseTableSchemaName: "PUBLIC", constraintName: "FK_HA00GJTK4W9951FHV1B48N8WP", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "CRISIS", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}

	changeSet(author: "jacob (generated)", id: "1427034488544-12") {
		addForeignKeyConstraint(baseColumnNames: "SHIP_ID", baseTableName: "SHIP_AFFECTED_BY", baseTableSchemaName: "PUBLIC", constraintName: "FK_NS3N4XV3F6GX0HK3B35S16G17", deferrable: "false", initiallyDeferred: "false", onDelete: "RESTRICT", onUpdate: "RESTRICT", referencedColumnNames: "ID", referencedTableName: "SHIP", referencedTableSchemaName: "PUBLIC", referencesUniqueColumn: "false")
	}
}
