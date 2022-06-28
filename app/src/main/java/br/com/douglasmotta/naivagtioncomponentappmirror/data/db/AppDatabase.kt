package br.com.douglasmotta.naivagtioncomponentappmirror.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.douglasmotta.naivagtioncomponentappmirror.data.db.dao.UserDAO

@Database( entities = [UserEntity::class], version = 1 )
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDAO() : UserDAO

    companion object {
        // padrão singleton para previnir a criação de várias instancias do database ao mesmo tempo
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase( context: Context ) : AppDatabase {
            val tempInstance = INSTANCE
            if ( tempInstance != null ) {
                return tempInstance
            }
            synchronized( this ) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}