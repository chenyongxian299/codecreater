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
SELECT GROUP_CONCAT(`COLUMN`.COLUMN_NAME) AS column_str FROM information_schema.`COLUMN` WHERE table_name = #tableName#
