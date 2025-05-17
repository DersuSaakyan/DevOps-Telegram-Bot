package by.emred.telegram.cloud.k8s.config

import io.kubernetes.client.openapi.ApiClient
import io.kubernetes.client.openapi.Configuration
import io.kubernetes.client.util.Config
import org.springframework.boot.SpringBootConfiguration
import org.springframework.context.annotation.Bean

@SpringBootConfiguration
class K8SClientProvider {
    @Bean
    fun apiClient(): ApiClient {
        // Поддерживает ~/.kube/config и in-cluster config
        val client: ApiClient = Config.defaultClient()
        Configuration.setDefaultApiClient(client)
        return client
    }
}