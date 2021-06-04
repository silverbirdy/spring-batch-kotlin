package kr.sample.config

import javax.sql.DataSource
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer
import org.springframework.context.annotation.Configuration

@Configuration
class BatchConfig : DefaultBatchConfigurer() {
    override fun setDataSource(dataSource: DataSource) {}
}
