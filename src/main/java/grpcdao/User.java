package grpcdao;

import dbConnector.DBConnector;
import grpc.UserDAOServiceGrpc;
import io.grpc.stub.StreamObserver;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class User extends UserDAOServiceGrpc.UserDAOServiceImplBase {

    private Connection conn;

    public User() {
        this.conn = DBConnector.getCustomerConnection();
    }

    public void createUser(grpc.User.CreateUserRequest request,
                           StreamObserver<grpc.User.CreateUserResponse> responseObserver) {

        Statement statement;
        grpc.User.GetUserResponse user = getUser(request.getUserName());
        if(user ==null) {
            try {
                String query = String.format("insert into account (user_id,password) values ('%s','%s');", request.getUserName(), request.getPassword());
                statement = conn.createStatement();
                statement.executeUpdate(query);
                user = getUser(request.getUserName());
                System.out.println("Row Inserted row" + user.getUserName() + " " + user.getPassword());
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        responseObserver.onNext(grpc.User.CreateUserResponse.newBuilder().setUserId(user.getId()).build());
        responseObserver.onCompleted();
    }

    public void getUser(grpc.User.GetUserRequest request,
                        StreamObserver<grpc.User.GetUserResponse> responseObserver) {
        responseObserver.onNext(getUser(request.getUserName()));
        responseObserver.onCompleted();
    }

    public void getAccount(grpc.User.GetAccountRequest request,
                           io.grpc.stub.StreamObserver<grpc.User.GetUserResponse> responseObserver) {
        Statement statement;
        grpc.User.GetUserResponse user = null;
        try {
            String query=String.format("select * from account where user_id= '%s' and password = '%s' ",request.getUserName(), request.getPassword());
            statement=conn.createStatement();
            ResultSet res =statement.executeQuery(query);
            if(res.next()) {
                user = grpc.User.GetUserResponse.newBuilder().setUserName(res.getString("user_id")).setPassword(res.getString("password")).setId(res.getInt("id")).build();
            }
        }catch (Exception e){
            System.out.println(e);
        }
        responseObserver.onNext(user);
        responseObserver.onCompleted();
    }

    public grpc.User.GetUserResponse getUser(String userName){
        Statement statement;
        grpc.User.GetUserResponse user = null;
        try {
            String query=String.format("select * from account where user_id= '%s' ",userName);
            statement=conn.createStatement();
            ResultSet res =statement.executeQuery(query);
            while(res.next()) {
                user = grpc.User.GetUserResponse.newBuilder().setUserName(res.getString("user_id")).setPassword(res.getString("password")).setId(res.getInt("id")).build();
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return user;
    }

}