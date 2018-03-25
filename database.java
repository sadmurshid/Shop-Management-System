package project;

import java.sql.*;

public class database {

    private static String databaseName="shop_management_database";
    private static String [] [] tableNameAndParameter=null;
    private static Connection conn = null;
    private static Statement statement;
    private static String sql;
    private static ResultSet resultSet, rs, rs1;

    public database(String address, String user, String password)
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(address, user, password);
            System.out.println("conn built");
            databaseExist(databaseName);
            statement = conn.createStatement();
            sql = "use " + databaseName;
            statement.executeUpdate(sql);
            createTableNameAndParameter();
            for (int i = 0; i < tableNameAndParameter.length; i++) {
                if (!tableExist(databaseName, tableNameAndParameter[i][0]))
                    createTable(databaseName, tableNameAndParameter[i][0], tableNameAndParameter[i][1]);
            }

        } catch (Exception e){
            e.printStackTrace();
        }

    }

    public database()
    {

    }

/*  Method Name: createTableNameAndParameter
    Parameter  : null
    Return     : void
    Purpose    : Initiate tableNameAndParameter String array which consists [tableName][parameters]
*/
    private static void createTableNameAndParameter()
    {
        tableNameAndParameter=new String[5][2];
        tableNameAndParameter[0][0]="user";
        tableNameAndParameter[0][1]="user_ID int not null AUTO_INCREMENT,"+
                                     "name varchar(40),"+
                                     "password varchar(32),"+
                                     "phone_number varchar(11),"+
                                     "address varchar(100),"+
                                     "email varchar(50),"+
                                     "backgroundColor varchar(15),"+
                                     "inventory_access int not null,"+
                                     "batch_access int not null,"+
                                     "order_access int not null,"+
                                     "debits_access int not null,"+
                                     "PRIMARY KEY (user_ID)";
        tableNameAndParameter[1][0]="inventory";
        tableNameAndParameter[1][1]="product_ID int not null,"+
                                    "company_name varchar(20) not null,"+
                                    "product_name varchar(20) not null,"+
                                    "serial int not null,"+
                                    "quantity double not null,"+
                                    "sell_price double not null,"+
                                    "buy_price double not null,"+
                                    "max_discount double not null,"+
                                    "sr_name varchar(40),"+
                                    "sr_phone_number varchar(11),"+
                                    "starting_inventory double not null,"+
                                    "minimum_require double,"+
                                    "category varchar(20),"+
                                    "product_description varchar(50),"+
                                    "PRIMARY KEY (product_ID)";
        tableNameAndParameter[2][0]="batch";
        tableNameAndParameter[2][1]="product_ID int not null,"+
                                    "buy_price double not null,"+
                                    "quantity double not null,"+
                                    "buy_date date not null,"+
                                    "sr_name varchar(40),"+
                                    "PRIMARY KEY (product_ID, buy_date)";
        tableNameAndParameter[3][0]="orders";
        tableNameAndParameter[3][1]="order_ID int not null AUTO_INCREMENT,"+
                                    "product_ID int not null,"+
                                    "product_name varchar(20) not null,"+
                                    "quantity double not null,"+
                                    "sell_price double not null,"+
                                    "per_item_discount double,"+
                                    "total_discount double not null,"+
                                    "user_ID int not null,"+
                                    "customer_name varchar(40),"+
                                    "order_date date not null,"+
                                    "amount_received double not null,"+
                                    "amount_returned double not null,"+
                                    "PRIMARY KEY (order_ID)";
        tableNameAndParameter[4][0]="debits";
        tableNameAndParameter[4][1]="customer_ID int not null,"+
                                    "debits_ID int not null,"+
                                    "order_ID int not null,"+
                                    "date_given date,"+
                                    "date_return date,"+
                                    "PRIMARY KEY (customer_ID,debits_ID)";
    }

