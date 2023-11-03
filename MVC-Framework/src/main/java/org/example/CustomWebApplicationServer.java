package org.example;

import org.example.calculatorWebApplication.ClientRequestHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CustomWebApplicationServer
{
    private final int port;

    private final ExecutorService executorService = Executors.newFixedThreadPool(10);

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
                 * Step2 - 사용자 요청이 들어올 때마다 Thread 를 새로 생성해서 사용자 요청을 처리한다.
                 */
                executorService.execute(new ClientRequestHandler(clientSocket)); // Thread Pool 적용
//                new Thread(new ClientRequestHandler(clientSocket)).start();

                /**
                 * Step1 - 사용자 요청을 메인 Thread 가 처리하도록 한다.
                 */

//                try (InputStream inputStream = clientSocket.getInputStream(); OutputStream outputStream = clientSocket.getOutputStream())
//                {
//                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
//                    DataOutputStream dataOutputStream = new DataOutputStream(outputStream);

//                    String line;
//
//                    while ((line = bufferedReader.readLine()) != "")
//                    {
//                        System.out.println(line);
//                    }

//                    HttpRequest httpRequest = new HttpRequest(bufferedReader);
//
//                    // GET /calculate?operand1=11&operator=*&operand2=55 HTTP/1.1
//                    if (httpRequest.isGetRequest() && httpRequest.matchPath("/calculate"))
//                    {
//                        QueryStrings queryStrings = httpRequest.getQueryStrings();
//
//                        int operand1 = Integer.parseInt(queryStrings.getValue("operand1"));
//                        String operator = queryStrings.getValue("operator");
//                        int operand2 = Integer.parseInt(queryStrings.getValue("operand2"));
//
//                        int result = Calculator.calculate(new PositiveNumber(operand1), operator, new PositiveNumber(operand2));
//                        byte[] body = String.valueOf(result).getBytes();
//
//                        HttpResponse response = new HttpResponse(dataOutputStream);
//                        response.response200Header("application/json", body.length);
//                        response.responseBody(body);
//                    }
//                }
            }
        }
    }
}
