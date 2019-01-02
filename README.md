# Mobile Subscriber rest API

## Overview  


Start your server as an simple java application  

You can view the api documentation in swagger-ui by pointing to  
http://localhost:8080/api/swagger-ui.html  (change localhost to dns or IP of testing server)

Change default port value in application.properties

Used Spring aop for logging

Used mockito and hamcrest for unit and integration testing

In order to be considered of production level application it should have security added. Oauth2 would be ideal. 
Also api should be fronted with a load balancer gateway for resiliency with at least two nodes running. 



