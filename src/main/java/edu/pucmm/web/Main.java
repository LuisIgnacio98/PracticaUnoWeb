package edu.pucmm.web;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Document documento = null;
        String body = null;
        int cantP = 0;
        int cantI = 0;
        int inputIndex = 0;


        String URL = "https://www.vogella.com/";


        try {
            //Aqui se hace la conexion con JSOUP y le paso la URL
            documento = Jsoup.connect(URL).get();
            //Imprimir Titulo de la URL
            if(documento.title() == ""){
                System.out.println("**NO HAY TITULO**");
            }else {
                System.out.println("Titulo del Documento:\n" + documento.title());
            }

            //Aqui se guarda todo el etiquedado de la pagina
            body = Jsoup.connect(URL).execute().body();

        }catch (IOException e){
            e.printStackTrace();
        }

        System.out.println("------------------------------------\n");

        // Contar las lineas
        String[] cantidadLineas;
        //Split para recortar cada linea y almacenar en array
        cantidadLineas = body.split("\n");
        System.out.println(cantidadLineas.length + " Lineas en total");


        //Contar párrafos
        for (Element parrafo : documento.select("p")){
            cantP++;
        }
        if(cantP == 0){
            System.out.println("**No hay párrafos en este documento**");
        }else{
            System.out.println(cantP + " Párrafos en total");
        }


        //Contar imagenes
        for (Element imagen : documento.select("p > img ")){
            cantI++;
        }
        System.out.println(cantI + " Imagenes en total");

        // Cantidad de formularios
        Elements inputsForm = documento.select("form");
        Elements formsGet = documento.select("form[method=GET]");
        Elements formsPost = documento.select("form[method=POST]");
        int cantidadFormulariosGet = 0;
        int cantidadFormulariosPost = formsPost.size();
        for(Element form : inputsForm) {
            //Se verifica si tiene el atributo method
            if(!form.hasAttr("method")) {
                cantidadFormulariosGet++;
            }
        }
        System.out.println(cantidadFormulariosGet + " Formularios GET");
        System.out.println(cantidadFormulariosPost + " Formularios POST");


       // Tipos de input en el form
        if(inputsForm.size() != 0){
            for(Element form : inputsForm){
                for(Element input: form.getElementsByTag("input")){
                    inputIndex++;
                    System.out.println("Input# " + inputIndex + " del tipo " + input.attr("type").toLowerCase());
                }
            }
        }else System.out.println("**Al parecer no hay formularios**");


    }
}
