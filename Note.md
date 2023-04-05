### Docker compose note
- @links tag:
  - Link containers: links allow you to define extra aliases by which a service is reachable from another service.
  - They are not required to enable services to communicate. By default, any service can reach any other service at that service's name.
  - In the following example, db is reachable from web at the hostnames db and database.
  ```yaml
     services:
      web:
        build: .
        links:
          - "db:database"
      db:
        image: postgres
  ```