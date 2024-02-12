package grpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.15.0)",
    comments = "Source: feedback.proto")
public final class FeedbackServiceGrpc {

  private FeedbackServiceGrpc() {}

  public static final String SERVICE_NAME = "grpc.FeedbackService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<grpc.Feedback.SaveFeedbackRequest,
      grpc.Feedback.SaveFeedbackResponse> getSaveFeedbackMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SaveFeedback",
      requestType = grpc.Feedback.SaveFeedbackRequest.class,
      responseType = grpc.Feedback.SaveFeedbackResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<grpc.Feedback.SaveFeedbackRequest,
      grpc.Feedback.SaveFeedbackResponse> getSaveFeedbackMethod() {
    io.grpc.MethodDescriptor<grpc.Feedback.SaveFeedbackRequest, grpc.Feedback.SaveFeedbackResponse> getSaveFeedbackMethod;
    if ((getSaveFeedbackMethod = FeedbackServiceGrpc.getSaveFeedbackMethod) == null) {
      synchronized (FeedbackServiceGrpc.class) {
        if ((getSaveFeedbackMethod = FeedbackServiceGrpc.getSaveFeedbackMethod) == null) {
          FeedbackServiceGrpc.getSaveFeedbackMethod = getSaveFeedbackMethod = 
              io.grpc.MethodDescriptor.<grpc.Feedback.SaveFeedbackRequest, grpc.Feedback.SaveFeedbackResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "grpc.FeedbackService", "SaveFeedback"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.Feedback.SaveFeedbackRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.Feedback.SaveFeedbackResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new FeedbackServiceMethodDescriptorSupplier("SaveFeedback"))
                  .build();
          }
        }
     }
     return getSaveFeedbackMethod;
  }

  private static volatile io.grpc.MethodDescriptor<grpc.Feedback.CheckFeedbackExistenceRequest,
      grpc.Feedback.CheckFeedbackExistenceResponse> getCheckFeedbackExistenceMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CheckFeedbackExistence",
      requestType = grpc.Feedback.CheckFeedbackExistenceRequest.class,
      responseType = grpc.Feedback.CheckFeedbackExistenceResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<grpc.Feedback.CheckFeedbackExistenceRequest,
      grpc.Feedback.CheckFeedbackExistenceResponse> getCheckFeedbackExistenceMethod() {
    io.grpc.MethodDescriptor<grpc.Feedback.CheckFeedbackExistenceRequest, grpc.Feedback.CheckFeedbackExistenceResponse> getCheckFeedbackExistenceMethod;
    if ((getCheckFeedbackExistenceMethod = FeedbackServiceGrpc.getCheckFeedbackExistenceMethod) == null) {
      synchronized (FeedbackServiceGrpc.class) {
        if ((getCheckFeedbackExistenceMethod = FeedbackServiceGrpc.getCheckFeedbackExistenceMethod) == null) {
          FeedbackServiceGrpc.getCheckFeedbackExistenceMethod = getCheckFeedbackExistenceMethod = 
              io.grpc.MethodDescriptor.<grpc.Feedback.CheckFeedbackExistenceRequest, grpc.Feedback.CheckFeedbackExistenceResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "grpc.FeedbackService", "CheckFeedbackExistence"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.Feedback.CheckFeedbackExistenceRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  grpc.Feedback.CheckFeedbackExistenceResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new FeedbackServiceMethodDescriptorSupplier("CheckFeedbackExistence"))
                  .build();
          }
        }
     }
     return getCheckFeedbackExistenceMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static FeedbackServiceStub newStub(io.grpc.Channel channel) {
    return new FeedbackServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static FeedbackServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new FeedbackServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static FeedbackServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new FeedbackServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class FeedbackServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void saveFeedback(grpc.Feedback.SaveFeedbackRequest request,
        io.grpc.stub.StreamObserver<grpc.Feedback.SaveFeedbackResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSaveFeedbackMethod(), responseObserver);
    }

    /**
     */
    public void checkFeedbackExistence(grpc.Feedback.CheckFeedbackExistenceRequest request,
        io.grpc.stub.StreamObserver<grpc.Feedback.CheckFeedbackExistenceResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getCheckFeedbackExistenceMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getSaveFeedbackMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                grpc.Feedback.SaveFeedbackRequest,
                grpc.Feedback.SaveFeedbackResponse>(
                  this, METHODID_SAVE_FEEDBACK)))
          .addMethod(
            getCheckFeedbackExistenceMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                grpc.Feedback.CheckFeedbackExistenceRequest,
                grpc.Feedback.CheckFeedbackExistenceResponse>(
                  this, METHODID_CHECK_FEEDBACK_EXISTENCE)))
          .build();
    }
  }

  /**
   */
  public static final class FeedbackServiceStub extends io.grpc.stub.AbstractStub<FeedbackServiceStub> {
    private FeedbackServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private FeedbackServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected FeedbackServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new FeedbackServiceStub(channel, callOptions);
    }

    /**
     */
    public void saveFeedback(grpc.Feedback.SaveFeedbackRequest request,
        io.grpc.stub.StreamObserver<grpc.Feedback.SaveFeedbackResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSaveFeedbackMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void checkFeedbackExistence(grpc.Feedback.CheckFeedbackExistenceRequest request,
        io.grpc.stub.StreamObserver<grpc.Feedback.CheckFeedbackExistenceResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCheckFeedbackExistenceMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class FeedbackServiceBlockingStub extends io.grpc.stub.AbstractStub<FeedbackServiceBlockingStub> {
    private FeedbackServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private FeedbackServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected FeedbackServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new FeedbackServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public grpc.Feedback.SaveFeedbackResponse saveFeedback(grpc.Feedback.SaveFeedbackRequest request) {
      return blockingUnaryCall(
          getChannel(), getSaveFeedbackMethod(), getCallOptions(), request);
    }

    /**
     */
    public grpc.Feedback.CheckFeedbackExistenceResponse checkFeedbackExistence(grpc.Feedback.CheckFeedbackExistenceRequest request) {
      return blockingUnaryCall(
          getChannel(), getCheckFeedbackExistenceMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class FeedbackServiceFutureStub extends io.grpc.stub.AbstractStub<FeedbackServiceFutureStub> {
    private FeedbackServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private FeedbackServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected FeedbackServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new FeedbackServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<grpc.Feedback.SaveFeedbackResponse> saveFeedback(
        grpc.Feedback.SaveFeedbackRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getSaveFeedbackMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<grpc.Feedback.CheckFeedbackExistenceResponse> checkFeedbackExistence(
        grpc.Feedback.CheckFeedbackExistenceRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getCheckFeedbackExistenceMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_SAVE_FEEDBACK = 0;
  private static final int METHODID_CHECK_FEEDBACK_EXISTENCE = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final FeedbackServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(FeedbackServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_SAVE_FEEDBACK:
          serviceImpl.saveFeedback((grpc.Feedback.SaveFeedbackRequest) request,
              (io.grpc.stub.StreamObserver<grpc.Feedback.SaveFeedbackResponse>) responseObserver);
          break;
        case METHODID_CHECK_FEEDBACK_EXISTENCE:
          serviceImpl.checkFeedbackExistence((grpc.Feedback.CheckFeedbackExistenceRequest) request,
              (io.grpc.stub.StreamObserver<grpc.Feedback.CheckFeedbackExistenceResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class FeedbackServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    FeedbackServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return grpc.Feedback.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("FeedbackService");
    }
  }

  private static final class FeedbackServiceFileDescriptorSupplier
      extends FeedbackServiceBaseDescriptorSupplier {
    FeedbackServiceFileDescriptorSupplier() {}
  }

  private static final class FeedbackServiceMethodDescriptorSupplier
      extends FeedbackServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    FeedbackServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (FeedbackServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new FeedbackServiceFileDescriptorSupplier())
              .addMethod(getSaveFeedbackMethod())
              .addMethod(getCheckFeedbackExistenceMethod())
              .build();
        }
      }
    }
    return result;
  }
}
