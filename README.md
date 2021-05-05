## Демо проект для демонстрации SpringAutoRestDocs и Swagger
Для локального запуска необходимо запустить сборку в корне проекта грэдлом: `gradlew clean build`

После чего можно запустить [мейн класс модуля spring-auto-rest-docs](https://github.com/GrinRus/rest-docs-demo/blob/master/spring-auto-rest-docs/src/main/java/ru/spring/auto/rest/docs/demo/Main.java).

Документация будет доступна по следущим адресам:
- Swagger UI http://localhost:8080/api/swagger-ui/index.html
- Документация сгенеренная из swagger контракта http://localhost:8080/index.html
- Документация сгенеренная из некоторых сниппетов SpringAutoRestDocs http://localhost:8080/docs/index.html