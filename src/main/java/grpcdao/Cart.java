package grpcdao;

import dbConnector.DBConnector;
import grpc.CartServiceGrpc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import io.grpc.stub.StreamObserver;

public class Cart extends CartServiceGrpc.CartServiceImplBase {
    private final Connection conn;

    public Cart() {
        this.conn = DBConnector.getProductConnection();
    }

    private int getCart(int buyerId) {
        String query = "SELECT * FROM cart WHERE buyer_id = ?";
        try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setInt(1, buyerId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("id");
                } else {
                    return createCart(buyerId);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error while getting cart: " + e.getMessage());
            return -1;
        }
    }

    private int createCart(int buyerId) {
        String query = "INSERT INTO cart (buyer_id) VALUES (?)";
        try (PreparedStatement preparedStatement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, buyerId);
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1); // Return the generated ID
                }
            }
        } catch (SQLException e) {
            System.out.println("Error while creating cart: " + e.getMessage());
        }
        return -1;
    }

    private boolean updateCartQuantity(int cartId, String itemId, int quantityChange) {
        try {
            String updateQuery = "UPDATE cart_item SET quantity = quantity + ? WHERE cart_id = ? AND item_id = ?";
            String insertQuery = "INSERT INTO cart_item (cart_id, item_id, quantity) VALUES (?, ?, ?)";
            try (PreparedStatement updateStatement = conn.prepareStatement(updateQuery);
                 PreparedStatement insertStatement = conn.prepareStatement(insertQuery)) {

                updateStatement.setInt(1, quantityChange);
                updateStatement.setInt(2, cartId);
                updateStatement.setString(3, itemId);

                int updatedRows = updateStatement.executeUpdate();
                if (updatedRows > 0) {
                    return true;
                } else if (quantityChange > 0) {
                    insertStatement.setInt(1, cartId);
                    insertStatement.setString(2, itemId);
                    insertStatement.setInt(3, quantityChange);

                    int insertedRows = insertStatement.executeUpdate();
                    return insertedRows > 0;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error while updating cart quantity: " + e.getMessage());
        }
        return false;
    }

    private boolean deleteCart(int cartId) {
        try {
            String deleteQuery = "DELETE FROM cart_item WHERE cart_id = ?";
            try (PreparedStatement deleteStatement = conn.prepareStatement(deleteQuery)) {
                deleteStatement.setInt(1, cartId);
                int rowsAffected = deleteStatement.executeUpdate();
                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            System.out.println("Error while deleting cart: " + e.getMessage());
        }
        return false;
    }

    @Override
    public void getCart(grpc.Cart.GetCartRequest request,
                        StreamObserver<grpc.Cart.GetCartResponse> responseObserver) {
        int cartId = getCart(request.getBuyerId());
        responseObserver.onNext(grpc.Cart.GetCartResponse.newBuilder().setCartId(cartId).build());
        responseObserver.onCompleted();
    }

    @Override
    public void updateCartQuantity(grpc.Cart.UpdateCartQuantityRequest request,
                                   StreamObserver<grpc.Cart.UpdateCartQuantityResponse> responseObserver) {
        boolean success = updateCartQuantity(request.getCartId(), request.getItemId(), request.getQuantityChange());
        responseObserver.onNext(grpc.Cart.UpdateCartQuantityResponse.newBuilder().setSuccess(success).build());
        responseObserver.onCompleted();
    }

    @Override
    public void deleteCart(grpc.Cart.DeleteCartRequest request,
                           StreamObserver<grpc.Cart.DeleteCartResponse> responseObserver) {
        boolean success = deleteCart(request.getCartId());
        responseObserver.onNext(grpc.Cart.DeleteCartResponse.newBuilder().setSuccess(success).build());
        responseObserver.onCompleted();
    }
}
