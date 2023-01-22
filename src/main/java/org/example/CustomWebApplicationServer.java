package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class CustomWebApplicationServer
{
    private final int port;

    private static final Logger logger = LoggerFactory.getLogger(CustomWebApplicationServer.class);

    public CustomWebApplicationServer(int port)
    {
        this.port = port;
    }

    public void start() throws IOException
    {
        try(ServerSocket serverSocket = new ServerSocket(port))
        {
            logger.info("[CustomerWebApplicationServer] started {} port.", port);

            Socket clientSocket;
            logger.info("[CustomerWebApplicationServer] waiting for client.");

            while ((clientSocket = serverSocket.accept()) != null)
            {
                logger.info("[CustomerWebApplicationServer] client connected!");

                /**
                 * Step1 - 사용자 요청을 메인 Thread 가 처리하도록 한다.
                 */

                try (InputStream inputStream = clientSocket.getInputStream(); OutputStream outputStream = clientSocket.getOutputStream())
                {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
                    DataOutputStream dataOutputStream = new DataOutputStream(outputStream);

                    String line;

                    while ((line = bufferedReader.readLine()) != "")
                    {
                        System.out.println(line);
                    }
                }
            }
        }

    }
}
