package kr.sample.batch.job.step

import kr.sample.batch.job.reader.SampleJpaPagingItemReader
import kr.sample.model.Sample
import kr.sample.model.SampleRepository
import org.springframework.batch.core.Step
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory
import org.springframework.batch.item.ItemProcessor
import org.springframework.batch.item.ItemReader
import org.springframework.batch.item.ItemWriter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.persistence.EntityManagerFactory
import kr.sample.batch.job.listener.SampleItemReaderListener

@Configuration
class SampleStepConfig(
    private val stepBuilderFactory: StepBuilderFactory,
    private val entityManagerFactory: EntityManagerFactory,
    private val sampleRepository: SampleRepository,
    private val sampleItemReaderListener: SampleItemReaderListener
) {

    @Bean
    fun sampleCountStep(): Step {
        return stepBuilderFactory.get(STEP_NAME)
            .chunk<Sample, Sample>(CHUNK_SIZE)
            .reader(reader())
            .processor(processor())
            .writer(writer())
            .listener(sampleItemReaderListener)
            .build()
    }

    private fun reader(): ItemReader<Sample> {
//        return SampleCsvItemReader().reader()
        return SampleJpaPagingItemReader(entityManagerFactory).reader()
    }

    private fun processor(): ItemProcessor<Sample, Sample> {
        return ItemProcessor { sample ->
            sample.countUp()
            sample
        }
    }

    private fun writer(): ItemWriter<Sample> {
        return ItemWriter { sample ->
            sampleRepository.saveAll(sample)
        }
    }

    companion object {
        const val STEP_NAME = "SAMPLE_STEP"
        const val CHUNK_SIZE = 100
    }
}
