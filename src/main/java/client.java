import grpc.User;
import grpc.UserDAOServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class client {

    public static void main (String[] args){
        ManagedChannel managedChannel = ManagedChannelBuilder.forAddress("localhost", 8999).usePlaintext().build();
        UserDAOServiceGrpc.UserDAOServiceBlockingStub userDAOServiceBlockingStub = UserDAOServiceGrpc.newBlockingStub(managedChannel);
        User.CreateUserRequest request = User.CreateUserRequest.newBuilder().setUserName("name").setPassword("password").build();
        User.CreateUserResponse createUserResponse = userDAOServiceBlockingStub.createUser(request);
        System.out.println("response"+createUserResponse.getUserId());
    }
}
