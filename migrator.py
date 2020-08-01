import mysql.connector
import os




try:
    connection = mysql.connector.connect(user=os.environ['SPRING_APP_DB_USR'], password=os.environ['SPRING_APP_DB_PASSWD'],
                              host=os.environ['SPRING_APP_DB_HOST'],
                              database=os.environ['SPRING_APP_COMMERCIFY_DB_NAME'])


    if connection.is_connected():
        db_Info = connection.get_server_info()
        cursor = connection.cursor()
        cursor.execute("select title from migrations")
        rows = cursor.fetchall()
        executed_migrations = [item[0] for item in rows]
        
        migrations_list_files = os.listdir("db_migrations")
        migrations_list = [m.replace('.sql','') for m in migrations_list_files]
        print (migrations_list)
        print (executed_migrations)

        pending = list(set(migrations_list)-set(executed_migrations))
        print ('pending')
        print (pending)

        

except Error as e:
    print("Error while connecting to MySQL", e)
finally:
    if (connection.is_connected()):
        cursor.close()
        connection.close()
        print("MySQL connection is closed")
        
        
connection.close()
