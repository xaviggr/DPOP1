package Persistence;

import Persistence.exception.PersistenceJsonException;
import Persistence.exception.ReadJsonException;
import Persistence.exception.WriteJsonException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * La clase abstracta DAOJSON proporciona métodos genéricos para interactuar con archivos JSON
 * en el contexto de un DAO (Data Access Object). Contiene funciones para verificar la existencia
 * de archivos, crear un nuevo archivo JSON, leer todos los datos desde un archivo y guardar datos
 * en un archivo JSON.
 */
@SuppressWarnings("SpellCheckingInspection")
public abstract class DAOJSON {
    protected String path = "data/";

    /**
     * Verifica si el archivo especificado existe.
     *
     * @throws FileNotFoundException Si el archivo no existe.
     */
    protected void checkIfFileExists() throws FileNotFoundException {
        File file = new File(this.path);
        if (!file.exists()) {
            throw new FileNotFoundException("File " + this.path + " not exists.");
        }
    }

    /**
     * Crea un nuevo archivo JSON con una estructura inicial vacía.
     *
     * @throws PersistenceJsonException Si hay un error al intentar escribir en el archivo.
     */
    public void createFile() throws PersistenceJsonException {
        try {
            FileWriter fileWriter = new FileWriter(this.path);

            try {
                Gson gson = (new GsonBuilder()).setPrettyPrinting().create();
                fileWriter.write(gson.toJson(new JsonArray()));
            } catch (Throwable var5) {
                try {
                    fileWriter.close();
                } catch (Throwable var4) {
                    var5.addSuppressed(var4);
                }

                throw var5;
            }

            fileWriter.close();
        } catch (IOException var6) {
            throw new WriteJsonException(this.path, var6);
        }
    }

    /**
     * Lee todos los datos desde el archivo JSON y retorna un objeto JsonArray.
     *
     * @return Un JsonArray que representa los datos leídos desde el archivo.
     * @throws PersistenceJsonException Si hay un error al intentar leer desde el archivo.
     */
    protected JsonArray readAllFromFile() throws PersistenceJsonException {
        try {
            FileReader fr = new FileReader(this.path);
            JsonElement element = JsonParser.parseReader(fr);
            return element.isJsonNull() ? new JsonArray() : element.getAsJsonArray();
        } catch (FileNotFoundException var3) {
            throw new ReadJsonException(this.path, var3);
        }
    }

    /**
     * Guarda un objeto JsonArray en el archivo JSON.
     *
     * @param products Un JsonArray que representa los datos a guardar en el archivo.
     * @throws PersistenceJsonException Si hay un error al intentar escribir en el archivo.
     */
    protected void saveToFile(JsonArray products) throws PersistenceJsonException {
        try {
            FileWriter fileWriter = new FileWriter(this.path);

            try {
                Gson gson = (new GsonBuilder()).setPrettyPrinting().create();
                fileWriter.write(gson.toJson(products));
            } catch (Throwable var6) {
                try {
                    fileWriter.close();
                } catch (Throwable var5) {
                    var6.addSuppressed(var5);
                }

                throw var6;
            }

            fileWriter.close();
        } catch (IOException var7) {
            throw new WriteJsonException(this.path, var7);
        }
    }
}
