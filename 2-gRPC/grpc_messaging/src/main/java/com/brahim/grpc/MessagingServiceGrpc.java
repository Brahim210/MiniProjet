package com.brahim.grpc;

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
    comments = "Source: messaging.proto")
public final class MessagingServiceGrpc {

  private MessagingServiceGrpc() {}

  public static final String SERVICE_NAME = "MessagingService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.brahim.grpc.Messaging.MessageRequest,
      com.brahim.grpc.Messaging.MessageResponse> getSendMessageMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SendMessage",
      requestType = com.brahim.grpc.Messaging.MessageRequest.class,
      responseType = com.brahim.grpc.Messaging.MessageResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.brahim.grpc.Messaging.MessageRequest,
      com.brahim.grpc.Messaging.MessageResponse> getSendMessageMethod() {
    io.grpc.MethodDescriptor<com.brahim.grpc.Messaging.MessageRequest, com.brahim.grpc.Messaging.MessageResponse> getSendMessageMethod;
    if ((getSendMessageMethod = MessagingServiceGrpc.getSendMessageMethod) == null) {
      synchronized (MessagingServiceGrpc.class) {
        if ((getSendMessageMethod = MessagingServiceGrpc.getSendMessageMethod) == null) {
          MessagingServiceGrpc.getSendMessageMethod = getSendMessageMethod = 
              io.grpc.MethodDescriptor.<com.brahim.grpc.Messaging.MessageRequest, com.brahim.grpc.Messaging.MessageResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "MessagingService", "SendMessage"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.brahim.grpc.Messaging.MessageRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.brahim.grpc.Messaging.MessageResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new MessagingServiceMethodDescriptorSupplier("SendMessage"))
                  .build();
          }
        }
     }
     return getSendMessageMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.brahim.grpc.Messaging.MessageRequest,
      com.brahim.grpc.Messaging.MessageResponse> getReceiveMessageMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ReceiveMessage",
      requestType = com.brahim.grpc.Messaging.MessageRequest.class,
      responseType = com.brahim.grpc.Messaging.MessageResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
  public static io.grpc.MethodDescriptor<com.brahim.grpc.Messaging.MessageRequest,
      com.brahim.grpc.Messaging.MessageResponse> getReceiveMessageMethod() {
    io.grpc.MethodDescriptor<com.brahim.grpc.Messaging.MessageRequest, com.brahim.grpc.Messaging.MessageResponse> getReceiveMessageMethod;
    if ((getReceiveMessageMethod = MessagingServiceGrpc.getReceiveMessageMethod) == null) {
      synchronized (MessagingServiceGrpc.class) {
        if ((getReceiveMessageMethod = MessagingServiceGrpc.getReceiveMessageMethod) == null) {
          MessagingServiceGrpc.getReceiveMessageMethod = getReceiveMessageMethod = 
              io.grpc.MethodDescriptor.<com.brahim.grpc.Messaging.MessageRequest, com.brahim.grpc.Messaging.MessageResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "MessagingService", "ReceiveMessage"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.brahim.grpc.Messaging.MessageRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.brahim.grpc.Messaging.MessageResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new MessagingServiceMethodDescriptorSupplier("ReceiveMessage"))
                  .build();
          }
        }
     }
     return getReceiveMessageMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static MessagingServiceStub newStub(io.grpc.Channel channel) {
    return new MessagingServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static MessagingServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new MessagingServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static MessagingServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new MessagingServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class MessagingServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void sendMessage(com.brahim.grpc.Messaging.MessageRequest request,
        io.grpc.stub.StreamObserver<com.brahim.grpc.Messaging.MessageResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSendMessageMethod(), responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<com.brahim.grpc.Messaging.MessageRequest> receiveMessage(
        io.grpc.stub.StreamObserver<com.brahim.grpc.Messaging.MessageResponse> responseObserver) {
      return asyncUnimplementedStreamingCall(getReceiveMessageMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getSendMessageMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.brahim.grpc.Messaging.MessageRequest,
                com.brahim.grpc.Messaging.MessageResponse>(
                  this, METHODID_SEND_MESSAGE)))
          .addMethod(
            getReceiveMessageMethod(),
            asyncBidiStreamingCall(
              new MethodHandlers<
                com.brahim.grpc.Messaging.MessageRequest,
                com.brahim.grpc.Messaging.MessageResponse>(
                  this, METHODID_RECEIVE_MESSAGE)))
          .build();
    }
  }

  /**
   */
  public static final class MessagingServiceStub extends io.grpc.stub.AbstractStub<MessagingServiceStub> {
    private MessagingServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private MessagingServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MessagingServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new MessagingServiceStub(channel, callOptions);
    }

    /**
     */
    public void sendMessage(com.brahim.grpc.Messaging.MessageRequest request,
        io.grpc.stub.StreamObserver<com.brahim.grpc.Messaging.MessageResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSendMessageMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<com.brahim.grpc.Messaging.MessageRequest> receiveMessage(
        io.grpc.stub.StreamObserver<com.brahim.grpc.Messaging.MessageResponse> responseObserver) {
      return asyncBidiStreamingCall(
          getChannel().newCall(getReceiveMessageMethod(), getCallOptions()), responseObserver);
    }
  }

  /**
   */
  public static final class MessagingServiceBlockingStub extends io.grpc.stub.AbstractStub<MessagingServiceBlockingStub> {
    private MessagingServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private MessagingServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MessagingServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new MessagingServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.brahim.grpc.Messaging.MessageResponse sendMessage(com.brahim.grpc.Messaging.MessageRequest request) {
      return blockingUnaryCall(
          getChannel(), getSendMessageMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class MessagingServiceFutureStub extends io.grpc.stub.AbstractStub<MessagingServiceFutureStub> {
    private MessagingServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private MessagingServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MessagingServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new MessagingServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.brahim.grpc.Messaging.MessageResponse> sendMessage(
        com.brahim.grpc.Messaging.MessageRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getSendMessageMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_SEND_MESSAGE = 0;
  private static final int METHODID_RECEIVE_MESSAGE = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final MessagingServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(MessagingServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_SEND_MESSAGE:
          serviceImpl.sendMessage((com.brahim.grpc.Messaging.MessageRequest) request,
              (io.grpc.stub.StreamObserver<com.brahim.grpc.Messaging.MessageResponse>) responseObserver);
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
        case METHODID_RECEIVE_MESSAGE:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.receiveMessage(
              (io.grpc.stub.StreamObserver<com.brahim.grpc.Messaging.MessageResponse>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class MessagingServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    MessagingServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.brahim.grpc.Messaging.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("MessagingService");
    }
  }

  private static final class MessagingServiceFileDescriptorSupplier
      extends MessagingServiceBaseDescriptorSupplier {
    MessagingServiceFileDescriptorSupplier() {}
  }

  private static final class MessagingServiceMethodDescriptorSupplier
      extends MessagingServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    MessagingServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (MessagingServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new MessagingServiceFileDescriptorSupplier())
              .addMethod(getSendMessageMethod())
              .addMethod(getReceiveMessageMethod())
              .build();
        }
      }
    }
    return result;
  }
}
