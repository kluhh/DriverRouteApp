package com.example.love2.domain.use_case.get_route
import com.example.love2.common.Resource
import com.example.love2.data.remote.dto.toRouteItem
import com.example.love2.data.repository.Repository
import com.example.love2.domain.model.RouteItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetRouteUseCase @Inject constructor(private val repository: Repository) {

    // Operator function for use case invocation
    operator fun invoke(): Flow<Resource<List<RouteItem>>> = flow {
        try {
            // Emit loading status before starting data fetch
            emit(Resource.Loading())

            // Fetch server response
            val serverResponse = repository.getRoute()

            // Map each RouteItemDto to a RouteItem and store them in a list
            val route = serverResponse.routes.map { it.toRouteItem() }

            // Emit the list of routes as a success resource
            emit(Resource.Success(route))
        }  catch (e: HttpException) {
            // Handle network errors
            emit(Resource.Error(e.localizedMessage ?: "Error"))
        } catch (e: IOException) {
            // Handle other I/O exceptions (e.g., no internet connection)
            emit(Resource.Error("Could not reach server."))
        }
    }

}