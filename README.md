# PruebaTecnicaTia

Ejecutar script para creacion e insercion de base de datos en SQLServer 
Microsoft SQL Server 2022 (RTM-GDR) (KB5046861) - 16.0.1135.2 (X64) Oct 18 2024 15:31:58 Copyright (C) 2022 Microsoft Corporation Express Edition (64-bit) on Windows 10 Pro 10.0 (Build 22631: ) (Hypervisor)

IDE utilizado para el desarrollo del microservicio: spring-tool-suite-4-4.15.3.RELEASE

Version de Java utilizada 
java version "11.0.16.1" 2022-08-18 LTS Java(TM) SE Runtime Environment 18.9 (build 11.0.16.1+1-LTS-1) Java HotSpot(TM) 64-Bit Server VM 18.9 (build 11.0.16.1+1-LTS-1, mixed mode)

Version de Maven utilizada 
Apache Maven 3.8.6 (84538c9988a25aec085021c365c560670ad80f63) Maven home: C:\apache-maven-3.8.6-bin Java version: 11.0.16.1, vendor: Oracle Corporation, runtime: C:\Program Files\Java\jdk-11.0.16.1 Default locale: es_ES, platform encoding: Cp1252 OS name: "windows 11", version: "10.0", arch: "amd64", family: "windows"

Ejecutar el archivo java lombok.jar (Lombok es una libreria de java que ofrece anotaciones para generar automáticamente código común durante la compilación)

Al ejecutar lombok.jar detectara los IDEs de desarrollo a los cuales se les instalara esta libreria. Click en Install/Update

Importar proyecto Maven en el IDE de desarrollo

En el archivo de propiedades modificar las siguientes que son necesarias para la conexion a base de datos 
spring.datasource.url=jdbc:sqlserver://{direccionIP}:{puerto};databaseName=pruebaDB;TrustServerCertificate=True 
spring.datasource.username={user} 
spring.datasource.password={password}

Click derecho en el proyecto. Run As - Maven Install para generar el .jar

Abrir un cmd y ubicarse en el directorio del proyecto dentro de la carpeta target C:{DirectorioRaiz}\workspace-spring-tool-suite-4-4.15.3.RELEASE\demoVentas\target

Ejecutar el archivo .jar con el siguiente comando: java -jar {NombreDelJar}.jar

Al iniciar el servicio se creara una carpeta en el directorio raiz que tendra un archivo .log (C:/MicroservicioPrueba/Ventas/log)

Los metodos del servicio estan expuestos mediante Swagger. Puede acceder a ellos con la siguiente URL: http://{direccionIP}:8085/demoVentas/swagger-ui.html

El front React + Vite 
Usa node 20.18.2 
Instalar dependencias: 20.18.2 
Levantar aplicacion: npm run start
