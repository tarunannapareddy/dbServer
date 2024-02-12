package grpcdao;

import dbConnector.DBConnector;
import grpc.SellerOuterClass;
import grpc.SellerServiceGrpc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Seller extends SellerServiceGrpc.SellerServiceImplBase {
    private Connection conn;

    public Seller() {
        this.conn = DBConnector.getCustomerConnection();
    }

    @Override
    public void createSeller(SellerOuterClass.Seller request,
                             io.grpc.stub.StreamObserver<SellerOuterClass.CreateSellerResponse> responseObserver) {
        int rows = -1;
        String query = "INSERT INTO seller (id, name) VALUES (?, ?)";
        try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setInt(1, request.getId());
            preparedStatement.setString(2, request.getName());
            rows = preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        responseObserver.onNext(SellerOuterClass.CreateSellerResponse.newBuilder().setSuccess(rows > 0).build());
        responseObserver.onCompleted();
    }

    @Override
    public void getSellerInfo(SellerOuterClass.GetSellerInfoRequest request,
                              io.grpc.stub.StreamObserver<SellerOuterClass.Seller> responseObserver) {
        SellerOuterClass.Seller sellerInfo = null;
        String query = "SELECT * FROM seller WHERE id = ?";
        try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setInt(1, request.getSellerId());
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                sellerInfo = SellerOuterClass.Seller.newBuilder()
                        .setId(rs.getInt(1))
                        .setName(rs.getString(2))
                        .setPositiveReviewCount(rs.getInt(3))
                        .setNegativeReviewCount(rs.getInt(4))
                        .setTotalSold(rs.getInt(5))
                        .build();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        responseObserver.onNext(sellerInfo);
        responseObserver.onCompleted();
    }

    @Override
    public void updateReviewCounts(SellerOuterClass.UpdateReviewCountsRequest request,
                                   io.grpc.stub.StreamObserver<SellerOuterClass.UpdateReviewCountsResponse> responseObserver) {
        String query;
        if (request.getReview()) {
            query = "UPDATE seller SET pos_rev_count = pos_rev_count + 1 WHERE id = ?";
        } else {
            query = "UPDATE seller SET neg_rev_count = neg_rev_count + 1 WHERE id = ?";
        }
        int rows = -1;
        try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setInt(1, request.getSellerId());
            rows = preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        responseObserver.onNext(SellerOuterClass.UpdateReviewCountsResponse.newBuilder().setSuccess(rows > 0).build());
        responseObserver.onCompleted();
    }

    @Override
    public void updateItemsSold(SellerOuterClass.UpdateItemsSoldRequest request,
                                io.grpc.stub.StreamObserver<SellerOuterClass.UpdateItemsSoldResponse> responseObserver) {
        String query = "UPDATE seller SET total_sold = total_sold + ? WHERE id = ?";
        int rows = -1;
        try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setInt(1, request.getItemsSold());
            preparedStatement.setInt(2, request.getSellerId());
            rows = preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        responseObserver.onNext(SellerOuterClass.UpdateItemsSoldResponse.newBuilder().setSuccess(rows > 0).build());
        responseObserver.onCompleted();
    }
}
