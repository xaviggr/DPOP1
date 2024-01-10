package Persistence;

import com.google.gson.*;

import java.io.*;
import java.net.URL;

/**
 * Clase abstracta que utiliza Gson para realizar operaciones de lectura y escritura de datos en formato JSON.
 * Esta clase proporciona métodos comunes para gestionar archivos JSON.
 *
 * @see com.google.gson.Gson
 */
public abstract class DAOJSON {
    /**
     * Ruta del archivo JSON en el sistema de archivos o en el classpath.
     */
    protected String path = "data/";

    /**
     * Verifica si el archivo JSON especificado existe en el classpath.
     *
     * @throws FileNotFoundException Si el archivo no existe.
     */
    public void checkIfFileExists() throws FileNotFoundException {
        File file = new File(path);
        if (!file.exists()) {
            throw new FileNotFoundException("File " + path + " not exists.");
        }
    }

    /**
     * Crea un nuevo archivo JSON en el directorio "data/".
     *
     * @throws IOException Si ocurre un error durante la creación del archivo.
     */
    public void createFile() throws IOException {
        try (FileWriter fileWriter = new FileWriter(new File(path))) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            fileWriter.write(gson.toJson(new JsonArray()));
        }
    }

    /**
     * Lee todos los elementos JSON del archivo especificado.
     *
     * @return Un JsonArray que contiene todos los elementos leídos.
     */
    protected JsonArray readAllFromFile() {
        try {
            FileReader fr = new FileReader(path);
            JsonElement element = JsonParser.parseReader(fr);
            if (element.isJsonNull())
                return new JsonArray();
            return element.getAsJsonArray();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Guarda el JsonArray especificado en el archivo JSON.
     *
     * @param products El JsonArray a guardar en el archivo.
     * @return true si la operación fue exitosa, false si hubo algún error.
     */
    protected boolean saveToFile(JsonArray products) {
        try (FileWriter fileWriter = new FileWriter(path)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            fileWriter.write(gson.toJson(products));
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
