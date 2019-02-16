package database;

import slick.jdbc.JdbcBackend._

sealed case class DataBasePool(executor: Database)

object DataBasePool {

  private val db: Database = Database.forConfig("db")

  def getConnection: DataBasePool = DataBasePool(db)

}
