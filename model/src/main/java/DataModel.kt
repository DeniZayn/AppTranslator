import com.example.model.viewmodel.Meanings


class DataModel (
        @field:SerializedName("text") val text: String?,
        @field:SerializedName("meanings") val meanings: List<Meanings>?
        )