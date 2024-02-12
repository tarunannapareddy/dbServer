package grpcdao;

import dbConnector.DBConnector;
import grpc.BuyerOuterClass;
import grpc.BuyerServiceGrpc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Buyer extends BuyerServiceGrpc.BuyerServiceImplBase {
    private Connection conn;

    public Buyer() {
        this.conn = DBConnector.getCustomerConnection();
    }
    public void createBuyer(grpc.BuyerOuterClass.Buyer request,
                            io.grpc.stub.StreamObserver<grpc.BuyerOuterClass.CreateBuyerResponse> responseObserver) {
        int rows =-1;
        String  query= "insert into buyer (id,name) values (?,?)";
        try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setInt(1, request.getId());
            preparedStatement.setString(2, request.getName());
            try {
                rows =preparedStatement.executeUpdate();
            }catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        responseObserver.onNext(BuyerOuterClass.CreateBuyerResponse.newBuilder().setSuccess(rows>0).build());
        responseObserver.onCompleted();
    }

    public void getBuyerInfo(grpc.BuyerOuterClass.GetBuyerInfoRequest request,
                             io.grpc.stub.StreamObserver<grpc.BuyerOuterClass.Buyer> responseObserver) {
        BuyerOuterClass.Buyer buyerInfo = null;
        String  query= "SELECT * FROM buyer where id="+request.getBuyerId();
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                buyerInfo = BuyerOuterClass.Buyer.newBuilder().setId(rs.getInt(1)).setName(rs.getString(2)).setItemsPurchased(rs.getInt(3)).build();
            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        responseObserver.onNext(buyerInfo);
        responseObserver.onCompleted();
    }

    public void updateNumItems(grpc.BuyerOuterClass.UpdateNumItemsRequest request,
                               io.grpc.stub.StreamObserver<grpc.BuyerOuterClass.UpdateNumItemsResponse> responseObserver) {
        String query = "UPDATE buyer set items_purchased = items_purchased + ? where id = ?";
        int rows =-1;
        try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            try {
                preparedStatement.setInt(1,request.getCount());
                preparedStatement.setInt(2,request.getBuyerId());
                rows =preparedStatement.executeUpdate();
            }catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        responseObserver.onNext(BuyerOuterClass.UpdateNumItemsResponse.newBuilder().setSuccess(rows>0).build());
        responseObserver.onCompleted();
    }

}
