/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.eci.escuelaing.arem.namesncs;

import java.net.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Base64.Encoder;

import javax.imageio.ImageIO;

import java.io.*;

//Hacer que el servidor acepte mas de una peticion -> Hecho
//Hacer que funcione el script de la primera tarea
public class HttpServer {

    public static final String HTML_HEADER = "HTTP/1.1 200 OK\r\n"
            + "Content-Type: text/html\r\n"
            + "\r\n";
    public static final String JS_HEADER = "HTTP/1.1 200 OK\r\n"
            + "Content-Type: text/javascript\r\n"
            + "\r\n";
    public static final String IMG_HEADER = "HTTP/1.1 200 OK\r\n"
            + "Content-Type: image/jpeg\r\n";
    public static final String CSS_HEADER = "HTTP/1.1 200 OK\r\n"
            + "Content-Type: text/css\r\n"
            + "\r\n";
    public static final String ERROR_HEADER = "HTTP/1.1 400 Bad Request\r\n";
    

    public static void main(String[] args) throws IOException {
        // System.out.println(readFile("src/main/java/edu/eci/escuelaing/arem/namesncs/ArchivoPrueba.html"));
        // readImage("C:/Users/Cristian/Desktop/Tarea2ClientesServiciosArep/src/main/resources/img/ImagenOculta.jpg");
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(getPort());
        } catch (IOException e) {
            System.err.println("Could not listen on port: "+ serverSocket.getLocalPort());
            System.exit(1);
        }
        boolean running = true;
        while (running) {
            Socket clientSocket = null;
            try {
                System.out.println("Listo para recibir ...");
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
            }
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            clientSocket.getInputStream()));
            String firstLine = in.readLine();
            System.out.println("FirstLine:" + firstLine);
            String[] line = firstLine.split(" ");
            System.out.println("Line  : "+ line[1]);
            try{
                getFile(clientSocket, line[1]); 
            }catch(IOException e){
                out.println(ERROR_HEADER);
            }
            out.close();
            in.close();
            clientSocket.close();
        }
        serverSocket.close();
    }

    /**
     * Lee el archivo dado, y lo entrega en una pagina
     * 
     * @param Path Direccion del archivo a leer
     * @return Documento leido en un string
     * @throws IOException
     */
    public static void readFile(Socket client, String path, String extension) throws IOException {
        System.out.println("Leyendo Archivo");
        String[] header = getHeader(extension);
        System.out.println(header[0]);
        path = header[1] + path; 
        PrintWriter out = new PrintWriter(client.getOutputStream(), true);
        if(header[0].equals(ERROR_HEADER)){
            out.println(header[1]);
        }else{
            Path file = Paths.get(path);
            StringBuffer output = new StringBuffer();
            output.append(header[0]);
            InputStream in = Files.newInputStream(file);
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line = null;
            while ((line = reader.readLine()) != null) {
                output.append(line + "\n");
            }
            out.println(output);
        }
    }

    public static String[] getHeader(String extension){
        if(extension.equals("html")){
            return new String[]{HTML_HEADER,"src/main/resources/public"};
        }else if(extension.equals("css")){
            return new String[]{CSS_HEADER,"src/main/resources/public/css"};
        }else if(extension.equals("js")){
            return new String[]{JS_HEADER,"src/main/resources/public/js"};
        }else{
            return new String[]{ERROR_HEADER,""};
        }
    }

    public static void getFile(Socket client, String path) throws IOException{
        System.out.println("Path: "+ path+" "+ path.split("\\.") + path.substring(1));
        String extension = "";
        extension = (path.contains(".")) ? path.split("\\.")[1]:"";
        System.out.println("Extension:"+ extension);
        if(extension.equals("png")){
            readImage(path, client.getOutputStream());
        }else{
            readFile(client, path, extension);
        }
    }

    public static void readImage(String path, OutputStream outS) throws IOException{
        System.out.println("Leyendo imagen");
        path = "src/main/resources/img" + path; 
        System.out.println("ImgPath:"+path);
        File file = new File(path);
        FileInputStream fis = new FileInputStream(file);
        byte[] data = new byte[(int) file.length()];
        fis.read(data);
        fis.close();
        DataOutputStream binaryOut = new DataOutputStream(outS);
        binaryOut.writeBytes("HTTP/1.0 200 OK\r\n");
        binaryOut.writeBytes("Content-Type: image/png\r\n");
        binaryOut.writeBytes("Content-Length: " + data.length);
        binaryOut.writeBytes("\r\n\r\n");
        binaryOut.write(data);
        binaryOut.close();
    }

    static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 35000; //returns default port if heroku-port isn't set
    }

    public static String getForm() {
        return HTML_HEADER
                + "<!DOCTYPE html>\n"
                + "<html>\n"
                + "    <head>\n"
                + "        <title>Form Example</title>\n"
                + "        <meta charset=\"UTF-8\">\n"
                + "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                + "    </head>\n"
                + "    <body>\n"
                + "        <h1>Form with GET</h1>\n"
                + "        <form action=\"/hello\">\n"
                + "            <label for=\"name\">Name:</label><br>\n"
                + "            <input type=\"text\" id=\"name\" name=\"name\" value=\"John\"><br><br>\n"
                + "            <input type=\"button\" value=\"Submit\" onclick=\"loadGetMsg()\">\n"
                + "        </form> \n"
                + "        <div id=\"getrespmsg\"></div>\n"
                + "\n"
                + "        <script>\n"
                + "            function loadGetMsg() {\n"
                + "                let nameVar = document.getElementById(\"name\").value;\n"
                + "                const xhttp = new XMLHttpRequest();\n"
                + "                xhttp.onload = function() {\n"
                + "                    document.getElementById(\"getrespmsg\").innerHTML =\n"
                + "                    this.responseText;\n"
                + "                }\n"
                + "                xhttp.open(\"GET\", \"/hello?name=\"+nameVar);\n"
                + "                xhttp.send();\n"
                + "            }\n"
                + "        </script>\n"
                + "\n"
                + "        <h1>Form with POST</h1>\n"
                + "        <form action=\"/hellopost\">\n"
                + "            <label for=\"postname\">Name:</label><br>\n"
                + "            <input type=\"text\" id=\"postname\" name=\"name\" value=\"John\"><br><br>\n"
                + "            <input type=\"button\" value=\"Submit\" onclick=\"loadPostMsg(postname)\">\n"
                + "        </form>\n"
                + "        \n"
                + "        <div id=\"postrespmsg\"></div>\n"
                + "        \n"
                + "        <script>\n"
                + "            function loadPostMsg(name){\n"
                + "                let url = \"/hellopost?name=\" + name.value;\n"
                + "\n"
                + "                fetch (url, {method: 'POST'})\n"
                + "                    .then(x => x.text())\n"
                + "                    .then(y => document.getElementById(\"postrespmsg\").innerHTML = y);\n"
                + "            }\n"
                + "        </script>\n"
                + "    </body>\n"
                + "</html>";
    }
}
