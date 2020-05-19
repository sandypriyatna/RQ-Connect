package id.credeva.rqconnect.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import id.credeva.rqconnect.data.db.entities.Article

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAllArticle(quotes: List<Article>)

    @Query("SELECT * FROM Article")
    fun getAllArticle(): LiveData<List<Article>>

}