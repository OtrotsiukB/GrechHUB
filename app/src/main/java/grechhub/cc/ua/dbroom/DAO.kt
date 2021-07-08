package grechhub.cc.ua.dbroom

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import grechhub.cc.ua.data.Achievement

    @Dao
    interface DAO {
        @Query("SELECT * FROM achievementstable")
        fun getAllAchievements(): List<Achievement>

        @Insert
        fun insertAchievements(achievements:List<Achievement>)

        @Query("DELETE FROM achievementstable")
         fun deleteAllachievement()


    }
