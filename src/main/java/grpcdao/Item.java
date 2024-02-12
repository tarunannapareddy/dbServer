package grpcdao;

import dbConnector.DBConnector;
import grpc.ItemOuterClass;
import grpc.ItemServiceGrpc;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Item extends ItemServiceGrpc.ItemServiceImplBase {
    private Connection conn;

    public Item() {
        this.conn = DBConnector.getProductConnection();
    }

    @Override
    public void addItem(ItemOuterClass.Item request,
                        io.grpc.stub.StreamObserver<ItemOuterClass.AddItemResponse> responseObserver) {
        String itemId = "";
        String insertQuery = "INSERT INTO item (item_id, category, seller_id, name, quantity, sale_price, keywords, condition) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = conn.prepareStatement(insertQuery, new String[]{"item_id"})) {
            preparedStatement.setString(1, request.getItemId());
            preparedStatement.setInt(2, request.getCategory());
            preparedStatement.setInt(3, request.getSellerId());
            preparedStatement.setString(4, request.getName());
            preparedStatement.setInt(5, request.getQuantity());
            preparedStatement.setDouble(6, request.getSalePrice());
            preparedStatement.setArray(7, conn.createArrayOf("VARCHAR", request.getKeyWordsList().toArray()));
            preparedStatement.setString(8, request.getCondition());

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    itemId = generatedKeys.getString(1); // Return the generated ID
                }
            }
        } catch (Exception e) {
            System.out.println("Error while adding item: " + e.getMessage());
        }
        responseObserver.onNext(ItemOuterClass.AddItemResponse.newBuilder().setSuccess(!itemId.isEmpty()).setItemId(itemId).build());
        responseObserver.onCompleted();
    }

    @Override
    public void updateItemPrice(ItemOuterClass.UpdateItemPriceRequest request,
                                io.grpc.stub.StreamObserver<ItemOuterClass.UpdateItemPriceResponse> responseObserver) {
        String updateQuery = "UPDATE item SET sale_price = ? WHERE item_id = ?";
        int rows = -1;
        try (PreparedStatement preparedStatement = conn.prepareStatement(updateQuery)) {
            preparedStatement.setDouble(1, request.getNewSalePrice());
            preparedStatement.setString(2, request.getItemId());

            rows = preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error while updating item price: " + e.getMessage());
        }
        responseObserver.onNext(ItemOuterClass.UpdateItemPriceResponse.newBuilder().setSuccess(rows > 0).build());
        responseObserver.onCompleted();
    }

    @Override
    public void getItem(ItemOuterClass.GetItemRequest request,
                        io.grpc.stub.StreamObserver<ItemOuterClass.Item> responseObserver) {
        ItemOuterClass.Item item = null;
        String query = "SELECT * FROM item WHERE item_id = ?";
        try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setString(1, request.getItemId());
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                List<String> keywords = new ArrayList<>();
                Array keywordsArray = rs.getArray("keywords");
                if (keywordsArray != null) {
                    Object[] keywordsObject = (Object[]) keywordsArray.getArray();
                    for (Object keyword : keywordsObject) {
                        keywords.add((String) keyword);
                    }
                }
                item = ItemOuterClass.Item.newBuilder()
                        .setId(rs.getInt("id"))
                        .setItemId(rs.getString("item_id"))
                        .setName(rs.getString("name"))
                        .setCategory(rs.getInt("category"))
                        .setCondition(rs.getString("condition"))
                        .setQuantity(rs.getInt("quantity"))
                        .setSalePrice(rs.getDouble("sale_price"))
                        .setSellerId(rs.getInt("seller_id"))
                        .addAllKeyWords(keywords)
                        .build();
            }
        } catch (Exception e) {
            System.out.println("Error while getting item: " + e.getMessage());
        }
        responseObserver.onNext(item);
        responseObserver.onCompleted();
    }

    @Override
    public void updateItemQuantity(ItemOuterClass.UpdateItemQuantityRequest request,
                                   io.grpc.stub.StreamObserver<ItemOuterClass.UpdateItemQuantityResponse> responseObserver) {
        String updateQuery = "UPDATE item SET quantity = quantity - ? WHERE item_id = ?";
        int rows = -1;
        try (PreparedStatement preparedStatement = conn.prepareStatement(updateQuery)) {
            preparedStatement.setInt(1, request.getQuantityToReduce());
            preparedStatement.setString(2, request.getItemId());

            rows = preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error while updating item quantity: " + e.getMessage());
        }
        responseObserver.onNext(ItemOuterClass.UpdateItemQuantityResponse.newBuilder().setSuccess(rows > 0).build());
        responseObserver.onCompleted();
    }

    @Override
    public void getItemsBySellerId(ItemOuterClass.GetItemsBySellerIdRequest request,
                                   io.grpc.stub.StreamObserver<ItemOuterClass.Item> responseObserver) {
        String query = "SELECT * FROM item WHERE seller_id = ?";
        try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setInt(1, request.getSellerId());
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                List<String> keywords = new ArrayList<>();
                Array keywordsArray = rs.getArray("keywords");
                if (keywordsArray != null) {
                    Object[] keywordsObject = (Object[]) keywordsArray.getArray();
                    for (Object keyword : keywordsObject) {
                        keywords.add((String) keyword);
                    }
                }

                ItemOuterClass.Item item = ItemOuterClass.Item.newBuilder()
                        .setId(rs.getInt("id"))
                        .setItemId(rs.getString("item_id"))
                        .setName(rs.getString("name"))
                        .setCategory(rs.getInt("category"))
                        .setCondition(rs.getString("condition"))
                        .setQuantity(rs.getInt("quantity"))
                        .setSalePrice(rs.getDouble("sale_price"))
                        .setSellerId(rs.getInt("seller_id"))
                        .addAllKeyWords(keywords)
                        .build();

                responseObserver.onNext(item);
            }
        } catch (Exception e) {
            System.out.println("Error while getting items by seller id: " + e.getMessage());
        }
        responseObserver.onCompleted();
    }

    @Override
    public void getItemsByCategoryAndKeywords(ItemOuterClass.GetItemsByCategoryAndKeywordsRequest request,
                                              io.grpc.stub.StreamObserver<ItemOuterClass.Item> responseObserver) {
        String query = "SELECT * FROM item WHERE category = ? AND ARRAY[?]::VARCHAR[] && keywords";
        try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setInt(1, request.getCategoryId());
            preparedStatement.setArray(2, conn.createArrayOf("VARCHAR", request.getKeywordsListList().toArray(new String[0])));

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                List<String> keywords = new ArrayList<>();
                Array keywordsArray = rs.getArray("keywords");
                if (keywordsArray != null) {
                    Object[] keywordsObject = (Object[]) keywordsArray.getArray();
                    for (Object keyword : keywordsObject) {
                        keywords.add((String) keyword);
                    }
                }

                ItemOuterClass.Item item = ItemOuterClass.Item.newBuilder()
                        .setId(rs.getInt("id"))
                        .setItemId(rs.getString("item_id"))
                        .setName(rs.getString("name"))
                        .setCategory(rs.getInt("category"))
                        .setCondition(rs.getString("condition"))
                        .setQuantity(rs.getInt("quantity"))
                        .setSalePrice(rs.getDouble("sale_price"))
                        .setSellerId(rs.getInt("seller_id"))
                        .addAllKeyWords(keywords)
                        .build();

                responseObserver.onNext(item);
            }
        } catch (Exception e) {
            System.out.println("Error while getting items by category and keywords: " + e.getMessage());
        }
        responseObserver.onCompleted();
    }

}
