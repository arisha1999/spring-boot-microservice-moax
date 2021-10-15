# Microservices for MOEX
### **Summary:** MOEX Microservices REST API
### **Requirements:** 
+ Language - Java 8
+ Framework - Spring boot
+ Build tool - Maven
+ Submit sources via a public git repository

### **Working features**
Once you run the entire application stack using docker compose, you should be able access the public routes below:
| FEAUTURE | TYPE | ROUTE |
|:---------:|:---------:|:---------:|
| Get last price | GET | http://localhost:8000/instruments/{ticker}/ |
| Get price by date | GET | http://localhost:8000/instruments/{ticker}/ |
| Get history of instrument | GET | http://localhost:8100/instruments/{ticker}/from/{from}/to/{to} |
