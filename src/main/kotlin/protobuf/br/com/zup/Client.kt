package protobuf.br.com.zup

import io.grpc.ManagedChannelBuilder

fun main() {

    val channel = ManagedChannelBuilder
        .forAddress("localhost", 50051)
        .usePlaintext()
        .build()

    val request = CarroRequest.newBuilder()
        .setModelo("Gol")
        .setPlaca("HPX-1234")
        .setAno(1984)
        .setTipoDeCombustivel(TipoDeCombustivel.ALCOOL)
        .setProprietario(Proprietario.newBuilder()
            .setCpf("111.222.333.44")
            .setNome("Fulano de Tal")
        )
        .build()

    val client = CarroServiceGrpc.newBlockingStub(channel)
    val response = client.cadastrar(request)

    println(response)
}