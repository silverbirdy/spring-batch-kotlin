package kr.sample.batch.job.listener

import java.lang.Exception
import kr.sample.model.Sample
import org.springframework.batch.core.ItemReadListener
import org.springframework.stereotype.Component

@Component
class SampleItemReaderListener : ItemReadListener<Sample> {
    override fun beforeRead() {
        println("beforeRead")
    }

    override fun afterRead(item: Sample) {
        println("afterRead : ${item.id} ${item.count}")
    }

    override fun onReadError(ex: Exception) {
        println("ReadError")
    }
}
