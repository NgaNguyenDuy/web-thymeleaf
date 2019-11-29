### Note for create Spring boot application with Thymeleaf
==

- Define variable on pom.xml file inside <properties> pair tag. Access these variable by syntax: ${variable.here}
- For Thymeleaf, have to put template files into src/main/resources/templates
- For Static file like css or js, put in /src/main/resources/static
- To run spring boot application by Maven: `$ mvn spring-boot:run` Default port is 8080.
- To change default port, use the following ways:
    + change property server.port in `application.properties` file or 
    server:
        port: <port-number>
    in `application.yml`
    + Using command line option: `$ java -jar target/web-thymeleaf-1.0-SNAPSHOT.jar --server.port=<port-number>` or
    `$ java -jar -Dserver.port=<port-number> target/web-thymeleaf-1.0.SNAPSHOT.jar`

- @Value("${welcome.username}") will refer to variable welcome.username in `application.properties` or `application.yml`
- @GetMapping("/home") annotation ensures that HTTP GET request to /home are mapped to main() method.
- @RequestParam(name="name", required=false, defaultValue="") bind value of the query String parameter name into the name param of mainWithParam() method.
Query String is not `required`, so if it absent in the request, the default value "" is used.

- To deploy WAR file to Tomcat

**Note**: In Spring Boot, the new final executable JAR file with embedded server solution may not suitable in all production environments, especially the deployment team (a team with good knowledge of server optimization and monitoring skills, but lack of, the development experience), they want full control of the server, and they don’t touch code, so, we need a traditional WAR file.

=> Update the @SpringBootApplication class to extend SpringBootServletInitializer, and override the configure method.

1. 
    ```java
    import org.springframework.boot.builder.SpringApplicationBuilder;
    import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
    ```

2. Extends SpringBootServletInitializer

    `public class StartWebApplication extends SpringBootServletInitializer {}`

3. Override configure method

    ```java
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(StartWebApplication.class);
    }
    ```

4. Update pom.xml
    <package>war</package>

5. Mark the embedded servlet container dependency as being provided
    ```xml
    <dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-tomcat</artifactId>
		<scope>provided</scope>
	</dependency>
    ```

    This final step to ensure that the embedded servlet container does not interface with the servlet container to which the war file is deployed.

- To able to create an executable jar, we need to add the spring-boot-maven-plugin to the pom.xml file.
    ```xml
    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>
    ```
- Create executable JAR file by command: `$ mvn clean package`
- Run jar file: `$ java -jar target/web-thymeleaf-1.0-SNAPSHOT.jar`
