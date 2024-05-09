`mvn install`

`docker build -t postgres_image .`

`docker run -d -p 5433:5432 --name postgres_container postgres_image`