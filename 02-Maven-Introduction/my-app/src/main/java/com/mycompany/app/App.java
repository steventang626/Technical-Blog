package com.mycompany.app;

import org.apache.commons.lang3.StringUtils;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );

        String prefix = "yuhan";
        String string = "Yuhan's blog is useful.";
        System.out.println(StringUtils.startsWithIgnoreCase(string, prefix));
    }
}
