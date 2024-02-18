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

public abstract class DAOJSON {
    protected String path = "data/";

    public DAOJSON() {
    }

    protected void checkIfFileExists() throws FileNotFoundException {
        File file = new File(this.path);
        if (!file.exists()) {
            throw new FileNotFoundException("File " + this.path + " not exists.");
        }
    }

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

    protected JsonArray readAllFromFile() throws PersistenceJsonException {
        try {
            FileReader fr = new FileReader(this.path);
            JsonElement element = JsonParser.parseReader(fr);
            return element.isJsonNull() ? new JsonArray() : element.getAsJsonArray();
        } catch (FileNotFoundException var3) {
            throw new ReadJsonException(this.path, var3);
        }
    }

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
