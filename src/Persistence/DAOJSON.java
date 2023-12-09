package Persistence;

import com.google.gson.*;

import java.io.*;
import java.net.URL;

public abstract class DAOJSON {
    protected String path;
    protected URL resource;

    //Nuevo
    public void checkIfFileExists() throws FileNotFoundException {
        resource = ClassLoader.getSystemClassLoader().getResource(path);

        if (resource == null) {
            throw new FileNotFoundException("File " + path + " not exists.");
        }
        File file = new File(resource.getPath());
        if (!file.exists()) {
            throw new FileNotFoundException("File " + path + " not exists.");
        }
    }

    //Nuevo
    public void createFile() throws IOException {
        try (FileWriter fileWriter = new FileWriter("data/" + path)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            fileWriter.write(gson.toJson(new JsonArray()));
        }
    }

    //Nuevo
    protected JsonArray readAllFromFile() {
        resource = ClassLoader.getSystemClassLoader().getResource(path);
        assert resource != null;
        try {
            FileReader fr = new FileReader(resource.getFile());
            JsonElement element = JsonParser.parseReader(fr);
            if (element.isJsonNull())
                return new JsonArray();
            return element.getAsJsonArray();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    //Nuevo
    protected boolean saveToFile(JsonArray products) {
        try (FileWriter fileWriter = new FileWriter(resource.getFile())) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            fileWriter.write(gson.toJson(products));
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
