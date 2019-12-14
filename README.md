# Coffeeshop Core

## Dev

docker run docker run -d -p 5432:5432 --name pgdemodb -e POSTGRES_PASSWORD=redhat-19 postgres
docker run --name pgadmin -p 80:80 -e 'PGADMIN_DEFAULT_EMAIL=<<EMAIL_ADDRESS>>' -e 'PGADMIN_DEFAULT_PASSWORD=redhat-19' -d dpage/pgadmin4

## Kafka

Topics

order_updates