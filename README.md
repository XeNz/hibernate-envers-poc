# Hibernate Envers audit test

## Start project

Start PostgreSQL database using:
`docker-compose up -d`

Launch Spring boot Application using dev profile

## Remarks

* This project auto generates and applies the DDL. In a real application you will have to seed the DDL yourself.
* `CustomRevisionEntityListener` implementation will need to be changed for actual use
* `AuditRevisionEntity` will probably contain more metadata in a real application
* Be sure to match the Envers version to the Hibernate version you are currently using
* I don't really see a concrete use case for custom listeners yet, unless you would want to not auto register the
  default listeners