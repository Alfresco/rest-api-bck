/*
 * Copyright (C) 2005-2016 Alfresco Software Limited.
 *
 * This file is part of Alfresco
 *
 * Alfresco is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Alfresco is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Alfresco. If not, see <http://www.gnu.org/licenses/>.
 */
package org.alfresco.util;

import org.alfresco.dataprep.SiteService;
import org.alfresco.dataprep.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.social.alfresco.api.entities.Site.Visibility;

public class DataLoader
{
    protected final static String ADMIN_USERNAME = "admin";
    protected final static String ADMIN_PASSWORD = "admin";
    protected final static String TEST_USER1 = "testUser1";
    protected final static String TEST_USER2 = "testUser2";
    protected final static String TEST_SITE_PUBLIC = "testSitePublic";
    protected final static String TEST_SITE_MODERATED = "testSiteModerated";
    protected final static String TEST_SITE_PRIVATE = "testSitePrivate";
    protected final static String TEST_DOMAIN = "-default-";
    
    protected static ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath*:dataprep-context.xml");
    
    public static void main(String[] args)
    {
        try
        {
            System.out.println("Starting data load");
            DataLoader loader = new DataLoader();
            loader.loadUsers();
            // TODO: Apply the Share Services AMP in build, then uncomment
            // loader.loadSites();
            System.out.println("Completed data load");
        }
        catch (Exception error)
        {
            System.err.println("Data load failed: " + error.getMessage());
            
            // make sure build systems know there was a failure
            System.exit(1);
        }
    }
    
    public void loadUsers()
    {
        // retrieve user service
        UserService userService = (UserService)ctx.getBean("userService");
        
        // create testUser1 if they don't already exist
        if (!userService.userExists(ADMIN_USERNAME, ADMIN_PASSWORD, TEST_USER1))
        {
            if (userService.create(ADMIN_USERNAME, ADMIN_PASSWORD, TEST_USER1, TEST_USER1, "test1@alfresco.com", "Test", "User1"))
            {
                System.out.println("Created user: " + TEST_USER1);
            }
            else
            {
                throw new RuntimeException("Failed to create user: " + TEST_USER1);
            }
        }
        else
        {
            System.out.println(TEST_USER1 + " already exists, skipping");
        }
        
        // create testUser1 if they don't already exist
        if (!userService.userExists(ADMIN_USERNAME, ADMIN_PASSWORD, TEST_USER2))
        {
            if (userService.create(ADMIN_USERNAME, ADMIN_PASSWORD, TEST_USER2, TEST_USER2, "test2@alfresco.com", "Test", "User2"))
            {
                System.out.println("Created user: " + TEST_USER2);
            }
            else
            {
                throw new RuntimeException("Failed to create user: " + TEST_USER2);
            }
        }
        else
        {
            System.out.println(TEST_USER2 + " already exists, skipping");
        }
    }
    
    public void loadSites()
    {
        // retrieve site service
        SiteService siteService = (SiteService)ctx.getBean("siteService");
        
        // create testSitePublic, if it doesn't exist
        if (!siteService.exists(TEST_SITE_PUBLIC, ADMIN_USERNAME, ADMIN_PASSWORD))
        {
            siteService.create(ADMIN_USERNAME, ADMIN_PASSWORD, TEST_DOMAIN, TEST_SITE_PUBLIC, "Public site", Visibility.PUBLIC);
            System.out.println("Created site: " + TEST_SITE_PUBLIC);
        }
        else
        {
            System.out.println(TEST_SITE_PUBLIC + " already exists, skipping");
        }
        
        // create testSiteModerated, if it doesn't exist
        if (!siteService.exists(TEST_SITE_MODERATED, ADMIN_USERNAME, ADMIN_PASSWORD))
        {
            siteService.create(ADMIN_USERNAME, ADMIN_PASSWORD, TEST_DOMAIN, TEST_SITE_MODERATED, "Moderated site", Visibility.MODERATED);
            System.out.println("Created site: " + TEST_SITE_MODERATED);
        }
        else
        {
            System.out.println(TEST_SITE_MODERATED + " already exists, skipping");
        }
        
        // create testSitePrivate, if it doesn't exist
        if (!siteService.exists(TEST_SITE_PRIVATE, ADMIN_USERNAME, ADMIN_PASSWORD))
        {
            siteService.create(ADMIN_USERNAME, ADMIN_PASSWORD, TEST_DOMAIN, TEST_SITE_PRIVATE, "Private site", Visibility.PRIVATE);
            System.out.println("Created site: " + TEST_SITE_PRIVATE);
        }
        else
        {
            System.out.println(TEST_SITE_PRIVATE + " already exists, skipping");
        }
    }
}
