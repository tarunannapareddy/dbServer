import grpcdao.*;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class GrpcServer {
    public static void main(String[] args) throws IOException, InterruptedException {
        Server server = ServerBuilder
                .forPort(8999)
                .addService(new Buyer())
                .addService(new Cart())
                .addService(new Feedback())
                .addService(new Item())
                .addService(new Seller())
                .addService(new User()).build();

        server.start();
        server.awaitTermination();
    }
}