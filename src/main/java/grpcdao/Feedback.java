package grpcdao;

import dbConnector.DBConnector;
import grpc.FeedbackServiceGrpc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Feedback extends FeedbackServiceGrpc.FeedbackServiceImplBase {
    private Connection conn;

    public Feedback() {
        this.conn = DBConnector.getProductConnection();
    }

    @Override
    public void saveFeedback(grpc.Feedback.SaveFeedbackRequest request,
                             io.grpc.stub.StreamObserver<grpc.Feedback.SaveFeedbackResponse> responseObserver) {
        boolean success = false;
        try {
            String query = "INSERT INTO feedback (item_id, buyer_id, feedback) VALUES (?, ?, ?)";
            try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
                preparedStatement.setString(1, request.getItemId());
                preparedStatement.setInt(2, request.getBuyerId());
                preparedStatement.setBoolean(3, request.getFeedback());
                int rowsAffected = preparedStatement.executeUpdate();
                success = rowsAffected > 0;
            }
        } catch (SQLException e) {
            System.out.println("Error while saving feedback: " + e.getMessage());
        }
        responseObserver.onNext(grpc.Feedback.SaveFeedbackResponse.newBuilder().setSuccess(success).build());
        responseObserver.onCompleted();
    }

    @Override
    public void checkFeedbackExistence(grpc.Feedback.CheckFeedbackExistenceRequest request,
                                       io.grpc.stub.StreamObserver<grpc.Feedback.CheckFeedbackExistenceResponse> responseObserver) {
        boolean exists = false;
        try {
            String query = "SELECT 1 FROM feedback WHERE buyer_id = ? AND item_id = ? LIMIT 1";
            try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
                preparedStatement.setInt(1, request.getBuyerId());
                preparedStatement.setString(2, request.getItemId());
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    exists = resultSet.next();
                }
            }
        } catch (SQLException e) {
            System.out.println("Error while checking feedback existence: " + e.getMessage());
        }
        responseObserver.onNext(grpc.Feedback.CheckFeedbackExistenceResponse.newBuilder().setExists(exists).build());
        responseObserver.onCompleted();
    }
}
