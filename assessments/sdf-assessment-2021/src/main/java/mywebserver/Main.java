package mywebserver;

import static mywebserver.Constants.*;

public final class Main {
    public static void main(String[] args) {
        Integer port = DEFAULT_PORT;
        String[] docRoot = DEFAULT_DOC_ROOT;

        if (args.length > 0) {
            int i = 0;
            while (i < args.length) {
                switch (args[i]) {
                    case OPTION_PORT:
                        port = Integer.parseInt(args[++i]);
                        break;
                    case OPTION_DOC_ROOT:
                        docRoot = args[++i].split(DIR_DELIMITER);
                        break;
                }
                ++i;
            }
        }

        HttpServer server = new HttpServer(docRoot, port);
        server.start();
    }
}
