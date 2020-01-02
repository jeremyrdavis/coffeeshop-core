# Coffeeshop Core

## Dev

docker run docker run -d -p 5432:5432 --name pgdemodb -e POSTGRES_PASSWORD=redhat-19 postgres
docker run --name pgadmin -p 80:80 -e 'PGADMIN_DEFAULT_EMAIL=<<EMAIL_ADDRESS>>' -e 'PGADMIN_DEFAULT_PASSWORD=redhat-19' -d dpage/pgadmin4

## Kafka

Topics

order_updates

## Tests

### Unit Tests

mvn clean test runs the unit tests and uses Mocks to isolate functionality

### Integration tests

mvn clean verify -Ptestcontainers uses the "testcontainers" profile that excludes the Mocks when building the tests

NOTE: unit tests are skipped

Failsafe plugin needs to be configured to exclude the Mocks under testConpile

```
                    <plugin>
                        <groupId>org.apache.maven.plugins</groupId>
                        <artifactId>maven-compiler-plugin</artifactId>
                        <configuration combine.self="override">
                            <excludes>
                                <exclude>**/Mock*.java</exclude>
                            </excludes>
                        </configuration>
                    </plugin>

```