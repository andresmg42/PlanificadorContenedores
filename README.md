Nombre Integrantes: 

Andres David Ortega-2241885
Jose Daniel Trujillo Suarez-2225611

Este programa esta hecho en NetBeans en un sistema operativo de windows con la version de JAVA 11, para ejecturalo se necesita tener instalado y ejecutandose Docker.
Tambien la conexion con Docker se hace mediante la uri:"tcp://localhost:2375", que general mente es la de windows. En caso de tener otra URI, configurar la varible 
clientConfig de los archivos de el directorio algoritmo y el archivo controladorEjecucion.java de el directorio controlador, por ejemplo Para ejecutar el proyecto 
en un sistema linux se debe configurar los archivos de la carpeta algoritmos y controlador donde se instancia la variable clientConfig en los contructores de las
clases(archivos) mensionadas de  esta manera:
clientConfig = DefaultDockerClientConfig.createDefaultConfigBuilder().withDockerHost("unix:///var/run/docker.sock").build();
Ademas se debe tener libre el puerto localhost:8000 que es por donde se expone el puerto de la base de datos.
