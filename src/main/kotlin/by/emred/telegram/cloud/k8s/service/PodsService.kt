package by.emred.telegram.cloud.k8s.service

import io.kubernetes.client.openapi.ApiClient
import io.kubernetes.client.openapi.apis.CoreV1Api
import org.springframework.stereotype.Service

@Service
class PodsService(
    private val client: ApiClient
) {
    private val coreApi: CoreV1Api = CoreV1Api(client)

    fun getPods(namespace: String): String {
        try {
            val pods = coreApi.listNamespacedPod(namespace).execute()
            val podsList = pods.items
                ?.stream()
                ?.map { pod ->
                    val name = pod?.metadata?.name
                    val phase = pod.status?.phase
                    "- $name ($phase)"
                }?.toList()

            return podsList!!.joinToString("\n")
        } catch (e: Exception) {
            return "Connection refused to k8s"
        }
    }
}