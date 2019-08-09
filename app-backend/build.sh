./mvnw compile
./mvnw -Dmaven.test.skip=true package
docker build -t app-backend .
