FROM maven:latest

ENV PROJECT_HOME /usr/src/desafiotriagil
ENV JAR_NAME desafiotriagil.jar

RUN mkdir -p $PROJECT_HOME
WORKDIR $PROJECT_HOME

COPY . .

RUN mvn clean package -DskipTests

RUN mv $PROJECT_HOME/target/$JAR_NAME $PROJECT_HOME/

ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=prod", "desafiotriagil.jar"]
