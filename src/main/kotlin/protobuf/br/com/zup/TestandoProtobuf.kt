package protobuf.br.com.zup

import java.io.FileInputStream
import java.io.FileOutputStream


fun main() {
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

    // escrevemos o objeto trafegando na rede
    // serializando
    println(request)

    request.writeTo(FileOutputStream("carro-request.bin"))

    // Leitura do objeto do disco ou da rede
    // deserializando
    val request2 = CarroRequest.newBuilder()
        .mergeFrom(FileInputStream("carro-request.bin"))

    request2.setAno(1985).build()
    println(request2)

}