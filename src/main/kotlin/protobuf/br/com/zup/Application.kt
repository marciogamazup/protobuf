package protobuf.br.com.zup

import io.micronaut.runtime.Micronaut.*
fun main(args: Array<String>) {
	build()
	    .args(*args)
		.packages("protobuf.br.com.zup")
		.start()
}

