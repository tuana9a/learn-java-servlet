# info

my learning java serlvet

# how to run

take example files and remove `.example` from it

```shell
# package
mvn package
```

```shell
# run
PORT=8080
java -jar target/dependency/webapp-runner.jar --port $PORT target/*.war
```