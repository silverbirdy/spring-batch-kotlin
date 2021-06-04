package kr.sample.batch.job.reader

import kr.sample.model.Sample
import org.springframework.batch.item.ItemReader


interface SampleItemReader {
    fun reader(): ItemReader<Sample>
}
