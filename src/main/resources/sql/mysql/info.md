queryAllSchemata
===
SELECT * FROM information_schema.`SCHEMATA`

queryAllTablesBySchemata
===
SELECT * FROM information_schema.`TABLES` WHERE table_schema = #schemaName#

queryAllColumnByTable
===
SELECT * FROM information_schema.`COLUMNS` WHERE table_name = #tableName#

queryColumnStrByTable
===
SELECT GROUP_CONCAT(`COLUMNS`.COLUMN_NAME) AS column_str FROM information_schema.`COLUMNS` `COLUMNS` WHERE table_name = #tableName#
