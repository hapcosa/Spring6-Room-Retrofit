package com.example.spring6.di
import com.example.spring6.dataSource.restDataSource
import android.content.Context
import androidx.room.Room
import com.example.spring6.dataSource.dataBaseTelefono
import com.example.spring6.utils.constants.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton
import com.example.spring6.models.telefonoDao

@Module
@InstallIn(SingletonComponent::class)
class appModule {

    @Singleton
    @Provides
    @Named("BaseUrl")
    fun provideBaseUrl() = BASE_URL

    @Singleton
    @Provides
    fun provideRetrofit(@Named("BaseUrl") baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .build()
    }

    @Singleton
    @Provides
    fun restDataSource(retrofit: Retrofit): restDataSource =
        retrofit.create(restDataSource::class.java)

    @Singleton
    @Provides
    fun dataBasetelefono(@ApplicationContext context: Context): dataBaseTelefono {
        return Room.databaseBuilder(
            context,
            dataBaseTelefono::class.java,
            "telefono_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun telefonoDao(db: dataBaseTelefono): telefonoDao = db.telefonoDao()
}