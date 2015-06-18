package com.focusdays2015.glass;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

public class ValueableObjectApplication extends Application
{
	   HashSet<Object> singletons = new HashSet<Object>();

	   public ValueableObjectApplication()
	   {
	      singletons.add(new ValueableObjectStore());
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