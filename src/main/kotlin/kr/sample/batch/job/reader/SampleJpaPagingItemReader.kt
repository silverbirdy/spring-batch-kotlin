package kr.sample.batch.job.reader

import kr.sample.model.Sample
import kr.sample.batch.job.step.SampleStepConfig
import org.springframework.batch.item.database.JpaPagingItemReader
import javax.persistence.EntityManagerFactory

class SampleJpaPagingItemReader(
    private val entityManagerFactory: EntityManagerFactory
) : SampleItemReader {
    override fun reader(): JpaPagingItemReader<Sample> {
        val reader: JpaPagingItemReader<Sample> = JpaPagingItemReader()
        reader.setQueryString("SELECT i FROM Sample i")
        reader.pageSize = SampleStepConfig.CHUNK_SIZE
        reader.setEntityManagerFactory(entityManagerFactory)
        return reader
    }
}
