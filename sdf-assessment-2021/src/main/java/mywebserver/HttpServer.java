package mywebserver;

import static mywebserver.Constants.*;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer implements Runnable {

    private final String[] paths;
    private List<Path> docRoot = null;
    private final Integer port;
    private ExecutorService pool;

    public HttpServer() {
        this(DEFAULT_DOC_ROOT, DEFAULT_PORT);
    }

    public HttpServer(String[] docRoot) {
        this(docRoot, DEFAULT_PORT);
    }

    public HttpServer(Integer port) {
        this(DEFAULT_DOC_ROOT, port);
    }

    public HttpServer(String[] docRoot, Integer port) {
        this.paths = docRoot;
        this.port = port;
    }

    public void start() {

        docRoot = new LinkedList<>();
        for (String p: this.paths) {
            Path dir = Paths.get(p);
            File f = dir.toFile();
            if (!(f.exists() || f.isDirectory()) || (!f.canRead())) {
                System.err.printf("PANIC: %s file is either not a directory, does not exists or not readeable\n", f);
                System.exit(1);
            }
            docRoot.add(dir);
        }

        this.pool = Executors.newFixedThreadPool(4);
        this.pool.submit(this);
    }

    @Override
    public void run() {

        System.out.printf("Server started on port %d, serving from %s\n", port, this.docRoot);

        ServerSocket server = null;
        try {
            server = new ServerSocket(port);
            while (true) {
                final Socket client = server.accept();
                this.pool.submit(new HttpClientConnection(client, this.docRoot));
            }
        } catch (Exception ex) {
            System.err.printf("PANIC: Server error: %s\n", ex.getMessage());
            ex.printStackTrace();
            System.exit(1);
        } finally {
            try { server.close(); } catch (Exception ex) { }
        }
    }
}
