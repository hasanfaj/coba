Inserting an Image into SQL Server

public class InsertBlob_SQLServer
    {
     static Connection conn = null;
    
     public InsertBlob_SQLServer()
         {
         try
             {
             Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
             conn = DriverManager.getConnection("jdbc:odbc:javaxxx", "userid", "pwd");
         }
         catch(Exception e)
             {
             e.printStackTrace();
         }
     }
     public static void main(String[] args)
         {
         InsertBlob_SQLServer insertBlob1 = new InsertBlob_SQLServer();
         if(args.length != 2)
             {
             System.out.println("Usage: java InsertBlob_SQLServer FileName File");
             System.out.println("Example: java InsertBlob_SQLServer myImage.jpg \"C:\\\\MyFolder\\\\myImage.jpg\"");
         }
         else
             {
             try
                 {
                 insertBlob1.insPic(conn, args[0], args[1]);
             }
             catch(Exception e)
                 {
                 e.printStackTrace();
             }
             finally
                 {
                 insertBlob1 = null;
             }
         }
     }
    
     private void insPic(Connection c, String name, String fName)
         {
         try
             {
             File f = new File(fName);
             FileInputStream in = new FileInputStream(f);
             byte[] image = new byte[(int) f.length()];
             in.read(image);
             // Below: the question marks are IN parameter placeholders.
             String sql = "INSERT INTO testImage VALUES(?,?)";
             PreparedStatement stmt = c.prepareStatement(sql);
             stmt.setString(1, name);
             stmt.setBytes(2, image);
             stmt.executeUpdate();
             stmt.close();
         }
         catch (SQLException e)
             {
             System.out.print(e.getMessage());
         }
         catch (IOException e)
             {
             System.out.print(e.getMessage());
         }
     }
}
//create table testImage(fname varchar(100),img image)
