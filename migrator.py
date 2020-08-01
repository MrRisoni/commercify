import mysql.connector
import os




try:
    connection = mysql.connector.connect(user=os.environ['SPRING_APP_DB_USR'], password=os.environ['SPRING_APP_DB_PASSWD'],
                              host=os.environ['SPRING_APP_DB_HOST'],
                              database=os.environ['SPRING_APP_COMMERCIFY_DB_NAME'])


    if connection.is_connected():
        db_Info = connection.get_server_info()
        print("Connected to MySQL Server version ", db_Info)
        cursor = connection.cursor()
        cursor.execute("select database();")
        record = cursor.fetchone()
        print("You're connected to database: ", record)

except Error as e:
    print("Error while connecting to MySQL", e)
finally:
    if (connection.is_connected()):
        cursor.close()
        connection.close()
        print("MySQL connection is closed")
        
        
connection.close()
