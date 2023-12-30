# My Spring Boot Application

This is a simple Spring Boot application that connects to a MSSQL database.

## copilot prompt
```
@workspace /new 
幫我產生一個SpringBoot專案，
專案包含 SpringBoot version 2.5.4 mssql-jdbc mybatis lombok Java Version 11 不要用JPA, 不要用xml mapper
並且幫我連線到 MsSqlServer url: Localhost 1433 username: TMMPALTU password:Pot12345 dataBaseName: testdb
專案名稱 SpringBootMybatisExample, 專案必須包含一個Controller一個Service一個mapper，一個user model
Controller呼叫Server, Server呼叫mapper，mapper讀取users table將資料放入user model，
mapper使用java annaction sql 不要用xml mapper，
user model 包含id name email 三個欄位，
userTable 包含id name email 三個欄位
```

## Prerequisites

- Java 11 or higher
- Maven
- MSSQL Server

## Setting up the Database
### 下載 mssql，記憶體要2.5G
```
docker pull mcr.microsoft.com/mssql/server:2022-latest
```

### 啟動mssql，指定UTF-8編碼 (密碼是<YourStrong@Passw0rd>)
```
docker run \
-e "ACCEPT_EULA=Y" \
-e "MSSQL_SA_PASSWORD=<YourStrong@Passw0rd>" \
-e "MSSQL_COLLATION=LATIN1_GENERAL_100_CI_AS_SC_UTF8" \
-p 1433:1433 \
--name mssql -h mssql \
-d mcr.microsoft.com/mssql/server:2022-latest
```

### 新增登入帳號及密碼
```

docker exec -it mssql /opt/mssql-tools/bin/sqlcmd -S localhost -U SA -P "<YourStrong@Passw0rd>" -Q "CREATE LOGIN TMMPALTU WITH PASSWORD = 'Pot12345'"
```

### 新增測試資料庫
```
docker exec -it mssql /opt/mssql-tools/bin/sqlcmd -S localhost -U SA -P "<YourStrong@Passw0rd>"  -Q "create database testdb"
```

### 開放資料庫權限給新建立的使用者
```
docker exec -it mssql /opt/mssql-tools/bin/sqlcmd -S localhost -U SA -P "<YourStrong@Passw0rd>"  -Q "ALTER SERVER ROLE [sysadmin] ADD MEMBER TMMPALTU"
```

### 驗證是否成功添加
```
docker exec -it mssql /opt/mssql-tools/bin/sqlcmd -S localhost -U SA -P "<YourStrong@Passw0rd>"  -Q "SELECT IS_SRVROLEMEMBER ('sysadmin', 'TMMPALTU')"
```

### 如果您想要從 `sysadmin` 角色中移除 `TMMPALTU`，您可以使用以下的 SQL 語句：
```
docker exec -it mssql /opt/mssql-tools/bin/sqlcmd -S localhost -U SA -P "<YourStrong@Passw0rd>"  -Q "ALTER SERVER ROLE [sysadmin] DROP MEMBER TMMPALTU"
```

### 產生新table
```
docker exec -it mssql /opt/mssql-tools/bin/sqlcmd -S localhost -U TMMPALTU -P "Pot12345"  -Q "CREATE TABLE testdb.dbo.users (
    id INT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL
);"
```

### 新增資料
```
docker exec -it mssql /opt/mssql-tools/bin/sqlcmd -S localhost -U TMMPALTU -P "Pot12345"  -Q "insert into testdb.dbo.users (id, name, email) values (1, 'test', 'test@example.com')"
docker exec -it mssql /opt/mssql-tools/bin/sqlcmd -S localhost -U TMMPALTU -P "Pot12345"  -Q "insert into testdb.dbo.users (id, name, email) values (2, '你的名字', 'test@example.com')"
docker exec -it mssql /opt/mssql-tools/bin/sqlcmd -S localhost -U TMMPALTU -P "Pot12345"  -Q "select * from testdb.dbo.users"
```

## Building the Application

You can build the application using Maven:

```
mvn clean install
```

This will compile the application, run the tests, and create a JAR file in the `target` directory.

## Running the Application

You can run the application using the `java -jar` command:

```
java -jar target/springboot-mybatis-example-0.0.1-SNAPSHOT.jar
```

This will start the application on port 8080.

## Using the Application

Once the application is running, you can access it at `http://localhost:8080/models/1`.

## Configuring the Database Connection

The database connection details are configured in the `src/main/resources/application.properties` file. You will need to update these details to match your MSSQL Server configuration.

## Database Schema

The database schema is defined in the `src/main/resources/db/mssql/schema.sql` file. This schema will be automatically applied when the application starts.

## Testing the Application

You can run the tests using Maven:

```
mvn test
```

This will run all the tests in the `src/test` directory.