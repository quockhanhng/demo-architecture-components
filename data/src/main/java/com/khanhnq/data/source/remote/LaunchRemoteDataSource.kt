package com.khanhnq.data.source.remote

class LaunchRemoteDataSource(
    private val apiService: ApiService
) : BaseRemoteDataSource() {

    suspend fun getLaunch(id: String) = getResult {
        apiService.getLaunch(id)
    }

    suspend fun getLaunches() = getResult {
        apiService.getAllLaunches()
    }

    companion object {
        private var instance: LaunchRemoteDataSource? = null

        fun getInstance(apiService: ApiService) =
            instance ?: synchronized(this) {
                instance ?: LaunchRemoteDataSource(apiService).also {
                    instance = it
                }
            }
    }
}
