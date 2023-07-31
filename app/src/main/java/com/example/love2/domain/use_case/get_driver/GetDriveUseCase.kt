package com.example.love2.domain.use_case.get_driver

import com.example.love2.common.Resource
import com.example.love2.data.remote.dto.toDriverItem
import com.example.love2.data.repository.Repository
import com.example.love2.domain.model.DriverItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

// Use case class for getting driver data
class GetDriveUseCase @Inject constructor(private val repository: Repository) {
    // Operator function for use case invocation
    operator fun invoke(): Flow<Resource<List<DriverItem>>> = flow {
        try {
            // Emit loading status before starting data fetch
            emit(Resource.Loading())

            // Fetch server response
            val serverResponse = repository.getDrivers()

            // Map each DriverItemDto to a DriverItem and store them in a list
            val drivers = serverResponse.drivers.map { it.toDriverItem() }

            // Emit the list of drivers as a success resource
            emit(Resource.Success(drivers))
        } catch (e: HttpException) {
            // Handle network errors
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            // Handle other I/O exceptions
            emit(
                Resource.Error(
                    e.localizedMessage ?: "Couldn't reach server, check your internet connection"
                )
            )
        }
    }
}
