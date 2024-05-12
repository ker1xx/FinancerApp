package com.example.kursovaya2024.DatabaseUtil

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.kursovaya2024.DatabaseUtil.Dao.BigSpendingDao
import com.example.kursovaya2024.DatabaseUtil.Dao.DailyStatisticDao
import com.example.kursovaya2024.DatabaseUtil.Dao.IncomeTypeDao
import com.example.kursovaya2024.DatabaseUtil.Dao.OperationDao
import com.example.kursovaya2024.DatabaseUtil.Dao.SpendingTypeDao
import com.example.kursovaya2024.DatabaseUtil.Dao.UserDao
import com.example.kursovaya2024.Models.DtoModel.BigSpendingDto
import com.example.kursovaya2024.Models.DtoModel.DailyStatisticDto
import com.example.kursovaya2024.Models.DtoModel.IncomeTypeDto
import com.example.kursovaya2024.Models.DtoModel.OperationDto
import com.example.kursovaya2024.Models.DtoModel.SpendingTypeDto
import com.example.kursovaya2024.Models.DtoModel.UserDto


@Database(entities = [BigSpendingDto::class,
                      DailyStatisticDto::class,
                      IncomeTypeDto::class,
                      OperationDto::class,
                      SpendingTypeDto::class,
                      UserDto::class], version = 1, exportSchema = false)
public abstract class AppDatabase : RoomDatabase() {

    public abstract fun bigSpendingDao() : BigSpendingDao
    public abstract fun dailyStatisticDao() : DailyStatisticDao
    public abstract fun incomeTypeDao() : IncomeTypeDao
    public abstract fun operationDao() : OperationDao
    public abstract fun spendingTypeDao() : SpendingTypeDao
    public abstract fun userDao() : UserDao

    companion object{
        private lateinit var instance : AppDatabase

        public fun getDbInstance(context : Context) : AppDatabase{
            if (instance == null)
            {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "FinancerDb"
                ).allowMainThreadQueries()
                    .build()
            }
            return instance
        }

    }




}