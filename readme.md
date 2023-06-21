# Aplicação Spring Boot e Primefaces

Esta aplicação está configurada para ser executada com os seguintes recursos:

1. Java 1.8;
2. JBoss EAP 7.2;
3. JSF 2.3;
4. Primefaces 10.0.0;
5. Spring Boot 2.7.12.

## Spring Security

Quando for utilizada versão 5.7.0 ou superior do Spring Secutiry, usar o método 
**HttpSecurity.authorizeHttpRequests()**, pois o método **HttpSecurity.authorizeRequests()** estará obsoleto.
