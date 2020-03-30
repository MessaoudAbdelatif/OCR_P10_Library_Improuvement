# **OCR_P10_Library_Improvement**
This is an update version 2.0.0 of initial project Public Service Library P7 v1.0.0

### **This solution contain 6 standalone Spring Boot MicroServices Restful :**

Architecture Solution closed to change and open to update.
Easy to maintain and deploy springboot provide embedded tomcat server.

#### _SpringBoot Cloud Edge MicroServices :_

##### **Config Server :**
It provide all environmental variables and configuration setup, this data are stored and persisted in separate github repository https://github.com/MessaoudAbdelatif/Library-config-repository

##### **Eureka Discovery :**
All the MS will be subscribing to Eureka MS, this will allow our gateway to handle the load increase secure and manage the entire solution.

##### **Zuul Gateway :** 
Handle all entries connexions at port 9004, dispatch using load balancer Ribbon solution from Netflix.
Secure user account profile using JWT combine with Spring Security.
provide each user a JWT stored in a secured cookie and http only.

#### _SpringBoot Functional & Logic Solution :_

##### **Library Back API :**
Provide all the logic & business implementation of the functional specifications this MS is N’Tier Modules Maven:

_Library-api-business
Library-api-dao
Library-api-model
Library-api-service_

##### **Library Webapp :**
Provide the web application views using html template generator Thyemleaf.
Consume the exposed end point from the back api.

##### **Library Batch :** 
Managed the e-mail reminder sender, user borrows delay, user booking notify.
Implementing Scheduler auto checking back Api.

## Deployment :

A dataset dump is provided with the project, it is located in the assets folder under the file name: db_library.sql 

Importante 1st run config server it contain environment variables for the others MS.

     java -jar target/config-server-2.0.0-SNAPSHOT.jar

when it’s lunch,  you can run:

    java -jar target/eureka-server-2.0.0-SNAPSHOT.jar

    java -jar target/library-api-server-2.0.0-SNAPSHOT.jar
    java -jar target/library-batch-2.0.0-SNAPSHOT.jar
    java -jar target/library-webapp-2.0.0-SNAPSHOT.jar
    java -jar target/zuul-server-2.0.0-SNAPSHOT.jar
    
    
Open Browser : localhost:9004/index 

Enjoy 😎
