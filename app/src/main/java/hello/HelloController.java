package hello;				
				
import org.springframework.web.bind.annotation.GetMapping;				
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.*;	
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit ;
import lombok.extern.slf4j.Slf4j;
import java.util.List;

// for JDBC
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.io.IOException;
import java.io.InputStream;

// ibatis

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import hello.entity.PuppyEntity;
import hello.entity.UserEntity;


@Slf4j
@RestController
//@RequestMapping(value = "/sample/db", produces = "application/json; charset=UTF-8")			
public class HelloController {			
	
    @RequestMapping(value = "/", method = RequestMethod.GET)			
	public String root() {			
		return "V1";		
	}	
	@RequestMapping(value = "/home", method = RequestMethod.GET)			
	public String index() {			
		return "Greetings from Spring Boot!";		
	}			

    @RequestMapping(value = "/health" , method = RequestMethod.GET)
    public String health(@RequestParam Long time) {
        log.error("java start");
        /*
        try {
            TimeUnit.SECONDS.sleep(time);
        }catch (InterruptedException e) {
            log.error("被中断了");
        }catch (Exception e) {
            log.error("其他异常",e);
        }
        */
        log.error("java end");
        return "health is good";
    }

    @RequestMapping(value = "/listinfo" , method = RequestMethod.GET)
    public String listInfo(@RequestParam Long time) {
        log.error("java start");
        String nameList = "NameList: ";
        
        Connection c = null;
        Statement stmt = null;
     
        try {
            log.error("JDBC start");
           Class.forName("org.postgresql.Driver");
     
        //   c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/tmp_db?socketTimeout=29","postgres", "123456");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/tmp_db","postgres", "123456");
     
        //     c.setAutoCommit(false);
     
            log.error("Start DB Connection");
        
           stmt = c.createStatement();
           String sqlsleep = "pg_sleep(" + time.toString() + ")," ;
           String sqlselect =  "select " + sqlsleep + "name from tbl_puppy limit 1";
           ResultSet rs = stmt.executeQuery( sqlselect );
           
           while ( rs.next() ) {
     
              //int albumid = rs.getInt("AlbumId");
     
              nameList = nameList + rs.getString("name") + ";";
            
              //int artistid  = rs.getInt("ArtistId");
     
              //System.out.printf( "AlbumId = %s , Title = %s, ArtistId = %s ", albumid,title, artistid );
     
              //System.out.println();
     
           }
     
           rs.close();
     
           stmt.close();
     
           c.close();
           log.error("End db connection");
           log.error("JDBC end");
     
        } catch ( Exception e ) {
     
           System.err.println( e.getClass().getName()+": "+ e.getMessage() );
           String errInfo = "DB Connection error: " + e.getClass().getName()+": "+ e.getMessage();
           log.error("Exception db connection");
           nameList = errInfo;
           log.error(errInfo);
           log.error("JDBC end");
     
        }
        log.error("java end");
        return nameList;
    }

    @RequestMapping(value = "/getusr",method = RequestMethod.GET)
    public String health3(@RequestParam int time) throws IOException  {
        String resource = "PuppyRepository.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();   
        //List<UserEntity> users = sqlSession.selectList("test.selectAll");
        UserEntity user = sqlSession.selectOne("test.selectOne");
        return user.toString();
  
    }

}				
