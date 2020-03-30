#**OCR_P10_Library_Improuvement**
This is an update version 2.0.0 of initial project Public Service Library P7 v1.0.0

###### **This solution contain 6 standalone Spring Boot MicroServices Restful :**

Architecture Solution closed to change and open to update.
Easy to maintain and deploy springboot provide embedded tomcat server.

###### _SpringBoot Cloud Edge MicroServices :_

**Config Server :**
It provide all environmental variables and configuration setup, this datas are stored and persisted in séparate github repository https://github.com/MessaoudAbdelatif/Library-config-repository

**Eureka Discovery :**
All the MS will be subscribing to Eureka MS, this will allow our gateway to handle the load increase secure and manage the entire solution.

**Zuul Gateway :** 
Handle all entries connexions at port 9004, dispatch using load balancer Ribbon solution from Netflix.
Secure user account profile using JWT combine with Spring Security.
provide each user a JWT stored in a secured cookie and http only.

###### _SpringBoot Functional & Logic Solution :_

**Library Back API :**
Provide all the logic & business implementation of the fonctionnel spécifications this MS is N’Tier Modules Maven:

_Library-api-business
Library-api-dao
Library-api-model
Library-api-service_

**Library Webapp :**
Provide the web application views using html template generator Thyemleaf.
Consume the exposed end point from the back api.

**Library Batch :** 
Managed the e-mail reminder sender, user borrows delay, user booking notify.
Implementing Scheduler auto checking back Api.

