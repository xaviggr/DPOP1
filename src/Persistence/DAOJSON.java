package Persistence;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

public abstract class DAOJSON {
    protected String path;
    protected FileReader fileReader;

    public DAOJSON() {
        this.path = null;
    }

    public void checkIfFileExists() throws FileNotFoundException {
        URL resource = ClassLoader.getSystemClassLoader().getResource(path);

        if (resource == null) {
            throw new FileNotFoundException("El archivo no se encuentra: " + path);
        }

        fileReader = new FileReader(resource.getFile());
    }
}
