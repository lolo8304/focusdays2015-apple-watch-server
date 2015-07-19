package com.focusdays2015.eyes2drive.model;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

public class Eyes2DriveApplication extends Application
{
	   HashSet<Object> singletons = new HashSet<Object>();

	   public Eyes2DriveApplication()
	   {
	      singletons.add(new Accounts());
	   }

	   @Override
	   public Set<Class<?>> getClasses()
	   {
	      HashSet<Class<?>> set = new HashSet<Class<?>>();
	      return set;
	   }

	   @Override
	   public Set<Object> getSingletons()
	   {
	      return singletons;  
	   }
	}