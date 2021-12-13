package protobuf.br.com.zup

import io.grpc.ServerBuilder
import io.grpc.stub.StreamObserver
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*


fun main() {

    val server = ServerBuilder
        .forPort(50051)
        .addService(CarroEndpoint())
        .build()

    server.start()
    server.awaitTermination()
}

class CarroEndpoint : CarroServiceGrpc.CarroServiceImplBase(){

    override fun cadastrar(request: CarroRequest?, responseObserver: StreamObserver<CarroResponse>?) {
//        super.cadastrar(request, responseObserver)

        println(request!!)

        val uuid = UUID.randomUUID()
        val id = uuid.toString()

        val instant = LocalDateTime.now().atZone(ZoneId.of("UTC")).toInstant()
        val criadoEm = com.google.protobuf.Timestamp.newBuilder()
            .setSeconds(instant.epochSecond)
            .setNanos(instant.nano)
            .build()

        val response = CarroResponse.newBuilder()
            .setID(id)
            .setCriadoEm(criadoEm)
            .build()
        responseObserver?.onNext(response)
        responseObserver?.onCompleted()
    }
}