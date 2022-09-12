# Tarea2ClientesServiciosArep
## Instalación y Ejecucion Local
Para instalar y ejecutar este preyecto, dirijase a su consola de comandos...   

```sh
  git clone https://github.com/cristian2306/Tarea2ClientesServiciosArep.git
  cd Tarea2ClientesServiciosArep
  mvn clean isntall
  mvn clean package exec:java -Dexec:mainClass="edu.eci.escuelaing.arem.namesncs.HttpServer"
  
```
Cuando el programa este ejecutandose en la línea de comandos debe aparecer el mensaje que le indique que esta esperando un _request_(*Listo para recibir...*).  
Ingrese a su navegador de preferencia en la siguiente direccion [Proyecto](http://localhost:35000).
Una vez dentro vera la siguiente pantalla.  
![ImagenIndex](https://github.com/cristian2306/Tarea2ClientesServiciosArep/blob/master/img/index.png)  
Puede visualizar las imagenes dispuestas oprimiendo los respectivos botones.  
- Imagen Leon  
  ![ImagenLeon](https://github.com/cristian2306/Tarea2ClientesServiciosArep/blob/master/img/Leon.png)    
- Imagen Mario  
  ![ImagenMario](https://github.com/cristian2306/Tarea2ClientesServiciosArep/blob/master/img/Mario.png)    
Como puede observar, se despliegan las dos imagenes, que son cargadas por medio de una peticion HTTP. Al igual que el funcionamiento de los botones, que se encuentra en el archivo _prueba.js_, al igual que el estilo que se encuentra en _style.css_.  

## Ejecucion en Heroku  
Para acceder al programa en heroku, ingrese en el boton que se ecnuentra a continuacion.
[![ProjectDesign](https://www.herokucdn.com/deploy/button.png)](https://desolate-wildwood-11249.herokuapp.com/)  
Como se puede dar cuenta se encuentra la misma pagina que cuando lo ejecutó de manera local.  

## Path de pruebas
- /Mario.png
- /Lion.png
- /ArchivoPrueba.html
- /style.css
- /prueba.js
