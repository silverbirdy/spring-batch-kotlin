package kr.sample.batch.job.reader

import kr.sample.model.Sample
import org.springframework.batch.item.ItemReader
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder
import org.springframework.core.io.ClassPathResource

class SampleCsvItemReader : SampleItemReader {
    override fun reader(): ItemReader<Sample> {
        return FlatFileItemReaderBuilder<Sample>()
            .name("sampleCsvItemReader")
            .resource(ClassPathResource("sample.csv"))
            .delimited()
            .names("id", "count")
            .targetType(Sample::class.java)
            .build()
    }
}
