package ibf2021.psf.day31.repositories;

public class SQL {
    
    public static final String SQL_GET_ALL_BOOKS = "select * from book2018";

    // select * from book2018 where title like '%abc%'
    public static final String SQL_GET_BOOKS_BY_TITLE = "select * from book2018 where title like ?";

    public static final String SQL_GET_ALL_BOOKS_BY_LIMIT_OFFSET =
            "select * from book2018 limit ? offset ?";

    public static final String SQL_GET_BOOKS_FORMAT = 
            "select distinct(format) from book2018 where format not like ''";
}
