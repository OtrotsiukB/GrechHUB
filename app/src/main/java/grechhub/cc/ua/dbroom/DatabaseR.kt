package grechhub.cc.ua.dbroom

import android.app.Application
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import grechhub.cc.ua.data.Achievement

@Database(entities = [Achievement::class], version = 1)
abstract class DatabaseR : RoomDatabase(){



    abstract fun DaoInDB():DAO

   /* val instance: DatabaseR by lazy {
        Room.databaseBuilder(

            DatabaseR::class.java,
            DATABASE_NAME
        ).allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }*/
    companion object {
        private const val DATABASE_NAME = "Achievements.db"

        fun create(applicationContext: Context): DatabaseR = Room.databaseBuilder(
            applicationContext,
            DatabaseR::class.java,
            DATABASE_NAME
        ).allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
       /* val instance: DatabaseR by lazy {
            Room.databaseBuilder(
                App,
                DatabaseR::class.java,
                DATABASE_NAME
            ).allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
        }*/

    }

}