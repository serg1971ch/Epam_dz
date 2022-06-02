package ru.shibanov.sergey;

import org.apache.commons.lang3.StringUtils;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        String name  = "Sergey Shibanov";
        boolean contained1 = StringUtils.containsAny(name, 'a', 'b', 's');
        System.out.println(contained1);
        System.out.println(StringUtils.upperCase(name));
    }
}