/*  Method Name: databaseExist
    Parameter  : String databaseName
    Return     : void
    Purpose    : Check a database using the given name. If don't exist then call createDatabase to create one.
*/
    private static void databaseExist(String databaseName)
    {
        String getDatabaseName;
        ResultSet resultSet=null;
        boolean requiredDatabase=false;
        try {
            resultSet = conn.getMetaData().getCatalogs();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        try{
            while(resultSet.next())
            {
                getDatabaseName=resultSet.getString(1);
                if(getDatabaseName.equals(databaseName))
                {
                    requiredDatabase=true;
                    break;
                }
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        if(requiredDatabase==false){
            createDatabase(databaseName);
        }
    }

/*  Method Name: createDatabase
    Parameter  : String database
    Purpose    : Create a database using the given name
*/
    private static void createDatabase(String database)
    {
        String sql="create database "+database+" ;";
        System.out.println(sql);
        Statement stmt=null;
        try{
            stmt=conn.createStatement();
            stmt.executeUpdate(sql);
            System.out.println("Database created successfully");
        }
        catch(Exception e)
        {
            System.out.println("Database creation failed\n");
            System.out.println(e);
            e.printStackTrace();
        }
    }

/*  Method Name: tableExist
    Parameter  : String databaseName, String tableName
    Purpose    : Check a table whether exist by the given name
    Return     : Boolean true if table exist false if don't exist
*/
    private static boolean tableExist(String databaseName, String tableName) throws Exception
    {
        boolean isTableExist=false;
        try{
            DatabaseMetaData metaData=conn.getMetaData();
            resultSet=metaData.getTables( databaseName, null,"%", null);
            String str;
            while(resultSet.next())
            {
                str=resultSet.getString("TABLE_NAME");
                if(str.equals(tableName)){
                    isTableExist=true;
                    break;
                }
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
//        System.out.println("Table Name : "+tableName+" Existence : "+isTableExist);
        return isTableExist;
    }

/*  Method Name: createTable
    Parameter  : String databaseName, String tableName, String nameParameter
                    for nameParameter the whole query inside "(" ")" must be provided
    Purpose    : Create a table by the given name
    Return     : Boolean true if table creation successful false if failed
*/
    private static boolean createTable(String databaseName, String tableName, String nameParameter)
    {
        boolean creationSuccessful=false;
        try{
            sql="use "+databaseName;
            statement.executeUpdate(sql);
            sql="create table "+tableName+" ("+nameParameter+");";
//            System.out.println(sql);
            statement.executeUpdate(sql);
            creationSuccessful=true;
        }
        catch(Exception e){
            System.out.println(e);
        }
        return creationSuccessful;
    }

    public boolean loginAllowed(String userName, String password)
    {
        boolean allowed=false;
        String tempUser=null, tempPassword=null;
//        System.out.println("user : "+userName+" password : "+password);
        try{
            sql="use "+databaseName;
            statement.executeUpdate(sql);
            sql="select name, password from user order by user_ID;";
            resultSet=statement.executeQuery(sql);
            while(resultSet.next())
            {
                tempUser=resultSet.getString("name");
                tempPassword=resultSet.getString("password");
                System.out.println(tempUser+" "+tempPassword);
                if(tempUser.equals(userName) && tempPassword.equals(password))
                    return true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return allowed;
    }

    private String userTableGetUserName(String userID)
    {
        String userName=null;
        String ID=null;
        try{
            sql="use "+databaseName;
            statement.executeUpdate(sql);
            sql="select name, userID from user order by userID;";
            resultSet=statement.executeQuery(sql);
            while(resultSet.next())
            {
                userName=resultSet.getString("name");
                ID=resultSet.getString("user_ID");
                if(userID.equals(ID))
                {
                    return userName;
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return userName;
    }

    private String userTableGetPassword(String userID)
    {
        String password=null;
        String ID=null;
        try{
            sql="use "+databaseName;
            statement.executeUpdate(sql);
            sql="select password, userID from user order by userID;";
            resultSet=statement.executeQuery(sql);
            while(resultSet.next())
            {
                password=resultSet.getString("password");
                ID=resultSet.getString("user_ID");
                if(userID.equals(ID))
                {
                    return password;
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }

        return password;
    }

    public String userTableGetBackgroundColor(String userName)
    {
        String color=null;
        String tempName;
        try{
            sql="use "+databaseName;
            statement.executeUpdate(sql);
            sql="select backgroundColor, name from user order by name;";
            resultSet=statement.executeQuery(sql);
            while(resultSet.next())
            {
                color=resultSet.getString("backgroundColor");
                tempName=resultSet.getString("name");
                if(tempName.equals(userName))
                {
                    return color;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return color;
    }

    public boolean addUser(String name, String password, String phone_number, String address, String email
                                , int inventory, int batch, int order, int debits, String b_color){
//        boolean boo=false;
        try{
            sql="use "+databaseName;
            statement.executeUpdate(sql);
            sql="select max(user_ID) from user";
            rs = statement.executeQuery(sql);
            rs.next();
            int max=rs.getInt("max(user_ID)");
            if(!accessIntCorrect(inventory)||!accessIntCorrect(batch)||!accessIntCorrect(order)
                            ||!accessIntCorrect(debits)){
                return false;
            }
            sql="insert into user values ("+(max+1)+", '"+name+"', '"+password+"', '"+ phone_number+"', '"+address+"', '"
                    +email+"', '"+b_color+"', "+ inventory+", "+batch+", "+order+", "+debits+");";
            System.out.println(sql);
            statement.executeUpdate(sql);
            return true;

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }

    private boolean accessIntCorrect(int integer){
        boolean boo=false;
        try{
            if(integer==0||integer==1)
                return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return  boo;
    }


/*  Method Name: nameExist
    Parameter  : String Name
    Purpose    : Check names from the user table whether exist by the given name
    Return     : Boolean true if name exist false if don't exist
*/
    public boolean nameExist(String name){
        boolean boo=false;
        try{
            String tempUser;
            sql="use "+databaseName;
            statement.executeUpdate(sql);
            sql="select name from user order by user_ID;";
            resultSet=statement.executeQuery(sql);
            while(resultSet.next())
            {
                tempUser=resultSet.getString("name");
//                System.out.println(tempUser+" "+tempPassword);
                if(tempUser.equals(name))
                    return true;
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return boo;
    }

}
