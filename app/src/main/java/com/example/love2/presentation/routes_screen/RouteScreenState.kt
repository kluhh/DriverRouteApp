import com.example.love2.domain.model.RouteItem


data class RouteScreenState(
    val isLoading: Boolean = false,
    val route: List<RouteItem?>? = emptyList(),
    val error: String = ""
)