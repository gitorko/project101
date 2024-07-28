# Project101

Spring Boot Postgres - Multi-tenancy & Routing

### Version

Check version

```bash
$java --version
openjdk version "21.0.3" 2024-04-16 LTS
```

### Postgres DB

```
docker run -p 5432:5432 --name pg-container -e POSTGRES_PASSWORD=password -d postgres:14
docker ps
docker exec -it pg-container psql -U postgres -W postgres
CREATE USER test WITH PASSWORD 'test@123';
CREATE DATABASE "test-db" WITH OWNER "test" ENCODING UTF8 TEMPLATE template0;
grant all PRIVILEGES ON DATABASE "test-db" to test;
```

Schema 

```bash

CREATE SCHEMA america;
CREATE SCHEMA asia;

select nspname as "Schema"
from pg_catalog.pg_namespace
where nspname not like 'pg_%';
```

```bash
docker stop pg-container
docker start pg-container
```

### Dev

To run the backend in dev mode.

```bash
./gradlew clean build
./gradlew bootRun
```