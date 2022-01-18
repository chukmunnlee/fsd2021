package mywebserver;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import static mywebserver.Constants.*;

public class HttpClientConnection implements Runnable {

    private final Socket client;
    private final List<Path> docRoots;

    public HttpClientConnection(Socket client, List<Path> docRoots) {
        this.client = client;
        this.docRoots = docRoots;
    }

    @Override
    public void run() {
        try (InputStream is = client.getInputStream()) {
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);

            String req = br.readLine();

            System.out.printf(">> %s\n", req);

            String[] terms = req.split(" ");
            String method = terms[0];
            String file = terms[1];
            if (ROOT.equals(file))
                file = INDEX_HTML;

            OutputStream os = client.getOutputStream();
            BufferedOutputStream bos = new BufferedOutputStream(os);

            HttpWriter writer = new HttpWriter(bos);

            if (!GET_METHOD.equals(method)) {
                writer.writeString("HTTP/1.1 405 Method Not Allowed");
                writer.writeString();
                writer.writeString("%s method is not supported\r\n".formatted(method));

            } else {
                Optional<File> opt = getFile(file, docRoots);
                if (opt.isPresent()) {
                    File f = opt.get();

                    writer.writeString("HTTP/1.1 200 OK");

                    if (file.endsWith(IMAGE_PNG)) 
                        writer.writeString("Content-Type: image/png");

                    writer.writeString();

                    FileInputStream fis = new FileInputStream(f);
                    BufferedInputStream bis = new BufferedInputStream(fis);
                    byte[] buffer = new byte[1024];
                    int size = 0;

                    while (-1 != (size = bis.read(buffer, 0, buffer.length)))
                        writer.writeBytes(buffer, 0, size);

                } else {
                    writer.writeString("HTTP/1.1 404 Not Found");
                    writer.writeString();
                    writer.writeString("%s not found\r\n".formatted(file.substring(1)));
                }
            }

            writer.close();
            os.close();

        } catch (Exception ex) {
            try { client.close(); } catch (Exception ex2) { }
        }
    }

    private Optional<File> getFile(String resource, List<Path> docRoots) {
        for (Path p: docRoots) {
            File f = Paths.get(p.toString(), resource).toFile();
            if (f.exists() && f.isFile())
                return Optional.of(f);
        }
        return Optional.empty();
    }
}
