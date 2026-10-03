import java.util.Scanner;
import java.sql.*;

public class file1
{

    String name,add,gender,pass;
    long aaddhar,mobile;


    //SAVING USER DETAILS INTO DATA BASE
    void NewAccount(String name,String add,long aaddhar,String gender,long mobile,String pass)
    {
        this.name=name;
        this.add=add;
        this.aaddhar=aaddhar;
        this.mobile=mobile;
        this.gender=gender;
        this.pass=pass;

        if(NewAccountquery(name,add,aaddhar,gender,mobile,pass)==1)
        {
            System.out.println("USER DETAILS SAVED SUCCESSFULLY!");
            rooms();

        }

        else
        {
            System.out.println("MOBILE NUMBER OR AADHAR ID MAY EXIST TRY USING OTHER!!");
        }

    }

    void login(long mobile,String pass)
    {

        if(loginquery(mobile,pass)==1)
        {
            System.out.println("LOGIN SUCCESSFULL");

            rooms();
        }

        else
        {
            System.out.println("CHECK YOUR LOGIN CREDINTIALS AND TRY AGAIN");
        }

    }

    int loginquery(long num, String pass)
{
    String sql = "SELECT * FROM person WHERE mobileno = ? AND password = ?";

    try
    {
        Connection con = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/hotelmanagement",
            "root",
            "NIGHT MARE 444"
        );

        PreparedStatement st = con.prepareStatement(sql);

        st.setLong(1, num);
        st.setString(2, pass);

        ResultSet rs = st.executeQuery();

        if (rs.next())
        {
            rs.close();
            st.close();
            con.close();
            return 1;
        }

        rs.close();
        st.close();
        con.close();
        return 0;
    }
    catch (Exception e)
    {
        e.printStackTrace();
        return -1;
    }
}

int NewAccountquery(String name, String add, long aadhar,
               String gender, long mobile, String pass)
{
    String sql = "INSERT INTO person " +
                 "(name, address, AadharId, gender, mobileno, password) " +
                 "VALUES (?, ?, ?, ?, ?, ?)";

    try
    {
        Connection con = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/hotelmanagement",
            "root",
            "NIGHT MARE 444"
        );

        PreparedStatement st = con.prepareStatement(sql);

        st.setString(1, name);
        st.setString(2, add);
        st.setLong(3, aadhar);
        st.setString(4, gender);
        st.setLong(5, mobile);
        st.setString(6, pass);

        int result = st.executeUpdate();

        st.close();
        con.close();

        return result;
    }
    catch(Exception Ignored)
    {
        return -1;
    }
}

    //DISPLAYING ROOM'S DETAILS
    static void rooms()
    {

        try{

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelmanagement",
            "root",
            "NIGHT MARE 444");


            String sql = "select room_type, total_rooms,available_rooms,price_per_night from rooms";

            PreparedStatement st= con.prepareStatement(sql);
            ResultSet rs=st.executeQuery();

            System.out.println("========================================================");
            System.out.println("               HOTEL MANAGEMENT SYSTEM");
            System.out.println("========================================================");

            while(rs.next())
            {
                String roomtype=rs.getString("room_type");
                int totalrooms=rs.getInt("total_rooms");
                int availablerooms = rs.getInt("available_rooms");
                double price=rs.getDouble("price_per_night");

                System.out.println("Room type :"+roomtype);
                System.out.println("Total Rooms :"+totalrooms);
                System.out.println("Available rooms :"+availablerooms);
                System.out.println("Price :"+price);
                System.out.println("========================================================");
                System.out.println(" ");

            }
        }

        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }        
    public static void main(String[]args)
    {
        Scanner sc=new Scanner(System.in);
        file1 ob = new file1();

        System.out.println("ENTER New TO FOR NEW BOOKING,LOGIN TO CHECK YOUR BOOKING :");
        String choice=sc.next();
        choice = choice.toUpperCase();

switch(choice)
{
    case "NEW":
        sc.nextLine();

        System.out.println("ENTER YOUR FULL NAME :");
        String name = sc.nextLine();

        System.out.println("ENTER YOUR ADDRESS :");
        String add = sc.nextLine();

        System.out.println("ENTER YOUR AADHAR NUMBER/ID :");
        long aadhar = sc.nextLong();

        System.out.println("ENTER YOUR GENDER :");
        String gender = sc.next();

        System.out.println("ENTER YOUR MOBILE NUMBER :");
        long mobile = sc.nextLong();

        sc.nextLine();

        System.out.println("ENTER A UNIQUE PASSWORD :");
        String pass = sc.nextLine();

        ob.NewAccount(name, add, aadhar, gender, mobile, pass);

        break;

    case "LOGIN":
        System.out.println("ENTER YOUR MOBILE NUMBER :");
        long num = sc.nextLong();
        sc.nextLine();
        System.out.println("ENTER THE PASSWORD :");
        sc.nextLine();
        String p = sc.nextLine();
        sc.nextLine();
        ob.login(num,p);

        break;

    default:
        System.out.println("ENTER A VALID INPUT!");
    sc.close();
    }
}
}
