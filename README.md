## ConteinersNetoLess
��� 1. ������� ��� ���� ������� jar-������ � ������ Spring Boot ������������. ��� ����� � ���������, � ����� ������ ������� ��������� �������.

��� gradle: ./gradlew clean build (���� ����� Permission denied, ����� ������� ��������� chmod +x ./gradlew).

��� maven: ./mvnw clean package (���� ����� Permission denied, ����� ������� ��������� chmod +x ./mvnw).

��� 2. ������ �� ������� ��� ������ ��� ������ ��������� � dev � prod. ��� �����:

��� ������� ���������� ���� server.port=8080 � ������� � dev � ������� ��������� netology.profile.dev=true � application.properties � �������� ����������:

��� gradle: ./gradlew clean build (���� ����� Permission denied, ����� ������� ��������� chmod +x ./gradlew);

��� maven: ./mvnw clean package (���� ����� Permission denied, ����� ������� ��������� chmod +x ./mvnw);

�������� Dockerfile � ������ �������:

FROM openjdk:8-jdk-alpine
EXPOSE 8080
ADD build/libs/<�������� ������ ������>.jar myapp.jar
ENTRYPOINT ["java","-jar","/myapp.jar"]
���� �� �������� � ������� maven, ����� jar ����� ������ � ����� target, � ���� � gradle � � build/libs, �, ��������������, � ADD ���� ����������� ���� ������ �� ��������, ������� �� ������������.

������ �������� �����, �������� � ����� ������� � ��������� ������� docker build -t devapp .. ��� �� ������� ���� ���������� � ����� � ��������� devapp.
��� 3. ������ ��� ����� ������� ������ ����� �� ����� �� ����������, �� � ������� �����������:

���������� ���� server.port=8081 � ������� � prod � ������� ��������� netology.profile.dev=false � application.properties � �������� ����������, ��� � ���������� ������;
�������� � Dockerfile �������� EXPOSE � 8080 �� 8081;
�������� �����, �������� � ����� ������� � ��������� ������� docker build -t prodapp .. ��� �� ������� ���� ���������� � ����� � ��������� prodapp.
��� 4. �������� ��� �������������� ����:

�������� � ����������� �������:
��� gradle:

testImplementation 'org.testcontainers:junit-jupiter:1.15.1'

��� maven:

<dependency>
    <groupId>org.testcontainers</groupId>
    <artifactId>junit-jupiter</artifactId>
    <version>1.15.1</version>
    <scope>test</scope>
</dependency>
�������� �������� ����� � ���������� src/test:
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DemoApplicationTests {
    @Autowired
    TestRestTemplate restTemplate;

    @BeforeAll
    public static void setUp() {
     
    }

    @Test
    void contextLoads() {
        ResponseEntity<String> forEntity = restTemplate.getForEntity("http://localhost:" + myapp.getMappedPort(8080), String.class);
        System.out.println(forEntity.getBody());
    }

}
����� ��� ���� ������� ��� ����� GenericContainer � ����� ������ � ������ ��� ���� �����, ������� �� ������� �����.
� ������ setUp() ��������� ���������� ����� �������.
�������� ��� ����-����� ��� �������� ������������ ����, ��� ����� ��� ���� ����������. ��� ����� ����������� ������ ������ TestRestTemplate, ������� ����������� � �������. � ������� ���� �������� ������. ����� ������, �� ����� ����� ������� ��� ���������, �������������� ������� getMappedPort, ��� �� ������� �� ������. � ��� �������� ������������ ������ ��������� � ������� ������ assertEquals.